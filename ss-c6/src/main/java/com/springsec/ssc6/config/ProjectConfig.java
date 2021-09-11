package com.springsec.ssc6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.springsec.ssc6.security.filters.UsernamePasswordAuthFilter;
import com.springsec.ssc6.security.providers.OtpAuthProvider;
import com.springsec.ssc6.security.providers.UserNamePasswordAuthProvider;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private OtpAuthProvider otpAuthProvider;
	@Autowired
	private UserNamePasswordAuthProvider userNamePasswordAuthProvider;
	@Autowired
	private UsernamePasswordAuthFilter usernamePasswordAuthFilter ;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return NoOpPasswordEncoder.getInstance();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userNamePasswordAuthProvider)
		.authenticationProvider(otpAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		// http.addFilter(usernamePasswordAuthFilter);
		http.addFilterAt(usernamePasswordAuthFilter, BasicAuthenticationFilter.class);
	}

}
