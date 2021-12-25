package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	public Address findFirstByUserIdOrderByDateAddedDesc(Long userId);
}
