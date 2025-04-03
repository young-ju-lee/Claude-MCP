<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>제도변경사항</h2>
	        <p>kt그룹 4단계 국가정보통신서비스의 1,2,3단계 대비 달라진 <span class="kt-red-text">제도변경사항</span>을 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>제도소개</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepoint.do');">제도변경사항</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">제도변경사항</a>
	      </div>
	      <ul>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepoint.do');">서비스</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepointspeed.do');">속도체계</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepointsla.do');">SLA</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepointdis.do');">할인율</a>
	        </li>   
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>국가정보통신 서비스 분류</h3>
	        <ul class="list-style1">
	          <li>기존 4단계 그룹 체계에서 최근 국가기관의 서비스 이용수요에 따라 CCTV서비스의 신규 그룹 편성 
	          을 하여 5단계의 효율적인 서비스 제공체계 개선</li>
	          <li>(전용회선서비스 그룹(A그룹), 인터넷서비스(B그룹), 인터넷전화서비스(C그룹), 무선서비스(D그룹), CCTV서비스(E그룹))
	           </li>
	        </ul>
	        
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_changepoint1_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>서비스 체계</h3>
	        <ul class="list-style1">
	          <li>CCTV전송회선 서비스를 E그룹으로 신규 편성, IP-VPN서비스 종료, IoT서비스 신규 추가로 서비스 그룹을 개선</li>
	        </ul>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_changepoint2_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	    </div>
	    </div>

	<jsp:include page="../common/footer.jsp" />
</html>