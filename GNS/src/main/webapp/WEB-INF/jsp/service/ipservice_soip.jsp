<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>인터넷전화 서비스</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">인터넷전화 서비스</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do',3);">C. 인터넷 전화 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip.do');">인터넷 전화 서비스</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">인터넷전화 서비스</a>
	      </div>
	      <ul>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip.do');">인터넷전화 서비스</a>
	        </li> 
	        <li>	          
	              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone.do');">모바일행정전화 서비스</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/ipservice_soip_infra.do');">인터넷전화 인프라</a>
	        </li> 
	         <li>	          
	              <a href="javascript:menuMove('<%=request.getContextPath()%>/service/wireless_m_internetphone_infra.do');">모바일행정전화 인프라</a>
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
	        <h3>인터넷전화  서비스란?</h3>
	         <ul class="list-style1">	         
	          <li><b>(기본서비스)</b> 기관간 통화, 국내통화, 국제통화, 이동통화</li>
	          <li><b>(부가서비스)</b> 모바일행정전화, 문자(SMS), 영상통화, 통화 연결음(컬러링), 발신번호표시, 대표번호발신, 변경번호 자동안내, 센트릭스(Centrex) 등</li>	         
	        </ul>
	      </div>
	      <div class="col s12">
	        <h3>인터넷전화 서비스 종류</h3>
	        <table class="centered">
	           <thead>
	           	<tr>
	              <th>구분</th>
	              <th>서비스명</th>
	              <th>서비스 정의</th>
	            </tr>
	          </thead>
	          <tbody>
	            <tr>
	              <td rowspan="4">기본서비스</td>
	              <td>기관간통화</td>
	              <td>인터넷전화로 행정기관간 통화 제공(무료) ※ 무료통화 : GNS 이용 행정기관 간</td>
	            </tr>
	            <tr>
	              <td>국내통화</td>
	              <td>인터넷전화에서 유선전화로 거는 통화(시내, 시외 구분없이 제공)</td>
	            </tr>
	            <tr>
	              <td>국제통화</td>
	              <td>인터넷전화를 사용하는 국제통화</td>
	            </tr>
	            <tr>
	              <td>이동통화(LM)</td>
	              <td>인터넷전화에서 이동전화로 거는 통화 (LM : Land to Mobile)</td>
	            </tr>
	            <tr>
	              <td rowspan="8" class="border-end-bottom">부가서비스</td>
	              <td>모바일행정전화</td>
	              <td>보안이 강화된 스마트폰 등 이동전화로 언제 어디서나 유선 행정전화와 같이 직원간 무료통화가 가능한 LTE기반 mVoIP서비스</td>
	            </tr>
	            <tr>	             
	              <td>문자(SMS)</td>
	              <td>유선전화 또는 이동전화로 보내는 단문메시지</td>
	            </tr>
	            <tr>
	              <td>영상통화</td>
	              <td>인터넷전화기 등 간 1:1 영상통화</td>
	            </tr>
	            <tr>
	              <td>통화연결음(컬러링)</td>
	              <td>링백톤을 기계음 대신 멀티미디어 링백톤 제공</td>
	            </tr>
	            <tr>
	              <td>발신번호표시</td>
	              <td>발신자의 전화번호 표시</td>
	            </tr>
	            <tr>
	              <td>대표번호발신</td>
	              <td>발신자의 대표전화번호 표시 제공</td>
	            </tr>
	            <tr>
	              <td>변경번호 자동안내</td>
	              <td>발신자에게 신규번호 안내 서비스 제공</td>
	            </tr>
	            <tr>
	              <td>센트릭스(Centrex)</td>
	              <td>IP-PBX 기능을 사업자망에서 제공하는 서비스</td>
	            </tr>
	          
	          </tbody>
	        	
	        </table>
	      </div>
	    </div>
	    
	  </div>
	  
	  <div class="container price_info " class="display:none">
		<jsp:include page="../service/ipservice_soip_fee.jsp" />
	    
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