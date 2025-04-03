<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.lang.Math;"%>
<%@ include file="../common/taglib.jsp" %>
 <jsp:include page="../common/header.jsp"/>
	<div class="page-contents">
	  <div class="login container">
	    <div class="row">
	      <div class="col s12 offset-m3 m6">
	        <form id="UserForm" name="UserForm" method="post"  >
			    <input type="hidden" name="user_id" id="user_id" value="${user_id}" />			    	
		   		 <input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
				<input type="hidden" value="" name="request_url" />  
	        <div class="page-title">
	          <div>
	            <h2>인증번호발송</h2>
	          </div>
	        </div>
			<div style="font-size: 1.125rem; margin-bottom: 3rem; background:url(../images/icon_certno.png) no-repeat 14px 8px; padding-left:132px;">
              <p>
               메일 인증 후 로그인 하실 수 있습니다.<br>
               메일 인증을 하면 암호문자 입력 없이 로그인이 가능합니다.<br>
			    메일 인증시 쿠키(메일인증)가 생성되어 발급되며, 쿠키 생성이 안된 매개체로 로그인 하는 경우 재인증이 필요합니다.
              </p>
               
              <div><a class="btn btn-block" style="font-size: 1rem; width:170px; color: #000; background-color: #fff; border:1px solid #CCC; box-shadow:none; line-height: 28px;" id="sendbtn" onclick="javascript:return mailSend();">인증번호 발송</a>
              <%-- <a class="btn btn-block" style="font-size: 1rem; width:170px; color: #000; background-color: #fff; border:1px solid #CCC; box-shadow:none; line-height: 28px;" id="sendbtn3" onclick="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data2.do');">샘플메일화면확인</a> --%>
               <div id="ViewTimer" style="width:150px; height:15px; float:left;"></div>
              </div>
            </div>
			<div class="user-info-write">
			  <dl>
			    <dt style="width: 21%;">인증번호 입력</dt>			   
			    <dd><input name="mail_code" type="text" class="kt-input" id="mail_code" title="인증번호"></dd>
                <dd style="width: 30%;"><a class="btn btn-block kt-red" style="margin: 1px 0px 0px;" onclick="javascript:return mailCodeCheck();" href="javascript:" id="sendbtn2">확인</a></dd>
			  </dl>
			</div>
			  </form> 	     
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
 $(document).ready(function () {   //메일인증 페이지 접근 시 타이머 숨김
	    	$("#ViewTimer").hide();	    	
	});
 var t1 = null;
 var t2 = null;
 var tcounter=180;
 var tcounter2=60;
 //인증코드 입력 3분체크 시간함수
  function Timer(){
     tcounter=tcounter-1;
    temp=Math.floor(tcounter/60);
    if(Math.floor(tcounter/60)<10){
         temp='0'+temp;
        }
    temp = temp + ":";
    if((tcounter%60)<10){
               temp = temp + '0';
        }
    temp = "남은 시간 : "+temp +(tcounter%60);
    document.getElementById("ViewTimer").innerHTML=temp;   //타이머 스타트
 if(tcounter == 0){
     tstop();      //3분 시간이 지나면 tstop호출 
	 }
	} 

//3분 타이머 종료 후 tstop함수실행 (Y면 재실행,N이면 로그인)	
function tstop(){ 	
	if(confirm("인증 코드 입력 시간 3분이 경과했습니다. 다시 이메일 인증을 진행하시겠습니까?")){
		$("#ViewTimer").hide();
		mailSend();
	}else{
		$("#ViewTimer").hide();
		var subUrl = '<%=request.getContextPath()%>';	
		document.UserForm.action=subUrl + "/member/idpw_pass.do" ;
		document.UserForm.method ="post";
		document.UserForm.submit();
		return true;
	}
}
	
//1분 시간 체크 타이머 함수
function OneTimer(){	
    tcounter2=tcounter2-1;
   temp=Math.floor(tcounter2/60);
   if(Math.floor(tcounter2/60)<10){
        temp='0'+temp;
       }
       temp = temp + ":";
   if((tcounter2%60)<10){
       temp = temp + '0';
       }
   temp = "남은 시간 : "+temp +(tcounter2%60);
   document.getElementById("ViewTimer").innerHTML=temp;   
	if(tcounter2 == 0){
	    tstop2();       
		 }
		} 
//1분 딜레이 타임 종료 후 tstop2 재실행
function tstop2(){	
	alert("다시 메일 발송이 가능합니다");
   $("#ViewTimer").hide();
   $("#user-info-write").attr('disabled', true);  
   var subUrl = '<%=request.getContextPath()%>';	
	 document.UserForm.action=subUrl + "/member/update_mail_code.do" ;  //입력시간이 끝나면 기존 6자리 숫자 다시 변경해야함
	 document.UserForm.method ="post";
	 document.UserForm.submit();
	 return true;
		}
