package com.springsec.ssc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScan({"com.springsec.ssc1"})
@EnableWebSecurity(debug = true)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class) ;

	}

}
