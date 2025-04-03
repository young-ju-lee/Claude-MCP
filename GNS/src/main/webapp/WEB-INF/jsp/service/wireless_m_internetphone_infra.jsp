<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>모바일행정전화 인프라</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">모바일행정전화 인프라</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',3);">C. 인터넷 전화 서비스</a></li>	       
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone_infra.do');">모바일행정전화 인프라</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">모바일행정전화 인프라</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip.do');">인터넷전화 서비스</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">모바일행정전화 서비스</a>
	        </li>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip_infra.do');">인터넷전화 인프라</a>
	        </li> 
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone_infra.do');">모바일행정전화 인프라</a>
	        </li>  
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	      <h3>모바일행정전화 인프라</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_infra5_new.png" alt="tset" class="responsive-img2" />
	        </div>
	      </div>
	      
	    </div>
	    
	  </div>
	  
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>