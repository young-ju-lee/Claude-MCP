<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>무선 서비스</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">무선서비스</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',4);">D. 무선 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data.do');">무선 데이터 서비스</a></li>	     
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">무선데이터 서비스</a>
	      </div>
	      <ul>
	        <%-- <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">모바일인터넷전화 서비스</a>
	        </li>  --%>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_data.do');">무선데이터 서비스</a>
	        </li>  
	         <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_service.do');">IoT 서비스</a>
	         </li>	       
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_infra.do');">무선 인프라</a>
	        </li>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/iot_infra.do');">IoT 인프라</a>
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
	      <div class="col s12 l6">
	        <h3>무선데이터 서비스란?</h3>
	        <ul class="list-style1">
	          <li>기업전용 LTE 서비스
	          	<ul>
	          		<li>기업전용 LTE 서비스는 전용 LTE망을 활용하여 업무용 데이터를 사용할 수 있는 서비스입니다.</li>
	          	</ul>
		        <div class="center-align">
			    	<img src="<%=request.getContextPath()%>/images/img_wireless_lte.jpg" alt="tset" class="responsive-img" />
		    	</div>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12 l6">
	      <br><br>
	      	<ul class="list-style1">
    		  <li>기업전용 LTE 요금제 (단위 : 원/월, VAT포함) </li>
	        </ul>
	        <table class="centered">
				<thead>
		            <tr>
		              <th>데이터 제공량</th>
		              <th>서비스 이용료 </th>
		              <th>비고</th>
		            </tr>
		          </thead>
		          <tbody>
						<tr>
							<td>300GB</td>
							<td>200만</td>
							<td rowspan="7" class="border-end-bottom">데이터 초과 시 초과요율 : 0.005원(0.5Kb) 적용,<br>
								데이터 한도 소진 이후 초과 요금을 지불하지 않을 경우 QoS(3Mbps) 제공
							</td>
						</tr>
						<tr>
							<td>500GB</td>
							<td>300만</td>
						</tr>
						<tr>
							<td>1 Tera</td>
							<td>500만</td>
						</tr>
						<tr>
							<td>5 Tera</td>
							<td>1,000만</td>
						</tr>
						<tr>
							<td>15 Tera</td>
							<td>2,000만</td>
						</tr>
						<tr>
							<td>30 Tera</td>
							<td>3,000만</td>
						</tr>
						<tr>
							<td>50 Tera</td>
							<td>4,000만</td>
						</tr>
					</tbody>
				</table>
	      </div>
	    </div>
	    
	    <div class="row">
	      <div class="col s12 l6">
	        <ul class="list-style1">
	          <li>부가서비스 : 비즈데이터
	          	<ul>
	          		<li>기업 및 공공기 임직원들이 패드, 스마트폰 및 데이터서비스 모뎀 유형의 단말을 통해 소속기관의 업무용 서버에 접속하여 그룹웨어 등 업무용 솔루션을 이용할 수 있게 하는 IP기반 데이터 부가서비스</li>
	          	</ul>
		    	<img src="<%=request.getContextPath()%>/images/img_wireless_data.jpg" alt="tset" class="responsive-img" />
		    	
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12 l6">
			<ul class="list-style1">
   		  		<li>요금제 및 상품 가입 </li>
	        </ul>
	        <table class="centered">
				<thead>
		            <tr>
		              <th>구분</th>
		              <th>요금</th>
		              <th>데이터</th>
		            </tr>
		          </thead>
		          <tbody>
						<tr>
							<td>오피스 1G</td>
							<td>1,000/월</td>
							<td>1GB</td>
						</tr>
						<tr>
							<td>오피스 4G</td>
							<td>2,000/월</td>
							<td>4GB</td>
						</tr>
						<tr>
							<td>비즈데이터 프리</td>
							<td>5,000/월</td>
							<td>무제한</td>
						</tr>
					</tbody>
				</table>
				<ul class="list-style1">
	    		  <li>상품가입기준 : IP 4개 등록(50명 이상 고객)
	    		  	<ul>
	    		  		<li>네트워크 시스템 수용 능력을 감안하여 별도 협의</li>
	    		  	</ul>
	    		  </li>
	    		  <li>네트워크 등록 : 요청일 기준 D+14일 소요 (부가서비스 적용시점 최소 2주전, 네트워크 IP 등록요청) </li>
	    		  <li>요청 절차 : 기안 작성 및 신청서, 사업자 등록증 사본 제출
	    		  	<ul>
	    		  		<li>Biz판매기획팀/모바일컨설팅팀을 통해 요청</li>
	    		  	</ul>
	    		  </li>
	    		  <li><span class="kt-red-text">주의 사항 : IP변경 시, 2주전 요청 필요 (요청 없이 변경 시, 사용량 정상과금 됨)</span> </li>
		        </ul>
	      </div>
	    </div>
	    
	  </div>
	  <div class="container price_info " class="display:none">
	  	 <jsp:include page="../service/wireless_data_fee.jsp" />
	  </div>
	</div>

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
</script>
</html>