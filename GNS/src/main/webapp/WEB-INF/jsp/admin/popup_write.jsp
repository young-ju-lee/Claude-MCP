<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
		<div class="container">
			<div class="page-title">
		      <div>
		        <h2>팝업창관리</h2>
		        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">팝업창관리</span>입니다.</p>
		      </div>
		      <ul>
	        	 <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
		         <li>관리</li>
		         <li><a href="javascript:menuMove('<%=request.getContextPath()%>/admin/popup_list.do');">팝업창관리</a></li>
		      </ul>
		    </div>
			<form name="popupform" method="POST" >
				<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
				<input type="hidden" value="" name="request_url" />
			</form>
			<div class="bbs-write">
				<form action=""	name="fm_insert" id="fm_insert" method="post">
					<input type="hidden" name="flag" value="${flag}"/>
					<input type="hidden" name="pop_id" value="${popupVo.pop_id}" />
					<input type="hidden" name="pop_width" id="pop_width" title="팝업크기(가로)" value="500" />	             	            
					<input type="hidden" name="pop_height" id="pop_height" title="팝업크기(세로)" value="420" />
					<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
					<input type="hidden" value="" name="request_url" />
					<input type="hidden" value="" name="pop_title" />
					<input type="hidden" value="" name="pop_fr_date" />
					<input type="hidden" value="" name="pop_to_date" />
					<input type="hidden" value="" name="pop_yn" />
					<input type="hidden" value="" name="pop_cont" />
				</form>
				<dl>
			        <dt>제목</dt>
			        <dd><input type="text" title="제목 입력" id="pop_title" style="line-height: 20px;" value="${popupVo.pop_title}"></dd>
		        </dl>
				<dl>
			        <dt>게시기간</dt>
			        <dd>
			        	<input name="search_from" type="text"  id="datepicker1" class="kt-input" style="width: 15%; line-height: 20px;" readonly value="${popupVo.pop_fr_date}"/><i id="date_icon" class="material-icons" style="cursor: pointer;padding-bottom: 10px;">date_range</i>
			            ~
			            <input name="search_to" type="text"  id="datepicker2" class="kt-input" style="width: 15%; line-height: 20px;" readonly value="${popupVo.pop_to_date}"/><i id="date_icon2" class="material-icons" style="cursor: pointer;padding-bottom: 10px;">date_range</i>
			        </dd>
		        </dl>
		        <dl>
			        <dt>사용유무</dt>
			        <dd>
			        	<div class="select-wrap">
				            <select name="" id="pop_yn" title="사용유무">
				            	<option value="Y" <c:if test="${popupVo.pop_yn!='N'}">selected</c:if>>사용</option>				                            
	                          	<option value="N" <c:if test="${popupVo.pop_yn=='N'}">selected</c:if>>미사용</option>
				            </select>
				          </div>
			        </dd>
		        </dl>
		        <dl>
			        <dt>내용</dt>
			        <dd>
			          <textarea name="" id="pop_cont" style="height:300px;" title="내용">${popupVo.pop_cont}</textarea>
		          	<textarea rows="16" id="content" style="display: none;">${popupVo.pop_cont}</textarea>
			        </dd>
			    </dl>
				
				<form action="<%=request.getContextPath()%>/admin/popup_delete.do" name="fm_delete" method="post">
					<input type="hidden" name="pop_id" value="${popupVo.pop_id}" />
					<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
					<input type="hidden" value="" name="request_url" />
				</form>
    			
			</div>
			
			<div class="row">
				<div class="col s12 right-align">
			    	<a href="javascript:" class="btn show-on-small" onclick="javascript:show();">미리보기</a>
			    	<a href="javascript:" class="btn show-on-small" onclick="javascript:goList()">목록</a>
			    	<c:choose>
			    		<c:when test="${flag == 'E'}">
			    		<a href="javascript:" class="btn show-on-small kt-red" onclick="javascript:goSave('edit');">수정</a>
			    	</c:when>
			    	<c:otherwise>
			    		<a href="javascript:" class="btn show-on-small kt-red" onclick="javascript:goSave('write');">등록</a>
			    	</c:otherwise>
			    	</c:choose>
			    	<a href="javascript:" class="btn show-on-small" onclick="javascript:reset();">초기화</a>
			    	<c:if test="${flag == 'E'}">
			    		<a href="javascript:" class="btn show-on-small kt-red" onclick="return goDel()">삭제</a>
			    	</c:if>
			    </div>
			</div>


		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />

<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>


<script>
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


function reset(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	var title = "${popupVo.pop_title}";
	var content = $("#content").text();
	var pop_yn = "${popupVo.pop_yn}";
	var fromDate = "${popupVo.pop_fr_date}";
	var toDate = "${popupVo.pop_to_date}";

	$("#pop_title").val(title);
	$("#pop_cont").val(content);
	$("#datepicker1").val(fromDate);
	$("#datepicker2").val(toDate);

	$.each($("#pop_yn option"),function(){
		$(this).removeAttr("selected");
		if(this.value == pop_yn){
			$(this).prop("selected",true);
		}
	});
	
}

function goList(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	document.popupform.action = "<%=request.getContextPath()%>/admin/popup_list.do";
	document.popupform.submit();
}

