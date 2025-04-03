package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MsgData {
	@JsonProperty("senderid")
	private String senderId;
	@JsonProperty("to")
	private String to;
	@JsonProperty("content")
	private String content;
	
	public MsgData() {
		this.senderId = "";
		//this.content = "안녕하세요. KT고객센터 입니다.\n고객님께서 요청하신 빠른 고장 AS를 위해 상담원이 잠시 후 연락드릴 예정입니다.\n신속한 상담을 원하시면 ‘상담하기’ 버튼을 눌러주세요.";
		this.content = "안녕하세요. KT서비스입니다.\n고객님께서 고장 문의 해주신 내용으로 'KT 전문 상담원'이 신속한 상담을 통해 해결해 드릴 예정입니다.\n 신속한 해결과 상담을 원하시면 '상담하기' 버튼을 눌러주세요.";
	}
	
	public MsgData(String to) {
		this();
		this.to = to;
	}
}
