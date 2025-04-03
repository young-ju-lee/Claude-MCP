package com.kt.csai.domain.reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.reservation.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	Optional<Reservation> findByttIdAndStatusNot(String ttId, String status);
}
