<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="login container">
	    <div class="row">
	      <div class="col s12 offset-m3 m6">
	        <div class="page-title">
	          <div>
	            <h2>비밀번호 변경</h2>
	          </div>
	        </div>
	        <form id="UserForm" name="UserForm" method="post"  >
			    <input type="hidden" name="user_id" id="user_id" value="${user_id}" />
			    <input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
				<input type="hidden" value="" name="request_url" />
				<div class="user-info-write">
		        	<dl>
				        <dt style="width: 28%;">기존 비밀번호</dt>
				        <dd><input type="password" class="kt-input" title="기존 비밀번호" name="old_pwd" id="old_pwd"  autocomplete="off"></dd>
				    </dl>
		        	<dl>
				        <dt style="width: 28%;">신규 비밀번호</dt>
				        <dd><input type="password" class="kt-input" title="신규 비밀번호 " name="new_pwd" id="new_pwd" autocomplete="off"></dd>
				    </dl>
		        	<dl>
				        <dt style="width: 28%;">신규 비밀번호 확인</dt>
				        <dd><input type="password" class="kt-input" title="신규 비밀번호 확인 " name="new_c_pwd" id="new_c_pwd" onkeydown="if(event.keyCode == 13) {return checkMsg();}" autocomplete="off"></dd>
				    </dl>
				    <dl>
				        <dt></dt>
				        <dd>
				          <p>
				            <i class="material-icons kt-red-text">info_outline</i> 비밀번호는 영문자, 숫자, 특수문자 조합이면 8자 이상입니다.
				          </p>
				        </dd>
				      </dl>
		        </div>
	        </form>
	        
	        <a href="javascript:" onclick="javascript:return checkMsg();" class="btn btn-block kt-red">확인</a>
	      </div>
	    </div>
	  </div>
	</div>
	
	<jsp:include page="../common/footer.jsp" />
</html>

<script>
 var RealUrl = location.href;
 if(RealUrl.indexOf("http:") != -1 && (RealUrl.indexOf("kt-egov") != -1 || RealUrl.indexOf("gns.kt.com") != -1)) {
 	RealUrl = RealUrl.replace('http:','https:');
 	document.pageForm.action = RealUrl;
 	document.pageForm.submit();
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
//<!-- 2017.12.22  보안성검토 비번 체크로직 추가 -->
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
          alert("연속된 문자열을 4자 이상 사용할 수 없습니다");
          return false;
          } 

  	   if( SamePass_2 > 1){  	
           alert("연속된 문자열을 4자 이상 사용할 수 없습니다");
           return false;
           } 
          
        
	return true;
 }
//<!-- 2017.12.22  보안성검토 비번 체크로직 추가 -->
function validate_samepass2(pwd2,uid2){
	
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

function checkMsg(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		

	var subUrl = '<%=request.getContextPath()%>';	
	//if((event.keyCode != 13)&&(event.keyCode != 0)) return false;

    //기존 비밀번호 자릿수
    if (document.UserForm.elements["old_pwd"].value.length < 8) {
        alert("기존 비밀번호는 반드시 8자 이상 입력해 주세요!");
        document.UserForm.old_pwd.focus();
        document.UserForm.old_pwd.select();
        return false;
    }
    
    //신규 비밀번호 자릿수
    if (document.UserForm.elements["new_pwd"].value.length < 8) {
        alert("신규 비밀번호는 반드시 8자 이상 입력해 주세요!");
        document.UserForm.new_pwd.focus();
        document.UserForm.new_pwd.select();
        return false;
    }
  //<!-- 2017.12.22  보안성검토 비번 체크로직 추가 -->
    if (document.UserForm.elements["new_pwd"].value.indexOf(" ") != -1){
        alert("비밀번호에 공백이 있습니다. 다시 입력해 주십시오.");
        return false;
    }
  //<!-- 2017.12.22  보안성검토 비번 체크로직 추가 -->
    //비밀번호 동일 문자 연속문자 체크
    if(!validate_samepass(document.UserForm.elements["new_pwd"].value)) {                    
           document.UserForm.new_pwd.focus();
           document.UserForm.new_pwd.select();
           return false;
       }
  //<!-- 2017.12.22  보안성검토 비번 체크로직 추가 -->
    //ID와 PW비교하여 ID에서 연속되는 문자 4개 있으면 리턴
     if(!validate_samepass2(document.UserForm.elements["new_pwd"].value,document.UserForm.elements["user_id"].value)) {                    
        document.UserForm.new_pwd.focus();
        document.UserForm.new_pwd.select();
        return false;
    } 
    //비밀번호 영문자, 숫자, 특수문자 조합    
    if(!validate_password(document.UserForm.elements["new_pwd"].value)) {
        alert("비밀번호는 영문자, 숫자, 특수문자가 혼용되어야 합니다!");
        document.UserForm.new_pwd.focus();
        document.UserForm.new_pwd.select();
        return false;
    }

    //사용자ID 랑 비밀번호 체크
    if(document.UserForm.elements["user_id"].value == document.UserForm.elements["new_pwd"].value) {
      	alert("아이디와 비밀번호는 같게 신청하실 수 없습니다. 비밀번호를 다시 입력해 주십시오.");
        document.UserForm.new_pwd.focus();
        document.UserForm.new_pwd.select();
        return false;
    }
 
   //기존 비번이랑 신규 비번이랑 동일 할 경우
    if(document.UserForm.elements["old_pwd"].value == document.UserForm.elements["new_pwd"].value) {
      	alert("신규 비밀번호가 기존 비밀번호랑 동일합니다.");
        document.UserForm.new_pwd.focus();
        document.UserForm.new_pwd.select(); 
        return false; 
    } 
    
    //신규 비번이랑 확인 비번 동일한지 체크 
    if(document.UserForm.elements["new_pwd"].value != document.UserForm.elements["new_c_pwd"].value) {
      	alert("확인 입력한 비밀번호가 틀립니다.");
        document.UserForm.new_c_pwd.focus();
        document.UserForm.new_c_pwd.select();
        return false;
    } 
	
    //document.UserForm.action=subUrl + "/member/idpw_change_done.do" ;
    //document.UserForm.method ="post";
    //document.UserForm.submit(); 
	//return true;

	if(RealUrl.indexOf("kt-egov") != -1) {
		document.UserForm.action="https://www.kt-egov.co.kr/member/idpw_change_done.do";
	} else if(RealUrl.indexOf("gns.kt.com") != -1) {
		document.UserForm.action="https://gns.kt.com/member/idpw_change_done.do";
	} else {
		document.UserForm.action=subUrl + "/member/idpw_change_done.do" ;
	}
	document.UserForm.method ="post";
	document.UserForm.submit();
	return true;
}

if("${error}" != ""){
	alert("${error}");
}
</script>