package com.kt.csai.domain.consult.payload.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UploadResponse {
	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
}
