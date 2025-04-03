<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : error2.jsp
* Overview    : 오류페이지 테스트 화면 
* Description : 오류페이지 테스트 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-Control" content="no-store">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="1L">
<%-- 
<%
response.setHeader("Cache-Control", "no_store");
response.setHeader("Cache-control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
 --%>
<title>errorPage</title>
</head>
<body>
<h3>Due to following reasons an error has occurred...</h3>
<ul>
<li>status_code : ${requestScope["javax.servlet.error.status_code"]}</li>
<li>exception_type : ${requestScope["javax.servlet.error.exception_type"]}</li>
<li>message : ${requestScope["javax.servlet.error.message"]}</li>
<li>exception : ${requestScope["javax.servlet.error.exception"]}</li>
<li>request_uri : ${requestScope["javax.servlet.error.request_uri"]}</li>
</ul>
</body>
</html>