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
		        <h2>CCTV회선 서비스 요금상세조회</h2>
		        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">CCTV회선 서비스 요금상세조회</span>를 알려드리겠습니다.</p>
		      </div>
		    </div>
			<jsp:include page="../service/leased_cctv_detail2.jsp" />
		</div>
	</div>
	
</body>
<script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	speed();

	$("#distance").change(function(){
		$("#distance_view").html($("#distance>option:selected").text());
		$("#fee_cctv").html("");
	});
	$("#contract_period").change(function(){
		$("#contract_period_view").html($("#contract_period>option:selected").text());
		$("#fee_cctv").html("");
	});

	$("#dist_code2").change(function(){
		$("#dist_code2_view").html($("#dist_code2>option:selected").text());
		$("#fee_cctv").html("");
	});

	$("#cctv_speed").change(function(){
		$("#cctv_speed_view").html($("#cctv_speed>option:selected").text());
		$("#fee_cctv").html("");
	}); 
	
});  

function speed(){
	var svc_type = "CCTV";
	var param =  escape(encodeURIComponent(svc_type));
	
	$.ajax({
	    url : "<%=request.getContextPath()%>/charge/cctvsetting.do",
	    data : "svc_type="+param
	    		+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
  				+ "&request_url=" + location.href,
		type : "POST",
	    dataType :"json",
	    success:function(data){
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
}


function calcul(){
		

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


	searchType = "_cctv";
	var varSpeed = $("#speed").val();
	var svc_type = escape(encodeURIComponent("CCTV"));
	var distance = escape(encodeURIComponent($("#distance").val()));
	var cctv_speed = escape(encodeURIComponent($("#cctv_speed").val()));
	
	$.ajax({
	    url : "<%=request.getContextPath()%>/charge/calculation.do",
	 data : "dist_code="+$("#dist_code2").val() + "&svc_type="+svc_type+"&speed="+varSpeed
	    	+"&distance_cd="+$("#distance_cd").val()+"&distance="+distance+"&contract_period="
	    	+$("#contract_period").val()+"&cctv_speed="+cctv_speed
   			+"&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href,
		type : "POST",
	    dataType :"json",
	    success:function(data){
		    
	    	$("#fee_cctv").html(data.fee+" 원");	
	    },
	    error:function(request,status,error){
	      alert(request + " : " + status + " : " + error);
	    }
	});

}
</script>
</html>
