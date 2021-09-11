package com.springsec.ssc6.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsec.ssc6.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByUsername(String username);

}
