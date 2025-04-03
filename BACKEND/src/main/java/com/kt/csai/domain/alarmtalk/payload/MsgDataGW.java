package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.ErrInfoGWDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MsgDataGW {

	@JsonProperty("senderid")	
	private String senderId;	
	@JsonProperty("to")
	private String to;
	@JsonProperty("content")
	private String content;				

	public MsgDataGW(ErrInfoGWDto errinfo) {		 		
		this.senderId = "";
		//this.content = "OSS-OM GW 에러가 발생했으니 즉시 확인 바랍니다. ";
		this.to = errinfo.getTelNum();
		this.content = "OSS-OM GW 연동 에러 발생 "+errinfo.getWfwNm()+" 즉시 확인바랍니다";
	}

}
