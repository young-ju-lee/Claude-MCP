package com.kt.csai.domain.log.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationType {
	LOGIN("login")
	, LOGOUT("logout")
	;

	private final String type;
}
