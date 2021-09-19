package com.springsec.ssc10.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

public class CorsConfigSource implements CorsConfigurationSource {

	@Override
	public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		
		CorsConfiguration cconfiguration = new CorsConfiguration();

		cconfiguration.addAllowedMethod("POST");
		cconfiguration.addAllowedOrigin("*");
		
		return cconfiguration ;

	}

}
