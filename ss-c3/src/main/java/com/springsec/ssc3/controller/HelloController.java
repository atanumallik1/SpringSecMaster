package com.springsec.ssc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsec.ssc3.model.User;

@RestController
public class HelloController {
	
	@Autowired
	private JdbcUserDetailsManager userDetailsManager;
	@Autowired
	private PasswordEncoder pe ;

	@GetMapping("/hello")
	public String hello() {

		return "hello";
	}

	@PostMapping("/user")
	public void addUser(@RequestBody User user) {
		user.setPassword(pe.encode(user.getPassword())) ;
		userDetailsManager.createUser(user);
	}

}
