package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public Order findFirstByUserIdOrderByDateOrderedDesc(Long userId);
	
	public Order findFirstByOrderByOrderIdDesc();
	
	public Order findFirstByHash(String hash);
	
	public Order findFirstByHashAndUserId(String hash, Long userId);
	
	public List<Order> findAllByUserId(Long userId);

}
