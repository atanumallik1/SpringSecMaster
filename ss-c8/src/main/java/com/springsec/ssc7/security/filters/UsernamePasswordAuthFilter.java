package com.springsec.ssc7.security.filters;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springsec.ssc7.entities.Otp;
import com.springsec.ssc7.repositories.OtpRepository;
import com.springsec.ssc7.security.authentications.OtpAuthentication;
import com.springsec.ssc7.security.authentications.UserNamePasswordAuthentication;
import com.springsec.ssc7.security.managers.TokenManager;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

	// authentication Manager is not the part of the context by default
	// we are creating the bean in class ProjectConfig --> authenticationManagerBean
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private OtpRepository otpRepository ;

	@Autowired
	private TokenManager tokenManager ;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String userName = request.getHeader("username");
		String password = request.getHeader("password");
		String otp = request.getHeader("otp");

		if (otp == null) {
			// Step 1
			Authentication aa = new UserNamePasswordAuthentication(userName, password);
			aa = authenticationManager.authenticate(aa);
			//SecurityContextHolder.getContext().setAuthentication(aa);
            // Issue the Otp 
			Otp otpEntity = new Otp();
			String otpCode = String.valueOf(new Random().nextInt(9999));
			
			otpEntity.setUsername(userName);
			otpEntity.setOtp(otpCode);
			otpRepository.save(otpEntity);
			
		
		} else {

			// Step 2
			Authentication otpAuthentication = new OtpAuthentication(userName, otp);
			otpAuthentication = authenticationManager.authenticate(otpAuthentication);
			// We issue a Token
			String token = UUID.randomUUID().toString();
			response.setHeader("Authorization", token);
			tokenManager.add(token);

		}

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// This filter is activated when you want to fire url :localhost:8080//login
		// For all pags other than Login we have another filter
		return !request.getServletPath().equals("/login");
	}

}
