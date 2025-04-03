<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="../common/meta.jsp" %>
  <%@ include file="../common/css.jsp" %>
</head>

<body>
	<div class="page-contents" style="margin-bottom: 0;">
		<div class="container">
			<div class="page-title" style="height:auto">
		      <div style="height:auto">
		        <h2>IP-VPN 서비스 요금상세조회</h2>
		        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">IP-VPN 서비스 요금상세조회</span>를 알려드리겠습니다.</p>
		      </div>
		    </div>
			<jsp:include page="../service/leased_basic_detail2.jsp" />
		</div>
	</div>
	
</body>
<script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	$("#dist_gubun").html("시내/외 구분");
	speed();
	
	$("#subStationCombo").change(function(){
		$("#subStationView").html($("#subStationCombo>option:selected").text());
		distance();
	});

	$("#topStationCombo").change(function(){
		$("#topStationView").html($("#topStationCombo>option:selected").text());
	});
		

	$("#speed").change(function(){
		$("#speedView").html($("#speed>option:selected").text());
	});
    
  });  

function speed(){
	var svc_type = "IP-VPN";
	var param =  escape(encodeURIComponent(svc_type));

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
	    },
	    error:function(request,status,error){
	      alert(request + " : " + status + " : " + error);
		}
	});
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
	    data : "uco="+$("#topStationCombo").val() + "&lco="+$("#subStationCombo").val()
			+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href,
		type : "POST",
	    dataType :"json",
	    success:function(data){
	    	$("#displayDistance").html(data.distance);
	    	/* 
	    	if(data.dist_code == "SN00") {
				$("#displayDistance").html("시내");
			} else {
				$("#displayDistance").html("시외");
			}
			 */
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
	    	var temp = "<option value=\"\">선택하세요</option>";			
			$.each(data.stationList, function(){
			temp += "<option value=\""+this.icis_2+"\">"+this.addr+"</option>";

			});
			if(obj == "T"){
				$("#topStationCombo").html(temp);	 
			}else if(obj == "S"){
				$("#subStationCombo").html(temp);	 
			}
	    },
	    error:function(request,status,error){
	      alert(request + " : " + status + " : " + error);
	    }
	});
	
}

function calcul(){
				
		
	
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
	
	var varSpeed = $("#speed").val();
	var svc_type = escape(encodeURIComponent("IP-VPN"));
	
	$.ajax({
	    url : "<%=request.getContextPath()%>/charge/calculation.do",
	    data : "dist_code="+$("#dist_code").val() + "&svc_type="+svc_type+"&speed="+varSpeed
   			+"&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href,
		type : "POST",
	    dataType :"json",
	    success:function(data){
		    
	    	$("#fee").html(data.fee+" 원");	
	    },
	    error:function(request,status,error){
	      alert(request + " : " + status + " : " + error);
	    }
	  });

}
</script>
</html>
