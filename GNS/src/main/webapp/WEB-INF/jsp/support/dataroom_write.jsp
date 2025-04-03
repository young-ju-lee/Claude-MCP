<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : dataroom_write.jsp
* Overview    : 자료실 내용작성 화면 
* Description : 자료실 내용작성 화면 
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>


	<!-- Header start -->
	<!--Header 인크로우드 위치입니다.-->
	<jsp:include page="../common/header.jsp" />
	<!-- Header end -->
	
	<form name="dataroomform" method="post" action="">
		<input type="hidden" name="file_id" />
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
	</form>

	<div class="page-contents">
	  <div class="container">
	    <div class="page-title">
	      <div>
	        <h2>자료실</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">자료실</span>입니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>고객지원</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/support/dataroom_list.do');">자료실</a></li>
	      </ul>
	    </div>
	    
	    <div class="bbs-write">
		    
			<dl>
		        <dt>제목</dt>
		        <dd><input type="text" title="제목 입력" id="file_title" value="${dataRoomVo.file_title}" style="line-height: 20px;" maxlength="100"></dd>
		      </dl>
		      <dl>
		        <dt>구분</dt>
		        <dd>
		          <input type="radio" name="file_gbn" id="file_gbn" title="구분" value="GU"
                  <c:if test="${dataRoomVo.file_gbn=='GU'}">checked</c:if> />이용지침서
			      <input type="radio" name="file_gbn" id="file_gbn" title="구분" value="AG" 
			      <c:if test="${dataRoomVo.file_gbn=='AG'}">checked</c:if> />홈페이지 이용약관
			      <input type="radio" name="file_gbn" id="file_gbn" title="구분" value="AP" 
			      <c:if test="${dataRoomVo.file_gbn=='AP'}">checked</c:if> />신청서
			      <input type="radio" name="file_gbn" id="file_gbn" title="구분" value="OT" 
			      <c:if test="${dataRoomVo.file_gbn=='OT'}">checked</c:if> />기타                
		        </dd>
		      </dl>
		      <dl>
		        <dt>파일</dt>
		        <dd>
		        	<form action=""	name="fm_insert" id="fm_insert" method="post" enctype="multipart/form-data">
						<input type="hidden" name="flag" value="${flag}"/>
						<input type="hidden" name="file_id" value="${dataRoomVo.file_id}" />
						<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
						<input type="hidden" value="" name="request_url" />
						<input type="hidden" name="file_title" value=""/>
						<input type="hidden" name="file_cont" value=""/>
						<input type="hidden" name="file_gbn" value=""/>
			          <input type="file" name="upload_file" id="upload_file" title="파일" style="width: 500px; height: 35px;" />
			          &nbsp;&nbsp;
			          <c:if test="${flag=='E'}">
	                     <label for="r_gbn_l" class="kt-label right-m blue-text" id="file_name">[&nbsp;${dataRoomVo.file_name}&nbsp;]</label>
	                  </c:if>
			          <br />
			          <span class="kt-red-text">* PDF 형식의 문서만 첨부 가능합니다.<br/>* 보안문서의 경우 보안해제 후 등록하시기 바랍니다.</span><br>
			          
                     
					</form>
		        </dd>
		      </dl>
		      <dl>
		        <dt>내용</dt>
		        <dd>
		          <textarea rows="16" id="file_cont">${dataRoomVo.file_cont}</textarea>
		          <textarea rows="16" id="content" style="display: none;">${dataRoomVo.file_cont }</textarea>
		        </dd>
		      </dl>
	    </div>
	    <div class="row">
	      <div class="col s12 offset-m3 m6 center">
	        <a href="javascript:" onclick="goList()" class="btn ">목록</a>
	        <c:choose>
	        	<c:when test="${flag=='E'}">
	        		<a href="javascript:" onclick="goSave(); return false;" class="btn kt-red">수정</a>
	        	</c:when>
	        	<c:otherwise>
	        		<a href="javascript:" onclick="goSave(); return false;" class="btn kt-red">등록</a>
	        	</c:otherwise>
	        </c:choose>
	        
	        <a href="javascript:" onclick="reset(); return false;" class="btn">초기화</a>
	      </div>
	    </div>
	   </div>
	  </div>
	  
	<!-- Footer Bottom start -->
	<!--footer 인크로드 위치입니다. -->
	<jsp:include page="../common/footer.jsp" />
	<!-- //Footer Bottom end -->

