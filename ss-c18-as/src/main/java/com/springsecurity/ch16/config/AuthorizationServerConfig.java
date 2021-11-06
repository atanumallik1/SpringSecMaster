package com.springsecurity.ch16.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory().withClient("client1").secret(passwordEncoder.encode("secret1")).scopes("read").authorizedGrantTypes("password");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(jwtTokenStoreConverter());

	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtTokenStoreConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtTokenStoreConverter() {

		JwtAccessTokenConverter jat = new JwtAccessTokenConverter();
		// Configuring the private key to sign the token
		KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(new ClassPathResource("ssia.jks"),
				"ssia123".toCharArray());
		jat.setKeyPair(keyFactory.getKeyPair("ssia"));

		return jat;
	}

}
