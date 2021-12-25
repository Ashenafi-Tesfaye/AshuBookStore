package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	
}
