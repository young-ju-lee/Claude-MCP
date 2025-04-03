
<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : traffic1.jsp
* Overview    : 회선조회 화면 
* Description : 회선조회 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<jsp:include page="../common/header.jsp" />

<div class="page-contents">
	<div class="container">
		<div class="page-title">
			<div>
				<h2>트래픽조회 서비스</h2>
				<p>
					kt그룹 국가정보통신서비스의 <span class="kt-red-text">트래픽조회 서비스</span>를 제공합니다.
				</p>
			</div>
			<ul>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img
						src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif"
						alt="홈페이지 메인"></a></li>
				<li>트래픽 조회</li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/traffic/traffic1.do');">회선조회</a></li>
			</ul>
		</div>

		<div class="page-tab">
			<div class="current-label">
				<a href="javascript:">트래픽조회 서비스</a>
			</div>
			<ul>
				<li class="active"><a href="javascript:">회선조회</a></li>
				<li><a href="javascript:" onclick="javascript:goTraffic2();">트래픽 조회</a></li>
				<li><a href="javascript:" onclick="javascript:goTraffic3();">그래프</a></li>
			</ul>
		</div>
		<form id="trafficform" name="trafficform" method="post">
			<input type="hidden" value="${researchflag}" id="researchflag"
				name="researchflag" /> <input type="hidden"
				value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
				name="request_token" /> <input type="hidden" value=""
				name="request_url" />
			<input type="hidden" value="${ord_flag}" id="ord_flag" name="ord_flag" />
			<div class="row">
				<div class="col s12">
					<h3>회선번호를 클릭,선택 후 트래픽조회, 그래프 탭을 클릭 시 해당 회선번호의 트래픽 정보를 확인 하실 수
						있습니다.</h3>
					<%
				 	if (request.getSession().getAttribute("user_auth_id").equals("20")){ 
    				%>

					<label for="search_llnum" class="kt-label">회선번호</label>
					<div class="select-wrap">
						<select id="search_llnum" name="search_llnum" title="회선번호"></select>
					</div> &nbsp;&nbsp;&nbsp;
					<!-- 2018.12.21 전체관리자 권한 인 경우 회선번호 ID변경 및 쓰기 가능하도록 수정 -->
					<%} else if (request.getSession().getAttribute("user_auth_id").equals("40")) {%>
					<label for="search_nat" class="kt-label mr_5">노드</label>
					<div class="select-wrap">
						<select id="search_nat" name="search_nat" title="노드">
						</select>
					</div>
					&nbsp;&nbsp;&nbsp; <label for="search_llnum_admin" class="kt-label">회선번호</label>
					
					<input class="kt-input" name="search_llnum_admin" 
						id="search_llnum_admin" title="회선번호" type="text" value=""  />
					&nbsp;&nbsp;&nbsp; <a href="javascript:" onclick="goSearch2();" class="btn">검색</a>
					
					<%} else {%>
					<label for="search_nat" class="kt-label">노드</label>
					<div class="select-wrap">
						<select id="search_nat" name="search_nat" title="노드">
						</select>
					</div>
					&nbsp;&nbsp;&nbsp; <label for="search_llnum" class="kt-label">회선번호</label>
					<input class="kt-input right-m" name="search_llnum" 
						id="search_llnum" title="회선번호" type="text" value="${search_llnum}" readonly />
					&nbsp;&nbsp;&nbsp; <a href="javascript:" onclick="goSearch();" class="btn">검색</a>
					<%} %>
				</div>
			</div>
			<div class="row">
				<div class="col s12">
					<div class="header-fixed-wrapper">
						<table class="header-fixed">
							<thead>
								<tr>
									<th class="center" style="width: 30%;">고객명</th>
									<th class="center" style="width: 30%;">국가기관명</th>
									<th class="center" style="width: 12%;">회선번호</th>
									<th class="center" style="width: 12%;">통신속도</th>
								</tr>
							</thead>
						</table>
						<div class="body-wrapper">
							<table class="body-scroll" id="llum_table">
								<tbody>
									<c:choose>
									   <c:when test="${sessionScope.user_auth_id != 20}">
											<c:forEach var="list" items="${traffic_list}">
												<tr onclick="rowClick('${list.llnum}');" style="cursor: pointer;" id="${list.llnum}">
													<td class="center" style="width: 30%;">${list.cust_name}</td>
													<td class="center" style="width: 30%;">${list.nat_name}</td>
													<td class="center" style="width: 12%;"><a href="javascript:" onclick="javascript:selectllnum( '${list.llnum} ');">${list.llnum}</a></td>
													<td class="center" style="width: 12%;">${list.line_spec_cd}</td>
												</tr>
										    </c:forEach>
										</c:when>
										<c:otherwise>
									<c:choose>
										<c:when test="${empty traffic_list}">
										<!-- 2018.12.21 전체관리자 권한으로 리스트가 없는 경우 공란으로 표시 -->
										<% if (request.getSession().getAttribute("user_auth_id").equals("40")){%>
										  
										<%}else{ %>
										<!-- 2018.12.21 기존 해지된 회선입니다 text 삭제처리
											<tr>
												<td class="center" colspan="5">해지된 회선입니다.</td>
											</tr>
										 -->	
											<%} %>
										</c:when>										
										<c:otherwise>
											<c:forEach var="list" items="${traffic_list}">
												<tr onclick="rowClick('${list.llnum}');" style="cursor: pointer;" id="${list.llnum}">
													<td class="center" style="width: 30%;">${list.cust_name}</td>
													<td class="center" style="width: 30%;">${list.nat_name}</td>
													<td class="center" style="width: 12%;"><a href="javascript:" onclick="javascript:selectllnum( '${list.llnum} ');">${list.llnum}</a></td>
													<td class="center" style="width: 12%;">${list.line_spec_cd}</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<jsp:include page="../common/footer.jsp" />

