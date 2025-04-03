
<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : stat_list.jsp
* Overview    : 메뉴별 접속통계 화면 
* Description : 메뉴별 접속통계 화면
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

<!-- Header start -->
<!--Heder 인크로우드 위치입니다.-->
<jsp:include page="../common/header.jsp" />
<!-- //Header end -->

<div class="page-contents">
	<div class="container">
		<div class="page-title">
			<div>
				<h2>메뉴별 접속 통계</h2>
				<p>
					kt그룹 국가정보통신서비스의 <span class="kt-red-text">메뉴별 접속 통계</span>입니다.
				</p>
			</div>
			<ul>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img
						src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif"
						alt="홈페이지 메인"></a></li>
				<li>관리자</li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/member/stat_list.do');">메뉴별
						접속 통계</a></li>
			</ul>
		</div>

		<div class="page-tab">
			<div class="current-label">
				<a href="javascript:">메뉴별 접속 통계</a>
			</div>
			<ul>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/member/member_list.do');">회원
						관리</a></li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/admin/popup_list.do');">팝업창
						관리</a></li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/member/login_list.do');">접속자
						통계</a></li>
				<li class="active"><a
					href="javascript:menuMove('<%=request.getContextPath()%>/member/stat_list.do');">메뉴별
						접속 통계</a></li>
			</ul>
		</div>
		<form action="<%=request.getContextPath()%>/member/stat_list.do"
			name="fm_search" method="post">
			<input type="hidden"
				value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
				name="request_token" /> <input type="hidden" value=""
				name="request_url" />
			<div class="row">
				<div class="col s12">
					<input class="kt-input" type="radio" name="search_type"
						id="search_type-A" value="A" title="전체"
						onclick="goSelectRadio(this.value);"
						<c:if test="${menustateVo.search_type=='A'}">checked</c:if> /> <label
						for="search_type-A" class="kt-label right-m">전체</label> <input
						class="kt-input" type="radio" name="search_type" id="search_type-D"
						value="D" title="일자" onclick="goSelectRadio(this.value);"
						<c:if test="${menustateVo.search_type=='D'}">checked</c:if> /> <label
						for="search_type-D" class="kt-label right-m">일자</label> 
						
						      	<input name="search_from" type="text"  id="datepicker1" class="kt-input" readonly value="${menustateVo.search_from}"/><i id="date_icon" class="material-icons" style="cursor: pointer;">date_range</i>
				  					<label id="date_space">~</label>
				  					<input name="search_to" type="text"  id="datepicker2" class="kt-input" readonly value="${menustateVo.search_to}"/><i id="date_icon2" class="material-icons" style="cursor: pointer;">date_range</i>
					&nbsp;&nbsp;&nbsp; <label for="search_id" class="kt-label">사용자ID</label>
					<input class="kt-input right-m" type="text" id="search_id"
						name="search_id" value="${menustateVo.search_id}" /> <a
						href="javascript:" onclick="javascript:goSearch();" class="btn">조회</a>
				</div>
			</div>
			<c:if
				test="${menustateVo.search_id != null && menustateVo.search_id != '' }">
				<p>
					<span class="kt-red-text">${menustateVo.search_id}</span> 님의 메뉴별 접속
					통계 입니다.
				</p>
			</c:if>
			<div class="row">
				<div class="col s12">
					<div class="header-fixed-wrapper">

						<table class="header-fixed">
							<thead>
								<tr>
									<th class="center">번호</th>
									<th class="center">대분류</th>
									<th class="center">중분류</th>
									<th class="center">소분류</th>
									<th class="center">건수</th>
								</tr>
							</thead>
						</table>
						<div class="body-wrapper">

							<table class="body-scroll">
								<tbody>
									<c:forEach var="list" items="${stat_list}">
										<tr>
											<td class="center">${list.seq}</td>
											<td class="center">${list.menu_lvl1_nm}</td>
											<td class="center">${list.menu_lvl2_nm}</td>
											<td class="center">${list.menu_lvl3_nm}</td>
											<td class="center">${list.menu_count}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- Footer Bottom start -->
<!--footer 인크로드 위치입니다. -->
<jsp:include page="../common/footer.jsp" />
<!-- //Footer Bottom end -->

<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script>
$(document).ready(function(){    
	var r = $('input[name=search_type]:checked').val();
	goSelectRadio(r);

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
	            }
		});

		$("#date_icon").bind("click",function(){
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
	            }
		});


		$("#date_icon2").bind("click",function(){
			$("#datepicker2").datepicker('show');
		});

		
	});
});

//검색시 
function goSearch(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			

	if($("#search_id").val() != "") {
		$.ajax({
		    url : "<%=request.getContextPath()%>/member/user_id_check.do",
		    data : "user_id="+$("#search_id").val()
		    	+ "&request_token=" + "<%=request.getSession().getAttribute("CSRF_TOKEN")%>"
				+ "&request_url=" + location.href,
			type : "POST",
			dataType :"json",
	 	    success:function(data){
	 	    	if (data.user_id == "") {
	 	 	    	alert("존재하지 않는 ID 입니다.");
	 	 	    	$("#search_id").val("");
		 	    }
		     },
		     error:function(request,status,error){
		      alert("[error] 아이디 확인중 에러가 발생했습니다 잠시후에 확인하세요");
		     }
		});
	}
	document.fm_search.submit();
}

//페이지 이동시 
function goPage(pageNum){	
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		
	//alert(pageNum);
	document.fm_search.page.value = pageNum; //검색 페이지값
	document.fm_search.search_yn.value = "N"; //신규 검색여부
	document.fm_search.submit();	
}

//
function goSelectRadio(value){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		
	if(value=='A'){
		$("#datepicker1").attr("disabled",true); 
		$("#datepicker2").attr("disabled",true); 
		document.getElementById("date_space").style.visibility = "hidden";	
		document.getElementById("date_icon").style.visibility = "hidden";	
		document.getElementById("date_icon2").style.visibility = "hidden";	
		document.getElementById("datepicker1").style.visibility = "hidden";
		document.getElementById("datepicker2").style.visibility = "hidden";
	}else{
		$("#datepicker1").attr("disabled",false); 
		$("#datepicker2").attr("disabled",false); 
		document.getElementById("date_space").style.visibility = "visible";	
		document.getElementById("date_icon").style.visibility = "visible";	
		document.getElementById("date_icon2").style.visibility = "visible";	
		document.getElementById("datepicker1").style.visibility = "visible";
		document.getElementById("datepicker2").style.visibility = "visible";		
	}
}
</script>
</html>

