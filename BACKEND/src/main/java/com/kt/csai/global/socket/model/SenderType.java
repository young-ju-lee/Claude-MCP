package com.kt.csai.global.socket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SenderType {
	CUSTOMER("CT")
	, CONSULT("CS")
	, NOTIFICATION("NT")
	;
	
	private final String type;
}
