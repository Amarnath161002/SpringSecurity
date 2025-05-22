package com.example.myapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.myapp.entity.UserEntity;
//import com.example.myapp.model.User;
import com.example.myapp.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	public UserEntity signup(UserEntity input) {
    	UserEntity user = new UserEntity();
        
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setUsername(input.getUsername());
        user.setPassword(input.getPassword()); 
        user.setPassword(passwordEncoder.encode(input.getPassword()));
     // user.setRole(Role.User); 
        // Set other fields if needed, like lastname, roles, etc.

        return userRepository.save(user);
    }
}
