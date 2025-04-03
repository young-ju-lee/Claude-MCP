package com.kt.csai.global.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kt.csai.global.common.exception.model.ErrorCode;

import lombok.Getter;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class InternalServerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
	
	public InternalServerException(ErrorCode errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}
}
