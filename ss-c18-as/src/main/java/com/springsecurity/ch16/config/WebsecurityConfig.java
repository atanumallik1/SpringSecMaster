package com.springsecurity.ch16.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class WebsecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService uds() {

		UserDetailsManager udm = new InMemoryUserDetailsManager();
		
		UserDetails uds = User.withUsername("john").password(pe().encode( "12345"))
		    .authorities("read").build() ;
		udm.createUser(uds);
		return udm;
	}

	@Bean
	public PasswordEncoder pe() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
