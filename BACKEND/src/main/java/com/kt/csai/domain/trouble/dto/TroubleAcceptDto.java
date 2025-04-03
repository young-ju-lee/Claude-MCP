package com.kt.csai.domain.trouble.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.global.util.StringUtil;

import lombok.Data;

@Data
public class TroubleAcceptDto {
	
	@JsonProperty("ttId")
	private String ttId;
	
	@JsonProperty("troubReceiptTypecodeDesc")
	private String troubReceiptTypecodeDesc;
	
	@JsonProperty("reportTypeCodeDesc")
	private String reportTypeCodeDesc;
	
	@JsonProperty("custName")
	private String custName;
	
	@JsonProperty("reporterTelNum1")
	private String reporterTelNum1;
	
	@JsonProperty("reporterTelNum2")
	private String reporterTelNum2;
	
	@JsonProperty("svcId")
	private String svcId;
	
	@JsonProperty("svcMainTypeCodeDesc")
	private String svcMainTypeCodeDesc;
	
	@JsonProperty("troubReceiptDt")
	private String troubReceiptDt;
		
	@JsonProperty("rsvDt")
	private String rsvDt;
	
	@JsonProperty("rsvTime")
	private String rsvTime;
	
	@JsonProperty("reporterOpinion")
	private String reporterOpinion;
	
//	public String getReporterTelNum1() {
//		return StringUtil.maskingForTel(reporterTelNum1);
//	}
//	
//	public String getReporterTelNum2() {
//		return StringUtil.maskingForTel(reporterTelNum2);
//	}
//	
//	public String getCustName() {
//		return StringUtil.maskingForName(custName);
//	}
//	
//	public String getSvcId() {
//		return StringUtil.maskingForTel(this.svcId);
//	}	
}
