<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : check_member.jsp
* Overview    : 회원가입 시 동의 화면 
* Description : 회원가입 시 동의 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2018/01/11       노재덕            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<!-- Header start -->
	<!--Heder 인크로우드 위치입니다.-->
<jsp:include page="../common/header.jsp" />
	<!-- //Header end -->
    
<div class="page-contents">
  <div class="join container">
    <div class="page-title">
      <div>
        <h2>회원가입</h2>
        <p>
          회원가입을 하시면 보다 많은 정보를 얻으실 수 있습니다.<br />
          kt그룹 국가정보통신서비스는 <span class="kt-red-text">효율적, 안정적</span> 운영을 위한 상시 감시 및 복구 체계를 구축하고 있습니다.
        </p>
      </div>
      <ul>
        <li><span class="kt-red-text">1. 14세 미만 어린이 확인</li>
        <li>2. 약관 및 방침 동의</span></li>
        <li>3. 회원정보 입력</li>
        <li>4. 완료</li>
      </ul>     
    </div>
    <form id="clauseform" name="clauseform" method="post">
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
	 <div>
      <ul>
	        <li><a href="javascript:checkmember();"><img src="<%=request.getContextPath()%>/images/14over.GIF" alt="내국인"></a>       
	        <a href="javascript:cancel();"><img src="<%=request.getContextPath()%>/images/14under.GIF" alt="14세미만어린이"></a></li>
	      </ul>
	   </div>
	 </form>     
</div>



<!-- Footer Bottom start -->
<!--footer 인크로드 위치입니다. -->
<jsp:include page="../common/footer.jsp" /> 
<!-- //Footer Bottom end -->


<script>
	 var RealUrl = location.href;
	 if(RealUrl.indexOf("http:") != -1 && (RealUrl.indexOf("kt-egov") != -1 || RealUrl.indexOf("gns.kt.com") != -1)) {
	 	RealUrl = RealUrl.replace('http:','https:');
	 	document.pageForm.action = RealUrl;
	 	document.pageForm.submit();
	 }
	 function checkmember() {
			document.clauseform.action="<%=request.getContextPath()%>/member/clause.do" ;
			document.clauseform.submit();
		}     
	
	function cancel() {
		alert("14세 미만 어린이는 회원가입 하실 수 없습니다");
		document.clauseform.action="<%=request.getContextPath()%>/main.do" ;
		document.clauseform.submit();
	};
</script>
</html>