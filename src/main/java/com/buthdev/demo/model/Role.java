package com.buthdev.demo.model;

import jakarta.persistence.OneToMany;
import lombok.Getter;

@Getter
public enum Role {

	ADMIN("admin"),
	BASIC("basic");
	
	private String role;
	
	Role(String role){
		this.role = role;
	}
	
	@OneToMany
	private User user;
}
