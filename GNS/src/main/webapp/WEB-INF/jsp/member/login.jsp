<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : login.jsp
* Overview    : 로그인 화면 
* Description : 로그인 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<%
//2019.11.15 쿠키 가지고 오는 부분 신규추가
String cokiid2="";
String cokisalt2="";  

Cookie[] cookies = request.getCookies(); //저장된 쿠키값 가지고 오기
if(cookies!=null){
 for(int i=0; i<cookies.length; i++){
	 
	if(cookies[i].getName().equals("cokiid")){
		cokiid2=cookies[i].getValue();
		// 2023-01-30 XSS 처리 추가
		cokiid2 = cokiid2.replaceAll("<","&lt;");
		cokiid2 = cokiid2.replaceAll(">","&gt;");
	}
	
	if(cookies[i].getName().equals("cokisalt")){
		cokisalt2 = cookies[i].getValue();
		// 2023-01-30 XSS 처리 추가
		cokisalt2 = cokisalt2.replaceAll("<","&lt;");
		cokisalt2 = cokisalt2.replaceAll(">","&gt;");
	}
   }
 }
%>

	<!-- Header start -->
	<!--Heder 인크로우드 위치입니다.-->
<jsp:include page="../common/header.jsp" />
	<!-- //Header end -->
<body></body>
	<div class="page-contents">
	  <div class="login container">
	    <div class="row">
	      <div class="col s12 offset-m3 m6">
	        <div class="page-title">
	          <div>
	            <h2>로그인</h2>
	          </div>
	        </div>
	        <!-- 2017.12.22  보안성검토 계정잠금 10회->5회로 변경-->
	        <form id="loginform" name="loginform"  method="post"  >
	        	<input type="hidden" name="flag" value="${flag}"/>
				<input type="hidden" value="" name="request_url" />	
				<input type="hidden" name="mailpass" id="mailpass" value=""  />
				<!--2018.02.23 captcha 음성듣기 버그 수정 -->
				<input type="hidden" value="" id="resound" />				
				<!-- 2017.12.22  보안성검토 존재하지 않는 아이디 메세지 수정 및 조건수정-->
	        	<p>
	        	   <c:choose>
			         	<c:when test="${(flag == 'N' && login_fail_cnt == 1)}">
	         				<span id="user_id_msg">아이디 또는 비밀번호 또는 보안문자가 <span class="kt-red-text"><c:out value="${login_fail_cnt}" /></span> 회 틀렸습니다.<br />
	          					<span class="kt-red-text">5회 연속 틀릴 경우, 24시간(1일) 사용이 제한</span>되오니 확인 후 입력하십시오.</span>
						</c:when>
						<c:when test="${(flag == 'E' && login_fail_cnt < 5 && login_fail_cnt > 0 )}">       		
	          				<span id="user_id_msg">아이디 또는 비밀번호 또는 보안문자가 <span class="kt-red-text"><c:out value="${login_fail_cnt}" /></span> 회 틀렸습니다.<br />
	          					<span class="kt-red-text">5회 연속 틀릴 경우, 24시간(1일) 사용이 제한</span>되오니 확인 후 입력하십시오.</span>
						</c:when>
	          			<c:when test="${flag == 'E' && login_fail_cnt >= 5}">       		
	          				<span id="user_id_msg">아이디 또는 비밀번호 또는 보안문자가 5회 틀리셨습니다.<br />
	        					<c:out value = "${login_fail_time}" /> 후 다시 이용해 주십시오.</span>
						</c:when>
		       			<c:when test="${flag =='1'}">       		
	         				<span id="user_id_msg" >회원이 가입되었습니다.(승인단계를 거처야 정상적으로 이용이 가능합니다.)</span>
						</c:when>
		         	</c:choose>
	         	</p>
		        <input name="user_id" type="text" id="txtUserId" class="input-id" title="아이디" style="line-height: 20px;" autocomplete="off" placeholder="아이디">
		        <input type="password" id="txPassword" class="input-pw" title="비밀번호" style="line-height: 20px;" autocomplete="off" placeholder="비밀번호" >
		        <input name="user_pw" type="hidden" />
		       
    
		     <p>
		          <input type="checkbox" name="id_store" id="checkbox" value="" onClick="chkSaveID();" />
		          <label for="saveID">아이디 저장</label>
		   </p>     
	        

	   
	   <div id="captchaform">
	   <!-- 2019.05.24 captcha 예외처리 긴급배포 로그인 아이디값 파라미터 추가 -->
	   <input type="hidden" name="captchapass_user_Id" id="captchapass_user_Id" value=""  />
	   <input type="hidden" name="captchapass" id="captchapass" value=""  />
	   <input type="hidden" name="cokicheckid" id="cokicheckid" value=""  />
	   
                    <!-- Adding BotDetect Captcha to the page -->                    
                    <botDetect:captcha id="loginCaptcha" 
                                userInputID="captchaCode"
                                codeLength="6"
                                imageWidth="300"
                                imageHeight="50"              
                                codeStyle="ALPHANUMERIC" 
                                imageStyle="BULLETS,BULLETS2,SPLIT,SPLIT2,INBANDAGES"
                                soundFormat="WAV_PCM_16BIT_8KHZ_MONO"
                                />
          			<div class="validationDiv">
                       <!--  <input name="captchaCode" type="text" id="text" class="input-pw" title="보안문자를 입력해주세요" style="line-height: 20px; text-transform:uppercase;" autocomplete="off" placeholder="보안문자를 입력해주세요" onkeydown="javascript:onEnterkey(event, 'login');"> -->
                       
                       <!-- 2017.11.24 엔터 오류 수정 2가지 -->
                       <!-- <input name="captchaCode" type="text" id="txtcaptchaCode" class="input-pw" title="보안문자를 입력해주세요" style="line-height: 20px; text-transform:uppercase;" autocomplete="off" placeholder="보안문자를 입력해주세요" onkeypress="if(event.keyCode==13){checkMsg()};"> -->
                       <!-- 2018.02.23 로그인시 엔터키 잠금 --> 
                       <input name="captchaCode" type="text" id="txtcaptchaCode" class="input-pw" title="보안문자를 입력해주세요" style="line-height: 20px; text-transform:uppercase;" autocomplete="off" placeholder="보안문자를 입력해주세요" onkeydown="if(event.keyCode==13){return false;};"> 
                      
                    </div>
        	<div style="visibility:hidden" id="soundDiv"></div>            
      	</div>
    </form>
	  	   <!-- 2019.11.15 쿠키 값 체크를 위해 전송하는 폼 추가  -->     
		   <form id="cokiForm" name="cokiForm" action="login" method="post"> 
		    <input type="hidden" name="cokiid" id="cokiid" value=""  />
		     <input type="hidden" name="cokisalt" id="cokisalt" value=""  /> 
		   </form>
	        <a href="javascript:" onclick="javascript:return checkMsg();" class="btn btn-block kt-red">로그인</a>
	        <a href="javascript:join_move();" class="btn btn-flat btn-block">회원가입</a>
	      
	      </div>
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

