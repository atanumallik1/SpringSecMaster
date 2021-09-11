package com.springsec.ssc6.security.filters;

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

import com.springsec.ssc6.entities.Otp;
import com.springsec.ssc6.repositories.OtpRepository;
import com.springsec.ssc6.security.authentications.OtpAuthentication;
import com.springsec.ssc6.security.authentications.UserNamePasswordAuthentication;

@Component
@EnableWebSecurity(debug = true)

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

	// authentication Manager is not the part of the context by default
	// we are creating the bean in class ProjectConfig --> authenticationManagerBean
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private OtpRepository otpRepository ;

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
			Authentication aa = new OtpAuthentication(password, otp);
			aa = authenticationManager.authenticate(aa);
			SecurityContextHolder.getContext().setAuthentication(aa);
			// We issue a Token
			response.setHeader("Authorization", UUID.randomUUID().toString());
		
		}

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/login");
	}

}
