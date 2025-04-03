package com.kt.csai.domain.authentication.payload;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class RequestSigninVo {

	private String loginId;		// 로그인ID
	private String loginPwd;	// 로그인PWD
	private String ossAthnInntNo;	// OSS인증고유번호
	private String ontmPwd;		// OTP번호
	
}