$(document).ready(function () {
	cokicheck(); //2019.11.15 쿠키체크 함수 실행
    document.getElementById('txtUserId').focus();
    var sLoginID = getCookie('SaveLoginID');
    var sLoginIDChk = getCookie('LoginIDChk');
    if (sLoginIDChk == "Y") {
        document.getElementById('txtUserId').value = sLoginID;
        document.getElementById('checkbox').checked = true;
        document.getElementById('txPassword').focus();
    }
    else {
        document.getElementById('txtUserId').value = '';
        document.getElementById('txPassword').value = '';
    }
    $(".BDC_CaptchaDiv").attr("style","width:550px");
    $(".BDC_CaptchaImageDiv").attr("style","width:300px");
    $(".BDC_CaptchaIconsDiv").attr("style","width:80px");
    $(".BDC_ReloadLink").click(checkMsg2);    
    $(".BDC_ReloadLink").attr("title","새로고침");
    $(".BDC_SoundLink").attr("title","음성듣기"); 
    $(".BDC_SoundLink").after('<span>음성듣기</span>');   
    $(".BDC_ReloadLink").after('<span>새로고침</span>');
    $(".BDC_SoundLink").click(playSound);
  
});

function chkSaveID() {
    if (document.getElementById('checkbox').checked == false) {
    	setCookie( 'SaveLoginID', '', -1 );
    	setCookie( 'LoginIDChk', '', -1 );    	                  
    }
	// cookie 에 ID값 저장.
	if (document.getElementById('checkbox').checked == true) {

		setCookie( 'SaveLoginID', document.getElementById('txtUserId').value , 7 );
    	setCookie( 'LoginIDChk', 'Y', 7 );     		
    }
}


