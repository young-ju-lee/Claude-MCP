<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : notice_list.jsp
* Overview    : 공지사항 리스트 화면 
* Description : 공지사항 리스트 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />

	<div class="page-contents">
	
		<form action="<%=request.getContextPath()%>/support/notice_view.do" name="fm_view" method="post">
		 	<input type="hidden" name="notice_id" />
		 	<input type="hidden" name="page" />
		 	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" id="request_token" name="request_token"/>
		 	<input type="hidden" value="" name="request_url" />
		 </form>	
		 <form name="noticeform" method="post" action="" >
			<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
			<input type="hidden" value="" name="request_url" />
		 </form>
		  <div class="container">
		  	<div class="page-title">
		      <div>
		        <h2>공지사항</h2>
		        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">공지사항</span>입니다.</p>
		      </div>
		      <ul>
	        	<li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
		        <li>고객지원</li>
		        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/support/notice_list.do');">공지사항</a></li>
		      </ul>
		    </div>
		  
		  <div class="row">
	    	<div class="col s12">
	    		<div id="list" class="bbs-list highlight striped">
		        <!--        리스트 헤더 시작-->
			        <ul class="header">
			          <li class="num">번호</li>
			          <li class="title">제목</li>
			          <li class="date">작성일</li>
			          <li class="hit">조회수</li>
			        </ul>
		        <!--        리스트 헤더 끝-->
					<c:forEach var="list" items="${notice_list}">
						<ul>
				          <li class="num">${list.seq}</li>
				          <li class="title">
				            <a href="javascript:goView('${list.notice_id}','${noticeVo.page}');">${list.notice_title}
				            	<c:if test="${list.new_yn == 'y'}"> 
									<img src="<%=request.getContextPath()%>/images/icon_new.gif" alt="신규" width="9" height="9" />
								</c:if> 
				            </a>
				          </li>
				          <li class="date">${list.notice_date}</li>
				          <li class="hit">${list.notice_cnt}</li>
				        </ul>  	    		  							    
			        </c:forEach>
			        <c:choose>
				      <c:when test= "${empty notice_list}">
				         <c:set var="totCnt" value="0"/>
				      </c:when> 
				      <c:otherwise>
				         <c:set var="totCnt" value="${notice_list[0].row_cnt}"/>		         
				      </c:otherwise> 
				   </c:choose>	
				</div>
			     <div class="row">
			      <div class="col s12 offset-m3 m6 center">
			        <fieldset class="search-box">
			          <legend>공지사항 LIST 검색 영역</legend>
			          <form action="<%=request.getContextPath()%>/support/notice_list.do" name="fm_search" method="post">
					    <input type="hidden" name="page" value="${noticeVo.page}"/>
					    <input type="hidden" name="p_search_title" value="${noticeVo.p_search_title}"/>
					    <input type="hidden" name="search_yn" value="N"/>
					    <input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
						<input type="hidden" value="" name="request_url" />
				          <div class="select-wrap">
				            <select name="notice_search" id="notice_search" title="검색 옵션 선택">
				              <option value="T">제목</option>
				            </select>
				          </div>
				          <input type="text" class="text" title="검색어 입력" name="search_title" id="search_title" onkeydown="onEnterkey(event, 'search');" value="${noticeVo.p_search_title}">
				          <a href="javascript:" onclick="goSearch();" class="btn kt-red">찾기</a>
			          </form>
			        </fieldset>
			      </div>
			      <%
			    	//권한체크			
				   if ( request.getSession().getAttribute("user_id") != null  && request.getSession().getAttribute("user_auth_id").equals("40") ) { 
				   %>
				   	<div class="col s12 m3 right-align">
				    	<a href="javascript:" class="btn show-on-small" onclick="goWrite()">작성</a>
				    </div>
			       <%
			        }
			       %>
			    </div>
			    
					<!-- Paging Start -->
					<div id="paging" >
						<page:pagelist pageCount="5" listCount="${noticeVo.limit}" currPage="${noticeVo.page}" 
					   totCount="${totCnt}"	   
					   funcName="goPage"/>
					</div> 
	    	</div>
	    </div>
	</div>

 </div>
<!--footer 인크로드 위치입니다. -->
	<jsp:include page="../common/footer.jsp" />
<!-- //Footer Bottom end -->
<script>
function goView(str, page){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.fm_view.notice_id.value = str; //검색 페이지값
	document.fm_view.page.value = page; //신규 검색여부
	document.fm_view.submit();		
}

//검색시 
function goSearch(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	//alert("goSearch");
	document.fm_search.search_yn.value = "Y"; //신규 검색여부
	document.fm_search.submit(); 
}

//페이지 이동시
function goPage(pageNum){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	//alert(pageNum);
	document.fm_search.page.value = pageNum; //검색 페이지값
	document.fm_search.search_yn.value = "N"; //신규 검색여부
	document.fm_search.submit();	
}

//목록 버튼클릭시
function goList(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.noticeform.action = "<%=request.getContextPath()%>/support/notice_list.do";
	document.noticeform.submit();
}

//작성버튼 클릭시
function goWrite() {
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.noticeform.action = "<%=request.getContextPath()%>/support/notice_write.do";
	document.noticeform.submit();
}
</script>
</html>