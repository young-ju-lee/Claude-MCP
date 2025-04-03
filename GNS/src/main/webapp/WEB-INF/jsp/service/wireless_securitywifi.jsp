<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>무선 서비스</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">무선서비스</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',4);">D. 무선 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_securitywifi.do');">보안 Wifi 서비스</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">보안WiFi 서비스</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">모바일인터넷전화 서비스</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data.do');">무선데이터 서비스</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_cctv.do');">무선CCTV 서비스</a>
	        </li>   
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_securitywifi.do');">보안WiFi 서비스</a>
	        </li>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a>
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
	        <h3>보안WiFi 서비스</h3>
	        <ul class="list-style1">
	          <li>보안이 강화된 스마트 폰 등 이동전화로 언제 어디서나 유선 행정 전화와 같이 직원간 무료통화가 가능한 LTE기반 mVoIP 서비스</li>
	        </ul>
	        
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/ready.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	  </div>
	  
	  <div class="container price_info " class="display:none">
		<jsp:include page="../service/wireless_securitywifi_fee.jsp" />
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