/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : BaseDaoImpl.java
* Overview    : DAO 부모 클래스 
* Description : DAO 부모 클래스
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/


package com.ktds.framework.core.mvc;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("baseDAO")
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

	@Override
	public List<T> select(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	} 

	@Override
	public Integer update(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<T> model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<T> model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<T> model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> select(T model, HashMap param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(T model, HashMap param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(T model, HashMap param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(List<T> model, HashMap param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(List<T> model, HashMap param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<T> model, HashMap param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(T model, HashMap param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
