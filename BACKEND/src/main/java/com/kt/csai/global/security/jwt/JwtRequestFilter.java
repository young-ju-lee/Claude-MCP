package com.kt.csai.global.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kt.csai.domain.authentication.payload.AuthDto;
import com.kt.csai.domain.authentication.payload.ResponseSigninVo;
import com.kt.csai.domain.authentication.payload.SigninRequest;
import com.kt.csai.domain.authentication.service.TokenService;
import com.kt.csai.domain.log.user.model.OperationType;
import com.kt.csai.domain.log.user.service.UserAccessService;
import com.kt.csai.global.util.CookieUtil;
import com.kt.csai.global.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;
	private final CookieUtil cookieUtil;
	private final TokenService tokenService;
	private final UserAccessService userAccessService;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// 필터 제외 경로
		return request.getServletPath().startsWith("/auth");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final Cookie accessToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
		final Cookie refreshToken = cookieUtil.getCookie(request, JwtUtil.REFRESH_TOKEN_NAME);

		if (refreshToken == null) {
			filterChain.doFilter(request, response);
			return;
		}

		String accessJwt = accessToken == null ? null : accessToken.getValue();
		String refreshJwt = refreshToken.getValue();

		/*
		 * access token이 존재하고 유효하면 인증 처리
		 */
		if (accessToken != null && refreshToken != null) {
			accessJwt = accessToken.getValue();

			if (!doAuthentication(accessJwt)) {
				accessJwt = null;
			}
		}

		/*
		 * refresh token 검증 후 access token 재발행
		 */
		if (accessJwt == null && refreshJwt != null) {

			accessJwt = renewalAccessToken(response, refreshJwt);
			if (accessJwt == null) {
				String userId = tokenService.getUserIdByRefreshToken(refreshJwt);
				if (userId != null)
					userAccessService.addLog(request, OperationType.LOGOUT, userId);

				refreshJwt = null;
			}
		}

		if (accessJwt == null && refreshJwt == null) {
			cookieUtil.clearTokenCookies(response);
		}

		filterChain.doFilter(request, response);
	}

	private boolean renewalRefreshToken(HttpServletResponse response, String refreshJwt) {
		boolean isSuccess = false;

		ResponseSigninVo userInfo = tokenService.getUserInfo(refreshJwt);
		String empNo = userInfo.getLoginId();

		AuthDto authDto = tokenService.findUserByEmpNo(empNo);
		if (jwtUtil.validateToken(refreshJwt, authDto)) {
			String newRefreshJwt = jwtUtil.generateRefreshToken(userInfo);
			cookieUtil.createCookie(response, JwtUtil.REFRESH_TOKEN_NAME, newRefreshJwt);
			authDto.setRefreshToken(newRefreshJwt);

			authDto = tokenService.updateRefreshToken(authDto);
			if (newRefreshJwt.equals(authDto.getRefreshToken())) {
				log.info("update success");
			} else {
				log.info("업데이트 실패");
			}

			isSuccess = true;
		} else {
			cookieUtil.clearTokenCookies(response);
		}

		return isSuccess;
	}

	private String renewalAccessToken(HttpServletResponse response, String refreshJwt) {
		String newAccessJwt = null;

		if (!jwtUtil.isTokenExpired(refreshJwt)) {
			ResponseSigninVo userInfo = tokenService.getUserInfo(refreshJwt);
			String empNo = userInfo.getLoginId();
			AuthDto authDto = tokenService.findUserByEmpNo(empNo);

			if (jwtUtil.validateToken(refreshJwt, authDto)) {
				newAccessJwt = jwtUtil.generateToken(userInfo);
				cookieUtil.createCookie(response, JwtUtil.ACCESS_TOKEN_NAME, newAccessJwt);

				tokenService.authenticationRegistration(SigninRequest.builder().empNo(empNo).build());
			}
		}

		return newAccessJwt;
	}

	public boolean doAuthentication(String accessJwt) {
		boolean isSuccess = false;

		if (!jwtUtil.isTokenExpired(accessJwt)) {
			ResponseSigninVo userInfo = tokenService.getUserInfo(accessJwt);
			String empNo = userInfo.getLoginId();
			tokenService.authenticationRegistration(SigninRequest.builder().empNo(empNo).build());

			isSuccess = true;
		}

		return isSuccess;
	}
}