//쿠키 가져오기  
function getCookie( name ) {  
	
   var nameOfCookie = name + "=";  
   var x = 0;  
   while ( x <= document.cookie.length )  
   {  
       var y = (x+nameOfCookie.length);  
       if ( document.cookie.substring( x, y ) == nameOfCookie ) {  
           if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )  
               endOfCookie = document.cookie.length;  
           return unescape( document.cookie.substring( y, endOfCookie ) );  
       }  
       x = document.cookie.indexOf( " ", x ) + 1;  
       if ( x == 0 )  
           break;  
   }  
   return "";  
}  

//2023-01-30 PERSISTENT_COOKIE 처리 추가(CRLF 제거)
function removeCrLf(str){
	str = str.replaceAll("\r", "").replaceAll("\n", "").replaceAll("%0d", "").replaceAll("%0a", "");
	return str;
}


// 24시간 기준 쿠키 설정하기  
// expiredays 후의 클릭한 시간까지 쿠키 설정  
function setCookie(name, value, expiredays) {
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);
	value = removeCrLf(value);
	name = removeCrLf(name);
	
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
}

//로그인 정보 확인
function checkMsg(form){

	var subUrl = '<%=request.getContextPath()%>';
	
	//if((event.keyCode != 13)&&(event.keyCode != 0)) return false;

	var userloginform = document.loginform;
	
	if(userloginform.user_id.value == ""){
		alert("아이디를 확인하세요.");
		userloginform.user_id.focus();
		return false;
	}else if($("#txPassword").val() == ""){
		alert("비밀번호를 확인하세요.");
		$("#txPassword").focus();
		return false;
	}

	// Client pw RSA 추가
    var rsa = new RSAKey();
	rsa.setPublic('${setKey.publicKeyModulus}', '${setKey.publicKeyExponent}');
	userloginform.user_pw.value = rsa.encrypt($("#txPassword").val());

	//2019.05.24 captcha 예외처리 긴급배포 로그인 아이디값 파라미터 추가
    var captchapass_user_Id2 = userloginform.user_id.value;
    userloginform.captchapass_user_Id.value = captchapass_user_Id2;
    //2019.11.15 쿠키ID 비교 추가
	if(userloginform.captchapass.value == "Y" && userloginform.user_id.value !="admin1"){ //쿠키ID값과 입력값이 다르다면 captcha 다시 보여줌		
		if(userloginform.user_id.value != userloginform.cokicheckid.value){
			$("#captchaform").show();
			document.getElementById('captchapass').value = "N";
			document.getElementById('mailpass').value = "N";
			return false;
		} 
	}
		
	/*if(RealUrl.indexOf("kt-egov") != -1) {
		userloginform.action="https://www.kt-egov.co.kr/member/login_check.do";
	} else if(RealUrl.indexOf("gns.kt.com") != -1) {
		userloginform.action="https://gns.kt.com/member/login_check.do";
	} else {
		userloginform.action=subUrl + "/member/login_check.do" ;
	}*/
	userloginform.action=subUrl + "/member/login_check.do";
	userloginform.method = "post";
	userloginform.submit();

		
}   

