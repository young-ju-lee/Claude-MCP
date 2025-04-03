package com.kt.csai.domain.consult.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kt.csai.domain.consult.exception.model.ConsultErrorCode;

import lombok.Getter;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
@Getter
public class ConsultServiceUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ConsultErrorCode consultErrorCode;
	private String consultId;

	public ConsultServiceUnavailableException(ConsultErrorCode consultErrorCode) {
		super(consultErrorCode.getErrorMessage());
		this.consultErrorCode = consultErrorCode;
	}
	
	public ConsultServiceUnavailableException(ConsultErrorCode consultErrorCode, String consultId) {
		super(consultErrorCode.getErrorMessage());
		this.consultErrorCode = consultErrorCode;
		this.consultId = consultId;
	}
}
