<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : personal_pop.jsp
* Overview    : 이전 개인정보 처리방침 다운로드 화면 
* Description : 이전 개인정보 처리방침 다운로드 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/12/16       조학래            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="../common/meta.jsp" %>
  	<%@ include file="../common/css.jsp" %>
</head>
<body style="padding-top: 0;">
	<form name="downloadForm" method="post" action="<%=request.getContextPath()%>/member/personal_download.do">
		<input type="hidden" name="file_name">
		<input type="hidden" name="file_id">
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
	</form>
	<form name="deleteForm" method="post" action="<%=request.getContextPath()%>/member/personal_delete.do">
		<input type="hidden" name="file_id"	value="" />
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
    </form>
	<form name="personalForm" method="post" action="<%=request.getContextPath()%>/member/personalPop.do">
  		<input type="hidden" name="page" value="1"/>
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
	</form>

		<div class="container">
			<div class="page-title" style="height:auto;">
				<div style=" height:auto">
		        	<h2>이전 개인정보처리방침</h2>
		        	<p>국가정보통신서비스의 <span class="kt-red-text">이전 개인정보처리방침</span>다운로드 하실 수 있습니다.</p>
		      	</div>
			</div>
			<c:if test="${sessionScope.user_auth_id == '40'}">
				
					<div class="row">
						<div class="col s12">
							<form action=""	name="uploadForm" method="post" enctype="multipart/form-data">
								<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
								<input type="hidden" value="" name="request_url" />
								<input type="hidden" name="file_title" value=""/>
								<input type="hidden" name="file_gbn" value="PS"/>
								<input type="file" name="upload_file" id="upload_file" title="파일" style="width: 300px; height: 30px;" />
					    		<a href="javascript:" class="btn show-on-small" onclick="javascript:personalUpload();">업로드</a> <br/>
					    		<span style="font-weight: bold;">* 업로드 파일양식은 <span class="kt-red-text">개인정보처리방침_YYYY-MM-DD.pdf</span> 입니다.</span><br />
					    		<span style="font-weight: bold;" class="kt-red-text">* 보안문서의 경우 보안해제 후 등록하시기 바랍니다.</span>
			    			</form>
			    		</div>
					</div>
				
			</c:if>
		   	<div class="row">
				<div class="col s12">
		        	<table>
		        		<colgroup>
			                <col />
			                <col />
			                <c:if test="${sessionScope.user_auth_id == '40'}">
			                	<col />
			                </c:if>
	              		</colgroup>
	        	  		<thead>
	        	  			<tr>
	        	  				<th class="center">시행일자</th>
	        	  				<th class="center">다운로드</th>
	        	  				<c:if test="${sessionScope.user_auth_id == '40'}">
	        	  					<th class="center">파일삭제</th>
	        	  				</c:if>
	        	  			</tr>
	        	  		</thead>
		          		<tbody>
		          			<c:forEach var="list" items="${personal_list}">
		          				<tr>
									<td class="center">${list.file_title}</td>
									<td class="center">
										<a href="javascript:" class="btn show-on-small" onclick="javascript:personalDownLoad('${list.file_id}','${list.file_ext}','${list.file_name}');">다운로드</a>
									</td>
									<c:if test="${sessionScope.user_auth_id == '40'}">
										<td class="center">
											<a href="javascript:" class="btn show-on-small kt-red" onclick="javascript:personalDelete('${list.file_id}');">삭제</a>
										</td>
									</c:if>
								</tr>
		          			</c:forEach>					
		          		</tbody>
	        		</table>
	        		
				    <c:choose>
			        	<c:when test= "${empty personal_list}">
			          		<c:set var="totCnt" value="0"/>
			        	</c:when> 
			        	<c:otherwise>
			          		<c:set var="totCnt" value="${personal_list[0].rowCnt}"/>		         
			        	</c:otherwise> 
			      	</c:choose>
	        		<div id="paging" >
						<page:pagelist pageCount="3" listCount="${dataroomVo.limit}" currPage="${dataroomVo.page}" 
					   totCount="${totCnt}"	   
					   funcName="goPage"/>
					</div>
	      		</div>
	    	</div>
	    	
		</div>

</body>
<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	var url = location.href;
    $("input[name='request_url']").val(url);
});

