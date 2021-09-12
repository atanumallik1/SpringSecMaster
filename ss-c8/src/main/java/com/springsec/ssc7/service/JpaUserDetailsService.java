package com.springsec.ssc7.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springsec.ssc7.entities.User;
import com.springsec.ssc7.repositories.UserRepository;
import com.springsec.ssc7.security.model.SecurityUser;

@Component
public class JpaUserDetailsService implements UserDetailsService {
	
	public JpaUserDetailsService() {
		
		//This constructor I have created just to see creates this class 
		//
		System.out.println("In the constructor of JpaUserDetailsService");
	}
    
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userResult = userRepository.findUserByUsername(username);
		User user = userResult.orElseThrow(() -> new UsernameNotFoundException( username + " not found :( "));
		
		return new SecurityUser(user);
	}

}
