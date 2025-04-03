/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : PopupVo.java
* Overview    : 팝업창 관리 VO
* Description : 팝업창 관리 VO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.vo.admin;

/***
 * 팝업창 관리 VO
 * @author hoonbyung
 *
 */
public class PopupVo {
	String pop_id = "";
	String pop_type = "";
	String pop_date = "";
	String pop_fr_date = "";
	String pop_to_date = "";
	String pop_title = "";
	String pop_cont = "";
	long pop_width = 0;
	long pop_height = 0;
	String pop_yn = "";
	String user_id = "";	
	
	//페이징 관련 
	long   limit = 10; // 출력 글의 총 행수	
	long   offSet = 0;   // 출력 글의 위치
	String page = "1";  // 페이지 
	
	long seq; //순번
	long row_cnt; //총행의 갯수
		
	String search_title = ""; //검색시 제목		
	String p_search_title = ""; //이전 검색 제목
	String search_yn = "N"; 
		
	public String getPop_id() {
		return pop_id;
	}
	public void setPop_id(String pop_id) {
		this.pop_id = pop_id;
	}
	public String getPop_type() {
		return pop_type;
	}
	public void setPop_type(String pop_type) {
		this.pop_type = pop_type;
	}
	public String getPop_date() {
		return pop_date;
	}
	public void setPop_date(String pop_date) {
		this.pop_date = pop_date;
	}
	public String getPop_fr_date() {
		return pop_fr_date;
	}
	public void setPop_fr_date(String pop_fr_date) {
		this.pop_fr_date = pop_fr_date;
	}
	public String getPop_to_date() {
		return pop_to_date;
	}
	public void setPop_to_date(String pop_to_date) {
		this.pop_to_date = pop_to_date;
	}
	public String getPop_title() {
		return pop_title;
	}
	public void setPop_title(String pop_title) {
		this.pop_title = pop_title;
	}
	public String getPop_cont() {
		return pop_cont;
	}
	public void setPop_cont(String pop_cont) {
		this.pop_cont = pop_cont;
	}
	public long getPop_width() {
		return pop_width;
	}
	public void setPop_width(long pop_width) {
		this.pop_width = pop_width;
	}
	public long getPop_height() {
		return pop_height;
	}
	public void setPop_height(long pop_height) {
		this.pop_height = pop_height;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}	
	public String getPop_yn() {
		return pop_yn;
	}
	public void setPop_yn(String pop_yn) {
		this.pop_yn = pop_yn;
	}
	
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
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
	public long getRow_cnt() {
		return row_cnt;
	}
	public void setRow_cnt(long row_cnt) {
		this.row_cnt = row_cnt;
	}
	public String getSearch_title() {
		return search_title;
	}
	public void setSearch_title(String search_title) {
		this.search_title = search_title;
	}
	public String getP_search_title() {
		return p_search_title;
	}
	public void setP_search_title(String p_search_title) {
		this.p_search_title = p_search_title;
	}
	
	public String getSearch_yn() {
		return search_yn;
	}
	public void setSearch_yn(String search_yn) {
		this.search_yn = search_yn;
	}
}
