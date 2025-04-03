<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : popup_preview.jsp
* Overview    : 팝업창 미리보기 화면 
* Description : 팝업창 미리보기 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

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
<title>kt그룹 국가정보통신서비스 공지팝업</title>

<!-- CSS file -->
<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css" />

</head>
<body>
${popupVo.pop_cont}
</body>
</html>