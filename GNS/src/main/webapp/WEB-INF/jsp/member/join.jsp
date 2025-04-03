
<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : join.jsp
* Overview    : 회원가입 화면 
* Description : 회원가입 화면
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
<div class="page-contents">
	<div class="join container">
		<div class="page-title">
			<div>
				<c:choose>
					<c:when test="${flag == 'U'}">
						<h2>My Info</h2>
						<p>
							kt그룹 국가정보통신서비스의<span class="kt-red-text">My Info</span>입니다.
						</p>
					</c:when>
					<c:otherwise>
						<h2>회원가입</h2>
						<p>
							회원가입을 하시면 보다 많은 정보를 얻으실 수 있습니다.<br /> kt그룹 국가정보통신서비스는 <span
								class="kt-red-text">효율적, 안정적</span> 운영을 위한 상시 감시 및 복구 체계를 구축하고
							있습니다.
						</p>
					</c:otherwise>
				</c:choose>

			</div>
			<ul>
				<c:choose>
					<c:when test="${flag == 'U'}">
						<li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        			<li><a href="javascript:menuMove('<%=request.getContextPath()%>/member/member_edit.do');">My Info</a></li>
	        
					</c:when>
					<c:otherwise>
						<li>1. 14세 미만 어린이 확인</li>
				        <li>2. 약관 및 방침 동의</li>
				        <li><span class="kt-red-text">3. 회원정보 입력</span></li>
				        <li>4. 완료</li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
		<h3>회원 정보 입력</h3>
		<!-- 2017.12.22  보안성검토 선택동의 및 ID중복체크 수정 추가 -->
		<form name="UserForm" id="UserForm" method="post">
		<input type="hidden" name="ucheck1"  value="${ucheck1}" />
		<input type="hidden" name="ucheck2"  value="${ucheck2}" />
		<input type="hidden" name="ucheck3"  value="${ucheck3}" />
		
			<input type="hidden" name="user_stat" value="00" />  <input
				type="hidden" name="user_id2" value="" /><input
				type="hidden" name="id_check" value="N" /> <input type="hidden"
				name="flag" value="${flag}" /> <input type="hidden"
				value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
				name="request_token" /> <input type="hidden" value=""
				name="request_url" />
			<c:if test="${flag == 'U'}">
				<input type="hidden" value="user_id" name="user_id" />
				<input type="hidden" value="user_nm" name="user_nm" />
			</c:if>
			<div class="user-info-write">
				<dl>
					<dt><span class="kt-red-text">*</span>&nbsp;이름</dt>
					<dd>
					<!-- 2019.09.26  모의해킹 마스킹 수정 사용자 이름 회원가입 수정 분기처리 -->
					<!-- 2019.09.26  모의해킹 마스킹 수정 사용자 이름 마스킹처리 -->
					<c:choose>
							<c:when test="${flag != 'U'}">
								<input type="text" class="kt-input" title="이름" name="user_nm"
									id="user_nm" value="" >
									
							</c:when>
							
							<c:otherwise>
								<span id="user_nm" >${fn:substring(memberVo.user_nm,0,fn:length(memberVo.user_nm)-1)}*</span>
							</c:otherwise>
						</c:choose>
					
						<%-- <input type="text" class="kt-input" title="이름" name="user_nm"
							id="user_nm" value="${fn:substring(memberVo.user_nm,0,fn:length(memberVo.user_nm)-1)}*"> --%>
					</dd>
				</dl>
				<dl>
					<dt><span class="kt-red-text">*</span>&nbsp;아이디</dt>
					<dd>
						<c:choose>
							<c:when test="${flag != 'U'}">
								<input type="text" class="kt-input" title="아이디" name="user_id"
									id="user_id" value="" >
									<!-- 2017.12.22  보안성검토 아이디중복체크 수정 -->
									<!-- 2019.09.26  모의해킹 마스킹 수정 사용자 ID 마스킹처리 -->
								 <span class="btn" 	onclick="javascript:userId_check();">ID 중복검색</span> 
									
								<span id="user_id_msg" class="kt-red-text"
									style="margin-left: 15px;">ID 중복검색을 하여 확인하세요.</span>
							</c:when>
							
							<c:otherwise>
								<span id="user_id" >${fn:substring(memberVo.user_id,0,fn:length(memberVo.user_id)-3)}***</span>
							</c:otherwise>
						</c:choose>
					</dd>
				</dl>
				<dl>
					<dt><span class="kt-red-text">*</span>&nbsp;비밀번호</dt>
					<dd>
						<input type="password" class="kt-input" title="비밀번호" id="user_pw" value="" autocomplete="off">
						<input name="user_pw" type="hidden" id="en_user_pw" />
						<span class="kt-red-text"> 비밀번호는 8자리 이상 문자,숫자,특수문자로 조합하여야
							등록됩니다.</span>
					</dd>
				</dl>
				<dl>
					<dt><span class="kt-red-text">*</span>&nbsp;비밀번호 확인</dt>
					<dd>
						<input type="password" class="kt-input" title="비밀번호확인"
							id="user_pw1" value="" autocomplete="off">
					</dd>
				</dl>
				<c:choose>
					<c:when test="${flag != 'U'}">
						<dl>
							<dt><span class="kt-red-text">*</span>&nbsp;이메일</dt>
							<dd>
								<input type="email" class="kt-input" title="이메일" name="user_mail"
									id="user_mail" value="${memberVo.user_mail}">
							</dd>
						</dl>
						<dl>
							<dt><span class="kt-red-text">*</span>&nbsp;전화번호</dt>
							<dd>
								<input type="text" class="kt-input min" title="전화번호" name="tel1"
									id="tel1" value="${memberVo.tel1}" maxlength="4" size="4">
								- <input type="text" class="kt-input min" title="전화번호"
									name="tel2" id="tel2" value="${memberVo.tel2}" maxlength="4"
									size="4"> - <input type="text" class="kt-input min"
									title="전화번호" name="tel3" id="tel3" value="${memberVo.tel3}"
									maxlength="4" size="4">
							</dd>
						</dl>
						<dl>
							<dt><span class="kt-red-text">*</span>&nbsp;휴대전화번호</dt>
							<dd>
								<input type="text" class="kt-input min" title="휴대폰번호번호"
									name="mobile1" id="mobile1" value="${memberVo.mobile1}"
									maxlength="4" size="4"> - <input type="text"
									class="kt-input min" title="휴대폰번호번호" name="mobile2" id="mobile2"
									value="${memberVo.mobile2}" maxlength="4" size="4"> - <input
									type="text" class="kt-input min" title="휴대폰번호번호" name="mobile3"
									id="mobile3" value="${memberVo.mobile3}" maxlength="4" size="4">
							</dd>
						</dl>	
					</c:when>
					
					<c:otherwise>
						<dl>
							<dt><span class="kt-red-text">*</span>&nbsp;이메일</dt>
							<dd>
								<span class="mr_5">
									${fn:substring(memberVo.user_mail,0,fn:indexOf(memberVo.user_mail,'@')-3)}***@${fn:substring(memberVo.user_mail,fn:indexOf(memberVo.user_mail,'@')+1,fn:length(memberVo.user_mail))}
								</span>
								<span style="display:none;" class="u_input_1 inputArrow">
									<input type="email" class="kt-input" title="이메일" name="user_mail" id="user_mail" value="">
									<span class="kt-red-text">입력된 정보로 등록됩니다.</span>
								</span>
								<a href="javascript:" class="btn kt-red" onClick="javascript:open_update(1, this);">변경</a>
								
							</dd>
						</dl>
						<dl>
							<dt><span class="kt-red-text mr_5">*</span>&nbsp;전화번호</dt>
							<dd>
								<span class="mr_5">
									${memberVo.tel1} - ${fn:substring(memberVo.tel2,0,fn:length(memberVo.tel2)-2)}** - *${fn:substring(memberVo.tel3,1,fn:length(memberVo.tel3))}
								</span>
								<span style="display:none;" class="u_input_2 inputArrow">
								  <input type="text" class="kt-input min" title="전화번호" name="tel1"
									id="tel1" value="" maxlength="4" size="4">
								- <input type="text" class="kt-input min" title="전화번호"
									name="tel2" id="tel2" value="" maxlength="4"
									size="4"> - <input type="text" class="kt-input min"
									title="전화번호" name="tel3" id="tel3" value=""
									maxlength="4" size="4">
									<span class="kt-red-text mr_5">입력된 정보로 등록됩니다.</span>
								</span>
								<a href="javascript:" class="btn kt-red" onClick="javascript:open_update(2, this);">변경</a>
							</dd>
						</dl>
						<dl>
							<dt><span class="kt-red-text">*</span>&nbsp;휴대전화번호</dt>
							<dd>
								<span class="mr_5">
									${memberVo.mobile1} - ${fn:substring(memberVo.mobile2,0,fn:length(memberVo.mobile2)-2)}** - *${fn:substring(memberVo.mobile3,1,fn:length(memberVo.mobile3))}
								</span>
								<span style="display:none;" class="u_input_3 inputArrow">
								<input type="text" class="kt-input min" title="휴대폰번호번호"
									name="mobile1" id="mobile1" value=""
									maxlength="4" size="4"> - <input type="text"
									class="kt-input min" title="휴대폰번호번호" name="mobile2" id="mobile2"
									value="" maxlength="4" size="4"> - <input
									type="text" class="kt-input min" title="휴대폰번호번호" name="mobile3"
									id="mobile3" value="" maxlength="4" size="4">
									<span class="kt-red-text mr_5">입력된 정보로 등록됩니다.</span>
								</span>
								<a href="javascript:" class="btn kt-red" onClick="javascript:open_update(3, this);">변경</a>
							</dd>
						</dl>
					</c:otherwise>
				</c:choose>
				
				<dl>
					<dt></dt>
					<dd>
						<p>
							기존 초고속 국가망 서비스를 사용하고 계신 분들은 아래 항목을 입력해 주시면 확인후 전용회원으로 승격해 드리겠습니다.<br />
							전용회원이 되셔야 고객 센터의 서비스(트래픽조회 서비스 등)를 이용하실 수 있습니다.
						</p>
					</dd>
				</dl>
				<dl>
					<dt>회선번호</dt>
					<dd>
						<input type="text" class="kt-input" title="회선번호" name="ll_id"
							id="ll_id" value="${memberVo.ll_id}" maxlength="12">
					</dd>
				</dl>
				<dl>
					<dt></dt>
					<dd>
						<p>하나의 회선번호 입력시 나머지 회선번호는 자동 등록 됩니다.</p>
					</dd>
				</dl>
				<dl>
					<dt>회원등급</dt>
					<dd>
						<c:if
							test="${memberVo.user_auth_id==null || memberVo.user_auth_id=='10'}">
							<span id="user_auth_nm">일반회원</span>
							<input type="hidden" name="user_auth_id" id="user_auth_id"
								title="회원 등급" value="10" />
						</c:if>
						<c:if test="${memberVo.user_auth_id=='20'}">
							<span id="user_auth_nm">전용회원</span>
							<input type="hidden" name="user_auth_id" id="user_auth_id"
								title="회원 등급" value="20" />
						</c:if>
						<c:if test="${memberVo.user_auth_id=='30'}">
							<span id="user_auth_nm">상담관리자</span>
							<input type="hidden" name="user_auth_id" id="user_auth_id"
								title="회원 등급" value="30" />
						</c:if>
						<c:if test="${memberVo.user_auth_id=='40'}">
							<span id="user_auth_nm">전체관리자</span>
							<input type="hidden" name="user_auth_id" id="user_auth_id"
								title="회원 등급" value="40" />
						</c:if>
					</dd>
				</dl>
			</div>
		</form>
		<div class="center">
			<a href="javascript:" class="btn kt-red"
				onClick="javascript:check_data();"> <c:choose>
					<c:when test="${flag == 'U'}">수정</c:when>
					<c:otherwise>회원가입</c:otherwise>
				</c:choose>
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

