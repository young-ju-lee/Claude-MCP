package com.kt.csai.domain.log.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SecurityType {
	ACCESS("ACCESS")
	, PROCESS("PRCS")
	, MANAGEMENT("MNGT")
	, API("API")
	, AUTH("AUTH")
	, UPIP("UPIP")
	;

	private final String type;
}