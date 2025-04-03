/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : MemberVo.java
* Overview    : 회원정보 관리 VO
* Description : 회원정보 관리 VO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.vo.member;

public class MemberVo {
	String user_nm;
	String user_id;
	String user_id1;         //<!-- 2018.03.23 보안성검토 로그 센터 연동 추가 -->        
	String user_id2;         //<!-- 2018.03.23 보안성검토 로그 센터 연동 추가 -->  
	String user_id3;         //<!-- 2018.03.23 보안성검토 로그 센터 연동 추가 -->  
	String user_approval_id; //<!-- 2018.01.19  보안성검토 권한부여자 추가 -->
	String event_code;       //<!-- 2018.01.19  보안성검토 권한 이벤트추가 --> 
	String user_pw;
	String user_pw2;         //<!-- 2018.07.20 salt 암호화 추가 기존pw+salt 사용위한 변수 추가 -->
	String user_mail;	
	String user_zipcd;
	String user_addr1;
	String user_addr2;
	String tel1;
	String tel2;
	String tel3;
	String user_phone;
	String mobile1;
	String mobile2;
	String mobile3;
	String user_mobile;
	String ll_id;
	String user_auth_id;
	String user_stat;
	String inst_date;
	String updt_date;
	String user_before;
	String user_auth;
	String seq;	
	String init_yn;
	String pw_updt_date; //<!-- 2017.12.22  보안성검토 비번 업뎅트 날짜 추가 -->
	String ucheck1;     //<!-- 2017.12.22  보안성검토 필수동의1 -->
	String ucheck2;     //<!-- 2017.12.22  보안성검토 필수동의2 --> 
	String ucheck3;     //<!-- 2017.12.22  보안성검토 선택동의1 -->
	String menu_name;   //<!-- 2018.02.23  보안성검토 개인정보로그저장 메뉴명 컬럼 추가 --> 
	String multi_log_type; //<!-- 2018.03.23  보안성검토 개인정보로그저장 단건,복수건체크 컬럼 추가 -->
	String multi_log_param;//<!-- 2018.03.23 보안성검토 로그 센터 연동 추가 -->  
	String old_pwd;
	String new_pwd;
	String new_c_pwd;
	String salt;   //<!-- 2018.07.20 salt 암호화 추가 -->
	String session_id; //<!--2019.04.26 중복로그인 체크를 위한 session_id추가 -->
	//<!-- 2019.11.15 메일인증 & 쿠키 컬럼 추가 저장 -->
	String mail_code;
	String host;
	String port;
	String admin_mail;
	String  subject;
	String  content;
	String mail_flag;
	String mail_yn;	
	String userAgent;
	String coki_salt;
	String cokiKey;
	String cokiKeyEn;
	String coki_saltEn;
	String rsaOpenKey;
	String rsaSecretKey;
	
	long   row_cnt;
	
    long limit=10;    //출력 행의 갯수
	long offSet = 0;  // 출력 행의 위치
    String page = "1"; //페이징 위치 	
  
	String search_yn = "N";
	String search_group = "";
    String search_value = "";
    String p_search_group = "";
    String p_search_value = "";
    
    // 가입회선정보
    String cust_nm;
	String llnum;
    String app_nat_name;
    String reqer_name;
    String cntc_tel_no;
    
    String sa_id;
    String logic_port_id;
    
    // login 정보수집
    String ip;
    String siteGubun;
    
    long login_fail_cnt;
    long login_fail_time;
    String unlock_yn;
    
    public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public String getCoki_salt() {
		return coki_salt;
	}
	public void setCoki_salt(String coki_salt) {
		this.coki_salt = coki_salt;
	}
	
	public String getCokiKey() {
		return cokiKey;
	}
	public void setCokiKey(String cokiKey) {
		this.cokiKey = cokiKey;
	}
	
	public String getCokiKeyEn() {
		return cokiKeyEn;
	}
	public void setCokiKeyEn(String cokiKeyEn) {
		this.cokiKeyEn = cokiKeyEn;
	}
	
	public String getrsaSecretKey() {
		return rsaSecretKey;
	}
	public void setRsaSecretKey(String rsaSecretKey) {
		this.rsaSecretKey = rsaSecretKey;
	}
	public String getrsaOpenKey() {
		return rsaOpenKey;
	}
	public void setRsaOpenKey(String rsaOpenKey) {
		this.rsaOpenKey = rsaOpenKey;
	}
	public String getcoki_saltEn() {
		return coki_saltEn;
	}
	public void setCoki_saltEn(String coki_saltEn) {
		this.coki_saltEn = coki_saltEn;
	}

