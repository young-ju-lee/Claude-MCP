<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
     <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>특징</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">특징</span>을 소개해드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>제도소개</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/intro/feature.do');">특징</a></li>
	      </ul>
	    </div>
	    
	    <div class="row">
	      
	      <div class="col s12 l6">
            <img src="<%=request.getContextPath()%>/images/kt5g03_img1.jpg" class="responsive-img image-deco-1" alt="kt 서비스 사진" />
          </div>
          
	      <div class="col s12 l6">
	        <h3>이용자 중심 서비스 제공체계</h3>
	        <ul class="list-style1">
	          <li>신규서비스 이용제도에 대한 신속한 이용기관 의견수렴 및 지원체계를 마련하기 위하여 국가정보통신서비스 이용기관협의회 구성운영
	          	<ul>
	          		<li>신규서비스 제공, 사업자 선정 및 퇴출, 이용제도(이용요금, 서비스 품질 등) 관리 등 실질적인 심의 기능부여</li>
	          	</ul>
	          </li>
	          <li>국가기관의 저비용 · 고품질의 국가정보통신서비스 서비스 이용 유도 및 신규 수요의 효율적 적용 등 통신망 고도화 관련 기술지원 실시</li>
	        </ul>
	        <h3>서비스의 통신품질(SLA) 강화</h3>
	        <ul class="list-style1">
	          <li>국가정보통신서비스 품질 및 보상기준 등 SLA(Service Level Agreement: 서비스 수준 계약) 적용
	          	<ul>
	          		<li>국가정보통신서비스 관리환경 구축 · 운영을 통한 엄격한 품질관리</li>
	          	</ul>
	          </li>
	          <li>주기별(년 1회) 인프라 점검, 서비스 품질측정 및 만족도 조사 등을 통한 통신망 품질 제고</li>
	        </ul>
	      </div>
	    </div>
	    
	    <div class="divider"></div>
	    
	    <div class="row">
	      <div class="col s12 l6">
	        <h3>보안성 강화</h3>
	        <ul class="list-style1">
	          <li>국가기관 트래픽과 민간 트래픽, 업무망(회선서비스망)과 인터넷 IP서비스망을 완전 분리
	          	<ul>
	          		<li>주기별(년 1회) 국가정보통신서비스 인프라에 대한 보안지도점검을 통한 보안성 제고</li>
	          	</ul>
	          </li>
	          <li>국가사이버안전관리규정 및 국가사이버안전매뉴얼, 정보통신기반보호법 등에 근거하여 통신사업자별 세부 보안지침을 수립하여 적용</li>
	          <li>상용 인터넷망과의 접점지역(서울, 대전)에 보안 접속환경 구축</li>
	        </ul>
	        <h3>안정성 확보</h3>
	        <ul class="list-style1">
	          <li>국가정보통신서비스의 효율적 · 안정적 운영을 위한 상시 감시 및 복구 체계 구축</li>
	          <li>장애 · 재난 등 통신장애가 발생할 경우, 즉각적 복구 가능한 무장애 통신망 구성
	          	<ul>
	          		<li>통신장비, 전송경로 등에 대한 이중화 · 이원화 구성 등으로 장애발생시 즉각적 절체</li>
	          	</ul>
	          </li>
	        </ul>
	      </div>
	      <div class="col s12 l6 right-align">
            <img src="<%=request.getContextPath()%>/images/kt5g03_img2.jpg" class="responsive-img image-deco-1" alt="kt 서비스 사진" />
          </div>
	    </div>
	    
	    <div class="divider"></div>
	    
	    <div class="row">
	      <div class="col s12 l6">
            <img src="<%=request.getContextPath()%>/images/kt5g03_img3.jpg" class="responsive-img image-deco-1" alt="kt 서비스 사진" />
          </div>
	      <div class="col s12 l6">
	        <h3>관리체계</h3>
	        <ul class="list-style1">
	          <li>공정하고 올바른 이용제도의 운영과 안정적인 서비스 제공을 위하여 주관/전담기관을 통한 관리기능 강화</li>
	          <li>전문기관이 관리환경에서의 관리정보 수집 및 분석을 통하여 국가정보통신서비스 인프라 및 서비스수준 관리
	          	<ul>
	          		<li>이용제도 운영 및 서비스 관리를 위하여 필요한 관리정보를 정의하고 구성, 가입자, 성능, 장애, SLA중심으로 관리환경 운영</li>
	          	</ul>
	          </li>
	        </ul>
	        <h3>기술지원</h3>
	        <ul class="list-style1">
	          <li>전문성이 부족한 이용기관 및 신기술 적용을 위한 교육, 전문컨설팅 등 이용기관 전문기술지원 강화</li>
	        </ul>
	      </div>
	    </div>
	    
	    
	  </div>


    </div>

	<jsp:include page="../common/footer.jsp" />
</html>