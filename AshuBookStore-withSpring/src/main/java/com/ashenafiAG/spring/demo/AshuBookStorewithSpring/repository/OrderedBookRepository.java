package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.OrderedBook;

public interface OrderedBookRepository extends JpaRepository<OrderedBook, Long> {
	public List<OrderedBook> findByOrderId(Long orderId);

}
