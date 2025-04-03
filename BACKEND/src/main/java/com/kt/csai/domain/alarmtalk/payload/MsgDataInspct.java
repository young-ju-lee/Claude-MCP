package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.InspctDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MsgDataInspct {
	@JsonProperty("senderid")
	private String senderId;
	@JsonProperty("to")
	private String to;
	@JsonProperty("content")
	private String content;	
	
	public MsgDataInspct(InspctDto inspct) {
		this.senderId = "";		
		this.to = inspct.getTelNm();
		this.content = "안녕하세요. "+inspct.getIsptDt()+" 조간점검 근무자는 "+inspct.getName()+" 입니다.";
	}

}
