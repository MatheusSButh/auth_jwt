package com.buthdev.demo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.buthdev.demo.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	UserDetails findByName(String name);
}
