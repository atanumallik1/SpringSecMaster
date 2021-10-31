package com.springsecurity.ch16.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.ch16.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	  Optional<Client> findClientByClientId(String clientId)  ;

}
