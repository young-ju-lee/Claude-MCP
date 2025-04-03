package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("ref")
	private String ref;
	@JsonProperty("supplement")
	private Supplement supplement;
}
