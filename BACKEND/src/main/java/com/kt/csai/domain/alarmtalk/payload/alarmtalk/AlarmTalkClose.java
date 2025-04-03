package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.domain.alarmtalk.payload.MsgAttr;
import com.kt.csai.domain.alarmtalk.payload.MsgData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AlarmTalkClose {
	@JsonProperty("msg_type")
	private String msgType;
	@JsonProperty("mt_failover")
	private String mtFailover;
	@JsonProperty("msg_data")
	private MsgData msgData;
	@JsonProperty("msg_attr")
	private MsgAttr msgAttr;

	public AlarmTalkClose() {
		this.msgType = "AL";
		this.mtFailover = "N";
		this.msgData = new MsgData();
		this.msgAttr = new MsgAttr();
		
		//this.msgAttr.setTemplateCode("CSAI0003");
		//this.msgData.setContent("상담요청후 10분 경과되었습니다.\nKT 전문상담원에게 다시 알림톡 요청해 주시기 바랍니다.");
		this.msgAttr.setTemplateCode("CSAI_202406_01");  
		this.msgData.setContent("상담 요청 후 10분이 경과되어 상담이 자동으로 종료가 되었습니다.\n감사합니다.");
	}

	public AlarmTalkClose(String to) {
		this();
		this.msgData.setTo(to);
	}
}

