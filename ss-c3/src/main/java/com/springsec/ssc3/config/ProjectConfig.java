package com.springsec.ssc3.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public JdbcUserDetailsManager userDetailsService() {
		// In chapter 1 : we used InMemoryUserDetailsManager
		// In Chapter 2: we learned implemented our own UserDetailsService
		// In this example: we use a UserDetailsManager ( JdbcUserDetailsManager )

		DataSource datasorce = null;
		return new JdbcUserDetailsManager(datasource());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public DataSource datasource() {
		// It is also possible to setup these by Properties
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl("jdbc:mysql://localhost/chapter3");
		ds.setUsername("root");
		ds.setPassword("rootroot");

		return ds;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic();
		http.csrf().disable();

		http.authorizeRequests()
		    .mvcMatchers("/users")
		    .permitAll().anyRequest().authenticated();

	}

}