var init = "${init_yn}";
if(init == "Y"){
	alert('비밀번호가 초기화 되었습니다. \n현재 화면에서 비밀번호를 수정바랍니다.');
}

function validate_username(urstr){
	
    var i; var result=0;
    filteredValues = " <>{}[],./!@#$%^&*()_+|\=-?''";
	
    for(i=0; i<urstr.length; i++) {
        if((urstr.charAt(i) >= "A") && (urstr.charAt(i) <= "Z")){
            result = true;
        }
        else{
            if((urstr.charAt(i) >= "a") && (urstr.charAt(i) <= "z")){
                result = true;
            }
            else{
                if((urstr.charAt(i) >= "0") && (urstr.charAt(i) <= "9")){
                    result = true;
                }
                else {
                   return false;
                }
            }
        }
    }

    if(result == true){
        return true;
    }
        
}
//<!-- 2017.12.22  보안성검토 비밀번호 체크 로직 추가 -->
 function validate_samepass(pwd){
	 
	var SamePass_0=0;
	var SamePass_1=0;
	var SamePass_2=0;
	var chr_pass_0;
	var chr_pass_1;
	var chr_pass_2;
	
	for(var i=0; i< pwd.length; i++){
	  chr_pass_0= pwd.charAt(i);
	  chr_pass_1= pwd.charAt(i+1);
	 
	 

	  if(chr_pass_0 == chr_pass_1 ){		
             SamePass_0 = SamePass_0+1  
           
		  }

	  chr_pass_2= pwd.charAt(i+2);

       if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0)==1 && chr_pass_1.charCodeAt(0) - chr_pass_2.charCodeAt(0)==1)
           {
    	   
           SamePass_1 = SamePass_1 +1
          
           }	

      if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0)== -1 && chr_pass_1.charCodeAt(0) - chr_pass_2.charCodeAt(0)==-1)
          {    	
         
           SamePass_2 = SamePass_2 +1   
                
          }       
       
	}

	
	if(SamePass_0 > 2){
        alert("동일문자를 4번이상 사용할 수 없습니다");
        return false;
        }      
            
  	     if(SamePass_1 > 1){  	
          alert("연속된 문자 혹은 숫자를 4자 이상 사용할 수 없습니다");
          return false;
          } 

  	   if( SamePass_2 > 1){  	
           alert("연속된 문자 혹은 숫자를 4자 이상 사용할 수 없습니다");
           return false;
           } 
          
        
	return true;
 }
