package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.domain.alarmtalk.entity.AlarmTalkId;
import com.kt.csai.domain.alarmtalk.entity.Customer;
import com.kt.csai.domain.alarmtalk.entity.Sender;

import lombok.Data;

@Data
public class AlarmTalkHistDto {
	
	@JsonProperty("alarmTalkId")
	private AlarmTalkId alarmTalkId;
	
	@JsonProperty("sender")
	private Sender sender;
	
	@JsonProperty("customer")
	private Customer customer;
	
	@JsonProperty("troubReceiptDt")
	private String troubReceiptDt;
	
	@JsonProperty("reporterOpinion")
	private String reporterOpinion;
	
	@JsonProperty("svcMainClsCode")
	private String svcMainClsCode;
	
	@JsonProperty("svcMainClsCodeDesc")
	private String svcMainClsCodeDesc;
	
}
