package com.kt.csai.domain.trouble.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NonMoveTgtRequest {
	
	@JsonProperty("svcMainClsCode")
	private String[] svcMainClsCode;
	
	@JsonProperty("custName")
	private String custName;
	
	@JsonProperty("svcId")
	private String svcId;
	
	@JsonProperty("troubReceiptDt")
	private String troubReceiptDt;
	
	@JsonProperty("page")
	private int page;
	
	@JsonProperty("size")
	private int size;
	
	@JsonProperty("officesCode")
	private String[] officesCode;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("TTID")
	private String TTID;
	
	@JsonProperty("rsvDt")
	private String rsvDt;
	
	@JsonProperty("rsvFromDt")
	private String rsvFromDt;
	
	@JsonProperty("rsvToDt")
	private String rsvToDt;
	
}
