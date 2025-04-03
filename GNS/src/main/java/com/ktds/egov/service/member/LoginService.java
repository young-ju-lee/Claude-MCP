/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : LoginService.java
* Overview    : 로그인 화면 Service
* Description : 로그인 화면 Service
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

import com.ktds.egov.dao.member.LoginDAO;
import com.ktds.egov.vo.member.MemberVo;
import com.ktds.framework.core.mvc.BaseService;

@Service("porloginService")
public class LoginService implements BaseService<MemberVo> {
	protected final Log log = LogFactory.getLog(getClass());
    
    @Resource(name = "porloginDao")
    private LoginDAO loginDao;
    
    
	@Override
	@Transactional(readOnly = true)
	public List<MemberVo> select(MemberVo model) throws SQLException {
		return loginDao.select(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MemberVo> select(MemberVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return loginDao.select(model, param);
	}
	 //<!--2018.01.19 보안성검토 사용자 로그인 로그 수정 -->
	@Override
	public Integer insert(MemberVo model) throws SQLException {	
		return null;
	}
	
	 //<!--2018.01.19 보안성검토 사용자 로그인 로그 수정 -->
	@Override
	public Integer insert(MemberVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub		
		return loginDao.insert(model, param);
	}

	@Override
	public Integer update(MemberVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(MemberVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return loginDao.update(model, param);
	}

	@Override
	public Integer delete(MemberVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(MemberVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		//<!--2019.04.26 중복로그인 제거 -->
		return loginDao.delete(model, param);
	}

	@Override
	public Integer insert(List<MemberVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<MemberVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<MemberVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<MemberVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<MemberVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<MemberVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
