package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	
}
