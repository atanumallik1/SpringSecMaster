package com.springsec.ssc7.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springsec.ssc7.security.authentications.TokenAuthentication;

public class TokenAuthFilter extends OncePerRequestFilter {
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// We are checking is the is a valida token ..
		// This is very simple logic for the sake of explanation ; in realife
		// they can be very complex and must not be put in the filter class
		// we need a separate class

		String token = request.getHeader("Authorization");

		// This Authentication object is not yet authenticates
		TokenAuthentication tokenAuthentication = new TokenAuthentication(token, null);
		// Following Authentication object will contain the isAuthenticated = true
		Authentication autheticationResult = authenticationManager.authenticate(tokenAuthentication);

		SecurityContextHolder.getContext().setAuthentication(autheticationResult);
		filterChain.doFilter(request, response);
		// This is where the Authentication Finishes
		// We need to populate the SecurityContext to mark the
		// end of successful authentication

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		// This filter will be called for all
		// pages other than http://localhost:8080/Login
		return request.getServletPath().equals("/login");

	}

}
