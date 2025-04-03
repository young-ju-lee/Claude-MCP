package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MsgAttrInspct {
	@JsonProperty("sender_key")
	private String senderKey;
	@JsonProperty("response_method")
	private String responseMethod;
	@JsonProperty("template_code")
	private String templateCode;
	/*@JsonProperty("supplement")
	private Supplement supplement; //QuickReply 연결(?)
	*/

	public MsgAttrInspct() {
		
		//@KTDS_CSAI
		this.senderKey = "6dae9523ee4990e4094375106fd90891a4422d56";
		this.templateCode = "OSS_ALARM_01";
		this.responseMethod = "push";
	}

	public MsgAttrInspct(String extra) {
		this();		
		//this.supplement = new Supplement(new QuickReply());  // 상담하기 버튼 포함
	}
}
