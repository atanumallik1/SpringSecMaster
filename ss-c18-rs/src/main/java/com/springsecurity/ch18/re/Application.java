package com.springsecurity.ch18.re;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.springsecurity.ch18.re" })
public class Application {
	public static void main(String args[]) {

		SpringApplication.run(Application.class, args);

	}

}
