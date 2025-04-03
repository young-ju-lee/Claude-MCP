
<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : traffic2.jsp
* Overview    : 트래픽조회 화면 
* Description : 트래픽조회 화면
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
				<li>트래픽조회 서비스</li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/traffic/traffic2.do');">트래픽조회</a></li>
			</ul>
		</div>

		<div class="page-tab">
			<div class="current-label">
				<a href="javascript:">트래픽조회 서비스</a>
			</div>
			<ul>
				<li><a href="javascript:" onclick="javascript:goTraffic1();">회선조회</a></li>
				<li class="active"><a href="javascript:">트래픽 조회</a></li>
				<li><a href="javascript:" onclick="javascript:goTraffic3();">그래프</a></li>
			</ul>
		</div>

		<form name="traffic" action="" method="post">
			<input type="hidden" value="${researchflag}" id="researchflag"
				name="researchflag" /> <input type="hidden"
				value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
				name="request_token" /> <input type="hidden" value=""
				name="request_url" /> <input type="hidden" value="${search_nat}"
				id="search_nat" name="search_nat" />
				
			<!-- 프로그레스 바 -->
			<jsp:include page="../common/progressbar.jsp" />
			
			<div class="row">
				<div class="col s12">
					<h3>회선번호로 실시간, 시간별, 일별, 월별 트래픽정보를 확인하실 수 있습니다. (단, 조회기간이 한달
						이상일 경우 조회시간이 느려질 수 있습니다.)</h3>
					<%
							 if ( request.getSession().getAttribute("user_auth_id").equals("30") || request.getSession().getAttribute("user_auth_id").equals("40")){ 
			    			%>
					<label for="search_llnum" class="kt-label">회선번호</label> <input
						id="search_llnum" name="search_llnum" class="kt-input right-m"
						type="text" title="회선번호" value="${search_llnum}" />
					<%} else { %>
					<label for="search_llnum" class="kt-label">회선번호</label>
					<div class="select-wrap">
						<select id="search_llnum" name="search_llnum" title="회선번호"></select>
					</div>
					<%} %>

					<input class="kt-input" type="radio" id="traffic_type-r"
						name="traffic_type" value="R" title="실시간"
						<c:if test="${traffic_type=='R'}">checked</c:if> /> <label
						for="traffic_type-r" class="kt-label right-m">실시간</label> <input
						class="kt-input" type="radio" id="traffic_type-h"
						name="traffic_type" value="H" title="시간별"
						<c:if test="${traffic_type=='H'}">checked</c:if> /> <label
						for="traffic_type-h" class="kt-label right-m">시간별</label> <input
						class="kt-input" type="radio" id="traffic_type-d"
						name="traffic_type" value="D" title="일별"
						<c:if test="${traffic_type=='D'}">checked</c:if> /> <label
						for="traffic_type-d" class="kt-label right-m">일별</label> <input
						class="kt-input" type="radio" id="traffic_type-m"
						name="traffic_type" value="M" title="월별"
						<c:if test="${traffic_type=='M'}">checked</c:if> /> <label
						for="traffic_type-m" class="kt-label right-m">월별</label> <span
						class="vertical-div"></span> <label for="datepicker1"
						class="kt-label">일 자</label> <input name="" type="hidden"
						id="datepicker1" class="kt-input" value="${search_from}" readonly />
						<input name="search_from" type="text"
						id="date1" class="kt-input w_110px" value="${search_from}" readonly />
						
						<i
						id="date_icon" class="material-icons" style="cursor: pointer;">date_range</i>
					~ <input name="" type="hidden" id="datepicker2"
						class="kt-input" value="${search_to}" readonly />
						<input name="search_to" type="text"
						id="date2" class="kt-input w_110px" value="${search_to}" readonly />
						
						<i
						id="date_icon2" class="material-icons" style="cursor: pointer;">date_range</i>
					<a href="javascript:" onclick="javascript:check();"
						class="btn">조회</a> <a href="javascript:"
						onclick="javascript:goExcel();" class="btn teal darken-2"><i
						class="material-icons">file_download</i>엑셀</a>
				</div>
			</div>
			<div class="row">
				<div class="col s12">
					<div class="header-fixed-wrapper">
						<table class="header-fixed" id="traffic_thead">

						</table>
						<div class="body-wrapper" id="traffic_div">
							<table class="body-scroll" id="traffic_tbody">

							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>



