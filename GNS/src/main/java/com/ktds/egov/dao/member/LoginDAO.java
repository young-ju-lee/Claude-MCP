/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : LoginDAO.java
* Overview    : 로그인 화면 DAO
* Description : 로그인 화면 DAO
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

import com.ktds.egov.vo.member.MemberVo;
import com.ktds.framework.core.mvc.BaseDaoImpl;

@Repository("porloginDao")
public class LoginDAO extends BaseDaoImpl<MemberVo> {
    @Override
    //<!--2018.01.26 보안성검토 사용자 로그인 로그 수정 -->
    public Integer insert(MemberVo memberVo, HashMap map) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  insertLogin invoked...");
      //  return getSqlSession().insert("com.ktds.egov.member.insertLogin", memberVo);
        return getSqlSession().insert("com.ktds.egov.member."+ map.get("mapperInfo"), memberVo);  

    }
    
    @Override
    public List<MemberVo> select(MemberVo memberVo) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  select invoked...");
        return getSqlSession().selectList("com.ktds.egov.member.selectUserIdCheck", memberVo);

    }
    
    @Override
	public Integer update(MemberVo memberVo, HashMap map) throws SQLException {
		// TODO Auto-generated method stub
    	logger.info("[INFO]  update invoked...");
		return getSqlSession().update("com.ktds.egov.member."+ map.get("mapperInfo"), memberVo);  
	}
    //<!--2019.04.26 중복로그인 제거 -->
    @Override
   	public Integer delete(MemberVo memberVo, HashMap map) throws SQLException {
   		// TODO Auto-generated method stub
       	logger.info("[INFO]  update invoked...");
   		return getSqlSession().delete("com.ktds.egov.member."+ map.get("mapperInfo"), memberVo);  
   	}

	@Override
    public List<MemberVo> select(MemberVo memberVo, HashMap map) throws SQLException {
        // TODO Auto-generated method stub
    	logger.info("[INFO]  select invoked...");
    	return getSqlSession().selectList("com.ktds.egov.member."+ map.get("mapperInfo"), memberVo);        
    }
}
