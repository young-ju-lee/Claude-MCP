/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : ChargeService.java
* Overview    : 이용요금계산 Service
* Description : 이용요금계산 Service
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.service.charge;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.egov.dao.charge.ChargeDao;
import com.ktds.egov.vo.charge.ChargeVo;
import com.ktds.framework.core.mvc.BaseService;

@Service("chargeService")
public class ChargeService implements BaseService<ChargeVo>{
	protected final Log log = LogFactory.getLog(getClass());

	@Resource(name = "chargeDao")
	private ChargeDao chargeDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ChargeVo> select(ChargeVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ChargeVo> select(ChargeVo model, HashMap param)
			throws SQLException {
		// TODO Auto-generated method stub
		return chargeDao.select(model, param);
	}

	@Override
	public Integer insert(ChargeVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(ChargeVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(ChargeVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(ChargeVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(ChargeVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(ChargeVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<ChargeVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<ChargeVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<ChargeVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<ChargeVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<ChargeVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<ChargeVo> model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
