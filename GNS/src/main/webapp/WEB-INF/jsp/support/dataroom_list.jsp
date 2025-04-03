
<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : dataroom_list.jsp
* Overview    : 자료실 리스트 화면 
* Description : 자료실 리스트 화면 
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
<jsp:include page="../common/header.jsp" />

<form name="dataroomform" method="post" action="">
	<input type="hidden" name="file_id" /> <input type="hidden"
		value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
		name="request_token" /> <input type="hidden" value=""
		name="request_url" />
</form>


<div class="page-contents">
	<div class="container">

		<div class="page-title">
			<div>
				<h2>자료실</h2>
				<p>
					kt그룹 국가정보통신서비스의 <span class="kt-red-text">자료실</span>입니다.
				</p>
			</div>
			<ul>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img
						src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif"
						alt="홈페이지 메인"></a></li>
				<li>고객지원</li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/support/dataroom_list.do');">자료실</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="col s12">
				<!--  게시판 리스트 시작 -->
				<div class="bbs-list highlight striped">
					<!--        리스트 헤더 시작-->
					<ul class="header">
						<li class="num">번호</li>
						<li class="title">제목</li>
						<li class="num">파일</li>
						<li class="date">작성일</li>
						<li class="hit">조회수</li>
					</ul>
					<!--        리스트 헤더 끝-->

					<!--        리스트 한단위 시작-->
					<c:forEach var="list" items="${dataroom_list}">
						<ul>
							<li class="num">${list.seq}</li>
							<li class="title"><a
								href="javascript:goView('${list.file_id}')"> <b> <c:choose>
											<c:when test="${list.file_gbn == 'GU'}">
							   [이용지침서]
							   </c:when>
											<c:when test="${list.file_gbn == 'AG'}">
							   [이용약관]
							   </c:when>
											<c:when test="${list.file_gbn == 'AP'}">
							   [신청서]
							   </c:when>
							   <c:when test="${list.file_gbn == 'OT'}">
							   [기타]
							   </c:when>
										</c:choose>
								</b> ${list.file_title}
							</a> <c:if test="${list.new_yn == 'y'}">
									<img src="<%=request.getContextPath()%>/images/icon_new.gif"
										alt="" width="9" height="9" alt="신규" />
								</c:if></li>
							<li class="num">
								<%-- 
				          	<c:choose>
					  	      <c:when test= "${empty list.file_ext}">
					  	        &nbsp;
					  	      </c:when> 
						      <c:when test= "${list.file_ext == 'xls'}">
						         <img src="../images_old/icon/xls.gif" width="16" height="16" alt="엑셀"/>
						      </c:when> 
						      <c:when test= "${list.file_ext == 'doc'}">
						         <img src="../images_old/icon/doc.gif" width="16" height="16" alt="워드"/>
						      </c:when>
						      <c:when test= "${list.file_ext == 'hwp'}">
						         <img src="../images_old/icon/hwp.gif" width="16" height="16" alt="아래아한글"/>
						      </c:when>
						      <c:when test= "${list.file_ext == 'ppt'}">
						         <img src="../images_old/icon/ppt.gif" width="16" height="16" alt="파워포인트"/>
						      </c:when>		
						      <c:when test= "${list.file_ext == 'jpg'}">
						         <img src="../images_old/icon/jpg.gif" width="16" height="16" alt="jpg"/>
						      </c:when>
						      <c:when test= "${list.file_ext == 'tif'}">
						         <img src="../images_old/icon/tif.gif" width="16" height="16" alt="tif"/>
						      </c:when>					      						      				      
						      <c:otherwise>
						         <!-- 그외 첨부파일들은 무조건 zip 이미지로 표시 -->
						         <img src="../images_old/icon/zip.gif" width="16" height="16" alt="압축파일"/>					         		         
						      </c:otherwise> 
						   </c:choose>
						    --%> 
						    <i class="material-icons" style="font-size: 18px;">attach_file</i>
							</li>
							<li class="date">${list.file_date}</li>
							<li class="hit">${list.file_cnt}</li>
						</ul>
					</c:forEach>
					<c:choose>
						<c:when test="${empty dataroom_list}">
							<c:set var="totCnt" value="0" />
						</c:when>
						<c:otherwise>
							<c:set var="totCnt" value="${dataroom_list[0].rowCnt}" />
						</c:otherwise>
					</c:choose>
				</div>
				<div class="row">
					<div class="col s12 offset-m3 m6 center">
						<fieldset class="search-box">
							<legend>자료실 LIST 검색 영역</legend>
							<form name="fm_search" method="post"
								action="<%=request.getContextPath()%>/support/dataroom_list.do">
								<input type="hidden" name="page" value="1" /> <input
									type="hidden" name="p_search_title"
									value="${dataroomVo.p_search_title}" /> <input type="hidden"
									name="p_search_gbn" value="${dataroomVo.p_search_gbn}" /> <input
									type="hidden" name="search_yn" value="N" /> <input
									type="hidden"
									value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
									name="request_token" /> <input type="hidden" value=""
									name="request_url" />

								<div class="select-wrap">
									<select name="search_gbn" id="search_gbn" title="구분">
										<option value="ALL"
											${'ALL' eq dataroomVo.p_search_gbn ? 'selected' : ''}>전체</option>
										<option value="GU"
											${'GU' eq dataroomVo.p_search_gbn ? 'selected' : ''}>이용지침서</option>
										<option value="AG"
											${'AG' eq dataroomVo.p_search_gbn ? 'selected' : ''}>홈페이지 이용약관</option>
										<option value="AP"
											${'AP' eq dataroomVo.p_search_gbn ? 'selected' : ''}>신청서</option>
											<option value="OT"
											${'OT' eq dataroomVo.p_search_gbn ? 'selected' : ''}>기타</option>
									</select>
								</div>
								<input name="search_title" id="search_title" type="text"
									class="text" title="검색어 입력"
									value="${dataroomVo.p_search_title}"
									onkeydown="onEnterkey(event, 'search');" /> <a
									href="javascript:" class="btn kt-red"
									onclick="javascript:goSearch();">찾기</a>
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
				<!--     pagination 시작 -->
				<div id="paging">
					<page:pagelist pageCount="5" listCount="${dataroomVo.limit}"
						currPage="${dataroomVo.page}" totCount="${totCnt}"
						funcName="goPage" />
				</div>
				<!--     pagination 끝 -->
			</div>
		</div>
	</div>
</div>

<jsp:include page="../common/footer.jsp" />


<script>
function goWrite(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.dataroomform.file_id.value = "";
	document.dataroomform.action = "<%=request.getContextPath()%>/support/dataroom_write.do";
	document.dataroomform.submit();
}

function goView(file_id){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.dataroomform.file_id.value = file_id;
	document.dataroomform.action = "<%=request.getContextPath()%>/support/dataroom_view.do";
	document.dataroomform.submit();
}

//검색시 
function goSearch(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.fm_search.search_yn.value = "Y"; //신규 검색여부
	document.fm_search.submit();
}

//페이지 이동시
function goPage(pageNum){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.fm_search.page.value = pageNum; //검색 페이지값
	document.fm_search.search_yn.value = "N"; //신규 검색여부
	document.fm_search.submit();	
}

//전체목록 보기
function goList(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.dataroomform.file_id.value = "";
	document.dataroomform.action = "<%=request.getContextPath()%>/support/dataroom_list.do";
	document.dataroomform.submit();
}
</script>
</html>