package com.buthdev.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buthdev.demo.dtos.AuthenticationDTO;
import com.buthdev.demo.dtos.LoginResponseDTO;
import com.buthdev.demo.dtos.RegisterDTO;
import com.buthdev.demo.model.User;
import com.buthdev.demo.repositories.UserRepository;
import com.buthdev.demo.security.TokenService;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody AuthenticationDTO authDto) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(authDto.name(), authDto.password());
		var auth = authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity register(@RequestBody RegisterDTO registerDto) {
		if (userRepository.findByName(registerDto.name()) != null) {
			return ResponseEntity.badRequest().build();
		}
		
		String ecryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
		
		User newUser = new User(registerDto.name(), ecryptedPassword, registerDto.role());
		
		userRepository.save(newUser);
		
		return ResponseEntity.ok().build();
	}
	
}
