package com.springsec.ssc3.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class ProjectConfig {
	@Autowired
	DataSource dataSource ;
	
	@Bean
	public UserDetailsService userDetailsService() {
		// In chapter 1 : we used InMemoryUserDetailsManager
		// In Chapter 2: we learned implemented  our own UserDetailsService
		// In this example: we use a UserDetailsManager ( JdbcUserDetailsManager )
		
		DataSource datasorce = null;
		return new JdbcUserDetailsManager(datasorce);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean 
	public DataSource datasource() {
		return null;
	}

}
