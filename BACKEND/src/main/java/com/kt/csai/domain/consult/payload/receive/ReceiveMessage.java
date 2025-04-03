package com.kt.csai.domain.consult.payload.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReceiveMessage {
	@JsonProperty("pf_id")
	private String pfId;
	@JsonProperty("user_key")
	private String userKey;
	@JsonProperty("type")
	private String type;
	@JsonProperty("content")
	private String content;
	@JsonProperty("attachment")
	private String attachment;
	@JsonProperty("extra")
	private String extra;
}
