package com.kt.csai.global.common.component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kt.csai.domain.authentication.payload.AuthDto;
import com.kt.csai.domain.authentication.payload.ResponseSigninVo;
import com.kt.csai.domain.authentication.service.TokenService;
import com.kt.csai.global.util.CookieUtil;
import com.kt.csai.global.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CsaiInterceptor implements HandlerInterceptor {
	
	private final TokenService tokenService;
	private final JwtUtil jwtUtil;
	private final CookieUtil cookieUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		this.renewalToken(request, response);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}	
	
	private boolean renewalToken(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = false;
		
		final Cookie refreshToken = cookieUtil.getCookie(request, JwtUtil.REFRESH_TOKEN_NAME);
		if(refreshToken == null) {
			cookieUtil.clearTokenCookies(response);
			return isSuccess;
		}

		String refreshJwt = refreshToken.getValue();
		
		ResponseSigninVo userInfo = tokenService.getUserInfo(refreshJwt);
		String empNo = userInfo.getLoginId();

		AuthDto authDto = tokenService.findUserByEmpNo(empNo);
		if (jwtUtil.validateToken(refreshJwt, authDto)) {
			String newRefreshJwt = jwtUtil.generateRefreshToken(userInfo);
			cookieUtil.createCookie(response, JwtUtil.REFRESH_TOKEN_NAME, newRefreshJwt);
			authDto.setRefreshToken(newRefreshJwt);

			authDto = tokenService.updateRefreshToken(authDto);
			newRefreshJwt.equals(authDto.getRefreshToken());

			isSuccess = true;
		} else {
			cookieUtil.clearTokenCookies(response);
		}

		return isSuccess;
	}
}
