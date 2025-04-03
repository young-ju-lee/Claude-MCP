/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : DataRoomDAO.java
* Overview    : 자료실 화면 DAO
* Description : 자료실 화면 DAO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.dao.support;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ktds.egov.dao.admin.PopupDao;
import com.ktds.egov.vo.support.NoticeVo;
import com.ktds.egov.vo.support.DataRoomVo;
import com.ktds.framework.core.mvc.BaseDaoImpl;

@Repository("dataRoomDao")
public class DataRoomDAO extends BaseDaoImpl<DataRoomVo>{ 	
	protected final Log logger = LogFactory.getLog(getClass());
	
	public List<DataRoomVo> select(DataRoomVo model)
			throws SQLException {
		// TODO Auto-generated method stub		
		return getSqlSession().selectList("com.ktds.egov.support.selectFile", model);
	}
	
	public List<DataRoomVo> select(DataRoomVo model, HashMap map)
			throws SQLException {
		// TODO Auto-generated method stub
		logger.info("[INFO]  selectList invoked...");

		return getSqlSession().selectList(
				"com.ktds.egov.support." + map.get("mapperInfo"), model);
	}

	public Integer insert(DataRoomVo model) throws SQLException {
		// TODO Auto-generated method stub
		logger.info("[INFO]  insert invoked...");
		return getSqlSession().insert("com.ktds.egov.support.insertDataRoom",
				model);
	}
	
	@Override
    public Integer insert(DataRoomVo model, HashMap map) throws SQLException {
        logger.info("[INFO]  insert invoked...");

        return getSqlSession().insert("com.ktds.egov.support."+ map.get("mapperInfo"), model);

    }
	
	//자료실 내용 삭제
	public Integer delete(DataRoomVo model) throws SQLException {		
		return getSqlSession().update("com.ktds.egov.support.deleteDataRoom",model);
	}
	
    @Override
    public Integer update(DataRoomVo model, HashMap map) throws SQLException {
        logger.info("[INFO]  update invoked...");

        return getSqlSession().update("com.ktds.egov.support."+ map.get("mapperInfo"), model);

    }
}
