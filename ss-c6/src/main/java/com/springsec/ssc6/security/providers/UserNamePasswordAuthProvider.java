package com.springsec.ssc6.security.providers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springsec.ssc6.security.authentications.UserNamePasswordAuthentication;
import com.springsec.ssc6.service.JpaUserDetailsService;

@Component
public class UserNamePasswordAuthProvider implements AuthenticationProvider {
	@Autowired
	private JpaUserDetailsService jpaUserDetailsService;
	@Autowired
	private PasswordEncoder passowrdEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails user = jpaUserDetailsService.loadUserByUsername(userName);
		if (passowrdEncoder.matches(password, user.getPassword())) {
			
			// At this place we need to use the following constructor with "Grantedauthrities" param
			Authentication authenticatedUser = new UserNamePasswordAuthentication(user, password,
					user.getAuthorities());
			return authenticatedUser;
		}

		throw new BadCredentialsException("User credentials do not match") ;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UserNamePasswordAuthentication.class.equals(authentication);
	}

}
