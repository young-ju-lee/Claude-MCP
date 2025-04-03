package com.kt.csai.domain.consult.payload.send.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kt.csai.domain.consult.model.chat.Chat;
import com.kt.csai.domain.consult.payload.receive.ResultResponse;
import com.kt.csai.global.util.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ChatVideoWrite extends Chat {
	@JsonProperty("user_key")
	protected String userKey;

	@JsonProperty("serial_number")
	protected String serialNumber;
	
	@JsonProperty("message_type")
	private String messageType;

	@JsonProperty("file_url")
	private String fileUrl;
	
	public ChatVideoWrite(ResultResponse response, String userKey) {
		this.messageType = "VI";
		this.serialNumber = response.getSerialNumber();
		this.fileUrl = response.getFile();
		this.userKey = userKey;
	}
	
	@Override
	public void generateSerialNumber() {
		this.serialNumber = StringUtil.generateSerialNumber();
	}
}
