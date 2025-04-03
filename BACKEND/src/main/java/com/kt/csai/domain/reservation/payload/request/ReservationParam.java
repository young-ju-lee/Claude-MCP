package com.kt.csai.domain.reservation.payload.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReservationParam {
	@JsonProperty("tt_id")
	private String ttId;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("rsv_time")
	private LocalDateTime rsvTime;
}
