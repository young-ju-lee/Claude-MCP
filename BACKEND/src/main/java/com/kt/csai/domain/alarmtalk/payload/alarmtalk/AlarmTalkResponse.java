package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AlarmTalkResponse {
	@JsonProperty("messageId")
	private String messageId;
	@JsonProperty("to")
	private String to;
	@JsonProperty("status")
	private String status;
	@JsonProperty("text")
	private String text;

	@JsonProperty("consult_id")
	private String consultId;

	public boolean isSuccess() {
		return "A000".equals(status);
	}
}
