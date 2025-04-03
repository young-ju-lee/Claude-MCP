<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="bottom-banner">
  <div id="bottom-slides" class="default-slider">
    <ul class="slides-container">
      <li>
        <img src="<%=request.getContextPath()%>/images/bottom_banner_bg1.jpg" alt="">
        <div class="container">
          <p>
			국내 통신사 사업자 중<br>
            <span>최대 통신인프라를 통한 <strong>전국 커버리지를 제공</strong>합니다.</span>
          </p>
        </div>
      </li>
      <li>
        <img src="<%=request.getContextPath()%>/images/bottom_banner_bg2.jpg" alt="">
        <div class="container">
          <p>
           	 공중망과 분리된 별도의 자체망<br>
            <span>다양한 보안기능 제공으로 <strong>완벽한 보안성 </strong>을 제공 합니다.</span>
          </p>
        </div>
      </li>
      <li>
        <img src="<%=request.getContextPath()%>/images/bottom_banner_bg3.jpg" alt="">
        <div class="container">
          <p>
           	 전문 KT 전담인력으로<br>
            <span><strong>24시간 안정적으로 유지보수</strong>를 제공 합니다.</span>
          </p>
        </div>
      </li>
    </ul>

    <nav class="slides-navigation">
      <div class="container">
        <a href="javascript:" class="next"><i class="material-icons">navigate_next</i></a>
        <a href="javascript:" class="prev"><i class="material-icons">navigate_before</i></a>
      </div>
    </nav>

    <div class="bottom-pager">
      <div class="container">
        <a href="javascript:void(0);" class="current"><span>&nbsp;특장점&nbsp;</span>1</a>
        <a href="javascript:void(0);"><span>&nbsp;특장점&nbsp;</span>2</a>
        <a href="javascript:void(0);"><span>&nbsp;특장점&nbsp;</span>3</a>
      </div>
    </div> 
  </div>
</div>
<div class="help-banner">
	<div class="container">
		<div class="row">
			<div class="col s12 m8">
				<div class="title">
					<img src="<%=request.getContextPath()%>/images/speach_bull.png"
						alt="말풍선">
					<h3>
						도움이 필요하세요? <small>상담 및 전화 서비스 신청 가능합니다.</small>
					</h3>
				</div>
			</div>
			<div class="col s12 m2 center box">
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/support/faq.do')"
					class="box">FAQ</a>
			</div>
			<div class="col s12 m2 center box">
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/counsel.do')">서비스신청</a>
			</div>
			<!-- <div class="col s12 m2 center box">
			2018.06.29 긴급반영 상담번호 수정
				<a href="javascript:">1588-0114 (ARS 2>2)</a>
			</div> -->
		</div>
	</div>
</div>
<div class="sitemap">
	<div class="container">
		<div class="row">
			<div class="col s12 m6 l2 kt-logo">
				<img src="<%=request.getContextPath()%>/images/icon_kt_new.png"
					alt="korea telecom">
			</div>
			<div class="col s12 m6 l2">
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/intro/definition.do')"
					class="main">제도소개</a>
				<div class="divider"></div>
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/intro/definition.do')"
					class="sub">정의</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/intro/feature.do')"
					class="sub">특징</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/intro/service.do')"
					class="sub">서비스체계</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepoint.do')"
					class="sub">제도변경사항</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/intro/counsel.do')"
					class="sub">추진체계</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/intro/propulsion.do')"
					class="sub">추진경과 </a>
			</div>
			<div class="col s12 m6 l2">
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',1)"
					class="main">kt그룹서비스</a>
				<div class="divider"></div>
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 1);"
					class="sub">전용회선서비스</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 2);"
					class="sub">인터넷서비스</a><a
					href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 3);"
					class="sub">인터넷전화서비스</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 4);"
					class="sub">무선서비스</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 5);"
					class="sub">CCTV서비스</a>
			</div>
			<div class="col s12 m6 l2">
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/procedure.do')"
					class="main">이용안내</a>
				<div class="divider"></div>
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/kt_merit.do')"
					class="sub">KT특장점</a>
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/ktsat_merit.do')"
					class="sub">KTsat특장점</a>
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/procedure.do')"
					class="sub">이용절차</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/charges.do')"
					class="sub">요금정보</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/counsel.do')"
					class="sub">상담안내</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/kt_merit.do')"
					class="sub">운영/장애 안내</a>
			</div>

			<div class="col s12 m6 l2">
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/traffic/traffic1.do')"
					class="main">트래픽조회</a>
				<div class="divider"></div>
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/traffic/traffic1.do')"
					class="sub">트래픽조회서비스</a>
			</div>

			<div class="col s12 m6 l2">
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/support/notice_list.do')"
					class="main">고객지원</a>
				<div class="divider"></div>
				<a
					href="javascript:menuMove('<%=request.getContextPath()%>/support/notice_list.do')"
					class="sub">공지사항</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/support/dataroom_list.do')"
					class="sub">자료실</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/support/faq.do')"
					class="sub">FAQ</a> <a
					href="javascript:menuMove('<%=request.getContextPath()%>/member/agreement.do')"
					class="sub">홈페이지 이용약관</a> 
					<!--  2017.12.23 보안성검토 개인정보처리방침 수정 -->
					<a href="javascript:menuMove('<%=request.getContextPath()%>/member/personal.do')"
					class="sub">개인정보처리방침</a>
			</div>
		</div>
	</div>
