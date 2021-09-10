package com.springsec.ssc5.security.filters;

import java.io.IOException;

import javax.servlet.Filter;
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

import com.springsec.ssc5.security.authentication.CustomAuthentication;

@Component
public class CustomAuthenticationFilter implements Filter {

	@Autowired
	AuthenticationManager manager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest http = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String authorization = http.getHeader("Authorization");

		CustomAuthentication authentication = new CustomAuthentication(authorization, null);
		try {
			Authentication result = manager.authenticate(authentication);

			if (result.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(result);
				chain.doFilter(request, response);

			} else {
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		} catch (AuthenticationException ex) {
			httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

		}

	}

}
