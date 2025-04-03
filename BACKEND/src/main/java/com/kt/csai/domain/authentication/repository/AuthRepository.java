package com.kt.csai.domain.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.authentication.entity.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {
	Optional<Auth> findByRefreshToken(String refreshToken);
}
