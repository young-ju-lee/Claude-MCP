package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TroubleDto {
	@JsonProperty("tt_id")
	private String ttId;
	
	@JsonProperty("svc_main_cls_code")
	private String svcMainClsCode;
	
	@JsonProperty("lscn")
	private String lscn;
}
