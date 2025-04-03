package com.kt.csai.domain.consult.model.chat;

import com.kt.csai.domain.consult.payload.receive.ResultResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatType {
	TEXT("TX"), IMAGE("IM"), VIDEO("VI");

	private final String type;

	public static ChatType chatType(ResultResponse resultResponse) {
		if (resultResponse.getImage() != null && !resultResponse.getImage().isEmpty()) {
			return ChatType.IMAGE;
		} else if (resultResponse.getFile() != null && !resultResponse.getFile().isEmpty()) {
			return ChatType.VIDEO;
		}
		return ChatType.TEXT;
	}

}
