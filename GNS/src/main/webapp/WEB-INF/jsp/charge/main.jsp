<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : main.jsp
* Overview    : 이용요금계산 테스트 화면 
* Description : 이용요금계산 테스트 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<meta http-equiv="Cache-Control" content="no-store">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="1L">
<%-- 
<%
response.setHeader("Cache-Control", "no_store");
response.setHeader("Cache-control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
 --%>
<title>국가망</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/old/user_style.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/old/style1.css" type="text/css">
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/old/main_common.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/old/default.js"></script>

<script type="text/javascript">

$(document).ready(function(){ 
	//페이지 최초 진입시 기본상품은 선택되어져있고, 선택된 상품에 따른 속도값 셋팅
	speed();

	//상품 변경시
	$("#svc_type").change(function(){
		speed();
		empty();
		});
	//하위국 변경시
	$("#subStationCombo").change(function(){
		distance();
		});
	
	
	});
	// 초기 셋팅시 상품에 따른 속도값 셋팅
	function speed(){
	var svc_type = $("#svc_type").val();

	
	if(svc_type == "인터넷"){				//인터넷일때 표시화면
		$("#cctv_view").hide();
		$("#distance_view").hide();
		$("#speed_view").show();
	}else if(svc_type=="CCTV전송"){		//CCTV일때 표시화면
		$("#cctv_view").show();
		$("#distance_view").hide();
		$("#speed_view").hide();
	}else{										//나머지일때 표시화면
		$("#distance_view").show();
		$("#cctv_view").hide();
		$("#speed_view").show();
		
		}
		var param =  escape(encodeURIComponent(svc_type));
		
		if(svc_type == "CCTV전송"){
			if($("#cctvYN").val()=="N"){
				$.ajax({
				    url : "<%=request.getContextPath()%>/charge/cctvsetting.do",
				    data : "svc_type="+param,
				    dataType :"json",
				    success:function(data){		//return  JSON 형태의 텍스트로 리턴.
					    
					    //요금구분메뉴 셋팅
				    	var temp = "<option value=\"\">선택하세요</option>";			
						$.each(data.feeArray, function(){
						temp += "<option value=\""+this.distance_cd+"\">"+this.fee_basis+"</option>";
			
						});
						$("#distance_cd").html(temp);	 
						//거리기준메뉴 셋팅
						var temp = "<option value=\"\">선택하세요</option>";			
						$.each(data.distanceArray, function(){
						temp += "<option value=\""+this.distance+"\">"+this.distance+"</option>";
			
						});
						$("#distance").html(temp);	 
						//약정기간메뉴 셋팅
						var temp = "<option value=\"\">선택하세요</option>";			
						$.each(data.contractArray, function(){
						temp += "<option value=\""+this.dist_code+"\">"+this.contract_period+"</option>";
			
						});
						$("#contract_period").html(temp);	 
						//계약구분메뉴 셋팅
						var temp = "<option value=\"\">선택하세요</option>";			
						$.each(data.speedArray, function(){
						temp += "<option value=\""+this.speed+"\">"+this.speed+"</option>";
			
						});
						$("#cctv_speed").html(temp);	 
						$("#cctvYN").val("Y");
				    },
				    error:function(request,status,error){
				      alert(request + " : " + status + " : " + error);
				    }
				  });
				}
		}else{
			
			$.ajax({
			    url : "<%=request.getContextPath()%>/charge/speed.do",
			    data : "svc_type="+param,
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
	}

	// 국사 검색, 상위국 하위국 같이사용함
	function stationSearch(obj){
		var param;
		if(obj == "T"){		//상위국 검색버튼 클릭시.
			if($("#topStationText").val() == ""){
				alert("상위국명을 입력하세요.");
				return;
			}
			param =  escape(encodeURIComponent($("#topStationText").val()));
		}else if(obj == "S"){		//하위국 검색버튼 클릭시
			if($("#subStationText").val() == ""){
				alert("하위국명을 입력하세요.");
				return;
			}

			if($("#topStationCombo").val() == null || $("#topStationCombo").val() == ""){
				alert("상위국을 먼저 선택하세요.");
				return;
			}
			param =  escape(encodeURIComponent($("#subStationText").val()));
		}
		
		$.ajax({
		    url : "<%=request.getContextPath()%>/charge/stationSearch.do",
		    data : "addr="+param,
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
	//거리계산 , 하위국 콤보박스로 주소선택시 이벤트 발생
	function distance(){
		if($("#topStationCombo").val()==null ||$("#topStationCombo").val()==""		//하위국 선택시 상위국 선택유무확인
				){
			alert("상위국 하위국을 선택하세요");
			return;
			}
		if($("#subStationCombo").val()=="") return;

		$.ajax({
		    url : "<%=request.getContextPath()%>/charge/distanceSearch.do",
		    data : "uco="+$("#topStationCombo").val() + "&lco="+$("#subStationCombo").val(),
		    dataType :"json",
		    success:function(data){
			    
		    	$("#displayDistance").html(data.dist_value + " Km");
		    	$("#dist_code").val(data.dist_code);
		    },
		    error:function(request,status,error){
		      alert(request + " : " + status + " : " + error);
		    }
		  });

		}
	//계산 버튼 
	function calcul(){
		if($("#svc_type").val() == "인터넷"){ //인터넷일 경우  기본 유효성검사
			if($("#speed").val() == ""){
				alert("속도를 선택하세요");
				return;
				}
			}else if($("#svc_type").val() == "CCTV전송"){	//cctv일 경우  기본 유효성검사
				if($("#distance_cd").val() == ""){
					alert("요금구분을 선택하세요");
					return;
					}
				if($("#distance").val() == ""){
					alert("거리기준을 선택하세요");
					return;
				}
				if($("#contract_period").val() == ""){
					alert("약정기간을 선택하세요");
					return;
				}
				if($("#cctv_speed").val() == ""){
					alert("계약구분을 선택하세요");
					return;
				}
			}else{		//나머지 기본 유효성 검사
				if($("#topStationCombo").val()==null ||$("#topStationCombo").val()==""||
						 $("#subStationCombo").val()==null || $("#subStationCombo").val()==""){
					alert("상위국과 하위국을 선택하세요.");
					return;
					}
				if($("#speed").val() == ""){
					alert("속도를 선택하세요");
					return;
					}
			}
		var svc_type = escape(encodeURIComponent($("#svc_type").val()));			//한글데이터 변환
		var distance = escape(encodeURIComponent($("#distance").val()));			//한글데이터 변환
		var cctv_speed = escape(encodeURIComponent($("#cctv_speed").val()));	//한글데이터 변환
		$.ajax({
		    url : "<%=request.getContextPath()%>/charge/calculation.do",
		    data : "dist_code="+$("#dist_code").val() + "&svc_type="+svc_type+"&speed="+$("#speed").val()
		    			+"&distance_cd="+$("#distance_cd").val()+"&distance="+distance+"&contract_period="
		    			+$("#contract_period").val()+"&cctv_speed="+cctv_speed,		//get 방식이라 URL이 길어질 순 있으나 2천자를 넘기진 않을거라 판단
		    dataType :"json",
		    success:function(data){
		    	$("#fee").html(data.fee);
		    },
		    error:function(request,status,error){
		      alert(request + " : " + status + " : " + error);
		    }
		  });

		
		}

	// 상품벼경시 초기화 펑션
	function empty(){
		
		$("#topStationText").val("");
		$("#topStationCombo").empty();
		$("#subStationText").val("");
		$("#subStationCombo").empty();
		$("#fee").html("");
		$("#displayDistance").html("");
		$("#dist_code").val("");
		$("#distance_cd").val("");
		$("#distance").val("");
		$("#contract_period").val("");
		$("#cctv_speed").val("");
		
		}
</script>
</head>
<body>
<!-- cctvYN은 페이지 접근시 1번 DB조회 후 중복조회를 막기위함. -->
<input type="hidden" name="cctvYN" id="cctvYN" value="N">
	요금표 메인화면.
	
	<br><br>
		상품 : 
			<select id="svc_type" name="svc_type">
				<c:forEach items="${goodsList }" var="list">
					<option value="${list.svc_type }">${list.svc_type }</option>
				</c:forEach>
				
			</select>
	<br>
	<div id="distance_view">
		<b><label>거리조회</label></b><br/>
			<label for="topStationText">상위국 :</label> <input type="text" id="topStationText" name="topStationText" title="상위국 :"><input type="button" onclick="stationSearch('T')" value="검색"><br>
				<select id = "topStationCombo" name="topStationCombo" title="상위국 ">
					
				</select> 
				<br><br>
			<label for="subStationText">하위국 :</label> <input type="text" id="subStationText" name="subStationText"  title="하위국 :"><input type="button" onclick="stationSearch('S')" value="검색"><br>
				<select id = "subStationCombo" name="subStationCombo" title="하위국 ">
					
				</select>
				<br>
				거리 : <span id="displayDistance"></span>&nbsp;	&nbsp;<input type="hidden" name="dist_code" id="dist_code" > 
	</div>
	<div id="cctv_view">
		<label for="distance_cd">요금구분 :</label> <select id="distance_cd" name="distance_cd" title="요금구분 ">
					</select>
					<br>
		<label for="distance">거리기준 :</label> <select id="distance" name="distance"  title="거리기준 ">
					</select>
					<br>
		<label for="contract_period">약정기간 :</label> <select id="contract_period" name="contract_period"  title="약정기간 ">
					</select>
					<br>
		<label for="cctv_speed">계약구분 :</label> <select id="cctv_speed" name="cctv_speed"  title="계약구분 ">	
				</select>

						&nbsp;	&nbsp;<input type="button" value="계산" onclick = "calcul();">
				<br><br>
	</div>
	<div id="speed_view">
		<label for="speed">속도 :</label><select id="speed" name="speed" title="속도 ">	
			</select>
				&nbsp;	&nbsp;<input type="button" value="계산" onclick = "calcul();">
			<br><br>
	</div>
		요금 : <span id="fee"></span>
		
				
		
	
</body>
</html>