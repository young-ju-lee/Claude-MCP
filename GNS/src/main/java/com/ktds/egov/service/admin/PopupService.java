/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : PopupService.java
* Overview    : 팝업창 관리 BIZ Service
* Description : 팝업창 관리 BIZ Service
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.service.admin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.egov.dao.admin.PopupDao;
import com.ktds.egov.vo.admin.PopupVo;
import com.ktds.framework.core.mvc.BaseService;

@Service("popupService")
public class PopupService{

	protected final Log log = LogFactory.getLog(getClass());

	//팝업창관리 DAO
	@Resource(name = "popupDao")
	private PopupDao popupDao;
	
	/***
	 * 팝업창관리 pop_id 를 리턴
	 * @return
	 */
	public String selectPopupId() throws SQLException {
		return this.popupDao.selectPopupId();
	}
	
	/***
	 * 메인 팝업창관리 조회
	 * @return
	 */
	public List<PopupVo> selectMainPopupList(PopupVo vo, HashMap param) throws SQLException {
		return this.popupDao.selectMainPopupList(vo, param);
	}
	
	/***
	 * 팝업창관리 조회
	 * @return
	 */
	public List<PopupVo> selectList(PopupVo vo) throws SQLException {
		return this.popupDao.selectList(vo);
	}
	
	/***
	 * 팝업창관리 조회
	 * @return
	 */
	public PopupVo select(String popId) throws SQLException {
		return this.popupDao.select(popId);		
	}

	/***
	 * 메인 팝업창관리 조회
	 * @return
	 */
	/*
	public PopupVo selectMainPopup() throws SQLException {
		return this.popupDao.selectMainPopup();		
	}
	*/
	/***
	 * 팝업창관리 저장
	 * @return 행수
	 */
	public int insertPopup(PopupVo vo) throws SQLException {
		return this.popupDao.insertPopup(vo);
	}

	/***
	 * 팝업창관리 수정
	 * @return 행수
	 */
	public int updatePopup(PopupVo vo) throws SQLException {
		return this.popupDao.updatePopup(vo);
	}
	
	/***
	 * 팝업창관리 삭제
	 * @return 행수
	 */
	public int deletePopup(String popId) throws SQLException {
		return this.popupDao.deletePopup(popId);
	}
}
