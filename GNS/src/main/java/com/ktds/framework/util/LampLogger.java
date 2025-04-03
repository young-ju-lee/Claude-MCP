/* POR version 1.0
 * Copyright @ 2013 kt Inc. All rights reserved.
 * This is a proprietary software of kt Inc, and you may not use this file except in compliance
 * with license agreement with kt Inc. Any redistribution or use of this software, with or without
 * modification shall be strictly prohibited without prior written approval of kt Inc, and
 * the copyright notice above does not evidence any actual or intended publication of such software.
 *
 * File Name   : LampLogger.java
 * Overview    : Lamp용 로그 처리 
 * Description : Lamp용 로그 처리 util 을 제공한다.
 * *=====================================================================
 * Version    변경  일자       개발자               설명
 * ---------- ---------- --------- --------------------------------------
 * 1.0.0.0    2022/10/21       이준영            신규개발
 *=====================================================================
 */

package com.ktds.framework.util;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.egov.controller.member.MemberController;
import com.ktds.egov.vo.member.MemberVo;

/**
 * LampLogger LAMP Logging 관련 기능.
 */
public class LampLogger {
	protected final Logger logger = LogManager.getLogger(MemberController.class);
	protected final String serviceCode = "OG051201";
	protected final String serviceDomain = "https://gns.kt.com";
	protected final String fileName = "/transaction.log";
	//protected final String fileLocation = "c:/test/home/gnslamp"; 
	protected final String fileLocation = "/home/gnslamp";  
	
