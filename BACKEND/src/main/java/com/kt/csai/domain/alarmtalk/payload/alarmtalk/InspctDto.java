package com.kt.csai.domain.alarmtalk.payload.alarmtalk;
	
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
	
@Data
public class InspctDto {

	@JsonProperty("telNm")
	private String telNm = "";	

	@JsonProperty("name")
	private String name = "";
	
	@JsonProperty("isptDt")
	private String isptDt = "";
	
}