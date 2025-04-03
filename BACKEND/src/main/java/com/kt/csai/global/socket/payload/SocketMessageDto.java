package com.kt.csai.global.socket.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocketMessageDto {
	@JsonProperty("consult_id")
	private String consultId;
	
	@JsonProperty("user_key")
	private String userKey;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("content_type")
	private String contentType;
	
	@JsonProperty("file_name")
	private String fileName;
	
	@JsonProperty("sender_type")
	private String senderType;
	
	@JsonProperty("chat_time")
	private String chatTime;
}
