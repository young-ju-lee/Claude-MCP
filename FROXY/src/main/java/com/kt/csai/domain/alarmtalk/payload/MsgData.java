package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MsgData {
	@JsonProperty("senderid")
	private String senderId;
	@JsonProperty("to")
	private String to;
	@JsonProperty("content")
	private String content;
}
