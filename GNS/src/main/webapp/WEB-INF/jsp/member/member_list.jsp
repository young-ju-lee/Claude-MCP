<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : member_list.jsp
* Overview    : 가입자 리스트 화면 
* Description : 가입자 리스트 화면
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

<!-- Container Start -->
<div class="page-contents">
	  <div class="container">   
	    <div class="page-title">
	      <div>
	        <h2>회원 관리</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">회원 관리</span>입니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>관리자</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/member/login_list.do');">회원 관리</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">회원 관리</a>
	      </div>
	      <ul>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/member/member_approval.do');" >회원 관리</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/admin/popup_list.do');">팝업창 관리</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/member/login_list.do');">접속자 통계</a>
	        </li>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/member/stat_list.do');">메뉴별 접속 통계</a>
	        </li>  
	      </ul>
	    </div>
	<!-- contents Start -->
    <form action="<%=request.getContextPath()%>/member/member_list.do" name="fm_search" method="post">
	    <input type="hidden" name="page" value="1"/>
	    <input type="hidden" name="p_search_group" value="${memberVo.p_search_group}"/>
	    <input type="hidden" name="p_search_value" value="${memberVo.p_search_value}"/>
	    <input type="hidden" name="search_yn" value="N"/>
	    <input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
		<div class="row">
       		<div class="col s12">
				<div class="select-wrap">
	            	<select name="search_group"  id="search_group">
	            		<option value="member_id">아이디</option>
	            		<option value="member_name" <c:if test="${memberVo.p_search_group == 'member_name'}">selected</c:if>>이름</option>
	            	</select>
	            </div>
            	<input class="kt-input right-m" name="search_value" id="search_value" title="이름" type="text" value="${memberVo.p_search_value}" />
                <a href="javascript:" class="btn" id="search_btn">찾기</a>                
                <a href="javascript:" class="btn" onclick="maillock_all('N');"> 메일ON</a>	            
                <a href="javascript:" class="btn" onclick="maillock_all('Y');"> 메일OFF</a>	
             </div>
         </div>
     </form>
        
		<div class="row">
         <div class="col s12">
			<form name="init_form" id="init_form" method="post">
				<input type="hidden" value="" name="ruser_id" />
				<input type="hidden" value="" name="rmail_yn" />
				<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
				<input type="hidden" value="" name="request_url" />
			</form>
			<c:choose>
	        	<c:when test= "${empty member_list}">
	          		<c:set var="totCnt" value="0"/>
	        	</c:when> 
	        	<c:otherwise>
	          		<c:set var="totCnt" value="${member_list[0].row_cnt}"/>		         
	        	</c:otherwise> 
	      	</c:choose>
			<form action="<%=request.getContextPath()%>/member/member_approval.do" name="fm_edit" method="post">
				<input type="hidden" name="user_id" value=""/>
				<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
				<input type="hidden" value="" name="request_url" />
				
				<div class="row">
		      <div class="col s12">	                 
		        <table>
		           <thead>
		           	<tr>
		              	<th class="center">번호</th>
          				<th class="center">아이디</th>
          				<th class="center">성명</th>
          				<th class="center">가입일자</th>
          				<th class="center">회원등급</th>
          				<th class="center">비밀번호 초기화</th>
          				<th class="center">메일잠금 여부</th>
          				<th class="center">계정잠금 해제</th>
		            </tr>
		          </thead>
		          <tbody>
		          	<c:forEach var="list" items="${member_list}">
			            <tr>
							<td class="center">${list.seq}</td>
							<!-- 2018.01.19 보안성검토 마스킹처리 -->
							<td class="center"><a href="javascript:goEdit('${list.user_id}');">${fn:substring(list.user_id,0,fn:length(list.user_id)-3)}***</a></td>
							<td class="center">${fn:substring(list.user_nm,0,fn:length(list.user_nm)-1)}*</td>	
							<td class="center">${list.inst_date}</td>
							<td class="center">
								<c:choose>
			        			    <c:when test= "${empty list.user_auth}">
			        			      &nbsp;
			        			    </c:when>
			        			    <c:otherwise>
			        			       ${list.user_auth}
			        			    </c:otherwise>
			        			</c:choose>
							</td>
							<td class="center">
								<a href="javascript:" class="btn" onclick="javascript:init_pw('${list.user_id}');">초기화</a>
							</td>
							
							<td class="center">	  							
									<a href="javascript:" class="btn" onclick="maillock_id('${list.user_id}','${list.mail_yn}');"> ${list.mail_yn}</a>								
							</td>
							<td class="center">
								<c:if test="${list.unlock_yn == 'Y'}">
									<a href="javascript:" class="btn" onclick="unloack_id('${list.user_id}');">잠금해제</a>
								</c:if>
							</td>
	        			</tr>
	        		</c:forEach>
		          </tbody>
		        	
		        </table>
		      </div>
			</div>
				
			</form>     	 		       	 		   	 	             
		<!-- Paging Start -->
		<div id="paging" >
			<page:pagelist pageCount="10" listCount="${memberVo.limit}" currPage="${memberVo.page}" 
		   totCount="${totCnt}"	   
		   funcName="goPage"/>
		</div>
		<!-- //Paging End -->
		
		</div>
	</div>
	<!-- contents End -->
