/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : CrossScriptingFilter.java
* Overview    : CrossSiteScripting 방지 filter 클래스 
* Description : 보안(CrossSiteScripting) 를 방지한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/


package com.ktds.framework.frame.filter;

import java.io.IOException;  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 	 
  
public class CrossScriptingFilter implements Filter {  
 public FilterConfig filterConfig = null;	 
 public void init(FilterConfig filterConfig) throws ServletException {  
         this.filterConfig = filterConfig;  
 }  
 	 
 public void destroy() {  
         this.filterConfig = null;  
 }  
 
 //@RequestMapping( method={RequestMethod.GET})	 
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)   
         throws IOException, ServletException {  
        /*  if("GET".equals(((HttpServletRequest) request).getMethod())){
        	  chain.doFilter(new RequestWrapper((HttpServletRequest) request,((HttpServletRequest) request).getMethod()), response);  
          }else{
        	  chain.doFilter( request , response);	  
          }*/
          chain.doFilter(new RequestWrapper((HttpServletRequest) request,((HttpServletRequest) request).getMethod()), response); 
          
 	 
 }  
 	 
}  
