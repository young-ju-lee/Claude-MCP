/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : SupportDAO.java
* Overview    : 공지사항 화면 DAO
* Description : 공지사항 화면 DAO
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

import org.springframework.stereotype.Repository;

import com.ktds.egov.vo.support.NoticeVo;
import com.ktds.framework.core.mvc.BaseDaoImpl;

@Repository("supportDao")
public class SupportDAO extends BaseDaoImpl<NoticeVo>{
	// Select
    @Override
    public List<NoticeVo> select(NoticeVo NoticeVo) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  selectList invoked...");

        return getSqlSession().selectList("com.ktds.egov.support.selectNotice", NoticeVo);

    }
    
    @Override
    public List<NoticeVo> select(NoticeVo noticeVo,HashMap map) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  selectList invoked...");

        return getSqlSession().selectList("com.ktds.egov.support."+ map.get("mapperInfo"), noticeVo);

    }

    @Override
    public Integer insert(NoticeVo NoticeVo) throws SQLException {
        logger.info("[INFO]  insert invoked...");

        return getSqlSession().insert("com.ktds.egov.support.insertNotice", NoticeVo);

    }
    
    @Override
    public Integer update(NoticeVo NoticeVo) throws SQLException {
        logger.info("[INFO]  update invoked...");

        return getSqlSession().update("com.ktds.egov.support.updateNotice", NoticeVo);

    }
    
    @Override
    public Integer delete(NoticeVo NoticeVo) throws SQLException {
        logger.info("[INFO]  delete invoked...");

        return getSqlSession().update("com.ktds.egov.support.deleteNotice", NoticeVo);

    }
    
    @Override
    public Integer update(NoticeVo NoticeVo, HashMap map) throws SQLException {
        logger.info("[INFO]  update invoked...");

        return getSqlSession().update("com.ktds.egov.support."+ map.get("mapperInfo"), NoticeVo);

    }
    
}
