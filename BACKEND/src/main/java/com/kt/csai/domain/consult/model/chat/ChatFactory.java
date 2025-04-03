package com.kt.csai.domain.consult.model.chat;

import com.kt.csai.domain.consult.payload.receive.ResultResponse;
import com.kt.csai.domain.consult.payload.send.chat.ChatImageWrite;
import com.kt.csai.domain.consult.payload.send.chat.ChatVideoWrite;

public class ChatFactory {
	public static Chat create(ResultResponse response, String userKey) {
		ChatType type = ChatType.chatType(response);
		switch (type) {
		case TEXT:
			return null;
		case IMAGE:
			return new ChatImageWrite(response, userKey);
		case VIDEO:
			return new ChatVideoWrite(response, userKey);
		default:
			return null;
		}
	}
}
