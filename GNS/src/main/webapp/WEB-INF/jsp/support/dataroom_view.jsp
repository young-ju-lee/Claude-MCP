<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : dataroom_view.jsp
* Overview    : 자료실 내용출력 화면 
* Description : 자료실 내용출력 화면 
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
<!--header 인크로우드 위치입니다.-->
<jsp:include page="../common/header.jsp" />
<!-- //Header end -->

<form name="download_form" method="post" action="<%=request.getContextPath()%>/support/file_download.do">
	<input type="hidden" name="file_name">
	<input type="hidden" name="file_id">
	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
	<input type="hidden" value="" name="request_url" />
</form>

<form name="dataroomform" method="post" action="">
	<input type="hidden" name="file_id" />
	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
	<input type="hidden" value="" name="request_url" />
</form>

<!-- Container Start -->
<div class="page-contents">
	<div class="container">
	  	<div class="page-title">
	      <div>
	        <h2>자료실</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">자료실</span>은 항상 열려있습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>고객지원</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/support/dataroom_list.do');">자료실</a></li>
	      </ul>
	    </div>
	    
	    <div class="bbs-write read">
	      <dl>
	        <dt>제목</dt>
	        <dd><b>	<c:choose>
				   <c:when test= "${dataRoomVo.file_gbn == 'GU'}">
				   [이용지침서]
				   </c:when>
				   <c:when test= "${dataRoomVo.file_gbn == 'AG'}">
				   [홈페이지 이용약관]
				   </c:when>
				   <c:when test= "${dataRoomVo.file_gbn == 'AP'}">
				   [신청서]
				   </c:when>
			   </c:choose></b>		
			   ${dataRoomVo.file_title}
			   <c:if test="${dataRoomVo.new_yn == 'y'}"> 
					<img src="<%=request.getContextPath()%>/images/icon_new.gif" alt="" width="9" height="9" alt="신규"/>
			   </c:if>
		  </dd>
	      </dl>
	      <dl>
	      <dt>첨부파일</dt>
	        <dd>
	          <%-- <c:choose>
		  	      <c:when test= "${empty dataRoomVo.file_ext}">
		  	        &nbsp;
		  	        
		  	      </c:when> 
			      <c:when test= "${dataRoomVo.file_ext == 'xls'}">
			         <img src="<%=request.getContextPath()%>/images/icon/xls.gif" width="16" height="16" alt="엑셀"/>
			      </c:when> 
			      <c:when test= "${dataRoomVo.file_ext == 'doc'}">
			         <img src="<%=request.getContextPath()%>/images/icon/doc.gif" width="16" height="16" alt="워드"/>
			      </c:when>
			      <c:when test= "${dataRoomVo.file_ext == 'hwp'}">
			         <img src="<%=request.getContextPath()%>/images/icon/hwp.gif" width="16" height="16" alt="아래아한글"/>
			      </c:when>
			      <c:when test= "${dataRoomVo.file_ext == 'ppt'}">
			         <img src="<%=request.getContextPath()%>/images/icon/ppt.gif" width="16" height="16" alt="파워포인트"/>
			      </c:when>		
			      <c:when test= "${dataRoomVo.file_ext == 'jpg'}">
			         <img src="<%=request.getContextPath()%>/images/icon/jpg.gif" width="16" height="16" alt="jpg"/>
			      </c:when>
			      <c:when test= "${dataRoomVo.file_ext == 'tif'}">
			         <img src="<%=request.getContextPath()%>/images/icon/tif.gif" width="16" height="16" alt="tif"/>
			      </c:when>	
			      <c:otherwise>
			         <!-- 그외 첨부파일들은 무조건 zip 이미지로 표시 -->
			         <i class="material-icons">attach_file</i>
				  </c:otherwise> 
			   </c:choose> --%>
			   <i class="material-icons">attach_file</i>
			   <a href="javascript:downLoad('${dataRoomVo.file_id}','${dataRoomVo.file_ext}','${dataRoomVo.file_name}')">${dataRoomVo.file_name}</a>  
	        </dd>
	      </dl>
	      <dl>
	        <dt>내용</dt>
	        <dd>
	          <p>${dataRoomVo.file_cont}</p>
	        </dd>
	      </dl>
	      <c:if test="${not empty dataRoomVo.prev_file_id}">
		      <dl>
		        <dt><i class="material-icons">chevron_left</i> Next</dt>
		        <dd>
		          <p><a href="javascript:goView('${dataRoomVo.prev_file_id}')">
	                <b>
	                <c:choose>
					   <c:when test= "${dataRoomVo.prev_file_gbn == 'GU'}">
					   [이용지침서]
					   </c:when>
					   <c:when test= "${dataRoomVo.prev_file_gbn == 'AG'}">
					   [홈페이지 이용약관]
					   </c:when>
					   <c:when test= "${dataRoomVo.prev_file_gbn == 'AP'}">
					   [신청서]
					   </c:when>
				   </c:choose>
				   </b>	
	                ${dataRoomVo.prev_file_title}
	                </a></p>
		        </dd>
		      </dl>
	      </c:if>
	      
          <c:if test="${not empty dataRoomVo.next_file_id}">
		      <dl>
		        <dt>Prev<i class="material-icons">chevron_right</i></dt>
		        <dd>
		          <p><a href="javascript:goView('${dataRoomVo.next_file_id}')">
	                <b>
	                <c:choose>
					   <c:when test= "${dataRoomVo.next_file_gbn == 'GU'}">
					   [이용지침서]
					   </c:when>
					   <c:when test= "${dataRoomVo.next_file_gbn == 'AG'}">
					   [홈페이지 이용약관]
					   </c:when>
					   <c:when test= "${dataRoomVo.next_file_gbn == 'AP'}">
					   [신청서]
					   </c:when>
				   </c:choose>
				   </b>
	                ${dataRoomVo.next_file_title}
	                </a></p>
		        </dd>
		      </dl>
	      </c:if>
	      
	      <form action="<%=request.getContextPath()%>/support/dataroom_edit.do" name="fm_edit" method="post">
            <input type="hidden" name="file_id"	value="${dataRoomVo.file_id}" />
            <input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
			<input type="hidden" value="" name="request_url" />
           </form>
           
           <form action="<%=request.getContextPath()%>/support/dataroom_delete.do" name="fm_delete" method="post">
           	<input type="hidden" name="file_id"	value="${dataRoomVo.file_id}" />
           	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
			<input type="hidden" value="" name="request_url" />
           </form> 
           
	    </div>
	    
	    <div class="row">
	      <div class="col s12 offset-m3 m6 center">
	        <a href="javascript:" class="btn" onclick="goList()">목록</a>
			<%
		    	//권한체크			
			   if ( request.getSession().getAttribute("user_id") != null  && request.getSession().getAttribute("user_auth_id").equals("40") ) { 
			%> 
		        <a href="javascript:" onclick="goEdit();" class="btn ">수정</a>
		        <a href="javascript:" onclick="goDelete(); return false;" class="btn kt-red">삭제</a>
		    <%
	             }
	        %>
	      </div>
	    </div>
	    
	</div>
