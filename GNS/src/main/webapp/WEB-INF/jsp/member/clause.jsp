<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : clause.jsp
* Overview    : 회원가입 시 동의 화면 
* Description : 회원가입 시 동의 화면
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
	<!--Heder 인크로우드 위치입니다.-->
<jsp:include page="../common/header.jsp" />
	<!-- //Header end -->
    
<div class="page-contents">
  <div class="join container">
    <div class="page-title">
      <div>
        <h2>회원가입</h2>
        <p>
          회원가입을 하시면 보다 많은 정보를 얻으실 수 있습니다.<br />
          kt그룹 국가정보통신서비스는 <span class="kt-red-text">효율적, 안정적</span> 운영을 위한 상시 감시 및 복구 체계를 구축하고 있습니다.
        </p>
      </div>
      <ul>
        <li>1. 14세 미만 어린이 확인</li>
        <li><span class="kt-red-text">2. 약관 및 방침 동의</span></li>
        <li>3. 회원정보 입력</li>
        <li>4. 완료</li>
      </ul>
    </div>
	<form id="clauseform" name="clauseform" method="post">
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
		<!-- 2017.12.22  보안성검토 선택동의 추가 -->
		<input type="hidden" value="" name="check1" />
		<input type="hidden" value="" name="check2" />
		<input type="hidden" value="" name="check3" />
	    <div>
	      <h3 class="p-top"><span class="kt-red-text">[필수]</span> 홈페이지 이용약관</h3>
	      <div class="txt-box">
	        <h4>[제1조 목적]</h4>
	        <p>이 약관은 kt그룹 국가정보통신서비스(http://gns.kt.com)가(이하 "운영자") 제공하는 정보서비스를 이용하는 가입자(이하 "회원")의 이용 조건 및 제반절차에 관한 사항을 정하는데 목적이 있습니다.</p>
	        <h4>[제2조 회원의 가입]</h4>
	        <p>kt그룹 국가정보통신서비스의 회원이 되고자 할 경우에는 본 약관의 동의와 회원가입 신청서를 작성하여 운영자의 이용승낙이 있어야 하며, 운영자가 가입 신청서를 검토한 결과 부적합 한 사유가 발생 하면 "회원"가입이 거부 될 수도 있습니다.</p>
	        <h4>[제3조 회원의 구분]</h4>
	        <p>회원은 일반회원과 고객회원으로 구분되며, 이에 따른 서비스 제한 및 kt 내부관련 서비스는 이용제한이 있습니다. <br>
	          1. 일반회원 : 약관동의 및 회원가입신청서 작성 후 운영자의 이용 승낙 등록 됩니다. <br>
	          2. 고객회원 : 약관동의 및 회원가입신청서 작성 후 운영자가 고객여부를 확인한 후 개별 통지 하여
	          등록됩니다.</p>
	        <h4>[제4조 가입정보 변경 및 해지]</h4>
	        <p>회원은 본인의 이용정보가 변경되었을 경우에 그 내용을 온라인에서 수정하여야 하며, 회원 가입 해지를 하고자 할 때에는 본인이 직접 온라인에서 가입해지 하셔야 합니다. <br>
	          변경 혹은 해지하지 않은 정보로 인하여 발생된 문제에 대한 책임은 전적으로 회원에게 있으며 운영자는 책임을 지지 않습니다.</p>
	        <h4>[제5조 서비스 이용]</h4>
	        <p>kt그룹 국가정보통신서비스는 연중무휴 1일 24시간 동안 제공되는 것을 원칙으로 합니다. 다만, 운영자의 업무상 또는 기타 부득이한 경우에는 그러하지 아니합니다.KT국가정보통신망 서비스내에서 얻은 정보를 운영자의 사전 승낙 없이 영리를 목적으로한 복제, 출판, 재배포 등을 할 수 없습니다.</p>
	        <h4>[제6조 개인정보 보호]</h4>
	        <p>운영자는 회원의 동의 없이 본 서비스의 정한 목적 외의 용도로 또는 관계법령에 위반하여 개인정보를 제 3자에게 제공하지 않으며, 개인정보가 외부로 유출되지 않도록 항상 최선을 다하겠습니다.</p>
	        <h4>[제7조 약관의 해석]</h4>
	        <p>운영자는 본 약관의 내용을 회원가입 전에 화면에 게시하며, 본 약관에서 정하지 않은 사항 및 해석은 관계법령에 의거합니다.</p>
	        <h4>[제8조 손해배상]</h4>
	        <p>운영자의 고의 또는 과실 없이 회원님에게 발생한 일체의 손해에 대하여 운영자는 책임을 부담하지 않습니다</p>
	        <h4>[제9조 정보 유효 기준일]</h4>
	        <p>1. 상품정보를 포함한 모든 자료는 2002년 7월 1일을 기준으로 한다. <br>
	          2. 요금표등의 변경된 기준은 홈페이지에 즉시 적용을 기본으로 한다<br>
	          단, 내부사정으로 인해 미적용된 자료에 대한 분쟁시 책임지지 않는다.</p>
	      </div>
	      <p><input name="chick1" id="chick1" title="위 홈페이지 이용약관을 동의합니다." type="checkbox" value=""> <label for="chick1">위 홈페이지 이용약관에 동의합니다.</label></p>
	     
	       <h3 class="p-top"><span class="kt-red-text">[필수]</span> 개인정보 수집/이용 동의</h3>
	      <div class="txt-box">	       
	        
	        <table class="centered">
	        <tr>
	        <th style="width: 35%;">목적</th>
	         <th style="width: 33%;">항목</th>
	         <th style="width: 32%;">보유기간</th>
	        </tr>
	        <tr>
	        <td>이용자 식별 및 본인여부 확인</td>
	        <td>성명,아이디,비밀번호</td>
	         <td>회원탈퇴 후 즉시</td>
	        </tr>
	         <tr>
	        <td>계약 이행을 위한 연락, 민원 등 고객 고충 처리</td>
	        <td>연락처(이메일,휴대전화번호)</td>
	         <td>회원탈퇴 후 즉시</td>
	        </tr>
	        <!-- 2018.01.19 14세미만 가입불가화면을 만듬에 따라 삭제처리
	         <tr>	        
	        <td>만14세 미만 아동의 개인정보 수집 시 법정 대리인 동의여부 확인</td>
	        <td>법정 대리인의 성명,내/외국인,성별,생년월일,주소,연락전화번호,계약자와의 관계</td>
	         <td>회원탈퇴 후 즉시</td>
	        </tr>
	        -->
	        </table>
	        <p><b>※ 회사의 서비스 제공 등 계약의 이행에 필요한 경우 해당 위탁업무와 수탁자(증감, 변동 가능)를 회사의 홈페이지(gns.kt.com)의 ‘개인정보처리방침’에 공개함으로써, 처리위탁 동의에 갈음합니다.</b></p>
	        <p><b>※ 국가정보통신 서비스 제공을 위해서 필요한 최소한의 개인정보이므로 동의를 해 주셔야 서비스를 이용하실 수 있습니다.</b></p>
	      
	      </div>
	
	      <p><input name="chick2" id="chick2" title="위 홈페이지 이용약관에 동의합니다." type="checkbox" value=""> <label for="chick2">위 홈페이지 이용약관에 동의합니다.</label></p>
	      <!-- 2017.12.22  보안성검토 선택동의 추가 -->
	      <div>   
	       
	        <h3 class="p-top"><span class="kt-red-text">[선택]</span> 개인정보의 처리위탁에 대한 동의</h3>
	         <div class="txt-box">
	          <table class="centered">
	        <tr>
	        <th>수탁사</th>
	         <th>위탁내용</th>
	        </tr>
	        <tr>
	        <td>(주)KTDS</td>
	        <td>서비스 운영/장애처리 기술지원</td>
	        </tr>
	        </table>
	        <p><b>※ 동의를 거부하시는 경우에도 국가정보통신 서비스는 이용하실 수 있습니다.</b></p>
	         </div>
	         <p><input name="chick3" id="chick3" title="위 홈페이지 이용약관에 동의합니다." type="checkbox" value=""> <label for="chick3">위 홈페이지 이용약관에 동의합니다.</label></p>
	            
	      
	           
	      
	      
	      <div class="center">
	        <a href="javascript:" class="btn kt-red" onclick="javascript:checkMsg();">동의합니다.</a>&nbsp;
	        <a href="javascript:cancel();" class="btn">동의하지 않습니다.</a>
	      </div>
	    </div>
	 </form> 
  </div>
</div>



<!-- Footer Bottom start -->
<!--footer 인크로드 위치입니다. -->
<jsp:include page="../common/footer.jsp" /> 
<!-- //Footer Bottom end -->


<script>
	 var RealUrl = location.href;
	 if(RealUrl.indexOf("http:") != -1 && (RealUrl.indexOf("kt-egov") != -1 || RealUrl.indexOf("gns.kt.com") != -1)) {
	 	RealUrl = RealUrl.replace('http:','https:');
	 	document.pageForm.action = RealUrl;
	 	document.pageForm.submit();
	 }
	//로그인 정보 확인
	//<!-- 2017.12.22  보안성검토 선택동의 추가 -->
	function checkMsg(){		
		
	 	if(!document.clauseform.chick3.checked){
			document.clauseform.check3.value = "N";
			}
		else{
			document.clauseform.check3.value = "Y";
			}   
		if(!document.clauseform.chick1.checked){
			alert("홈페이지 이용약관에 동의해주세요");
			return false;
		}else if(!document.clauseform.chick2.checked){
			alert("개인정보 수집/이용 동의해주세요");
			return false;
		}
		//<!-- 2017.12.22  보안성검토 선택동의 추가 -->
		document.clauseform.check1.value = "Y";
		document.clauseform.check2.value = "Y";
		
		document.clauseform.action="<%=request.getContextPath()%>/member/join.do" ;
		document.clauseform.submit();
	}        
	
	function cancel() {
		document.clauseform.action="<%=request.getContextPath()%>/main.do" ;
		document.clauseform.submit();
	};
</script>
</html>