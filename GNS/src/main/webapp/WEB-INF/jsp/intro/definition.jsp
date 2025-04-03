<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<jsp:include page="../common/header.jsp" />

<div class="page-contents">
	<div class="container">

		<div class="page-title">
			<div>
				<h2>정의</h2>
				<p>
					kt그룹 국가정보통신서비스의 <span class="kt-red-text">정의</span>입니다.
				</p>
			</div>
			<ul>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img
						src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif"
						alt="홈페이지 메인"></a></li>
				<li>제도소개</li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/intro/definition.do');">정의</a></li>
			</ul>
		</div>

		<div class="row">
			<div class="col s12">
				<h3>정의</h3>
				<ul class="list-style1">
					<li>GNS는 국가기관, 지자체가 민간 통신망과 분리된 국가기간 전용의 통신망을 이용하도록 행자부가 통신사와
						이용요금, 품질, 보안 등 협약을 맺어 운용하는 이용 제도이며, GNS4.0은 수요증가가 예상되는 IoT 서비스 요금제 신규마련 및 SLA(service level agreement)
					보상기준에 대한 명확한 정의를 통해 이용기관 및 통신사 편의성 제고, 행정기관으로 한정했던 서비스 이용대상을 
						공공기관까지 확대하였으며, 주요 통신국사 경로 이원화 및 보안성·안전성 강화 관련 통신 인프라에 대한 점검을 강화 하였습니다.</li>
				</ul>
			</div>
		</div>

		<div class="row">
			<div class="col s12">
				<div class="center-align">
					<img src="<%=request.getContextPath()%>/images/img_definition_new.png"
						alt="tset" class="responsive-img" />
				</div>
			</div>
		</div>


	</div>
</div>

<jsp:include page="../common/footer.jsp" />
</html>