</div>
<!-- Container End -->

<!-- Footer Bottom start -->
<!--footer 인크로드 위치입니다. -->
<jsp:include page="../common/footer.jsp" />
<!-- //Footer Bottom end -->

<script>
function goList(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.dataroomform.file_id.value = "";
	document.dataroomform.action = "<%=request.getContextPath()%>/support/dataroom_list.do";
	document.dataroomform.submit();
};

function goView(file_id){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.dataroomform.file_id.value = file_id;
	document.dataroomform.action = "<%=request.getContextPath()%>/support/dataroom_view.do";
	document.dataroomform.submit();
};

function downLoad(file_id, file_ext,file_name){	
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			
	 	 		var param =  escape(encodeURIComponent(file_name));	
	 	 		<%-- location.href="<%=request.getContextPath()%>/support/file_download.do?file_name="+ param + "&file_id=" + file_id; --%>
	 	 		
	 	 		document.download_form.file_name.value=file_name;
	 	 		document.download_form.file_id.value=file_id+ "." + file_ext;
	 	 		download_form.submit();
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

  			 				
  			 	 		var param =  escape(encodeURIComponent(file_name));	
  			 	 		<%-- location.href="<%=request.getContextPath()%>/support/file_download.do?file_name="+ param + "&file_id=" + file_id; --%>
  			 	 		
  			 	 		document.download_form.file_name.value=file_name;
  			 	 		document.download_form.file_id.value=file_id+ "." + file_ext;
  			 	 		download_form.submit();
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

//수정버튼 클릭시
function goEdit(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	fm_edit.submit();	
};

//삭제버튼 클릭시
function goDelete(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			if(!confirm("삭제하시겠습니까?")){
  				return false;
  			}
  			
  			fm_delete.submit();	
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
  			 	 	    if(!confirm("삭제하시겠습니까?")){
  			  				return false;
  			  			}
  			  			
  			  			fm_delete.submit();	
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
