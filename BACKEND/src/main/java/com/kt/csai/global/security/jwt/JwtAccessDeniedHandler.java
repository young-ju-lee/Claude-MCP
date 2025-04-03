package com.kt.csai.global.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Value("${URL.WEB}")
	private String redirectUrl;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		/*
		 * 권한이 없는 경우 
		 * 해당 핸들러는 403 error시
		 */
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.sendRedirect(redirectUrl);
	}
}
