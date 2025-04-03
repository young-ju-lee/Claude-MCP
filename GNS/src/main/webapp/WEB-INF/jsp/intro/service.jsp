<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>서비스체계</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">서비스체계</span>를 소개해드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>제도소개</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/intro/service.do');">서비스체계</a></li>
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>기존 4단계 그룹체계에서 최근 국가기관의 서비스 이용수요에 따라 CCTV서비스의 신규그룹 편성 등 효율적인 서비스 제공체계 개선</li>
	        </ul>
	        
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_service_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />

</html>