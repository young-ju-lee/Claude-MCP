package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthTokenResponse {
	// 성공 시
	@JsonProperty("schema")
	private String schema;
	@JsonProperty("accessToken")
	private String accessToken;
	@JsonProperty("expired")
	private String expired;
	// 실패 시
	@JsonProperty("clientId")
	private String clientId;
	@JsonProperty("status")
	private String status;
	@JsonProperty("text")
	private String text;
}
