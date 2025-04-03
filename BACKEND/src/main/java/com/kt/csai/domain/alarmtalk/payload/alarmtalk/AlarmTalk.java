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
public class AlarmTalk {
	@JsonProperty("msg_type")
	private String msgType;
	@JsonProperty("mt_failover")
	private String mtFailover;
	@JsonProperty("msg_data")
	private MsgData msgData;
	@JsonProperty("msg_attr")
	private MsgAttr msgAttr;

	public AlarmTalk() {
		this.msgType = "AL";
		this.mtFailover = "N";
		this.msgData = new MsgData();
		this.msgAttr = new MsgAttr();
	}

	public AlarmTalk(AlarmTalkRequest requestAlarmTalk) {
		this();
		this.msgData.setTo(requestAlarmTalk.getCustomer().getCustTelNo());
		this.msgAttr = new MsgAttr(requestAlarmTalk.getConsultId());
	}
}
