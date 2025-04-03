package com.kt.csai.domain.consult.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DoneConsult {
	@JsonProperty("consult_id")
	private String consultId;
	
	@JsonProperty("last_guide_code")
	private String lastGuideCode;
	
	@JsonProperty("go_yn")
	private String goYn;
	
	@JsonProperty("go_reason")
	private String goReason;
	
	@JsonProperty("scn")
	private String scn;
}
