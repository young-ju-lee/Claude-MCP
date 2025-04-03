package com.kt.csai.domain.authentication.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001.CsaiOtsysOntmPwdInputVo;
import com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001.PcpCustOtpAthnLoginProviderImplServiceClient;
import com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001.ReqCsaiOtpAthnLogIn;
import com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001.ReqCsaiOtpAthnLogInResponse;
import com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0002.PcpCustOtpAthnLoginValidProviderImplServiceClient;
import com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0002.ReqCsaiOtpAthnLogInValid;
import com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0002.ReqCsaiOtpAthnLogInValidResponse;
import com.kt.csai.domain.authentication.payload.RequestSigninVo;
import com.kt.csai.domain.authentication.payload.ResponseSigninVo;
import com.kt.csai.domain.authentication.repository.UserInfoCacheRepository;
import com.kt.csai.domain.log.user.model.OperationType;
import com.kt.csai.domain.log.user.service.UserAccessService;
import com.kt.csai.global.common.payload.BaseResponse;
import com.kt.csai.global.security.encryption.service.RsaService;
import com.kt.csai.global.util.IpAddressUtil;
import com.kt.csai.global.util.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nets.ldap.ADUtilSSL;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
	private final IpAddressUtil ipAddressUtil;
	private final Jaxb2Marshaller marshaller;

	private final TokenService tokenService;

	private final UserInfoCacheRepository userInfoCacheRepository;

	private final UserAccessService userAccessService;

	private final RsaService rsaService;

	@Value("${LDAP.CONN.HOST}")
	private String host;

	@Value("${LDAP.CONN.PORT}")
	private String port;

	@Value("${LDAP.CONN.BASEDN}")
	private String baseDN;

	@Value("${LDAP.CONN.ID}")
	private String connId;

	@Value("${LDAP.CONN.PWD}")
	private String connPwd;

	@Value("${LDAP.CONN.SSLYN}")
	private boolean sslYn;

	@Value("${URL.OSB}")
	private String osbUrl;
	
	//opt skip
	private static boolean isSkip = false;

	public BaseResponse requestSignin(RequestSigninVo requestSigninVo, HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		log.info("========== start AuthService.requestSignin ==========");
		ResponseSigninVo result = new ResponseSigninVo();
		Map userInfoMap = new HashMap<>();
		
		Boolean bLogin = false;
		String loginId;
		String loginPwd;
		try {
			loginId = rsaService.decryption(requestSigninVo.getLoginId(), session);
			loginPwd = rsaService.decryption(requestSigninVo.getLoginPwd(), session);
		} catch (Exception e1) {
			e1.printStackTrace();

			return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "유효하지 않은 사번/비밀번호 입니다.");
		}

		// id, pwd 검증
		BaseResponse valid = isValidLoginInfo(loginId, loginPwd);
		if (valid.getResultCode() == BaseResponse.RESULT_CODE_FAIL) {
			return valid;
		}

		try {
			// LDAP 인증 요청
			bLogin = ADUtilSSL.auth_loginPeriod(host, port, baseDN, loginId, loginPwd, connId, connPwd, sslYn);
			
			if (bLogin == false) {
				return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "유효하지 않은 사번/비밀번호 입니다.");
			}
			
			// LDAP 사용자 정보 요청
			userInfoMap = ADUtilSSL.query_userinfo(host, port, baseDN, loginId, connId, connPwd, sslYn);

			// res VO 세팅
			result = new ResponseSigninVo(userInfoMap);
			result.setLoginId(loginId);
			// OTP 요청정보 세팅
			String ipAddr = ipAddressUtil.getIpAddress(req);

			CsaiOtsysOntmPwdInputVo csaiOtsysOntmPwdInputVo = new CsaiOtsysOntmPwdInputVo();
			csaiOtsysOntmPwdInputVo.setCsaiAthnInntNo(loginId);
			csaiOtsysOntmPwdInputVo.setUserMphonNo(StringUtil.eraseDash((String) userInfoMap.get("mobile")));
			csaiOtsysOntmPwdInputVo.setHandsetType("1");
			csaiOtsysOntmPwdInputVo.setHandsetId(ipAddr);
			csaiOtsysOntmPwdInputVo.setOtpDspTypeId("1");
			csaiOtsysOntmPwdInputVo.setMblSvcTypeCd("");

			ReqCsaiOtpAthnLogIn csaiAthnOtpAthn = new ReqCsaiOtpAthnLogIn();
			csaiAthnOtpAthn.setCsaiOtsysOntmPwdInputVo(csaiOtsysOntmPwdInputVo);

			// OTP 번호 요청
			PcpCustOtpAthnLoginProviderImplServiceClient client = new PcpCustOtpAthnLoginProviderImplServiceClient();
			client.setMarshaller(marshaller);
			client.setUnmarshaller(marshaller);
			ReqCsaiOtpAthnLogInResponse resOTP = client.requestOTPNumber(osbUrl, csaiAthnOtpAthn);
			
			// res VO 세팅
			if (resOTP != null && resOTP.get_return() != null && "1".equals(resOTP.get_return().getRsltCd())) {
				//base 1.1 :: sign 체크시 update ( otp 정상 ) 
				isSkip = false;
				
				result.setOssAthnInntNo(resOTP.get_return().getOssAthnInntNo());
				result.setResCd(resOTP.get_return().getRsltCd());
				result.setErrMsg(resOTP.get_return().getRsltMsg());
				result.setIsSkip(String.valueOf(isSkip));
				
				log.info("========== end AuthService.requestSignin ==========");
				userInfoCacheRepository.addUserInfo(loginId, result);
				return new BaseResponse(BaseResponse.RESULT_CODE_SUCCESS, "", result);
			} else {
				
				//base 1.1 :: sign 체크시 update ( otp 실패 )
				isSkip = true;

				if (resOTP != null && resOTP.get_return() != null) {
					log.info("========== error AuthService.requestSignin ==========");
					log.info("## Error : {}", resOTP.get_return().getRsltMsg());
					
					result.setResCd(resOTP.get_return().getRsltCd());
					result.setErrMsg(resOTP.get_return().getRsltMsg());
					
					if ("7".equals(resOTP.get_return().getRsltCd())) {												
						
						result.setIsSkip(String.valueOf(isSkip));
						
						return new BaseResponse(BaseResponse.RESULT_CODE_EXCEPTION_CASE, "OTP 인증 기능 OFF 상태입니다.", result);
						
					} else if ("10".equals(resOTP.get_return().getRsltCd())) {
						//base 1.1 :: sign 체크시 update ( otp 실패 ) 
						isSkip = false;						
						result.setIsSkip(String.valueOf(isSkip));
						
						return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "10분내 OTP 5회 발송으로 인해 발송이 차단됩니다.", 	result);
						
					} else {
						
						result.setIsSkip(String.valueOf(isSkip));
						
						return new BaseResponse(BaseResponse.RESULT_CODE_SERVER_ERROR, "OTP 발송 연동요청에 실패하였습니다.", result);
						
					}
					
				} else {										
					log.info("========== error AuthService.requestSignin ==========");
					log.info("## Error : {}", "null response");
					
					result.setResCd("");
					result.setErrMsg("null response");
					result.setIsSkip(String.valueOf(isSkip));
					
					return new BaseResponse(BaseResponse.RESULT_CODE_SERVER_ERROR,
							resOTP != null && resOTP.get_return() != null ? resOTP.get_return().getRsltMsg()
									: "OTP 발송 연동요청에 실패하였습니다.",result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("========== error AuthService.requestSignin ==========");
			log.info("## Exception : {}", e);

			if (e.getMessage().indexOf("data 0003") > 0) {
				return new BaseResponse(BaseResponse.RESULT_CODE_LDAP_FAIL, "유효하지 않은 서비스 계정입니다. 시스템관리자에게 문의해주세요.");
			} else if (e.getMessage().indexOf("data 0004") > 0) {
				return new BaseResponse(BaseResponse.RESULT_CODE_EXPIRED_INFO, "비밀번호가 만료되었습니다. 비밀번호를 변경해주세요.");
			} else if (e.getMessage().indexOf("data 0005") > 0) {
				return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "유효하지 않은 사번/비밀번호 입니다.");
			}
		}

		log.info("========== error AuthService.requestSignin ==========");
		return new BaseResponse(BaseResponse.RESULT_CODE_SERVER_ERROR, "LDAP 및 OTP연동에 실패하였습니다.");
	}

	@Transactional
	public BaseResponse requestOtpAuth(RequestSigninVo requestSigninVo, HttpServletRequest req,
			HttpServletResponse res, HttpSession session) {
		tokenService.getUserInfo(req, res);
		log.info("========== start AuthService.requestOtpAuth ==========");

		ResponseSigninVo result = new ResponseSigninVo();

		Boolean bLogin = false;
		
		String loginId;
		String loginPwd;
				
		try {		
			
			loginId = rsaService.decryption(requestSigninVo.getLoginId(), session);
			loginPwd = rsaService.decryption(requestSigninVo.getLoginPwd(), session);
			
			requestSigninVo.setLoginId(loginId);
			requestSigninVo.setLoginPwd(loginPwd);
					
		} catch (Exception e1) {
			e1.printStackTrace();
			
			return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "유효하지 않은 사번/비밀번호 입니다.");
		}

		BaseResponse valid = isValidLoginInfo(loginId, loginPwd);
		if (valid.getResultCode() == BaseResponse.RESULT_CODE_FAIL) {
			return valid;
		}
		if (StringUtil.isNullOrEmpty(requestSigninVo.getOssAthnInntNo())) {
			return new BaseResponse(BaseResponse.RESULT_CODE_OVER_REQ, "OTP 인증 요청을 처음부터 다시 시작해주세요.");
		} else if (StringUtil.isNullOrEmpty(requestSigninVo.getOntmPwd())) {
			return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "OTP 인증번호 정보를 확인해주세요.");
		}

		try {

			// 운영환경 제외 OTP 기능 skip
//			if ( Arrays.stream(environment.getActiveProfiles()).anyMatch(env-> ( !env.equalsIgnoreCase("prod")))
//					&& DEBUG_USERPHONE.equals(request.getUserMphoneNo()) ) {
//				
//				String accessJwt = jwtUtil.doGenerateToken(DEBUG_USERNAME, JwtUtil.TOKEN_VALIDATION_SECOND * 60 * 2); // 2�떆媛� �뿀�슜
//				String refreshJwt = jwtUtil.generateRefreshToken(DEBUG_USERNAME);
//				
//				Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, accessJwt);
//				Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
//				
//				res.addCookie(accessToken);
//				res.addCookie(refreshToken);
//				
//				return new BaseResponse(BaseResponse.RESULT_CODE_SUCCESS);
//			}

			// LDAP 인증 요청
			bLogin = ADUtilSSL.auth_loginPeriod(host, port, baseDN, loginId, loginPwd, connId, connPwd, sslYn);
			log.info("## LDAP auth result : {}", bLogin);
			if (bLogin == false) {
				return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "유효하지 않은 사번/비밀번호 입니다.");
			}

			// OTP 요청정보 세팅
			String ipAddr = ipAddressUtil.getIpAddress(req);

			com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0002.CsaiOtsysOntmPwdInputVo csaiOtsysOntmPwdInputVo = new com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0002.CsaiOtsysOntmPwdInputVo(
					requestSigninVo, ipAddr);

			ReqCsaiOtpAthnLogInValid csaiAthnOtpAthn = new ReqCsaiOtpAthnLogInValid();
			csaiAthnOtpAthn.setCsaiOtsysOntmPwdInputVo(csaiOtsysOntmPwdInputVo);
			
			// OTP 번호 요청
			PcpCustOtpAthnLoginValidProviderImplServiceClient client = new PcpCustOtpAthnLoginValidProviderImplServiceClient();
			client.setMarshaller(marshaller);
			client.setUnmarshaller(marshaller);
			ReqCsaiOtpAthnLogInValidResponse resOTP = client.requestOTPAuth(osbUrl, csaiAthnOtpAthn);
			
			// res VO 세팅
			if (resOTP != null && resOTP.get_return() != null && "1".equals(resOTP.get_return().getRsltCd())) {
				result.setResCd(resOTP.get_return().getRsltCd());
				result.setErrMsg(resOTP.get_return().getRsltMsg());

				log.info("========== end AuthService.requestOtpAuth ==========");
				tokenService.doLogin(userInfoCacheRepository.removeUserInfo(loginId), res);
				userAccessService.addLog(req, OperationType.LOGIN, loginId);
				return new BaseResponse(BaseResponse.RESULT_CODE_SUCCESS, "로그인에 성공하였습니다.");
			} else {

				if (resOTP != null && resOTP.get_return() != null) {
					log.info("========== error AuthService.requestOtpAuth ==========");
					log.info("## Error : {}", resOTP.get_return().getRsltMsg());

					if ("7".equals(resOTP.get_return().getRsltCd())) {
						result.setResCd("7");
						result.setErrMsg(resOTP.get_return().getRsltMsg());						
						return new BaseResponse(BaseResponse.RESULT_CODE_EXCEPTION_CASE, "OTP 인증 기능을 사용할 수 없는 상태입니다.", result);
					} else if ("2".equals(resOTP.get_return().getRsltCd())) {
						result.setResCd("2");
						result.setErrMsg(resOTP.get_return().getRsltMsg());
						return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "OTP 인증에 실패하였습니다. 다시 시도해주세요.", result);
					} else if ("26".equals(resOTP.get_return().getRsltCd())) {
						result.setResCd("26");
						result.setErrMsg(resOTP.get_return().getRsltMsg());
						return new BaseResponse(BaseResponse.RESULT_CODE_EXCEPTION_CASE, "OTP 인증 유효시간이 초과되었습니다. OTP를 재발송 해주세요.", result);
					} else if ("36".equals(resOTP.get_return().getRsltCd())) {
						result.setResCd("36");
						result.setErrMsg(resOTP.get_return().getRsltMsg());
						return new BaseResponse(BaseResponse.RESULT_CODE_EXCEPTION_CASE, "OTP 실패횟수 기준 초과되었습니다. OTP를 재발송 해주세요.", result);
					} else {
						result.setResCd(resOTP.get_return().getRsltCd());
						result.setErrMsg(resOTP.get_return().getRsltMsg());
						return new BaseResponse(BaseResponse.RESULT_CODE_SERVER_ERROR, "OTP 인증 연동요청에 실패하였습니다.", result);						
					}

				} else {					
					log.info("========== error AuthService.requestOtpAuth ==========");
					log.info("## Error : {}", "null response");

					return new BaseResponse(BaseResponse.RESULT_CODE_SERVER_ERROR,
							resOTP != null && resOTP.get_return() != null ? resOTP.get_return().getRsltMsg()
									: "OTP 인증 요청에 실패했습니다.");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("========== error AuthService.requestOtpAuth ==========");
			log.info("## Exception : {}", e);

			if (e.getMessage().indexOf("data 0003") > 0) {
				return new BaseResponse(BaseResponse.RESULT_CODE_LDAP_FAIL, "유효하지 않은 서비스 계정입니다. 시스템관리자에게 문의해주세요.");
			} else if (e.getMessage().indexOf("data 0004") > 0) {
				return new BaseResponse(BaseResponse.RESULT_CODE_EXPIRED_INFO, "비밀번호가 만료되었습니다. 비밀번호를 변경해주세요.");
			} else if (e.getMessage().indexOf("data 0005") > 0) {
				return new BaseResponse(BaseResponse.RESULT_CODE_LDAP_FAIL, "유효하지 않은 사번/비밀번호 입니다.");
			}
		}

		log.info("========== error AuthService.requestOtpAuth ==========");
		return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "LDAP 및 OTP연동에 실패하였습니다.");
	}
	
	public BaseResponse isValidLoginInfo(String loginId, String loginPwd) {

		if (StringUtil.isNullOrEmpty(loginId)) {
			return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "ID를 입력해주세요.");
		} else if (StringUtil.isNullOrEmpty(loginPwd)) {
			return new BaseResponse(BaseResponse.RESULT_CODE_FAIL, "비밀번호를 입력해주세요.");
		}

		return new BaseResponse(BaseResponse.RESULT_CODE_SUCCESS);
	}
	
	public BaseResponse requestSkipOtp(RequestSigninVo requestSigninVo, HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		
		try { 
			tokenService.getUserInfo(req, res);
			
			String loginId, loginPwd;
			
			loginId = rsaService.decryption(requestSigninVo.getLoginId(), session);
			loginPwd = rsaService.decryption(requestSigninVo.getLoginPwd(), session);
			
			requestSigninVo.setLoginId(loginId);
			requestSigninVo.setLoginPwd(loginPwd);

			if(isSkip) {
							
				if(userInfoCacheRepository.removeUserInfo(loginId) == null) {
					
					ResponseSigninVo respon = new ResponseSigninVo();
					Map userInfoMap = new HashMap<>();
			
					// LDAP 사용자 정보 요청
					userInfoMap = ADUtilSSL.query_userinfo(host, port, baseDN, loginId, connId, connPwd, sslYn);

					// res VO 세팅
					respon = new ResponseSigninVo(userInfoMap);
					respon.setLoginId(loginId);
					respon.setIsSkip("true");
					
					userInfoCacheRepository.addUserInfo(loginId, respon);
				}
				
				tokenService.doLogin(userInfoCacheRepository.removeUserInfo(loginId), res);
				userAccessService.addLog(req, OperationType.LOGIN, loginId);								
				log.info("========== end AuthService.skipOtpAuth ==========");				
			
				return new BaseResponse(BaseResponse.RESULT_CODE_SKIP, "OTP SKIP 성공하였습니다.");
			}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	

}