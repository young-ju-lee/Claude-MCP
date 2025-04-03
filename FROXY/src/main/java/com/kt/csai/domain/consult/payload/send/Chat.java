package com.kt.csai.domain.consult.payload.send;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chat { 
	@JsonProperty("user_key")
	protected String userKey;

	@JsonProperty("serial_number")
	protected String serialNumber;

	@JsonProperty("message_type")
	private String messageType;

	@JsonProperty("image_url")
	private String imageUrl;
	
	@JsonProperty("file_url")
	private String fileUrl;
}