//<!-- 2017.12.22  보안성검토 비밀번호 체크 로직 추가 -->
 function validate_samepass2(pwd2){
	    var uid2 = document.UserForm.elements["user_id"].value;
	    var uid3 = document.UserForm.elements["user_id2"].value ;
	    var flag = document.UserForm.elements["flag"].value; 
	    if(flag !="U" && uid3 != uid2){
	    	alert("ID중복 체크를 해 주세요!");	       
	        $("#user_id_msg").html("ID 중복검색을 하여 확인하세요.");
	        document.UserForm.id_check.value = "N";	         
	        return false;
		    }
	 
	    	var chr_id_0;
			var chr_id_1;
			var chr_id_2;
			var chr_id_3;    
		    
		 for(var i=0; i< uid2.length-3; i++){
			 chr_id_0= uid2.charAt(i);
			 chr_id_1= uid2.charAt(i+1);
			 chr_id_2= uid2.charAt(i+2);
			 chr_id_3= uid2.charAt(i+3);
			 chr_id_4= chr_id_0+chr_id_1+chr_id_2+chr_id_3;
			 
			 if(pwd2.indexOf(chr_id_4)!=-1){
		         alert("ID와 동일한 연속문자를 4번이상 사용하실 수 없습니다");
		         return false;
					 } 
		 }
		 return true;
	   
 }




