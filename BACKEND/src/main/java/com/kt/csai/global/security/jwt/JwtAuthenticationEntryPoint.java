package com.kt.csai.global.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	@Value("${URL.WEB}")
	private String redirectUrl;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		/*
		 * 로그인이 되지 않은 경우 
		 * 해당 핸들러는 401 error시
		 */
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.sendRedirect(redirectUrl);
	}
}
