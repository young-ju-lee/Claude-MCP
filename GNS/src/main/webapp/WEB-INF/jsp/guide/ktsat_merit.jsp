<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>운영/장애 안내</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">KTsat특장점</span>을 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>이용안내</li>
	        <li>운영/장애 안내</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/guide/ktsat_merit.do');">KTsat특장점</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">KTsat특장점</a>
	      </div>
	      <ul>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/ktsat_merit.do');">KTsat특장점</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/ktsat_wi.do');">위성서비스</a>
	        </li>   
	      
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_ktsat_merit1_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	     <div class="col s12">
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_ktsat_merit2_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>