	public String getUnlock_yn() {
		return unlock_yn;
	}
	public void setUnlock_yn(String unlock_yn) {
		this.unlock_yn = unlock_yn;
	}
	public long getLogin_fail_cnt() {
		return login_fail_cnt;
	}
	public void setLogin_fail_cnt(long login_fail_cnt) {
		this.login_fail_cnt = login_fail_cnt;
	}
	public long getLogin_fail_time() {
		return login_fail_time;
	}
	public void setLogin_fail_time(long login_fail_time) {
		this.login_fail_time = login_fail_time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSiteGubun() {
		return siteGubun;
	}
	public void setSiteGubun(String siteGubun) {
		this.siteGubun = siteGubun;
	}
	
	public String getOld_pwd() {
		return old_pwd;
	}
	public void setOld_pwd(String old_pwd) {
		this.old_pwd = old_pwd;
	}
	public String getNew_pwd() {
		return new_pwd;
	}
	public void setNew_pwd(String new_pwd) {
		this.new_pwd = new_pwd;
	}
	public String getNew_c_pwd() {
		return new_c_pwd;
	}
	public void setNew_c_pwd(String new_c_pwd) {
		this.new_c_pwd = new_c_pwd;
	}
	public String getSa_id() {
		return sa_id;
	}
	public void setSa_id(String sa_id) {
		this.sa_id = sa_id;
	}
	public String getLogic_port_id() {
		return logic_port_id;
	}
	public void setLogic_port_id(String logic_port_id) {
		this.logic_port_id = logic_port_id;
	}
	public String getCust_nm() {
		return cust_nm;
	}
	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
	}
	public String getLlnum() {
		return llnum;
	}
	public void setLlnum(String llnum) {
		this.llnum = llnum;
	}
	public String getApp_nat_name() {
		return app_nat_name;
	}
	public void setApp_nat_name(String app_nat_name) {
		this.app_nat_name = app_nat_name;
	}
	public String getReqer_name() {
		return reqer_name;
	}
	public void setReqer_name(String reqer_name) {
		this.reqer_name = reqer_name;
	}
	public String getCntc_tel_no() {
		return cntc_tel_no;
	}
	public void setCntc_tel_no(String cntc_tel_no) {
		this.cntc_tel_no = cntc_tel_no;
	}
	
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	//<!-- 2018.03.23 보안성검토 로그 센터 연동 추가 -->  
	public String getUser_id1() {
		return user_id1;
	}
	public void setUser_id1(String user_id1) {
		this.user_id1 = user_id1;
	}
	public String getUser_id2() {
		return user_id2;
	}
	public void setUser_id2(String user_id2) {
		this.user_id2 = user_id2;
	}
	public String getUser_id3() {
		return user_id3;
	}
	public void setUser_id3(String user_id3) {
		this.user_id3 = user_id3;
	}
	//<!-- 2018.01.19  보안성검토 권한부여자 추가 -->
	public String getUser_approval_id() {
		return user_approval_id;
	}
	public void setUser_approval_id(String user_approval_id) {
		this.user_approval_id = user_approval_id;
	}
	//<!-- 2018.01.19  보안성검토 권한부여자 추가 -->
		public String getEvent_code() {
			return event_code;
		}
		public void setEvent_Code(String event_code) {
			this.event_code = event_code;
		}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	//<!-- 2018.07.20 salt 암호화 추가 기존pw+salt 사용위한 변수 추가 -->
	public String getUser_pw2() {
		return user_pw2;
	}
	public void setUser_pw2(String user_pw2) {
		this.user_pw2 = user_pw2;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getUser_zipcd() {
		return user_zipcd;
	}
	public void setUser_zipcd(String user_zipcd) {
		this.user_zipcd = user_zipcd;
	}
	public String getUser_addr1() {
		return user_addr1;
	}
	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}
	public String getUser_addr2() {
		return user_addr2;
	}
	public void setUser_addr2(String user_addr2) {
		this.user_addr2 = user_addr2;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getMail_code() {
		return mail_code;
	}
	public void setMail_code(String mail_code) {
		this.mail_code = mail_code;
	}
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getAdmin_mail() {
		return admin_mail;
	}
	public void setAdmin_mail(String admin_mail) {
		this.admin_mail = admin_mail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setMail_flag(String mail_flag) {
		this.mail_flag = mail_flag;
	}
	public String getMail_flag() {
		return mail_flag;
	}
	public void setMail_yn(String mail_yn) {
		this.mail_yn = mail_yn;
	}
	public String getMail_yn() {
		return mail_yn;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getLl_id() {
		return ll_id;
		/*
		if(isBlank(ll_id))
		{
			return null;
		} else {
			return ll_id;
		}
		*/
		
	}
	public void setLl_id(String ll_id) {
		this.ll_id = ll_id;
	}
	public String getUser_auth_id() {
		return user_auth_id;
	}
	public void setUser_auth_id(String user_auth_id) {
		this.user_auth_id = user_auth_id;
	}
	public String getUser_stat() {
		return user_stat;
	}
	public void setUser_stat(String user_stat) {
		this.user_stat = user_stat;
	}
	public String getInst_date() {
		return inst_date;
	}
	public void setInst_date(String inst_date) {
		this.inst_date = inst_date;
	}
	public String getUpdt_date() {
		return updt_date;
	}
	public void setUpdt_date(String updt_date) {
		this.updt_date = updt_date;
	}
	public String getUser_before() {
		return user_before;
	}
	public void setUser_before(String user_before) {
		this.user_before = user_before;
	}
	public String getUser_auth() {
		return user_auth;
	}
	public void setUser_auth(String user_auth) {
		this.user_auth = user_auth;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public long getRow_cnt() {
		return row_cnt;
	}
	public void setRow_cnt(long row_cnt) {
		this.row_cnt = row_cnt;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public long getOffSet() {
		return offSet;
	}
	public void setOffSet(long offSet) {
		this.offSet = offSet;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSearch_yn() {
		return search_yn;
	}
	public void setSearch_yn(String search_yn) {
		this.search_yn = search_yn;
	}
	public String getSearch_group() {
		return search_group;
	}
	public void setSearch_group(String search_group) {
		this.search_group = search_group;
	}
	public String getSearch_value() {
		return search_value;
	}
	public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}

	public String getP_search_group() {
		return p_search_group;
	}
	public void setP_search_group(String p_search_group) {
		this.p_search_group = p_search_group;
	}
	public String getP_search_value() {
		return p_search_value;
	}
	public void setP_search_value(String p_search_value) {
		this.p_search_value = p_search_value;
	}
	public String getInit_yn() {
			return init_yn;
	}
	public void setInit_yn(String init_yn) {
		this.init_yn = init_yn;
	}
	//<!-- 2017.12.22  보안성검토 비번 업뎅트 날짜 추가 -->
		public String getPw_updt_date() {
			return pw_updt_date;
	}
	public void setPw_updt_date(String pw_updt_date) {
		this.pw_updt_date = pw_updt_date;
	}
	//<!-- 2017.12.22  보안성검토 필수동의1 -->
	public String getUcheck1() {
		return ucheck1;
	}
	public void setUcheck1(String ucheck1) {
		this.ucheck1 = ucheck1;
	}
	//<!-- 2017.12.22  보안성검토 필수동의2 -->
	public String getUcheck2() {
		return ucheck2;
	}
	public void setUcheck2(String ucheck2) {
		this.ucheck2 = ucheck2;
	}
	//<!-- 2017.12.22  보안성검토 선택동의1 -->
	public String getUcheck3() {
		return ucheck3;
	}
	public void setUcheck3(String ucheck3) {
		this.ucheck3 = ucheck3;
	}
	//<!-- 2018.02.23  보안성검토 개인정보로그 저장 메뉴명 컬럼 추가 -->
		public String getMenu_name() {
			return menu_name;
		}
		public void setMenu_name(String menu_name) {
			this.menu_name = menu_name;
		}	
	//<!-- 2018.03.23  보안성검토 개인정보로그 저장 로우데이터체크 컬럼 추가 -->
			public String getMulti_log_type() {
				return multi_log_type;
			}
			public void setMulti_log_type(String multi_log_type) {
				this.multi_log_type = multi_log_type;
			}
			//<!-- 2018.03.23  보안성검토 개인정보로그 저장 검색어 컬럼 추가 -->
			public String getMulti_log_param() {
				return multi_log_param;
			}
			public void setMulti_log_param(String multi_log_param) {
				this.multi_log_param = multi_log_param;
			}
	
		//<!-- 2018.07.20  salt 암호화 처리 slat 컬럼 추가 -->
		public String getSalt() {
			return salt;
		}
		public void setSalt(String salt) {
			this.salt = salt;
		}		
		//<!-- 2019.04.26  중복로그인 체크를 위한 session_id 컬럼 추가 -->
				public String getSession_id() {
					return session_id;
				}
				public void setSession_id(String session_id) {
					this.session_id = session_id;
				}			
	public boolean isBlank(String str) {
		int strLen;
		boolean result = true;
		if(str == null || str.length() ==0) {
			result = true;
		} else {
			strLen = str.length();
			for (int i=0; i<strLen; i++) {
				if(Character.isWhitespace(str.charAt(i))) {
					result = false;
					
					break;
				}
			}
		}
		return result;
	}
}
