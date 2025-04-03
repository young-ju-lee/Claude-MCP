<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="login container">
	    <div class="row">
	      <div class="col s12 offset-m3 m6">
	        <div class="page-title">
	          <div>
	            <h2>ID(아이디), Password(비밀번호) 조회내역</h2>
	          </div>
	        </div>
			<div class="user-info-write">
	        	<dl>
			        <dt style="width: 17%;">ID(아이디)</dt>
			        <dd>${memberVo.user_id}</dd>
			    </dl>
	        	<dl>
			        <dt style="width: 17%;">이름</dt>
			        <dd>${memberVo.user_nm}</dd>
			    </dl>
	        	<dl>
			        <dt style="width: 17%;">비밀번호</dt>
			        <dd>${memberVo.user_pw}</dd>
			    </dl>
			    <dl>
			        <dt></dt>
			        <dd>
			          <p>
			            <i class="material-icons kt-red-text">info_outline</i> 비밀번호가 변경되었습니다. 로그인 후 비밀번호 변경 바랍니다.
			          </p>
			        </dd>
			      </dl>
	        </div>
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
</script>