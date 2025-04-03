package com.kt.csai.domain.authentication.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.csai.domain.authentication.payload.LoginRequest;
import com.kt.csai.domain.authentication.payload.RequestSigninVo;
import com.kt.csai.domain.authentication.payload.ResponseSigninVo;
import com.kt.csai.domain.authentication.service.AuthService;
import com.kt.csai.domain.authentication.service.TokenService;
import com.kt.csai.global.security.encryption.service.RsaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
//@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	private final TokenService tokenService;
	private final RsaService rsaService;

	@PostMapping("/signin")
	@ResponseBody
	public ResponseEntity<?> requestSignin(@RequestBody RequestSigninVo requestSigninVo, HttpServletRequest req,
			HttpServletResponse res, HttpSession session) {

		log.info("========== start AuthController.requestSignin ==========");
		log.info("## RequestSigninVo : {}", requestSigninVo.toString());

		// BaseResponse baseResponse = authService.requestSignin(requestSigninVo, req);

		log.info("========== end AuthController.requestSignin ==========");
		return new ResponseEntity<>(authService.requestSignin(requestSigninVo, req, res, session), HttpStatus.OK);
	}
	
	
	@PostMapping("/skipOtp")
	@ResponseBody
	public ResponseEntity<?> requestSkipOtp(@RequestBody RequestSigninVo requestSigninVo, HttpServletRequest req,
			HttpServletResponse res, HttpSession session) {

		log.info("========== start AuthController.requestSkipOtp ==========");
		log.info("## RequestSigninVo : {}", requestSigninVo.toString());

		log.info("========== end AuthController.requestSkipOtp ==========");
		return new ResponseEntity<>(authService.requestSkipOtp(requestSigninVo, req, res, session), HttpStatus.OK);
		
	}

	@PostMapping("/authOtp")
	@ResponseBody
	public ResponseEntity<?> requestOtpAuth(@RequestBody RequestSigninVo requestSigninVo, HttpServletRequest req,
			HttpServletResponse res, HttpSession session) throws IOException {
		log.info("========== start AuthController.requestOtpAuth ==========");
		log.info("## RequestSigninVo : {}", requestSigninVo.toString());	
		
		// BaseResponse baseResponse = authService.requestOtpAuth(requestSigninVo, req,
		// res);

		log.info("========== end AuthController.requestOtpAuth ==========");
		return new ResponseEntity<>(authService.requestOtpAuth(requestSigninVo, req, res, session), HttpStatus.OK);
	}
		
	@GetMapping("/signout")
	@ResponseBody
	public ResponseEntity<?> requestSignout() {
		log.info("========== start AuthController.requestSignout ==========");
		log.info("========== end AuthController.requestSignout ==========");
		return new ResponseEntity<>("logout", HttpStatus.OK);
	}

	@GetMapping("/denied")
	@ResponseBody
	public ResponseEntity<?> deny() {
		return new ResponseEntity<>("denied", HttpStatus.OK);
		// return null; // new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "로그인이
		// 필요합니다.");
	}

	@GetMapping("/renewal-token")
	public ResponseEntity<?> renewalRefreshToken(HttpServletRequest request, HttpServletResponse response) {
		return new ResponseEntity<>(tokenService.renewalRefreshToken(response, request), HttpStatus.OK);
	}

	@GetMapping("/user-info")
	public ResponseEntity<?> getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		return new ResponseEntity<>(tokenService.getUserInfo(request, response), HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<?> loginTest(LoginRequest loginRequest, HttpServletRequest req, HttpServletResponse res) {
		ResponseSigninVo sign = new ResponseSigninVo();
		sign.setLoginId("82256987");
		tokenService.doLogin(sign, res);
		return new ResponseEntity<>("login", HttpStatus.OK);
	}

	@GetMapping("/public-key")
	public ResponseEntity<?> getPublicKey(HttpSession session) {
		return new ResponseEntity<>(rsaService.generatePublicKey(session), HttpStatus.OK);
	}
}