//팝업창내역 삭제 
function goDel(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			if(!confirm("삭제하시겠습니까?")){
  				return false;
  			}
  			document.fm_delete.submit();
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
  			 	 	    if(!confirm("삭제하시겠습니까?")){
  			  				return false;
  			  			}
  			  			document.fm_delete.submit();
  			}
  	},
  				     error:function(request,status,error){
  				    	 alert(request + " : " + status + " : " + error);
  				    	var sessioncheck = false;
							return sessioncheck ;
  				     }
  				  });
  			
  			}
			

	
}

//창닫기  
function closePop(name) {
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
	$("#popup_Layer").css("display", "none");
}  

function show(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		$("#popup_content").html($("#pop_cont").val().replace(/\n/g,"<br>"));
		$("#popup_title").html($("#pop_title").val());
		$("#popup_Layer").css("display", "block");
}

//저장
function goSave(type){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			var width = $("#pop_width").val();	 
  			var height = $("#pop_height").val();
  			if(width == null || height == null || isNaN(width) == true || isNaN(height) == true){
  				alert('크기를 정확히 입력하세요.');		
  				return;
  			}

  			var title = $("#pop_title").val();
  			if(title == null || title==''){
  				alert('제목을 입력하세요.');		
  				return;		
  			} 
  			
  			var cont = $("#pop_cont").val();
  			if(cont == null || cont==''){
  				alert('내용을 입력하세요.');		
  				return;		
  			}

  			var fromDate = $("#datepicker1").val();
  			var toDate = $("#datepicker2").val();
  			if(fromDate == null || fromDate=='' || toDate == null || toDate == ''){
  				alert('팝업창 시작, 종료일을 입력하세요.');		
  				return;		
  			}	 

  			var width = $("#pop_width").val();
  			var height = $("#pop_height").val();

  			if(width <= 0 || height <= 0){
  				alert('크기는 0 보다 커야합니다.');
  				return;
  			}

  			var text = "";
  			if(type == "edit"){
  				text = "수정";
  			}else{
  				text = "등록";
  			}
  			
  			if(!confirm(text+"하시겠습니까?")){
  				return false;	
  			}

  			setCookie("egov", "", "");
  			
  			$("#fm_insert [name=pop_title").val($("#pop_title").val());
  			$("#fm_insert [name=pop_fr_date").val($("#datepicker1").val());
  			$("#fm_insert [name=pop_to_date").val($("#datepicker2").val());
  			$("#fm_insert [name=pop_yn").val($("#pop_yn").val());
  			$("#fm_insert [name=pop_cont").val($("#pop_cont").val());

  			document.fm_insert.action = "<%=request.getContextPath()%>/admin/popup_write_register.do";
  			document.fm_insert.submit();
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
  			 	 	    var width = $("#pop_width").val();	 
  			  			var height = $("#pop_height").val();
  			  			if(width == null || height == null || isNaN(width) == true || isNaN(height) == true){
  			  				alert('크기를 정확히 입력하세요.');		
  			  				return;
  			  			}

  			  			var title = $("#pop_title").val();
  			  			if(title == null || title==''){
  			  				alert('제목을 입력하세요.');		
  			  				return;		
  			  			} 
  			  			
  			  			var cont = $("#pop_cont").val();
  			  			if(cont == null || cont==''){
  			  				alert('내용을 입력하세요.');		
  			  				return;		
  			  			}

  			  			var fromDate = $("#datepicker1").val();
  			  			var toDate = $("#datepicker2").val();
  			  			if(fromDate == null || fromDate=='' || toDate == null || toDate == ''){
  			  				alert('팝업창 시작, 종료일을 입력하세요.');		
  			  				return;		
  			  			}	 

  			  			var width = $("#pop_width").val();
  			  			var height = $("#pop_height").val();

  			  			if(width <= 0 || height <= 0){
  			  				alert('크기는 0 보다 커야합니다.');
  			  				return;
  			  			}

  			  			var text = "";
  			  			if(type == "edit"){
  			  				text = "수정";
  			  			}else{
  			  				text = "등록";
  			  			}
  			  			
  			  			if(!confirm(text+"하시겠습니까?")){
  			  				return false;	
  			  			}

  			  			setCookie("egov", "", "");
  			  			
  			  			$("#fm_insert [name=pop_title").val($("#pop_title").val());
  			  			$("#fm_insert [name=pop_fr_date").val($("#datepicker1").val());
  			  			$("#fm_insert [name=pop_to_date").val($("#datepicker2").val());
  			  			$("#fm_insert [name=pop_yn").val($("#pop_yn").val());
  			  			$("#fm_insert [name=pop_cont").val($("#pop_cont").val());

  			  			document.fm_insert.action = "<%=request.getContextPath()%>/admin/popup_write_register.do";
  			  			document.fm_insert.submit();
  			}
  	},
  				     error:function(request,status,error){
  				    	 alert(request + " : " + status + " : " + error);
  				    	var sessioncheck = false;
							return sessioncheck ;
  				     }
  				  });
  			
  			}
			
	
	
}

//24시간 기준 쿠키 설정하기  
// expiredays 후의 클릭한 시간까지 쿠키 설정  
function setCookie(name, value, expiredays) {
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);

	document.cookie = name + "=" + escape(value) + "; path=/; expires="
	+ todayDate.toGMTString() + ";";
}
</script>
</html>