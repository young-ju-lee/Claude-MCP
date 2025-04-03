package com.kt.csai.domain.consult.payload.cache;

import com.kt.csai.domain.consult.model.chat.Chat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatInfo {
	private String serialNumber;
	private Chat chat;
}
