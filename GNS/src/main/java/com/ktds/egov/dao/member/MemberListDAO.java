/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : MemberListDAO.java
* Overview    : 회원관리 리스트 화면 DAO
* Description : 회원관리 리스트 화면 DAO
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

@Repository("memberListDao")
public class MemberListDAO extends BaseDaoImpl<MemberVo> {
	// Select
    @Override
    public List<MemberVo> select(MemberVo memberVo) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  select invoked...");
        return getSqlSession().selectList("com.ktds.egov.member.selectMemberList", memberVo);

    }
  //<!--2018.02.23 보안성검토 개인정보 로그 저장 -->
    @Override
    public Integer insert(MemberVo memberVo) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  insert invoked...");
        
        return getSqlSession().insert("com.ktds.egov.member.insertPrivacy", memberVo);
        
    }
    
    //<2019-04-26 구조적 보안진단 중복로그인체크로직 추가 -->
    @Override
    public List<MemberVo> select(MemberVo memberVo, HashMap map) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  selectLoginList invoked...");

        return getSqlSession().selectList("com.ktds.egov.member."+ map.get("mapperInfo"), memberVo);

    } 
}
