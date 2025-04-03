package com.kt.csai.domain.authentication.service;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.authentication.entity.Auth;
import com.kt.csai.domain.authentication.payload.AuthDto;
import com.kt.csai.domain.authentication.payload.LoginRequest;
import com.kt.csai.domain.authentication.payload.ResponseSigninVo;
import com.kt.csai.domain.authentication.payload.SigninRequest;
import com.kt.csai.domain.authentication.repository.AuthRepository;
import com.kt.csai.global.common.payload.CommonResponse;
import com.kt.csai.global.security.custom.CustomAuthenticationProvider;
import com.kt.csai.global.util.CookieUtil;
import com.kt.csai.global.util.JwtUtil;
import com.kt.csai.global.util.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {

	private final AuthRepository authRepository;
	private final ModelMapper modelMapper;
	private final JwtUtil jwtUtil;
	private final CookieUtil cookieUtil;

	public void authenticationRegistration(LoginRequest loginRequest) {
		Authentication authentication = new CustomAuthenticationProvider().authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmpNo(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public void authenticationRegistration(SigninRequest loginRequest) {
		Authentication authentication = new CustomAuthenticationProvider().authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmpNo(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public CommonResponse<?> getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		CommonResponse<ResponseSigninVo> commonResponse = new CommonResponse<>();
		ResponseSigninVo responseData = null;
		final Cookie refreshToken = cookieUtil.getCookie(request, JwtUtil.REFRESH_TOKEN_NAME);

		boolean validToken = !(refreshToken == null || jwtUtil.isTokenExpired(refreshToken.getValue()));

		if (validToken) {
			String strUserInfo = jwtUtil.getUserInfo(refreshToken.getValue());
			responseData = jwtUtil.convertTokenToUserInfo(strUserInfo);
			
//			responseData.setUserName(StringUtil.maskingForName(responseData.getUserName()));
//			responseData.setLoginId(StringUtil.maskingForEmployeeNumber(responseData.getLoginId()));
//			responseData.setMobile(StringUtil.maskingForTel(responseData.getMobile()));
		}

		

		commonResponse.setData(responseData);

		return commonResponse;
	}

	public ResponseSigninVo getUserInfo(String token) {

		String strUserInfo = jwtUtil.getUserInfo(token);
		return jwtUtil.convertTokenToUserInfo(strUserInfo);
	}

	public void doLogin(ResponseSigninVo userInfo, HttpServletResponse response) {		
		log.info(userInfo.toString());
		
		String empNo = userInfo.getLoginId();
		AuthDto authDto = findUserByEmpNo(empNo);
		if (authDto == null) {
			authDto = new AuthDto();
			authDto.setEmpNo(empNo);
		}

		String accessJwt = jwtUtil.generateToken(userInfo);
		String refreshJwt = jwtUtil.generateRefreshToken(userInfo);
		authDto.setRefreshToken(refreshJwt);
		log.info(authDto.toString());
		authRepository.save(modelMapper.map(authDto, Auth.class));

		cookieUtil.createCookie(response, JwtUtil.ACCESS_TOKEN_NAME, accessJwt);
		cookieUtil.createCookie(response, JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
	}

	public AuthDto findUserByEmpNo(String empNo) {
		Optional<Auth> auth = authRepository.findById(empNo);

		if (!auth.isPresent())
			return null;

		return modelMapper.map(auth.get(), AuthDto.class);
	}

	public String getUserIdByRefreshToken(String refreshToken) {
		Optional<Auth> auth = authRepository.findByRefreshToken(refreshToken);

		if (!auth.isPresent())
			return null;

		AuthDto authDto = modelMapper.map(auth.get(), AuthDto.class);
		return authDto.getEmpNo();
	}

	@Transactional
	public AuthDto updateRefreshToken(AuthDto authDto) {
		Auth auth = modelMapper.map(authDto, Auth.class);

		auth = authRepository.save(auth);

		return modelMapper.map(auth, AuthDto.class);
	}

	public CommonResponse<Boolean> renewalRefreshToken(HttpServletResponse response, HttpServletRequest request) {
		final Cookie refreshToken = cookieUtil.getCookie(request, JwtUtil.REFRESH_TOKEN_NAME);
		CommonResponse<Boolean> res = new CommonResponse<>();
		boolean isSuccess = false;

		if (refreshToken != null) {
			String refreshJwt = refreshToken.getValue();

			ResponseSigninVo userInfo = this.getUserInfo(refreshJwt);
			String empNo = userInfo.getLoginId();

			AuthDto authDto = this.findUserByEmpNo(empNo);
			if (jwtUtil.validateToken(refreshJwt, authDto)) {
				String accessJwt = jwtUtil.generateToken(userInfo);

				String newRefreshJwt = jwtUtil.generateRefreshToken(userInfo);
				cookieUtil.createCookie(response, JwtUtil.ACCESS_TOKEN_NAME, accessJwt);
				cookieUtil.createCookie(response, JwtUtil.REFRESH_TOKEN_NAME, newRefreshJwt);

				authDto.setRefreshToken(newRefreshJwt);

				authDto = this.updateRefreshToken(authDto);
				if (newRefreshJwt.equals(authDto.getRefreshToken())) {
					log.info("update success");
				}

				isSuccess = true;
			} else {
				cookieUtil.clearTokenCookies(response);
			}
		}

		res.setData(isSuccess);

		return res;
	}

}
