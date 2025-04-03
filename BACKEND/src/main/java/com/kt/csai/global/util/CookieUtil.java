package com.kt.csai.global.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CookieUtil {
	public Cookie createCookie(String cookieName, String value) {
		Cookie token = new Cookie(cookieName, value);
		token.setHttpOnly(true);
		token.setMaxAge((int)JwtUtil.TOKEN_VALIDATION_SECOND); // 단위가 '초' 단위이기 때문에 / 1000
		token.setPath("/");
		
		return token;
	}
	
	public void createCookie(HttpServletResponse response, String cookieName, String value) {
		ResponseCookie cookie = ResponseCookie.from(cookieName, value)
				.path("/")
				.secure(true)
				.sameSite("None")
				.domain("csai.kt.co.kr")
				.maxAge((int)JwtUtil.TOKEN_VALIDATION_SECOND)
				.build();
		
		
		response.addHeader("Set-Cookie", cookie.toString());
	}
	
	public Cookie getCookie(HttpServletRequest request, String cookieName) {
		final Cookie[] cookies = request.getCookies();
		if(cookies == null)
			return null;
		
		for(Cookie cookie : cookies) {
			if(cookieName.equals(cookie.getName()))
				return cookie;
		}
		
		return null;
	}
	
	public Cookie clear(String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		
		return cookie;
	}
	
	public void clear(HttpServletResponse response, String cookieName) {
		ResponseCookie cookie = ResponseCookie.from(cookieName, null)
				.path("/")
				.secure(true)
				.sameSite("None")
				.domain("csai.kt.co.kr")
				.maxAge(0)
				.build();
		
		
		response.addHeader("Set-Cookie", cookie.toString());
	}
	
	public void setMaxAge(Cookie token, long second) {
		token.setMaxAge((int)second);
	}
	
	public void clearTokenCookies(HttpServletResponse response) {
		clear(response, JwtUtil.ACCESS_TOKEN_NAME);
		clear(response, JwtUtil.REFRESH_TOKEN_NAME);
	}
}
