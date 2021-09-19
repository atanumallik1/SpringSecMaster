package com.springsec.ssc1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.authenticationManager(authenticationManager);
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
		                  .authorizedGrantTypes("password")
		                  .and()
		                  .withClient("client2")
		                  .scopes("read")
		                  .secret("secret2")
		                  .authorizedGrantTypes("authorization_code") 
		                  .redirectUris("http://localhost:8080/redirect");
		                  
		                  
		                  

	}
	
	
}
