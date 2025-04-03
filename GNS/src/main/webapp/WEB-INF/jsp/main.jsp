<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp"%>

<!-- Header start -->
<!--Heder 인크로우드 위치입니다.-->
<jsp:include page="common/header.jsp" />
<!-- //Header end -->
<%-- 
	<%
		response.sendRedirect(request.getContextPath()+"/work.jsp");
	%>
--%>

<div class="main-banner">
  <div id="main-slides" class="default-slider">
    <ul class="slides-container">
     
      <li>
       <img src="<%=request.getContextPath()%>/images/main_banner_bg3.png" alt="전용회선과 위성통신을 통해 국가정보통신서비스 선두사업자로 도약하겠습니다." class="large">
       <img src="<%=request.getContextPath()%>/images/main_banner_bg3_small_new.png" alt="전용회선과 위성통신을 통해 국가정보통신서비스 선두사업자로 도약하겠습니다." class="small">
      
      </li>
      
       <li>
        <img src="<%=request.getContextPath()%>/images/main_banner_bg1_new.jpg" alt="1등 서비스를 고객님들께서 인정해 주셨습니다." class="large">
        <img src="<%=request.getContextPath()%>/images/main_banner_bg1_small_new.jpg" alt="1등 서비스를 고객님들께서 인정해 주셨습니다." class="small">
       <!--  <div class="container">
          <p>
            <strong class="kt-red-text">KT</strong>의 <strong class="kt-red-text">1등 서비스</strong>를<br />
            고객님들께서<br />
            인정해 주셨습니다.
          </p>
        </div> -->
      </li>
    </ul>

		<nav class="slides-navigation">
			<div class="container">
				<a href="javascript:" class="next"><i class="material-icons">navigate_next</i></a>
				<a href="javascript:" class="prev"><i class="material-icons">navigate_before</i></a>
			</div>
		</nav>
		<nav class="main-pager">
			<div class="container">
				<a href="javascript:"><img
					src="<%=request.getContextPath()%>/images/thumbs_3_new.jpg" /></a> <a
					href="javascript:"><img
					src="<%=request.getContextPath()%>/images/thumbs_2.jpg" /></a>
			</div>
		</nav>
	</div>
</div>

<section class="kt-egov-merits">
	<div class="container">

		<div class="row">
			<div class="col s12 center">
				<h1 class="kt-red-text">
					<small>풍부한 관련 사업 경험과 전국 서비스 제공 능력을 통해</small><br /> 최상의 국가정보통신서비스를
					구축해 드립니다.
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col s12 m6 l3-2">
				<a href="javascript:"
					onclick="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 1);"
					class="merit-link"> <img src="images/service_01.png"
					alt="전용회선서비스">
					<h3>
						전용회선서비스<br /> <small>두 지점간 완벽한 보안성능을</br> 갖춘 24시간 단독회선 서비스</small>
					</h3>
				</a>
			</div>
			<div class="col s12 m6 l3-2">
				<a href="javascript:"
					onclick="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 2);"
					class="merit-link"> <img src="images/service_02.png"
					alt="IP서비스">
					<h3>
						인터넷서비스<br /> <small>다양한 속도로 안정적인 네트워크 접속을 제공하는 인터넷 전용회선 서비스</small>
					</h3>
				</a>
			</div>
			<div class="col s12 m6 l3-2">
				<a href="javascript:"
					onclick="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 3);"
					class="merit-link"> <img src="images/service_03.png"
					alt="인터넷전화서비스">
					<h3>
						인터넷전화서비스<br /> <small>인터넷 전화와 직원간 무료통화가 가능한 LTE기반 mVoIP서비스</small>
					</h3>
				</a>
			</div>
			<div class="col s12 m6 l3-2">
				<a href="javascript:"
					onclick="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 4);"
					class="merit-link"> <img src="images/service_05.png"
					alt="무선서비스">
					<h3>
						무선서비스<br /> <small>LTE기반의 스마트 단말 행정서비스와 정보통신기술 기반의 IoT서비스</small>
					</h3>
				</a>
			</div>
			<div class="col s12 m6 l3-2">
				<a href="javascript:"
					onclick="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 5);"
					class="merit-link"> <img src="images/service_06.png"
					alt="CCTV서비스">
					<h3>
						CCTV서비스<br /> <small>CCTV단말로부터 관제센터까지 영상 전달을 제공하는 시내 전용회선서비스</small>
					</h3>
				</a>
			</div>
		</div>
	</div>
</section>

<section class="kt-egov-bbs">
	<div class="container">
		<div class="row">
			<form action="<%=request.getContextPath()%>/support/notice_view.do"
				name="noticeForm" method="post">
				<input type="hidden" name="notice_id" /> <input type="hidden"
					value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
					name="request_token" /> <input type="hidden" value=""
					name="request_url" />
				<div class="col s12 m6">
					<header>
						<div class="left">
							<i class="material-icons md-18 kt-red-text">&#xE616;</i>
							<h3>공지사항</h3>
						</div>
						<div class="right">
							<a
								href="javascript:menuMove('<%=request.getContextPath()%>/support/notice_list.do');"><i
								class="material-icons md-18 more">&#xE145;</i></a>
						</div>
					</header>
					<ul>
						<c:forEach var="list" items="${notice_list}">
							<li><a href="javascript:goView('${list.notice_id}','N');"
								<c:if test="${list.new_yn == 'y'}">class="new"</c:if>>
									${list.notice_title} <span>${list.notice_date }</span>
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</form>
			<form action="<%=request.getContextPath()%>/support/dataroom_view.do"
				name="dataroomForm" method="post">
				<input type="hidden" name="file_id" /> <input type="hidden"
					value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
					name="request_token" /> <input type="hidden" value=""
					name="request_url" />
				<div class="col s12 m6">
					<header>
						<div class="left">
							<i class="material-icons md-18 kt-red-text">&#xE2C4;</i>
							<h3>자료실</h3>
						</div>
						<div class="right">
							<a
								href="javascript:menuMove('<%=request.getContextPath()%>/support/dataroom_list.do');"><i
								class="material-icons md-18 more">&#xE145;</i></a>
						</div>
					</header>
					<ul>
						<c:forEach var="list" items="${dataroom_list}">
							<li><a href="javascript:goView('${list.file_id}','D');"
								<c:if test="${list.new_yn == 'y'}">class="new"</c:if>>
									${list.file_title} <span>${list.file_date }</span>
							</a></li>

						</c:forEach>
					</ul>
				</div>
			</form>
		</div>
	</div>
</section>

<!-- Footer Bottom start -->
<!--footer 인크로드 위치입니다. -->
<jsp:include page="common/footer.jsp" />
<!-- //Footer Bottom end -->

<script>
	$(document).ready(function() {
		$('#main-slides').superslides({
	        hashchange: false,
	        pagination: true,
	        play: 10000,
	        animation_speed: 800,
	        inherit_height_from: '.main-banner'
	      }).on('animated.slides', function() { 
	        var current_index = $(this).superslides('current');
	        $('.main-pager a').removeClass('current');
	        $('.main-pager a').eq(current_index).addClass('current');
	      });
	    $('.main-pager a').click(function(){
	        $('#main-slides').superslides('animate', $('.main-pager a').index(this));
	      });
	});

	function goView(str, flag) {
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가
		sessioncheck();
  		
  		if (flag === "N") {
			document.noticeForm.notice_id.value = str;
			document.noticeForm.submit();
		} else if (flag === "D") {
			document.dataroomForm.file_id.value = str;
			document.dataroomForm.submit();
		}
	}
</script>

<jsp:include page="common/popup_script.jsp" />
</html>