<script>  
	function reset(){
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		sessioncheck();
				
		var title = "${dataRoomVo.file_title}";
		var content = $("#content").text();
		var file = "${dataRoomVo.file_name}";

		$("#file_title").val(title);
		$("#file_cont").val(content);
		$("#file_name").val(file);
		$("#upload_file").val("");
	}

    function checkStr(strOriginal, strFind, strChange){
    	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
    	sessioncheck();
    			
        var position, strOri_Length;
        position = strOriginal.indexOf(strFind);
        while (position != -1){
            strOriginal = strOriginal.replace(strFind, strChange);
            position = strOriginal.indexOf(strFind);
        }
        strOri_Length = strOriginal.length;
        return strOri_Length;
    }

/******************************************************************************
 *  Function Name : checkInputData
 *  Description   : 데이터의 입력 유무 및 입력 크기 제한을 검사
 *  Parameters    : form_id  - 검사할 입력 필드의 form에서의 id
 *                  min_size - 검사할 입력 필드의 최소 입력 byte 수
 *                  max_size - 검사할 입력 필드의 최대 입력 byte 수
 *                  title    - 검사할 입력 필드의 명칭
 *                  need     - 필수 입력 여부 (true : 필수, false : 필수 아님)
 *  Example       :
 *  if (!checkInputData(document.myForm.user_id, 4, 8, "아이디", true)) return;
 *  Comment       :
 ******************************************************************************/

    function checkInputData(form_title, min_size, max_size, title, need) {
        if (need && (checkStr(form_title, " ", "")==0 || checkStr(form_title, "&nbsp;", "")==0)) {
            alert("입력항목 [" + title + "]을(를) 입력하십시요.");
            return false;
        } else if (!need && (form_title == "")) {
            return true;
        }

        var	unicode	= escape(form_title);
        var	count	= 0;
        var	i	= 0;

        for (i = 0; i < form_title.length; i++) {
            if (form_title.charAt(i) == ' ') count++;
        }

        if (count == form_title.length) {
            alert("입력항목 [" + title + "]의 형식이 맞지 않습니다.");
            return false;
        }

        count	= 0;

        for (i = 0; i < unicode.length; i++) {
            if (unicode.charAt(i) == '%') {
                i++;

                if (unicode.charAt(i) == 'u') {
                    count	+= 2;
                    i		+= 4;
                } else {
                    count++;
                    i++;
                }
            } else {
                count++;
            }
        }

        if (count < min_size) {
            alert("입력항목 [" + title + "]의 글자수는 " + min_size + "Bytes 이상입니다.\n\n"
                      + "현재 글자수 : " + count + "Bytes");
            return false;
        }

        if (count > max_size) {
            alert("입력항목 [" + title + "]의 글자수는 " + max_size + "Bytes로 제한됩니다.\n\n"
                      + "현재 글자수 : " + count + "Bytes");
            return false;
        }

        return true;
    }

 // 저장 버튼 클릭시