</div>
</div>
<!-- Container End -->

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

//검색시
$(document).ready(function(){
	$("#search_btn").click(function() { 
	   	document.fm_search.search_yn.value = "Y"; //신규 검색여부
		document.fm_search.submit();
	});

	$("#search_value").keydown(function() { 
	   	if(event.keyCode == 13) {
	   	   	document.fm_search.search_yn.value = "Y"; //신규 검색여부
	   		document.fm_search.submit();
		}
	});
});

//사용자 수정팝업 가기
function goEdit(id){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		
	document.fm_edit.user_id.value = id;
	document.fm_edit.submit();	
}

//페이지 이동시 
function goPage(pageNum){	
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		
	document.fm_search.page.value = pageNum; //검색 페이지값
	document.fm_search.search_yn.value = "N"; //신규 검색여부
	document.fm_search.submit();	
}

//비밀번호 초기화
function init_pw(user_id){
	
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		
	
	 var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			var rt = confirm("비밀번호를 초기화 하시겠습니까?");
  			if(!rt) return;

  			

  		document.init_form.ruser_id.value = user_id;
  			
  			$.ajax({
  			    url : "<%=request.getContextPath()%>/member/init_pw.do",
  			    data : $("#init_form").serialize(),
  				type : "POST",
  				dataType :"json",
  		 	    success:function(data){
  		 	    	if(data.result == "pw_error") {
  		 	 	    	alert("로직변경이 발생했습니다!");
  		 	 	    	document.init_form.action = "<%=request.getContextPath()%>/error.do";
  		 	    	    document.init_form.submit();
  		 	 	    }else if (data.result != "1") {
  		 	    		alert("비밀번호 초기화에 실패하였습니다.");
  			 	    }
  		 	    	else {
  		 	    		alert("비밀번호가 초기화 되었습니다.\n초기화된 비밀번호는 new1234!입니다. 로그인 후 변경하시기 바랍니다.");
  		 	    		document.init_form.action="<%=request.getContextPath()%>/member/member_list.do" ;
  		 	    	    document.init_form.submit();
  			 	    }
  			     },
  			     error:function(request,status,error){
  			    	 alert(request + " : " + status + " : " + error);
  			     }
  			  });
  		}else{
  			actionUrl = "<%=request.getContextPath()%>/member/session_check.do";
  	 		
  	 		
  			 $.ajax({
  				    url : actionUrl,		    
  					type : "POST",
  					data : "user_id="+id
  			    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
  					+ "&request_url=" + location.href,
  					dataType :"json",
  			 	    success:function(data){
  			 	 	    if(data.result == "session_null") { 
  			 	 	  
  			 	 	   			 	 	    
  			 	 	 alert("session이 종료되었습니다");
  		  	 	      actionUrl = "<%=request.getContextPath()%>/member/logout2.do"; 
  		  	 	    var frm = document.sessionForm;
  		  	 	  	frm.action=actionUrl;
  		  	 	  	frm.submit();  		  	 	  
  		  	 	
  			 	 	    }else {    
  			 	 	    var rt = confirm("비밀번호를 초기화 하시겠습니까?");
  			  			if(!rt) return;

  			  			

  			  		document.init_form.ruser_id.value = user_id;
  			  			
  			  			$.ajax({
  			  			    url : "<%=request.getContextPath()%>/member/init_pw.do",
  			  			    data : $("#init_form").serialize(),
  			  				type : "POST",
  			  				dataType :"json",
  			  		 	    success:function(data){
  			  		 	    	if(data.result == "pw_error") {
  			  		 	 	    	alert("로직변경이 발생했습니다!");
  			  		 	 	    	document.init_form.action = "<%=request.getContextPath()%>/error.do";
  			  		 	    	    document.init_form.submit();
  			  		 	 	    }else if (data.result != "1") {
  			  		 	    		alert("비밀번호 초기화에 실패하였습니다.");
  			  			 	    }
  			  		 	    	else {
  			  		 	    		alert("비밀번호가 초기화 되었습니다.\n초기화된 비밀번호는 new1234!입니다. 로그인 후 변경하시기 바랍니다.");
  			  		 	    		document.init_form.action="<%=request.getContextPath()%>/member/member_list.do" ;
  			  		 	    	    document.init_form.submit();
  			  			 	    }
  			  			     },
  			  			     error:function(request,status,error){
  			  			    	 alert(request + " : " + status + " : " + error);
  			  			     }
  			  			  });
  			}
  	},
  				     error:function(request,status,error){
  				    	 alert(request + " : " + status + " : " + error);
  				    	var sessioncheck = false;
							return sessioncheck ;
  				     }
  				  });
  			
  			}
		
	
}

