<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>전용회선 서비스</h2>
	        <p>kt그룹 국가정보통신서비스 <span class="kt-red-text">전용회선 서비스</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 1);">A. 전용회선 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/leasedline_infra.do');">전용회선 인프라</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">전용회선 인프라</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_basic.do');">기본회선 서비스</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_backbone.do');">백본회선 서비스</a>
	        </li>      
	        <%-- <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_cctv.do');">CCTV 전송회선 서비스</a>
	        </li> --%>
	     <%--    <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_lamda.do');">람다회선 서비스</a>
	        </li> --%>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leasedline_infra.do');">전용회선 인프라</a>
	        </li>
	      </ul>
	    </div>
	    
	    <div class="row">
	      <%-- 
	      <div class="col s12">
	        <h3>전용 회선서비스 구성방안</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_infra.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	       --%>
	      <div class="col s12">
	      <h3>전용 회선서비스 구성방안</h3>
		      <div class="center-align">
		        <div class="center-align">
		        		<img src="<%=request.getContextPath()%>/images/img_formation_map.png" alt="tset" class="responsive-img" />
		        	</div>
	          </div>
	      </div>
	    </div>
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>