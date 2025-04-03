/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : NoticeVo.java
* Overview    : 공지사항 관리 VO
* Description : 공지사항 관리 VO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.vo.support;

public class NoticeVo{
	
	String notice_id;
	String user_id;
	String notice_date;
	String notice_title;
	String notice_cont;
	String notice_ord;
	String new_yn;	
	long   notice_cnt;
	long   row_cnt;
	
	long   seq;
	
	long limit=10;
	
	long offSet=0;
	String search_yn="N";
	String search_title="";
	String p_search_title="";
	String page= "1";
		
	
	String prev_notice_id;
	String prev_notice_title;
	String next_notice_id;
	String next_notice_title;
	public String getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_cont() {
		return notice_cont;
	}
	public void setNotice_cont(String notice_cont) {
		this.notice_cont = notice_cont;
	}
	public String getNotice_ord() {
		return notice_ord;
	}
	public void setNotice_ord(String notice_ord) {
		this.notice_ord = notice_ord;
	}
	public String getNew_yn() {
		return new_yn;
	}
	public void setNew_yn(String new_yn) {
		this.new_yn = new_yn;
	}
	public long getNotice_cnt() {
		return notice_cnt;
	}
	public void setNotice_cnt(long notice_cnt) {
		this.notice_cnt = notice_cnt;
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
	public String getSearch_yn() {
		return search_yn;
	}
	public void setSearch_yn(String search_yn) {
		this.search_yn = search_yn;
	}
	public String getSearch_title() {
		return search_title;
	}
	public void setSearch_title(String search_title) {
		this.search_title = search_title;
	}
	
	public String getPrev_notice_id() {
		return prev_notice_id;
	}
	public void setPrev_notice_id(String prev_notice_id) {
		this.prev_notice_id = prev_notice_id;
	}
	public String getPrev_notice_title() {
		return prev_notice_title;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public void setPrev_notice_title(String prev_notice_title) {
		this.prev_notice_title = prev_notice_title;
	}
	public String getNext_notice_id() {
		return next_notice_id;
	}
	public void setNext_notice_id(String next_notice_id) {
		this.next_notice_id = next_notice_id;
	}
	public String getNext_notice_title() {
		return next_notice_title;
	}
	public void setNext_notice_title(String next_notice_title) {
		this.next_notice_title = next_notice_title;
	}
	
	public String getP_search_title() {
		return p_search_title;
	}
	public void setP_search_title(String p_search_title) {
		this.p_search_title = p_search_title;
	}
	
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	
}