	/**
	 * makeLoginLog : login시 log 남김 - 분기 단순화 용으로 별도 구성
	 */
	public void makeLoginLog(HttpServletRequest request, MemberVo memberVo, String uuid) {
		// init ( UUID / serverIp / sysName )
		String serverIp = getServerIp();
		String sysName = getSysName(serverIp);

		String clientIp = Global.getClientIp(request);
		
		// log json 생성
		JSONObject mainObj = new JSONObject();
		// 시간 설정
		mainObj.put("timestamp", getDateNow());
		mainObj.put("service", serviceCode);
		mainObj.put("operation", "LOGIN");
		mainObj.put("transactionId", serviceCode + "-" + uuid);
		mainObj.put("logType", "IN_REQ");
		// host
		JSONObject subObj = new JSONObject();
		subObj.put("name", sysName);
		subObj.put("ip", serverIp);
		mainObj.put("host", subObj);
		// response
		subObj = new JSONObject();
		subObj.put("type", "I");
		subObj.put("duration", "0.0");
		mainObj.put("response", subObj);
		// user
		subObj = new JSONObject();
		subObj.put("id", memberVo.getUser_id());
		subObj.put("ip", clientIp);
		subObj.put("agent", request.getHeader("User-Agent"));
		mainObj.put("user", subObj);
		// destination
		subObj = new JSONObject();
		subObj.put("name", sysName);
		subObj.put("ip", serverIp);
		mainObj.put("destination", subObj);
		// url
		mainObj.put("url", request.getRequestURL().toString());
		// serviceDomain
		mainObj.put("serviceDomain", serviceDomain);
		// security
		subObj = new JSONObject();
		subObj.put("type", "ACCESS");
		subObj.put("event", "LOGIN");
		mainObj.put("security", subObj);
		
		File Folder = new File(fileLocation);
		
		if(!Folder.exists()){
			try {
				Folder.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation + fileName, true), "utf-8"));
			//FileWriter file = new FileWriter(fileLocation, true);
			// file.write(LogString);
			file.write(mainObj.toString());
			file.write("\r\n");
			file.flush();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("LampLogger : LOGIN log 생성 : IN_REQ");
	}
	
	
	/**
	 * 로그인 :	LOGIN 	| type : ACCESS | target : null
	 * 로그아웃 :	LOGOUT  | type : ACCESS | target : null
	 * 회원가입 :	CREATE  | type : UPIP 	| target : ID
	 * 이메일인증 :	EMAIL  	| type : AUTH 	| target : EMAIL
	 * 회원탈퇴 :	DELETE	| type : UPIP	| target : ID
	 */
	public void makeLog(HttpServletRequest request, HttpSession session, JSONObject lampFlagObj) {
		// 걸린 시간 계산 : 로직 마지막에 마지막에 호출함으로 완료 시간 계산
		Long ltEnd = System.currentTimeMillis();
		String ltTime = ((ltEnd - lampFlagObj.getDouble("ltStart"))/1000.0) + "";
		
		// init ( UUID / serverIp / sysName )
		String uuid = lampFlagObj.getString("uuid");
		String serverIp = getServerIp();
		String sysName = getSysName(serverIp);
		String targetName = lampFlagObj.getString("targetName");
		String sType = "";
		String sTarget = "";
		String personalInfoList = "";
		String authName = "";
		String detailMsg = "";
		String userId = "#NULL";
		
		String clientIp = Global.getClientIp(request);
		
		try {
			// object -> session 순으로 check
			Object userCheck = lampFlagObj.get("userId");
			if(userCheck != null){
				userId = lampFlagObj.getString("userId");
			}
			
			if(session != null && request.isRequestedSessionIdValid()){
				if(session.getAttribute("user_id") != null && userId == "#NULL"){
					userId = session.getAttribute("user_id").toString();
				}
				authName = getAuthName(session);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(targetName == "LOGIN" || targetName == "LOGOUT"){
			sType = "ACCESS";
			
		}else if(targetName == "CREATE" || targetName == "DELETE"){
			sType = "UPIP";
			sTarget = userId;
			if(targetName == "CREATE"){
				personalInfoList =	"user_nm : " + lampFlagObj.getString("userNm") + 
									", user_id : " + lampFlagObj.getString("userId") + 
									", user_pw, user_mail : " + lampFlagObj.getString("userMail") + 
									", tel : " + lampFlagObj.getString("userTel") + 
									", mobile : " + lampFlagObj.getString("userMobile");
				detailMsg = "회원가입";
				authName = "일반회원";
			}else{
				personalInfoList = "user_id : " + lampFlagObj.getString("userId") + ", user_pw";
				detailMsg = "계정삭제";
			}
		}else if(targetName == "EMAIL"){
			sType = "AUTH";
			sTarget = lampFlagObj.getString("userEmail");
			personalInfoList = "user_mail : " + lampFlagObj.getString("userEmail");
			detailMsg = "본인확인";
		}
		
		// log json 생성
		JSONObject mainObj = new JSONObject();
		// 시간 설정
		mainObj.put("timestamp", getDateNow());
		mainObj.put("service", serviceCode);
		mainObj.put("operation", targetName);
		mainObj.put("transactionId", serviceCode + "-" + uuid);
		mainObj.put("logType", lampFlagObj.getString("logType"));
		// host
		JSONObject subObj = new JSONObject();
		subObj.put("name", sysName);
		subObj.put("ip", serverIp);
		mainObj.put("host", subObj);
		// response
		subObj = new JSONObject();
		if(lampFlagObj.getString("reType") == "I"){
			subObj.put("type", "I");
			subObj.put("duration", ltTime);
		} else if(lampFlagObj.getString("reType") == "S"){
			subObj.put("type", "S");
			subObj.put("code", "SystemError");
			subObj.put("desc", lampFlagObj.getString("reMsg"));
			subObj.put("duration", ltTime);
		} else {
			subObj.put("type", "E");
			subObj.put("code", "BusinessError");
			subObj.put("desc", lampFlagObj.getString("reMsg"));
			subObj.put("duration", ltTime);
		}
		mainObj.put("response", subObj);
		
		// user
		subObj = new JSONObject();
		subObj.put("id", userId);
		subObj.put("ip", clientIp);
		subObj.put("type", authName);
		subObj.put("agent", request.getHeader("User-Agent"));
		mainObj.put("user", subObj);
		// destination
		subObj = new JSONObject();
		subObj.put("name", sysName);
		subObj.put("ip", serverIp);
		mainObj.put("destination", subObj);
		// url
		mainObj.put("url", request.getRequestURL().toString());
		// serviceDomain
		mainObj.put("serviceDomain", serviceDomain);
		// security
		subObj = new JSONObject();
		subObj.put("type", sType);
		subObj.put("event", targetName);
		// #TODO 분기 부분
		/*
		 * subObj.put("target", ""); subObj.put("readOther", "");
		 */
		if(sType != "ACCESS"){
			subObj.put("detail", detailMsg);
			subObj.put("target", sTarget);
			subObj.put("personalInfoList", personalInfoList);
		}
		mainObj.put("security", subObj);
		
		File Folder = new File(fileLocation);
		
		if(!Folder.exists()){
			try {
				Folder.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation + fileName, true), "utf-8"));
			//FileWriter file = new FileWriter(fileLocation, true);
			// file.write(LogString);
			file.write(mainObj.toString());
			file.write("\r\n");
			file.flush();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("LampLogger : " + targetName + " log 생성 : " + lampFlagObj.getString("logType"));
	}
	
	public String getDateNow() {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String formatedNow = formatter.format(now);

		return formatedNow;
	};

	public String getSysName(String serverIp) {
		// 시스템 명
		String sysName = "gns20ap";
		if (serverIp.equals("10.217.37.170")) {
			sysName = "c-gnsim-td1-w01";
		} else if (serverIp.equals("10.217.38.205")) {
			sysName = "c-gnsim-tk1-a02";
		} else if (serverIp.equals("14.63.147.25")) {
			sysName = "c-gnsim-pd1-w03";
		} else if (serverIp.equals("14.63.147.23")) {
			sysName = "c-gnsim-pd1-w02";
		} else if (serverIp.equals("10.220.177.67")) {
			sysName = "gns20ap1";
		} else if (serverIp.equals("10.220.177.66")) {
			sysName = "gns20ap2";
		}

		return sysName;
	};

	public String getServerIp() {
		InetAddress local;
		String serverIp = "";
		try {
			local = InetAddress.getLocalHost();
			serverIp = local.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serverIp;
	};

	public String getAuthName(HttpSession session) {
		// 권한
		String authName = "";
		try {
			String user_auth_id = "";
			if(session.getAttribute("user_auth_id") != null){
				user_auth_id = session.getAttribute("user_auth_id").toString();
			}
			if (user_auth_id.equals("40")) {
				authName = "전체관리자";
			} else if (user_auth_id.equals("30")) {
				authName = "상담관리자";
			} else if (user_auth_id.equals("20")) {
				authName = "전용회원";
			} else if (user_auth_id.equals("10")) {
				authName = "일반회원";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return authName;

	}
}
