package com.springsec.ssc3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class ProjectConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		// In chapter 1 : we used InMemoryUserDetailsManager
		// In Chapter 2: we learned implemented  our own UserDetailsService
		// In this example: we use a UserDetailsManager ( JdbcUserDetailsManager )
		
		JdbcUserDetailsManager mm = null ;
		
		return null;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
