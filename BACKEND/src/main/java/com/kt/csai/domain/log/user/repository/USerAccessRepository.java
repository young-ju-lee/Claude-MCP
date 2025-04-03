package com.kt.csai.domain.log.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.csai.domain.log.user.entity.UserAccess;

public interface USerAccessRepository extends JpaRepository<UserAccess, Long> {
}
