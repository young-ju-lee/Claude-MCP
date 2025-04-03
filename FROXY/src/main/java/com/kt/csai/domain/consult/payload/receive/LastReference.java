package com.kt.csai.domain.consult.payload.receive;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LastReference {
	@JsonProperty("extra")
	private String extra;
	@JsonProperty("bot")
	private String bot;
	@JsonProperty("bot_event")
	private String botEvent;
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
}
