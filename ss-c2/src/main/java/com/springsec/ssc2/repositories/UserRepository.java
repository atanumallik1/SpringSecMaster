package com.springsec.ssc2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsec.ssc2.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	//TO-DO: Documenation on the method name
	Optional<User> findUserByUsername(String username);
}
