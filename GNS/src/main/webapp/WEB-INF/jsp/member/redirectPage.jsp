<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta http-equiv="Cache-Control" content="no-store">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="1L">
<%-- 
<%
response.setHeader("Cache-Control", "no_store");
response.setHeader("Cache-control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
 --%>
 
<script type="text/javascript">
	function sendredirect() {
		var url = location.href;
		    //<!--2019.04.26 구조적보안진단 sendredirect 수정 -->         
		 var allowedUrl =   "login.do,logout.do,popup_list.do,popup_write.do,clause.do,join.do,member_delete.do/error.do,popup_write_register.do,traffic1.do,traffic2.do,traffic3.do, ";
         allowedUrl +=  "   main.do,/idpw_change.do,/idpw_change2.do,personalPop.do,dataroom_view.do,dataroom_write.do,idpw_change_done.do,member_edit2.do, ";
         allowedUrl +=  "   notice_list.do,notice_view.do,notice_write.do,notice_edit.do,notice_delete.do,dataroom_list.do,login_check.do,file_download.do,mail_send.do,  ";
         allowedUrl +=  "   dataroom_write_register.do,popup_delete.do,personal_insert_register.do,personal_delete.do,dataroom_delete.do,personal_download.do,member_list.do,mail_approval.do,idpw_pass.do ";
         var checkurl = url.substring(url.lastIndexOf("/")+1, url.length).toLowerCase();                
         
          if(allowedUrl.indexOf(checkurl) == -1){
        	        alert("정상적인 경로가 아닙니다");
	 	 	        actionUrl = "<%=request.getContextPath()%>/error.do";    	 	 	    	   
	    	    	document.redirectform.action = actionUrl;
	    	    	document.redirectform.submit();

             }else{
              
            	document.redirectform.request_url.value = url;
         		document.redirectform.action = "<%=request.getContextPath()%>"+document.redirectform.redirect_url.value;
         		document.redirectform.submit();
             }  
     
	}
</script>

</head>
<body onload="sendredirect();">
	<form name="redirectform" method="post" action="">
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
		<input type="hidden" value="${redirect_url}" name="redirect_url" />
		<c:if test="${flag != null && flag != ''}">
			<input type="hidden" name="flag" value="${flag}"/>
		</c:if>
	</form>
</body>
</html>