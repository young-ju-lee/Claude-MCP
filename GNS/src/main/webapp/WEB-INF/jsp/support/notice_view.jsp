<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : notice_view.jsp
* Overview    : 공지사항 내용출력 화면 
* Description : 공지사항 내용출력 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<%
   String contextPath = request.getContextPath();
   String tableName = "";
   String flag = "";
   String skey = "";
   String keyword = "";
   int gotoPage;
   int seq;

   String boardKind = "";

   boolean hasReply = false;
   String strHasReply = (String)request.getAttribute("strHasReply");

   if("true".equals(strHasReply)) {
     hasReply = true;
   }

%>
<!-- Header start -->
<!--header 인크로우드 위치입니다.-->
<jsp:include page="../common/header.jsp" />
<!-- //Header end -->

<!-- Container Start -->
<div class="page-contents">
	<div class="container">
	  	<div class="page-title">
	      <div>
	        <h2>공지사항</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">최신 소식</span>을 빠르게 전해드리겠습니다.</p>
	      </div>
	      <ul>
        	<li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>고객지원</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/support/notice_list.do');">공지사항</a></li>
	      </ul>
	    </div>
	    
	    <div class="bbs-write read">
	      <dl>
	        <dt>제목</dt>
	        <dd>${noticeVo.notice_title}</dd>
	      </dl>
	      <dl>
	        <dt>내용</dt>
	        <dd>
	          <p>${noticeVo.notice_cont}</p>
	        </dd>
	      </dl>
	      
		  <c:if test="${not empty noticeVo.prev_notice_id}">
		      <dl>
		        <dt><i class="material-icons">chevron_left</i> Next</dt>
		        <dd>
		          <p><a href="javascript:goView('${noticeVo.prev_notice_id}')">${noticeVo.prev_notice_title}</a></p>
		        </dd>
		      </dl>
	      </c:if>
	      
          <c:if test="${not empty noticeVo.next_notice_id}">
		      <dl>
		        <dt>Prev<i class="material-icons">chevron_right</i></dt>
		        <dd>
		          <p><a href="javascript:goView('${noticeVo.next_notice_id}')">${noticeVo.next_notice_title}</a></p>
		        </dd>
		      </dl>
	      </c:if>
	    </div>
	    
	    <form action="<%=request.getContextPath()%>/support/notice_view.do" name="fm_view" method="post">
	    	<input type="hidden" name="notice_id" />
	    	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" id="request_token" name="request_token"/>
	    	<input type="hidden" value="" name="request_url" />
 	    </form>	
 	    
	     <form name="noticeform" method="post">
		  	<input type="hidden" name="page" value="${noticeVo.page}" />
		  	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" id="request_token" name="request_token"/>
	    	<input type="hidden" value="" name="request_url" />
		</form>
	    <!--    검색 및 버튼 시작 -->
	    <div class="row">
	      <div class="col s12 offset-m3 m6 center">
	        <a href="javascript:" class="btn" onclick="javascript:goList()">목록</a>
			<%
				//권한체크			
	             if ((request.getSession().getAttribute("user_id") != null) && (request.getSession().getAttribute("user_auth_id").equals("40"))) { 
	    	  %>    
		        <a href="javascript:" onclick="javascript:goEdit();" class="btn ">수정</a>
		        <a href="javascript:" onclick="javascript:goDel(); return false;" class="btn kt-red">삭제</a>
		    <form name="edit_form" method="post">
	         	<input type="hidden" name="notice_id" value="${noticeVo.notice_id}" />
	         	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" id="request_token" name="request_token"/>
	    		<input type="hidden" value="" name="request_url" />
	       </form>
	       <form name="delete_form" method="post">
	           	<input type="hidden" name="notice_id" value="${noticeVo.notice_id}" />
	           	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" id="request_token" name="request_token"/>
	    		<input type="hidden" value="" name="request_url" />
	       </form>
		    <%
	             }
	        %>
	      </div>
	    </div>
	    
	</div>

</div>
<!-- Container End -->

<!-- Footer Bottom start -->

<!--footer 인크로우드 위치입니다.-->
<jsp:include page="../common/footer.jsp" />
<!-- //Footer Bottom end -->

<script>
function goList(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.noticeform.action = "<%=request.getContextPath()%>/support/notice_list.do";
	document.noticeform.submit();
}

function goEdit(str){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			

    var flag = "<%=flag%>";
    var skey = "<%=skey%>";

    if (checkNumberXSS(flag)) {

      	if (checkEngXSS(skey)) {
        } else {
     		window.alert("잘못된 파라미터를 입력하셨습니다.");
      	}

    } else {
     	window.alert("잘못된 파라미터를 입력하셨습니다.");
    }
	document.edit_form.action = "<%=request.getContextPath()%>/support/notice_edit.do";
	document.edit_form.submit();
}

function goDel(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			if(!confirm("공지사항을 삭제하시겠습니까?")){
  				return false;
  			}
  			
  		  	document.delete_form.action = "<%=request.getContextPath()%>/support/notice_delete.do";
  		  	document.delete_form.submit();
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
  			 	 	    if(!confirm("공지사항을 삭제하시겠습니까?")){
  			  				return false;
  			  			}
  			  			
  			  		  	document.delete_form.action = "<%=request.getContextPath()%>/support/notice_delete.do";
  			  		  	document.delete_form.submit();
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



    // 숫자만 허용..
     function checkNumberXSS(strValue) {
     var inText = strValue;
     var ret;

     for (var i = 0; i < inText.length; i++) {
       ret = inText.charCodeAt(i);

       if ( (ret > 64) && (ret < 123)) {
              return false;
       } else if (ret > 122) {
                  return false;
       } else if ((ret > 57) && (ret < 65)) {
                  return false;
       } else if (ret < 48) {

         return false;
       } else {

       }

     }
     return true;

   }
    // 영문자만 허용
    function checkEngXSS(strValue) {
      var inText = strValue;
      var ret;

      if (inText.length == 0) {
        return true;
      } else {
        for (var i = 0; i < inText.length; i++) {
          ret = inText.charCodeAt(i);

          if ( (ret > 64) && (ret < 123)) {
            //    return true;
          } else  {
            //  alert("영문자 이외에는 입력하실 수 없습니다.");
            return false;
          }

        }
        return true;
      }

    }

function goView(str){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.fm_view.notice_id.value = str; //검색 페이지값	
	document.fm_view.submit();		
}
	
</script>
</html>