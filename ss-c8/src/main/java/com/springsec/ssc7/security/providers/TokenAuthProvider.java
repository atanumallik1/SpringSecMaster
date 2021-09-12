package com.springsec.ssc7.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.springsec.ssc7.security.authentications.TokenAuthentication;
import com.springsec.ssc7.security.managers.TokenManager;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

	@Autowired
	private TokenManager tokenManager;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String token = authentication.getName();
		boolean exist = tokenManager.contains(token);
		if (exist) {
			return new TokenAuthentication(token, null, null);
		}  
			throw new BadCredentialsException("Invalid Token....");

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return TokenAuthentication.class.equals(authentication);
	}

}
