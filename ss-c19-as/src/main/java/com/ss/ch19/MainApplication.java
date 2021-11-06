package com.ss.ch19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.ss.ch19" })
public class MainApplication {
	public static void main(String args[]) {
		SpringApplication.run(MainApplication.class, args);
	}
}
