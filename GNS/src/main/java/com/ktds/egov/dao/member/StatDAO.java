/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : StatDAO.java
* Overview    : 통계정보 메뉴 DAO
* Description : 통계정보 메뉴 DAO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.dao.member;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ktds.egov.vo.member.MenuStateVo;
import com.ktds.egov.vo.member.StatVo;
import com.ktds.framework.core.mvc.BaseDaoImpl;

@Repository("StatDAO")
public class StatDAO extends BaseDaoImpl<StatDAO>{	
	
		/***
		 * 메뉴접속통계 조회
		 * @return
		 */
	public List<StatVo> selectStat(MenuStateVo vo) throws SQLException {
			return getSqlSession().selectList("com.ktds.egov.member.selectStat", vo);			
	}
	
	/***
	 * 메뉴접속 적용
	 * @return
	 */
	public int updateStat(String menuId) throws SQLException {
		return getSqlSession().update("com.ktds.egov.member.updateStat", menuId);			
	}
	
	/***
	 * 팝업창관리 저장
	 * @return
	 */
	public int insertMenuState(MenuStateVo vo) throws SQLException {
		return getSqlSession().insert("com.ktds.egov.member.insertMenuState", vo);			
	}		


}