<script>

var prevObj = null;
var flag = '${sessionScope.user_auth_id}';
var ajaxreturn = true;

function rowClick(llnum){
     //2018.12.21 전체관리자 권한 인 경우에 클릭 했을 때 회선 번호 값을 search_llnum_admin 아니면 llnum으로 value값 저장
	if(flag==40){
		$("#search_llnum_admin").val( llnum );
		}else{
			$("#search_llnum").val( llnum );
			}	

	if(prevObj != null){
		prevObj.children("td").css("backgroundColor", "#fff");
	}
	
	 $("#"+llnum).children("td").css("backgroundColor", "#fdeaeb");

	prevObj = $("#"+llnum);	
}

function goSearch(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	if($("#search_llnum").val() != "" && flag == 20) {
		checkllnum();
		if(!ajaxreturn) {
			return;
		}
	}
	var frm = document.trafficform;
	frm.researchflag.value = "Y";
	frm.action = "<%=request.getContextPath()%>/traffic/traffic1.do";
    frm.submit();
}

function goSearch2(){	//2018.12.21 전체관리자로 검색 시에 함수 추가 -traffic4로 이동
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  		  var check1 = $("#search_llnum_admin").val();
	 	 	var check2 = $("#search_nat").val();
	 	     
	 	 	if(check2!="all"){
	 	 		if(check1==""){
	 	 			goSearch();			 
	 	 			}else{
	 	 				checkllnum2();
	 	 				if(!ajaxreturn) {
	 	 					$('#search_llnum_admin').val('');
	 	 					$('#search_llnum_admin').focus();
	 	 					return;
	 	 					
	 	 		  }
	 	 				var frm = document.trafficform;
	 	 				frm.researchflag.value = "Y";
	 	 				frm.search_llnum_admin.value = check1;
	 	 				frm.action = "<%=request.getContextPath()%>/traffic/traffic4.do";
	 	 			    frm.submit();
	 	 				}
	 	 		
	 	 	}else{
	 	 	
	 	 	if(check1==""){
	 	 		alert("회선번호를 입력 해 주세요");
	 	 		$('#search_llnum_admin').focus();
	 	 		return;
	 	 		 
	 	 		}else{
	 	 			checkllnum2();
	 	 			if(!ajaxreturn) {
	 	 				$('#search_llnum_admin').val('');
	 	 				$('#search_llnum_admin').focus();
	 	 				return;
	 	 				
	 	 	  }
	 	 }
	 	 			var frm = document.trafficform;
	 	 			frm.researchflag.value = "Y";
	 	 			frm.search_llnum_admin.value = check1;
	 	 			frm.action = "<%=request.getContextPath()%>/traffic/traffic4.do";
	 	 		    frm.submit();
	 	 			}
  		}else{
  			actionUrl = "<%=request.getContextPath()%>/member/session_check.do";
  	 		
  	 		
  			 $.ajax({
  				    url : actionUrl,		    
  					type : "POST",
  					data : "user_id="+id
  			    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
  					+ "&request_url=" + location.href,
  					dataType :"json",
  			 	    success:function(data){
  			 	 	    if(data.result == "session_null") { 
  			 	 	  
  			 	 	   			 	 	    
  			 	 	 alert("session이 종료되었습니다");
  		  	 	      actionUrl = "<%=request.getContextPath()%>/member/logout2.do"; 
  		  	 	    var frm = document.sessionForm;
  		  	 	  	frm.action=actionUrl;
  		  	 	  	frm.submit();  		  	 	  
  		  	 	
  			 	 	    }else {    

  			 	 	    var check1 = $("#search_llnum_admin").val();
  			 	 	var check2 = $("#search_nat").val();
  			 	     
  			 	 	if(check2!="all"){
  			 	 		if(check1==""){
  			 	 			goSearch();			 
  			 	 			}else{
  			 	 				checkllnum2();
  			 	 				if(!ajaxreturn) {
  			 	 					$('#search_llnum_admin').val('');
  			 	 					$('#search_llnum_admin').focus();
  			 	 					return;
  			 	 					
  			 	 		  }
  			 	 				var frm = document.trafficform;
  			 	 				frm.researchflag.value = "Y";
  			 	 				frm.search_llnum_admin.value = check1;
  			 	 				frm.action = "<%=request.getContextPath()%>/traffic/traffic4.do";
  			 	 			    frm.submit();
  			 	 				}
  			 	 		
  			 	 	}else{
  			 	 	
  			 	 	if(check1==""){
  			 	 		alert("회선번호를 입력 해 주세요");
  			 	 		$('#search_llnum_admin').focus();
  			 	 		return;
  			 	 		 
  			 	 		}else{
  			 	 			checkllnum2();
  			 	 			if(!ajaxreturn) {
  			 	 				$('#search_llnum_admin').val('');
  			 	 				$('#search_llnum_admin').focus();
  			 	 				return;
  			 	 				
  			 	 	  }
  			 	 }
  			 	 			var frm = document.trafficform;
  			 	 			frm.researchflag.value = "Y";
  			 	 			frm.search_llnum_admin.value = check1;
  			 	 			frm.action = "<%=request.getContextPath()%>/traffic/traffic4.do";
  			 	 		    frm.submit();
  			 	 			}
  			}
  	},
  				     error:function(request,status,error){
  				    	 alert(request + " : " + status + " : " + error);
  				    	var sessioncheck = false;
							return sessioncheck ;
  				     }
  				  });
  			
  			}
		
	

	//if($("#search_llnum").val() != "" && flag == 20) {
	//	checkllnum();
	//	if(!ajaxreturn) {
	//		return;
	//	}
	//}
	
}


