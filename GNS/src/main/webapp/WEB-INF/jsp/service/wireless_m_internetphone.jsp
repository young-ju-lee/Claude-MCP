<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>모바일행정전화 서비스</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">모바일행정전화 서비스</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',3);">C. 인터넷전화 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">모바일행정전화 서비스</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">모바일인터넷전화 서비스</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip.do');">인터넷전화 서비스</a>
	              
	        </li> 
	        <li class="active">
	        <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">모바일행정전화 서비스</a>
	        </li>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip_infra.do');">인터넷전화 인프라</a>
	        </li>  
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone_infra.do');">모바일행정전화 인프라</a>
	        </li> 
	      <%--   <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data.do');">무선데이터 서비스</a>
	        </li>  
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a>
	        </li>  --%>
	        
	       <%--  <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_cctv.do');">무선CCTV 서비스</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_securitywifi.do');">보안WiFi 서비스</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a>
	        </li> --%>
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
	      <div class="col s12 l7">
	        <h3>모바일인터넷전화 서비스란?</h3>
	      	<ul class="list-style1">
    		  <li>장비 구성도</li>
	        </ul>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_wireless_m_phone.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	      
	      <div class="col s12 l5">
	      	<br><br><br>
	      	<ul class="list-style1">
	      	<!-- 2018.07.20 라인 2줄 내림 -->
	      	</br></br>
    		  <li>모바일 인터넷 전화 서버
    		  	<ul>
    		  		<li>호처리서버와 미디어서버로 구성되며 기관의 사용자 수에 따라 달리 구성</li>
    		  		<li>(호처리 서버) Active-Standby 이중화 구성, 호처리, 가입자 관리</li>
    		  		<li>(미디어 서버) Active-Active 구성, 음성 Relay & 트랜스코딩</li>
    		  	</ul>
    		  </li>
    		  <li>MGW(미디어 게이트웨이)
    		  	<ul>
    		  		<li>모바일인터넷 서버와 교환기간 연동</li>
    		  		<li>SIP ↔ PRI(E1) VoIP Gateway</li>
    		  	</ul>
    		  </li>
    		  <li>인터넷회선 보안 장비
    		  	<ul>
    		  		<li>(방화벽 : UTM) 데이터 방화벽, 국정원  CC인증 (EAL 4)</li>
    		  		<li>VoIP방화벽(SBC) 인터넷전화 보안장비, Active-Standby 이중화 구성, 국정원 CC인증 (EAL 3)</li>
    		  	</ul>
    		  </li>
    		  <li>호환 교환기 : SIP또는 PRI 회선을 지원하는 교환기는 모두 연동 가능
    		  	<ul>
    		  		<li>※ UTM : Universal Threat Management</li>
    		  		<li>※ VoIP방화벽(SBC) : VoIP 방화벽 및 Session Border Controller</li>
    		  	</ul>
    		  </li>
	        </ul>
	      </div>
	    </div>
	    
	  </div>
	  
	  <div class="container price_info " class="display:none">
	  	 <jsp:include page="../service/wireless_m_fee.jsp" />	
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