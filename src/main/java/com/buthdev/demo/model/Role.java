package com.buthdev.demo.model;

import lombok.Getter;

@Getter
public enum Role {

	ADMIN("admin"),
	BASIC("basic");
	
	private String role;
	
	Role(String role){
		this.role = role;
	}
	
	private User user;
}
