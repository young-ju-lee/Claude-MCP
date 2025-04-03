package com.kt.csai.domain.consult.payload.receive;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveMessage {
	@JsonProperty("pf_id")
	private String pfId;
	@JsonProperty("user_key")
	private String userKey;
	@JsonProperty("type")
	private String type;
	@JsonProperty("content")
	private String content;
	@JsonProperty("contents")
	private List<Object> contents;
	@JsonProperty("attachment")
	private String attachment;
	@JsonProperty("extra")
	private String extra;
}
