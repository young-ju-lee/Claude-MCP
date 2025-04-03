<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  	<%@ include file="../common/meta.jsp" %>
  	<%@ include file="../common/css.jsp" %>
</head>

<body>
  <form name="pageForm" action="" method="post" >
 	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
	<input type="hidden" value="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getAttribute("javax.servlet.forward.request_uri")%>" name="request_url" />
	<input type="hidden" value="" name="pageType">
  </form>
  <form id="topMenuForm" name="topMenuForm" method="post" >
	<input type="hidden" name="user_id" value="<%=request.getSession().getAttribute("user_id") %>" />
	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
	<input type="hidden" value="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getAttribute("javax.servlet.forward.request_uri")%>" name="request_url" />
  </form>
  <!--2019.04.26 중복로그인 방지- session 폼 추가 공통함수 -->
  <form id="sessionForm" name="sessionForm" method="post" >
	<input type="hidden" name="user_id" value="<%=request.getSession().getAttribute("user_id") %>" />
	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
	<input type="hidden" value="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getAttribute("javax.servlet.forward.request_uri")%>" name="request_url" />
  </form>
  <header id="header">
    <div class="container">
      <div class="kt-egov-logo">
        <a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');">
          <img src="<%=request.getContextPath()%>/images/kt_egov_logo_new.png" alt="kt국가정보통신서비스" />
          <img src="<%=request.getContextPath()%>/images/kt_egov_logo_mobile_new.png" alt="kt국가정보통신서비스" class="mobile" /></a>
      </div>
      <a href="javascript:" class="hamberger-btn">
        <i class="material-icons">&#xE5D2;</i>
      </a>
      <div class="mini">
      	<a href="javascript:" class="account-btn">
	      <i class="material-icons">&#xE851;</i>
	    </a>
        <ul>
          <%
			//권한체크
				if(request.getSession().getAttribute("user_id") != null) {
			%>
					<li><a href="javascript:menuMove2('<%=request.getContextPath()%>/member/logout.do');">LOGOUT</a></li>
			<%
					if(request.getSession().getAttribute("user_auth_id").equals("40")) {
			%>
    					<li><a href="javascript:menuMove2('<%=request.getContextPath()%>/member/member_list.do');">관리자</a></li> 	
			<%		
					}
			%>
			        <!-- 2019.04.26 구조적보안진단 MY INFO 체크로직 추가 -->  
					<li><a href="javascript:menuMove2('<%=request.getContextPath()%>/member/member_edit2.do');">MY Info</a></li>
					<!-- 2017.12.22  보안성검토 회원탈퇴 추가 -->
					<li><a href="javascript:menuMove2('<%=request.getContextPath()%>/member/member_delete.do');">회원탈퇴</a></li>
			<%
				} else {
			%>
				<li><a href="javascript:menuMove2('<%=request.getContextPath()%>/member/login.do');">LOGIN</a></li>
				<!-- 2018.01.19 보안성검토 14세미만 어린이 구분 추가 -->
                <li><a href="javascript:menuMove2('<%=request.getContextPath()%>/member/check_member.do');">JOIN US</a></li>
				<!-- 2023-07-14 : 모의해킹 취약점 개선 : pw찾기 삭제 -->
			<%
				}
			%>
        </ul>
      </div>
    </div>
    <nav class="gnb ">
      <ul>
        <li>
          <a href="javascript:">제도소개</a>
          <div class="submenu intro">
            <div class="container">
              <div class="menu-title">
                <h3>제도소개</h3>
                <p>kt그룹 국가정보통신서비스<br />제도소개 입니다.</p>
                  <%-- 2019 09 27 좌측메뉴삭제 <ul>
                  <li>
                    <a href="javascript:menuMove('<%=request.getContextPath()%>/support/faq.do')">
                      <strong>FAQ</strong>
                      <p>자주 질문하시는 사항</p>
                    </a>
                  </li>
                  <li>
                    <a href="javascript:">
                    <!-- 2018.06.29 상담번호 수정 긴급배포 -->
                      <strong>1588-0114 (ARS 2 > 2)</strong>
                      <p>평일 9시 ~ 18시</p>
                    </a>
                  </li>
                </ul> --%>
              </div>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/definition.do');">정의</a>
                </dt>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/feature.do');">특징</a>
                </dt>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/service.do');">서비스체계</a>
                </dt>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepoint.do');">제도변경사항</a>
                </dt>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/counsel.do');">추진체계</a>
                </dt>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/propulsion.do');">추진경과</a>
                </dt>
              </dl>
            </div>
          </div>
        </li>

        <li>
          <a href="javascript:">kt그룹서비스</a>
          <div class="submenu service">
            <div class="container">
              <div class="menu-title">
                <h3>kt그룹 서비스</h3>
                <p>kt그룹 국가정보통신서비스<br />입니다.</p>
                 <%-- 2019 09 27 좌측메뉴삭제 <ul>
                  <li>
                    <a href="javascript:menuMove('<%=request.getContextPath()%>/support/faq.do')">
                      <strong>FAQ</strong>
                      <p>자주 질문하시는 사항</p>
                    </a>
                  </li>
                  <li>
                    <a href="javascript:">
                    <!-- 2018.06.29 상담번호 수정 긴급배포 -->
                      <strong>1588-0114 (ARS 2 > 2)</strong>
                      <p>평일 9시 ~ 18시</p>
                    </a>
                  </li>
                </ul> --%>
              </div>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 1);">A. 전용회선서비스</a>
                </dt>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_basic.do');">기본회선서비스</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_backbone.do');">백본회선서비스</a>
                </dd>
               <!-- 
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_cctv.do');">CCTV 전송회선 서비스</a>
                </dd>
                -->
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leasedline_infra.do');">전용회선 인프라</a>
                </dd>
              
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',2);">B. 인터넷서비스 </a>
                </dt>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_internet.do');">인터넷서비스</a>
                </dd>                 
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_infra.do');">인터넷 인프라</a>
                </dd> 
              </dl>
               <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',3);">C .인터넷전화서비스</a>
                </dt>                
                  <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip.do');">인터넷전화서비스</a>
                </dd>
                 <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">모바일행정전화 서비스</a>
                </dd>
                <%--  <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_ipvpn.do');">IP-VPN 서비스</a>
                </dd> --%>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip_infra.do');">인터넷 전화 인프라</a>
                </dd> 
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone_infra.do');">모바일행정전화 인프라</a>
                </dd>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',4);">D. 무선서비스</a>
                </dt>
               <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data.do');">무선데이터서비스</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_service.do');">IoT서비스</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_infra.do');">IoT 인프라</a>
                </dd>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',5);">E. CCTV서비스</a>
                </dt>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_cctv.do');">CCTV 전송회선 서비스</a>
                </dd>
                <%-- <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">모바일 인터넷 전화 서비스</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data.do');">무선 데이터 서비스</a>
                </dd> --%>
              <%--   <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_cctv.do');">무선 CCTV 서비스</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_securitywifi.do');">보안 Wifi 서비스</a>
                </dd> 
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a>
                </dd>
                --%>
              </dl>
            </div>
          </div>
        </li>

        <li>
          <a href="javascript:">이용안내</a>
          <div class="submenu guide">
            <div class="container">
              <div class="menu-title">
                <h3>이용안내</h3>
                <p>kt그룹 국가정보통신서비스<br />이용안내 입니다.</p>
                   <%-- 2019 09 27 좌측메뉴삭제 <ul>
                  <li>
                    <a href="javascript:menuMove('<%=request.getContextPath()%>/support/faq.do')">
                      <strong>FAQ</strong>
                      <p>자주 질문하시는 사항</p>
                    </a>
                  </li>
                  <li>
                    <a href="javascript:">
                    <!-- 2018.06.29 상담번호 수정 긴급배포 -->
                      <strong>1588-0114 (ARS 2 > 2)</strong>
                      <p>평일 9시 ~ 18시</p>
                    </a>
                  </li>
                </ul> --%>
              </div>
              <dl>
                <dt>
                  <a href="javascript:">이용절차</a>
                </dt>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/procedure.do');">이용절차</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/procedure1.do');">청약절차</a>
                </dd>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:">요금정보</a>
                </dt>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/charges.do');">요금표</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/area.do');">권역/거리/속도체계</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/charge/reckoning.do');">이용요금계산</a>
                </dd>
              </dl>

              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/counsel.do');">상담안내</a>
                </dt>
              </dl>

              <dl>
                <dt>
                  <a href="javascript:">운영/장애 안내</a>
                </dt>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/kt_merit.do');">KT특장점</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/ktsat_merit.do');">KTsat특장점</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/formation.do');">조직/체계</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/hindrance.do');">장애대응 방안</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/control.do');">망관리/감시</a>
                </dd>
              </dl>
            </div>
          </div>
        </li>

        <li>
          <a href="javascript:">트래픽조회</a>
          <div class="submenu traffic">
            <div class="container">
              <div class="menu-title">
                <h3>트래픽조회</h3>
                <p>kt그룹 국가정보통신서비스<br />트래픽조회 입니다.</p>
                <%-- 2019 09 27 좌측메뉴삭제 <ul>
                  <li>
                    <a href="javascript:menuMove('<%=request.getContextPath()%>/support/faq.do')">
                      <strong>FAQ</strong>
                      <p>자주 질문하시는 사항</p>
                    </a>
                  </li>
                  <li>
                    <a href="javascript:">
                    <!-- 2018.06.29 상담번호 수정 긴급배포 -->
                      <strong>1588-0114 (ARS 2 > 2)</strong>
                      <p>평일 9시 ~ 18시</p>
                    </a>
                  </li>
                </ul> --%>
              </div>
              <dl>
                <dt>
                  <a href="javascript:">트래픽조회 서비스</a>
                </dt>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/traffic/traffic1.do');">회선조회</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/traffic/traffic2.do');">트래픽조회</a>
                </dd>
                <dd>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/traffic/traffic3.do');">그래프</a>
                </dd>
              </dl>
            </div>
          </div>
        </li>

        <li>
          <a href="javascript:">고객지원</a>
          <div class="submenu support">
            <div class="container">
              <div class="menu-title">
                <h3>고객지원</h3>
                <p>kt그룹 국가정보통신서비스<br />고객지원 입니다.</p>
                <%-- 2019 09 27 좌측메뉴삭제  <ul>
                  <li>
                    <a href="javascript:menuMove('<%=request.getContextPath()%>/support/faq.do')">
                      <strong>FAQ</strong>
                      <p>자주 질문하시는 사항</p>
                    </a>
                  </li>
                  <li>
                    <a href="javascript:">
                    <!-- 2018.06.29 상담번호 수정 긴급배포 -->
                      <strong>1588-0114 (ARS 2 > 2)</strong>
                      <p>평일 9시 ~ 18시</p>
                    </a>
                  </li>
                </ul> --%>
              </div>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/support/notice_list.do');">공지사항</a>
                </dt>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/support/dataroom_list.do');">자료실</a>
                </dt>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/support/faq.do');">FAQ</a>
                </dt>
              </dl>
              <dl>
                 <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/member/agreement.do');">홈페이지 이용약관</a>
                </dt>
              </dl>
              <dl>
                <dt>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/member/personal.do');">개인정보처리방침</a>
                </dt>
              </dl>
            </div>
          </div>
        </li>
      </ul>
    </nav>
  </header>