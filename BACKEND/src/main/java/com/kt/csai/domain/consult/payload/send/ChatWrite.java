package com.kt.csai.domain.consult.payload.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.domain.consult.payload.receive.UserMetadataRequest;
import com.kt.csai.global.socket.payload.SocketMessageDto;
import com.kt.csai.global.util.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatWrite {
	@JsonProperty("user_key")
	private String userKey;
	@JsonProperty("serial_number")
	private String serialNumber;
	@JsonProperty("message_type")
	private String messageType;
	@JsonProperty("message")
	private String message;
	@JsonProperty("image_url")
	private String imageUrl;
	@JsonProperty("auto_answer")
	private String autoAnswer;
	@JsonProperty("ref_key")
	private String refKey;

	public ChatWrite() {
		this.serialNumber = StringUtil.generateSerialNumber();
	}

	public ChatWrite(UserMetadataRequest userMetadataRequest) {
		this();
		this.userKey = userMetadataRequest.getUserKey();
		this.message = "신규 대화방이 생성되었습니다.";
	}

	public ChatWrite(SocketMessageDto socketMessageDto) {
		this();
		this.userKey = socketMessageDto.getUserKey();
		this.message = socketMessageDto.getContent();
	}

	public void closeChat(String userKey) {
		this.userKey = userKey;

		//this.message = "상담이 종료되었습니다.\n감사합니다.\n이후 보내시는 메세지는 전달이 되지 않습니다.";  
		this.message = "소중한 시간 내주셔서 감사합니다.\n'KT 전문 상담원'과 상담이 종료되었습니다.\n이후 보내시는 메세지는 전달이 되지 않습니다.";    
	}
}
