package com.springsec.ssc7.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/hello2")
	public String hello2(Authentication authentication) {
		
		// This Authentication authentication object is available from 
		// the endpoint (controller) method 
		
		// for non endpoint methods we need to use SecurityContextHolder
		// by default this variable is threadLocal
		Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
		
		return "hello2 , authetication 1:  " + authentication.getName( ) + " Authentication 2: " +auth2.getName();
	}
}
