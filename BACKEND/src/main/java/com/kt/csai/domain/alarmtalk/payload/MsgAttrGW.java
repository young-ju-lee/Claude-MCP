package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MsgAttrGW {
	@JsonProperty("sender_key")
	private String senderKey;
	@JsonProperty("response_method")
	private String responseMethod;
	@JsonProperty("template_code")
	private String templateCode;
	/*@JsonProperty("supplement")
	private Supplement supplement; //QuickReply 연결(?)
	*/

	public MsgAttrGW() {
		
		//@써티웨어
		this.senderKey = "43ce9d655ac3157805092bde698be8e96f29bad3";
		this.templateCode = "GW0002";
		this.responseMethod = "push";
	}

	public MsgAttrGW(String extra) {
		this();		
		//this.supplement = new Supplement(new QuickReply());  // 상담하기 버튼 포함
	}
}
