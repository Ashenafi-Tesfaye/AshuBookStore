package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Order;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository.OrderRepository;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public Order getRecentlyUsedAddress(Long userId) {
		return orderRepository.findFirstByUserIdOrderByDateOrderedDesc(userId);
	}
	
	public void insertOrder(ModelMap model, Long userId, BigDecimal totalCost, Long addressId) {
		Order order = new Order();
		order.setAddressId(addressId);
		order.setDateOrdered(new Date());
		order.setUserId(userId);
		order.setTotal(totalCost);
		order.setHash(generatedOrderNumberHash());
		orderRepository.save(order);
	}

	public Long placeOrder(ModelMap model, Long userId, BigDecimal totalCost, Long addressId) {
		insertOrder(model, userId, totalCost,addressId);
		return orderRepository.findFirstByOrderByOrderIdDesc().getOrderId();
	}
	
	private String generatedOrderNumberHash() {
		
		return UUID.randomUUID().toString();
	}
	
	public void updatePaid(Long orderId) {
		Order order = orderRepository.findById(orderId).get();
		order.setPayed(true);
		orderRepository.save(order);
	}

	public Order getOrderById(Long orderId) {
		return orderRepository.findById(orderId).get();
	}
	
	public String getOrderHash(Long orderId) {
		return getOrderById(orderId).getHash();
	}
	
	public String formatDate (Date date) {

		DateFormat df = new SimpleDateFormat ("EEEE, MMMM dd, yyyy");
		return df.format(date).toString();
	}
	
	public Order getOrderByHash(String orderId) {
		return orderRepository.findFirstByHash(orderId);
	}
	
	public Order getOrderByHashAndByUserId(String hash, Long userId) {
		return orderRepository.findFirstByHashAndUserId(hash, userId);
	}
	
	public List<Order> getAllOrdersByUserId(Long userId){
		return orderRepository.findAllByUserId(userId);
	}
	
}
