package com.kt.csai.domain.consult.payload.send;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatWrite {
	@JsonProperty("user_key")
	private String userKey;
	@JsonProperty("serial_number")
	private String serialNumber;
	@JsonProperty("message_type")
	private String messageType;
	@JsonProperty("message")
	private String message;
	@JsonProperty("image_url")
	private String imageUrl;
	@JsonProperty("file_url")
	private String fileUrl;
	@JsonProperty("file_name")
	private String fileName;
	@JsonProperty("file_size")
	private String fileSize;
	@JsonProperty("auto_answer")
	private String autoAnswer;
	@JsonProperty("ref_key")
	private String refKey;
}
