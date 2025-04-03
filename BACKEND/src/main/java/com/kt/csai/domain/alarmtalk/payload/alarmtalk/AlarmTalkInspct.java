package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.domain.alarmtalk.payload.MsgAttrInspct;
import com.kt.csai.domain.alarmtalk.payload.MsgDataInspct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AlarmTalkInspct {
	@JsonProperty("msg_type")
	private String msgType;
	@JsonProperty("mt_failover")
	private String mtFailover;
	@JsonProperty("msg_data")
	private MsgDataInspct msgData;
	@JsonProperty("msg_attr")
	private MsgAttrInspct msgAttr;	

	public AlarmTalkInspct(InspctDto inspct) {
		this.msgType = "AL";
		this.mtFailover = "N";
		this.msgData = new MsgDataInspct(inspct);
		this.msgAttr = new MsgAttrInspct();
	}

}
