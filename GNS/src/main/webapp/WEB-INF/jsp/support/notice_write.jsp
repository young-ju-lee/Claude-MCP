<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : notice_write.jsp
* Overview    : 공지사항 내용작성 화면 
* Description : 공지사항 내용작성 화면
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
<!--header ì¸í¬ë¡ì°ë ìì¹ìëë¤.-->
<jsp:include page="../common/header.jsp" />
<!-- //Header end -->

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
	    
	    <form name="noticeform" method="post">
			<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" id="request_token" name="request_token"/>
	    	<input type="hidden" value="" name="request_url" />
		</form>
	    
	    <div class="bbs-write">
	    	<form action="" id="fm_insert" name="fm_insert" method="post" enctype="multipart/form-data">
				<input type="hidden" name="tableName" value=""/>
				<input type="hidden" name="gotoPage" value=""/>
				<input type="hidden" name="flag" value="${flag }"/>
				<input type="hidden" name="skey" value=""/>
				<input type="hidden" name="keyword" value=""/>
				<input type="hidden" name="recordCnt" value="1"/>
				<input type="hidden" name="mode" value=""/>
				<input type="hidden" name="saveImage" value=""/>
				<input type="hidden" name="tag" value="0">
				<input type="hidden" name="notice_id" value="${noticeVo.notice_id }"/>    
				<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" id="request_token" name="request_token"/>
				<input type="hidden" value="" name="request_url" />
				<input type="hidden" name="notice_title" value=""/>
				<input type="hidden" name="notice_cont" value=""/>
		     </form>
		      <dl>
		        <dt>제목</dt>
		        <dd><input type="text" title="제목 입력" id="notice_title" value="${noticeVo.notice_title }" style="line-height: 20px;"  maxlength="100"></dd>
		      </dl>
		      <dl>
		        <dt>내용</dt>
		        <dd>
		          <textarea rows="16" id="notice_cont">${noticeVo.notice_cont }</textarea>
		          <textarea rows="16" id="content" style="display: none;">${noticeVo.notice_cont }</textarea>
		        </dd>
		      </dl>
	    </div>
	    <!--    검색 및 버튼 시작 -->
	    <div class="row">
	      <div class="col s12 offset-m3 m6 center">
	        <a href="javascript:" onclick="javascript:goList();" class="btn ">목록</a>
	        <c:choose>
	        	<c:when test="${flag == 'W' }">
	        	<a href="javascript:" onclick="javascript:goSave();" class="btn kt-red">등록</a>
	        	</c:when>
	        	<c:otherwise>
	        		<a href="javascript:" onclick="javascript:goSave();" class="btn kt-red">수정</a>
	        	</c:otherwise>
	        </c:choose>
	        
	        <a href="javascript:" onclick="reset();" class="btn">초기화</a>
	      </div>
	    </div>
	    
	</div>

</div>

<!-- Footer Bottom start -->
<jsp:include page="../common/footer.jsp" />
<!-- //Footer Bottom end -->

<script>
function reset(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			

	var title = "${noticeVo.notice_title}";
	var content = $("#content").text();

	$("#notice_title").val(title);
	$("#notice_cont").val(content);
}


function goList(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		
	document.noticeform.action = "<%=request.getContextPath()%>/support/notice_list.do";
	document.noticeform.submit();
}

function checkStr(strOriginal, strFind, strChange){
    var position, strOri_Length;
    position = strOriginal.indexOf(strFind);
    while (position != -1){
        strOriginal = strOriginal.replace(strFind, strChange);
        position = strOriginal.indexOf(strFind);
    }
    strOri_Length = strOriginal.length;
    return strOri_Length;
}

/******************************************************************************
*  Function Name : checkInputData
*  Description   : 데이터의 입력 유무 및 입력 크기 제한을 검사
*  Parameters    : form_id  - 검사할 입력 필드의 form에서의 id
*                  min_size - 검사할 입력 필드의 최소 입력 byte 수
*                  max_size - 검사할 입력 필드의 최대 입력 byte 수
*                  title    - 검사할 입력 필드의 명칭
*                  need     - 필수 입력 여부 (true : 필수, false : 필수 아님)
*  Example       :
*  if (!checkInputData(document.myForm.user_id, 4, 8, "아이디", true)) return;
*  Comment       :
******************************************************************************/