function validate_password(urstr){

    var i; 
    var result0 = 0;
    var result1 = 0;
    var result2 = 0;

    filteredValues = "<>{}[],./!@#$%^&*()_+|\=-?''";

    
	
    for(i=0; i<urstr.length; i++) {

        if((urstr.charAt(i) >= "A") && (urstr.charAt(i) <= "Z")){
            result0 = 100;
        }
        else{
            if((urstr.charAt(i) >= "a") && (urstr.charAt(i) <= "z")){
                result0 = 100;
            }
            else{
                if((urstr.charAt(i) >= "0") && (urstr.charAt(i) <= "9")){
                    result1 = 10;
                }
                else {
                    
                    if(!(filteredValues.indexOf(urstr.charAt(i)) == -1)){
                        result2 = 1;
                    }
                    else {
                    	result2 = 0;
                    }
                }
            }
        }
    }

    if((result0+result1+result2) == 111){
        return true;
    }else{
        return false;
    }   
}

function validate_user_mail(user_mail){

	if (user_mail.length == 0) {
	    alert("E-Mail을 입력해 주십시오.");
	    return false;
	}
		

    if (!validate_ascii_data (user_mail)) {
        alert("E-Mail에 잘못된 문자가 있습니다. 다시 입력해 주십시오.");
        return false;
    }

    var invalidChars = "\"|&;<>!*\'\\"   ;

    for (var i = 0; i < invalidChars.length; i++) {
        if (user_mail.indexOf(invalidChars.charAt(i)) != -1) {
            alert("E-Mail에 잘못된 문자가 있습니다. 다시 입력해 주십시오!.");
            return false;
        }
    }

    if (user_mail.indexOf("@")==-1){
        alert("E-Mail이 잘못되었습니다. '@'가 있는지 확인해 주십시오.");
        return false;
    }

    if (user_mail.indexOf(" ") != -1){
        alert("E-Mail에 공백이 있습니다. 다시 입력해 주십시오.");
        return false;
    }

    if (window.RegExp) {
        var reg1str = "(@.*@)|(\\.\\.)|(@\\.)|(\\.@)|(^\\.)";
        var reg2str = "^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$";

        var reg1 = new RegExp (reg1str);
        var reg2 = new RegExp (reg2str);
        if (reg1.test(user_mail) || !reg2.test(user_mail)) {
            alert("E-Mail에 잘못된 문자가 있습니다. 다시 입력해 주십시오.");
            return false;
        }
    }
    return true;
}

