package com.kt.csai.domain.consult.exception.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConsultErrorCode {
	INVALID_IMAGE_EXCEPTION("-505", "이미지의 크기는 3MB 이하만 전송 가능합니다.")
	, BLOCKED_USER_MESSAGE_EXCEPTION("-511", "메세지를 차단한 사용자입니다.")
	, NOT_FOUND("404", "데이터를 찾을 수 없습니다.")
	, INVALID_REQUEST("400", "유효하지 않은 요청입니다.")
	;
	private final String errorCode;
	private final String errorMessage;

	private static final Map<String, ConsultErrorCode> BY_ERROR_CODE = Stream.of(values())
			.collect(Collectors.toMap(ConsultErrorCode::getErrorCode, e -> e));
	
	public static ConsultErrorCode valueOfErrorCode(String errorCode) {
		return BY_ERROR_CODE.get(errorCode);
	}
}
