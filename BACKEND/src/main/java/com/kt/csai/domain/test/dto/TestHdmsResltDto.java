package com.kt.csai.domain.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TestHdmsResltDto {
	
	@JsonProperty("ttid")
	private String[] ttid;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("tableNm")
	private String tableNm;
	
}
