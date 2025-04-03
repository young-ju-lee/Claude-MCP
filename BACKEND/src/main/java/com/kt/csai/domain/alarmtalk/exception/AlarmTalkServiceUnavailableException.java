package com.kt.csai.domain.alarmtalk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kt.csai.domain.alarmtalk.exception.model.AlarmTalkErrorCode;

import lombok.Getter;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
@Getter
public class AlarmTalkServiceUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private AlarmTalkErrorCode alarmTalkErrorCode;

	public AlarmTalkServiceUnavailableException(AlarmTalkErrorCode alarmTalkErrorCode) {
		super(alarmTalkErrorCode.getErrorMessage());
		this.alarmTalkErrorCode = alarmTalkErrorCode;
	}
}
