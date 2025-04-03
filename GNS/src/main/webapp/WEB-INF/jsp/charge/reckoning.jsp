<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<jsp:include page="../common/header.jsp" />

<div class="page-contents">
	<div class="container">

		<div class="page-title">
			<div>
				<h2>요금정보</h2>
				<p>
					kt그룹 국가정보통신서비스의 <span class="kt-red-text">요금정보</span>를 알려드리겠습니다.
				</p>
			</div>
			<ul>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img
						src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif"
						alt="홈페이지 메인"></a></li>
				<li>이용안내</li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/charge/reckoning.do');">이용요금계산</a></li>
			</ul>
		</div>

		<div class="page-tab">
			<div class="current-label">
				<a href="javascript:">요금정보</a>
			</div>
			<ul>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/charges.do');">요금표</a>
				</li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/guide/area.do');">권역/거리/속도체계</a>
				</li>
				<li class="active"><a
					href="javascript:menuMove('<%=request.getContextPath()%>/charge/reckoning.do');">이용요금계산</a>
				</li>
			</ul>
		</div>

		<div class="row">
			<div class="col s12 m12 l9">
				<label for="search_llnum" class="kt-label">상품분류 :</label>
				<div class="select-wrap">
					<select class="s_goods" id="basic_goods" title="검색 옵션 선택"
						onChange="goCharge(this.value)">
						<option value="basic" selected>기본회선</option>
						<option value="basic">백본회선</option>						
						<option value="ip">인터넷</option>						
						<option value="cctv">CCTV</option>
						<option value="IoT">IoT</option>
					</select>
				</div>
			</div>
		</div>

		<div id="basic_service" class="feeForm">
			<jsp:include page="../service/leased_basic_detail2.jsp" />
		</div>
		<div id="cctv_service" class="feeForm" style="display: none">
			<jsp:include page="../service/leased_cctv_detail2.jsp" />
		</div>
	    <div id="IoT_service" class="feeForm" style="display: none">
			<jsp:include page="../service/iot_detail2.jsp" />
		</div>

		<div id="ip_service" class="feeForm" style="display: none">
			<jsp:include page="../service/ipservice_internet_detail2.jsp" />
		</div>

		<input type="hidden" id="dist_code" name="dist_code" value="" />
	</div>
</div>
<jsp:include page="../common/footer.jsp" />

<script>

function feeForm(value){
	
			
		
	var formId = value+"_service";
	$(".feeForm").each(function(){
		if(this.id == formId){
			$(this).show();
		}else{
			$(this).hide();
		}
	});

	if($("#basic_goods option:selected").text() == "IP-VPN") {
		$("#dist_gubun").html("시내/외 구분");
	}
}

function resetForm(){
	
			
		
	$("#subStationCombo").html("<option value=''>하위주소 입력 후 검색 결과에서 상세 주소를 선택하세요</option>");
	$("#topStationCombo").html("<option value=''>상위주소 입력 후 검색 결과에서 상세 주소를 선택하세요</option>");

	$("#speed option:eq(0)").attr("selected","selected");

	$("#basic_service input").val("");
	$("#basic_service label").html("");
	$("#cctv_service label").html("");
	$("#ip_service label").html("");
	$("#dist_code").val("");
}


function goCharge(value){
	
			
		
	feeForm(value);
	speed(value);
	resetForm();
}

function resetFee(){
		
	$("#fee").html("");
	$("#fee_internet").html("");
	$("#fee_cctv").html("");
	$("#fee_IoT").html("");
}

$(document).ready(function(){
	goCharge('basic');
	
	$("#subStationCombo").change(function(){
		$("#subStationView").html($("#subStationCombo>option:selected").text());
		distance();
		resetFee();
	});

	$("#topStationCombo").change(function(){
		$("#topStationView").html($("#topStationCombo>option:selected").text());
		resetFee();
	});

	$("#speed").change(function(){
		$("#speedView").html($("#speed>option:selected").text());
		resetFee();
	});

	$("#speed1").change(function(){
		$("#speedView1").html($("#speed1>option:selected").text());
		resetFee();
	});
	$("#speed2").change(function(){
		$("#speedView2").html($("#speed2>option:selected").text());
		resetFee();
	});

	$("#distance_cd").change(function(){
		$("#distance_cd_view").html($("#distance_cd>option:selected").text());
		resetFee();
	});

	$("#distance").change(function(){
		$("#distance_View").html($("#distance>option:selected").text());
		resetFee();
	});

	$("#contract_period").change(function(){
		$("#contract_period_view").html($("#contract_period>option:selected").text());
		resetFee();
	});

	$("#cctv_speed").change(function(){
		$("#cctv_speed_view").html($("#cctv_speed>option:selected").text());
		resetFee();
	});

	$("#dist_code2").change(function(){
		$("#dist_code2_view").html($("#dist_code2>option:selected").text());
		resetFee();
	});
	
});  