</div>
<div class="footer">
  <div class="container">
   <div class="row">
    <div class="col s12 m12 l5">
        <div class="contact2">
            <div class="row">
              <div class="col s12 m6 l6">
                <ul class="footer-list">
                  <h4><i class="material-icons"> perm_phone_msg</i>서비스신청 및 상담문의</h4>
                  <li>A · E그룹</li>
                  <li><a href="tel:1899-3773">1899-3773</a><span> (ARS1번)</span></li>
                  <!-- <li><span>(청약/상담 1번, 장애접수 2번)</span></li> -->
                    <li class="sbtxt">FAX : <a href="tel:1899-3773">0502-918-7798</a> <span></span></li>
                   <li class="sbtxt">고장접수 : <a href="tel:1899-3773">1899-3773</a> <span>(ARS2번)</span></li>
                </ul>
              </div>
              <div class="col s12 m6 l6">
                <ul class="footer-list">
                 <h4><i class="material-icons"> perm_phone_msg</i>서비스신청 및 상담문의</h4>
                  <li>B · C · D그룹</li>
                  <li><a href="tel:1588-0114">1588-0114</a> <span>(ARS2 &gt; 2)</span></li>
                  <li class="sbtxt">FAX : <a href="tel:1588-0114">080-789-0001</a> <span>(ARS1번)</span></li>
                  <li class="sbtxt">고장접수 : <a href="tel:1588-0114">1588-0114</a> <span>(ARS3 &gt; 0)</span></li>
                </ul>
              </div>
            </div>
        </div>
      </div>
     <div class="col s12 m12 l7">
      <div class="row legal2">
        <div class="col s12">
          <ul class="footer-list">
            <li>회사소개<a href="https://corp.kt.com" target="_blank">(KT</a>·<a href="https://ktsat.net" target="_blank">KT sat)</a></li>
            <li><a href="javascript:menuMove('/member/agreement.do')">홈페이지 이용약관</a></li>
            <li><a href="javascript:menuMove('/member/personal.do')">개인정보처리방침</a></li>
          </ul>
        </div>
      </div>
      <div class="row family-site2">
        <div class="col s12">
        <ul class="footer-list">
					<li><a href="http://www.mois.go.kr" target="_blank"><img
							src="<%=request.getContextPath()%>/images/family_01_new.png"
							alt="행정안전부" class="responsive-img"></a></li>
					<li><a href="http://www.ncia.go.kr/index.jsp" target="_blank"><img
							src="<%=request.getContextPath()%>/images/family_02_new.png"
							alt="국가정보관리원" class="responsive-img"></a></li>
					<li><a href="http://www.nia.or.kr" target="_blank"><img
							src="<%=request.getContextPath()%>/images/family_03_new.png"
							alt="한국정보진흥원(NIA)" class="responsive-img"></a></li>
					<li><a href="http://www.gns.or.kr" target="_blank"><img
							src="<%=request.getContextPath()%>/images/family_04_new.png"
							alt="GNS서비스홈페이지" class="responsive-img"></a></li>
				</ul>     
        </div>
      </div>
      <div class="row copyright2">
        <div class="col s12">
          Copyright ?2017 KT corp.. 모든 권리 보유.<br>
          <span>463-711 경기도 성남시 분당구 불정로 90(정자동 206번지 (주)케이티 대표이사 김영섭 사업자등록번호 102-81-42945</span>
        </div>
      </div>
     </div>
   </div>
  </div>
</div>

<%-- <div class="footer">
	<div class="container">
		<div class="row contact">
			<div class="col s12">
				<ul class="footer-list">
				<!-- 2018.06.29 긴급반영 상담번호 수정 -->
					<li>서비스 신청 및 상담문의(A,E그룹) : <a href="tel:1899-3773">1899-3773</a> <span>(ARS 1)</span>
					</li>
					<li>고장접수 : <a href="tel:1899-3773">1899-3773</a> <span>(ARS 2)</span>
					</li>
					<li>서비스 신청 및 상담문의(B,C,D그룹) : <a href="tel:1588-0114">1588-0114</a> <span>(ARS
						2 > 2)</span>
					</li>
					<li>FAX : <a href="tel:1588-0114">080-789-0001</a> <span>(ARS
							1번)</span>
					</li>
					<!-- 2018.06.29 긴급반영 상담번호 수정 -->
					<li>고장접수 : <a href="tel:1588-0114">1588-0114</a> <span>(ARS
							3 > 0)</span>
					</li>
				</ul>
			</div>
		</div>
		<div class="row legal">
			<div class="col s12">
				<ul class="footer-list">
					<li>회사소개<a href="https://corp.kt.com" target="_blank">(KT</a>/<a href="https://ktsat.net" target="_blank">KT SAT)</a>					
					</li>
					<li><a
						href="javascript:menuMove('<%=request.getContextPath()%>/member/agreement.do')">홈페이지 이용약관</a>
					</li>
					<li><a
						href="javascript:menuMove('<%=request.getContextPath()%>/member/personal.do')">개인정보처리방침</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="row family-site">
			<div class="col s12">
				<ul class="footer-list">
					<li><a href="http://www.mois.go.kr" target="_blank"><img
							src="<%=request.getContextPath()%>/images/family_01_new.png"
							alt="행정안전부" class="responsive-img"></a></li>
					<li><a href="http://www.ncia.go.kr/index.jsp" target="_blank"><img
							src="<%=request.getContextPath()%>/images/family_02_new.png"
							alt="국가정보관리원" class="responsive-img"></a></li>
					<li><a href="http://www.nia.or.kr" target="_blank"><img
							src="<%=request.getContextPath()%>/images/family_03_new.png"
							alt="한국정보진흥원(NIA)" class="responsive-img"></a></li>
					<li><a href="http://gns.kt.com" target="_blank"><img
							src="<%=request.getContextPath()%>/images/family_04_new.png"
							alt="GNS서비스홈페이지" class="responsive-img"></a></li>
				</ul>
			</div>
		</div>
		<div class="row copyright">
			Copyright ?2017 KT corp.. 모든 권리 보유.<br /> <span>463-711 경기도
				성남시 분당구 불정로 90(정자동 206번지 (주)케이티 대표이사 황창규 사업자등록번호 102-81-42945</span>
		</div>
	</div>
</div> --%>

<jsp:include page="../common/popup.jsp" />

</body>
<form name="intpopform" action="" method="post" target="" >
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
	</form>
<script src="<%=request.getContextPath()%>/js/jsbn.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/rsa.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/prng4.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/rng.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<c:set var="uri" value="${pageContext.request.requestURI}" />
<c:if test="${fn:containsIgnoreCase(uri, 'traffic3')}">
	<jsp:include page="../common/jqplot_script.jsp" />
</c:if>
  <script src="<%=request.getContextPath()%>/js/jquery.easing.1.3.js"></script>
  <script src="<%=request.getContextPath()%>/js/jquery.animate-enhanced.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/jquery.superslides.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){

	var url = location.href;
    $("input[name='request_url']").val(url);
	
    
    
      $('#bottom-slides').superslides({
        hashchange: false,
        pagination: true,
        play: 7000,
        animation_speed: 800,
        inherit_height_from: '.bottom-banner'
      }).on('animated.slides', function() { 
        var current_index = $(this).superslides('current');
        $('.bottom-pager a').removeClass('current');
        $('.bottom-pager a').eq(current_index).addClass('current');
      });

      $('.bottom-pager a').click(function(){
        $('#bottom-slides').superslides('animate', $('.bottom-pager a').index(this));
      });

      
    $('nav.gnb dl>dt:not(:only-child)>a').click(function(){
      $(this).parent().parent().toggleClass('active');
    });
    $('div.current-label > a').click(function(){
      $('.page-tab').toggleClass('active');
    });
    
    $('.page-tab li > a').click(function () {
        $('div.current-label > a').text($(this).text());
        console.log($(this).text());
        $('.page-tab').removeClass('active');
    });

    $('.hamberger-btn').click(function(){
        $('nav.gnb').toggleClass('expand');
      });

    $('.account-btn').click(function(){
        $('div.mini > ul').toggleClass('open');
        });

    headerFix();

  });

  $(window).resize(function(){
      headerFix();
    });
    
    
    function headerFix(){
      var sumWidth = 0;
      var tdElements = $('.header-fixed-wrapper .body-scroll tbody tr:first-child th, .header-fixed-wrapper .body-scroll tbody tr:first-child td');
      tdElements.each(function(index){
        
        $(this).parent().parent().parent().parent().parent().find('.header-fixed thead tr:first-child th').eq(index).css('width',$(this).outerWidth(true)+'px');
        sumWidth += $(this).outerWidth(true);
        
        if(index == tdElements.length-1){
          var scrollBarWidth = $('.header-fixed-wrapper').outerWidth(true)-sumWidth;
          $(this).parent().parent().parent().parent().parent().find('.header-fixed thead tr:first-child th').eq(index).css('width',$(this).outerWidth(true)+scrollBarWidth+'px');
        }
      });
    }
    
  $(window).load(function(){

		var url = location.href;

		$(".submenu").prev().removeClass("active");

		if(url.indexOf("/intro") > -1){
			$(".submenu.intro").prev().addClass("active");
		}else if(url.indexOf("/service") > -1){
			$(".submenu.service").prev().addClass("active");
		}else if(url.indexOf("/guide") > -1 || url.indexOf("/charge") > -1){
			$(".submenu.guide").prev().addClass("active");	
		}else if(url.indexOf("/traffic") > -1){
			$(".submenu.traffic").prev().addClass("active");	
		}else if(url.indexOf("/support") > -1 || url.indexOf("/member") > -1){
			$(".submenu.support").prev().addClass("active");	
		}
  });

  function menuMove(url, pageType){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직  
	  sessioncheck();
	  		
	var id = '${sessionScope.user_id}';
	if(id == "" && url.indexOf("traffic") > -1) {
		alert("로그인 후 이용하시기 바랍니다.");
		menuMove2("<%=request.getContextPath()%>/member/login.do");
		return;
	}
	
	
	var auth = '${sessionScope.user_auth_id}';
	if(auth == "10" && url.indexOf("traffic") > -1) {
		alert("전용회원만 이용하실 수 있습니다.");
		return;
	}

	var mail = '${sessionScope.mflag}';
	if(mail != "Y" && url.indexOf("traffic") > -1) {
		alert("메일 인증 후 이용하시기 바랍니다.");
		menuMove2("<%=request.getContextPath()%>/member/logout.do");
		return;
	}
	
	var frm = document.pageForm;
	//pageType : 메뉴 KT서비스 중분류 (A,B,C,D타입) -> 1,2,3,4 
	frm.pageType.value = pageType;
	frm.action=url;
	frm.submit();
  }

  function menuMove2(menuname) {	 
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직  
	  sessioncheck();
	  			  
	document.topMenuForm.action = menuname;
	document.topMenuForm.submit();
  }

  function onEnterkey(event, type){
	  if(event.keyCode == 13) {
		  if(type == "login"){
			  return checkMsg();
		  }else if(type == "search"){
			  return goSearch();
		  }
	  }
  }

//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직추가
  function sessioncheck(){
	 
  	  var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			var sessioncheck = true;
  			return sessioncheck;
  		}else{
  			actionUrl = "<%=request.getContextPath()%>/member/session_check.do";
  	 		
  	 		
  			 $.ajax({
  				    url : actionUrl,		    
  					type : "POST",
  					data : "user_id="+id
  			    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
  					+ "&request_url=" + location.href,
  					dataType :"json",
  			 	    success:function(data){
  			 	 	    if(data.result == "session_null") { 
  			 	 	  
  			 	 	   			 	 	    
  			 	 	 alert("session이 종료되었습니다");
  		  	 	      actionUrl = "<%=request.getContextPath()%>/member/logout2.do"; 
  		  	 	    var frm = document.sessionForm;
  		  	 	  	frm.action=actionUrl;
  		  	 	  	frm.submit();  		  	 	  
  		  	 	
  			 	 	    }else {    
  			 	 	  	var sessioncheck = true;
  						return sessioncheck;
  			}
  	},
  				     error:function(request,status,error){
  			//	    	 alert(request + " : " + status + " : " + error);
  				    	var sessioncheck = false;
							return sessioncheck;
  				     }
  				  });
  			
  			}
  	  }


  
 </script>
