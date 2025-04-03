<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
		<div class="page-contents">
			<div class="container">
				<div class="page-title">
			      <div>
			        <h2>팝업창관리</h2>
			        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">팝업창관리</span>입니다.</p>
			      </div>
			      <ul>
		        	<li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
			        <li>관리</li>
			        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/admin/popup_list.do');">팝업창관리</a></li>
			      </ul>
			    </div>
			    
			    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">메뉴별 접속 통계</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/member/member_list.do');" >회원 관리</a>
	        </li> 
	        <li class="active">
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
			    
			    <div class="row">
	    			<div class="col s12">
	    				<form action="<%=request.getContextPath()%>/admin/popup_edit.do" name="fm_edit" method="post">
							<input type="hidden" name="pop_id" value=""/>
							<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
							<input type="hidden" value="" name="request_url" />	
		    				<div id="list" class="bbs-list highlight striped">
					        <!--        리스트 헤더 시작-->
						        <ul class="header">
						          <li class="num">번호</li>
						          <li class="title">제목</li>
						          <li class="date">등록일</li>
						        </ul>
					        <!--        리스트 헤더 끝-->
								<c:forEach var="list" items="${popup_list}">	
									<ul>
							          <li class="num">${list.seq}</li>
							          <li class="title">
							            <a href="javascript:goEdit('${list.pop_id}');">&nbsp;&nbsp;&nbsp;${list.pop_title}</a>
							          </li>
							          <li class="date">${list.pop_date}</li>
							        </ul>  				    
					            </c:forEach>	
							</div>
						</form>
						
						<c:choose>
					        <c:when test= "${empty popup_list}">
					          <c:set var="totCnt" value="0"/>
					        </c:when> 
					        <c:otherwise>
					          <c:set var="totCnt" value="${popup_list[0].row_cnt}"/>		         
					        </c:otherwise> 
					    </c:choose>	
					</div>
				</div>
				<div class="row">
				     <div class="col s12 offset-m3 m6 center">
				        <fieldset class="search-box">
				          <legend>팝업창관리 LIST 검색 영역</legend>
				          <form action="<%=request.getContextPath()%>/admin/popup_list.do" name="fm_search" method="post">
						      <input type="hidden" name="page" value="1"/>
						      <input type="hidden" name="p_search_title" value="${popupVo.p_search_title}"/>
						      <input type="hidden" name="search_yn" value="N"/>
						      <input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
							  <input type="hidden" value="" name="request_url" />
					          <div class="select-wrap">
					            <select name="" id="" title="검색 옵션 선택">
					              <option value="">제목</option>
					            </select>
					          </div>
					          <input type="text" class="text" title="검색어 입력" name="search_title" id="search_title" onkeydown="onEnterkey(event, 'search');" value="${popupVo.p_search_title}">
					          <a href="javascript:" onclick="javascript:goSearch();" class="btn kt-red">찾기</a>
				          </form>
				        </fieldset>
				      </div>
	    			
		    			<div class="col s12 m3 right-align">
					    	<a href="javascript:" class="btn show-on-small" onclick="javascript:goWrite()">작성</a>
					    </div>
	    			
						<form id="popupform" name="popupform" method="post"  >
							<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
							<input type="hidden" value="" name="request_url" />
						</form>
			      </div>
			      
	    			<div id="paging" >
						<page:pagelist pageCount="5" listCount="${popupVo.limit}" currPage="${popupVo.page}" 
					   totCount="${totCnt}"	   
					   funcName="goPage"/>
					</div>
			</div>
		</div>
	
	<jsp:include page="../common/footer.jsp" />
</html>

<script type="text/javascript">

//전체목록 버튼클릭시
function goList(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.popupform.action = "<%=request.getContextPath()%>/admin/popup_list.do";
	document.popupform.submit();
}

//팝업창 작성 가기
function goWrite(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.popupform.action = "<%=request.getContextPath()%>/admin/popup_write.do";
	document.popupform.submit();
}

//팝업창 작성 가기
function goEdit(id){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
    //alert(id);
	document.fm_edit.pop_id.value = id;
	document.fm_edit.submit();	
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
</script>
