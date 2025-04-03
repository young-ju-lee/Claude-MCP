<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>이용절차</h2>
	        <p>kt그룹 국가정보통신서비스 <span class="kt-red-text">이용절차</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>이용안내</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/guide/procedure.do');">이용절차</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">이용절차</a>
	      </div>
	      <ul>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/procedure.do');">이용절차</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/procedure1.do');">청약절차</a>
	        </li>   
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>국가정보통신서비스 이용절차</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_counsel_new2.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>