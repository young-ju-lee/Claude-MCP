/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : TrafficService.java
* Overview    : 추가서비스 트래픽 관리 Service
* Description : 추가서비스 트래픽 관리 Service
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.service.traffic;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.egov.dao.traffic.TrafficDao;
import com.ktds.egov.vo.traffic.TrafficVo;

@Service("trafficService")
public class TrafficService {

	protected final Log log = LogFactory.getLog(getClass());
	
	//트래픽관리 DAO
	@Resource(name = "trafficDao")
	private TrafficDao trafficDao;
	
	/***
	 * 가입 회선번호정보 조회
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TrafficVo> checkllnum(TrafficVo vo) throws SQLException {
		log.info("TrafficService start!!");
		return this.trafficDao.checkllnum(vo);
	}
	
	/***
	 * 전용회선 가입자 회선번호정보 조회
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TrafficVo> selectllnumList2(TrafficVo vo) throws SQLException {
		log.info("전용회선 가입자 회선번호정보 조회");
		return this.trafficDao.selectllnumList2(vo);
	}
	
	/***
	 * 관리자 회선번호정보 조회
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TrafficVo> selectllnumList3(TrafficVo vo) throws SQLException {		
		return this.trafficDao.selectllnumList3(vo);
	}
	
	/***
	 * <!-- 2018.12.21 admin 전체회선 조회 쿼리 추가  -->
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TrafficVo> selectllnumList_admin(TrafficVo vo) throws SQLException {		
		return this.trafficDao.selectllnumList_admin(vo);
	}
	
	/***
	 * 해당 가입자의 전체 전용회선번호 조회
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TrafficVo> selectAllLLnum(String llnum) throws SQLException {
		return this.trafficDao.selectAllLLnum(llnum);
	}

	/***
	 * 해당 가입자의 전체 전용회선번호 조회
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TrafficVo> selectAllNode() throws SQLException {
		return this.trafficDao.selectAllNode();
	}
	
	/**
	 * 트래픽 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficRealList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficRealList(vo);
	}
	
	/**
	 * 트래픽 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficHourList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficHourList(vo);
	}
	
	/**
	 * 트래픽 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficDayList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficDayList(vo);
	}
	
	/**
	 * 트래픽 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficMonthList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficMonthList(vo);
	}
	
	/**
	 * 트래픽그래프 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficGraphRealList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficGraphRealList(vo);
	}
	
	/**
	 * 트래픽그래프 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficGraphHourList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficGraphHourList(vo);
	}
	
	/**
	 * 트래픽그래프 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficGraphDayList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficGraphDayList(vo);
	}
	
	/**
	 * 트래픽그래프 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficGraphMonthList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficGraphMonthList(vo);
	}
	
	/**
	 * 트래픽엑셀 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficExcelRealList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficExcelRealList(vo);
	}
	
	/**
	 * 트래픽엑셀 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficExcelHourList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficExcelHourList(vo);
	}
	
	/**
	 * 트래픽엑셀 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficExcelDayList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficExcelDayList(vo);
	}
	
	/**
	 * 트래픽엑셀 조회 
	 * @param vo
	 * @return
	 */
	public List<TrafficVo> selectTrafficExcelMonthList(TrafficVo vo) throws SQLException {
		return this.trafficDao.selectTrafficExcelMonthList(vo);
	}
}
