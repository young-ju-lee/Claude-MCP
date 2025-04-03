/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : MenuStateVo.java
* Overview    : 메뉴통계 관리 VO
* Description : 메뉴통계 관리 VO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.vo.member;

public class MenuStateVo {

	private String user_id;
	private String menu_id;
	
	private String search_type;	
	private String search_from;
	private String search_to;
	private String search_id;
	
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
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
	public String getSearch_id() {
		return search_id; 
	}
	public void setSearch_id(String search_id) {
		this.search_id = search_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	

}
