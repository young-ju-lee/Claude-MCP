/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : BaseService.java
* Overview    : Service 부모 인터페이스 
* Description : Service 부모 인터페이스
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.framework.core.mvc;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic Service 인터페이스
 * 
 * @author mhchang
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
public interface BaseService<T> {  
	
	@Transactional(readOnly = true)
	public List<T> select(T model) 						 throws Exception;
	@Transactional(readOnly = true)
	public List<T> select(T model 		, HashMap param) throws Exception;
	
	public Integer insert(T model)  					 throws Exception; 
	public Integer insert(T model 		, HashMap param) throws Exception;
	public Integer update(T model) 						 throws Exception; 
	public Integer update(T model 		, HashMap param) throws Exception;
	public Integer delete(T model) 						 throws Exception;
	public Integer delete(T model 		, HashMap param) throws Exception;
	
	public Integer insert(List<T> model)  			      throws Exception;
	public Integer insert(List<T> model , HashMap param)  throws Exception;
	public Integer update(List<T> model)  				  throws Exception;
	public Integer update(List<T> model , HashMap param)  throws Exception;
	public Integer delete(List<T> model)  				  throws Exception;
	public Integer delete(List<T> model , HashMap param)  throws Exception;
}  
   