package com.springsec.ssc10.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);

		http.csrf().disable(); // Only for Test Do not do this in real life
		http.authorizeRequests().anyRequest().permitAll() ;
		
		// Cors Configuration settings 
		http.cors(new CustomCorsConfig());
		
	}

}
