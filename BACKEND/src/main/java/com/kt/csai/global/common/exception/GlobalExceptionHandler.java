package com.kt.csai.global.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kt.csai.domain.alarmtalk.exception.AlarmTalkServiceUnavailableException;
import com.kt.csai.domain.consult.exception.ConsultServiceUnavailableException;
import com.kt.csai.domain.consult.exception.model.ConsultErrorCode;
import com.kt.csai.global.common.payload.CommonResponse;
import com.kt.csai.global.common.payload.ExceptionResponse;
import com.kt.csai.global.socket.model.ContentType;
import com.kt.csai.global.socket.model.SenderType;
import com.kt.csai.global.socket.service.SocketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

	private SocketService socketService;

	@ExceptionHandler(AlarmTalkServiceUnavailableException.class)
	public ResponseEntity<?> exceptionHandler(AlarmTalkServiceUnavailableException exception) {
		log.error("AlarmTalkServiceUnavailableException ", exception);

		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getAlarmTalkErrorCode());

		CommonResponse<ExceptionResponse> response = new CommonResponse<>();
		response.setSuccess(false);
		response.setData(exceptionResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler(ConsultServiceUnavailableException.class)
	public ResponseEntity<?> exceptionHandler(ConsultServiceUnavailableException exception) {
		log.error("ConsultServiceUnavailableException ", exception);

		String consultId = exception.getConsultId();
		ConsultErrorCode errorCode = exception.getConsultErrorCode();

		if (consultId != null && !"".equals(consultId)) {
			socketService.sendMessage(consultId, ContentType.TEXT, errorCode.getErrorMessage(), SenderType.NOTIFICATION);
		}

		ExceptionResponse exceptionResponse = new ExceptionResponse(errorCode);

		CommonResponse<ExceptionResponse> response = new CommonResponse<>();
		response.setSuccess(false);
		response.setData(exceptionResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
