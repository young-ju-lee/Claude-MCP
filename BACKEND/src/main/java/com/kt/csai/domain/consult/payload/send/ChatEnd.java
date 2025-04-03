package com.kt.csai.domain.consult.payload.send;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ChatEnd {
	@JsonProperty("user_key")
	private String userKey;
	@JsonProperty("serial_number")
	private String serialNumber;
	@JsonProperty("end_type")
	private String endType;
	@JsonProperty("bot_event")
	private String botEvent;
}
