<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<jsp:include page="../common/header.jsp" />

<div class="page-contents">
	<div class="container">
	   
	    <div class="page-title">
			<div>
				<h2>kt그룹서비스</h2>
				<p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">A·B·C·D·E Group</span>에 대해 소개 드립니다.</p>
			</div>
			<ul>
	        	<li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
				<li>kt그룹서비스</li>
			</ul>
		</div>
	    
		<div class="service-tab">
			<div class="current-label">
				<a href="javascript:">A. 전용회선서비스</a>
			</div>
			<ul>
	            <li>
	              <input type="radio" name="service-tab" id="service-tab-1" value="1" checked="checked" />
	              <label for="service-tab-1"><span>A. 전용회선서비스</span></label>
	            </li> 
	            <li>
	              <input type="radio" name="service-tab" id="service-tab-2" value="2" />
	              <label for="service-tab-2"><span>B.인터넷서비스 </span></label>
	            </li> 
	             <li>
	              <input type="radio" name="service-tab" id="service-tab-3" value="3" />
	              <label for="service-tab-3"><span>C. 인터넷전화서비스</span></label>
	            </li> 
	            <li>
	              <input type="radio" name="service-tab" id="service-tab-4" value="4" />
	              <label for="service-tab-4"><span>D. 무선서비스</span></label>
	            </li>      
	            <li>
	              <input type="radio" name="service-tab" id="service-tab-5" value="5" />
	              <label for="service-tab-5"><span>E. CCTV서비스</span></label>
	            </li>
			</ul>
		</div>
		
		<div class="service-info">
          <ul>
            <li class="list hide service-1">
              <div>
                <div class="image">
	        		<div class="center-align">
	                  <img src="<%=request.getContextPath()%>/images/service_01.png" alt="전용회선서비스" class="responsive-img" />
	                </div>
                </div>
                <div class="text">
                  <h2>A그룹[전용회선서비스]</h2>                  
                  <p>전용회선서비스 그룹은 <b>기본회선(망분리회선, 단기이용 포함), 백본회선</b>으로 구성</p>
                  <p><b>(망분리회선)</b> 상.하위 주소가 동일한 기본회선서비스에 대하여 인터넷.</br>업무망 분리 용도 등으로 여러 회선을 묶어서 제공하는 서비스</p>
                  <p><b>(단기이용)</b> 선거.행사.대회 등 일정 기간 기본회선을 사용일수 기반으로 </br>제공하는 서비스</p>
                   
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leasedline_infra.do');" class="btn">전용회선 인프라</a>
                </div>
              </div>
            </li>
            <li class="list hide service-2">
              <div>
                <div class="image">
		        	<div class="center-align">
	                  <img src="<%=request.getContextPath()%>/images/service_02.png" alt="IP 서비스" class="responsive-img" />
                    </div>
                </div>
                <div class="text">
                  <h2>B그룹[인터넷서비스]</h2>                  
                  <p>인터넷 서비스 그룹은 <b>인터넷 접속을 위한 서비스</b>로 구성</p>
                  <p>민간기관 트래픽과 물리적·논리적으로 분리된 전용 인터넷망을 통한 안정적인 인터넷 서비스</p>
                  <p><b>(단기이용)</b> 선거.행사.대회 등 일정 기간 인터넷을 사용일수 기반으로  제공하는 서비스</p>
                <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_infra.do');" class="btn">인터넷 인프라</a>
                </div>
              </div>
            </li>
            <li class="list hide service-3">
              <div>
                <div class="image">
			        <div class="center-align">
		              <img src="<%=request.getContextPath()%>/images/service_03.png" alt="인터넷 전화 서비스" class="responsive-img" />
                    </div>
                </div>
                <div class="text">
                  <h2>C그룹[인터넷전화서비스]</h2>
                  <h4>설치와 사용이 편리한 인터넷 전화 서비스</h4>
                  <p>일반전화 대비 저렴한 요금으로 다양한 부가 기능 서비스까지 이용할 수 있는 서비스 환경을 제공합니다. 인터넷전화 서비스 그룹은 인터넷전화 및 모바일행정전화 서비스로 구성됩니다.</p>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip_infra.do');" class="btn">인터넷 전화 인프라</a>
                </div>
              </div>
            </li>
            <li class="list hide service-4">
              <div>
                <div class="image">
		        	<div class="center-align">
	                  <img src="<%=request.getContextPath()%>/images/service_05.png" alt="무선 서비스" class="responsive-img" />
                    </div>
                </div>
                <div class="text">
                  <h2>D그룹[무선 서비스]</h2>
                  <h4>요금제 부담 없이 이용하는 업무용 무선 서비스</h4>
                  <p>무선서비스 그룹은 <b>무선데이터</b>와 <b>Iot서비스</b>로 구성됩니다.</p>
                  <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');" class="btn">무선 인프라</a>
                </div>
              </div>
            </li>
             <li class="list hide service-5">
              <div>
                <div class="image">
		        	<div class="center-align">
	                  <img src="<%=request.getContextPath()%>/images/service_06.png" alt="CCTV 서비스" class="responsive-img" />
                    </div>
                </div>
                <div class="text">
                  <h2>E그룹[CCTV 서비스]</h2>
                  <h4>CCTV서비스 그룹은 CCTV전송회선 서비스로 구성</h4> 
                </div>
              </div>
            </li>
          </ul>
        </div>
        
        <h3>서비스 목록</h3>
        <div class="service-list">
          <ul>
            <li class="list hide service-1">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_basic.do');">
                <div class="image" style="vertical-align:middle;">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="기본회선 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>기본회선 서비스</h4>
                  <p>기관간 연결수단을 제공하는 전용회선 서비스</p>
                  <p>접속방식 : E1/T1, DS3, SDH, Ethernet 등</p>
                  <p>망분리회선, 단기이용회선 포함</p>
                </div>
              </a>
            </li>
            <li class="list hide service-1">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_backbone.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="백본회선 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>백본회선 서비스</h4>
                  <p>백본구간의 기관간 연결수단을 제공하는 전용</p>
                  	<p>회선서비스로 특정 기관에 적용</p>
                  	<p>접속방식 : E1/T1, DS3, SDH, Ethernet 등</p>
                </div>
              </a>
            </li>
            <%-- <li class="list hide service-1">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_cctv.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="cctv 전송회선 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>CCTV 전송회선 서비스</h4>
                  <p>CCTV와 관제센터 등을 연결하기 위한 전용회선 서비스</p>
                  <p>접속방식 : Ethernet 등</p>
                </div>
              </a>
            </li> --%>
           <%--  <li class="list hide service-1">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_lamda.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="람다회선 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>람다회선 서비스</h4>
                  <p>통합전산센터의 재해복구망 구성을 위하여 람다를 기반으로</p>
                  <p>제공하는 전용회선 서비스</p>
                </div>
              </a>
            </li> --%>
            <li class="list hide service-2">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_internet.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="인터넷 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>인터넷 서비스</h4>
                  <p>이용기관에 인터넷 접속 수단을 제공하는 서비스</p>
                  <p>접속방식 : E1/T1, DS3, SDH, Ethernet 등</p>
                  <p>단기이용 서비스 포함</p>
                </div>
              </a>
            </li>
           <%--  <li class="list hide service-2">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_ipvpn.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="IP-VPN 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>IP-VPN 서비스</h4>
                  <p>IP망 기반으로 VPN 구성 수단을 제공하는 서비스</p>
                  <p>접속방식 : E1/T1, DS3, SDH, Ethernet 등</p>
                </div>
              </a>
            </li> --%>
            <li class="list hide service-3">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="인터넷 전화 서비스" class="responsive-img" />
                </div>                
                <div class="text">
                  <h4>인터넷전화 서비스</h4>
                  <p>기본 서비스와 부가서비스로 구성</p>
                
                </div>
              </a>
            </li>
            <li class="list hide service-3">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="모바일행정전화 서비스" class="responsive-img" />
                </div>                
                <div class="text">
                  <h4>모바일행정전화 서비스</h4>
                  <p>LTE기반 mVoIP서비스</p>
                 
                </div>
              </a>
            </li>
            <li class="list hide service-4">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="무선 데이터 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>무선 데이터 서비스</h4>
                  <p>스마트 단말등으로 모바일 행정서비스와 현장 업무서비스 등을 이용하기 위한 LTE 기반 데이터 서비스</p>
                </div>
              </a>
            </li>
             <li class="list hide service-4">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_service.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="Iot 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>Iot 서비스</h4>
                  <p>정보통신기술 기반으로 모든 사물을 연결해 플랫폼·네트워크간 정보를 교류하고 지원하는 지능형 인프라 및 서비스</p>
                </div>
              </a>
            </li>
           <%--  <li class="list hide service-4">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="무선 인터넷 전화 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>모바일인터넷전화 서비스</h4>
                  <p>보안이 강화된 스마트폰 등 이동전화로 언제 어디서나 유선 행정 전화와 같이 직원간 무료통화가 가능한 LTE 기반 mVOIP서비스</p>
                </div>
              </a>
            </li> --%>
            <li class="list hide service-5">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_cctv.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="cctv 전송회선 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>CCTV 전송회선 서비스</h4>
                  <p>이용기관의 CCTV단말로부터 관제센터까지 영상을 전달하기 위해 제공하는 시내전용회선서비스</p>
                  <p>접속방식 : Ethernet 등</p>
                </div>
              </a>
            </li>
          <%--   <li class="list hide service-4">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_cctv.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="무선 CCTV 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>무선 CCTV 서비스</h4>
                  <p>CCTV 단말(감시카메라)로 촬영된 영상 정보를 무선망(WiFi, LTE 등)을 이용하여 데이터 저장매체로 기록하는 서비스</p>
                </div>
              </a>
            </li>
            <li class="list hide service-4">
              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_securitywifi.do');">
                <div class="image">
                  <img src="<%=request.getContextPath()%>/images/addService_img8.png" alt="보안WiFi 서비스" class="responsive-img" />
                </div>
                <div class="text">
                  <h4>보안WiFi 서비스</h4>
                  <p>보안적합성 검증을 통해 보안성이 강화된 WiFi로 부,처내 업무망과 인터넷망 접속 수단을 제공하는 서비스</p>
                </div>
              </a>
            </li> --%>
            
          </ul>
        </div>
		
	</div>
</div>

<jsp:include page="../common/footer.jsp" />

<script>
$(document).ready(function(){
	 $('.service-tab li > label').click(function () {
        $('div.current-label > a').text($(this).text());
        $('.service-tab').removeClass('active');
        service_page_tab_ctr($('.service-tab li > label').index($(this))+1);
      }); 

     var pageType = ${pageType};

     $('.service-tab li > label')[pageType-1].click();
	service_page_tab_ctr(pageType);
  }); 
function service_page_tab_ctr(current_num){
	
    $('li.list').each(function(){
      if($(this).hasClass('service-'+current_num)){
        $(this).removeClass('hide');
      }else {
        if(!$(this).hasClass('hide')){
          $(this).addClass('hide');
        }
      }
    });
  }
</script>
</html>