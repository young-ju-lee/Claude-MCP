/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : ChargeVo.java
* Overview    : 이용요금계산 화면 VO
* Description : 이용요금계산 화면 VO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.vo.charge;

public class ChargeVo {

	String svc_type;
	String svc_type_ename;			// 동적 쿼리를 위한 파라미터
	String speed;
	String cctv_speed;					// CCTV와 나머지의 SPEED파라미터 구분을 위한 추가
	String distance;
	String dist_code;
	String fee;
	long inst_fee;
	String distance_cd;
	long speed_cd;
	String addr;
	String co_korea;
	String bonbu;
	String icis_2;
	String uco;
	String lco;
	long dist_value;

	
	String fee_basis;					// CCTV 화면을위해 추가함. distance_cd 한글명 출력
	String contract_period;			// CCTV 화면을위해 추가함. dist_code 랑 동일


	String top_nat;
	String botton_nat;
	String dist_val;
	
	public String getTop_nat() {
		return top_nat;
	}
	public void setTop_nat(String top_nat) {
		this.top_nat = top_nat;
	}
	public String getBotton_nat() {
		return botton_nat;
	}
	public void setBotton_nat(String botton_nat) {
		this.botton_nat = botton_nat;
	}
	public String getDist_val() {
		return dist_val;
	}
	public void setDist_val(String dist_val) {
		this.dist_val = dist_val;
	}
	
	public String getSvc_type() {
		return svc_type;
	}
	public void setSvc_type(String svc_type) {
		this.svc_type = svc_type;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDist_code() {
		return dist_code;
	}
	public void setDist_code(String dist_code) {
		this.dist_code = dist_code;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public long getInst_fee() {
		return inst_fee;
	}
	public void setInst_fee(long inst_fee) {
		this.inst_fee = inst_fee;
	}
	
	public String getDistance_cd() {
		return distance_cd;
	}
	public void setDistance_cd(String distance_cd) {
		this.distance_cd = distance_cd;
	}
	public long getSpeed_cd() {
		return speed_cd;
	}
	public void setSpeed_cd(long speed_cd) {
		this.speed_cd = speed_cd;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCo_korea() {
		return co_korea;
	}
	public void setCo_korea(String co_korea) {
		this.co_korea = co_korea;
	}
	public String getBonbu() {
		return bonbu;
	}
	public void setBonbu(String bonbu) {
		this.bonbu = bonbu;
	}
	public String getIcis_2() {
		return icis_2;
	}
	public void setIcis_2(String icis_2) {
		this.icis_2 = icis_2;
	}
	public String getUco() {
		return uco;
	}
	public void setUco(String uco) {
		this.uco = uco;
	}
	public String getLco() {
		return lco;
	}
	public void setLco(String lco) {
		this.lco = lco;
	}
	public long getDist_value() {
		return dist_value;
	}
	public void setDist_value(long dist_value) {
		this.dist_value = dist_value;
	}
	public String getSvc_type_ename() {
		return svc_type_ename;
	}
	public void setSvc_type_ename(String svc_type_ename) {
		this.svc_type_ename = svc_type_ename;
	}
	public String getFee_basis() {
		return fee_basis;
	}
	public void setFee_basis(String fee_basis) {
		this.fee_basis = fee_basis;
	}
	public String getContract_period() {
		return contract_period;
	}
	public void setContract_period(String contract_period) {
		this.contract_period = contract_period;
	}
	public String getCctv_speed() {
		return cctv_speed;
	}
	public void setCctv_speed(String cctv_speed) {
		this.cctv_speed = cctv_speed;
	}
	
}
