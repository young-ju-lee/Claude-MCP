/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : LoginListService.java
* Overview    : 회원관리 리스트 화면 Service
* Description : 회원관리 리스트 화면 Service
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.service.member;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.egov.dao.member.LoginListDAO;
import com.ktds.egov.vo.member.LoginVo;
import com.ktds.framework.core.mvc.BaseService;

@Service("LoginListService")
public class LoginListService implements BaseService<LoginVo> {
	protected final Log log = LogFactory.getLog(getClass());
    
    @Resource(name = "LoginListDAO")
    private LoginListDAO loginlistDao;
    
	@Override
	@Transactional(readOnly = true)
	public List<LoginVo> select(LoginVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional(readOnly = true)
	public List<LoginVo> select(LoginVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return loginlistDao.select(model, param);
	}

	@Override
	public Integer insert(LoginVo model) throws SQLException {	
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Integer insert(LoginVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(LoginVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(LoginVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(LoginVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(LoginVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<LoginVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<LoginVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<LoginVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<LoginVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<LoginVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<LoginVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
