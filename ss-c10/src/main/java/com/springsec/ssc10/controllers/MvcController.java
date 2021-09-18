package com.springsec.ssc10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MvcController {
	
	
	@GetMapping("/")
	public String main() {
		return "main.html" ;
	}
	
	@PostMapping("/test")
	@ResponseBody
	public String test() {
		System.out.println(":(");
		return "TEST!" ;
		
	}

}
