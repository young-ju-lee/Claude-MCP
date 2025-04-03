package com.kt.csai.global.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.kt.csai.domain.log.user.model.OperationType;
import com.kt.csai.domain.log.user.service.UserAccessService;
import com.kt.csai.global.util.CookieUtil;
import com.kt.csai.global.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtLogoutHandler implements LogoutSuccessHandler {
	private final CookieUtil cookieUtil;
	private final JwtUtil jwtUtil;
	private final UserAccessService userAccessService;

	@Value("${URL.WEB}")
	private String redirectUrl;
	
	@Override
	@Transactional
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String strUserInfo = jwtUtil.getUserInfo(cookieUtil.getCookie(request, JwtUtil.REFRESH_TOKEN_NAME).getValue());
		String userId = jwtUtil.convertTokenToUserInfo(strUserInfo).getLoginId();
		userAccessService.addLog(request, OperationType.LOGOUT, userId);
		
		cookieUtil.clear(response, JwtUtil.ACCESS_TOKEN_NAME);
		cookieUtil.clear(response, JwtUtil.REFRESH_TOKEN_NAME);
		
		response.sendRedirect(redirectUrl); // 로그인 페이지로 수정
	}

}
