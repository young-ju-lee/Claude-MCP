/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : LoginListDAO.java
* Overview    : 사용자 리스트 화면 DAO
* Description : 사용자 리스트 화면 DAO
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

import com.ktds.egov.vo.member.LoginVo;
import com.ktds.framework.core.mvc.BaseDaoImpl;

@Repository("LoginListDAO")
public class LoginListDAO extends BaseDaoImpl<LoginVo> {
   
    @Override
    public List<LoginVo> select(LoginVo loginVo, HashMap map) throws SQLException {
        // TODO Auto-generated method stub
        logger.info("[INFO]  selectLoginList invoked...");

        return getSqlSession().selectList("com.ktds.egov.member."+ map.get("mapperInfo"), loginVo);

    }
}
