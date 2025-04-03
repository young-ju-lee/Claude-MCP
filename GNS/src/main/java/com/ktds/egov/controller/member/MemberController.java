/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : MemberController.java
* Overview    : 회원가입 Controller
* Description : 회원가입, 회원관리, 로그인, 통계자료 화면으로 이동한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.controller.member;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.egov.common.util.FileManager;
import com.ktds.egov.service.member.StatService;
import com.ktds.egov.vo.member.MemberVo;
import com.ktds.egov.vo.support.DataRoomVo;
import com.ktds.egov.vo.member.MenuStateVo;
import com.ktds.egov.vo.member.StatVo;
import com.ktds.egov.vo.member.LoginVo;
//2018.07.20 암호화 salt 추가를 위한 base64 import
import com.ktds.egov.common.util.Base64Coder;

import com.ktds.framework.core.mvc.BaseService;
import com.ktds.framework.util.Global;
import com.ktds.framework.util.LampLogger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.captcha.botdetect.CodeStyle;
import com.captcha.botdetect.ImageStyle;
import com.captcha.botdetect.web.servlet.Captcha;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Controller
public class MemberController{
	protected final Logger logger = LogManager.getLogger(MemberController.class);
    @Resource(name = "memberService")
    private BaseService<MemberVo> memberService;    
    @Resource(name = "porloginService")
    private BaseService<MemberVo> loginService;  
    @Resource(name = "memberListService")
    private BaseService<MemberVo> memberListService;
	//메뉴접속통계 적용
    @Resource(name = "StatService")
    private StatService statService;
	//메뉴접속통계 적용
    @Resource(name = "LoginListService")
    private BaseService<LoginVo> loginlistService;
    //이전개인정보처리방침
    @Resource(name = "dataRoomService")
    private BaseService<DataRoomVo> dataRoomService;
    private static final String filePath = "/home/jboss/uploadFiles/"; //업로드 파일경로    
    private HttpSession session;
   	private Object httpRequest;	
   	
