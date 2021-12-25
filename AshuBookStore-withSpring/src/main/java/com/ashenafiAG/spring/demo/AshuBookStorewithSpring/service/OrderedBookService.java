package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Book;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Cart;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Item;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Order;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.OrderedBook;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository.OrderedBookRepository;

@Service
public class OrderedBookService {

	@Autowired 
	private OrderedBookRepository orderedBookRepository;
	
	@Autowired
	private BookService bookService;
	
	public void insert(Cart cart, Long orderId) {
		for(Item item : cart.getShoppingCart()) {
			OrderedBook orderedBook = new OrderedBook();
			orderedBook.setBookId(item.getBook().getId());
			orderedBook.setQuantity(item.getQuantity());
			orderedBook.setOrderId(orderId);
			orderedBook.setDateCreated(new Date());
			orderedBookRepository.save(orderedBook);
		}
	}
	
	public List<Item> getOrderedBooksBYOrderId(Long orderId){
		List<OrderedBook> orderedBooks = orderedBookRepository.findByOrderId(orderId);
		List<Item> list = new ArrayList<>();
		
		for(OrderedBook bookOrder : orderedBooks) {
			Book book = bookService.getBookById(bookOrder.getBookId());
			Item item = new Item(book, bookOrder.getQuantity());
			list.add(item);
		}
		
		return list;
	}
	
	public Integer getTotalQuantatity(List<Item> items) {
		int total = 0;
		for (Item item : items)
			total += item.getQuantity();

		return total;
	}
	
	public HashMap<Order, List<Item>> getOrderedBooks(List<Order> allOrderByUserId){
		HashMap<Order, List<Item>> orders = new HashMap<>();
		for(Order order:allOrderByUserId) 
			orders.put(order, getOrderedBooksBYOrderId(order.getOrderId()));
		return orders;
		
	}
}
