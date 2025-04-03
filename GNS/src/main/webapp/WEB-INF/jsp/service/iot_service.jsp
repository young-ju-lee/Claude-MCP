<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>IoT 서비스</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">IoT 서비스</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',4);">D. 무선 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_service.do');">IoT 서비스</a></li>
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
	         <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_service.do');">IoT 서비스</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a>
	        </li>
	      <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_infra.do');">IoT 인프라</a>
	        </li>
	      </ul>
	    </div>
	    
	    <div class="radio-tab">
	      <ul>
	        <li>
	          <input type="radio" name="radio-tab" id="t1" value="service_info" checked="checked" />
	          <label for="t1"><i class="material-icons"> live_help</i> 서비스 정보</label>
	        </li>
	        <li>
	          <input type="radio" name="radio-tab" id="t2" value="price_info" />
	          <label for="t2"><i class="material-icons">receipt</i> 요금 안내</label>
	        </li>
	      </ul>
	    </div>
	  </div>
	  
	 <div class="container service_info">
	    <div class="row">
	      <div class="col s12">
	        <h3>IoT 서비스란?</h3>
	        <ul class="list-style1">	          
	          	<ul>
	          		<li>정보통신기술 기반으로 모든 사물을 연결해 플랫폼·네트워크간 정보를 교류하고 지원하는 지능형 인프라 및 서비스입니다.</li>
	          	</ul>
		       <%--  <div class="center-align">
			    	<img src="<%=request.getContextPath()%>/images/img_wireless_lte.jpg" alt="tset" class="responsive-img" />
		    	</div> --%>
	          </li>
	        </ul>
	        
	         <h3>참고 : IoT 서비스 개요</h3>
	        <ul class="list-style1">
	         
	          	<ul>
	          		<li><b>사물인터넷 서비스 정의</b></br> - 정보통신기술 기반으로 모든 사물을 연결해 사물-네트워크
	          		서비스플랫폼 간 정보를 교류하고 소통하는 지능형 인프라 및 서비스</li>
	          	</ul>
	          		<ul>
	          		<li><b>정부사물인터넷 서비스, G-IoT</b></br> - 정부는 공공(대만서비스 혁신), 산업(생산성·효율성 및
	          		부가가치 향상), 개인(안전, 편리 등 삶의 질 제고) 등 국가 사회 현안 해결의 수단
	          		으로 사물인터넷 기술을 활용하고 있으며, 이를 위해 도입·운영하는 서비스·플랫폼·
	          		네트워크·디바이스 등 사물인터넷 인프라 전체를 정부사물인터넷이라고 정의</li>
	          	</ul>
	          	<ul>
	          	<li><b>정부사물인터넷 구성요소</b></li>
	          	<li>- (행정기관 개별 인프라①) 각 행정기관이 개별 구축한 G-IoT 인프라</li>
	          	<li>- (정부 공통기반②) 가관간 연계 등 공통활용을 위해 행정안전부에서 구축</li>
	          	<li>- (정부사물인터넷 망 ③·백홀④) 디바이스·게이트웨이 간 무선망과 플랫폼 연결망</li>
	          	</ul>
	          	<div class="left-align">
			        	<img src="<%=request.getContextPath()%>/images/img_IoT_service_new.png" alt="tset" class="responsive-img2" />
			        </div>
	          	<ul>
	          	<li><b>정부사물인터넷 공통기반 구축 목적</b></br> - 로밍 등을 통한 지역한계 극복 및 공통기반을 활용한 
	          	빠른 서비스 구현과 타기관·상용서비스 연계·협업을 통한 융·복합 서비스 가능한 기반 제공</li>
	          	<li><b>사물인터넷 서비스 이용제도의 역할</b></br> - 지자체 등 행정기관에서 구축한 다양한 통신
	          	표준(네트워크) LoRaWAN, NB-IoT 등 LPWAN 기술, (플랫폼) oneM2M등, (기기간 상호운영지원) OCF등)을 수용하여 서비스 범위를 전국으로 확대하고, 서비스 상호운영성 증대 및 중복  개발 방지로 예산절감</li>
	          	</ul>
	          	
	      </div>
	  </div>
	 </div>
		  <div class="container price_info " class="display:none">
	  	 <jsp:include page="../service/iot_service_fee.jsp" />
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />

<script>
$(document).ready(function(){
    $(".price_info").hide();
	
    $('.radio-tab label').click(function(){
      var num = $('.radio-tab label').index($(this));
      $('.radio-tab input').each(function(index){
        $('div.'+$(this).val()).hide();
        if(index == num){
          $('div.'+$(this).val()).show();
        }
      });
      
    });
    
    
  });  
</script>
</html>