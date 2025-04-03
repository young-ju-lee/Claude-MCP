/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : MemberDAO.java
* Overview    : 회원관리 메뉴 DAO
* Description : 회원관리 메뉴 DAO
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.dao.member;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ktds.egov.vo.admin.PopupVo;
import com.ktds.egov.vo.member.MemberVo;
import com.ktds.egov.vo.support.NoticeVo;
import com.ktds.framework.core.mvc.BaseDaoImpl;

@Repository("memberDao")
public class MemberDAO extends BaseDaoImpl<MemberVo> {
	// Select
    @Override
    public List<MemberVo> select(MemberVo memberVo) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  select invoked...");
        return getSqlSession().selectList("com.ktds.egov.member.selectUserId", memberVo);

    }
    
    @Override
    public List<MemberVo> select(MemberVo memberVo,HashMap map) throws SQLException {
        // TODO Auto-generated method stub
    	logger.info("[INFO]  selectList invoked...");

        return getSqlSession().selectList("com.ktds.egov.member."+ map.get("mapperInfo"), memberVo);

    }


    @Override
    public Integer insert(MemberVo memberVo) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  insert invoked...");
        return getSqlSession().insert("com.ktds.egov.member.insertUser", memberVo);

    }
    
    @Override
    public Integer insert(MemberVo memberVo, HashMap param) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  insert invoked...");
        //return getSqlSession().insert("com.ktds.egov.member.insertLogin", memberVo);
        //2019.11.15 insertLogin 만 사용하던 형태에서 map으로 insert되도록 수정
        return getSqlSession().insert("com.ktds.egov.member." + param.get("mapperInfo"), memberVo);
    }
        
    @Override
    public Integer update(MemberVo memberVo) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  insert invoked...");
        return getSqlSession().update("com.ktds.egov.member.updateUser", memberVo);

    }
    
    @Override
    public Integer update(MemberVo memberVo, HashMap map) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  insert invoked...");
        return getSqlSession().update("com.ktds.egov.member." + map.get("mapperInfo"), memberVo);

    }


    @Override
    public Integer delete(MemberVo memberVo, HashMap map) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  delete invoked...");
        return getSqlSession().delete("com.ktds.egov.member." + map.get("mapperInfo"), memberVo);

    }

}
