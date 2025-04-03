
<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : delete.jsp
* Overview    : 회원탈퇴 화면 
* Description : 회원탈퇴화면
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
<!-- 2017.12.22  보안성검토 회원탈퇴 추가 -->
<div class="page-contents">
	<div class="join container">
		<div class="page-title">
			<div>
			
						<h2>회원탈퇴</h2>
						<p>
							회원탈퇴을 하시면  <span class="kt-red-text">회원님의 개인정보는 모두 삭제됩니다.</span> 
						</p>
					
			</div>
	
		</div>
		<h3>탈퇴 회원 정보 확인</h3>
		<form name="UserForm" id="UserForm" method="post">
		<input type="hidden" name="ruser_id" id="ruser_id" value="${user_id}" />
			<input type="hidden"
				value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
				name="request_token" /> 
				<input type="hidden" value=""
				name="request_url" />
		          
			<div class="user-info-write">
				<dl>
					<dt><span class="kt-red-text">*</span>&nbsp;이름</dt>
					<dd>
						<span id="user_id">${memberVo.user_nm}</span>
					</dd>
				</dl>
				<dl>
					<dt><span class="kt-red-text">*</span>&nbsp;아이디</dt>
					<dd>
						
								<span id="user_id">${memberVo.user_id}</span>
				
					</dd>
				</dl>
				<dl>
					<dt><span class="kt-red-text">*</span>&nbsp;비밀번호</dt>
					<dd>
					<!-- 2018.07.20 회원탈퇴 엔터키 팝업적용 -->
						<input type="password" class="kt-input" title="비밀번호" 
							id="old_pwd" value="" onkeydown="if(event.keyCode == 13) {return check_data();}"/>
							<input type="hidden" name="old_pwd" id="en_old_pwd" />
							<span id="user_id_msg" class="kt-red-text"
									style="margin-left: 15px;">비밀번호 5회 이상 틀릴 시에 로그아웃 되며, 24시간 계정잠금 됩니다.</span>	
					  			
					</dd>
				</dl>
				
				
			</div>
		</form>
		<div class="center">
			<a href="javascript:" class="btn kt-red"
				onClick="javascript:check_data();"> 
					회원탈퇴
			
			</a>
		</div>
	</div>
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

function check_data(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
		
		
	alert(" 국가정보통신 서비스 회원탈퇴를 하시겠습니까?");
	var actionUrl;	
	
    var password = $("#old_pwd").val();
    
    // 비밀번호체크
        
   	if (password.length == 0) {
        alert("비밀번호를 입력해 주세요!");
        $("#old_pwd").focus();
        $("#old_pwd").select();
        return false;
   	}

	// Client pw RSA 추가
    var rsa = new RSAKey();
	rsa.setPublic('${setKey.publicKeyModulus}', '${setKey.publicKeyExponent}');
   	$("#en_old_pwd").val(rsa.encrypt($("#old_pwd").val()));
    

   	
    	if(RealUrl.indexOf("kt-egov") != -1) {
    		actionUrl = "https://www.kt-egov.co.kr/member/delete_id.do";
    	} else if(RealUrl.indexOf("gns.kt.com") != -1) {
    		actionUrl = "https://gns.kt.com/member/delete_id.do";
    	} else {
    		actionUrl = "<%=request.getContextPath()%>/member/delete_id.do";
    	}
    	 $.ajax({
    		    url : actionUrl,
    		    data : $("#UserForm").serialize(),
    			type : "POST",
    			dataType :"json",
    	 	    success:function(data){
    	 	 	    if(data.result == "pw_error") {  
        	 	 	    if(data.login_fail_cnt < 5) {
        	 	 	    	alert("비밀번호가 "+data.login_fail_cnt+"회 틀렸습니다" );
        	 	 	    	document.UserForm.action = "<%=request.getContextPath()%>/member/member_delete.do";
        	 	    	    document.UserForm.submit();
        	 	    	   
            	 	 	    }else{
                	 	 	    alert("비밀번호를 5회 이상 틀려 로그아웃 및 24시간 동안 계정잠금 됩니다")
	                	 	 	  if(RealUrl.indexOf("kt-egov") != -1) {
	  	 	 	    	    		actionUrl = "https://www.kt-egov.co.kr/member/login.do";
	  	 	 	    	    	} else if(RealUrl.indexOf("gns.kt.com") != -1) {
	  	 	 	    	    		actionUrl = "https://gns.kt.com/member/login.do";
	  	 	 	    	    	} else {
	  	 	 	    	    		actionUrl = "<%=request.getContextPath()%>/member/login.do";
	  	 	 	    	    	}
	  	 	 	    	    	document.UserForm.action = actionUrl;
	  	 	 	    	    	document.UserForm.submit();
                	 	 	    } 	 	 	    	
    	 	 	    	
    	 	 	    } else {
        	 	 	         	alert("회원탈퇴가 완료 되었습니다");
    	 	 	    	    	if(RealUrl.indexOf("kt-egov") != -1) {
    	 	 	    	    		actionUrl = "https://www.kt-egov.co.kr/member/login.do";
    	 	 	    	    	} else if(RealUrl.indexOf("gns.kt.com") != -1) {
    	 	 	    	    		actionUrl = "https://gns.kt.com/member/login.do";
    	 	 	    	    	} else {
    	 	 	    	    		actionUrl = "<%=request.getContextPath()%>/member/login.do";
    	 	 	    	    	}
    	 	 	    	    	document.UserForm.action = actionUrl;
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

