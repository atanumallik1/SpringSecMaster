package com.springsec.ssc7.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsec.ssc7.entities.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
	Optional<Otp> findOtpByUsername(String username);

}
