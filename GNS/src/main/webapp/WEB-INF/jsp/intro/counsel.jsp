<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>추진체계</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">추진체계</span>를 소개해드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>제도소개</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/intro/counsel.do');">추진체계</a></li>
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>국가정보통신서비스 추진체계</h3>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_counsel_new.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>국가정보통신서비스 주요 역할</h3>
	        <table>
	           <thead>
	          </thead>
	          <tbody>
	            <tr>
	              <th>구분</th>
	              <th>역할</th>
	              <!-- <th>상담문의</th> -->
	            </tr>
	            <tr>
	              <td style="text-align: center;">(주관기관)<br><span class="kt-red-text">행정안전부</span></td>
	              <td>
	              	<ul class="list-style1">
				  		<li>국가정보통신서비스 사업 총괄기획 조정</li>
				  		<li>국가정보통신서비스 이용제도 및 이용활성화 정책수립</li>
				  		<li>국가정보통신인프라 이용기관 협의회 구성 운영</li>
				  		<li>기타 국가정보통신서비스 정책 관련 사항</li>
				  	</ul>
	              </td>
	              <!-- <td>정보기반보호정책과<br>02-2100-3996</td> -->
	            </tr>
	            <tr>
	              <td style="text-align: center;">국가정보통신서비스<br>이용기관 협의회</td>
	              <td>
	              	<ul class="list-style1">
				  		<li>통신망 이용제도 관련 협의</li>
				  		<li>통신망 발전방안에 대한 협의 등</li>
				  	</ul>
	              </td>
	            </tr>
	            <tr>
	              <td style="text-align: center;">(전문기관)<br>한국정보화진흥원</td>
	              <td>
				  	<ul class="list-style1">
				  		<li>국가정보통신서비스 인프라 구성 및 운영 요건 마련∙관리</li>
				  		<li>국가정보통신서비스 이용요금 검토 및 서비스 품질기준 마련∙관리</li>
				  		<li>국가정보통신서비스 이용기관 기술지원, 기술교육 등 지원</li>
				  		<li>국가정보통신서비스 이용기관협의회 운영 지원</li>
				  		<li>국가정보통신서비스 발전계획 수립 등 고도화 지원</li>
				  		<li>기타 국가정보통신서비스 사업 관련 사항</li>
				  	</ul>
				  </td>
	              <!-- <td>네트워크서비스팀<br>053-230-1772<br>053-230-1775</td> -->
	            </tr>
	            <tr>
	              <td style="text-align: center;">(사업자)<br>KTsat, KT</br> LGU+, CJ헬로비전 </br> SKT, SKB </br>드림라인</br> 서경방송</td>
	              <td>
				  	<ul class="list-style1">
				  		<li>국가정보통신서비스 인프라의 구성 및 운영 </li>
				  		<li>서비스 제공 및 이용활성화 활동</li>
				  		<li>서비스 품질(SLA) 관리 활동</li>
				  		<li>기타 국가정보통신서비스 인프라 구성 및 서비스 제공 관련 사항</li>
				  	</ul>
				  </td>
	              <!-- <td><font class="kt-red-text" style="font-weight: bolder; font-size: 20px;">kt : 1588-0114</font><br>LG유플러스 : 1544-0001<br>SK컨소시엄 : 1600-0108</td> -->
	            </tr>
	          </tbody>
	        	
	        </table>
	      </div>
	    </div>
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>