package com.springsec.ssc5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.springsec.ssc5.security.filters.CustomAuthenticationFilter;
import com.springsec.ssc5.security.filters.CustomAuthenticationOncePerRequestFilter;
import com.springsec.ssc5.security.providers.CustomAuthenticationProvider;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationFilter customAuthenticationFilter;
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.addFilterAt(customAuthenticationFilter, BasicAuthenticationFilter.class);
		
		// We are not implementing any Authorization; 
		// We disable all Authorization related checks 
		http.authorizeRequests().anyRequest().permitAll();
	}

}
