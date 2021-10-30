package com.springsec.ssc1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private UserDetailsService uds;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder pe;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		super.configure(endpoints);
		endpoints.authenticationManager(authenticationManager);
		endpoints.userDetailsService(uds);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		super.configure(security);
		// Following configuration is done to restrict / permit access to introspection
		// API
		// i.e
		// localhost:8080/oauth/check_token?token=44a2d345-c08e-4706-8b01-cba8950735ce
		security.checkTokenAccess("permitAll()"); // isAuthenticated() / permitAll()
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// TODO Auto-generated method stub
		super.configure(clients);

		clients.inMemory().withClient("client1").secret(pe.encode("secret1")).scopes("read")
				.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(5000)
				.and()
				// Resource Server client will access only for Check Token, it is not an ordinary cleint; so we do  not need scope, grant types etc
				.withClient("resourceserver")
				.secret(pe.encode("12345"));
				
	}
}
