package com.springsec.ssc10.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

}