//다음에 인증하기 함수 - 2022.06.20 삭제

//메일 발송 
function mailSend(){
   	sessioncheck();
   	$("#sendbtn").attr('disabled', true);//메일발송 버튼 잠금
   	$("#mail_code").attr('disabled', false);  //딜레이로 잠금 되었을 때 다시 실행했을 때 입력창 해제
    $.ajax({
		    url : "<%=request.getContextPath()%>/member/mail_send.do",
		    data : $("#UserForm").serialize(),
			type : "POST",
			dataType :"json",
	 	    success:function(data){
	 	    	if(data.result == "mail_closed") {
	 	 	    	alert("해당 계정은 인증번호발송 버튼이 잠겨 있습니다");
	 	 	    	$("#sendbtn").attr('disabled', false);//메일발송 버튼해제
	 	 	    	document.UserForm.action = "<%=request.getContextPath()%>/member/mail_approval.do";
	 	    	    document.UserForm.submit();
	 	    	}else if(data.result == "mail_error") {
		 	    	if(data.result2 != undefined && data.result2 != ''){
						var mail_mask = data.result2.split('@');
						var mail_masked = mail_mask[0].substr(0, mail_mask[0].length - 3) + "***@" + mail_mask[1];
		 	 	    	alert("인증번호발송이 실패했습니다\n사용자님의 Mail( " + mail_masked + " )계정을 확인해주세요");
				 	}else{
				 		alert("인증번호발송이 실패했습니다\n사용자님의 Mail계정을 확인해주세요");
					}

	 	 	    	$("#sendbtn").attr('disabled', false);//메일발송 버튼해제
	 	 	    	document.UserForm.action = "<%=request.getContextPath()%>/member/mail_approval.do";
	 	    	    document.UserForm.submit();
	 	 	    }else if(data.result == "mail_delay"){
	 	 	    	alert("인증번호발송을 1분안에 10회 초과 발송되어 1분간 인증번호발송이 중지됩니다");
	 	 	    	clearInterval(t1);
	 	 	    	document.getElementById("ViewTimer").innerHTML=''; 
	 	 	    	 t2=setInterval(OneTimer,1000); 
	 	 	    	$("#ViewTimer").show();	
	 	 	    	$("#sendbtn").attr('disabled', true);
	 	 	    	$("#mail_code").attr('disabled', true);	 	 	    	
	 	 	    	$("#sendbtn2").attr('disabled', true);
	 	 	    } else {
	 	 	    	alert(data.result2+" 메일주소로 인증번호 발송이 완료되었습니다.\n시스템 상황에 따라 메일발송이 지연 될 수 있습니다.");	
	 	 	    	$("#sendbtn").attr('disabled', false);//메일발송 버튼해제
	 	 	    	  if(t1==null){		 	 	    	 
                          t1=setInterval(function(){
                          Timer();
           	 	 	    }, 1000);	
		 	 	      }else{			 	 	    
						clearInterval(t1);
						t1==null;
						tcounter=180; 							
						document.getElementById("ViewTimer").innerHTML='';   							
						 t1=setInterval(function(){
                        	   Timer();
           	 	 	    	}, 1000);	
		 	 	    	  }	 	 	    		    	
	 	 	    	$("#ViewTimer").show();		 	 	    	 
	 	 	  	}	 	 	  	
		     }
		  }); 
		} 

//메일인증코드 확인함수
function mailCodeCheck(){  
   	 sessioncheck();
   		  $.ajax({
    		url : "<%=request.getContextPath()%>/member/mailcode_check.do",
 		    data : $("#UserForm").serialize(),
			type : "POST",
			dataType :"json",
	 	    success:function(data){
	 	    	if(data.result == "mail_error") {
	 	 	    	alert("인증번호가 다릅니다. 다시 인증코드를 입력해주세요");
	 	 	    	document.UserForm.action = "<%=request.getContextPath()%>/member/mail_approval.do"; //인증번호가 다르면 재실행
	 	    	    document.UserForm.submit();
	 	 	    } else {
	 	 	    	alert("메일 인증이 완료되었습니다");
	 	 	    	document.UserForm.action = "<%=request.getContextPath()%>/main.do"; //인증이 완료되면 메인페이지 이동
	 	    	    document.UserForm.submit();
	 	 	  	}	 	 	  	
		     }
		    });
}
</script>