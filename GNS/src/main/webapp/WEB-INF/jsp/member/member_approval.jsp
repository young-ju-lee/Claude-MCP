<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : member_approval.jsp
* Overview    : 가입자 확인 및 승인 화면 
* Description : 가입자 확인 및 승인 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
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
        <h2>회원승인</h2>
        <p>
          kt그룹 국가정보통신서비스의 <span class="kt-red-text">회원 승인 페이지</span>입니다.
        </p>
      </div>
      <ul>
        <li>1. 회원관리</li>
        <li><span class="kt-red-text">2. 회원승인</span></li>
        <li>3. 완료</li>
      </ul>
    </div>
    
    <form  name="UserForm" id="UserForm"  method="post">
		
		<input type="hidden" value="" name="user_id" />
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
		<h3>회원정보</h3>
	    <div class="user-info-write">
	      <dl>
	        <dt>이름</dt>
	        <!-- 2018.01.19 보안성검토 마스킹처리 -->
	        <dd><label class="kt-label">${fn:substring(memberVo.user_nm,0,fn:length(memberVo.user_nm)-1)}*</label></dd>
	      </dl>
	      <dl>
	        <dt>아이디</dt>        
	        <!-- 2018.01.19 보안성검토 마스킹처리 -->
	        <dd>
	          <label class="kt-label">${fn:substring(memberVo.user_id,0,fn:length(memberVo.user_id)-3)}***</label>
	        </dd>
	      </dl>
	      <dl>
	        <dt>이메일</dt>
	        <dd>
	        	<label class="kt-label">${fn:substring(memberVo.user_mail,0,fn:indexOf(memberVo.user_mail,'@')-3)}***@${fn:substring(memberVo.user_mail,fn:indexOf(memberVo.user_mail,'@')+1,fn:length(memberVo.user_mail))}</label>
	      </dl>
	      <dl>
	        <dt>전화번호</dt>
	        <!-- 2018.01.19 보안성검토 마스킹처리 -->
	        <dd>
	          	<label class="kt-label">${memberVo.tel1}</label>-<label class="kt-label">${fn:substring(memberVo.tel2,0,fn:length(memberVo.tel2)-2)}**</label>-<label class="kt-label">*${fn:substring(memberVo.tel3,1,fn:length(memberVo.tel3))}</label>
	        </dd>
	      </dl>
	      <dl>
	        <dt>휴대전화번호</dt>
	        <!-- 2018.01.19 보안성검토 마스킹처리 -->
	        <dd>
	          <label class="kt-label">${memberVo.mobile1}</label>-<label class="kt-label">${fn:substring(memberVo.mobile2,0,fn:length(memberVo.mobile2)-2)}**</label>-<label class="kt-label">*${fn:substring(memberVo.mobile3,1,fn:length(memberVo.mobile3))}</label>
	        </dd>
	      </dl>
	      <dl>
	        <dt>회선번호</dt>
	        <dd>
	          <label class="kt-label">${memberVo.ll_id}</label>
	        </dd>
	      </dl>
	    </div>
	    <h3>가입회선 정보</h3>
	    <div class="row">
		      <div class="col s12">	                 
		        <table>
		           <colgroup>
	                <col />
	                <col />
	                <col />
	                <col />
	                <col />
	              </colgroup>
		           <thead>
		           	<tr>
		              	<th class="center">계약번호</th>
          				<th class="center">전용회선 번호</th>
          				<th class="center">신청자명</th>
          				<th class="center">연락번호</th>
          				<th class="center">논리포트ID</th>
		            </tr>
		          </thead>
		          <tbody>
		          	<c:forEach var="list" items="${member_info_list}">
			            <tr>
							<td class="center">${list.sa_id}</td>
							<td class="center">${list.llnum}</td>
							<!-- 2018.01.19 보안성검토 마스킹처리 -->
							<td class="center">${fn:substring(list.reqer_name,0,fn:length(list.reqer_name)-1)}*</td>
							<td class="center">${fn:substring(list.cntc_tel_no,0,fn:length(list.cntc_tel_no)-6)}***${fn:substring(list.cntc_tel_no,9,11)}</td>
							<td class="center">${list.logic_port_id}</td>		
	        			</tr>
	        		</c:forEach>
		          </tbody>	
		        </table>
		      </div>
			</div>
			<h3>회원정보</h3>
	    <div class="user-info-write">
	      <dl>
	        <dt>회원등급</dt>
	        <dd>
				<input class="kt-input" type="radio" name="user_auth_id" value="10" id="user_auth_id" title="회원등급" 
             		<c:if test="${memberVo.user_auth_id=='10'}">checked</c:if> />
					<label for="user_auth_id" class="kt-label right-m">일반회원</label>
            	<input class="kt-input" type="radio" name="user_auth_id" value="20" id="user_auth_id" title="회원등급"
           	   		<c:if test="${memberVo.user_auth_id=='20'}">checked</c:if> />
					<label for="user_auth_id" class="kt-label right-m">전용회원</label>
           		<input class="kt-input" type="radio" name="user_auth_id" value="30" id="user_auth_id" title="회원등급"
               		<c:if test="${memberVo.user_auth_id=='30'}">checked</c:if> />
					<label for="user_auth_id" class="kt-label right-m">상담관리자</label>
           		<input class="kt-input" type="radio" name="user_auth_id" value="40" id="user_auth_id" title="회원등급"
                	<c:if test="${memberVo.user_auth_id=='40'}">checked</c:if> />
                	<label for="user_auth_id" class="kt-label right-m">전체관리자</label>
			</dd>
	      </dl>
	    </div>
	    <div class="center">
	      <!-- <a href="" class="btn kt-red" onClick="return check_data();"> -->
	      <a href="javascript:" class="btn kt-red" onClick="javascript:approval('${memberVo.user_id}');">승인</a>
	      <a href="javascript:menuMove('<%=request.getContextPath()%>/member/member_list.do');" class="btn ">목록</a>
	    </div>
	</form>
    
  </div>
</div>

<!-- Footer Bottom start -->
<!--footer 인크로드 위치입니다. -->
<jsp:include page="../common/footer.jsp" />
<!-- //Footer Bottom end -->

<script>

function approval(user_id){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		
	document.UserForm.user_id.value = user_id;
	$.ajax({
	    url : "<%=request.getContextPath()%>/member/member_approval_register.do",
	    data : $("#UserForm").serialize(),
		type : "POST",
		dataType :"json",
 	    success:function(data){
 	    	if(data.result == "pw_error") {
 	 	    	alert("로직변경이 발생했습니다!");
 	 	    	document.UserForm.action = "<%=request.getContextPath()%>/error.do";
 	    	    document.UserForm.submit();
 	 	    }else if (data.result != "1") {
 	    		alert("승인 실패하였습니다.");
	 	    }
 	    	else {
 	    		alert("승인되었습니다.");
 	    		document.UserForm.action="<%=request.getContextPath()%>/member/member_list.do" ;
 	    	    document.UserForm.submit();
	 	    }
	     },
	     error:function(request,status,error){
	    	 alert(request + " : " + status + " : " + error);
	     }
	  });
}
</script>
</html>

