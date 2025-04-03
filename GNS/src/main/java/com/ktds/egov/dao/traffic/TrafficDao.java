/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : TrafficDao.java
* Overview    : 추가서비스 트래픽 관련 DAO
* Description : 추가서비스 트래픽 관련 DAO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.dao.traffic;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.ktds.egov.vo.traffic.TrafficVo;
import com.ktds.framework.core.mvc.BaseDaoImpl;

@Repository("trafficDao")
public class TrafficDao extends BaseDaoImpl<TrafficVo>{
	
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 가입 회선번호 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> checkllnum(TrafficVo vo) throws SQLException {
		log.info("TrafficDao start!!");
		return getSqlSession().selectList("com.ktds.egov.traffic.checkllnum", vo);
	}
	
	/**
	 * 전용회선 가입자 회선번호 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectllnumList2(TrafficVo vo) throws SQLException {
		log.info("전용회선 가입자 회선번호 조회 ");
		return getSqlSession().selectList("com.ktds.egov.traffic.selectllnumList2", vo);
	}
	

	/**
	 * 관리자 회선번호 조회
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectllnumList3(TrafficVo vo) throws SQLException {		
		return getSqlSession().selectList("com.ktds.egov.traffic.selectllnumList3", vo);
	}
	
	/**
	 * <!-- 2018.12.21 admin 전체회선 조회 쿼리 추가  -->
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectllnumList_admin(TrafficVo vo) throws SQLException {		
		return getSqlSession().selectList("com.ktds.egov.traffic.selectllnumList_admin", vo);
	}
	
	/**
	 * 해당 가입자의 전체 전용회선번호 조회
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectAllLLnum(String llnum) throws SQLException {
		log.info(" 해당 가입자의 전체 전용회선번호 조회 ");
		return getSqlSession().selectList("com.ktds.egov.traffic.selectAllLLnum", llnum);
	}
	
	/**
	 * 해당 가입자의 전체 노드
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectAllNode() throws SQLException {
		log.info(" 해당 가입자의 전체 전용회선번호 조회 ");
		return getSqlSession().selectList("com.ktds.egov.traffic.selectAllNode");
	}
	
	
	/**
	 * 트래픽 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficRealList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficRealList", vo);
	}
	
	/**
	 * 트래픽 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficHourList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficHourList", vo);
	}
	
	/**
	 * 트래픽 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficDayList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficDayList", vo);
	}
	
	/**
	 * 트래픽 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficMonthList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficMonthList", vo);
	}
	
	/**
	 * 트래픽그래프 조회
	 * @param vo 
	 * @return
	 */
	public List<TrafficVo> selectTrafficGraphRealList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficGraphRealList", vo);
	}
	
	/**
	 * 트래픽그래프 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficGraphHourList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficGraphHourList", vo);
	}
	
	/**
	 * 트래픽그래프 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficGraphDayList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficGraphDayList", vo);
	}
	
	/**
	 * 트래픽그래프 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficGraphMonthList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficGraphMonthList", vo);
	}
	
	/**
	 * 트래픽엑셀 조회
	 * @param vo 
	 * @return
	 */
	public List<TrafficVo> selectTrafficExcelRealList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficExcelRealList", vo);
	}
	
	/**
	 * 트래픽엑셀 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficExcelHourList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficExcelHourList", vo);
	}
	
	/**
	 * 트래픽엑셀 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficExcelDayList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficExcelDayList", vo);
	}
	
	/**
	 * 트래픽엑셀 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficExcelMonthList(TrafficVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.traffic.selectTrafficExcelMonthList", vo);
	}
}