function validate_ascii_data(urstr){
    var i;

    for(i=0; i<urstr.length; i++) {
        if((urstr.charAt(i) < " ") || (urstr.charAt(i) > "~"))
        return false;
    }
    return true;
}

function validate_numeric_data(urstr){
    var i;
    var result = 0;
    for(i=0; i<urstr.length; i++) {
        
        if (((urstr.charAt(i) >= "0") && (urstr.charAt(i) <= "9"))){
            result = true;
        }
        else {
            return false;
        }
    }
    if(result == true){
        return true;
    }
}

function check_data(){
	
	var actionUrl;
	var id;
	var name;
	var flag = document.UserForm.elements["flag"].value;

	//var name = document.UserForm.elements["user_nm"].value;
	//<!-- 2019.09.26  보안성검토 마스킹처리 분기처리 -->
	if(flag != "U") {
		name = document.UserForm.elements["user_nm"].value;
	} 
	if(flag != "U") {
		id = document.UserForm.elements["user_id"].value;
	} 
	
    var password = $("#user_pw").val();
    var confirm = $("#user_pw1").val();
    
    
   	 //<!-- 2019.09.26  보안성검토 마스킹처리 분기처리 -->
    //ID Check
    if(flag != "U") {	 
    	// 이름체크
       
       	if (name.length == 0) {
            alert("이름을 입력해 주세요!");
            document.UserForm.user_nm.focus();
            document.UserForm.user_nm.select();
            return false;
       	}   
    	//회원 ID Check
    	if (id.length == 0) {
	        alert("ID을 입력해 주세요!");
	        document.UserForm.user_id.focus();
	        document.UserForm.user_id.select();
	        return false;
    	} else if (id.length < 4) {
        	alert("회원 아이디는 반드시 4자 이상 입력해 주세요!");
        	document.UserForm.user_id.focus();
        	document.UserForm.user_id.select();
        	return false;
    	} else {
        	if(!validate_username(id)) {
            	alert("아이디는 영문자나 숫자만 입력 하실수 있습니다!");
            	document.UserForm.user_id.focus();
            	document.UserForm.user_id.select();
            	return false;
        	}
    	}
	}
	
    //id 중복 체크 확인
	if ((flag != "U") && (document.UserForm.id_check.value != "Y")) {		
		alert("ID중복 체크를 해 주세요!");
        document.UserForm.user_id.focus();
        document.UserForm.user_id.select();
        return false;
	}

	//<!-- 2017.12.22  보안성검토 비밀번호 체크 로직 추가 -->
	 if (($("#user_pw").val()).indexOf(" ") != -1){
		alert("비밀번호에 공백이 있습니다. 다시 입력해 주십시오.");
		$("#user_pw").focus();
		$("#user_pw").select();
		return false;
	}
	
    //비밀번호 Check
    if (($("#user_pw").val()).length < 8) {
        alert("비밀번호는 반드시 8자 이상 입력해 주세요!");
        $("#user_pw").focus();
        $("#user_pw").select();
        return false;
    }
    else if (($("#user_pw1").val()).length == 0) {
        alert("비밀번호 확인을 입력해 주세요!");
        $("#user_pw1").focus();
        $("#user_pw1").select();
        return false;
    }
    else {

      //<!-- 2017.12.22  보안성검토 비밀번호 체크 로직 추가 ID와 PW비교하여 ID에서 연속되는 문자 4개 있으면 리턴 -->
      
         if( !validate_samepass2($("#user_pw").val())) { 
        	 if((flag != "U") && document.UserForm.id_check.value != "Y"){
            	 document.UserForm.user_id.focus();
                 document.UserForm.user_id.select();
                 return false;
                }else{
                	$("#user_pw").focus();
                	$("#user_pw").select();
                     return false;
                    }   
                           
            
        }
     	//<!-- 2017.12.22  보안성검토 비밀번호 체크 로직 추가 비밀번호 동일 문자 연속문자 체크 -->
    	 
         if(!validate_samepass($("#user_pw").val())) { 
        	 $("#user_pw").focus();
        	 $("#user_pw").select();
             return false;                 
               
            } 
        if(!validate_password($("#user_pw").val())) {
            alert("비밀번호는 영문자, 숫자, 특수문자가 혼용되어야 합니다!");
            $("#user_pw").focus();
            $("#user_pw").select();
            return false;
        }
        else {
            if(id == password) {
                alert("아이디와 비밀번호는 같게 신청하실 수 없습니다. 비밀번호를 다시 입력해 주십시오.");
                $("#user_pw").focus();
                $("#user_pw").select();
                return false;
            }
        }
    }

    //비밀번호 확인 Check
    if(password != confirm) {
                alert("비밀번호와 비밀번호확인이 서로 틀립니다.");
                $("#user_pw").focus();
                $("#user_pw").select();
                return false;
     } 


	// Client pw RSA 추가
    var rsa = new RSAKey();
	rsa.setPublic('${setKey.publicKeyModulus}', '${setKey.publicKeyExponent}');
	$("#en_user_pw").val(rsa.encrypt($("#user_pw").val()));

    //E-Mail Check
    if($("#user_mail").is(":visible")){
	    if (!validate_user_mail(document.UserForm.elements["user_mail"].value)) {
	        document.UserForm.user_mail.focus();
	        document.UserForm.user_mail.select();
	        return false;
	    }
    }
    
    if($("#tel1").is(":visible")){
        var phone1 = document.UserForm.elements["tel1"].value;
        var phone2 = document.UserForm.elements["tel2"].value;
        var phone3 = document.UserForm.elements["tel3"].value;

      //전화번호 Check   
        if ( (phone1 == "") || (phone2 == "") || (phone3 == "") ) {
            alert("전화번호를 입력해 주세요!");

            if ( phone1 == "" ) {
                document.UserForm.tel1.focus();
                document.UserForm.tel1.select();
                return false;
            }
            else if ( phone2 == "" ) {
                document.UserForm.tel2.focus();
                document.UserForm.tel2.select();
                return false;
            }
            else if ( phone3 == "" ) {
                document.UserForm.tel3.focus();
                document.UserForm.tel3.select();
                return false;
            }
        }
        else if (!validate_numeric_data(document.UserForm.elements["tel1"].value)) {
            alert("전화번호의 지역번호를 숫자로 입력해 주십시오.");
            document.UserForm.elements["tel1"].focus();
            document.UserForm.elements["tel1"].select();
            return false;
        }
        else if (!validate_numeric_data(document.UserForm.elements["tel2"].value)) {
            alert("전화번호의 국번을 숫자로 입력해 주십시오.");
            document.UserForm.elements["tel2"].focus();
            document.UserForm.elements["tel2"].select();
            return false;
        }
        else if (!validate_numeric_data(document.UserForm.elements["tel3"].value)) {
            alert("전화번호의 뒷자리 번호를 숫자로 입력해 주십시오.");
            document.UserForm.elements["tel3"].focus();
            document.UserForm.elements["tel3"].select();
            return false;
        }
    }
    
    if($("#mobile1").is(":visible")){
    	 var mobile1 = document.UserForm.elements["mobile1"].value;
    	    var mobile2 = document.UserForm.elements["mobile2"].value;
    	    var mobile3 = document.UserForm.elements["mobile3"].value;

    	    

    	    //휴대전화번호 Check
    	    if ( (mobile1 == "") || (mobile2 == "") || (mobile3 == "") ) {
    	        alert("휴대전화번호를 모두 입력해 주세요!");
    	        if (mobile1 == "" ) {
    	            document.UserForm.mobile1.focus();
    	            document.UserForm.mobile1.select();
    	            return false;
    	        }
    	        else if ( mobile2 == "" ) {
    	            document.UserForm.mobile2.focus();
    	            document.UserForm.mobile2.select();
    	            return false;
    	        }
    	        else if ( mobile3 == "" ) {
    	            document.UserForm.mobile3.focus();
    	            document.UserForm.mobile3.select();
    	            return false;
    	        }
    	    }
    	    else if (!validate_numeric_data(document.UserForm.elements["mobile1"].value)) {
    	        alert("휴대전화번호의 앞자리 번호를 숫자로 입력해 주십시오.");
    	        document.UserForm.elements["mobile1"].focus();
    	        document.UserForm.elements["mobile1"].select();
    	        return false;
    	    }
    	    else if (!validate_numeric_data(document.UserForm.elements["mobile2"].value)) {
    	        alert("휴대전화번호의 가운데자리 번호를 숫자로 입력해 주십시오.");
    	        document.UserForm.elements["mobile2"].focus();
    	        document.UserForm.elements["mobile2"].select();
    	        return false;
    	    }
    	    else if (!validate_numeric_data(document.UserForm.elements["mobile3"].value)) {
    	        alert("휴대전화번호의 뒷자리 번호를 숫자로 입력해 주십시오.");
    	        document.UserForm.elements["mobile3"].focus();
    	        document.UserForm.elements["mobile3"].select();
    	        return false;
    	    }
    }
    
    if(flag == "U") {
    	if(RealUrl.indexOf("kt-egov") != -1) {
    		actionUrl = "https://www.kt-egov.co.kr/member/member_update_register.do";
    	} else if(RealUrl.indexOf("gns.kt.com") != -1) {
    		actionUrl = "https://gns.kt.com/member/member_update_register.do";
    	} else {
    		actionUrl = "<%=request.getContextPath()%>/member/member_update_register.do";
    	}
    }
    else {
    	if(RealUrl.indexOf("kt-egov") != -1) {
    		actionUrl = "https://www.kt-egov.co.kr/member/member_insert_register.do";
    	} else if(RealUrl.indexOf("gns.kt.com") != -1) {
    		actionUrl = "https://gns.kt.com/member/member_insert_register.do";
    	} else {
    		actionUrl = "<%=request.getContextPath()%>/member/member_insert_register.do";
    	}
    }

    $.ajax({
	    url : actionUrl,
	    data : $("#UserForm").serialize(),
		type : "POST",
		dataType :"json",
 	    success:function(data){
 	 	    if(data.result == "pw_error") {
 	 	    	alert("로직변경이 발생했습니다!");
 	 	    	document.UserForm.action = "<%=request.getContextPath()%>/error.do";
 	    	    document.UserForm.submit();
 	 	    } else {
 	 	    	if (data.result == "1") {
 	 	    		if(flag == "U") {
 	 	    	    	alert("수정되었습니다.");
 	 	    	    	actionUrl = "<%=request.getContextPath()%>/member/member_edit.do";
 	 	 	    	    document.UserForm.submit();
 	 	    	    } else {
 	 	    	    	alert("회원이 가입되었습니다.\n(승인단계를 거처야 정상적으로 이용이 가능합니다.)");
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
 		 	    }
 	 	    	else {
 	 	    		if(flag == "U") {
 	 	    	    	alert("수정에 실패하였습니다.");
 	 	    	    } else {
 	 	    	    	alert("회원가입에 실패하였습니다.)");
 	  	 	    	}
 		 	    }
 	 	 	}
	     },
	     error:function(request,status,error){
	    	 alert(request + " : " + status + " : " + error);
	     }
	  });
}

//user id check
function userId_check() {
	var param = document.UserForm.user_id.value;
	if(document.UserForm.user_id.value == ""){
		alert("아이디를 확인하세요.");
		UserForm.user_id.focus();
		return false;
	}
	 
 	$.ajax({
	    url : "<%=request.getContextPath()%>/member/user_id_check.do",
	    data : "user_id="+param
	    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href,
		type : "POST",
		dataType :"json",
 	    success:function(data){
 	    	if (data.user_id == "") {
	 	    	document.UserForm.id_check.value = "Y";	 
	 	    	//<!-- 2017.12.22  보안성검토 아이디중복체크 수정 -->	    	
	 	    	document.UserForm.user_id2.value = param;
	 	    	$("#user_id_msg").html("사용가능한 아이디 입니다.");
	 	    }
 	    	else {
	 	    	alert("이미 사용하는 아이디 입니다. 다시 입력하세요");
	 	    	$("#user_id_msg").html("이미 사용하는 아이디 입니다. 다시 입력하세요.");
	 	    }
	     },
	     error:function(request,status,error){
	      alert("[error] 아이디 확인중 에러가 발생했습니다 잠시후에 확인하세요");
	     }
	  });	  
}

// open_update
function open_update(num, tbtn){
	if($(".u_input_"+num).is(":visible")){
		$(".u_input_"+num).hide();
		$(tbtn).text("변경");
		$(".u_input_"+num).find("input").each(function(){
			$(this).val("");
		});
	}else{
		$(".u_input_"+num).show();
		$(tbtn).text("취소");
	}
	
	
}
</script>
</html>

