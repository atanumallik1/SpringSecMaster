package com.springsec.ssc10.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;

public class CustomCorsConfig implements Customizer<CorsConfigurer<HttpSecurity>> {

	@Override
	public void customize(CorsConfigurer<HttpSecurity> cc) {
		cc.configurationSource(new CorsConfigSource());

	}

}
