package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.enums;

public enum RoleType {

	CUSTOMER("CUSTOMER"), ADMIN("ADMIN");
	
	private String value;

	private RoleType(String value) {
		this.value = value;
	}
	
	
	
}
