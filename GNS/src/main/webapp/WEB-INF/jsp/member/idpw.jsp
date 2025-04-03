<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="login container">
	    <div class="row">
	      <div class="col s12 offset-m3 m6">
	        <div class="page-title">
	          <div>
	            <h2>Password(비밀번호) 찾기</h2>
	          </div>
	        </div>
	        <form id="idpwform" name="idpwform" method="post"  >
	        	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
				<input type="hidden" value="" name="request_url" />
				
				<div class="user-info-write">
		        	<dl>
				        <dt>아이디</dt>
				        <dd><input type="text" class="kt-input" title="아이디" name="user_id" id="user_id"></dd>
				    </dl>
		        	<dl>
				        <dt>이메일</dt>
				        <dd><input type="text" class="kt-input" title="이메일 " name="user_mail" id="user_mail"></dd>
				    </dl>
				    <dl>
				        <dt></dt>
				        <dd>
				          <p>
				            <i class="material-icons kt-red-text">info_outline</i> 비밀번호를 잊어버리신 분은 위의 항목을 입력하시고 확인을 눌러주세요.
				          </p>
				        </dd>
				      </dl>
		        </div>
	        </form>
	        
	        <a href="javascript:" onclick="javascript:return checkMsg();" class="btn btn-block kt-red">확인</a>
	      </div>
	    </div>
	  </div>
	</div>
	
	<jsp:include page="../common/footer.jsp" />
</html>

<script>
 var RealUrl = location.href;
 if(RealUrl.indexOf("http:") != -1 && (RealUrl.indexOf("kt-egov") != -1 || RealUrl.indexOf("gns.kt.com") != -1)) {
 	RealUrl = RealUrl.replace('http:','https:');
 	document.pageForm.action = RealUrl;
 	document.pageForm.submit();
 }
//아이디 이메일주소로 비밀번호 찾기
function checkMsg(){
	var subUrl = '<%=request.getContextPath()%>';	
	//if((event.keyCode != 13)&&(event.keyCode != 0)) return false;

	var form = document.idpwform;
	
	if(form.user_id.value == ""){
		alert("아이디를 입력하세요.");
		form.user_id.focus();
		return false;
	}else if(form.user_mail.value == ""){
		alert("이메일 주소를 입력하세요.");
		form.user_mail.focus();
		return false;
	}

	if(RealUrl.indexOf("kt-egov") != -1) {
		form.action="https://www.kt-egov.co.kr/member/idpw_2.do";
	} else if(RealUrl.indexOf("gns.kt.com") != -1) {
		form.action="https://gns.kt.com/member/idpw_2.do";
	} else {
		form.action=subUrl + "/member/idpw_2.do";
	}
	form.method ="post";
	form.submit();
	return true;
}

if("${error}" != ""){
	alert("${error}");
}
</script>