function goTraffic2(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	if(flag!=40){ //2018.12.21 유저권한이 전체관리자 아닌경우 분기처리
		if($("#search_llnum").val() != "" && flag == 20) {
			checkllnum();
			if(!ajaxreturn) {
				return;
			}
		}
		
		var frm = document.trafficform;
		frm.researchflag.value = "Y";
		frm.action = "<%=request.getContextPath()%>/traffic/traffic2.do";	
	    frm.submit();
	}else{ //2018.12.21 유저권한이 전체관리자인 경우 checkllnum2,traffic5를 사용하도록 수정
		
			checkllnum2();
			if(!ajaxreturn) {
				return;
			}	
		
		var frm = document.trafficform;
		frm.researchflag.value = "Y";
		frm.action = "<%=request.getContextPath()%>/traffic/traffic5.do";	
	    frm.submit();

		}

}
function goTraffic3(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	if(flag!=40){ //2018.12.21 유저권한이 전체관리자가 아닌경우 분기처리
	if($("#search_llnum").val() != "" && flag == 20) {
		checkllnum();
		if(!ajaxreturn) {
			return;
		}
	}
	
	var frm = document.trafficform;
	frm.researchflag.value = "Y";
	frm.action = "<%=request.getContextPath()%>/traffic/traffic3.do";
    frm.submit();
	}else{ //2018.12.21 유저권한이 전체관리자인 경우 checkllnum2,traffic6를 사용하도록 수정
		checkllnum2();
		if(!ajaxreturn) {
			return;
		}
		var frm = document.trafficform;
		frm.researchflag.value = "Y";
		frm.action = "<%=request.getContextPath()%>/traffic/traffic6.do";
	    frm.submit();
		}
}

