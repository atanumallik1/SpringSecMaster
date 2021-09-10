package com.springsec.ssc5.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springsec.ssc5.security.authentication.CustomAuthentication;

@Component
public class CustomAuthenticationOncePerRequestFilter extends OncePerRequestFilter {

	@Autowired
	AuthenticationManager manager;

	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String authorization = request.getHeader("Authorization");

		CustomAuthentication authentication = new CustomAuthentication(authorization, null);
		try {
			Authentication result = manager.authenticate(authentication);

			if (result.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(result);
				chain.doFilter(request, response);

			} else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		} catch (AuthenticationException ex) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		}

	}

}
