package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.ShoppingCart;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.ShoppingCartId;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId>{

	public ShoppingCart findByUserIdAndBookId(Long userId, Long bookId);
	
	public List <ShoppingCart> findByUserIdOrderByDateCreatedAsc(Long userId);
	
	public List <ShoppingCart> findAllByUserId(Long userId);
	
}