$(document).ready(function(){

	<%if (request.getSession().getAttribute("user_auth_id").equals("20")){%>
		selectAllLLnum();
	<%}else{%>
		selectNesCode();
	<%}%>
	
	$("#search_nat").change(function(){
		if(flag==40){ //2018.12.21 노드 값 수정 시에 전체관리자와 아닌경우 회선번호 값 수정
			$("#search_llnum_admin").val( "" );
			}else{
				$("#search_llnum").val( "" );
				}		
	});

	$("#search_llnum").change(function(){
		rowClick($("#search_llnum option:selected").val());
	});	

	
});

//전체 회선번호 조회
function selectNesCode(){
	
			
	var defaultNode = '${search_nat}'.replace(' ', '');
	
	$.ajax({
    	url : "<%=request.getContextPath()%>/traffic/select_node.do",
    	data : "request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
  			+ "&request_url=" + location.href,
    	dataType :"json",
		success:function(data){ 
			if(data.nodes.length == 0){ 
				return;
			}

    		var temp ='';	
    		if(flag==40){ //2018.12.21 전체관리자 권한 인 경우에 전체회선 조회 추가
				temp +="<option value=\"all\">전체회선 조회</option> ";
				}
			$.each(data.nodes, function(){
				if( this.node == defaultNode){
					temp += "<option value=\""+this.node+"\" selected>"+this.alias+"</option>";
				}
				else{
					temp += "<option value=\""+this.node+"\">"+this.alias+"</option>";
				}			
			});
			$("#search_nat").html(temp);
				 
    	},
    	error:function(request,status,error){
      	alert(request + " : " + status + " : " + error);
    	}
  	});
};

//회선번호 선택시 전용회선이 조회조건으로 
function selectllnum(llnum){

			
	if(flag==40){//2018.12.21 전체관리자인 경우에 search_llnum_admin 값으로 바뀌도록 수정
		$("#search_llnum_admin").val( llnum );
		}else{
			$("#search_llnum").val( llnum );
			}
	
}


//전체 회선번호 조회
function selectAllLLnum(){
	
			
	var defaultLLnum = '${search_llnum}'.replace(' ', '');
	
	$.ajax({
    	url : "<%=request.getContextPath()%>/traffic/select_llnum.do",
    	data : "request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
	  		+ "&request_url=" + location.href,
	  	type : "POST",
    	dataType :"json",
		success:function(data){ 
			if(data.llnum.length == 0){
				return;
			}
    		var temp = '';		
			$.each(data.llnum, function(){
				if( this.llnum == defaultLLnum){
					temp += "<option value=\""+this.llnum+"\" selected>"+this.llnum+"</option>";
				}
				else{
					temp += "<option value=\""+this.llnum+"\">"+this.llnum+"</option>";
				}			
			});
			$("#search_llnum").html(temp);

			rowClick($("#search_llnum option:selected").val());	 
    	},
    	error:function(request,status,error){
      	alert(request + " : " + status + " : " + error);
    	}
  	});
}

function checkllnum() {
				
	$.ajax({
    	url : "<%=request.getContextPath()%>/traffic/check_llnum.do",
    	data : "request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
  			+ "&request_url=" + location.href
  			+ "&search_llnum=" + $("#search_llnum").val(),
    	dataType :"json",
    	async: false,
		success:function(data){ 
			if(data.result == "1"){
				ajaxreturn = false;
				alert("해당 회선은 해지된 회선입니다.\n다른회선을 선택 후 조회하시기 바랍니다.");
			} else {
				ajaxreturn = true;
			}
			
    	},
    	error:function(request,status,error){
    		ajaxreturn = false;
      		alert(request + " : " + status + " : " + error);
      	
    	}
  	});
  	return ajaxreturn;
}

function checkllnum2() { //2018.12.21 전체관리자인 경우에 회선번호 체크함수 추가
	
			
	$.ajax({
    	url : "<%=request.getContextPath()%>/traffic/check_llnum.do",
    	data : "request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
  			+ "&request_url=" + location.href
  			+ "&search_llnum=" + $("#search_llnum_admin").val(),
    	dataType :"json",
    	async: false,
		success:function(data){ 
			if(data.result == "1"){
				ajaxreturn = false;
				alert("해당 회선은 존재하지 않는 회선입니다.");
			} else {
				ajaxreturn = true;
			}
			
    	},
    	error:function(request,status,error){
    		ajaxreturn = false;
      		alert(request + " : " + status + " : " + error);
      	
    	}
  	});
  	return ajaxreturn;
}
</script>
</html>