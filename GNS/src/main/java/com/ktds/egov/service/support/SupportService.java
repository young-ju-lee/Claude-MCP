/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : SupportService.java
* Overview    : 공지사항 관리 Service
* Description : 공지사항 관리 Service
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.service.support;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.egov.dao.support.SupportDAO;
import com.ktds.egov.vo.support.NoticeVo;
import com.ktds.framework.core.mvc.BaseService;

@Service("supportService")
public class SupportService  implements BaseService<NoticeVo>{
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource(name = "supportDao")
	private SupportDAO supportDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<NoticeVo> select(NoticeVo model) throws SQLException {
		return supportDao.select(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NoticeVo> select(NoticeVo model, HashMap param) throws SQLException {
		return supportDao.select(model, param);
	}

	@Override
	public Integer insert(NoticeVo model) throws SQLException {
		return supportDao.insert(model);
	}

	@Override
	public Integer insert(NoticeVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(NoticeVo model) throws SQLException {

		return supportDao.update(model);
	}

	@Override
	public Integer update(NoticeVo model, HashMap param) throws SQLException {
		return supportDao.update(model, param);
	}

	@Override
	public Integer delete(NoticeVo model) throws SQLException {

		return supportDao.delete(model);
	}

	@Override
	public Integer delete(NoticeVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<NoticeVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<NoticeVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<NoticeVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<NoticeVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<NoticeVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<NoticeVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
