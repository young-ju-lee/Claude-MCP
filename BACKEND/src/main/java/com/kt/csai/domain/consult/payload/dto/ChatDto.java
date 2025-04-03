package com.kt.csai.domain.consult.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatDto {
	@JsonProperty("sender_type")
	private String senderType;
	
	@JsonProperty("content_type")
	private String contentType;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("chat_time")
	private String chatTime;
}
