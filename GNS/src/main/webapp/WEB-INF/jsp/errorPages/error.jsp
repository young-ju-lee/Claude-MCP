
<%
	/* POR version 1.0
	 * Copyright @ 2013 kt Inc. All rights reserved.
	 * This is a proprietary software of kt Inc, and you may not use this file except in compliance
	 * with license agreement with kt Inc. Any redistribution or use of this software, with or without
	 * modification shall be strictly prohibited without prior written approval of kt Inc, and
	 * the copyright notice above does not evidence any actual or intended publication of such software.
	 *
	 * File Name   : error.jsp
	 * Overview    : 오류페이지 화면 
	 * Description : 오류페이지 화면
	 * *=====================================================================
	 * Version    변경  일자       개발자               설명
	 * ---------- ---------- --------- --------------------------------------
	 * 1.0.0.0    2013/11/11       강훈병            신규개발
	 *=====================================================================
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!-- Header start -->
<!--Heder 인크로우드 위치입니다.-->
<jsp:include page="../common/header.jsp" />
<!-- //Header end -->

<!-- Container Start -->
<div class="page-contents">
	<div class="container">
	        <h1><span class="kt-red-text">잘못된 접근</span> 또는 <span class="kt-red-text">존재하지 않는 페이지</span>입니다.</h1>
	        <h1> 잠시후 재시도 해주시기 바랍니다. </h1>
	</div>
</div>

<!-- Footer Bottom start -->
<!--footer 인크로드 위치입니다. -->
<jsp:include page="../common/footer.jsp" />
<!-- //Footer Bottom end -->

</body>
</html>