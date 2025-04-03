<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>운영/장애 안내</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">장애대응방안</span>을 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>이용안내</li>
	        <li>>운영/장애 안내</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/guide/hindrance.do');">장애대응 방안</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">장애대응 방안</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/kt_merit.do');">kt특장점</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/formation.do');">조직/체계</a>
	        </li>   
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/hindrance.do');">장애대응 방안</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/control.do');">망관리/감시</a>
	        </li>
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>장애대응 절차</h3>
	      </div>
	    </div>
	    <div class="row">
	      <div class="col s12">
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_hindrance.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>장애시 운영 방안</h3>
	        <ul class="list-style1">
	        	<li>kt지사/지점은 다원화된 루프망 보유</li>
	        	<li>광케이블 구성시 주/예비 루트 동시 장애가 발생할 확률은 매우 희박하나, 만약의 사태 발생시에도 kt지사/지점의 다원화된 광케이블 인프라로 즉시 긴급복구를 시행</li>
	        	<li>kt는 매년 을지훈련을 포함한 긴급복구 훈련을 통해 국가 주요 통신망에 대한 완벽한 복구태세 확립</li>
	        </ul>
	      </div>
	    </div>
	    
	    
	    <div class="row">
	      <div class="col s12">
	      	<h3>무장애 서비스 제공 방식</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_hindrance1.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>