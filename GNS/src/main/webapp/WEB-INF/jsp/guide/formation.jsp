<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>운영/장애 안내</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">조직체계</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>이용안내</li>
	        <li>운영/장애 안내</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/guide/formation.do');">조직/체계</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">전용회선 인프라</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/kt_merit.do');">kt특장점</a>
	        </li> 
	        <li class="active">
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
	        <h3>조직</h3>
	        <ul class="list-style1">
		        <li>
			        	전국 11개 지역고객본부, 약 3,500개 국사, 총인원 12,000여명의 유지보수 인력운영 및 네트워크운용단의 전문기술인력 등 풍부한 유지보수 인력과 직영 유지보수 체계를 통해 신속한 유지보수를 시행
		        </li>
		      </ul>
		      <div class="center-align">
		        		<img src="<%=request.getContextPath()%>/images/img_formation_map.gif" alt="tset" class="responsive-img" />
		        	</div>
	      </div>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>지원체계</h3>
	        <ul class="list-style1">
	        	<li>kt는 직영 유지보수를 기본으로 하여 체계화된 유지보수 조직을 구성하고 부서별 역할을 세분화 하여 고장, 이슈 발생시 신속한 유지보수를 수행</li>
	        </ul>
	      </div>
	    </div>
	    <div class="row">
	      <div class="col s12">
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_usury.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>