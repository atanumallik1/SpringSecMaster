package com.springsec.ssc6.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsec.ssc6.entities.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
	Optional<Otp> findOtpByUsername(String username);

}
