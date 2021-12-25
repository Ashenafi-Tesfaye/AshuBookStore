package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Book;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Cart;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Item;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.ShoppingCart;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.User;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private BookService bookService;
	
	public void insertItem(Item item, User user) {
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(user.getId());
		shoppingCart.setBookId(item.getBook().getId());
		shoppingCart.setDateCreated(new Date());
		shoppingCart.setQuantity(item.getQuantity());
		shoppingCartRepository.save(shoppingCart);
		
	}
	
	public void updateItem(Item item, User user) {
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserIdAndBookId(user.getId(), item.getBook().getId());
		shoppingCart.setQuantity(item.getQuantity());
		shoppingCartRepository.save(shoppingCart);
	}
	
	public void deleteItem(Item item, User user) {
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserIdAndBookId(user.getId(),item.getBook().getId());
		shoppingCartRepository.delete(shoppingCart);
	}
	
	public Cart getSavedUsersShoppingCart(User user) {
		List<ShoppingCart> shoppingCart = shoppingCartRepository.findByUserIdOrderByDateCreatedAsc(user.getId());
		Cart cart = new Cart();
		
		for(ShoppingCart shopgCart :shoppingCart) {
			Book book = bookService.getBookById(shopgCart.getBookId());
			Item item = new Item(book, shopgCart.getQuantity());
			cart.addItem(item);
		}
		return cart;
	}
	
	public void clearCartInDatabase(Long userId) {
		List<ShoppingCart> list = shoppingCartRepository.findAllByUserId(userId);
		System.out.println("clearCartdb: "+ list);
			for(ShoppingCart item: list) {
				shoppingCartRepository.delete(item);
			}
	}
	
	public void clearUserCart(long userId, Cart cart, HttpServletRequest request, ModelMap modelMap) {
		clearCartInDatabase(userId);
		request.getSession().setAttribute("cart", new Cart());
		modelMap.put("cart", new Cart());
	}
}
