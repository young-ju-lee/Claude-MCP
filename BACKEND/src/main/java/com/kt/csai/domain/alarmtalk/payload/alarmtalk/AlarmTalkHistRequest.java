package com.kt.csai.domain.alarmtalk.payload.alarmtalk;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AlarmTalkHistRequest {
	
	@JsonProperty("sndCmpNo")
	private String sndCmpNo;
	
	@JsonProperty("custName")
	private String custName;
	
	@JsonProperty("custTelNo")
	private String custTelNo;
	
	@JsonProperty("sndFromDt")
	private String sndFromDt;
	
	@JsonProperty("sndToDt")
	private String sndToDt;
	
	@JsonProperty("page")
	private int page;
	
	@JsonProperty("size")
	private int size;
	
}