//captcha 새로고침
function checkMsg2(form){
	$.ajax({
	   url : "<%=request.getContextPath()%>/member/captcha_reload.do",	 
	    data : $("#loginform").serialize(),
		type : "POST",
		dataType :"json",		
	    success:function(data){
            var data2 = data.result.captchaImageUrl;
            var data4 = data.result.captchaSoundUrl;
            var data3 = data2.replace(/&amp;/g,'&');
            var data5 = data4.replace(/&amp;/g,'&');
	    	
	    	  $("#loginCaptcha_CaptchaImage").attr("SRC",data3);
	    	  $("#loginCaptcha_SoundLink").attr("href",data5);
			var splitarr = data3.split("=");
				
	          $("#BDC_VCID_loginCaptcha").val(splitarr[3]);
	          // 2017.11.24 보안문자 입력 후 캡챠 새로고침 하면 보안문자 초기화
	    	  document.getElementById('txtcaptchaCode').value = '';
	    	  //2017.02.23 새로고침 시에 사운드 값 포기화
	    	  document.getElementById('resound').value = '';  
	     },
	  });	

	
}  

function cokicheck(){//2019.11.15 쿠키체크 함수 신규추가
	var cokiid = "<%=cokiid2%>";
	var cokisalt = "<%=cokisalt2%>";  	
    if(cokiid==""){//쿠키ID값이 없으면 패스
        }else{    // 쿠키ID값이 있으면 쿠키정보확인 로직 실행      
    	    var cokiForm = document.cokiForm;
    	    cokiForm.cokiid.value = cokiid;  //사용자에게 생성된 쿠키인지 확인하기 위해 cokiForm에 넣고 전송
    	    cokiForm.cokisalt.value = cokisalt;
    		$.ajax({
    		    url : "<%=request.getContextPath()%>/member/coki_check.do",
    		    data : $("#cokiForm").serialize(),
    			type : "POST",
    			dataType :"json",    			
    	 	    success:function(data){
    	 	    	if(data.result == "coki_ok") { //해당 쿠키ID,salt 값으로 데이터가 있으면 ok    	 	 	    
    	 	 	    	$("#captchaform").hide();    					
    					document.getElementById('captchapass').value = "Y"; //로그인시에 captcha 체크 예외처리 하기 위한값
    			    	document.getElementById('cokicheckid').value = cokiid; //로그인시에 쿠키에 저장된 ID와 비교하기 위해 사용
    			    	document.getElementById('mailpass').value = "Y"; //메일인증 안된 사용자를 확인하기 위한 값 N이면 메일인증페이지 Y면 메인인증 페이지 패스
    	 	 	    } else { //데이터가 없으면 데이터 변조 발생 창 띄우고 메인으로 이동
    	 	 	    	alert("쿠키 데이터 불일치가 발생했습니다. \n관리자에게 문의하세요");
    	 	 	    	document.cokiForm.action = "<%=request.getContextPath()%>/main.do";
    	 	    	    document.cokiForm.submit();
    	 	 	  	}    	 	 	  	
    		 	    }    		    
    		  });	
        }
}

  function playSound(){	
	  //<!--2018.02.23 captcha 음서듣기 버그 수정 -->
	  var checksound=document.getElementById('resound').value ;
	  if(checksound.indexOf('sound') > -1){
		  url1=checksound;
		  var uAgent = navigator.userAgent;	
		   	if (uAgent.indexOf('Trident')>-1 || uAgent.indexOf('MSIE') > -1){   		 
		   			$("#soundDiv").html("  <bgsound  src='" + url1 + "' > "); 		 
		   			return false;    			 
		   		} else{		
		   			$("#soundDiv").html("  <audio autoplay><source src='" + url1 + "' type='audio/x-wav'></source></audio> ");
		   		}
	   }else{
		   var url1 = $("#loginCaptcha_SoundLink").attr("href");	
		   document.getElementById('resound').value=url1;
		       var uAgent = navigator.userAgent;	
		   	if (uAgent.indexOf('Trident')>-1 || uAgent.indexOf('MSIE') > -1){   		 
		   			$("#soundDiv").html("  <bgsound  src='" + url1 + "' > "); 		 
		   			return false;    			 
		   		} else{
		   			$("#loginCaptcha_SoundLink").attr("href","#"); 
		   		 $("#soundDiv").html("  <audio autoplay><source src='" + url1 + "' type='audio/x-wav'></source></audio> ");		   			
		   		}
			  }
	
		
	} 
  

function join_move() {
	document.loginform.action = "<%=request.getContextPath()%>/member/clause.do";
	document.loginform.submit();
}




</script>
</html>
