/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : StatService.java
* Overview    : 통계관리 메뉴 Service
* Description : 통계관리 메뉴 Service
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.service.member;


import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ktds.egov.dao.member.StatDAO;
import com.ktds.egov.vo.member.MenuStateVo;
import com.ktds.egov.vo.member.StatVo;


@Service("StatService")
public class StatService{

	protected final Log log = LogFactory.getLog(getClass());

	//접속통계관리 DAO
	@Resource(name = "StatDAO")
	private StatDAO statDao;
	
		
	/***
	 * 메뉴접속통계 조회
	 * @return
	 */
	public List<StatVo> selectList(MenuStateVo vo) throws SQLException {
		return this.statDao.selectStat(vo);
	}
	

	public int updateStat(String menuId) throws SQLException {
		return this.statDao.updateStat(menuId);
	}
	
	public int insertMenuState(MenuStateVo vo) throws SQLException {
		return this.statDao.insertMenuState(vo);
	}

	

}
