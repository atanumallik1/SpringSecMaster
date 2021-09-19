package com.springsec.ssc1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestControllers {
	@GetMapping("/redirect")
	public String getReDirect() {
		return "Redirection from AuthenticationServer";
	}

}
