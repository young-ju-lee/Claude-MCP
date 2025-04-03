package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MsgDataCsai {
	@JsonProperty("senderid")
	private String senderId;
	@JsonProperty("to")
	private String to;
	@JsonProperty("content")
	private String content;
	
	public MsgDataCsai() {
		this.senderId = "";
		this.content = "CSAI 연동 에러가 발생했으니 즉시 확인 바랍니다. ";
	}
	
	public MsgDataCsai(String to) {
		this();
		this.to = to;
	}
}