function speed(type){
	
		
	
	var svc_type = "";
	var param = "";

	if(type == "basic"){
		svc_type = $("#basic_goods option:selected").text();
		param =  escape(encodeURIComponent(svc_type));
		
		$.ajax({
		    url : "<%=request.getContextPath()%>/charge/speed.do",
		    data : "svc_type="+param
		    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
					+ "&request_url=" + location.href,
			type : "POST",
		    dataType :"json",
		    success:function(data){
		    	var temp = "<option value=\"\">선택하세요</option>";			
				$.each(data.speed, function(){
					temp += "<option value=\""+this.speed+"\">"+this.speed+"</option>";
				});
				$("#speed").html(temp);	
				$("#speed1").html(temp);	  
		    },
		    error:function(request,status,error){
		      alert(request + " : " + status + " : " + error);
			}
		});
		
	}else if(type == "cctv"){
		svc_type = "CCTV";
		param =  escape(encodeURIComponent(svc_type));
		
		$.ajax({
		    url : "<%=request.getContextPath()%>/charge/cctvsetting.do",
		    data : "svc_type="+param
		    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
	  				+ "&request_url=" + location.href,
			type : "POST",
		    dataType :"json",
		    success:function(data){
			    
		    	/* var temp = "<option value=\"\">선택하세요</option>";			
				$.each(data.feeArray, function(){
				temp += "<option value=\""+this.distance_cd+"\">"+this.fee_basis+"</option>";
	
				});
				$("#distance_cd").html(temp);	  */

				var temp = "<option value=\"\">선택하세요</option>";			
				$.each(data.distanceArray, function(){	
							
				temp += "<option value=\""+this.distance+"\">"+this.distance+"</option>";
	
				});
				$("#distance").html(temp);	 

				 var temp = "<option value=\"\">선택하세요</option>";			
				$.each(data.contractArray, function(){
				
				temp += "<option value=\""+this.dist_code+"\">"+this.dist_code+"</option>";
	
				});
				$("#dist_code2").html(temp);	 

				var temp = "<option value=\"\">선택하세요</option>";			
				$.each(data.speedArray, function(){
				temp += "<option value=\""+this.speed+"\">"+this.speed+"</option>";
	
				});
				$("#cctv_speed").html(temp);	
		    },
		    error:function(request,status,error){
		      alert(request + " : " + status + " : " + error);
		    }
		});
	}else if(type == "ip"){
		svc_type = "인터넷";
		param =  escape(encodeURIComponent(svc_type));
		$.ajax({
	    	url : "<%=request.getContextPath()%>/charge/speed.do",
	    	data : "svc_type="+param
	    		+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
					+ "&request_url=" + location.href,
			type : "POST",
	    	dataType :"json",
			success:function(data){
	    		var temp = "<option value=\"\">선택하세요</option>";			
				$.each(data.speed, function(){
				temp += "<option value=\""+this.speed+"\">"+this.speed+"</option>";

				});
				$("#speed1").html(temp);	 
	    	},
	    	error:function(request,status,error){
	      	alert(request + " : " + status + " : " + error);
	    	}
	  	});
	}else if(type == "IoT"){
		
		svc_type = "IoT";
		param =  escape(encodeURIComponent(svc_type));
		$.ajax({
	    	url : "<%=request.getContextPath()%>/charge/speed.do",
	    	data : "svc_type="+param
	    		+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
					+ "&request_url=" + location.href,
			type : "POST",
	    	dataType :"json",
			success:function(data){
	    		var temp = "<option value=\"\">선택하세요</option>";			
				$.each(data.speed, function(){
				temp += "<option value=\""+this.speed+"\">"+this.speed+"</option>";

				});
				$("#speed2").html(temp);	 
	    	},
	    	error:function(request,status,error){
	      	alert(request + " : " + status + " : " + error);
	    	}
	  	});
	}

}


function distance(){
	
		
	if($("#topStationCombo").val()==null ||$("#topStationCombo").val()==""){
		alert("상위국을 선택하세요");
		return;
	}
	
	if($("#subStationCombo").val()==null || $("#subStationCombo").val()=="") {
		alert("하위국을 선택하세요");
		return;
	}
	
	$.ajax({
	    url : "<%=request.getContextPath()%>/charge/distanceSearch.do",
	    data : "svc_type="+ $("#basic_goods option:selected").text()
	    	+ "&uco="+$("#topStationCombo").val() + "&lco="+$("#subStationCombo").val()
			+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href,
		type : "POST",
	    dataType :"json",
	    success:function(data){
		    if($("#basic_goods option:selected").text() != 'IP-VPN') {
		    	$("#displayDistance").html(data.dist_value + " Km");
			} else {
				$("#displayDistance").html(data.distance);
				/* 
		    	if(data.dist_code == "SN00") {
					$("#displayDistance").html("시내");
				} else {
					$("#displayDistance").html("시외");
				}
				 */
			}
	    	
	    	$("#dist_code").val(data.dist_code);
	    },
	    error:function(request,status,error){
	      alert(request + " : " + status + " : " + error);
	    }
	});

}

