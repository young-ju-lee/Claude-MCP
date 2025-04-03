<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>IP서비스</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">IP서비스</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',2);">B. 인터넷 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_infra.do');">인터넷 서비스 인프라</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">인터넷 서비스 인프라</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_internet.do');">인터넷 서비스</a>
	        </li> 
	      <%--   <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_ipvpn.do');">IP-VPN 서비스</a>
	        </li>   --%> 
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_infra.do');">인터넷 서비스 인프라</a>
	        </li>   
	      </ul>
	    </div>
	    
	    <div class="radio-tab">
	      <ul>
	        <li>
	          <input type="radio" name="radio-tab" id="t1" value="infra_info" checked="checked" />
	          <label for="t1"><i class="material-icons"> live_help</i> 인프라 정보</label>
	        </li>
	        <li>
	          <input type="radio" name="radio-tab" id="t2" value="security_system" />
	          <label for="t2"><i class="material-icons">live_help</i> 보안시스템</label>
	        </li>
	        <li>
	          <input type="radio" name="radio-tab" id="t3" value="ipv6_provide" />
	          <label for="t3"><i class="material-icons">live_help</i> Ipv6 제공</label>
	        </li>
	      </ul>
	    </div>
	  </div> 
	    
	  <div class="container infra_info">
	    <div class="row">
	      <div class="col s12">
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
	  
	  <div class="container security_system " class="display:none">
	  	<div class="row">
	      <div class="col s12">
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_infra5_2.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>국가정보통신서비스 인프라로 유입되는 사설IP, Reserved IP 또는 유해성 IP 등의 트래픽 유통 방지</li>
	          <li>싱크홀 라우터 운용
	          	<ul>
	          		<li>인터넷망으로 유입되는 DDoS 트래픽을 DDoS 차단시스템으로 우회하여 공격성 트래픽은 차단하고 일반 트래픽은 정상소통</li>
	          	</ul>
	          </li>
	          <li>IPS 시스템 운용
	          	<ul>
	          		<li>인터넷망으로 유입되는 트래픽을 최신 탐지정책에 의해 탐지하여 차단정책에 따라 비정상트래픽을 차단</li>
	          	</ul>
	          </li>
	          <li>통신망 장비 보안성 강화
	          	<ul>
	          		<li>인터넷망으로 유입되는 Dos, spoofing 등의 유해성 IP트래픽에 대해 통신망 장비에서 비정상 트래픽 차단</li>
	          		<li>그외 인증체계에 기반한 장비 접근 제어, 인증된 라우터간 정보 전달 등을 통한 보안 취약점 차단</li>
	          		<li> 패킷 필터링</li>
	          		<li>접근 제한</li>
	          		<li>라우팅 취약성 보안</li>
	          	</ul>
	          </li>
	        </ul>
	      </div>
	    </div>
	  </div>
	  
	  <div class="container ipv6_provide " class="display:none">
	  	<div class="row">
	      <div class="col s12">
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_infra6_2.gif" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>서울/대전 G/W ~ 6NGIX 간 연동</li>
	          <li>서울/대전 GW 에 NAT64 기능 도입</li>
	          <li>DNS64 구축</li>
	          <li>싱크홀 라우터에 IPv6 기능 구현</li>
	        </ul>
	        
	        <h3>단계별 IPv6 서비스 제공</h3>
	        <ul class="list-style1">
	          <li>1단계
	          	<ul>
	          		<li>통신 장비에 대하여 IPv4/ IPv6 Dual Stack 지원 가능한 장비 도입</li>
	          		<li>단일 호스트 또는 네트워크 장비에서 IPv4와 IPv6를 동시에 사용</li>
	          		<li>IPv6 네트워크 구축 후 기존 IPv4로 서비스 중인 네트워크 통합 (IPv6 Tunnel 사용)</li>
	          	</ul>
	          </li>
	          <li>2단계
	          	<ul>
	          		<li>국가정보통신서비스 이용 국가기관 IPv6로의 전환 이행</li>
	          		<li>국가정보통신서비스 전체 IPv6를 통한 연동 서비스 제공</li>
	          	</ul>
	          </li>
	        </ul>
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