//페이지 이동시
function goPage(pageNum){
	var id = '${sessionScope.user_id}';
	if(id == null || id =="") {
		document.personalForm.page.value = pageNum; //검색 페이지값
	    document.personalForm.submit();
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
		  	 	   var frm = opener.document.sessionForm2;
 		  	 	  	frm.action=actionUrl;
 		  	 	  	frm.submit(); 
 		  	 	 //2019.04.26 세션 종료되었을 경우 팝업 창 자동 닫기 기능 추가
			 	 	 self.opener = self;
			 	 	 window.close(); 				  	 	  
		  	 	
			 	 	    }else{
			 	 	    document.personalForm.page.value = pageNum; //검색 페이지값
			 	 	  	document.personalForm.submit();
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

//파일 업로드 시
function personalUpload() {
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			var file = $("#upload_file").val();
  			var filename;
  			if(file == "") {
  				alert("PDF형식의 문서를 첨부하세요.");
  				return false;
  			} else {
  				filename = file.substring(file.lastIndexOf("\\")+1, file.length).toLowerCase();

  				var file_regx = /^개인정보처리방침_[0-9]{4,4}-[0-9]{2,2}-[0-9]{2,2}.pdf$/;

  				if(!file_regx.test(filename)) {
  					alert("첨부파일의 양식을 다시 확인하세요.\n보안문서의 경우 보안해제 후 등록하시기 바랍니다.");
  					return;
  				}
  			}
  			if(!confirm("등록하시겠습니까?")){
  				return false;
  			}
  			document.uploadForm.file_title.value = filename.replace(".pdf", "");
  			document.uploadForm.action = "<%=request.getContextPath()%>/member/personal_insert_register.do";
  			document.uploadForm.submit();
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
  		  	 	 var frm = opener.document.sessionForm2;
		  	 	  	frm.action=actionUrl;
		  	 	  	frm.submit(); 
		  	 	 //2019.04.26 세션 종료되었을 경우 팝업 창 자동 닫기 기능 추가
			 	 	 self.opener = self;
			 	 	 window.close(); 		  	 	  
  		  	 	
  			 	 	    }else {    
  			 	 	    var file = $("#upload_file").val();
  			  			var filename;
  			  			if(file == "") {
  			  				alert("PDF형식의 문서를 첨부하세요.");
  			  				return false;
  			  			} else {
  			  				filename = file.substring(file.lastIndexOf("\\")+1, file.length).toLowerCase();

  			  				var file_regx = /^개인정보처리방침_[0-9]{4,4}-[0-9]{2,2}-[0-9]{2,2}.pdf$/;

  			  				if(!file_regx.test(filename)) {
  			  					alert("첨부파일의 양식을 다시 확인하세요.\n보안문서의 경우 보안해제 후 등록하시기 바랍니다.");
  			  					return;
  			  				}
  			  			}
  			  			if(!confirm("등록하시겠습니까?")){
  			  				return false;
  			  			}
  			  			document.uploadForm.file_title.value = filename.replace(".pdf", "");
  			  			document.uploadForm.action = "<%=request.getContextPath()%>/member/personal_insert_register.do";
  			  			document.uploadForm.submit();
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

function personalDownLoad(file_id, file_ext, file_name){	
	var id = '${sessionScope.user_id}';
		if(id == null || id =="") {
			document.downloadForm.file_name.value=file_name;
			document.downloadForm.file_id.value=file_id+ "." + file_ext;
			document.downloadForm.submit();
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
 		  	 	  var frm = opener.document.sessionForm2;
		  	 	  	frm.action=actionUrl;
		  	 	  	frm.submit(); 
		  	 	 //2019.04.26 세션 종료되었을 경우 팝업 창 자동 닫기 기능 추가
			 	 	 self.opener = self;
			 	 	 window.close(); 				  	 	  
 		  	 	
 			 	 	    }else{
 			 	 	 	document.downloadForm.file_name.value=file_name;
 						document.downloadForm.file_id.value=file_id+ "." + file_ext;
 						document.downloadForm.submit();
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

function personalDelete(file_id) {
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			if(!confirm("삭제하시겠습니까?")){
  				return false;
  			}
  			document.deleteForm.file_id.value = file_id;
  			document.deleteForm.submit();
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
  		  	 	    var frm = opener.document.sessionForm2;
  		  	 	  	frm.action=actionUrl;
  		  	 	  	frm.submit(); 
  		  	 	 //2019.04.26 세션 종료되었을 경우 팝업 창 자동 닫기 기능 추가
			 	 	 self.opener = self;
			 	 	 window.close(); 		  	 	  
  		  	 	
  			 	 	    }else {    
  			 	 	    if(!confirm("삭제하시겠습니까?")){
  			  				return false;
  			  			}
  			  			document.deleteForm.file_id.value = file_id;
  			  			document.deleteForm.submit();
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

