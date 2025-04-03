package com.kt.csai.domain.trouble.payload.response;

import com.kt.csai.global.util.StringUtil;

import lombok.Data;

@Data
public class NonMoveTarget {
	private String ttId;
	private String custName;
	private String svcMainTypeCodeDesc;
	private String svcId;
	private String reporterTelNum1;
	private String reporterTelNum2;
	private String troubReceiptDt;
	private String firstRsvWishDate;
	private String firstRsvWishTime;
	private String reporterOpinion;
	private String svcMainClsCode;
	private String officesCodeDesc;
	private String cnvrstSttus;
	private String cnvrstSttusTxt;
	private String rsvDt;
	private String rsvTime;
	private String reservationTime;
	
//	public String getReporterTelNum1() {
//		return StringUtil.maskingForTel(this.reporterTelNum1);
//	}
//	
//	public String getReporterTelNum2() {
//		return StringUtil.maskingForTel(this.reporterTelNum2);
//	}
//	
//	public String getCustName() {
//		return StringUtil.maskingForName(this.custName);
//	}
//	
//	public String getSvcId() {
//		return StringUtil.maskingForTel(this.svcId);
//	}
}
