package com.springsec.ssc7.security.authentications;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class TokenAuthentication extends UsernamePasswordAuthenticationToken {

	public TokenAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		// We are explicitly usinhg the constructor with 3 params 
		// This constructor sets the isAuthenticated to True

		super(principal, credentials, authorities);
	}
	public TokenAuthentication(Object principal, Object credentials) {
		super(principal, credentials );
	}
	
}
