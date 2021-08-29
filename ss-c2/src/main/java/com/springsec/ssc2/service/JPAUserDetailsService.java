package com.springsec.ssc2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springsec.ssc2.entities.SercurityUser;
import com.springsec.ssc2.entities.User;
import com.springsec.ssc2.repositories.UserRepository;

public class JPAUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findUserByUsername(username);
		User u = user.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		return new SercurityUser(u);
	}

}
