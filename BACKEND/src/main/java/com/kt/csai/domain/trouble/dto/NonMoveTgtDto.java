package com.kt.csai.domain.trouble.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.global.util.StringUtil;

import lombok.Data;

@Data
public class NonMoveTgtDto {
	
	@JsonProperty("ttId")
	private String ttId;
	
	@JsonProperty("custName")
	private String custName;
	
	@JsonProperty("svcMainTypeCodeDesc")
	private String svcMainTypeCodeDesc;
	
	@JsonProperty("svcId")
	private String svcId;
	
	@JsonProperty("reporterTelNum1")
	private String reporterTelNum1;
	
	@JsonProperty("reporterTelNum2")
	private String reporterTelNum2;
	
	@JsonProperty("troubReceiptDt")
	private String troubReceiptDt;
	
	@JsonProperty("firstRsvWishDate")
	private String firstRsvWishDate;
	
	@JsonProperty("firstRsvWishTime")
	private String firstRsvWishTime;
	
	@JsonProperty("reporterOpinion")
	private String reporterOpinion;
	
	@JsonProperty("svcMainClsCode")
	private String svcMainClsCode;
	
	@JsonProperty("officesCodeDesc")
	private String officesCodeDesc;
	
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