//해당 사용자 삭제
function unloack_id(user_id){	
	
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			var rt = confirm('계정 잠금해제를 하시겠습니까?');
  			if(!rt) return;

  			
  			
  			document.init_form.ruser_id.value = user_id;
  			
  			$.ajax({
  			    url : "<%=request.getContextPath()%>/member/unlock_id.do",
  			    data : $("#init_form").serialize(),
  				type : "POST",
  				dataType :"json",
  		 	    success:function(data){
  		 	    	if (data.result != "1") {
  		 	    		alert("계정 잠금해제에 실패하였습니다.");
  			 	    }
  		 	    	else {
  		 	    		alert("계정이 잠금해제 되었습니다.");
  		 	    		document.init_form.action="<%=request.getContextPath()%>/member/member_list.do" ;
  		 	    	    document.init_form.submit();
  			 	    }
  			     },
  			     error:function(request,status,error){
  			    	 alert(request + " : " + status + " : " + error);
  			     }
  			  });
  		}else{
  			actionUrl = "<%=request.getContextPath()%>/member/session_check.do";
  	 		
  	 		
  			 $.ajax({
  				    url : actionUrl,		    
  					type : "POST",
  					data : "user_id="+id
  			    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
  					+ "&request_url=" + location.href,
  					dataType :"json",
  			 	    success:function(data){
  			 	 	    if(data.result == "session_null") { 
  			 	 	  
  			 	 	   			 	 	    
  			 	 	 alert("session이 종료되었습니다");
  		  	 	      actionUrl = "<%=request.getContextPath()%>/member/logout2.do"; 
  		  	 	    var frm = document.sessionForm;
  		  	 	  	frm.action=actionUrl;
  		  	 	  	frm.submit();  		  	 	  
  		  	 	
  			 	 	    }else {    
  			 	 	    var rt = confirm('계정 잠금해제를 하시겠습니까?');
  			  			if(!rt) return;

  			  			
  			  			
  			  			document.init_form.ruser_id.value = user_id;
  			  			
  			  			$.ajax({
  			  			    url : "<%=request.getContextPath()%>/member/unlock_id.do",
  			  			    data : $("#init_form").serialize(),
  			  				type : "POST",
  			  				dataType :"json",
  			  		 	    success:function(data){
  			  		 	    	if (data.result != "1") {
  			  		 	    		alert("계정 잠금해제에 실패하였습니다.");
  			  			 	    }
  			  		 	    	else {
  			  		 	    		alert("계정이 잠금해제 되었습니다.");
  			  		 	    		document.init_form.action="<%=request.getContextPath()%>/member/member_list.do" ;
  			  		 	    	    document.init_form.submit();
  			  			 	    }
  			  			     },
  			  			     error:function(request,status,error){
  			  			    	 alert(request + " : " + status + " : " + error);
  			  			     }
  			  			  });
  			}
  	},
  				     error:function(request,status,error){
  				    	 alert(request + " : " + status + " : " + error);
  				    	var sessioncheck = false;
							return sessioncheck ;
  				     }
  				  });
  			
  			}
		
	
};
//2019.11.15 모든 사용자 메일ON/OFF
function maillock_all(mail_yn){	
	
		if(mail_yn=="N"){	
			var rt = confirm('모든 사용자의 메일발송을 ON하시겠습니까?');				
			if(!rt) return;
		}else{
			var rt = confirm('모든 사용자의 메일발송을 OFF하시겠습니까?');				
			if(!rt) return;
			}		
				
			document.init_form.rmail_yn.value = mail_yn;
			
			$.ajax({
			    url : "<%=request.getContextPath()%>/member/maillock_all.do",
			    data : $("#init_form").serialize(),
				type : "POST",
				dataType :"json",
		 	    success:function(data){
		 	    	
		 	    		alert("전체 계정 메일잠금 상태가 변경되었습니다");
		 	    		document.init_form.action="<%=request.getContextPath()%>/member/member_list.do" ;
		 	    	    document.init_form.submit();
			 	  
			     },
			     error:function(request,status,error){
			    	 alert(request + " : " + status + " : " + error);
			     }
			  });

};

