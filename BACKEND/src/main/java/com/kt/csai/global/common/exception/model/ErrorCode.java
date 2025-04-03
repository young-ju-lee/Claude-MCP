package com.kt.csai.global.common.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	INVALID_REQUEST("CE0001", "잘못된 요청입니다.")
	, INVALID_READ_CHAT_FILE("C0001", "채팅 로그 파일 조회 실패")
	, INVALID_WRITE_CHAT_FILE("C0002", "채팅 로그 기록 실패")
	,
	;
	
	private final String errorCode;
	private final String errorMessage;
}