    @RequestMapping(value = "/member/login", method = { RequestMethod.POST })
    public ModelAndView getLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        session = request.getSession(true);
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        boolean isCaptchaSolved = (session.getAttribute("isCaptchaSolved") != null);
        if (!isCaptchaSolved) {
            Captcha captcha = Captcha.load(request, "loginCaptcha");
            captcha.setCodeStyle(CodeStyle.ALPHANUMERIC);
            if(request.getParameter("captchaCode")==null){
                      messages.put("captchaIncorrect", "Incorrect code");
            }else{
            	boolean isHuman = captcha.validate(request.getParameter("captchaCode"));
            	  if (!isHuman) {
                      messages.put("captchaIncorrect", "Incorrect code");
                  } else {
                      isCaptchaSolved = true;
                      session.setAttribute("isCaptchaSolved", true);
                  }
            }
        }
     // 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		
  		ModelAndView mav = new ModelAndView();
  		// 보여질 View 페이지를 설정한다.
  		mav.addObject("setKey", setKey(session, request));
  		mav.setViewName("/member/login");
  		return mav;
    }

  	//logOut 화면
  	@RequestMapping(value = "/member/logout", method = { RequestMethod.POST,
  			RequestMethod.GET })
  	public ModelAndView getLogout(HttpSession session,
  			HttpServletRequest request, HttpServletResponse response , String user_id,MemberVo memberVo) throws Exception {
  		// LAMP logger : Setter
  		LampLogger lampLogger = new LampLogger();
  		JSONObject lampFlagObj = new JSONObject();
  		String uuid = UUID.randomUUID().toString();
  		lampFlagObj.put("uuid", uuid);
  		lampFlagObj.put("ltStart", System.currentTimeMillis()); // 시작 시간
  		lampFlagObj.put("targetName", "LOGOUT"); // 실행 이벤트 이름
  		lampFlagObj.put("logType", "IN_REQ"); // 메인 Flag 값
  		lampFlagObj.put("reType", "I"); // 서브 Flag 값
  		lampFlagObj.put("userId", user_id); // 사용자 ID
  		lampLogger.makeLog(request, session, lampFlagObj);
  		
  		
  		//<!--2018.01.19 보안성 검토 로그저장 -->
  		String clientIp = Global.getClientIp(request);

		memberVo.setUser_id(user_id);
		memberVo.setIp(clientIp);
		memberVo.setSiteGubun("GNS_POR");		
		HashMap map = new HashMap();
		 map.put("mapperInfo", "insertLogout");
		loginService.insert(memberVo,map);
		//<!--2019.04.26 로그아웃 시에 중복 로그인 테이블 USER_ID삭제>
		HashMap map2 = new HashMap();
		map2.put("mapperInfo", "deleteLoginCheck");
		loginService.delete(memberVo,map2);
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
  		ModelAndView mav = new ModelAndView();
  		// LAMP logger
  		lampFlagObj.put("logType", "IN_RES");
  		lampLogger.makeLog(request, session, lampFlagObj);
  		session.invalidate();
  		// 보여질 View 페이지를 설정한다.
  		mav.addObject("setKey", setKey(session, request));
  		mav.setViewName("/member/login");

  		return mav;
  	}
  	
  //logOut 화면
  //<!--2019.04.26 중복로그인 동시접속 기존 남아있는 세션 로그아웃 처리>
  	@RequestMapping(value = "/member/logout2", method = { RequestMethod.POST,
  			RequestMethod.GET })
  	public ModelAndView getLogout2(HttpSession session,
  			HttpServletRequest request, HttpServletResponse response ) throws Exception {
  		// LAMP logger : Setter
  		LampLogger lampLogger = new LampLogger();
  		JSONObject lampFlagObj = new JSONObject();
  		String uuid = UUID.randomUUID().toString();
  		lampFlagObj.put("uuid", uuid);
  		lampFlagObj.put("ltStart", System.currentTimeMillis()); // 시작 시간
  		lampFlagObj.put("targetName", "LOGOUT"); // 실행 이벤트 이름
  		lampFlagObj.put("logType", "IN_REQ"); // 메인 Flag 값
  		lampFlagObj.put("reType", "I"); // 서브 Flag 값
  		lampFlagObj.put("userId", session.getAttribute("user_id")); // 사용자 ID
  		lampLogger.makeLog(request, session, lampFlagObj);
  		
  		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성  		
  		ModelAndView mav = new ModelAndView();
  		// LAMP logger
  		lampFlagObj.put("logType", "IN_RES");
  		lampLogger.makeLog(request, session, lampFlagObj);
  		
  		session.invalidate();
  		// 보여질 View 페이지를 설정한다.
  		mav.addObject("setKey", setKey(session, request));
  		mav.setViewName("/member/login");
  		
  		return mav;
  	}
  	
  	// login check 
  	@RequestMapping(value = "/member/login_check", method = { RequestMethod.POST,
  			/* RequestMethod.GET */ })		//20141219 이호남 - post방식만 받을 것.
  	public ModelAndView getLoginCheck(HttpSession session,
  			HttpServletRequest request, HttpServletResponse response,MemberVo memberVo) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
  		String result = "";
  		logger.info("[INFO] mailpass : " + request.getParameter("mailpass")); //메일인증 체크 확인 값
  		
  		// LAMP logger : Setter
  		LampLogger lampLogger = new LampLogger();
  		JSONObject lampFlagObj = new JSONObject();
  		String uuid = UUID.randomUUID().toString();
  		lampFlagObj.put("uuid", uuid);
  		lampFlagObj.put("ltStart", System.currentTimeMillis()); // 시작 시간
  		lampFlagObj.put("targetName", "LOGIN"); // 실행 이벤트 이름
  		lampFlagObj.put("userId", memberVo.getUser_id()); // 사용자 ID
  		lampFlagObj.put("logType", "IN_RES"); // 메인 Flag 값
  		lampFlagObj.put("reType", "I"); // 서브 Flag 값
  		
  		// LAMP logger : Login IN_REQ
  		lampLogger.makeLoginLog(request, memberVo, uuid);
  		
  		
  		// 캡챠 검증 부분 추가
  		logger.info("[INFO] captchapass_user_Id : " + request.getParameter("captchapass_user_Id"));
		logger.info("[INFO] captchapass : " + request.getParameter("captchapass"));
		
		boolean isHuman = false;
		if(request.getParameter("captchapass_user_Id").equals("admin1")||request.getParameter("captchapass").equals("Y")){
			isHuman = true;
		}else{
			Captcha captcha = Captcha.load(request, "loginCaptcha");		                       
            if(request.getParameter("captchaCode") == null){
            	isHuman = false;
            }else{
            	isHuman = captcha.validate(request.getParameter("captchaCode"));
	        }
		}
  		
  		String mflag = request.getParameter("mailpass");
  		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
  		ModelAndView mav = new ModelAndView();
  		
  		// Client RSA 복호화 부분 추가
  		PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
        session.removeAttribute("__rsaPrivateKey__"); // 키 재사용 방지
        if (privateKey == null) {
        	mav.addObject("setKey", setKey(session, request));
        	mav.setViewName("/member/login");
			// LAMP logger : 잘못된 로그인 시도
			lampFlagObj.put("reType", "E");
			lampFlagObj.put("reMsg", "잘못된 로그인 시도");
			lampLogger.makeLog(request, session, lampFlagObj);
			return mav;
        }
		try {
			String clientUserPw = decryptRsa(privateKey, memberVo.getUser_pw());
			memberVo.setUser_pw(clientUserPw);
		} catch (Exception e) {
	        // LAMP logger : Login IN_RES : 로그인 실패 : 시스템 에러 발생
  			lampFlagObj.put("reType", "S");
  			lampFlagObj.put("reMsg", "시스템 에러 발생");
  			lampLogger.makeLog(request, session, lampFlagObj);
			
			mav.addObject("redirect_url", "/error.do");
			mav.setViewName("/redirectPage");
		}
  		
  		HashMap<String,String> map = new HashMap<String,String>();
  		map.put("mapperInfo", "selectCheckUser"); //로그인 ID 존재유무 확인 
  		try{
  			List<MemberVo> user_checklist = memberService.select(memberVo, map);
  			result = String.valueOf(user_checklist.size()); 	
  	  		if(result.equals("1") && isHuman){ //ID가 존재하면
  	  			memberVo.setUser_id(user_checklist.get(0).getUser_id()); //VO에 해당 아이디 저장
  	  			memberVo.setLogin_fail_cnt(user_checklist.get(0).getLogin_fail_cnt()); //로그인실패건수 저장
  	  			memberVo.setLogin_fail_time(user_checklist.get(0).getLogin_fail_time()); //로그인 실패 시간 저장
  	  			String clientIp = Global.getClientIp(request);
				memberVo.setIp(clientIp); //IP를 VO에 저장
  	  			map.put("mapperInfo", "updateUserLoginState"); //POR_USER 테이블에 IP,로그인실패건수,로그인실패타임 업데이트 처리  	  			
  	  			if(memberVo.getLogin_fail_cnt() >= 5) { //로그인 실패 건수가 5건 이상이면 
  	  				if((new Date().getTime() - memberVo.getLogin_fail_time()) > 86400000) { //현재 시간에서 마지막 로그인 실패 시간이 하루가 지났다면 (86400=24*60*60)
  	  					memberVo.setLogin_fail_cnt(0); //실패건수 0으로 저장
  	  					memberVo.setLogin_fail_time(0); //실패 시간 0으로 저장
  	  					memberService.update(memberVo, map); //POR_USER테이블에 로그인실패건수, 실패시간 업데이트
  	  				} else { //하루가 지나지 않았다면
  	  					String hour = String.valueOf((86400000-(new Date().getTime()-memberVo.getLogin_fail_time()))/(1000*60*60));
  	  					String min = String.valueOf(((86400000-(new Date().getTime()-memberVo.getLogin_fail_time()))%(1000*60*60))/(1000*60));
  	  					mav.addObject("login_fail_cnt", memberVo.getLogin_fail_cnt());
  	  					mav.addObject("login_fail_time", hour + "시간 " + min + "분");
  	  					mav.addObject("flag", "E"); //flag E값을 주고 화면으로 리턴 화면에서 24시간 잠금 메세지 보여줌
  	  					mav.addObject("setKey", setKey(session, request));
  	  					mav.setViewName("/member/login"); 
  	  					// LAMP logger : Login IN_RES : 로그인 실패 : 계정 잠김
  	  					lampFlagObj.put("reType", "E");
  	  					lampFlagObj.put("reMsg", "계정 잠김");
  	  					lampLogger.makeLog(request, session, lampFlagObj);
  	  					return mav;
  	  				}
  	  			} else { //로그인 실패 건수가 5건 미만이라면
  	  				if((new Date().getTime() - memberVo.getLogin_fail_time()) > 86400000) { //현재 시간에서 로그인 실패 시간을 뺀 값이 하루보다 크다면
  	  					memberVo.setLogin_fail_cnt(0); //로그인실패 건수 0
  	  					memberVo.setLogin_fail_time(0); //로그인 실패 타임 0 으로 VO에 저장
  	  					memberService.update(memberVo, map); //POR_USER테이블에 로그인실패건수, 실패시간 업데이트
  	  				}
  	  			}
  	  		//2019.12.06 RSA 암복호화 추가
  	  		HashMap<String,String> rsaOpen = new HashMap<String,String>();
  	  	    rsaOpen.put("mapperInfo", "selectRsaOpenKey");
  	  	    HashMap<String,String> rsaSecret = new HashMap<String,String>();
  	  	    rsaSecret.put("mapperInfo", "selectRsaSecretKey");
  	  	    List<MemberVo> rsaOpenKey = memberService.select(memberVo,rsaOpen);
  	  	    String sPublicKey = rsaOpenKey.get(0).getrsaOpenKey(); //공개키 가져오기
  	        List<MemberVo> rsaSecretKey = memberService.select(memberVo,rsaSecret);
  	        String sPrivateKey = rsaSecretKey.get(0).getrsaSecretKey();//비공개키 가져오기  	        
  	        String rsa_user_pw = memberVo.getUser_pw();
  	        String rsa_user_pwEn=this.encrypt(rsa_user_pw,sPublicKey);  	        
  	        String rsa_user_pwde=this.decrypt(rsa_user_pwEn, sPrivateKey);
  	        memberVo.setUser_pw(rsa_user_pwde); //RSA 암복호화 한 pW를 저장
  	  		//2018.07.20 암호화 salt 추가 사용자 salt null 체크	
  	  		HashMap<String,String> map3 = new HashMap<String,String>();
  	  		map3.put("mapperInfo", "selectCheckSalt"); //POR_USER 테이블에서 user_id에 저장된 salt값+rsa_user_pw을 가지고옴  	  			
  	  		List<MemberVo> saltCheck = memberService.select(memberVo,map3);
  	  		String saltResult = String.valueOf(saltCheck.size()); //가지고 온 salt값이 몇개인지 확인 (user_id별 1개만 존재)  	  	
  	  		if(saltResult.equals("0")){ //salt값이 없다면 salt변경 적용 한 이후 처음으로 로그인 하는 회원인 경우
  	  		  List<MemberVo> list = memberService.select(memberVo); //selectMemberList 쿼리 실행 MemberListDAO에서 select 찾아보시면됨 (사용자 정보를 가지고옴)
  	  		  result = String.valueOf(list.size()); 	
  	  		   if(result.equals("1")) { //사용자가 있다면
  				session.invalidate(); //세션초기화
  				session = request.getSession();
  				session.setAttribute("user_id", list.get(0).getUser_id()); //ID저장
  				session.setAttribute("user_auth_id", list.get(0).getUser_auth_id()); //권한저장
  				session.setAttribute("ll_id", list.get(0).getLl_id()); //회선번호저장
  				session.setAttribute("user_nm", list.get(0).getUser_nm()); //이름 저장
  				session.setAttribute("CSRF_TOKEN", UUID.randomUUID().toString()); //ToKen값 신규 생성 
  				session.setAttribute("mflag", mflag);
  			   	memberVo.setLogin_fail_cnt(0);//실패건수 0
  				memberVo.setLogin_fail_time(0); //실패시간 0  	  				
  				memberService.update(memberVo, map); //POR_USER테이블에 로그인실패건수, 실패시간 업데이트
  				memberVo.setSiteGubun("GNS_POR");  //사이트 구분값 저장	  				
  				//<!--2018.01.19 보안성 검토 사용자 로그인 로그 수정 -->  	  				
  			    HashMap map2 = new HashMap();
  			    map2.put("mapperInfo", "insertLogin"); //POR_LOGIN //POR_LOGIN_INFO 테이블에 로그인 정보 INSERT
  				loginService.insert(memberVo,map2); 
  	  			//<!--2018.04.26 보안성 검토 중복로그인 insert -->  	  				
  	  			HashMap logincheck = new HashMap();
	  			logincheck.put("mapperInfo", "insertLoginCheck"); //중복로그인 체크를 위해 중복체크 테이블에 user_id, 현재시간, session_id저장
	  			String session_id=session.getId();	//session에 저장된 ID   			 
	  			memberVo.setSession_id(session_id); //VO저장
	  			loginService.insert(memberVo,logincheck); //쿼리실행
  	  			 if(list.get(0).getInit_yn() != null && list.get(0).getInit_yn().equals("Y")){ //비번 3달체크 개발 이후 처음 로그인 하신다면
  	  			   mav.addObject("redirect_url", "/member/idpw_change.do"); //비번변경페이지로 이동
  	  			   mav.setViewName("/redirectPage");
			       // LAMP logger : Login IN_RES : 로그인 성공 : 비밀번호 변경 페이지로 이동
			       lampLogger.makeLog(request, session, lampFlagObj);
  	  			   return mav;
  	  			  }
  	  				mav.addObject("MemberVo", memberVo); 
  	  				// 보여질 View 페이지를 설정한다.  	  				
  	  				mav.addObject("redirect_url", "/main.do");  	  				
  	  				mav.setViewName("/redirectPage");
  	  			}else{ //사용자가 없다면
  	  				memberVo.setLogin_fail_cnt(memberVo.getLogin_fail_cnt()+1); //실패건수+1
  	  				memberVo.setLogin_fail_time(new Date().getTime()); //현재시간 저장
  	  				loginService.update(memberVo, map);  	  //유저정보 업데이트			
	  	  			//<!--2018.01.19 보안성 검토 로그저장 -->  	  	  		
	  	  			memberVo.setSiteGubun("GNS_POR"); //VO에 사이트 구분값 저장
  	  				HashMap<String,String> map2= new HashMap<String,String>();
  	  			    map2.put("mapperInfo", "insertLogfail"); //로그인실패 정보 로그테이블에 저장
				    loginService.insert(memberVo, map2);
  	  				mav.addObject("flag", "E"); //화면에 flag E저장
  	  				mav.addObject("login_fail_cnt", memberVo.getLogin_fail_cnt()); //실패건수 
  	  				mav.addObject("login_fail_time", "24시간"); //잠금시간
  	  				mav.addObject("setKey", setKey(session, request));
  	  				mav.setViewName("/member/login");//LOGIN화면으로 리턴
  	  				// LAMP logger : Login IN_RES : 로그인 실패 flag
  	  				lampFlagObj.put("reType", "E");
					lampFlagObj.put("reMsg", "잘못된 아이디 또는 패스워드 입력");
  	  			}
  	  			// 2018 07.20 salt 암호화 변경 salt 문자 base64 인코딩 추가
  	  		    String user_pw2 = memberVo.getUser_pw(); //사용자가 입력한 비번 값 가지고 오기
  	  			String salt = this.getSalt();  //salt생성
  	  		    String user_pw = user_pw2+salt; //가지고 온 비번+salt로 비번 재저장  	  		    
  	  		    memberVo.setUser_pw(user_pw);
  	  		    String saltEn = Base64Coder.encodeString(salt); //salt base64인코딩 변경 암호화
  	  		    memberVo.setSalt(saltEn);
  	  		    HashMap<String,String> map4= new HashMap<String,String>();
			    map4.put("mapperInfo", "updateSaltPw");
			    memberService.update(memberVo, map4); //salt및 new패스워드 업데이트 처리  	  		   
			  //<!-- 2017.12.22  보안성검토 비번 3개월 기간 체크 후 화면전환 추가 -->
	  			//비밀번호 변경일을 가지고 온다	  			   
	  			//3달전 날짜와 비교하기 위하여 숫자타입으로 변경한다.
	  			  int datenum = Integer.parseInt(list.get(0).getPw_updt_date());
	  				//현재 시스템의 날짜에서 3달전 데이터를 가지고 온다.
	  			  Calendar cal = Calendar.getInstance();
	  			  cal.add(cal.MONTH, -3);
	  			  Date checkDate = cal.getTime();
	  			  SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd");
	  			  String checkDate2 = format1.format(checkDate);	  			 
	  			  int datenum2 = Integer.parseInt(checkDate2);	  			  
	  			  if (datenum2 > datenum){ //3달이 지났다면 비번 변경페이지 이동
	  				mav.addObject("redirect_url", "/member/idpw_change2.do");
  					mav.setViewName("/redirectPage");
  					// LAMP logger : Login IN_RES : 로그인 성공 : 비밀번호 변경 페이지로 이동
  					lampLogger.makeLog(request, session, lampFlagObj);
  					return mav;
	  			  } 	  			  
	  			  //2019.11.15 쿠키값이 없다면 메일인증 페이지로 넘어가도록 개발 (admin1계정예외처리)	  			  
	  			  if(!mflag.equals("Y") && !memberVo.getUser_id().equals("admin1")){
	  				mav.addObject("redirect_url", "/member/mail_approval.do");
  					mav.setViewName("/redirectPage");
  					// LAMP logger : Login IN_RES : 로그인 성공 : 메일인증 페이지로 이동
  					lampLogger.makeLog(request, session, lampFlagObj);
  					return mav;
	  			  }
			    
  	  			}
  	  	else{ //salt값이 있는 경우
  	  	       String salt2 = saltCheck.get(0).getSalt();
  	  		   String saltDe = Base64Coder.decodeString(salt2); //salt base64인코딩 변경 복호화  	  		  
	  		   String user_pw2 = memberVo.getUser_pw(); 
	  		   String user_pw = user_pw2+saltDe; //가지고 온 비번+salt로 비번 재저장
	  		   memberVo.setUser_pw(user_pw);
	  		   List<MemberVo> list = memberService.select(memberVo); //재저장된 PW로 사용자 조회
	  		   result = String.valueOf(list.size());
	  		if(result.equals("1")) {	//있다면  	
	  			//2019-04-26 사용자 중복로그인체크	  			 	  				
	  			  HashMap logincheck = new HashMap();
	  			  logincheck.put("mapperInfo", "insertLoginCheck");//중복로그인 테이블에 정보 저장
	  			  String session_id=session.getId();	  			 
	  			  memberVo.setSession_id(session_id);
  				  loginService.insert(memberVo,logincheck);
  				  HashMap<String,String> selectLoginCheck = new HashMap<String,String>();
  				  selectLoginCheck.put("mapperInfo", "selectLoginCheck");	//중복로그인 테이블에 30분내로 같은 사용자로 로그인한 내역이 있는지 확인  				
				  List<MemberVo> selectLoginCheck2 = memberListService.select(memberVo, selectLoginCheck);
				if(selectLoginCheck2.size() > 1){ //1명을 초과했다면 				      
			       ModelAndView mav2 = new ModelAndView();
				   logger.info(" #### user_id : " + memberVo.getUser_id()  );
				   mav2.addObject("user_id", memberVo.getUser_id());
			       mav2.setViewName("/member/login_check");	//중복로그인 확인 페이지로 이동
			       // LAMP logger : Login IN_RES : 로그인 성공 : 중복 로그인 확인 페이지로 이동
			       lampLogger.makeLog(request, session, lampFlagObj);
				   return mav2;
				  }
	  				session.invalidate();
	  				session = request.getSession();	  				
	  				session.setAttribute("user_id", list.get(0).getUser_id());
	  				session.setAttribute("user_auth_id", list.get(0).getUser_auth_id());
	  				session.setAttribute("ll_id", list.get(0).getLl_id());
	  				session.setAttribute("user_nm", list.get(0).getUser_nm());
	  				session.setAttribute("CSRF_TOKEN", UUID.randomUUID().toString());
	  				session.setAttribute("mflag", mflag);
	  				memberVo.setLogin_fail_cnt(0);
	  				memberVo.setLogin_fail_time(0);
	  				memberService.update(memberVo, map);
	  				memberVo.setSiteGubun("GNS_POR");
	  				//<!--2018.01.19 보안성 검토 사용자 로그인 로그 수정 -->  	  				
	  			    HashMap map2 = new HashMap();
	  			    map2.put("mapperInfo", "insertLogin");
	  				loginService.insert(memberVo,map2);
	  				if(list.get(0).getInit_yn() != null && list.get(0).getInit_yn().equals("Y")){ //salt로 비밀번호 재저장을 하기 위해서 비밀번호 변경페이지 이동
	  					mav.addObject("redirect_url", "/member/idpw_change.do");
	  					mav.setViewName("/redirectPage");
	  					// LAMP logger : Login IN_RES : 로그인 성공 : 중복 로그인
	  					lampLogger.makeLog(request, session, lampFlagObj);
	  					return mav;
	  				}
	  			//<!-- 2017.12.22  보안성검토 비번 3개월 기간 체크 후 화면전환 추가 -->
	  			  int datenum = Integer.parseInt(list.get(0).getPw_updt_date()); 	//비밀번호 변경일을 가지고 온다
	  			  Calendar cal = Calendar.getInstance(); //현재 날짜를 가지오 온다
	  			  cal.add(cal.MONTH, -3); //위에서 가지고 온 날짜의 3달전 데이터를 가지고 온다.	
	  			  Date checkDate = cal.getTime();
	  		      SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd");
	  			  String checkDate2 = format1.format(checkDate);
	  			  int datenum2 = Integer.parseInt(checkDate2); //3달전 날짜와 비교하기 위하여 숫자타입으로 변경한다.	  			  
	  			  if (datenum2 > datenum){ //비밀번호 변경일이 3달보다 크다면 비밀번호 변경페이지로 이동한다
	  				mav.addObject("redirect_url", "/member/idpw_change2.do");
	  					mav.setViewName("/redirectPage");
	  					// LAMP logger : Login IN_RES : 로그인 성공 : 비밀번호 변경 페이지로 이동
	  					lampLogger.makeLog(request, session, lampFlagObj);
	  					return mav;
	  			  } 	
	  			  //2019.11.15 쿠키값이 없다면 메일인증 페이지로 넘어가도록 개발
	  			 if(!mflag.equals("Y") && !memberVo.getUser_id().equals("admin1")){
	  				mav.addObject("redirect_url", "/member/mail_approval.do");
  					mav.setViewName("/redirectPage");
  					// LAMP logger : Login IN_RES : 로그인 성공 : 메일 인증 페이지로 이동
  					lampLogger.makeLog(request, session, lampFlagObj);
  					return mav;
	  			  }
	  				mav.addObject("MemberVo", memberVo); 
	  				// 보여질 View 페이지를 설정한다.
	  				mav.addObject("redirect_url", "/main.do");
	  				mav.setViewName("/redirectPage");
	  		}else{//사용자가 없다면 
	  				memberVo.setLogin_fail_cnt(memberVo.getLogin_fail_cnt()+1); //로그인실패건수 +1
	  				memberVo.setLogin_fail_time(new Date().getTime()); //실패시간 저장
	  				loginService.update(memberVo, map);	  			//POR_USER 테이블에 해당유저 실패건수 실패시간 업데이트
		  			//<!--2018.01.19 보안성 검토 로그저장 -->	  	  		
		  			memberVo.setSiteGubun("GNS_POR");
	  				HashMap<String,String> map2= new HashMap<String,String>();
	  			    map2.put("mapperInfo", "insertLogfail");
				    loginService.insert(memberVo, map2);
	  				mav.addObject("flag", "E");
	  				mav.addObject("login_fail_cnt", memberVo.getLogin_fail_cnt());
	  				mav.addObject("login_fail_time", "24시간");
	  				mav.addObject("setKey", setKey(session, request));
	  				mav.setViewName("/member/login");
	  				// LAMP logger : Login IN_RES : 로그인 실패 flag
	  	    		lampFlagObj.put("reType", "E");
	  	    		lampFlagObj.put("reMsg", "잘못된 아이디 또는 패스워드 입력");
	  		}
  	  	  }
  	  		} else {
  	  		// 2017.12.22  보안성검토 존재하지 않는 아이디 일 때 flag,login_fail_cnt 값 수정
  	  			mav.addObject("flag", "N");
  	  			mav.addObject("login_fail_cnt", 1);
  	  			mav.addObject("setKey", setKey(session, request));
  	  			mav.setViewName("/member/login");
  	  			// LAMP logger : Login IN_RES : 로그인 실패 flag
  	    		lampFlagObj.put("reType", "E");
  	    		lampFlagObj.put("reMsg", "잘못된 아이디 또는 패스워드 입력");
  	  		}
  			
  		}catch(Exception e){
  		//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
	        // LAMP logger : Login IN_RES : 로그인 실패 : 시스템 에러 발생
  			lampFlagObj.put("reType", "S");
  			lampFlagObj.put("reMsg", "시스템 에러 발생");
  			lampLogger.makeLog(request, session, lampFlagObj);
  		
			mav.addObject("redirect_url", "/error.do"); 
			mav.setViewName("/redirectPage");			
  		}
  		// LAMP logger : flag
  		lampLogger.makeLog(request, session, lampFlagObj);
  		
  		return mav;
  	 }
  	
 // 개인정보처리방침
 	@RequestMapping(value = "/member/personal", method = { RequestMethod.POST })
 	public ModelAndView getPersonal(HttpSession session,
 			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
 		logger.info("[INFO] personal");
 		
 		//메뉴접속통계 적용
 		String menuId = "505010";
  		
  		MenuStateVo menuVo = new MenuStateVo();
  		menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"),""));
  		menuVo.setMenu_id(menuId);		
  		try {
			this.statService.insertMenuState(menuVo);
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
  		
 		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
 		ModelAndView mav = new ModelAndView();
 		mav.setViewName("/member/personal");
 		
 		return mav;
 	}
 	
	// 회원 LIST
 	@RequestMapping(value = "/member/member_list", method = { RequestMethod.POST,
 			RequestMethod.GET })
 	public ModelAndView getNoticeList(HttpSession session,
 			HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
 		logger.info("회원리스트 호출 /member/member_list");
 		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
 		ModelAndView mav = new ModelAndView();
 		//세션 권한 확인
 		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 	
 			mav.addObject("redirect_url", "/member/login.do");
 			mav.addObject("setKey", setKey(session, request));
 			mav.setViewName("/redirectPage");	
 			return mav;
 		}
 		//20180928 모의해킹 URL 강제접속 수정
 	   if(!session.getAttribute("user_auth_id").equals("40") )
 		{ 	
 					mav.addObject("redirect_url", "/error.do");
 		 			mav.setViewName("/redirectPage");	
 		 			return mav;
 		 }
 		String searchGroup = ""; //검색 조건
  		String searchValue = ""; //검색어
  		
  		//페이징 초기화
  		memberVo.setLimit(10); //출력 행의 갯수		
  		memberVo.setOffSet(0); //출력 첫행 위치
         
  		//검색 하는 경우
  		if(memberVo.getSearch_yn().equals("Y")){ 
  			String clientIp = Global.getClientIp(request);
			memberVo.setIp(clientIp);	
			//<!-- 2018.03.23 보안성검토 로그 센터 연동 추가 -->  
			memberVo.setUser_id1((String) session.getAttribute("user_id"));
			memberVo.setUser_id2(memberVo.getUser_id1());
			memberVo.setUser_id3(memberVo.getUser_id2());			
			memberVo.setMenu_name("회원관리");
  			//검색 버튼 클릭시		 			
  			logger.info(" ### 검색 버튼 클릭시	 ");
  			searchGroup = memberVo.getSearch_group();
  			searchValue = memberVo.getSearch_value();
  			memberVo.setP_search_group(searchGroup);
  			memberVo.setP_search_value(searchValue); //검색 조건을 계속 저장 관리
  			 			
  			//페이징 초기화
  			memberVo.setPage("1"); //페이징 순번
  		}else{ 			
  			if(memberVo.getPage().equals("1")){
  				//최초로 목록 화면 들어가는 경우
  				logger.info(" ### 최초로 목록 화면 들어가는 경우 ");
  				
  				//검색 조건 초기화, 페이징 초기화 								 	 			
  				memberVo.setOffSet(0);
  			}
  			else{
  				//페이징으로 화면 들어가는 경우
  				logger.info(" ### 페이징으로 화면 들어가는 경우 ");
  				
  				int page = Integer.parseInt(memberVo.getPage());
  				int offset = 0;
  				if(page == 1){
  					offset = 0;
  				}else
  				{
  					offset = page * 10 - 10;
  				}			
  				memberVo.setOffSet(offset); 				
  			} 			
  		}
 		
  		try{
  			List<MemberVo> list = this.memberListService.select(memberVo);
  			 			
  			
  			
  	 		long row_cnt = 0;
  	 		if(list.size() != 0) {
  	 			row_cnt = list.get(0).getRow_cnt();
  	 		}
  	 		for(int i=0; i < list.size(); i++) {
  	 			//2018.02.23 계정잠금해제 10->5회로 수정
  	 			if(list.get(i).getLogin_fail_cnt() >= 5 && ((new Date().getTime() - list.get(i).getLogin_fail_time()) < 86400000)) {
  	 				list.get(i).setUnlock_yn("Y");
  	 			}
  	 		}
  	 	 //<!-- 2018.03.23 보안성검토 로그 센터 연동 추가 -->  
  	 		if(memberVo.getSearch_yn().equals("Y")&&row_cnt == 1){
  	 			memberVo.setMulti_log_type("단건");
  				memberVo.setMulti_log_param(searchValue);
  				memberVo.setUser_id(list.get(0).getUser_id());  				
  	 			try {
  					this.memberListService.insert(memberVo);
  				} catch (Exception e) {
  				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
  					System.out.println("시스템 에러 발생");
  				}
  	 		}else if(memberVo.getSearch_yn().equals("Y")&&row_cnt > 1){
  	 			memberVo.setMulti_log_type("다건");	
  	 			memberVo.setMulti_log_param(searchValue);
  	 			memberVo.setLimit(1000); //출력제한 변경
  	 			List<MemberVo> list2 = this.memberListService.select(memberVo); //리미트 제한 1000개로  다시 가져옴
  	 			for(int i=0; i < list2.size(); i++) {
  	 				memberVo.setUser_id(list2.get(i).getUser_id());
  	 				try {
  	  					this.memberListService.insert(memberVo);
  	  				} catch (Exception e) {
  	  				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
  	  				System.out.println("시스템 에러 발생");
  	  				}
  	 			}
  	 			memberVo.setLimit(10); //로그 저장 완료 후 출력 갯수 복구
  	 		} 	 		

  	 		logger.info( " list.get(0).getRow_cnt() : " + row_cnt);
  	 		logger.info( " memberVo.getLimit : " + memberVo.getLimit());
  	 		  
  	 		mav.addObject("member_list", list);
  	 		mav.addObject("memberVo", memberVo);
  			
  		}catch(Exception e){
  		//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
  		}
 		
 		// 보여질 View 페이지를 설정한다.
 		mav.setViewName("/member/member_list");		
 		return mav;		
 	}
 	
	// 접속자통계 LIST
	@RequestMapping(value = "/member/login_list", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getStatList(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, LoginVo loginVo) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		logger.info("접속자통계리스트 호출 /member/login_list");
        
		logger.info(" ### search_type " + loginVo.getSearch_type());
		
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		
		//세션 권한 확인 - 20161202 모의해킹 취약점 - 장혁준
		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){
			mav.addObject("redirect_url", "/member/login.do");
			mav.addObject("setKey", setKey(session, request));
			mav.setViewName("/redirectPage");	
			return mav;
		}

		String searchType = "";
		//String searchId  = loginVo.getSearch_id();
		
		if(loginVo.getSearch_type() == null || loginVo.getSearch_type().equals("")){
			searchType = "Y";	
			loginVo.setSearch_type("Y");
		}else{
			searchType = loginVo.getSearch_type();
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		logger.info(" ### search_type " + searchType );
		
				
		if(searchType.equals("Y")) {
			map.put("mapperInfo", "SelectYearList");
		} else if(searchType.equals("M")) {
			map.put("mapperInfo", "SelectMonthList");
		} else if(searchType.equals("D")) {
			map.put("mapperInfo", "SelectDayList");
		} else if(searchType.equals("H")) {
			map.put("mapperInfo", "SelectHourList");
		}
		
		logger.info(" ### mapperInfo " + map.get("mapperInfo") );
		
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String today = sd.format(new Date());
				
		if(loginVo.getSearch_from() == null || loginVo.getSearch_from().equals("")){
			loginVo.setSearch_from(today);		
		}		
		if(loginVo.getSearch_to() == null || loginVo.getSearch_to().equals("")){
			loginVo.setSearch_to(today);
		}
		logger.info(" today : " + today);
		
		mav.addObject("loginVo", loginVo);
		
		try{
			List<LoginVo> list = this.loginlistService.select(loginVo, map);
			logger.info("접속자통계리스트 호출 size "+ list.size());
			mav.addObject("login_list", list);
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		  

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/member/login_list");		
		return mav;		
	}
	
	// 메뉴통계 LIST
		@RequestMapping(value = "/member/stat_list", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getStatList(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, MenuStateVo menuVo) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
			
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();
			
			//세션 권한 확인 - 20161202 모의해킹 취약점 - 장혁준
			if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){
				mav.addObject("redirect_url", "/member/login.do");
				mav.addObject("setKey", setKey(session, request));
				mav.setViewName("/redirectPage");	
				return mav;
			}
			logger.info("메뉴접속통계리스트 호출 /member/stat_list : " + menuVo.getSearch_id() + " - " + menuVo.getSearch_type());
	        
			
			
			if(menuVo.getSearch_type() == null || menuVo.getSearch_type().equals("")){	
				menuVo.setSearch_type("A");		
			} 
			
			if(menuVo.getSearch_id() == null || menuVo.getSearch_id().equals("")){	
				menuVo.setSearch_id("");		
			} 
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
			String today = sd.format(new Date());
					
			if(menuVo.getSearch_from() == null || menuVo.getSearch_from().equals("")){
				menuVo.setSearch_from(today);	 	
			}		
			if(menuVo.getSearch_to() == null || menuVo.getSearch_to().equals("")){
				menuVo.setSearch_to(today);
			}
			logger.info(" today : " + today);
			
			logger.info(" $$$ " + menuVo.getSearch_id() + "-" + menuVo.getSearch_type() + "-" + menuVo.getSearch_from() + ":" + menuVo.getSearch_to());
	         
			try {
				List<StatVo> list = this.statService.selectList(menuVo);
				logger.info("메뉴접속통계리스트 호출 size "+ list.size());
				mav.addObject("stat_list", list);
				mav.addObject("menustateVo", menuVo);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/member/stat_list");		
			return mav;		
		}
    
		//이용약관/개인정보활용 동의 화면
		@RequestMapping(value = "/member/clause", method = { RequestMethod.POST,
				/*RequestMethod.GET*/ }) //POST 방식만 받도록 선언. 2016-11-02 장혁준
		public ModelAndView getClause(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();
			
			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/member/clause");
			
			return mav;
		}
		
		//<!-- 2018.01.19 보안성검토 14세 미만 확인-->
		@RequestMapping(value = "/member/check_member", method = { RequestMethod.POST,
				}) 
		public ModelAndView getCheck_member(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();
			
			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/member/check_member");
			
			return mav;
		}
		
		
		
		
	//회원가입 화면
	@RequestMapping(value = "/member/join", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getJoin(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, String check1, String check2, String check3) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
	
		//<!-- 2017.12.22  보안성검토 선택동의 추가 -->
		MemberVo memberVo = new MemberVo(); 
		memberVo.setUcheck1(check1);
		memberVo.setUcheck2(check2);
		memberVo.setUcheck3(check3);
		
		
		
		
		// 보여질 View 페이지를 설정한다.
		mav.addObject( "ucheck1",memberVo.getUcheck1());
		mav.addObject( "ucheck2",memberVo.getUcheck2());
		mav.addObject( "ucheck3",memberVo.getUcheck3());
		
		mav.addObject("setKey", setKey(session, request));
		mav.setViewName("/member/join");
		
		return mav;
	}
	
	// user id check
	@RequestMapping(value = "/member/user_id_check", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ResponseEntity<String> getUserIdCheck(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo memberVo ) throws RuntimeException {

		JSONObject jobj = new JSONObject();
		
		try{
			List<MemberVo> list = loginService.select(memberVo);
			
			if(list.size() != 0) {
				
				for(int i=0; i<list.size();i++){
					jobj.put("user_id", list.get(i).getUser_id());
				}	
			}
			else {
				jobj.put("user_id", "");
			}

			
		}catch(Exception e ){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		// 한글 전송을 위한 셋팅 
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
        return new ResponseEntity<String>(jobj.toString(), responseHeaders, HttpStatus.CREATED);
	}
	// captcha_check
	@RequestMapping(value = "/member/captcha_reload", method = { RequestMethod.POST,
		/*RequestMethod.GET */ })		
	public ResponseEntity<String> captchaReload(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException {
					
		JSONObject obj = new JSONObject();
            Captcha captcha = Captcha.load(request, "loginCaptcha");
            captcha.setCodeStyle(CodeStyle.ALPHANUMERIC);
            session.setAttribute("isCaptchaSolved", true);
            obj.put("result", captcha);
       
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
	
	}	
	/*// captcha_check
		@RequestMapping(value = "/member/captcha_check", method = { RequestMethod.POST,
				/RequestMethod.GET/  })		//20141219 이호남 - post방식만 받을 것.
		public ResponseEntity<String> captchaCheck(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException {						
			JSONObject obj = new JSONObject();
			 //2019.05.24 captcha 예외처리 긴급배포 (admin1인경우 예외처리 추가)
			logger.info("[INFO] captchapass_user_Id : " + request.getParameter("captchapass_user_Id"));
			logger.info("[INFO] captchapass : " + request.getParameter("captchapass"));
			if(request.getParameter("captchapass_user_Id").equals("admin1")||request.getParameter("captchapass").equals("Y")){
				obj.put("result", "captcha_ok");
			}else{
				Captcha captcha = Captcha.load(request, "loginCaptcha");		                       
		            if(request.getParameter("captchaCode")==null){
		            	obj.put("result", "captcha_error");
		            }else{
		            	boolean isHuman = captcha.validate(request.getParameter("captchaCode"));
		            	  if (!isHuman) {
		            		  obj.put("result", "captcha_error");
		                  } else {		                   
		                      obj.put("result", "captcha_ok");
		                  }
		        }
			}
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);		
		}*/
		
	//2019.11.15 쿠키 체크 로직 신규추가
	@RequestMapping(value = "/member/coki_check", method = { RequestMethod.POST,
			/*RequestMethod.GET */ })		
	public ResponseEntity<String> coki_check(HttpSession session,
	HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws Exception {					
		JSONObject obj = new JSONObject();				
		logger.info("[INFO] cokiid : " + request.getParameter("cokiid")); //사용자 PC에 저장된 쿠키ID
		logger.info("[INFO] cokisalt : " + request.getParameter("cokisalt"));//사용자 PC에 저장된 쿠키salt값
		String coki_salt = request.getParameter("cokisalt");
		String user_id =request.getParameter("cokiid");					
		String cokiKey = user_id+coki_salt; //쿠키 salt+user_id  
  		try {
			String cokiKeyEn = encryptSHA256(cokiKey); //쿠키키 값을 sha256암호화
			memberVo.setCokiKeyEn(cokiKeyEn);
			memberVo.setUser_id(user_id);
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("mapperInfo", "select_cokiyn"); //쿠키키 암호화한 값이 있으면 성공
			List<MemberVo> list  = memberService.select(memberVo, map);		
			if(list.size() > 0){
				 obj.put("result", "coki_ok"); //데이터가 있으면 쿠키있음 captcha예외처리
			}else{
				obj.put("result", "coki_fail");//데이터가 없으면 데이터 변조
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("시스템에러발생");
		}		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);	
	}
	//2019.11.15 메일 발송
	@RequestMapping(value = "/member/mail_send", method = { RequestMethod.POST,
	 })		
	public ResponseEntity<String> mailsend(HttpSession session,
	HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException {		
		ModelAndView mav = new ModelAndView();     	
		
		String clientIp = Global.getClientIp(request);
		memberVo.setIp(clientIp);	
	    JSONObject obj = new JSONObject();
		//20210617 모의해킹 취약점 - 이준영
	    /*DR-2021-33021 '21년 GNS 모의해킹 취약점 개선 */
		String lid = memberVo.getUser_id();
		if(!lid.equals(session.getAttribute("user_id"))) {
			obj.put("result", "mail_error");	
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
		}
	    
		HashMap<String,String> map8 = new HashMap<String,String>();
		map8.put("mapperInfo", "select_mailyn"); //사용자 메일 잠금여부 확인
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mapperInfo", "update_mail_code");
		try {
			List<MemberVo> list8  = memberService.select(memberVo, map8);
			String mail_yn = list8.get(0).getMail_yn();
			if(mail_yn.equals("Y")){ //mail 잠금 여부 확인
				obj.put("result", "mail_closed");
			}else{ //잠금이 아니라면 메일인증코드 발급 후 업데이트
				String mail_code = this.getMailCode();
				   memberVo.setMail_code(mail_code);
				Integer list = memberService.update(memberVo, map);
				if(list==0){
	            	obj.put("result", "mail_error");	           
	            } else {	
	                	HashMap<String,String> map3 = new HashMap<String,String>();
	              		map3.put("mapperInfo", "selectMailCheck");
	              		HashMap<String,String> map2 = new HashMap<String,String>();
	              		map2.put("mapperInfo", "selectMailInfo");
	              		HashMap<String,String> map5 = new HashMap<String,String>();
	              		map5.put("mapperInfo", "sendMailInfo"); //같은 IP로 1분에 10회 체크로직
	            		List<MemberVo> list3  = memberService.select(memberVo, map3);
	    				List<MemberVo> list2  = memberService.select(memberVo, map2);
	    				List<MemberVo> list5  = memberService.select(memberVo, map5);
	    				if(list5.size()>10){
	    					obj.put("result", "mail_delay");
	    				}else{
	    					String host = list2.get(0).getHost();
							String port = list2.get(0).getPort();
							String admin_mail = list2.get(0).getAdmin_mail();
							String subject = list2.get(0).getSubject();
							String content = list2.get(0).getContent();
							String content2 = content.replaceAll("######", memberVo.getMail_code()); //메일인증코드로 변경
							String user_mail = list3.get(0).getUser_mail();
							String user_id = list3.get(0).getUser_id();
							String user_nm = list3.get(0).getUser_nm();								
							Properties p = System.getProperties();					
							p.put("mail.smtp.host", host);
							p.put("mail.smtp.port", port);
							Session session2 = Session.getInstance(p, null);		 		     
							Message msg = new MimeMessage(session2); 
							msg.setSentDate(new Date());
							//발신자
							InternetAddress mail_from = new InternetAddress(admin_mail);
							msg.setFrom(mail_from);
							//수신자
							InternetAddress mail_to = new InternetAddress(user_mail);
							msg.setRecipient(Message.RecipientType.TO, mail_to);
							//제목
							msg.setSubject(subject);
							//해더
							msg.setHeader("content-Type", "text/html");
                             
							MimeBodyPart body = new MimeBodyPart();
							body.setContent(content2,"text/html;charset=EUC-KR");
							Multipart mt = new MimeMultipart();
							mt.addBodyPart(body);
							memberVo.setUser_id(user_id);
							memberVo.setUser_mail(user_mail);
							memberVo.setUser_nm(user_nm);
							session.setAttribute("user_mail", user_mail);
							try{
								//메시지 발신
								msg.setContent(mt);									
								javax.mail.Transport.send(msg); //메일전송 운영배포시 주석제거								
								HashMap<String,String> map4 = new HashMap<String,String>();						
								memberVo.setMail_flag("Y");
								map4.put("mapperInfo", "InsertMailresult");
								memberService.insert(memberVo, map4);
			                      obj.put("result", "mail_ok");
			                      obj.put("result2", user_mail); //사용자 이메일 정보 저장	
			                    
							}catch(Exception e){
								System.out.println("메일전송실패");		
								HashMap<String,String> map4 = new HashMap<String,String>();						
								memberVo.setMail_flag("N");
								map4.put("mapperInfo", "InsertMailresult");
								memberService.insert(memberVo, map4);
			                      obj.put("result", "mail_error");
			                      obj.put("result2", memberVo.getUser_mail());
							}
	    				}
				}
		     }
			} catch (Exception e) {
				System.out.println("시스템 에러 발생");
			}
			HttpHeaders responseHeaders = new HttpHeaders();
		   responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		   return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
		
		}
		
		
		@RequestMapping(value = "/member/mailcode_check", method = { RequestMethod.POST,
		 })		
		public ResponseEntity<String> mailcodeCheck(HttpSession session,
		HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException {		
  		// LAMP logger : Setter
  		LampLogger lampLogger = new LampLogger();
  		JSONObject lampFlagObj = new JSONObject();
  		String uuid = UUID.randomUUID().toString();
  		lampFlagObj.put("uuid", uuid);
  		lampFlagObj.put("ltStart", System.currentTimeMillis()); // 시작 시간
  		lampFlagObj.put("targetName", "EMAIL"); // 실행 이벤트 이름
  		lampFlagObj.put("logType", "IN_REQ"); // 메인 Flag 값
  		lampFlagObj.put("reType", "I"); // 서브 Flag 값
  		lampFlagObj.put("userEmail", session.getAttribute("user_mail")); // 사용자 Email
  		lampLogger.makeLog(request, session, lampFlagObj);
  		lampFlagObj.put("logType", "IN_RES");
		
		JSONObject obj = new JSONObject();		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mapperInfo", "selectMailCodeCheck");		
			try {
				List<MemberVo> list  = memberService.select(memberVo, map);				
				if(list.size() > 0){
					String user_id = memberVo.getUser_id(); //사용자 ID		  	  		    
			  	  	String coki_salt = this.getSalt();  //쿠키salt생성
			  	  	String cokiKey = user_id+coki_salt; //쿠키 salt+user_id  
			  	  	String cokiKeyEn = encryptSHA256(cokiKey);		  	  			  	  		 
			  	  	String coki_saltEn = Base64Coder.encodeString(coki_salt); //coki_salt base64인코딩 변경 암호화	
			  	  	String userAgent = (String) request.getHeader("User-Agent");		  	  	
			  	  	memberVo.setUserAgent(userAgent);
		  	  		memberVo.setCoki_salt(coki_salt);
		  	  		memberVo.setCokiKey(cokiKey);
		  	  		memberVo.setCokiKeyEn(cokiKeyEn);
		  	  	    memberVo.setCoki_saltEn(coki_saltEn);		  	  	
		  	  	    HashMap map2 = new HashMap();
				    map2.put("mapperInfo", "insertcoki");
				    memberService.insert(memberVo,map2);			    
					String serverName = ".test-gns.co.kr"; 
					String cokiVal = UUID.randomUUID().toString();
					int maxAge = 60 * 60 * 24 * 365;
					boolean setSecure = false;
					String setPath = "/";
					String cokiid=user_id;
					String cokisalt=coki_salt;		
					setCookie(response, serverName, cokiKey, coki_salt, setPath, maxAge, setSecure,cokiid,cokisalt);
					session.setAttribute("mflag", "Y");
					obj.put("result", "mail_ok");
				}else{
					lampFlagObj.put("reType", "E");
		  			lampFlagObj.put("reMsg", "mail_error");
		  			
					obj.put("result", "mail_error");
				}
			} catch (Exception e) {
		        // LAMP logger : Login IN_RES : 메일인증 실패 : 시스템 에러 발생
	  			lampFlagObj.put("reType", "S");
	  			lampFlagObj.put("reMsg", "시스템 에러 발생");
	  			lampLogger.makeLog(request, session, lampFlagObj);
	  			
				System.out.println("시스템 에러 발생");
			}
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	    // LAMP logger
	    lampLogger.makeLog(request, session, lampFlagObj);
	    return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);	
    	}	
	//2019.11.15 실제 쿠키 저장	
	private void setCookie(HttpServletResponse response, String serverName,
				String cokiKey, String coki_salt, String setPath, int maxAge,
				boolean setSecure,String cokiid,String cokisalt) {
		//실제 쿠키를 저장
		Cookie salt = new Cookie("cokisalt", cokisalt);		
		Cookie id = new Cookie("cokiid", cokiid);
		//cookie.setSecure(setSecure);
		salt.setMaxAge(60 * 60);//만기일은 한시간 2023-01-30 PERSISTENT_COOKIE 처리 추가
		id.setMaxAge(60 * 60);//만기일은 한시간  2023-01-30 PERSISTENT_COOKIE 처리 추가
		salt.setPath("/");		
		id.setPath("/");
	    //salt.setDomain(".test-gns.co.kr");//운영의 경우는 .gns.kt.com으로 수정	
		//id.setDomain(".test-gns.co.kr");//운영의 경우는 .gns.kt.com으로 수정
		salt.setDomain(".gns.kt.com");//운영의 경우는 .gns.kt.com으로 수정		
		id.setDomain(".gns.kt.com");//운영의 경우는 .gns.kt.com으로 수정
		salt.setSecure(true); // 2023-01-30 RESOURCE_INJECTION 처리 추가
		id.setSecure(true); // 2023-01-30 RESOURCE_INJECTION 처리 추가
		response.addCookie(salt); //response 쿠키에 저장	
		response.addCookie(id);//response 쿠키에 저장
		}



	// 회원정보 insert
	@RequestMapping(value = "/member/member_insert_register", method = { RequestMethod.POST,
			/*RequestMethod.GET */ })		//20141219 이호남 - post방식만 받을 것.
	public ResponseEntity<String> setMemberInsert(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException {
		logger.info("[INFO] setMemberInsert");
		
		// LAMP logger : Setter
  		LampLogger lampLogger = new LampLogger();
  		JSONObject lampFlagObj = new JSONObject();
  		String uuid = UUID.randomUUID().toString();
  		lampFlagObj.put("uuid", uuid);
  		lampFlagObj.put("ltStart", System.currentTimeMillis()); // 시작 시간
  		lampFlagObj.put("targetName", "CREATE"); // 실행 이벤트 이름
  		lampFlagObj.put("logType", "IN_REQ"); // 메인 Flag 값
  		lampFlagObj.put("reType", "I"); // 서브 Flag 값
  		lampFlagObj.put("userId", memberVo.getUser_id()); // 사용자 ID
  		lampFlagObj.put("userNm", memberVo.getUser_nm()); // 사용자 ID
  		lampFlagObj.put("userMail", memberVo.getUser_mail()); // 사용자 ID
  		lampFlagObj.put("userTel", memberVo.getTel1() + "-" + memberVo.getTel2() + "-" + memberVo.getTel3()); // 사용자 ID
  		lampFlagObj.put("userMobile", memberVo.getMobile1() + "-" + memberVo.getMobile2() + "-" + memberVo.getMobile3()); // 사용자 ID
  		lampLogger.makeLog(request, session, lampFlagObj);
  		lampFlagObj.put("logType", "IN_RES");
		
		JSONObject obj = new JSONObject();
		
		// Client RSA 복호화 부분 추가
  		PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
        session.removeAttribute("__rsaPrivateKey__"); // 키 재사용 방지
        if (privateKey == null) {
			obj.put("result", "pw_error");
			lampFlagObj.put("reType", "E");
  			lampFlagObj.put("reMsg", "잘못된 회원가입 시도");
  			lampLogger.makeLog(request, session, lampFlagObj);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
        }
		try {
			String clientUserPw = decryptRsa(privateKey, memberVo.getUser_pw());
			memberVo.setUser_pw(clientUserPw);
		} catch (Exception e) {
	        lampFlagObj.put("reType", "S");
  			lampFlagObj.put("reMsg", "시스템 에러 발생");
  			lampLogger.makeLog(request, session, lampFlagObj);
  			
			System.out.println("시스템 에러 발생");
		}
		
		//20161202 모의해킹 취약점 - 장혁준
		String pw = memberVo.getUser_pw();
		
		if(!pw_vaildate(pw)) {
			obj.put("result", "pw_error");
			
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	        
	        lampFlagObj.put("reType", "E");
  			lampFlagObj.put("reMsg", "패스워드 유효성 실패");
  			lampLogger.makeLog(request, session, lampFlagObj);
	        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
		}
		
		
		//2017 09 11 모의해킹취약점 - 노재덕 
		String authCheck = memberVo.getUser_auth_id();
		if (!authCheck.equals("10")){
			obj.put("result", "pw_error");
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	        
	        lampFlagObj.put("reType", "E");
  			lampFlagObj.put("reMsg", "권한 조작으로 인한 실패");
  			lampLogger.makeLog(request, session, lampFlagObj);
	        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
		}
		try{
		// 2018 07.20 salt 암호화 변경 salt 문자 base64 인코딩 추가
  		    String user_pw2 = memberVo.getUser_pw(); //사용자가 입력한 비번 값 가지고 오기	  		    
  			String salt = this.getSalt();  //salt생성
  		    String user_pw = user_pw2+salt; //가지고 온 비번+salt로 비번 재저장	  		    
  		    memberVo.setUser_pw(user_pw);
  		    String saltEn = Base64Coder.encodeString(salt); //salt base64인코딩 변경 암호화
  		    memberVo.setSalt(saltEn);	  		    
			int result = memberService.insert(memberVo);
			obj.put("result", result);
		}catch(Exception e){
	        // LAMP logger : Login IN_RES : 회원가입 실패 : 시스템 에러 발생
  			lampFlagObj.put("reType", "S");
  			lampFlagObj.put("reMsg", "시스템 에러 발생");
  			lampLogger.makeLog(request, session, lampFlagObj);
			
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
        lampLogger.makeLog(request, session, lampFlagObj);
        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
	
	}
	
	//<!-- 2017.12.22  보안성검토 회원탈퇴 페이지 이동  -->
	//회원탈퇴 화면이동 
		@RequestMapping(value = "/member/member_delete", method = { RequestMethod.POST, RequestMethod.GET
				})
		public ModelAndView getMemberDeletePost(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
			
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();
			
	
			if(session.getAttribute("user_id") == null){
				mav.addObject("redirect_url", "/main.do");
				mav.setViewName("/redirectPage");	
				return mav;
			}
			
			memberVo.setUser_id(session.getAttribute("user_id").toString());
			
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("mapperInfo", "selectMemberView");
			
			try{

				List<MemberVo> list = memberService.select(memberVo, map);
				if(list.size() > 0){
					list.get(0).setUser_pw("");
					mav.addObject("memberVo", list.get(0));
				}
				
			}catch(Exception e){
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
			
			// 보여질 View 페이지를 설정한다.
			mav.addObject("setKey", setKey(session, request));
			mav.setViewName("/member/delete");
			
			return mav;
		}
		
		//<!-- 2019.04.26  구조적 보안진단 MY INFO 비번 확인페이지이동  -->
		//MY INFO 비밀번호 확인 페이지 이동 
			@RequestMapping(value = "/member/member_edit2", method = { RequestMethod.POST, RequestMethod.GET
					})
			public ModelAndView getMemberEdit2(HttpSession session,
					HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException {
				
				// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
				ModelAndView mav = new ModelAndView();
				
		
				if(session.getAttribute("user_id") == null){
					mav.addObject("redirect_url", "/main.do");
					mav.setViewName("/redirectPage");	
					return mav;
				}
				
				memberVo.setUser_id(session.getAttribute("user_id").toString());
				
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("mapperInfo", "selectMemberView");
				
				try{

					List<MemberVo> list = memberService.select(memberVo, map);
					if(list.size() > 0){
						list.get(0).setUser_pw("");
						mav.addObject("memberVo", list.get(0));
						mav.addObject("setKey", setKey(session, request));
					}
					
				}catch(Exception e){
					//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
					System.out.println("시스템 에러 발생");
				}
				
				// 보여질 View 페이지를 설정한다.
				mav.setViewName("/member/edit_check");
				
				return mav;
			}		
	
	//MyInfo 화면 
	@RequestMapping(value = "/member/member_edit", method = { RequestMethod.POST, RequestMethod.GET
			})
	public ModelAndView getMemberEditPost(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		
		logger.info(" init_yn : " + memberVo.getInit_yn());
		if(memberVo.getInit_yn() != null && memberVo.getInit_yn().equals("Y")){
			memberVo.setUser_id(Global.nvl(session.getAttribute("user_id"),""));
		}
		//20210617 모의해킹 취약점 - 이준영, 비밀번호 검증 확인
		/*DR-2021-33021 '21년 GNS 모의해킹 취약점 개선 */
		if(session.getAttribute("pw_checked").toString() != "true"){
			mav.addObject("redirect_url", "/main.do");
			mav.setViewName("/redirectPage");	
			return mav;
		}else{
			session.setAttribute("pw_checked", "false");
		}
		
		if(session.getAttribute("user_id") == null){
			mav.addObject("redirect_url", "/main.do");
			mav.setViewName("/redirectPage");	
			return mav;
		}
		
		memberVo.setUser_id(session.getAttribute("user_id").toString());
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mapperInfo", "selectMemberView");
		
		try{

			List<MemberVo> list = memberService.select(memberVo, map);
			if(list.size() > 0){
				list.get(0).setUser_pw("");
				mav.addObject("memberVo", list.get(0));
			}
			mav.addObject( "flag", "U");
			mav.addObject("init_yn",memberVo.getInit_yn()); //신규사번 경우
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		// 보여질 View 페이지를 설정한다.
		mav.addObject("setKey", setKey(session, request));
		mav.setViewName("/member/join");
		
		return mav;
	}
	
	//회원정보 조회후 수정화면 
	@RequestMapping(value = "/member/member_approval", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getMemberApproval(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
		ModelAndView mav = new ModelAndView();
		
		//세션 권한 확인   //20161202 모의해킹 취약점 - 장혁준
		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 			
			mav.addObject("redirect_url", "/member/login.do");
			mav.addObject("setKey", setKey(session, request));
			mav.setViewName("/redirectPage");	
			return mav;
		}
		//<!--2018.02.23 보안성검토 개인정보 로그저장  -->
		String clientIp = Global.getClientIp(request);
		memberVo.setIp(clientIp);
		//<!-- 2018.03.23 보안성검토 로그 센터 연동 추가 -->  
		memberVo.setUser_id1((String) session.getAttribute("user_id"));
		memberVo.setUser_id2(memberVo.getUser_id1());
		memberVo.setUser_id3(memberVo.getUser_id2());		
		memberVo.setMenu_name("회원상세조회");
		memberVo.setMulti_log_type("단건");
		memberVo.setMulti_log_param("");
		try {
			this.memberListService.insert(memberVo);
		} catch (Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mapperInfo", "selectMemberView");
		
		try{

			List<MemberVo> list = memberService.select(memberVo, map);
			if(list.size() > 0){
				mav.addObject("memberVo", list.get(0));
			}
			HashMap<String,String> map2 = new HashMap<String,String>();
			map2.put("mapperInfo", "selectMemberInfo");
			
			mav.addObject("member_info_list", memberService.select(list.get(0), map2));
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/member/member_approval");
		
		return mav;
	}
	
	// 회원정보 update
	@RequestMapping(value = "/member/member_update_register", method = { RequestMethod.POST})
	public ResponseEntity<String> setMemberUpdate(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException {
		
		logger.info("[INFO] setMemberUpdate");
	
		JSONObject obj = new JSONObject();
		
		//20210617 모의해킹 취약점 - 이준영
		/*DR-2021-33021 '21년 GNS 모의해킹 취약점 개선 */
		/*String lid = memberVo.getUser_id();
		if(!lid.equals(session.getAttribute("user_id"))) {
			obj.put("result", "pw_error");
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
		}*/
		memberVo.setUser_id(session.getAttribute("user_id").toString());
		
		//20171108 모의해킹 - 매개변수조작
		if(!session.getAttribute("user_auth_id").equals("40") && !memberVo.getUser_auth_id().equals(session.getAttribute("user_auth_id")))
		{ 	
			obj.put("result", "pw_error");			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
		}

		
  		// Client RSA 복호화 부분 추가
  		PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
        session.removeAttribute("__rsaPrivateKey__"); // 키 재사용 방지
        if (privateKey == null) {
			obj.put("result", "pw_error");			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
        }
		try {
			String clientUserPw = decryptRsa(privateKey, memberVo.getUser_pw());
			memberVo.setUser_pw(clientUserPw);
		} catch (Exception e) {
			System.out.println("시스템 에러 발생");
		}
		
		//20161202 모의해킹 취약점 - 장혁준
		String pw = memberVo.getUser_pw();
		if(!pw_vaildate(pw)) {
			obj.put("result", "pw_error");
			
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
		}
	
		try{
			// 2018 07.20 salt 암호화 변경 salt 문자 base64 인코딩 추가
  		    String user_pw2 = memberVo.getUser_pw(); //사용자가 입력한 비번 값 가지고 오기
  		    
  			String salt = this.getSalt();  //salt생성
  		    String user_pw = user_pw2+salt; //가지고 온 비번+salt로 비번 재저장
  		    //System.out.println("newpw===="+user_pw); 
  		    memberVo.setUser_pw(user_pw);
  		    String saltEn = Base64Coder.encodeString(salt); //salt base64인코딩 변경 암호화
  		    memberVo.setSalt(saltEn);
			int result = memberService.update(memberVo);
			if(result == 1) {
				session.setAttribute("ll_id", memberVo.getLl_id());
				/*DR-2021-33021 '21년 GNS 모의해킹 취약점 개선 */
				session.setAttribute("pw_checked", "true");
			}
			obj.put("result", result);
			obj.put("flag", "U");
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	// 회원 권한승인
	@RequestMapping(value = "/member/member_approval_register", method = { RequestMethod.POST})
	public ResponseEntity<String> setMemberapproval(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws RuntimeException {
		logger.info("[INFO] setMemberapproval");
	
	//세션 & 권한 확인   //20170911 모의해킹 취약점 - 노재덕
		
	JSONObject obj = new JSONObject();	
		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("") ||!session.getAttribute("user_auth_id").equals("40")){ 	
		    obj.put("result", "pw_error");			
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
				}
		//<!--2018.01.19 보안성검토 권한 수정 저장 -->
		HashMap<String,String> map2= new HashMap<String,String>();
		map2.put("mapperInfo", "insertLogauth");
		String user_approval_id = (String) session.getAttribute("user_id");
		memberVo.setSiteGubun("GNS_POR");
		memberVo.setEvent_Code("U");
		memberVo.setUser_approval_id(user_approval_id);
		 try {
			loginService.insert(memberVo, map2);
		} catch (Exception e1) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("mapperInfo", "updateUser2");
		try{
			int result = memberService.update(memberVo,map);
			obj.put("result", result);
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
	
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	// 이용약관
	@RequestMapping(value = "/member/agreement", method = { RequestMethod.POST })
	public ModelAndView getAgreement(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		logger.info("[INFO] agreement");
		
		//메뉴접속통계 적용
		String menuId = "504010";
 		
 		MenuStateVo menuVo = new MenuStateVo();
 		menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"),""));
 		menuVo.setMenu_id(menuId);		
 		try {
			this.statService.insertMenuState(menuVo);
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
 		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/agreement");
		
		return mav;
	}
	
	@RequestMapping(value = "/member/init_pw", method = { RequestMethod.POST})
	public ResponseEntity<String> init_pw(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, String ruser_id) throws RuntimeException {
	
	logger.info("[INFO] ID Initialization : " + ruser_id);
	
	//세션 & 권한 확인   //20170911 모의해킹 취약점 - 노재덕
	
	
		JSONObject obj = new JSONObject();
		
			if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("") ||!session.getAttribute("user_auth_id").equals("40")){ 	
			    obj.put("result", "pw_error");			
				HttpHeaders responseHeaders = new HttpHeaders();
		        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
					}
	
	HashMap<String,String> map= new HashMap<String,String>();
	map.put("mapperInfo", "update_pw");
	//2018.07.20 비밀번호 초기화 시에 salt적용처리
	    String salt = this.getSalt();  //salt생성
	    String user_pw2 = "_new_1234!";
	    String user_pw = user_pw2+salt; //가지고 온 비번+salt로 비번 재저장
	    MemberVo memberVo = new MemberVo();	  
	    memberVo.setUser_pw(user_pw);
	    String saltEn = Base64Coder.encodeString(salt); //salt base64인코딩 변경 암호화
	    memberVo.setSalt(saltEn);
	    memberVo.setUser_id(ruser_id);
	
	try{

		int result = memberService.update(memberVo,map);
		obj.put("result", result);
		
	}catch(Exception e){
		//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
		System.out.println("시스템 에러 발생");
	}
	
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/member/unlock_id", method = { RequestMethod.POST})
	public ResponseEntity<String> unlock_id(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, String ruser_id) throws RuntimeException {
	JSONObject obj = new JSONObject();
	logger.info("[INFO] Unlock ID : " + ruser_id);
	HashMap<String,String> map= new HashMap<String,String>();
	
	MemberVo memberVo = new MemberVo(); 
	memberVo.setUser_id(ruser_id);
	memberVo.setLogin_fail_cnt(0);
	memberVo.setLogin_fail_time(0);
	
	map.put("mapperInfo", "unlock_id");
	
	try{
		int result = memberService.update(memberVo,map);
		obj.put("result", result);
	}catch(Exception e){
		//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
		System.out.println("시스템 에러 발생");
	}
	
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
//2019.11.15 메일잠금 기능 신규추가
	@RequestMapping(value = "/member/maillock_id", method = { RequestMethod.POST})
	public ResponseEntity<String> maillock_id(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, String ruser_id, String rmail_yn) throws RuntimeException {
	JSONObject obj = new JSONObject();
	logger.info("[INFO] maillock ID : " + ruser_id + rmail_yn);
	HashMap<String,String> map= new HashMap<String,String>();
	MemberVo memberVo = new MemberVo(); 
	memberVo.setUser_id(ruser_id);
		if(rmail_yn.equals("Y")){
			rmail_yn="N";
		}else{
			rmail_yn="Y";
		}
		memberVo.setMail_yn(rmail_yn);
		map.put("mapperInfo", "maillock_id");
		try{
			int result = memberService.update(memberVo,map);
			obj.put("result", result);
		}catch(Exception e){		
			System.out.println("시스템 에러 발생");
		}	
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	//2019.11.15 전체 메일ON/OFF 기능 신규추가
	@RequestMapping(value = "/member/maillock_all", method = { RequestMethod.POST})
	public ResponseEntity<String> maillock_all(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, String rmail_yn) throws RuntimeException {
	JSONObject obj = new JSONObject();
	logger.info("[INFO] maillock all : " + rmail_yn);
	HashMap<String,String> map= new HashMap<String,String>();
	MemberVo memberVo = new MemberVo();
	memberVo.setMail_yn(rmail_yn);
	map.put("mapperInfo", "maillock_all");//모든 사용자 메일잠금 여부 변경		
		try{
			int result = memberService.update(memberVo,map);
			obj.put("result", result);
		}catch(Exception e){		
			System.out.println("시스템 에러 발생");
		}
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
	}
	//<!-- DR-2022-22145 ( 20 ) 개인정보 조회/출력 시 개인정보의 표시제한 처리 개선 : 회원삭제 로직 제거 -->


	
	//<!-- 2019.04.26 구조적보안진단 MY INFO 비번체크 로직추가  -->
		@RequestMapping(value = "/member/select_id", method = { RequestMethod.POST})
		public ResponseEntity<String> select_id(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, String ruser_id, String old_pwd) throws RuntimeException {
		
			JSONObject objcheck = new JSONObject();
			//세션 사용자 확인
	  		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 			
	  			objcheck.put("result", "system_error"); 
	  		}	
		
	  		// Client RSA 복호화 부분 추가
	  		PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
	        session.removeAttribute("__rsaPrivateKey__"); // 키 재사용 방지
	        if (privateKey == null) {
	        	objcheck.put("result", "system_error"); 
	        }
			try {
				String clientUserPw = decryptRsa(privateKey, old_pwd);
				old_pwd = clientUserPw;
			} catch (Exception e) {
				System.out.println("시스템 에러 발생");
			}
	  		
	  		
		//MY INFO 비번확인
		logger.info("[INFO] SELECT ID : " + ruser_id);
		logger.info("[INFO] old_pwd : " + old_pwd);
		MemberVo memberVo = new MemberVo();
		  //2019.12.06 RSA 암복호화 추가
			HashMap<String,String> rsaOpen = new HashMap<String,String>();
		    rsaOpen.put("mapperInfo", "selectRsaOpenKey");
		    HashMap<String,String> rsaSecret = new HashMap<String,String>();
		    rsaSecret.put("mapperInfo", "selectRsaSecretKey");
		    try{
		       List<MemberVo> rsaOpenKey = memberService.select(memberVo,rsaOpen);
		 	   String sPublicKey = rsaOpenKey.get(0).getrsaOpenKey(); //공개키 가져오기		 	   
		       List<MemberVo> rsaSecretKey = memberService.select(memberVo,rsaSecret);
		       String sPrivateKey = rsaSecretKey.get(0).getrsaSecretKey();//비공개키 가져오기
		       String rsa_user_pw = old_pwd;
		       String rsa_user_pwEn=this.encrypt(rsa_user_pw,sPublicKey);
		       String rsa_user_pwde=this.decrypt(rsa_user_pwEn, sPrivateKey);
		       memberVo.setUser_pw(rsa_user_pwde); //RSA 암복호화 한 pW를 저장		       
		    }catch (Exception e1) {	
			   System.out.println("시스템 에러 발생");
			}    
		
		    HashMap<String,String> map5 = new HashMap<String,String>();
			map5.put("mapperInfo", "selectCheckSalt");
			memberVo.setUser_id(ruser_id);
			List<MemberVo> saltCheck = null;
		  try {
			saltCheck = memberService.select(memberVo,map5);
			String salt = saltCheck.get(0).getSalt();
			String saltDe = Base64Coder.decodeString(salt);
			String old_pwd2 = old_pwd+saltDe;
			memberVo.setOld_pwd(old_pwd2);
			HashMap<String,String> map6= new HashMap<String,String>();
			map6.put("mapperInfo", "select_id");
			List<MemberVo> pwcheck = null;
			pwcheck = memberService.select(memberVo,map6);		
             if(pwcheck.size() == 0){
            	 objcheck.put("result", "pw_error");
            	 /*DR-2021-33021 '21년 GNS 모의해킹 취약점 개선 */
            	 session.setAttribute("pw_checked", "false");
             }else{
            	 objcheck.put("result", "id_select");
            	 /*DR-2021-33021 '21년 GNS 모의해킹 취약점 개선 */
            	 session.setAttribute("pw_checked", "true");
             }
	
		} catch (Exception e1) {
			logger.error("시스템 에러 발생---------------");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(objcheck.toString(), responseHeaders, HttpStatus.CREATED);
	
		}
		
//<!-- 2019.04.26 구조적보안진단 중복로그인 session체크 로직추가  -->
		@RequestMapping(value = "/member/session_check", method = { RequestMethod.POST})
		public ResponseEntity<String> session_check(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, MemberVo memberVo2) throws RuntimeException {
		
			JSONObject objcheck = new JSONObject();
             String user_id=(String)session.getAttribute("user_id");                  
		    HashMap<String,String> map = new HashMap<String,String>();
			map.put("mapperInfo", "selectSessionCheck");
			
			MemberVo memberVo = new MemberVo(); 
			memberVo.setUser_id(user_id);
				List<MemberVo> sessioncheck = null;
				try {
			sessioncheck = memberService.select(memberVo,map);						
             if(sessioncheck.size() == 0){
            	 objcheck.put("result", "session_null"); 		            	 
             }else{
            	 objcheck.put("result", "session_ok");  
             }
	
		} catch (Exception e1) {
			logger.error("시스템 에러 발생---------------");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(objcheck.toString(), responseHeaders, HttpStatus.CREATED);
	
		}		
	
	//<!-- 2017.12.22  보안성검토 회원탈퇴  -->
	@RequestMapping(value = "/member/delete_id", method = { RequestMethod.POST})
	public ResponseEntity<String> delete_id(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, String ruser_id, String old_pwd) throws RuntimeException {
	// LAMP logger : Setter
	LampLogger lampLogger = new LampLogger();
	JSONObject lampFlagObj = new JSONObject();
	String uuid = UUID.randomUUID().toString();
	lampFlagObj.put("uuid", uuid);
	lampFlagObj.put("ltStart", System.currentTimeMillis()); // 시작 시간
	lampFlagObj.put("targetName", "DELETE"); // 실행 이벤트 이름
	lampFlagObj.put("logType", "IN_REQ"); // 메인 Flag 값
	lampFlagObj.put("reType", "I"); // 서브 Flag 값
	lampFlagObj.put("userId", ruser_id); // 사용자 ID
	lampLogger.makeLog(request, session, lampFlagObj);
	lampFlagObj.put("logType", "IN_RES"); // 메인 Flag 값
	
	JSONObject obj = new JSONObject();
	
	// Client RSA 복호화 부분 추가
	PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
    session.removeAttribute("__rsaPrivateKey__"); // 키 재사용 방지
    if (privateKey == null) {
		lampFlagObj.put("reType", "E");
		lampFlagObj.put("reMsg", "잘못된 회원탈퇴 시도");
    	lampLogger.makeLog(request, session, lampFlagObj);
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
    	return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
    }
	try {
		String clientUserPw = decryptRsa(privateKey, old_pwd);
		old_pwd = clientUserPw;
	} catch (Exception e) {
		lampFlagObj.put("reType", "S");
		lampFlagObj.put("reMsg", "시스템 에러 발생");
    	lampLogger.makeLog(request, session, lampFlagObj);
		
		System.out.println("시스템 에러 발생");
	}
	
	//사용자 삭제
	logger.info("[INFO] DELETE ID : " + ruser_id);
	logger.info("[INFO] old_pwd : " + old_pwd);
	MemberVo memberVo = new MemberVo(); 
	//2019.12.06 RSA 암복호화 추가
		HashMap<String,String> rsaOpen = new HashMap<String,String>();
	    rsaOpen.put("mapperInfo", "selectRsaOpenKey");
	    HashMap<String,String> rsaSecret = new HashMap<String,String>();
	    rsaSecret.put("mapperInfo", "selectRsaSecretKey");
	    try{
	       List<MemberVo> rsaOpenKey = memberService.select(memberVo,rsaOpen);
	 	   String sPublicKey = rsaOpenKey.get(0).getrsaOpenKey(); //공개키 가져오기	 	   
	       List<MemberVo> rsaSecretKey = memberService.select(memberVo,rsaSecret);
	       String sPrivateKey = rsaSecretKey.get(0).getrsaSecretKey();//비공개키 가져오기
	       String rsa_user_pw = old_pwd;
	       String rsa_user_pwEn=this.encrypt(rsa_user_pw,sPublicKey);
	       String rsa_user_pwde=this.decrypt(rsa_user_pwEn, sPrivateKey);
	       memberVo.setUser_pw(rsa_user_pwde); //RSA 암복호화 한 pW를 저장	    
	    }catch (Exception e1) {	
	        // LAMP logger : Login IN_RES : 회원탈퇴 실패 : 시스템 에러 발생
  			lampFlagObj.put("reType", "S");
  			lampFlagObj.put("reMsg", "시스템 에러 발생");
	    	lampLogger.makeLog(request, session, lampFlagObj);
			System.out.println("시스템 에러 발생");
		}
	    HashMap<String,String> map5 = new HashMap<String,String>();
		map5.put("mapperInfo", "selectCheckSalt");
		memberVo.setUser_id(ruser_id);
		List<MemberVo> saltCheck = null;
			try {
				saltCheck = memberService.select(memberVo,map5);
			} catch (Exception e1) {
		        // LAMP logger : Login IN_RES : 회원탈퇴 실패 : 시스템 에러 발생
	  			lampFlagObj.put("reType", "S");
	  			lampFlagObj.put("reMsg", "시스템 에러 발생");
		    	lampLogger.makeLog(request, session, lampFlagObj);
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
	String saltResult = String.valueOf(saltCheck.size());
	String salt = saltCheck.get(0).getSalt();
	String saltDe = Base64Coder.decodeString(salt);
	String old_pwd2 = old_pwd+saltDe;
	memberVo.setOld_pwd(old_pwd2);	
	HashMap<String,String> map= new HashMap<String,String>();
	map.put("mapperInfo", "delete_id");
	//<!--2022.06.24 회원탈퇴시 메일 인증정보 삭제 -->
	HashMap<String,String> map1= new HashMap<String,String>();
	map1.put("mapperInfo", "delete_mail_approval");
	try{
		int result = memberService.delete(memberVo, map);
		memberService.delete(memberVo, map1);
		if(result == 0 ){
			HashMap<String,String> map2= new HashMap<String,String>();
			map2.put("mapperInfo", "selectCheckUser");
			List<MemberVo> user_checklist = memberService.select(memberVo, map2);
			memberVo.setUser_id(user_checklist.get(0).getUser_id());
	  		memberVo.setLogin_fail_cnt(user_checklist.get(0).getLogin_fail_cnt()+1);
	  		memberVo.setLogin_fail_time(new Date().getTime());
	  		HashMap<String,String> map3= new HashMap<String,String>();
	  		map3.put("mapperInfo", "updateUserLoginState");
			loginService.update(memberVo, map3);
			//<!--2018.01.19 보안성검토 로그저장 -->			
				String clientIp = Global.getClientIp(request);	
	  			memberVo.setIp(clientIp);
	  			memberVo.setSiteGubun("GNS_POR");
			HashMap<String,String> map4= new HashMap<String,String>();
			 map4.put("mapperInfo", "insertLogfail");
			 loginService.insert(memberVo, map4);
	  		if(memberVo.getLogin_fail_cnt() > 4){
	  			lampFlagObj.put("reType", "E");
	  			lampFlagObj.put("reMsg", "잘못된 비밀번호 입력");
	  			session.invalidate();
	  			obj.put("result", "pw_error");
				obj.put("login_fail_cnt", memberVo.getLogin_fail_cnt());
	  		}else{
	  			lampFlagObj.put("reType", "E");
	  			lampFlagObj.put("reMsg", "잘못된 비밀번호 입력");
	  			obj.put("result", "pw_error");
				obj.put("login_fail_cnt", memberVo.getLogin_fail_cnt());
	  		}
			
		}else{
			session.invalidate();
			obj.put("result", "id_delete");
		}
	}catch(Exception e){
		// LAMP logger : Login IN_RES : 회원탈퇴 실패 : 시스템 에러 발생
		lampFlagObj.put("reType", "S");
		lampFlagObj.put("reMsg", "시스템 에러 발생");
    	lampLogger.makeLog(request, session, lampFlagObj);
		//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
		System.out.println("시스템 에러 발생");
	}	
	lampLogger.makeLog(request, session, lampFlagObj);
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	// 2023-07-14 : 모의해킹 취약점 개선 : pw찾기 페이지 삭제
	//비밀번호/아이디 찾기
	/*@RequestMapping(value = "/member/idpw", method = { RequestMethod.POST })
	public ModelAndView getIdpw(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/member/idpw");
		
		return mav;
	}*/
	
	//사용자 이름 & 이메일 주소로 비밀번호 초기화 하기
	@RequestMapping(value = "/member/idpw_1", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView init_pw_with_usernm_mail(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, String user_nm, String user_mail1) throws RuntimeException {
				
		logger.info(" #### user_id : " + user_nm + " ,user_mail : " + user_mail1  );
		MemberVo vo = new MemberVo();
		vo.setUser_nm(user_nm);
		vo.setUser_mail(user_mail1);
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
									
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mapperInfo", "selectUserID1");
		
		try{
			List<MemberVo> list = loginService.select(vo, map);
			
			int cnt = 0;
			String user_id = ""; //사용자 ID
			
			for( MemberVo member : list){
				user_id = user_id.equals("")?"":"," + member.getUser_id();			
				cnt++;
			}
					
			if(cnt > 1){
				//중복 된 이름, 메일주소가 존재하는 경우
				mav.setViewName("/member/idpw");
				mav.addObject("error","중복 된 이름, 이메일이 존재합니다.(" + user_id + ")");			
				return mav;
			}
			else if(cnt == 0){
				//해당 내용에 대한 사용자가 존재하지 않을 경우
				mav.setViewName("/member/idpw");
				mav.addObject("error","해당 사용자는 존재하지 않습니다.");			
				return mav;
			}
			
			// 보여질 View 페이지를 설정한다.
			mav.addObject("memberVo",list.get(0));		
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		mav.setViewName("/member/idpw_done");		
		return mav;
	}
	
	private String getRanPWD(){
		char[] charSet1 = new char[]{'0','1','2','3','4','5','6','7','8','9'};
		char[] charSet2 = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'};
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<3;i++){			
			int index = (int)(charSet2.length * Math.random());		
			sb.append(charSet2[index]);			
		}
	
		for(int i=0; i<4;i++){			
			int index = (int)(charSet1.length * Math.random());
			sb.append(charSet1[index]);			
		}
		
		sb.append("!");
		
		return sb.toString();
	}
	//2018.07.30 salt 암호화 추가 난수 생성 20자리 고정
	private String getSalt(){		
		char[] charSet2 = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i'
				,'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<20;i++){			
			int index = (int)(charSet2.length * Math.random());		
			sb.append(charSet2[index]);			
		}
		
		return sb.toString();
	}
	
	//2019.11.15 메일인증 난수 6자리 생성
	private String getMailCode(){		
		char[] charSet3 = new char[]{'0','1','2','3','4','5','6','7','8','9'};
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<6;i++){			
			int index = (int)(charSet3.length * Math.random());		
			sb.append(charSet3[index]);			
		}
		
		return sb.toString();
	}
	
	//사용자 id & 이메일 주소로 비밀번호 초기화 하기
	//@RequestMapping(value = "/member/idpw_2", method = { RequestMethod.POST,
	//		/* RequestMethod.GET */ }) 		//2014.12.22 이호남 - post방식으로만 동작하도록 수정.
	/*public ModelAndView init_pw_with_userid_mail(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo vo) throws RuntimeException {
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
    
		logger.info(" #### user_id : " + vo.getUser_id() + " ,user_mail : " + vo.getUser_mail()  );
								
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mapperInfo", "selectUserID2");
		
		try{

			List<MemberVo> list = loginService.select(vo, map);
							
			if(list.size() == 0){
				//해당 내용에 대한 사용자가 존재하지 않을 경우
				mav.setViewName("/member/idpw");
				mav.addObject("error","해당 사용자는 존재하지 않습니다.");			
				return mav;
			}else if(list.size() > 1)
			{
				mav.setViewName("/member/idpw");
				mav.addObject("error","오류가 발생했습니다.");			
				return mav;
			} 
			
			//2018.07.20 이메일,id로 비번 초기화 salt적용
			String pwd = this.getRanPWD();
			String salt = this.getSalt();
			String pwd2 = pwd+salt;
			String saltEn = Base64Coder.encodeString(salt); //salt base64인코딩 변경 암호화
		    
			MemberVo rVo = list.get(0);
			rVo.setSalt(saltEn);
			rVo.setUser_pw2(pwd2); //신규 패스워드
			rVo.setUser_pw(pwd); //화면 보여주기 위한 비밀번호
			HashMap<String,String> map2= new HashMap<String,String>();
			map2.put("mapperInfo", "update_pw2");				
			memberService.update(rVo,map2); 

			// 보여질 View 페이지를 설정한다.
			mav.addObject("memberVo",rVo);	 	 
			
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		mav.setViewName("/member/idpw_done");		
		return mav;
	}*/
	
	
	//사용자 id & 이메일 주소로 비밀번호 초기화 하기
	@RequestMapping(value = "/member/idpw_change", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getIdPwChange(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
    
		mav.addObject("user_id", session.getAttribute("user_id"));
		// 보여질 View 페이지를 설정한다.
		mav.addObject("setKey", setKey(session, request));
		// 2023-07-14 : 모의해킹 취약점 개선
		mav.setViewName("/member/idpw_change");		
		return mav;
	}
	//<!-- 2017.12.22  보안성검토 비밀번호 변경일로 부터 3개월 지난 사용자 비번 변경 하기  -->
	
		@RequestMapping(value = "/member/idpw_change2", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getIdPwChange2(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();
	    
			mav.addObject("user_id", session.getAttribute("user_id"));
			// 보여질 View 페이지를 설정한다.			
			mav.setViewName("/member/idpw_change2");		
			return mav;
		}
		
		
//<!-- 2019.11.15 메일인증 페이지 이동 -->
	@RequestMapping(value = "/member/mail_approval", method = { RequestMethod.POST,
			 })
	public ModelAndView getMail_approval(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();    
		mav.addObject("user_id", session.getAttribute("user_id"));
		// 보여질 View 페이지를 설정한다.			
		mav.setViewName("/member/mail_approval");
		return mav;
	}
	
		//<!-- 2017.12.22  보안성검토 비밀번호 다음에 변경하기 -->
		@RequestMapping(value = "/member/idpw_pass", method = { RequestMethod.POST,
				 })
		public ModelAndView pass_pw(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, MemberVo vo) throws RuntimeException {
			
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();     	
	
			try{

				HashMap<String,String> map = new HashMap<String,String>();
				map.put("mapperInfo", "update_Pass");
				int rt = memberService.update(vo,map); 
				
			}catch(Exception e){
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
					
			// 보여질 View 페이지를 설정한다.
			
			mav.addObject("redirect_url", "/main.do");
			mav.setViewName("/redirectPage");	
			return mav;
		}
		
	//<!-- 2019.11.15  메일인증코드 업데이트 -->
	@RequestMapping(value = "/member/update_mail_code", method = { RequestMethod.POST,
			 })
	public ModelAndView update_mail_code(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo vo) throws RuntimeException {		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();    	
	
		try{
	       String mail_code = this.getMailCode();
	       vo.setMail_code(mail_code);
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("mapperInfo", "update_mail_code");
			memberService.update(vo,map);
		}catch(Exception e){					
			System.out.println("시스템 에러 발생");
		}				
		mav.addObject("user_id", session.getAttribute("user_id"));
		// 보여질 View 페이지를 설정한다.			
		mav.setViewName("/member/mail_approval");					
		return mav;
	}
	
	@RequestMapping(value = "/member/idpw_change_done", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView change_pw(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, MemberVo vo) throws Exception {
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView(); 
		// 2023-07-14 : 모의해킹 취약점 개선
		// Client RSA 복호화 부분 추가
		PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__"); // 키 재사용 방지
		// 키 발급 여부 확인
		if (privateKey == null) {
			mav.addObject("redirect_url", "/error.do");
			mav.setViewName("/redirectPage");	
			return mav;
		}
		
		// 복호화 로직 추가
		try {
			vo.setOld_pwd(decryptRsa(privateKey, vo.getOld_pwd()));
			vo.setNew_pwd(decryptRsa(privateKey, vo.getNew_pwd()));
		} catch (Exception e) {
			System.out.println("시스템 에러 발생");
		}
		//20161202 모의해킹 취약점 - 장혁준
		String pw = vo.getNew_pwd();
		if(!pw_vaildate(pw)) {

			mav.addObject("redirect_url", "/error.do");
			mav.setViewName("/redirectPage");	
			return mav;
			
		}
		//2018.07.20 slat 값 가지고 와서 체크로직 수정
		HashMap<String,String> map3 = new HashMap<String,String>();
	  		map3.put("mapperInfo", "selectCheckSalt");
			List<MemberVo> saltCheck = memberService.select(vo,map3);
			String salt2 = saltCheck.get(0).getSalt();
  			String saltDe = Base64Coder.decodeString(salt2);
  			String old_pwd = vo.getOld_pwd();
  			String old_pwd2 = old_pwd+saltDe;
  			vo.setOld_pwd(old_pwd2);		
				
	  					
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mapperInfo", "check_pw");
		
		try{

			List<MemberVo> list = loginService.select(vo, map);
									
			if(list.size() == 0){ 
				//해당 내용에 대한 사용자가 존재하지 않을 경우
				mav.setViewName("/member/idpw_change");		
				mav.addObject("error","해당 사용자는 존재하지 않습니다.");			
				mav.addObject("user_id", vo.getUser_id());
				return mav;
			}
			logger.info(" ### user_id : " + list.get(0).getUser_id()); 
			// 2018 07.20 salt 암호화 변경 salt 문자 base64 인코딩 추가
	  		    String user_pw2 = vo.getNew_pwd(); //사용자가 입력한 비번 값 가지고 오기	  		    
	  			String salt = this.getSalt();  //salt생성
	  		    String user_pw = user_pw2+salt; //가지고 온 비번+salt로 비번 재저장	  		    
	  		    vo.setNew_pwd(user_pw);
	  		    String saltEn = Base64Coder.encodeString(salt); //salt base64인코딩 변경 암호화
	  		    vo.setSalt(saltEn);
			HashMap<String,String> map2= new HashMap<String,String>();
			map2.put("mapperInfo", "update_pw3");				
			int rt = memberService.update(vo,map2);
			logger.info(" ### Change Password Update  : " + rt);
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
				
		// 보여질 View 페이지를 설정한다.
		mav.addObject("redirect_url", "/main.do");
		mav.setViewName("/redirectPage");	
		return mav;
	}
	
	public boolean pw_vaildate(String pw) {

		boolean regx = Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,}$", pw);
		logger.info(" ### Password Validate Check  : " + regx);

		return regx;
	}
	
	//이전 개인정보처리방침 리스트
	@RequestMapping(value = "/member/personalPop", method = { RequestMethod.POST })
 	public ModelAndView getPersonalPop(HttpSession session,
 			HttpServletRequest request, HttpServletResponse response, DataRoomVo dataRoomVo ) throws RuntimeException {
 		logger.info("[INFO] /member/personalPop");

 		//페이징 초기화
 		dataRoomVo.setLimit(3); //출력 행의 갯수		
 		dataRoomVo.setOffSet(0); //출력 첫행 위치
		dataRoomVo.setFile_gbn("PS");
 		if(dataRoomVo.getPage().equals("1")){
			//최초로 목록 화면 들어가는 경우
			logger.info(" ### 최초로 목록 화면 들어가는 경우 ");
			
			//검색 조건 초기화, 페이징 초기화 								 	 			
			dataRoomVo.setOffSet(0);
		}
		else{
			//페이징으로 화면 들어가는 경우
			logger.info(" ### 페이징으로 화면 들어가는 경우 ");
			
			int page = Integer.parseInt(dataRoomVo.getPage());
				int offset = 0;
				if(page == 1){
					offset = 0;
				}else
				{
					offset = page * 3 - 3;
				}			
				dataRoomVo.setOffSet(offset);
		}
 		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mapperInfo", "selectPersonal");
		
		try{
			mav.addObject("personal_list", this.dataRoomService.select(dataRoomVo, map));
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		mav.addObject("dataroomVo", dataRoomVo);
		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/member/personal_pop");
		
		return mav;
 	}
	
	// 개인정보처리방침저장 저장(INSERT)
 	@RequestMapping(value = "/member/personal_insert_register", method = { RequestMethod.POST })
 	public ModelAndView setPersonalInsert(HttpSession session,
		MultipartHttpServletRequest request, HttpServletResponse response, DataRoomVo dataRoomVo) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {
 		logger.info(" ### file_id :" + dataRoomVo.getFile_id());
 		logger.info("자료실 저장 /support/dataroom_write_register");
 		logger.info(" dataRoomVo conf value :" + dataRoomVo.getFile_cont() );

 		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
 		ModelAndView mav = new ModelAndView();
 		//<!--2019.04.26 구조적보안진단 파라미터 변조 수정>
 		//세션 사용자 확인
 		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){
 			mav.addObject("redirect_url", "/member/login.do");
 			mav.addObject("setKey", setKey(session, request));
			mav.setViewName("/redirectPage");
 			return mav;
 		}
 		
 		//세션 권한 확인
 		if(session.getAttribute("user_auth_id") == null || ! session.getAttribute("user_auth_id").equals("40")){ 			
 			mav.addObject("redirect_url", "/main.do");
			mav.setViewName("/redirectPage");
 			return mav;
 		}
 		int result = 0;
 		 		
 		//ID 할당
 		String fileId= "";  // 자료실 ID
 		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mapperInfo", "selectFileId"); //mapper 명

		try{
			List<DataRoomVo> list = dataRoomService.select(dataRoomVo, map); //자료실ID 할당( yyyMMdd + 001 (3자리 순번) ) 	

			map.clear();
			
			fileId = list.get(0).getFile_id(); //ID 쿼리조회 결과값(MAX + 1)
			logger.info(" ### list.get(0).getFile_id() :" + fileId );
			dataRoomVo.setFile_id(fileId); //자료실id	
	 		
	 		//파일업로드 경우 파일처리
			String fileName = ""; //업로드 파일명 
	 		String fileExt = "";  //파일확장자명 
	 		if(!request.getFile("upload_file").getOriginalFilename().equals("")){
	 			logger.info(" ### 파일업로드 경우 파일처리 ");
	 			fileName = request.getFile("upload_file").getOriginalFilename();

	 			fileExt = fileName.substring(fileName.lastIndexOf(".") + 1); 			
	 			logger.info("### upload 파일확장자 : " + fileExt);
	 			
				if(fileExt.equals("pdf")) {
					logger.info("### File EXT Check Success!");
			 	} else {
			 		logger.info("### BAD EXT!");
			 		
					mav.addObject("redirect_url", "/error.do");
					mav.setViewName("/redirectPage");
					return mav;
			 	}

	 			dataRoomVo.setFile_name(fileName);
	 			dataRoomVo.setFile_path(filePath);
	 			dataRoomVo.setFile_ext(fileExt);
	 			logger.info("### upload 디렉토리 : " + filePath + " 파일이름 : " + fileName);	
	 	 	}
	 		
	 		map.put("mapperInfo", "insertPersonal");
	 		result = dataRoomService.insert(dataRoomVo, map);
	 		logger.info(" 저장 : " + result);
	 		
	 		//파일 업로드 된 경우 파일업로드 처리
	 		if(!fileName.equals("")){
	 			logger.info("### 파일 업로드 된 경우 파일업로드 처리");	 			
	 			UploadFile("upload_file",filePath, fileId + "." + fileExt, request); //파일업로드 처리
	 		}
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
				
 		mav.addObject("redirect_url", "/member/personalPop.do");
		mav.setViewName("/redirectPage");
		return mav;
 	}
	
 	public void UploadFile(String componentName,String dirName,String fileName,MultipartHttpServletRequest multipartRequest) {  
    	  
    	File f = new File(dirName);   
    	//<!--2019.04.26 구조적보안진단 업로드 수정>
    	String fileExt = "";  //파일확장자명 
    	
    	fileExt = fileName.substring(fileName.lastIndexOf(".") + 1); 			
			logger.info("### upload2 파일확장자 : " + fileExt);
			
		if(fileExt.equals("pdf")) {
			logger.info("### File EXT Check Success!");
	 	} else {
	 		logger.info("### BAD EXT!");	 		
			return;
	 	}
    	
    	logger.info(" ################## " + dirName);
    	if(!f.exists())
    	{    		
    		 f.mkdirs();
    	}
    	else {
			try {
				if (Global.fileDelete(f)) {
					logger.info("### 디렉토리 삭제 디렉토리 삭제 " + dirName + "/ 디렉토리 삭제");
					logger.info("### 디렉토리 삭제 디렉토리 삭제 " + dirName + "/ 디렉토리 삭제");
					logger.info("### 디렉토리 삭제 디렉토리 삭제 " + dirName + "/ 디렉토리 삭제");
					f.mkdirs(); // 재생성
				} else {
					logger.info("### 삭제 실패 " + dirName + "/ 디렉토리 삭제");
				}
			} catch (Exception e) {
				// 2023-01-30 TOCTOU_RACE_CONDITION 처리 추가
			}

		}
    	
    	
    	if(multipartRequest.getFile(componentName) != null)
    	{
    		FileManager manager = new FileManager(multipartRequest);
	    	try {
				manager.UploadFile(componentName,dirName,fileName);
			} catch (Exception e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
    	}
    }
 	
 	// 이전 개인정보처리방침 다운로드
  	@RequestMapping(value = "/member/personal_download", method = { RequestMethod.POST})   //20161202 모의해킹 취약점 - 장혁준
  	public ModelAndView getPersonalDownload(HttpSession session,
  			HttpServletRequest request, HttpServletResponse response, String file_id, String file_name) throws RuntimeException {

  		logger.info( " ### file_id : " + file_id );
  		logger.info( " ### file_name : " + file_name );
  		
  		// 모의 해킹 진단 조치사항 - 다운로드 파일의 위치를 변경하여 주요정보 파일을 받을 수 있는 부분 수정
  		if(file_id.contains("..")||file_id.contains("/"))		{
  			
  			ModelAndView mav = new ModelAndView();
  			mav.addObject("redirect_url", "/member/personalPop.do");
 			mav.setViewName("/redirectPage");
 	
  			return mav;
  		}
  		// 2023-01-30 PATH_TRAVERSAL 처리 추가
		if (file_id != null) {
			file_id = file_id.replaceAll("\\", "");
			file_id = file_id.replaceAll("&", "");
		}
  		//return DownloadFile( file_path +file_name ,file_name);
  		return DownloadFile( filePath + file_id ,file_name);
  	}
  	
  	public ModelAndView DownloadFile(String fileName,String downloadName) {
  		//<!--2019.04.26 구조적보안진단 다운로드 수정 -->
  		if(downloadName.contains("..")||downloadName.contains("/"))		{
  			ModelAndView mav = new ModelAndView();
  			mav.addObject("redirect_url", "/error.do");
 			mav.setViewName("/redirectPage");
 	
  			return mav;
  		}
  		// 2023-01-30 PATH_TRAVERSAL 처리 추가
		if (fileName != null) {
			fileName = fileName.replaceAll("\\", "");
			fileName = fileName.replaceAll("&", "");
		}
    	File downloadFile = new File(fileName);
    	ModelAndView mvc = new ModelAndView("fileDownloadView","downloadFile",downloadFile);		
    	mvc.addObject("downloadFile",downloadFile);
    	mvc.addObject("egov_download_name",downloadName);
    	
    	return mvc;
    }
  	
  	//이전 개인정보처리방침 삭제
   	@RequestMapping(value = "/member/personal_delete", method = { RequestMethod.POST })
   	public ModelAndView getPersonalDelete(HttpSession session,
   			HttpServletRequest request, HttpServletResponse response, DataRoomVo dataRoomVo) throws RuntimeException, NoSuchAlgorithmException, InvalidKeySpecException {  		
   		ModelAndView mav = new ModelAndView();
 				
   		//세션 사용자 확인
  		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 			
  			mav.addObject("redirect_url", "/member/login.do");
  			mav.addObject("setKey", setKey(session, request));
  	 		mav.setViewName("/redirectPage");
  	 		return mav;
  		}
  		
  		//세션 권한 확인
  		if(session.getAttribute("user_auth_id") == null || ! session.getAttribute("user_auth_id").equals("40")){ 			
  			mav.addObject("redirect_url", "/main.do");
 			mav.setViewName("/redirectPage");
  			return mav;
  		}
	
  		try{

  	 		int result = this.dataRoomService.delete(dataRoomVo);
  	 		logger.info(" 삭제 : " + result);
  		}catch(Exception e){
  		//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
  		}
 		
 		// 보여질 View 페이지를 설정한다.		
 		mav.addObject("redirect_url", "/member/personalPop.do");
 		mav.setViewName("/redirectPage");
 		return mav;
   	}	
	//2019.11.15 sha256 암호로직 추가
	public static String encryptSHA256(String value) throws NoSuchAlgorithmException{
	     String encryptData = "";
	     MessageDigest sha = MessageDigest.getInstance("SHA-256");	     
	     sha.update(value.getBytes());	     
	     byte[] digest = sha.digest();	     
	     for (int i=0; i<digest.length; i++) {
	    	 encryptData += Integer.toHexString(digest[i] & 0xFF).toUpperCase();
	     }	     
	     return encryptData;
	 }
	//2019.12.06 RSA 암호화 추가
	/**
	 * 대상을 공개키로 암호화 처리
	 * 
	 * @param rsa_user_pw sPublicKey
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String rsa_user_pw, String sPublicKey) throws Exception {		
		
		byte[] bPublicKey2 = Base64.decodeBase64(sPublicKey.getBytes());
		KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");

		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bPublicKey2);
		PublicKey publicKey = keyFactory2.generatePublic(publicKeySpec);

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

		// 공개키 이용 암호화
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] bCipher = cipher.doFinal(rsa_user_pw.getBytes());
		String sCipherBase64 = Base64.encodeBase64String(bCipher);

		return sCipherBase64;
	}
	
	//2019.12.06 RSA 복호화 추가
	/**
	 * 대상을 비공개키로 복호화 처리
	 * 
	 * @param rsa_user_pwEn sPrivateKey
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String rsa_user_pwEn, String sPrivateKey) throws Exception {		
		byte[] bPrivateKey = Base64.decodeBase64(sPrivateKey.getBytes());
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

		// 개인키 이용 복호화
		byte[] bCipher = Base64.decodeBase64(rsa_user_pwEn.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bPlain = cipher.doFinal(bCipher);
		String sPlain = new String(bPlain);

		return sPlain;
	}
	
	// setKey	
	@RequestMapping(value = "/member/setKey", method = { RequestMethod.POST })
	public JSONObject setKey(HttpSession session, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048); // 키 사이즈 - 1024, 2048
		
		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		// 개인 키 생성 후 세션에 저장
		if(session != null && request.isRequestedSessionIdValid()){
			logger.info( " ### Set key : session is not null " );
		}else{
			// 세션 재 생성
			session = request.getSession();
		}
		session.setAttribute("__rsaPrivateKey__", privateKey);
		// 공개키를 문자열로 변환
		RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
		
		// 로그인 폼 Input hidden 값 설정 
		JSONObject obj = new JSONObject();
		obj.put("publicKeyModulus", publicKeyModulus);
		obj.put("publicKeyExponent", publicKeyExponent);
		
		return obj;
	}
	
	private String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] encryptedBytes = hexToByteArray(securedValue);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 
        return decryptedValue;
    }
	
    // 16진수 문자열을 바이트 배열로 변환   
    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }
}
