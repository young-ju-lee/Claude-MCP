/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : PopupDao.java
* Overview    : 관리자 메뉴 DAO
* Description : 관리자 메뉴 DAO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.dao.admin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ktds.egov.vo.admin.PopupVo;
import com.ktds.egov.vo.support.DataRoomVo;
import com.ktds.framework.core.mvc.BaseDaoImpl;

@Repository("popupDao")
public class PopupDao extends BaseDaoImpl<PopupVo>{
		
	/***
	 * 팝업창관리 ID 값 리턴
	 * @return
	 */
	public String selectPopupId() throws SQLException {		
		return getSqlSession().selectOne("com.ktds.egov.admin.selectPopupId");		
	}
	
	/***
	 * 메인창에서 팝업창 조회
	 * @return
	 */

	public List<PopupVo> selectMainPopupList(PopupVo vo, HashMap map) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.admin."+map.get("mapperInfo"), vo);			
	}

	/***
	 * 팝업창관리 조회
	 * @return
	 */
	public List<PopupVo> selectList(PopupVo vo) throws SQLException {
		return getSqlSession().selectList("com.ktds.egov.admin.selectPopupList", vo);			
	}
	
	/***
	 * 팝업창관리 조회
	 * @return
	 */
	public PopupVo select(String popId) throws SQLException {
		return getSqlSession().selectOne("com.ktds.egov.admin.selectPopup", popId);			
	}

	/***
	 * 메인창에서 팝업창 조회
	 * @return
	 */
	/*
	public PopupVo selectMainPopup(){
		return getSqlSession().selectOne("com.ktds.egov.admin.selectMainPopup");			
	}
	*/
	/***
	 * 팝업창관리 저장
	 * @return
	 */
	public int insertPopup(PopupVo vo) throws SQLException {
		return getSqlSession().insert("com.ktds.egov.admin.insertPopup", vo);			
	}		
	
	/***
	 * 팝업창관리 수정
	 * @return
	 */
	public int updatePopup(PopupVo vo) throws SQLException {
		return getSqlSession().insert("com.ktds.egov.admin.updatePopup", vo);			
	}
	
	/***
	 * 팝업창관리 삭제
	 * @return
	 */
	public int deletePopup(String popId) throws SQLException {
		return getSqlSession().delete("com.ktds.egov.admin.deletePopup", popId);			
	}
}
