package com.kt.csai.domain.consult.payload.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsultStatus {
	@JsonProperty("consult_id")
	private String consultId;

	@JsonProperty("status")
	private String status;
	
	@JsonProperty("rsv_time")
	private LocalDateTime rsvTime;
	
	@JsonProperty("user_key")
	private String userKey;
}
