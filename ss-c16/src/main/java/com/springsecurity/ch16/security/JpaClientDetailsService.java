package com.springsecurity.ch16.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import com.springsecurity.ch16.entities.Client;
import com.springsecurity.ch16.repositories.ClientRepository;

public class JpaClientDetailsService implements ClientDetailsService{

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		Optional<Client> client = clientRepository.findClientByClientId(clientId);

		return client.map(c -> new SecurityClient(c))
				.orElseThrow(() -> new ClientRegistrationException("Client Registration error"));

	}

}
