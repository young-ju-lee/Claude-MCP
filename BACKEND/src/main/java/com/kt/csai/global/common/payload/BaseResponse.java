package com.kt.csai.global.common.payload;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseResponse {

	public static final int RESULT_CODE_FAIL = 0;
	public static final int RESULT_CODE_SUCCESS = 1;
	public static final int RESULT_CODE_SKIP = 99;
	public static final int RESULT_CODE_SERVER_ERROR = 5;

	public static final int RESULT_CODE_EXCEPTION_CASE = 9;
	public static final int RESULT_CODE_LDAP_FAIL = 94;
	public static final int RESULT_CODE_EXPIRED_INFO = 95;
	public static final int RESULT_CODE_OVER_REQ = 96;

	private int resultCode;
	private String resultMessage;
	private Object data;
	private boolean extraBoolean;

	public BaseResponse() {

	}
   
	public BaseResponse(int resultCode) {
		this.resultCode = resultCode;
	}
   
	public BaseResponse(int resultCode, String resultMessage) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public BaseResponse(int resultCode, String resultMessage, Object data) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.data = data;
	}
   
	public BaseResponse(int resultCode, String resultMessage, Object data, boolean extraBoolean) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.data = data;
		this.extraBoolean = extraBoolean;
	}
}