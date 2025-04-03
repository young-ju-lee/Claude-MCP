package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class QuickReply {
	@JsonProperty("name")
	private String name;
	@JsonProperty("type")
	private String type;
	@JsonProperty("chat_extra")
	private String chatExtra;

	public QuickReply() {
		this.name = "상담하기";
		this.type = "BC";
	}

	public QuickReply(String extra) {
		this();
		this.chatExtra = extra;
	}
}
