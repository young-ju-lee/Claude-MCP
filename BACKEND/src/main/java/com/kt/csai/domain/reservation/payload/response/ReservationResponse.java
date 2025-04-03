package com.kt.csai.domain.reservation.payload.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReservationResponse {
	@JsonProperty("tt_id")
	private String ttId;
	
	@JsonProperty("rsv_time")
	private LocalDateTime rsvTime;
	
	@JsonProperty("status")
	private String status;
}