function checkInputData(form_title, min_size, max_size, title, need) {
    if (need && (checkStr(form_title, " ", "")==0 || checkStr(form_title, "&nbsp;", "")==0)) {
        alert("입력항목 [" + title + "]을(를) 입력하십시요.");
        return false;
    } else if (!need && (form_title == "")) {
        return true;
    }

    var	unicode	= escape(form_title);
    var	count	= 0;
    var	i	= 0;

    for (i = 0; i < form_title.length; i++) {
        if (form_title.charAt(i) == ' ') count++;
    }

    if (count == form_title.length) {
        alert("입력항목 [" + title + "]의 형식이 맞지 않습니다.");
        return false;
    }

    count	= 0;

    for (i = 0; i < unicode.length; i++) {
        if (unicode.charAt(i) == '%') {
            i++;

            if (unicode.charAt(i) == 'u') {
                count	+= 2;
                i		+= 4;
            } else {
                count++;
                i++;
            }
        } else {
            count++;
        }
    }

    if (count < min_size) {
        alert("입력항목 [" + title + "]의 글자수는 " + min_size + "Bytes 이상입니다.\n\n"
                  + "현재 글자수 : " + count + "Bytes");
        return false;
    }

    if (count > max_size) {
        alert("입력항목 [" + title + "]의 글자수는 " + max_size + "Bytes로 제한됩니다.\n\n"
                  + "현재 글자수 : " + count + "Bytes");
        return false;
    }

    return true;
}

 function goSave() {
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	 var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			var flag = "${flag}";
  		    var title = $("#notice_title").val();
  		    if (!checkInputData(title, 1, 100, "제목", true)) {
  		        return;
  		    }

  		    var content = $("#notice_cont").val();

  		    if(checkStr(content, " ", "")==0 || checkStr(content, "&nbsp;", "")==0) {
  		      alert("입력항목 [내용]을(를) 입력하십시요.");
  		      return;
  		    }

  		    /*
  		    if (!checkInputData(content, 1, 40000, "내용", true)) {
  		        return;
  		    }
  		    */
  			if(flag == "W") {
  				if(!confirm("공지사항을 등록하시겠습니까?")){
  					return false;
  				}
  			} else {
  				if(!confirm("공지사항을 수정하시겠습니까?")){
  					return false;
  				}
  			}
  		    

  			$("#fm_insert [name=notice_title").val($("#notice_title").val());
  			$("#fm_insert [name=notice_cont").val($("#notice_cont").val());
  			
  		   document.fm_insert.action="<%=request.getContextPath()%>/support/notice_write_register.do";
  		   document.fm_insert.submit();
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
  			 	 	    var flag = "${flag}";
  			  		    var title = $("#notice_title").val();
  			  		    if (!checkInputData(title, 1, 100, "제목", true)) {
  			  		        return;
  			  		    }

  			  		    var content = $("#notice_cont").val();

  			  		    if(checkStr(content, " ", "")==0 || checkStr(content, "&nbsp;", "")==0) {
  			  		      alert("입력항목 [내용]을(를) 입력하십시요.");
  			  		      return;
  			  		    }

  			  		    /*
  			  		    if (!checkInputData(content, 1, 40000, "내용", true)) {
  			  		        return;
  			  		    }
  			  		    */
  			  			if(flag == "W") {
  			  				if(!confirm("공지사항을 등록하시겠습니까?")){
  			  					return false;
  			  				}
  			  			} else {
  			  				if(!confirm("공지사항을 수정하시겠습니까?")){
  			  					return false;
  			  				}
  			  			}
  			  		    

  			  			$("#fm_insert [name=notice_title").val($("#notice_title").val());
  			  			$("#fm_insert [name=notice_cont").val($("#notice_cont").val());
  			  			
  			  		   document.fm_insert.action="<%=request.getContextPath()%>/support/notice_write_register.do";
  			  		   document.fm_insert.submit();
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

</script>
</html>