//2019.11.15 해당 사용자 메일발송 잠금
function maillock_id(user_id,mail_yn){
	var id = '${sessionScope.user_id}';
		if(id == null || id =="") {			
				var rt = confirm('계정 메일 잠금 상태변경을 하시겠습니까?');				
				if(!rt) return;				
			document.init_form.ruser_id.value = user_id;
			document.init_form.rmail_yn.value = mail_yn;
			
			$.ajax({
			    url : "<%=request.getContextPath()%>/member/maillock_id.do",
			    data : $("#init_form").serialize(),
				type : "POST",
				dataType :"json",
		 	    success:function(data){
		 	    	if (data.result != "1") {
		 	    		alert("계정 메일발송상태 변경이 실패하였습니다");
			 	    }
		 	    	else {
		 	    		alert("계정 메일발송상태 변경이 성공하였습니다");
		 	    		document.init_form.action="<%=request.getContextPath()%>/member/member_list.do" ;
		 	    	    document.init_form.submit();
			 	    }
			     },
			     error:function(request,status,error){
			    	 alert(request + " : " + status + " : " + error);
			     }
			  });
		}else{
			actionUrl = "<%=request.getContextPath()%>/member/session_check.do";
			 $.ajax({
				    url : actionUrl,		    
					type : "POST",
					data : "user_id="+id
			    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
					+ "&request_url=" + location.href,
					dataType :"json",
			 	    success:function(data){
			 	 	    if(data.result == "session_null") { 
			 	 	 alert("session이 종료되었습니다");
		  	 	      actionUrl = "<%=request.getContextPath()%>/member/logout2.do"; 
		  	 	    var frm = document.sessionForm;
		  	 	  	frm.action=actionUrl;
		  	 	  	frm.submit(); 
			 	 	    }else {    
		 	 	    	var rt = confirm('계정 메일 잠금 상태변경을 하시겠습니까?');				
						if(!rt) return;			 					
						document.init_form.rmail_yn.value = mail_yn;						
			  			document.init_form.ruser_id.value = user_id;
			  			$.ajax({
			  			    url : "<%=request.getContextPath()%>/member/maillock_id.do",
			  			    data : $("#init_form").serialize(),
			  				type : "POST",
			  				dataType :"json",
			  		 	    success:function(data){
			  		 	    	if (data.result != "1") {
			  		 	    		alert("계정 메일발송상태 변경이 실패하였습니다");
			  			 	    }
			  		 	    	else {
			  		 	    		alert("계정 메일발송상태 변경이 성공하였습니다");
			  		 	    		document.init_form.action="<%=request.getContextPath()%>/member/member_list.do" ;
			  		 	    	    document.init_form.submit();
			  			 	    }
			  			     },
			  			     error:function(request,status,error){
			  			    	 alert(request + " : " + status + " : " + error);
			  			     }
			  			  });
			}
	},
				     error:function(request,status,error){
				    	 alert(request + " : " + status + " : " + error);
				    	var sessioncheck = false;
							return sessioncheck ;
				     }
				  });
			
			}
		
	
};
</script>
</html>

