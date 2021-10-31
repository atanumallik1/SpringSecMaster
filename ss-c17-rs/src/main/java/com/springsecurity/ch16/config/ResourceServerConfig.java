package com.springsecurity.ch16.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig {
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(converter()) ;
	}
    @Bean
	public  JwtAccessTokenConverter converter() {

    	JwtAccessTokenConverter conv = new JwtAccessTokenConverter();
		// Folloiwng key is the Private key by which Auth server signs the Token
		conv.setSigningKey("secret");
		return conv; 
		
    
    }

}
