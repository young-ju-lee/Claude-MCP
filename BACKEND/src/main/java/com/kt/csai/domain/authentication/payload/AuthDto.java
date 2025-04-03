package com.kt.csai.domain.authentication.payload;

import lombok.Data;

@Data
public class AuthDto {
	private String empNo;
	private String refreshToken;
}
