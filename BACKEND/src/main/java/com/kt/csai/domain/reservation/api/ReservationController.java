package com.kt.csai.domain.reservation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kt.csai.domain.reservation.payload.request.ReservationParam;
import com.kt.csai.domain.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;
	
	@PostMapping("")
	public ResponseEntity<?> doReservation(@RequestBody ReservationParam reservationParam) {
		return new ResponseEntity<>(reservationService.doReservation(reservationParam), HttpStatus.OK);
	}
}
