package com.springsec.ssc5.security.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.springsec.ssc5.security.authentication.CustomAuthentication;

@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider{
     
	@Value("${key}")
	 private String key ;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 
		String authKey = authentication.getName() ;
		if(key.equals(authKey)) {
			
			// We are creating the following Authentication Object only for test; thsi 
			// Authentication object is very incomplete 
			// For example: it does not contain any granted authorities 
			// and this incomplete Authentication object can not be used for any Authorization
			// checks; so we need to disable all authorization checks on this app
			// from the websecurityConfigurerAdapter class 
			
			Authentication a = new CustomAuthentication(null, null, null);
			return a; 
			
		}
		else
			throw new BadCredentialsException("Please enter " + key + "in the Authorization header!");
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return CustomAuthentication.class.equals(authentication) ;
		
	}

}
