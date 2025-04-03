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
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">무선 인프라</a>
	      </div>
	      <ul>
	       <%--  <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">모바일인터넷전화 서비스</a>
	        </li>  --%>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data.do');">무선데이터 서비스</a>
	        </li>  
	          <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_service.do');">IoT 서비스</a>
	         </li> 	     
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a>
	        </li>
	          <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_infra.do');">IoT 인프라</a>
	         </li> 
	      </ul>
	    </div>
	    <div class="row">
	      <div class="col s12">
	      	<h3>무선 인프라</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_infra2.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	      <div class="col s12">
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_infra3.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	  </div> 
	  
	</div>

	<jsp:include page="../common/footer.jsp" />
<script>
$(document).ready(function(){
    $(".security_system").hide();
    $(".ipv6_provide").hide();
	
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