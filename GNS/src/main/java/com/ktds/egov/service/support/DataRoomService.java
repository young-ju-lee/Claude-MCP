/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : DataRoomService.java
* Overview    : 자료실 관리 Service
* Description : 자료실 관리 Service
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

import com.ktds.egov.dao.support.DataRoomDAO;
import com.ktds.egov.dao.support.SupportDAO;
import com.ktds.egov.vo.support.NoticeVo;
import com.ktds.egov.vo.support.DataRoomVo;
import com.ktds.framework.core.mvc.BaseService;

@Service("dataRoomService")
public class DataRoomService implements BaseService<DataRoomVo>{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource(name = "dataRoomDao")
	private DataRoomDAO dataRoomDao;

    @Override
	public List<DataRoomVo> select(DataRoomVo model) throws SQLException {
		// TODO Auto-generated method stub		
		return dataRoomDao.select(model);
	}

    @Override
	public List<DataRoomVo> select(DataRoomVo model, HashMap param)
			throws SQLException {
		// TODO Auto-generated method stub
		return dataRoomDao.select(model, param);
	}

    @Override
	public Integer insert(DataRoomVo model) throws SQLException {
		// TODO Auto-generated method stub
		return dataRoomDao.insert(model);
	}

    @Override
	public Integer update(DataRoomVo model) throws SQLException {
		// TODO Auto-generated method stub
		return null;		
	}

    @Override
	public Integer delete(DataRoomVo model) throws SQLException {
		// TODO Auto-generated method stub
		return this.dataRoomDao.delete(model);		
	}

	@Override
	public Integer insert(DataRoomVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return dataRoomDao.insert(model, param);
	}
	
	@Override
	public Integer insert(List<DataRoomVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<DataRoomVo> model, HashMap param)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(DataRoomVo model, HashMap param) throws SQLException {
		return dataRoomDao.update(model, param);

	}

	@Override
	public Integer delete(DataRoomVo model, HashMap param) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<DataRoomVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<DataRoomVo> model, HashMap param)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<DataRoomVo> model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<DataRoomVo> model, HashMap param)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
