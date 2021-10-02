package com.springsec.ssc1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(converter());
	}

	/*
	 * Grant types : based on this the process of obtaining the token from the Auth
	 * Server by the client changes
	 * 
	 * authorization_code / pkce password --->deprecated , simple client_credentials
	 * refresh_token implicit ---> fully deprecated
	 * 
	 */

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("client1")
		                  .scopes("read")
		                  .secret("secret1")
		                  .authorizedGrantTypes("password" , "refresh_token")
		                  
		                  .and()
		                  
		                  .withClient("client2")
		                  .scopes("read")
		                  .secret("secret2")
		                  .authorizedGrantTypes("authorization_code") 
		                  .redirectUris("http://localhost:8080/redirect" , "refresh_token")
		                  
		                  .and()
		// We do not get Refreshtoke where a User is involved; not with Client Credential flow 
		                  
		                  .withClient("client3")
		                  .scopes("read")
		                  .secret("secret3")
		                  .authorizedGrantTypes("client_credentials" )
		                  ;
		                  
		                  
		                  
		                  
		                  

	}
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(converter());
	}
	
	@Bean
	public JwtAccessTokenConverter converter() {
		return new JwtAccessTokenConverter();
	}
	
	
}
