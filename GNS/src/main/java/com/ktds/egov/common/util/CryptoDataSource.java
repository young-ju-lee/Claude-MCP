/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : CryptoDataSource.java
* Overview    : 64bit 암호화
* Description : 소스 내에 properties 파일 비밀번호를 64bit로 암호화한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.common.util;

import org.apache.commons.dbcp.BasicDataSource;

import com.ktds.egov.common.util.AES;


public class CryptoDataSource extends BasicDataSource {

	@Override
    public synchronized void setUrl(String url) {
        try {
        	super.setUrl(AES.decrypt(url));
		} catch (Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
    }
 
    @Override
    public void setUsername(String username) {
        try {
			super.setUsername(AES.decrypt(username));
		} catch (Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
    }
 
    @Override
    public void setPassword(String password) {
        try {
			super.setPassword(AES.decrypt(password));
		} catch (Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
    }  
}