<jsp:include page="../common/footer.jsp" />
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>

<script>
var flag = '${sessionScope.user_auth_id}';
var ajaxreturn = true;
	$(document).ready(function(){ 
		

		var monthArr = ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'];
		
	    $(function(){
			$("#datepicker1").datepicker({
		            changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
		            changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
		            minDate: '-10y', // 현재날짜로부터 100년이전까지 년을 표시한다.
		            nextText: 'next', // next 아이콘의 툴팁.
		             prevText: 'prev', // prev 아이콘의 툴팁.
		            numberOfMonths: [1,1], // 한번에 얼마나 많은 월을 표시할것인가. [2,3] 일 경우, 2(행) x 3(열) = 6개의 월을 표시한다.
		            stepMonths: 1, // next, prev 버튼을 클릭했을때 얼마나 많은 월을 이동하여 표시하는가. 
		             yearRange: 'c-2:c+2', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
		            showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
		            closeText: 'Close',  // 닫기 버튼 패널
		            showMonthAfterYear: true, // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다.
		            monthNamesShort: monthArr, // 월의 한글 형식.
		             dateFormat: 'yy-mm-dd',
		            onClose: function(dateText, inst) { 
		                   $(this).datepicker('setDate', dateText);
		                   $("#date1").val(dateText);
		            }
			});

			$("#date1").bind("click",function(){
				$("#ui-datepicker-div").css("margin-top","31px");
				$("#datepicker1").datepicker('show');
			});
			
			$("#date_icon").bind("click",function(){
				$("#ui-datepicker-div").css("margin-top","31px");
				$("#datepicker1").datepicker('show');
			});

			$("#datepicker2").datepicker({
					changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
		            changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
		            minDate: '-10y', // 현재날짜로부터 100년이전까지 년을 표시한다.
		            nextText: 'next', // next 아이콘의 툴팁.
		             prevText: 'prev', // prev 아이콘의 툴팁.
		            numberOfMonths: [1,1], // 한번에 얼마나 많은 월을 표시할것인가. [2,3] 일 경우, 2(행) x 3(열) = 6개의 월을 표시한다.
		            stepMonths: 1, // next, prev 버튼을 클릭했을때 얼마나 많은 월을 이동하여 표시하는가. 
		             yearRange: 'c-2:c+2', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
		            showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
		            closeText: 'Close',  // 닫기 버튼 패널
		            showMonthAfterYear: true, // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다.
		            monthNamesShort: monthArr, // 월의 한글 형식.
		             dateFormat: 'yy-mm-dd',
		            onClose: function(dateText, inst) { 
		                   $(this).datepicker('setDate', dateText);
		                   $("#date2").val(dateText);
		            }
			});

			$("#date2").bind("click",function(){
				$("#ui-datepicker-div").css("margin-top","31px");
				$("#datepicker2").datepicker('show');
			});
			$("#date_icon2").bind("click",function(){
				$("#ui-datepicker-div").css("margin-top","31px");
				$("#datepicker2").datepicker('show');
			});
			
		});


		<%if (request.getSession().getAttribute("user_auth_id").equals("20")){%>
			selectAllLLnum();
		<%}else{%>
	
			var llnum = "${search_llnum}";
	
			if(llnum != null && llnum != "") {
				trafficSearch();
			} 
		<%}%>


		
		
		/*
		//Paging
		$("input:radio[name=traffic_type]").change(function(){
			$("#page").val(1);
		});
		
		$("#traffic_div").scroll(function() {
			var page = parseInt($("#page").val());
			if($("#traffic_div").height()*page <= $("#traffic_div").scrollTop()  && last_data != 0 ) {
				$("#page").val(parseInt(page)+1);
				trafficSearch();
			}
		});
		 */
	});
	
	
	function goExcel(){
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		sessioncheck();
				
		if($("#search_llnum").val()=='' || $("#search_llnum").val()==null){
			alert('회선번호를 입력하세요.');
			return;
		}
		if($("#search_llnum").val() != "" && flag == 20) {
			checkllnum();
			if(!ajaxreturn) {
				return;
			}
		}
		if(!checkDate("엑셀 다운로드기간")) {
			return;
		}

		
		var frm = document.traffic;
		frm.researchflag.value = "Y";
		frm.action = "<%=request.getContextPath()%>/traffic/traffic_download.do";
	    frm.submit();
	}
	
	function goTraffic1(){
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		sessioncheck();
				
		if($("#search_llnum").val() != "" && flag == 20) {
			checkllnum();
			if(ajaxreturn == "false") {
				return;
			}
		}
		var frm = document.traffic;
		frm.researchflag.value = "Y";
		frm.action = "<%=request.getContextPath()%>/traffic/traffic1.do";
	    frm.submit();
	}
	
	
	
	function goTraffic3(){
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		sessioncheck();
				
		if($("#search_llnum").val() != "" && flag == 20) {
			checkllnum();
			if(!ajaxreturn) {
				return;
			}
		}
		var frm = document.traffic;
		frm.action = "<%=request.getContextPath()%>/traffic/traffic3.do";
	    frm.submit();
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

				trafficSearch();
					 
	    	},
	    	error:function(request,status,error){
	      	alert(request + " : " + status + " : " + error);
	    	}
	  	});
	}

	function NumberFormt(num) {
		return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

	function trafficSearch() {
		
				
		/* 
		//Paging
		var page = $("#page").val();
		 */	
		 if($("#search_llnum").val() != "" && flag == 20) {
				checkllnum();
				if(!ajaxreturn) {
					return;
				}
			}
		
		if(!checkDate("조회기간")) {
			return;
		}
		
		var radioValue = $("input:radio[name=traffic_type]:checked").val();
	    $.ajax({
		    url : "<%=request.getContextPath()%>/traffic/searchtraffic.do",
		  data : "search_from=" + $("#datepicker1").val() + "&search_to=" +  $("#datepicker2").val() 
		      + "&search_llnum=" + $("#search_llnum").val()
		  	  + "&traffic_type=" + radioValue
		  	  + "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
		  	  + "&request_url=" + location.href,
		  	type : "POST",
			dataType :"json",
		    success:function(data){
				var head = "";
				
				head = "<thead>";
				head += "<tr>";
				head += "<th class='center'>시간</th>";
				head += "<th class='center'>고객명</th>";
				head += "<th class='center'>기관명</th>";
				head += "<th class='center' style='width: 12%;'>대역폭(Kbps)</th>";
				head += "<th class='center' style='width: 10%;'>입력(Kbps)</th>";
				head += "<th class='center' style='width: 10%;'>사용률(입력)</th>";
				if(radioValue == "D") {
					head += "<th class='center' style='width: 10%;'>최번시(입력)</th>";
				} else if(radioValue == "M") {
					head += "<th class='center' style='width: 10%;'>최번일(입력)</th>";
				}
				head += "<th class='center' style='width: 10%;'>출력(Kbps)</th>";
				head += "<th class='center' style='width: 10%;'>사용률(출력)</th>";
				if(radioValue == "D") {
					head += "<th class='center' style='width: 10%;'>최번시(출력)</th>";
				} else if(radioValue == "M") {
					head += "<th class='center' style='width: 10%;'>최번일(출력)</th>";
				}
				head += "</thead>";

				$("#traffic_thead").empty();
				$("#traffic_thead").append(head).trigger("create");

     		  	if(data.result == 1){
         		  alert("검색하신 데이터가 존재하지 않습니다.");
         		  return;
				} else {
					/*
					//Paging
					if(data.result == 1) {
						last_data = data.result;
						return;
					}
					 */
					var body = "";
					body = "<tbody>";
					$.each(data.traffic_list, function(){
						body += "<tr>";
						body += "<td class='center'>"+this.collect_time+"</td>";
						body += "<td class='center'>"+this.cust_name+"</td>";
						body += "<td class='center'>"+this.nat_name+"</td>";
						body += "<td class='center' style='width: 12%;'>"+NumberFormt(this.band)+"</td>";
						body += "<td class='center' style='width: 10%;'>"+NumberFormt(this.inbps)+"</td>";
						body += "<td class='center' style='width: 10%;'>"+NumberFormt(this.per_inbps)+"%</td>";
						if(radioValue == "D") {
							body += "<td class='center' style='width: 10%;'>"+this.max_in_hour+"시</td>";
						} else if(radioValue == "M") {
							body += "<td class='center' style='width: 10%;'>"+this.max_in_day+"일</td>";
						}
						body += "<td class='center' style='width: 10%;'>"+NumberFormt(this.outbps)+"</td>";
						body += "<td class='center' style='width: 10%;'>"+NumberFormt(this.per_outbps)+"%</td>";
						if(radioValue == "D") {
							body += "<td class='center' style='width: 10%;'>"+this.max_out_hour+"시</td>";
						} else if(radioValue == "M") {
							body += "<td class='center' style='width: 10%;'>"+this.max_out_day+"일</td>";
						}
						body += "</tr>";				
					});
					body += "</tbody>";

					$("#traffic_tbody").empty();
					$("#traffic_tbody").append(body).trigger("create");
					
					headerFix();
				}
     		    
		    },
		    error:function(request,status,error){
		      console.log(request + " : " + status + " : " + error);
		    },
		    beforeSend:function(){
		    	$("#progressbar").css("display", "block");
			},
			complete:function(){
				$("#progressbar").css("display", "none");
			}
		  });
	}

	function check(){
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		sessioncheck();
				
		if($("#search_llnum").val()=='' || $("#search_llnum").val()==null){
			alert('회선번호를 입력하세요.');
			return;
		}
		trafficSearch();
	}

	function checkDate(str) {
		
				
		var radioValue = $("input:radio[name=traffic_type]:checked").val();
		var msg = "";
		var result = true;
		
		
		var startDateArr = $("#datepicker1").val().split('-');
		var endDateArr = $("#datepicker2").val().split('-');
		
		var startDate = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
		var endDate = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);

      
	//2018.04.20 트래픽 조회 기간 조회 방식 변경 날짜 차이 계산방식으로 변경	
       var checkDate = endDate.getTime() - startDate.getTime();
       var currDay = 24*60*60*1000; //시,분,초,밀리세컨
       var dayCheck = parseInt(checkDate/currDay);

         if(startDateArr[1]!=endDateArr[1]){
	           if(startDateArr[1]== "04" || startDateArr[1]=="06" || startDateArr[1]=="09" ||startDateArr[1]=="11"){
	                var dayCheck = dayCheck -1;
	           } else if(startDateArr[1]== "02"){
	               if(startDateArr[0]=="2020" || startDateArr[0]=="2024"){
	            	   var dayCheck = dayCheck -2;
	                   }else{
	                	   var dayCheck = dayCheck -3;
	                       }
	          }else if(endDateArr[1]=="04" || endDateArr[1]=="06" || endDateArr[1]=="09" || endDateArr[1]=="11" ){
	        	  var dayCheck = dayCheck +1;
	          }else if(endDateArr[1]== "02"){
	              if(endDateArr[0]=="2020" || endDateArr[0]=="2024"){
	           	   var dayCheck = dayCheck +2;
	                  }else{
	               	   var dayCheck = dayCheck +3;
	                      }
	          }
         }

       //alert("기간차이==="+dayCheck); 
		if(dayCheck < 0) {
			alert("조회 시작일을 확인하시기 바랍니다.");
			result = false;
			return result;
		}

		if(radioValue=="R"){
			if(dayCheck >2){
				 alert("실시간 "+str+"은 최대 3일까지 가능합니다.\n조회기간을 확인하시기 바랍니다. "); 
				 result = false;             
	              return result;
				}else{
			     result = true;             
		         return result;
					}
			}
		if(radioValue=="H"){
			if(dayCheck > 30){
				 alert("시간별 "+str+"은 최대 1개월까지 가능합니다.\n조회기간을 확인하시기 바랍니다. ");             
				 result = false;             
	              return result;
				}else{
			     result = true;             
		         return result;
					}
			}
		if(radioValue=="D"){
			if(dayCheck > 91){
	              alert("일별 "+str+"은 최대 3개월까지 가능합니다.\n조회기간을 확인하시기 바랍니다. ");              
	              result = false;             
	              return result;
				}else{
			     result = true;             
		         return result;
					}
			}
		if(radioValue=="M"){
			if(dayCheck > 91){
	              alert("월별 "+str+"은 최대 3개월까지 가능합니다.\n조회기간을 확인하시기 바랍니다. ");              
	              result = false;             
	              return result;
					}else{
			     result = true;             
		         return result;
					}
		}
		 return result;
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
</script>
</html>