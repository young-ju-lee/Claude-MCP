/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : MenuStateVo.java
* Overview    : 메뉴접속 VO
* Description : 메뉴접속 VO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.vo.member;

/***
 * 메뉴별접속 VO
 * @author 
 *
 */
public class StatVo {
	String menu_id 	= "";
	String menu_nm = "";
	String menu_lvl1_nm = "";
	String menu_lvl2_nm = "";
	String menu_lvl3_nm = "";
	String menu_count = "";
	String use_yn = "";
	
	//페이징 관련 
	long   limit = 10; // 출력 글의 총 행수	
	long   offSet = 0;   // 출력 글의 위치
	String page = "1";  // 페이지 
	
	long seq; //순번
	long row_cnt; //총행의 갯수
		
	String search_title = ""; //검색시 제목		
	String p_search_title = ""; //이전 검색 제목
	String search_yn = "N"; 
		
	public String getMenu_count() {
		return menu_count;
	}
	public void setMenu_count(String menu_count) {
		this.menu_count = menu_count;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_nm() {
		return menu_nm;
	}
	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}
	public String getMenu_lvl1_nm() {
		return menu_lvl1_nm;
	}
	public void setMenu_lvl1_nm(String menu_lvl1_nm) {
		this.menu_lvl1_nm = menu_lvl1_nm;
	}
	public String getMenu_lvl2_nm() {
		return menu_lvl2_nm;
	}
	public void setMenu_lvl2_nm(String menu_lvl2_nm) {
		this.menu_lvl2_nm = menu_lvl2_nm;
	}
	public String getMenu_lvl3_nm() {
		return menu_lvl3_nm;
	}
	public void setMenu_lvl3_nm(String menu_lvl3_nm) {
		this.menu_lvl3_nm = menu_lvl3_nm;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
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
