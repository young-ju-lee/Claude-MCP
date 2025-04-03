package com.kt.csai.global.socket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContentType {
	TEXT("TX")
	, IMAGE("IM")
	, VIDEO("VI")
	;
	
	private final String type;
	
	public static ContentType convertMessageTypeToContentType(String messageType) {
		switch (messageType) {
		case "text":
			return TEXT;
		case "photo":
			return IMAGE;
		case "video":
			return VIDEO;
		}
		
		return TEXT;
	}
}

