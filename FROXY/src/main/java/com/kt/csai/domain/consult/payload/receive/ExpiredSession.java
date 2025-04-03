package com.kt.csai.domain.consult.payload.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExpiredSession {
	@JsonProperty("pf_id")
	private String pfId;
	@JsonProperty("user_key")
	private String userKey;
	@JsonProperty("session_id")
	private String sessionId;
}