function goSave() { 
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	var id = '${sessionScope.user_id}';
  		if(id == null || id =="") {
  			var arrFileGbn = $("[name=file_gbn]");
  			var arrLen = $("[name=file_gbn]").length;
  			var fileGbn = "";
  			var flag = "${flag}";
  			for(var i=0;i<arrLen;i++){
  			   if(arrFileGbn[i].checked){
  				   fileGbn = arrFileGbn[i].value;
  			   }    
  			}

  			var title = $("#file_title").val();
  			if (!checkInputData(title, 1, 100, "제목", true)) {
  			    return;
  			}
  			/* 
  			var content = $("file_cont").val();

  			if(checkStr(content, " ", "")==0 || checkStr(content, "&nbsp;", "")==0) {
  		  		alert("입력항목 [내용]을(를) 입력하십시요.");
  		  		return;
  			}

  			var title = $("#file_title").val();
  			if( title == null || title == ''){
  			 alert("제목을 입력하세요.");
  			    return;    
  			}
  		 */
  			if (fileGbn == ""){
  				alert("구분을 입력하세요.");
  			    return;            
  			} 

  			var cont = $("#file_cont").val();
  			if( cont == null || cont == ''){
  			 alert("내용을 입력하세요.");
  			    return;    
  			}

  			var file = $("#upload_file").val();

  			if($("#file_name").html() == "" || $("#file_name").html() == null){
  				if(file == "") {
  					alert("PDF형식의 문서를 첨부하세요.");
  					return false;
  				} else {
  					var ext = file.substring(file.lastIndexOf(".")+1, file.length).toLowerCase();
  					if(ext != "pdf") {
  						alert("PDF 형식의 문서만 첨부 가능합니다.\n보안문서의 경우 보안해제 후 등록하시기 바랍니다.");
  						$("#upload_file").val("");
  						return;
  					}
  				}
  			}
  			
  			if(flag == "E") {
  				if(!confirm("자료를 수정하시겠습니까?")){
  					return false;
  				}
  			} else {
  				if(!confirm("자료를 등록하시겠습니까?")){
  					return false;
  				}
  			}

  			$("#fm_insert [name=file_title").val($("#file_title").val());
  			$("#fm_insert [name=file_cont").val($("#file_cont").val());
  			$("#fm_insert [name=file_gbn").val($("[name=file_gbn]:checked").val());
  			

  		  	document.fm_insert.action = "<%=request.getContextPath()%>/support/dataroom_write_register.do";
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
  			 	 	    var arrFileGbn = $("[name=file_gbn]");
  			  			var arrLen = $("[name=file_gbn]").length;
  			  			var fileGbn = "";
  			  			var flag = "${flag}";
  			  			for(var i=0;i<arrLen;i++){
  			  			   if(arrFileGbn[i].checked){
  			  				   fileGbn = arrFileGbn[i].value;
  			  			   }    
  			  			}

  			  			var title = $("#file_title").val();
  			  			if (!checkInputData(title, 1, 100, "제목", true)) {
  			  			    return;
  			  			}
  			  			/* 
  			  			var content = $("file_cont").val();

  			  			if(checkStr(content, " ", "")==0 || checkStr(content, "&nbsp;", "")==0) {
  			  		  		alert("입력항목 [내용]을(를) 입력하십시요.");
  			  		  		return;
  			  			}

  			  			var title = $("#file_title").val();
  			  			if( title == null || title == ''){
  			  			 alert("제목을 입력하세요.");
  			  			    return;    
  			  			}
  			  		 */
  			  			if (fileGbn == ""){
  			  				alert("구분을 입력하세요.");
  			  			    return;            
  			  			} 

  			  			var cont = $("#file_cont").val();
  			  			if( cont == null || cont == ''){
  			  			 alert("내용을 입력하세요.");
  			  			    return;    
  			  			}

  			  			var file = $("#upload_file").val();

  			  			if($("#file_name").html() == "" || $("#file_name").html() == null){
  			  				if(file == "") {
  			  					alert("PDF형식의 문서를 첨부하세요.");
  			  					return false;
  			  				} else {
  			  					var ext = file.substring(file.lastIndexOf(".")+1, file.length).toLowerCase();
  			  					if(ext != "pdf") {
  			  						alert("PDF 형식의 문서만 첨부 가능합니다.\n보안문서의 경우 보안해제 후 등록하시기 바랍니다.");
  			  						$("#upload_file").val("");
  			  						return;
  			  					}
  			  				}
  			  			}
  			  			
  			  			if(flag == "E") {
  			  				if(!confirm("자료를 수정하시겠습니까?")){
  			  					return false;
  			  				}
  			  			} else {
  			  				if(!confirm("자료를 등록하시겠습니까?")){
  			  					return false;
  			  				}
  			  			}

  			  			$("#fm_insert [name=file_title").val($("#file_title").val());
  			  			$("#fm_insert [name=file_cont").val($("#file_cont").val());
  			  			$("#fm_insert [name=file_gbn").val($("[name=file_gbn]:checked").val());
  			  			

  			  		  	document.fm_insert.action = "<%=request.getContextPath()%>/support/dataroom_write_register.do";
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
     
	function goList(){
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		sessioncheck();
				
		document.dataroomform.file_id.value = "";
		document.dataroomform.action = "<%=request.getContextPath()%>/support/dataroom_list.do";
		document.dataroomform.submit();
	}
	<%-- 
	function goModify(){
		document.dataroom_write_form.file_id.value = file_id;
		document.dataroom_write_form.action = "<%=request.getContextPath()%>/support/dataroom_write.do";
		document.dataroom_write_form.submit();
	}
	 --%>
</script>
</html>