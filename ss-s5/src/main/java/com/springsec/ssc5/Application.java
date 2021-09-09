package com.springsec.ssc5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.springsec.ssc5"})

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class) ;

	}

}
