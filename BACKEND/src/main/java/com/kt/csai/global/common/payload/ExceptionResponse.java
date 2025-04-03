package com.kt.csai.global.common.payload;

import com.kt.csai.domain.alarmtalk.exception.model.AlarmTalkErrorCode;
import com.kt.csai.domain.consult.exception.model.ConsultErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
	private String message;
	private String errorCode;
	
	public ExceptionResponse(AlarmTalkErrorCode errorCode) {
		this.errorCode = errorCode.getErrorCode();
		this.message = errorCode.getErrorMessage();
	}
	
	public ExceptionResponse(ConsultErrorCode errorCode) {
		this.errorCode = errorCode.getErrorCode();
		this.message = errorCode.getErrorMessage();
	}
}
