package com.example.myapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.entity.UserEntity;
import com.example.myapp.repository.UserRepository;

@RestController
@RequestMapping("/api/users")

public class HomeController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<UserEntity> getUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<UserEntity> getUserById(@PathVariable Long id) {
		return userRepository.findById(id);
	}
	
	
}
