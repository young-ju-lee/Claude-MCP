package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.domain.alarmtalk.payload.MsgAttrCsai;
import com.kt.csai.domain.alarmtalk.payload.MsgDataCsai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AlarmTalkCsai {
	@JsonProperty("msg_type")
	private String msgType;
	@JsonProperty("mt_failover")
	private String mtFailover;
	@JsonProperty("msg_data")
	private MsgDataCsai msgData;
	@JsonProperty("msg_attr")
	private MsgAttrCsai msgAttr;

	public AlarmTalkCsai() {
		this.msgType = "AL";
		this.mtFailover = "N";
		this.msgData = new MsgDataCsai();
		this.msgAttr = new MsgAttrCsai();
	}

	public AlarmTalkCsai(ErrInfoCsaiDto errinfo) {
		this();
		this.msgData.setTo(errinfo.getTelNum());
		this.msgAttr = new MsgAttrCsai();
	}
}
