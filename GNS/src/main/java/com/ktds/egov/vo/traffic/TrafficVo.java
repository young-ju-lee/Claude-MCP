/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : TrafficVo.java
* Overview    : 트래픽 관리 VO
* Description : 트래픽 관리 VO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.vo.traffic;

public class TrafficVo {

	String cust_name; //고객명 
	String nat_name;  //국가기관명
	String llnum; //전용회선번호
	String sa_id; //계약번호
	String ord_type_cd;
	String sa_dtl_cd; //상세상품코드
	String sa_cd; //상품코드 
	String line_spec_cd; //통신속도
	String svc_distance; //서비스거리
	String u_exch_ofc_cd; //상위국코드
	String l_exch_ofc_cd; //하위국코드
	
	
	String collect_time; //수집일시    
	
	long band; //band width 속도
	
   
	long inbps; //입력 
    long outbps; //출력
   
    long per_inbps; //사용률 
    long per_outbps; //사용률
    
    String max_in_hour; //최번 시
    String max_in_day; //최번 일
    
    String max_out_hour; //최번 시
    String max_out_day; //최번 일
    
	String search_llnum; //조회시 전용회선번호
	//<!-- 2018.12.21 admin 전체회선 조회 쿼리 추가  -->
	String search_llnum_admin; //admin회선조회 시 이용
	String search_nat;   //조회시 망운영국
    String researchflag; //재조회구분

	String traffic_type; //조회조건 
    String search_from;  //조회조건 시작일자
    String search_to;    //조회조건 종료일자
    
    String nealias;
    String nescode;
    
    long   limit = 30; // 출력 글의 총 행수	
	long   offSet = 0;   // 출력 글의 위치
	String page = "1";
	String gubun;
	
	
    
	
    public String getOrd_type_cd() {
		return ord_type_cd;
	}
	public void setOrd_type_cd(String ord_type_cd) {
		this.ord_type_cd = ord_type_cd;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
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
	public String getResearchflag() {
		return researchflag;
	}
	public void setResearchflag(String researchflag) {
		this.researchflag = researchflag;
	}
	public String getTraffic_type() {
		return traffic_type;
	}
	public void setTraffic_type(String traffic_type) {
		this.traffic_type = traffic_type;
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
    
    public String getSearch_nat() {
		return search_nat;
	}
	public void setSearch_nat(String search_nat) {
		this.search_nat = search_nat;
	}	    	
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getNat_name() {
		return nat_name;
	}
	public void setNat_name(String nat_name) {
		this.nat_name = nat_name;
	}

	public String getLlnum() {
		return llnum;
	}
	public void setLlnum(String llnum) {
		this.llnum = llnum;
	}
	public String getSa_id() {
		return sa_id;
	}
	public void setSa_id(String sa_id) {
		this.sa_id = sa_id;
	}
	public String getSa_dtl_cd() {
		return sa_dtl_cd;
	}
	public void setSa_dtl_cd(String sa_dtl_cd) {
		this.sa_dtl_cd = sa_dtl_cd;
	}
	public String getSa_cd() {
		return sa_cd;
	}
	public void setSa_cd(String sa_cd) {
		this.sa_cd = sa_cd;
	}
	public String getLine_spec_cd() {
		return line_spec_cd;
	}
	public void setLine_spec_cd(String line_spec_cd) {
		this.line_spec_cd = line_spec_cd;
	}
	public String getSvc_distance() {
		return svc_distance;
	}
	public void setSvc_distance(String svc_distance) {
		this.svc_distance = svc_distance;
	}
	public String getU_exch_ofc_cd() {
		return u_exch_ofc_cd;
	}
	public void setU_exch_ofc_cd(String u_exch_ofc_cd) {
		this.u_exch_ofc_cd = u_exch_ofc_cd;
	}
	public String getL_exch_ofc_cd() {
		return l_exch_ofc_cd;
	}
	public void setL_exch_ofc_cd(String l_exch_ofc_cd) {
		this.l_exch_ofc_cd = l_exch_ofc_cd;
	}
	public String getCollect_time() {
		return collect_time;
	}
	public void setCollect_time(String collect_time) {
		this.collect_time = collect_time;
	}
	public long getInbps() {
		return inbps;
	}
	public void setInbps(long inbps) {
		this.inbps = inbps;
	}
	public long getOutbps() {
		return outbps;
	}
	public void setOutbps(long outbps) {
		this.outbps = outbps;
	}
	public String getSearch_llnum() {
		return search_llnum;
	}
	public void setSearch_llnum(String search_llnum) {
		this.search_llnum = search_llnum;
	}
	public String getSearch_llnum_admin() {
		return search_llnum_admin;
	}
	public void setSearch_llnum_admin(String search_llnum_admin) {
		this.search_llnum_admin = search_llnum_admin;
	}
	public String getNealias() {
		return nealias;
	}
	public void setNealias(String nealias) {
		this.nealias = nealias;
	}
	public String getNescode() {
		return nescode;
	}
	public void setNescode(String nescode) {
		this.nescode = nescode;
	}
	public long getBand() {
			return band;
	}
	public void setBand(long band) {
		this.band = band;
	}		
	public long getPer_inbps() {
		return per_inbps;
	}
	public void setPer_inbps(long per_inbps) {
		this.per_inbps = per_inbps;
	}
	public long getPer_outbps() {
		return per_outbps;
	}
	public void setPer_outbps(long per_outbps) {
		this.per_outbps = per_outbps;
	}
	public String getMax_in_hour() {
		return max_in_hour;
	}
	public void setMax_in_hour(String max_in_hour) {
		this.max_in_hour = max_in_hour;
	}
	public String getMax_in_day() {
		return max_in_day;
	}
	public void setMax_in_day(String max_in_day) {
		this.max_in_day = max_in_day;
	}
	public String getMax_out_hour() {
		return max_out_hour;
	}
	public void setMax_out_hour(String max_out_hour) {
		this.max_out_hour = max_out_hour;
	}
	public String getMax_out_day() {
		return max_out_day;
	}
	public void setMax_out_day(String max_out_day) {
		this.max_out_day = max_out_day;
	}

}
