package com.springsec.ssc7.security.providers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springsec.ssc7.entities.Otp;
import com.springsec.ssc7.repositories.OtpRepository;
import com.springsec.ssc7.security.authentications.OtpAuthentication;

@Component
public class OtpAuthProvider implements AuthenticationProvider {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private OtpRepository otpRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// Logic
		String userName = authentication.getName();
		String otp = authentication.getCredentials().toString();

		Optional<Otp> u = otpRepository.findOtpByUsername(userName);
		if (u.isPresent()) {
			
			Otp  otpFeomDb = u.get() ;
			if(otpFeomDb.getOtp().equals(otp))
			{
			    return new OtpAuthentication(userName, otp, List.of(() -> "read"));
			}
			throw new BadCredentialsException("Incorrect OTP");

			
		}
		throw new BadCredentialsException("No OTP found");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return OtpAuthentication.class.equals(authentication);
	}

}
