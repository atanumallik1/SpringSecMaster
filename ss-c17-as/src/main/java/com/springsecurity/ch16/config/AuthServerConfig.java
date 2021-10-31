package com.springsecurity.ch16.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.springsecurity.ch16.security.JpaClientDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder pe ;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// We shall pass our Client Details Service
		// clients.inMemory().withClient(null).authorizedGrantTypes(null).scopes(null).secret(null);

		// We shall pass our Client Details Service
		clients.withClientDetails(cds());

	}

	@Bean
	public ClientDetailsService cds() {
		return new JpaClientDetailsService();
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.tokenStore(tokenStore()).accessTokenConverter(converter());

	}
	
	
	@Bean
	public TokenStore tokenStore() {	
		// By introducing a JWTokenStore we shall switch from Opaque token to JWT token
		return new JwtTokenStore(converter());
	}
	
	
	public JwtAccessTokenConverter converter() {
		JwtAccessTokenConverter conv = new JwtAccessTokenConverter();
		// Folloiwng key is the Private key by which Auth server signs the Token
		
		conv.setSigningKey("secret");
		
		return conv; 
		
		
	}
	
	

}
