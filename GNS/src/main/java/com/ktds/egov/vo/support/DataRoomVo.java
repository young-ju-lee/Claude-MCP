/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : DataRoomVo.java
* Overview    : 자료실 관리 VO
* Description : 자료실 관리 VO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.vo.support;

public class DataRoomVo {
	long seq;	
	String file_id;
	String file_gbn;
	String file_title;	
	String file_cont;
	String file_date;
	String file_name;
	String file_ext;
	String file_path;	
	String user_id;
	String new_yn;	
	
	String prev_file_id;
	String prev_file_gbn;
	String prev_file_title;
	String next_file_id;
	String next_file_gbn;	
	String next_file_title;
	long file_cnt;

	//페이징 관련 
	long   limit = 10; // 출력 글의 총 행수	
	long   offSet = 0;   // 출력 글의 위치
	String page = "1";  // 페이지 
			
	long   rowCnt;
	
	//검색 관련
	String search_gbn = "%"; //전체	
	String search_title = ""; //검색시 제목		
	String p_search_title = ""; //이전 검색 제목
	String p_search_gbn = ""; //이전 검색 구분	
	String search_yn = "N"; 
	
	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_gbn() {
		return file_gbn;
	}

	public void setFile_gbn(String file_gbn) {
		this.file_gbn = file_gbn;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getFile_cont() {
		return file_cont;
	}

	public void setFile_cont(String file_cont) {
		this.file_cont = file_cont;
	}

	public String getFile_date() {
		return file_date;
	}

	public void setFile_date(String file_date) {
		this.file_date = file_date;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_ext() {
		return file_ext;
	}

	public void setFile_ext(String file_ext) {
		this.file_ext = file_ext;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNew_yn() {
		return new_yn;
	}

	public void setNew_yn(String new_yn) {
		this.new_yn = new_yn;
	}

	public String getPrev_file_id() {
		return prev_file_id;
	}

	public void setPrev_file_id(String prev_file_id) {
		this.prev_file_id = prev_file_id;
	}

	public String getPrev_file_gbn() {
		return prev_file_gbn;
	}

	public void setPrev_file_gbn(String prev_file_gbn) {
		this.prev_file_gbn = prev_file_gbn;
	}

	public String getPrev_file_title() {
		return prev_file_title;
	}

	public void setPrev_file_title(String prev_file_title) {
		this.prev_file_title = prev_file_title;
	}

	public String getNext_file_id() {
		return next_file_id;
	}

	public void setNext_file_id(String next_file_id) {
		this.next_file_id = next_file_id;
	}

	public String getNext_file_gbn() {
		return next_file_gbn;
	}

	public void setNext_file_gbn(String next_file_gbn) {
		this.next_file_gbn = next_file_gbn;
	}

	public String getNext_file_title() {
		return next_file_title;
	}

	public void setNext_file_title(String next_file_title) {
		this.next_file_title = next_file_title;
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

	public long getRowCnt() {
		return rowCnt;
	}

	public void setRowCnt(long rowCnt) {
		this.rowCnt = rowCnt;
	}

	public String getSearch_gbn() {
		return search_gbn;
	}

	public void setSearch_gbn(String search_gbn) {
		this.search_gbn = search_gbn;
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

	public String getP_search_gbn() {
		return p_search_gbn;
	}

	public void setP_search_gbn(String p_search_gbn) {
		this.p_search_gbn = p_search_gbn;
	}

	public String getSearch_yn() {
		return search_yn;
	}

	public void setSearch_yn(String search_yn) {
		this.search_yn = search_yn;
	}

	public long getFile_cnt() {
		return file_cnt;
	}

	public void setFile_cnt(long file_cnt) {
		this.file_cnt = file_cnt;
	}		
}
