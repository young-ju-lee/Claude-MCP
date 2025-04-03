<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>상담안내</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">상담안내</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>이용안내</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/guide/counsel.do');">상담안내</a></li>
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	      	<div class="center-align">
	      	<img class="responsive-img left" alt="ktset" src="<%=request.getContextPath()%>/images/img_counsel3.png">
	      	<img class="responsive-img right" alt="kt" src="<%=request.getContextPath()%>/images/img_counsel4.png">
	        	<%-- <img src="<%=request.getContextPath()%>/images/img_counsel3.png" alt="tset" class="responsive-img" />
	        	<img src="<%=request.getContextPath()%>/images/img_counsel4.png" alt="tset" class="responsive-img" /> --%>
	        </div>
	      </div>
	    </div>
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>