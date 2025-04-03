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
		        <h2>IoT 서비스 요금상세조회</h2>
		        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">IoT 서비스 요금상세조회</span>를 알려드리겠습니다.</p>
		      </div>
		    </div>
			<jsp:include page="../service/iot_detail2.jsp" />
		</div>
	</div>
	
</body>
<script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script>
$(document).ready(function(){

	speed();
	$("#speed2").change(function(){
		$("#speedView2").html($("#speed2>option:selected").text());
		$("#fee_IoT").html("");
	});

});  

function speed(){
	var svc_type = "IoT";
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
			$("#speed2").html(temp);	 
    	},
    	error:function(request,status,error){
      	alert(request + " : " + status + " : " + error);
    	}
  	});
}

function calcul(){
	if($("#speed2").val() == ""){
		alert("요금제를 선택하세요");
		return;
	}
	
	var varSpeed = $("#speed2").val();
	var svc_type = escape(encodeURIComponent("IoT"));
	
	$.ajax({
	    url : "<%=request.getContextPath()%>/charge/calculation.do",
	    data : "&svc_type="+svc_type+"&speed="+varSpeed
   			+"&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
			+ "&request_url=" + location.href,
		type : "POST",
	    dataType :"json",
	    success:function(data){
		    
	    	$("#fee_IoT").html(data.fee+" 원");	
	    },
	    error:function(request,status,error){
	      alert(request + " : " + status + " : " + error);
	    }
	});

}
</script>
</html>
