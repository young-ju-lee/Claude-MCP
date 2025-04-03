<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>운영/장애 안내</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">망관리/감시</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>이용안내</li>
	        <li>운영/장애 안내</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/guide/control.do');">망관리/감시</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">망관리/감시</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/kt_merit.do');">kt특장점</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/formation.do');">조직/체계</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/hindrance.do');">장애대응 방안</a>
	        </li>   
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/control.do');">망관리/감시</a>
	        </li>
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	      	<ul class="list-style1">
	        	<li>장애 인지 시 해당 kt 운용팀에서 1차 조치하며, 장애상황에 따라 인접 kt국사, 네트워크운용단 및 네트워크기술지원본부에서 장애처리 지원</li>
	        </ul>
	      </div>
	    </div>
	    <div class="row">
	      <div class="col s12">
	        <h3>kt 망감시 체계</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_control.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>주요 임무</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_control1.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>