function stationSearch(obj){
	
	var param;
	
	if(obj == "T"){
		if($("#topStationText").val() == ""){
			alert("상위국명을 입력하세요.");
			return false;
		}

		param = $("#topStationText").val();
	
	}else if(obj == "S"){
		if($("#subStationText").val() == ""){
			alert("하위국명을 입력하세요.");
			return false;
		}

		if($("#topStationCombo").val() == null || $("#topStationCombo").val() == ""){
			alert("상위국을 먼저 선택하세요.");
			return false;
		}
		param =  escape(encodeURIComponent($("#subStationText").val()));
	}
	else{
		param = "";
	}
	
	$.ajax({
	    url : "<%=request.getContextPath()%>/charge/stationSearch.do",
	    data : "addr="+param
    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
		+ "&request_url=" + location.href,
		type : "POST",
	    dataType :"json",
	    success:function(data){
	    	//2019.12.06 주소 대량조회 방지 로직 추가, 0건인 경우 500건 이상인경우
	    	if(data.result == "addr_fail") {
 	 	    	alert("찾으실 지역의 동/읍/면 이름을 공백 없이 입력해주세요");
 	 	      if(obj == "T"){
 	 	      $("#topStationText").focus();
 	 	      $("#topStationText").val("");
 	 	 	    }else{
 	 	 	   $("#subStationText").focus();
 	 	 	   $("#subStationText").val("");
 	 	 	 }
	    	}else if(data.result == "addr_zero"){
	 	 	    	alert("해당 주소가 존재하지 않습니다");
	 	 	    	if(obj == "T"){
	 	 	      $("#topStationText").focus();
	 	 	      $("#topStationText").val("");
	 	 	 	    }else{
	 	 	 	   $("#subStationText").focus();
	 	 	 	  $("#subStationText").val("");
	 	 	 }
	    	}else{
	    		var temp = "<option value=\"\">선택하세요</option>";			
				$.each(data.stationList, function(){
					temp += "<option value=\""+this.icis_2+"\">"+this.addr+"</option>";
				});
				if(obj == "T"){
					$("#topStationCombo").html(temp);	 
				}else if(obj == "S"){
					$("#subStationCombo").html(temp);	 
				}
	    	}
	    	
	    },
	    error:function(request,status,error){
	      alert(request + " : " + status + " : " + error);
	    }
	});
}

function calcul(calType){
	
	var varSpeed = "";
	var svc_type = "";
	var data = "";
	var searchType = "";
	
	if(calType == "basic"){
		if($("#topStationCombo").val()==null ||$("#topStationCombo").val()==""){
			alert("상위국을 선택하세요.");
			return;
		}
		
		if($("#subStationCombo").val()==null || $("#subStationCombo").val()==""){
			alert("하위국을 선택하세요.");
			return;
		}
			
		if($("#speed").val() == ""){
			alert("속도를 선택하세요");
			return;
		}
		
		varSpeed = $("#speed").val();
		svc_type = $("#basic_goods option:selected").text();

		data = "dist_code="+$("#dist_code").val() + "&svc_type="+svc_type+"&speed="+varSpeed
			+"&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href;

		searchType = "";
		
	}else if(calType == "cctv"){
		
		if($("#distance").val() == ""){
			alert("계약구분을 선택하세요");
			return;
		}
		if($("#dist_code2").val() == ""){
			alert("약정기간을 선택하세요");
			return;
		}
		if($("#cctv_speed").val() == ""){
			alert("속도구분을 선택하세요");
			return;
		}
		
		varSpeed = $("#speed").val();
		svc_type = escape(encodeURIComponent("CCTV"));
		var distance = escape(encodeURIComponent($("#distance").val()));
		var cctv_speed = escape(encodeURIComponent($("#cctv_speed").val()));
		var dist_code = escape(encodeURIComponent($("#dist_code2").val()));

		data = "dist_code="+$("#dist_code2").val() + "&svc_type="+svc_type+"&speed="+varSpeed
	    	+"&distance="+distance+"&cctv_speed="+cctv_speed
				+"&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href;

		searchType = "_cctv";
		
		
	}else if(calType == "internet"){
		if($("#speed1").val() == ""){
			alert("속도를 선택하세요");
			return;
		}
		
		varSpeed = $("#speed1").val();
		svc_type = escape(encodeURIComponent("인터넷"));

		data = "&svc_type="+svc_type+"&speed="+varSpeed
			+"&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href;

		searchType = "_internet";
	}else if(calType == "IoT"){
		if($("#speed2").val() == ""){
			alert("요금제를 선택하세요");
			return;
		}
		
		varSpeed = $("#speed2").val();
		svc_type = escape(encodeURIComponent("IoT"));

		data = "&svc_type="+svc_type+"&speed="+varSpeed
			+"&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href;

		searchType = "_IoT";
	}

	$.ajax({
	    url : "<%=request.getContextPath()%>/charge/calculation.do",
	    data : data,
		type : "POST",
	    dataType :"json",
	    success:function(data){		    
	    	$("#fee"+searchType).html(data.fee+" 원");	
	    },
	    error:function(request,status,error){
	      alert(request + " : " + status + " : " + error);
	    }
	});

}
</script>
</html>