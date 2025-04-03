<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>요금정보</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">요금정보</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>이용안내</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/guide/charges.do');">요금정보</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">요금정보</a>
	      </div>
	      <ul>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/charges.do');">요금표</a>
	        </li> 
	       
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/area.do');">권역/거리/속도체계</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/charge/reckoning.do');">이용요금계산</a>
	        </li>   
	      </ul>
	    </div>
	    <div class="radio-tab2">
          <div class="row">
            <div class="col s12 m12 l9">
              <ul>
      		    <li>
                  <input name="r_gbn" id="r_gbn_l" onclick="goCharge(this.value)" type="radio" checked="" value="basic">
      			  <label class="kt-label" for="r_gbn_l">전용회선 서비스</label>
                </li>
                <li>
		    	  <input name="r_gbn" id="r_gbn_i" onclick="goCharge(this.value)" type="radio" value="ip">
		    	  <label class="kt-label " id="r_gbn2" for="r_gbn_i">인터넷 서비스</label>
                </li>
                <li>
		    	  <input name="r_gbn" id="r_gbn_s" onclick="goCharge(this.value)" type="radio" value="internet">
		    	  <label class="kt-label" for="r_gbn_s">인터넷전화 서비스</label>
                </li>
                <li>
		    	  <input name="r_gbn" id="r_gbn_w" onclick="goCharge(this.value)" type="radio" value="wireless_data">
		    	  <label class="kt-label" for="r_gbn_w">무선서비스</label>
                </li>
                <li>
                  <input name="r_gbn" id="r_gbn_c" onclick="goCharge(this.value)" type="radio" value="cctv">
		    	  <label class="kt-label" for="r_gbn_c">CCTV서비스</label>
                </li>
              </ul>
            </div>
            <div class="col s12 m12 l3">
              <div class="selbox">
		    	<label class="kt-label" for="search_llnum"><i class="material-icons"> devices</i>상품분류</label>
		    	<div class="select-wrap">
		          <select title="검색 옵션 선택" class="s_goods" id="basic_goods" onchange="goCharge(this.value)">
		            <option value="basic">기본회선</option>
	                <option value="backbone">백본회선</option>
	                                          
		          </select>
		          <select title="검색 옵션 선택" class="s_goods" id="ip_goods" style="display: none;" onchange="goCharge(this.value)">
		            <option value="ip">인터넷서비스</option>
	                                           
		          </select>
		          <select title="검색 옵션 선택" class="s_goods" id="internet_goods" style="display: none;" onchange="goCharge(this.value)">
		            <option value="internet">인터넷전화</option>
		              <option value="wireless_internet">모바일행정전화</option>
		          </select>
		          <select title="검색 옵션 선택" class="s_goods" id="wireless_data_goods" style="display: none;" onchange="goCharge(this.value)">		           
		            <option value="wireless_data">무선데이터 서비스</option>
		           <option value="iot">IoT 서비스</option>
		          </select>
                  <select title="검색 옵션 선택" class="s_goods" id="cctv_goods" style="display: none;" onchange="goCharge(this.value)">
		            <option value="cctv">CCTV</option>
		          </select>
		    	</div>
              </div>
            </div>
          </div>
	    </div>
	   	
	    <div class="row" id="charges3" >
	      <div class="col s12 m12 l12 right-align">
		    <a class="btn show-on-small" onclick="javascript:openPop('1');" href="javascript:">1단계 요금표</a>&nbsp;
		    <a class="btn show-on-small" onclick="javascript:openPop('2');" href="javascript:">2단계 요금표</a>&nbsp;
            <a class="btn show-on-small" onclick="javascript:openPop('3');" href="javascript:">3단계 요금표</a>
	      </div>
	     
	    </div>
	   <!--  <div class="row">
	      <div class="col s12 m12 l12">
      			<input class="kt-input" id="r_gbn_l" name="r_gbn" type="radio" value="basic" onClick="goCharge(this.value)" checked/>
      			<label for="r_gbn_l" class="kt-label right-m">전용회선 서비스</label>
		    	<input class="kt-input" id="r_gbn_i" name="r_gbn" type="radio" value="ip" onClick="goCharge(this.value)" />
		    	<label for="r_gbn_i" id="r_gbn2" class="kt-label right-m">인터넷 서비스</label>
		    	<input class="kt-input" id="r_gbn_s" name="r_gbn" type="radio" value="internet" onClick="goCharge(this.value)" />
		    	<label for="r_gbn_s" class="kt-label right-m">인터넷전화 서비스</label>
		    	<input class="kt-input" id="r_gbn_w" name="r_gbn" type="radio" value="wireless_data" onClick="goCharge(this.value)" />
		    	<label for="r_gbn_w" class="kt-label right-m">무선서비스</label>
		    	<input class="kt-input" id="r_gbn_w" name="r_gbn" type="radio" value="cctv" onClick="goCharge(this.value)" />
		    	<label for="r_gbn_w" class="kt-label right-m">CCTV서비스</label>
		    	<label for="search_llnum" class="kt-label">상품분류 :</label>
		    	<div class="select-wrap">
		            <select class="s_goods" id="basic_goods" title="검색 옵션 선택" onChange="goCharge(this.value)">
		              <option value="basic">기본회선</option>
	                  <option value="backbone">백본회선</option>
	                  <option value="cctv">CCTV</option>
	                  <option value="lamda">람다회선</option>                                  
		            </select>
		            <select class="s_goods" id="ip_goods" title="검색 옵션 선택" onChange="goCharge(this.value)" style="display: none;">
		              <option value="ip">인터넷서비스</option>
	                  <option value="ipvpn">IP-VPN서비스</option>                                
		            </select>
		            <select class="s_goods" id="internet_goods" title="검색 옵션 선택" onChange="goCharge(this.value)" style="display: none;">
		              <option value="internet">인터넷전화</option>
		                 <option value="wireless_internet">모바일행정전화</option>
		            </select>
		            <select class="s_goods" id="wireless_data_goods" title="검색 옵션 선택" onChange="goCharge(this.value)" style="display: none;">
		           
		              <option value="wireless_data">무선데이터 서비스</option>
		              <option value="iot_service">IoT 서비스</option>
		              <option value="wireless_cctv">무선CCTV</option>
		              <option value="wireless_wifi">보안WiFi</option>
		            </select>
		            <select class="s_goods" id="cctv_goods" title="검색 옵션 선택" onChange="goCharge(this.value)" style="display: none;">
		             <option value="CCTV">CCTV서비스</option>
		             </select>
		          </div>
	      </div>
	      </br></br>
	      <div class="col s12 m6 l12 left-align">
		      <a href="javascript:" class="btn show-on-small" onclick="javascript:openPop('1');">1단계 요금표</a>&nbsp;
		      <a href="javascript:" class="btn show-on-small" onclick="javascript:openPop('2');">2단계 요금표</a>
		       <a href="javascript:" class="btn show-on-small" onclick="javascript:openPop('3');">3단계 요금표</a>
	    	</div>
	    </div> -->
	  
	 <div id="basic_service" class="feeForm" style="display:none">
	    <jsp:include page="../service/leased_basic_fee.jsp" />
	    <br><br>
	  </div>
	  
	  <div id="backbone_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/leased_backbone_fee.jsp" />
	    <br><br>
	  </div>
	  
	  
	  
	  <%-- <div id="lamda_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/leased_lamda_fee.jsp" />
	    <br><br>
	  </div> --%>
	  
	  <div id="ip_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/ipservice_internet_fee.jsp" />
	    <br><br>
	  </div>
	  
	  <%-- <div id="ipvpn_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/ipservice_ipvpn_fee.jsp" />
	    <br><br>
	  </div> --%>
	  
	  <div id="internet_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/ipservice_soip_fee.jsp" />
	    <br><br>
	  </div>
	  
	  <div id="wireless_internet_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/wireless_m_fee.jsp" />
	    <br><br>
	  </div>
	  
	  <div id="wireless_data_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/wireless_data_fee.jsp" />
	    <br><br>
	  </div>
	  <div id="iot_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/iot_service_fee.jsp" />
	    <br><br>
	  </div>
	  <div id="cctv_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/leased_cctv_fee.jsp" />
	    <br><br>
	  </div>
	  <%-- <div id="wireless_cctv_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/wireless_cctv_fee.jsp" />
	    <br><br>
	  </div> --%>
	  
	  <%-- <div id="wireless_wifi_service" class="feeForm" style="display:none">
	  	 <jsp:include page="../service/wireless_securitywifi_fee.jsp" />
	    <br><br>
	  </div> --%>
	  
	</div>
