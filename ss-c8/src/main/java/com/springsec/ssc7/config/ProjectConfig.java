package com.springsec.ssc7.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.springsec.ssc7.security.filters.TokenAuthFilter;
import com.springsec.ssc7.security.filters.UsernamePasswordAuthFilter;
import com.springsec.ssc7.security.providers.OtpAuthProvider;
import com.springsec.ssc7.security.providers.TokenAuthProvider;
import com.springsec.ssc7.security.providers.UserNamePasswordAuthProvider;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private OtpAuthProvider otpAuthProvider;
	@Autowired
	private UserNamePasswordAuthProvider userNamePasswordAuthProvider;
	@Autowired
	private TokenAuthProvider tokenAuthProvider;

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
		auth.authenticationProvider(userNamePasswordAuthProvider).authenticationProvider(otpAuthProvider)
				.authenticationProvider(tokenAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilterAt(usernamePasswordAuthFilter(), BasicAuthenticationFilter.class);
		http.addFilterAfter(tokenAuthFilter(), BasicAuthenticationFilter.class);

	}

	@Bean
	public TokenAuthFilter tokenAuthFilter() {
		return new TokenAuthFilter();
	}

	@Bean
	public UsernamePasswordAuthFilter usernamePasswordAuthFilter() {
		return new UsernamePasswordAuthFilter();
	}

	@Bean
	public InitializingBean initializingBean() {
        // Security contecxt will be available to all threads in this application if the mode 
		// is SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
		return () -> {
 
			SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		};

	}

}
