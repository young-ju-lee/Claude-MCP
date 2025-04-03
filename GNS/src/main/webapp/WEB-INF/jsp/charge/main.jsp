<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : main.jsp
* Overview    : �̿��ݰ�� �׽�Ʈ ȭ�� 
* Description : �̿��ݰ�� �׽�Ʈ ȭ��
* *=====================================================================
* Version    ����  ����       ������               ����
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       ���ƺ�            �ű԰���
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
<title>������</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/old/user_style.css" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/old/style1.css" type="text/css">
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/old/main_common.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/old/default.js"></script>

<script type="text/javascript">

$(document).ready(function(){ 
	//������ ���� ���Խ� �⺻��ǰ�� ���õǾ����ְ�, ���õ� ��ǰ�� ���� �ӵ��� ����
	speed();

	//��ǰ �����
	$("#svc_type").change(function(){
		speed();
		empty();
		});
	//������ �����
	$("#subStationCombo").change(function(){
		distance();
		});
	
	
	});
	// �ʱ� ���ý� ��ǰ�� ���� �ӵ��� ����
	function speed(){
	var svc_type = $("#svc_type").val();

	
	if(svc_type == "���ͳ�"){				//���ͳ��϶� ǥ��ȭ��
		$("#cctv_view").hide();
		$("#distance_view").hide();
		$("#speed_view").show();
	}else if(svc_type=="CCTV����"){		//CCTV�϶� ǥ��ȭ��
		$("#cctv_view").show();
		$("#distance_view").hide();
		$("#speed_view").hide();
	}else{										//�������϶� ǥ��ȭ��
		$("#distance_view").show();
		$("#cctv_view").hide();
		$("#speed_view").show();
		
		}
		var param =  escape(encodeURIComponent(svc_type));
		
		if(svc_type == "CCTV����"){
			if($("#cctvYN").val()=="N"){
				$.ajax({
				    url : "<%=request.getContextPath()%>/charge/cctvsetting.do",
				    data : "svc_type="+param,
				    dataType :"json",
				    success:function(data){		//return  JSON ������ �ؽ�Ʈ�� ����.
					    
					    //��ݱ��и޴� ����
				    	var temp = "<option value=\"\">�����ϼ���</option>";			
						$.each(data.feeArray, function(){
						temp += "<option value=\""+this.distance_cd+"\">"+this.fee_basis+"</option>";
			
						});
						$("#distance_cd").html(temp);	 
						//�Ÿ����ظ޴� ����
						var temp = "<option value=\"\">�����ϼ���</option>";			
						$.each(data.distanceArray, function(){
						temp += "<option value=\""+this.distance+"\">"+this.distance+"</option>";
			
						});
						$("#distance").html(temp);	 
						//�����Ⱓ�޴� ����
						var temp = "<option value=\"\">�����ϼ���</option>";			
						$.each(data.contractArray, function(){
						temp += "<option value=\""+this.dist_code+"\">"+this.contract_period+"</option>";
			
						});
						$("#contract_period").html(temp);	 
						//��౸�и޴� ����
						var temp = "<option value=\"\">�����ϼ���</option>";			
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
			    	var temp = "<option value=\"\">�����ϼ���</option>";			
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

	// ���� �˻�, ������ ������ ���̻����
	function stationSearch(obj){
		var param;
		if(obj == "T"){		//������ �˻���ư Ŭ����.
			if($("#topStationText").val() == ""){
				alert("���������� �Է��ϼ���.");
				return;
			}
			param =  escape(encodeURIComponent($("#topStationText").val()));
		}else if(obj == "S"){		//������ �˻���ư Ŭ����
			if($("#subStationText").val() == ""){
				alert("���������� �Է��ϼ���.");
				return;
			}

			if($("#topStationCombo").val() == null || $("#topStationCombo").val() == ""){
				alert("�������� ���� �����ϼ���.");
				return;
			}
			param =  escape(encodeURIComponent($("#subStationText").val()));
		}
		
		$.ajax({
		    url : "<%=request.getContextPath()%>/charge/stationSearch.do",
		    data : "addr="+param,
		    dataType :"json",
		    success:function(data){
		    	var temp = "<option value=\"\">�����ϼ���</option>";			
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
	//�Ÿ���� , ������ �޺��ڽ��� �ּҼ��ý� �̺�Ʈ �߻�
	function distance(){
		if($("#topStationCombo").val()==null ||$("#topStationCombo").val()==""		//������ ���ý� ������ ��������Ȯ��
				){
			alert("������ �������� �����ϼ���");
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
	//��� ��ư 
	function calcul(){
		if($("#svc_type").val() == "���ͳ�"){ //���ͳ��� ���  �⺻ ��ȿ���˻�
			if($("#speed").val() == ""){
				alert("�ӵ��� �����ϼ���");
				return;
				}
			}else if($("#svc_type").val() == "CCTV����"){	//cctv�� ���  �⺻ ��ȿ���˻�
				if($("#distance_cd").val() == ""){
					alert("��ݱ����� �����ϼ���");
					return;
					}
				if($("#distance").val() == ""){
					alert("�Ÿ������� �����ϼ���");
					return;
				}
				if($("#contract_period").val() == ""){
					alert("�����Ⱓ�� �����ϼ���");
					return;
				}
				if($("#cctv_speed").val() == ""){
					alert("��౸���� �����ϼ���");
					return;
				}
			}else{		//������ �⺻ ��ȿ�� �˻�
				if($("#topStationCombo").val()==null ||$("#topStationCombo").val()==""||
						 $("#subStationCombo").val()==null || $("#subStationCombo").val()==""){
					alert("�������� �������� �����ϼ���.");
					return;
					}
				if($("#speed").val() == ""){
					alert("�ӵ��� �����ϼ���");
					return;
					}
			}
		var svc_type = escape(encodeURIComponent($("#svc_type").val()));			//�ѱ۵����� ��ȯ
		var distance = escape(encodeURIComponent($("#distance").val()));			//�ѱ۵����� ��ȯ
		var cctv_speed = escape(encodeURIComponent($("#cctv_speed").val()));	//�ѱ۵����� ��ȯ
		$.ajax({
		    url : "<%=request.getContextPath()%>/charge/calculation.do",
		    data : "dist_code="+$("#dist_code").val() + "&svc_type="+svc_type+"&speed="+$("#speed").val()
		    			+"&distance_cd="+$("#distance_cd").val()+"&distance="+distance+"&contract_period="
		    			+$("#contract_period").val()+"&cctv_speed="+cctv_speed,		//get ����̶� URL�� ����� �� ������ 2õ�ڸ� �ѱ��� �����Ŷ� �Ǵ�
		    dataType :"json",
		    success:function(data){
		    	$("#fee").html(data.fee);
		    },
		    error:function(request,status,error){
		      alert(request + " : " + status + " : " + error);
		    }
		  });

		
		}

	// ��ǰ����� �ʱ�ȭ ���
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
<!-- cctvYN�� ������ ���ٽ� 1�� DB��ȸ �� �ߺ���ȸ�� ��������. -->
<input type="hidden" name="cctvYN" id="cctvYN" value="N">
	���ǥ ����ȭ��.
	
	<br><br>
		��ǰ : 
			<select id="svc_type" name="svc_type">
				<c:forEach items="${goodsList }" var="list">
					<option value="${list.svc_type }">${list.svc_type }</option>
				</c:forEach>
				
			</select>
	<br>
	<div id="distance_view">
		<b><label>�Ÿ���ȸ</label></b><br/>
			<label for="topStationText">������ :</label> <input type="text" id="topStationText" name="topStationText" title="������ :"><input type="button" onclick="stationSearch('T')" value="�˻�"><br>
				<select id = "topStationCombo" name="topStationCombo" title="������ ">
					
				</select> 
				<br><br>
			<label for="subStationText">������ :</label> <input type="text" id="subStationText" name="subStationText"  title="������ :"><input type="button" onclick="stationSearch('S')" value="�˻�"><br>
				<select id = "subStationCombo" name="subStationCombo" title="������ ">
					
				</select>
				<br>
				�Ÿ� : <span id="displayDistance"></span>&nbsp;	&nbsp;<input type="hidden" name="dist_code" id="dist_code" > 
	</div>
	<div id="cctv_view">
		<label for="distance_cd">��ݱ��� :</label> <select id="distance_cd" name="distance_cd" title="��ݱ��� ">
					</select>
					<br>
		<label for="distance">�Ÿ����� :</label> <select id="distance" name="distance"  title="�Ÿ����� ">
					</select>
					<br>
		<label for="contract_period">�����Ⱓ :</label> <select id="contract_period" name="contract_period"  title="�����Ⱓ ">
					</select>
					<br>
		<label for="cctv_speed">��౸�� :</label> <select id="cctv_speed" name="cctv_speed"  title="��౸�� ">	
				</select>

						&nbsp;	&nbsp;<input type="button" value="���" onclick = "calcul();">
				<br><br>
	</div>
	<div id="speed_view">
		<label for="speed">�ӵ� :</label><select id="speed" name="speed" title="�ӵ� ">	
			</select>
				&nbsp;	&nbsp;<input type="button" value="���" onclick = "calcul();">
			<br><br>
	</div>
		��� : <span id="fee"></span>
		
				
		
	
</body>
</html>