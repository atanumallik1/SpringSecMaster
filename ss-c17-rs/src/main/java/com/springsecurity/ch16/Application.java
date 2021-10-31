package com.springsecurity.ch16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({"com.springsecurity.ch16"})
@SpringBootApplication
public class Application {
	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

}
