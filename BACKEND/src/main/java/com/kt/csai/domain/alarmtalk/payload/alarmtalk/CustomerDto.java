package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerDto {
	@JsonProperty("cust_nm")
	private String custNm;	
	
	@JsonProperty("cust_tel_no")
	private String custTelNo;
}
