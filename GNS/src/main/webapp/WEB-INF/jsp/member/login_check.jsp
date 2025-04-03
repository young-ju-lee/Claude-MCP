<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="login container">
	    <div class="row">
	      <div class="col s12 offset-m3 m6">
	        <!-- div class="page-title"-->
	          <div>
	            <h2>동일아이디로 로그인 되어 있습니다.</h2>
	             <h2> 로그아웃 하시겠습니까?</h2>
	            <h2>확인 버튼을 누르면 모두 로그아웃 처리됩니다!</h2>
	          </div>
	        <!--/div -->
	        <form id="UserForm" name="UserForm" method="post"  >
			    <input type="hidden" name="user_id" id="user_id" value="${user_id}" />
			    <input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
				<input type="hidden" value="" name="request_url" />
				
	        </form>
	        
	        <a href="javascript:" onclick="javascript:return logout();" class="btn btn-block kt-red">확인</a>
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

function logout(){

	var subUrl = '<%=request.getContextPath()%>';	
	//if((event.keyCode != 13)&&(event.keyCode != 0)) return false;

   

	if(RealUrl.indexOf("kt-egov") != -1) {
		document.UserForm.action="https://www.kt-egov.co.kr/member/logout.do";
	} else if(RealUrl.indexOf("gns.kt.com") != -1) {
		document.UserForm.action="https://gns.kt.com/member/logout.do";
	} else {
		document.UserForm.action=subUrl + "/member/logout.do" ;
	}
	document.UserForm.method ="post";
	document.UserForm.submit();
	return true;
}

if("${error}" != ""){
	alert("${error}");
}
</script>