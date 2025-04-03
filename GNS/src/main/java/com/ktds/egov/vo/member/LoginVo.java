/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : LoginVo.java
* Overview    : 로그인, 회원 관리 관련 VO
* Description : 로그인, 회원 관리 관련 VO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.vo.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/***
 * 접속자통계 VO
 * @author 
 *
 */
@Entity
public class LoginVo {
	/**
	 * 
	 */
	String search_type = "";
	String search_id = "";
	long   login_cnt = 0;
	long   tot_cnt   = 0;
	float  login_psen = 0;
	
	
	long seq; //순번
	long row_cnt; //총행의 갯수
	
	String search_from ="";
	String search_to ="";
	
	public String getSearch_from() {
		return search_from;
	}
	public void setSearch_from(String search_from) {
		this.search_from = search_from;
	}
	public String getSearch_to() {
		return search_to;
	}
	public void setSearch_to(String search_to) {
		this.search_to = search_to;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}

	public String getSearch_id() {
		return search_id;
	}
	public void setSearch_id(String search_id) {
		this.search_id = search_id;
	}
	public long getLogin_cnt() {
		return login_cnt;
	}
	public void setLogin_cnt(long login_cnt) {
		this.login_cnt = login_cnt;
	}
	public long getTot_cnt() {
		return tot_cnt;
	}
	public void setTot_cnt(long tot_cnt) {
		this.tot_cnt = tot_cnt;
	}
	public float getLogin_psen() {
		return login_psen;
	}
	public void setLogin_psen(float login_psen) {
		this.login_psen = login_psen;
	}
	
	

	
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	

	public long getRow_cnt() {
		return row_cnt;
	}
	public void setRow_cnt(long row_cnt) {
		this.row_cnt = row_cnt;
	}

}
