<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>IoT 인프라</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">IoT 인프라</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',4);">D. 무선 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_infra.do');">IoT 인프라</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">IoT 서비스</a>
	      </div>
	      <ul>
	         <li >
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data.do');">무선데이터 서비스</a>
	        </li> 
	         <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_service.do');">IoT 서비스</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a>
	        </li>
	      <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_infra.do');">IoT 인프라</a>
	        </li>
	      </ul>
	    </div>
	<div class="row">
	      <div class="col s12">
	      <h3>IoT 인프라</h3>
	      </br >
	      <ui><b><정부사물인터넷 구성모델></b></ui></br ></br ></br >
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_infra6_new.png" alt="tset" class="responsive-img2" />
	        </div>
	         </br >
	        <ui><b><사물인터넷 환경에서의 보안위협></b></ui></br ></br ></br >
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_infra7_new.png" alt="tset" class="responsive-img2" />
	        </div>
	         </br >
	        <ui><b><정부사물인터넷 구성 및 연계 개념도></b></ui></br ></br ></br >
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_infra8_new.png" alt="tset" class="responsive-img2" />
	        </div>
	      </div>
	      
	    </div>
	    
	  </div>
	  
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>