</div>
	<jsp:include page="../common/footer.jsp" />

<form name="popForm">
	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token" />
	<input type="hidden" value="" name="request_url" />
</form>
<script>

function selectForm(value){	
	var formId = value+"_goods";
	$(".s_goods").each(function(){
		if(this.id == formId){
			$(this).show();
		}else{
			$(this).hide();
		}

	});
	$("#"+formId+" option").removeAttr("selected");
	$("#"+formId+" option:first").attr("selected","selected");
	if(value=='wireless_data'){		
		$("#charges3").hide();
		}else{
			$("#charges3").show();
			}
}

function feeForm(value){
	var formId = value+"_service";
	$(".feeForm").each(function(){
		if(this.id == formId){
			$(this).show();
		}else{
			$(this).hide();
		}
	});
}

function goCharge(value){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		

	feeForm(value);
	
	if(value == 'basic' || value == 'ip' || value == 'internet' || value == 'wireless_data' ||value=='cctv'){
		selectForm(value);
	}
}

$(document).ready(function(){
	feeForm('basic');
    
  });  

function openPop(type){

    var url;
    var target;
	var form = document.popForm;
    
    if(type == "1"){
    	url = "<%=request.getContextPath()%>/guide/charges_1.do";
    	target = "type1";
    }else if(type == "2"){
    	url = "<%=request.getContextPath()%>/guide/charges_2.do";
    	target = "type2";
    }else{
    	url = "<%=request.getContextPath()%>/guide/charges_3.do";
    	target = "type3";
        }

	window.open("",target,"width=1050, height=800, scrollbars=yes");

	form.action = url;
	form.method = "post";
	form.target = target;
	form.submit();
}
</script>
</html>