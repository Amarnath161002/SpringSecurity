package com.example.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.entity.UserEntity;
//import com.example.myapp.model.User;
import com.example.myapp.service.AuthService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	AuthService authService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	 @PostMapping("/register")
	    public ResponseEntity<UserEntity> register(@RequestBody UserEntity userEntity) {
	        UserEntity registeredUser = authService.signup(userEntity);
	        registeredUser.setPassword(passwordEncoder.encode(registeredUser.getPassword())); 
	        return ResponseEntity.ok(registeredUser);
	    }
}
