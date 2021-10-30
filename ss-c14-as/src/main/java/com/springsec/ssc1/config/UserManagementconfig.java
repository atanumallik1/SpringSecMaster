package com.springsec.ssc1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

public class UserManagementconfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService uds() {

		UserDetailsManager udm = new InMemoryUserDetailsManager();

		UserDetails u = User.withUsername("bill").password(pe().encode("12345")).authorities("read").build();
		udm.createUser(u);

		return udm;

	}

	@Bean
	public PasswordEncoder pe() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
