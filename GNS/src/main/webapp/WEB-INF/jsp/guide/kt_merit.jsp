<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>운영/장애 안내</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">kt특장점</span>을 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>이용안내</li>
	        <li>운영/장애 안내</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/guide/kt_merit.do');">kt특장점</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">kt특장점</a>
	      </div>
	      <ul>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/kt_merit.do');">kt특장점</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/formation.do');">조직/체계</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/hindrance.do');">장애대응 방안</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/control.do');">망관리/감시</a>
	        </li>
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	      	<ul class="list-style1">
		        <li>
		          	풍부한 관련 사업 경험과 전국 서비스 제공 능력을 통해 최상의 국가정보통신서비스를 구축하며, 안정적으로 운영
		        </li> 
		     </ul>
	      </div>
	    </div>
	    <div class="row">
	      <div class="col s12">
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_kt_merit_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	      <div class="col s12">
	       <h3>A, B 그룹 특장점</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_ab_merit_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	      <div class="col s12">
	       <h3>C, D 그룹 특장점</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_cd_merit_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	       <div class="col s12">
	       <h3>E 그룹 특장점</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_ef_merit_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>