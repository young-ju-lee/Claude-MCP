package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MsgAttr {
	@JsonProperty("sender_key")
	private String senderKey;
	@JsonProperty("response_method")
	private String responseMethod;
	@JsonProperty("template_code")
	private String templateCode;
	@JsonProperty("supplement")
	private Supplement supplement;

	public MsgAttr() {
		this.senderKey = "6dae9523ee4990e4094375106fd90891a4422d56"; // 암호화 예정
		//this.templateCode = "CSAI_004";
		this.templateCode = "CSAI_202405_01";
		this.responseMethod = "push";
	}

	public MsgAttr(String extra) {
		this();
		this.supplement = new Supplement(new QuickReply(extra));
	}
}