package com.kt.csai.global.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kt.csai.global.common.exception.model.ErrorCode;

import lombok.Getter;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class InvalidRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;

	public InvalidRequestException(ErrorCode errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}
}
