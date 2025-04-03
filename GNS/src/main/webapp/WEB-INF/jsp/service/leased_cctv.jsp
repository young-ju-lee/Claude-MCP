<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>CCTV 서비스</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">CCTV 서비스</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 5);">E. CCTV 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_cctv.do');">CCTV 전송회선 서비스</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">CCTV 전송서비스</a>
	      </div>
	      <ul>	            
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_cctv.do');">CCTV 전송회선 서비스</a>
	        </li>	  
	      </ul>
	    </div>
	    
	    <div class="radio-tab">
	      <ul>
	        <li>
	          <input type="radio" name="radio-tab" id="t1" value="service_info" checked="checked" />
	          <label for="t1"><i class="material-icons"> live_help</i> 서비스 정보</label>
	        </li>
	        <li>
	          <input type="radio" name="radio-tab" id="t2" value="price_info" />
	          <label for="t2"><i class="material-icons">receipt</i> 요금 안내</label>
	        </li>
	      </ul>
	    </div>
	  </div>
	    
	  <div class="container service_info">
	  	<div class="row">
	      <div class="col s12">
	        <h3>CCTV 전송서비스란?</h3>
	        <ul class="list-style1">
	          <li>이용기관의 CCTV 단말로부터 관제센터까지 영상을 전달하기 위해 제공하는 시내 전용회선 서비스</li>
	        </ul>
	        <div class="left-align">
	        	<img src="<%=request.getContextPath()%>/images/img_circuit03.gif" alt="tset" class="responsive-img1" />
	        </div>
	      </div>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>CCTV 전송 서비스 적용 기준 및 예시</h3>
	        <ul class="list-style1">
	          <li>서비스 회선요금은 카메라수와 무관하게 1회선(10Mbps)당 이용요금 부과
	          	<ul>
	          		<li>(예시 1) 통신사업자가 CCTV 카메라별로 물리적 회선/포트(10M) 단위로 제공하는 경우 회선별로 요금 청구</li>
	          		 <div class="left-align">
	        	<img src="<%=request.getContextPath()%>/images/img_cctv_new_01.png" alt="tset" class="responsive-img2" />
	              </div>
	          		<li>(예시 2) 통신사업자는 CCTV 1회선(30M)을 제공하고 기관이 다수의 카메라를 연결하여 이용하는 경우 통신사업자는 물리적인 1회선 구성 및 요금 청구</li>
	          		 <div class="left-align">
	        	<img src="<%=request.getContextPath()%>/images/img_cctv_new_02.png" alt="tset" class="responsive-img2" />
	        </div>
	          	</ul>
	          </li>
	        </ul>
	        
	        <h3>CCTV 전송 서비스 이용 조건</h3>
	        <ul class="list-style1">
	          <li>CCTV서비스는 인입선을 공중(가공)으로 설치하는 것을 원칙으로 하되, 다음과 같은 경우에는 해당기관과 별도 협의를 통해 설치비용 중 일부를 요금에 반영
	          	<ul>
	          		<li>불가피한 사정으로 인입선을 지중화 하는 경우</li>
	          		<li>CCTV설치구역이 산간오지, 도서 해안지역 등 설치가 어려운 지역</li>
	          	</ul>
	          </li>
	          <li>신규로 공급되는 회선은 '신규계약' 요금을 적용(신설공사 수반)하되, 5년이 경과된 기 공급회선은 '재계약'요금을 적용</li>
	         <!--  <li>100M 이상으로 회선속도를 높일 경우, 10Mbps당 계약요금의 20%에 해당하는 비용을 추가부담</li> -->
	          <li>계약기간 만료 전 이용계약 해지 시 이용약정 잔여기간 총 요금의 50%를 위약금으로 부담(단, 공익사업으로 부득이 CCTV를 철거하는 경우 제외)</li>
	        </ul>
	        <%-- <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/img_circuit06.gif" alt="tset" class="responsive-img" />
	        </div> --%>
	      </div>
	    </div>
	  </div>
	    
	<div class="container price_info " class="display:none">
    	<!-- <div class="col s12 m3 right-align">
	    	<a href="javascript:" class="btn show-on-small" onclick="openPop();">요금상세조회</a>
	    </div> -->
		<jsp:include page="../service/leased_cctv_fee.jsp" />
    </div>
	    
	</div>

<form name="popForm">
	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token" />
	<input type="hidden" value="" name="request_url" />
</form>
	<jsp:include page="../common/footer.jsp" />
<script>
$(document).ready(function(){
    $(".price_info").hide();
	
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
function openPop(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		
	var form = document.popForm;
	var url = "<%=request.getContextPath()%>/service/leased_cctv_detail.do";
	window.open("","popForm","width=1100, height=635, scrollbars=yes");

	form.action = url;
	form.method = "post";
	form.target = "popForm";
	form.submit();
}
</script>
</html>