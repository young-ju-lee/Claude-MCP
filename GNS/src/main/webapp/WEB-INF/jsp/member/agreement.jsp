<%
/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : agreement.jsp
* Overview    : 홈페이지 이용약관 화면 
* Description : 홈페이지 이용약관 화면
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents agree-ps">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>홈페이지 이용약관</h2>
	        <p>kt그룹 국가정보통신서비스 <span class="kt-red-text">홈페이지 이용약관</span>입니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>고객지원</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/member/agreement.do');">홈페이지 이용약관</a></li>
	      </ul>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>홈페이지 이용약관</h3>
	        <ul class="list-style1">
	          <li>[제1조 목적]
		          <ul>
		          	<li>이 약관은 kt그룹 국가정보통신서비스(http://gns.kt.com)가(이하 "운영자") 제공하는 정보서비스를 이용하는 가입자(이하 "회원")의 이용 조건 및 제반절차에 관한 사항을 정하는데 목적이 있습니다.</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>[제2조 회원의 가입]
		          <ul>
		          	<li>kt그룹 국가정보통신서비스의 회원이 되고자 할 경우에는 본 약관의 동의와 회원가입 신청서를 작성하여 운영자의 이용승낙이 있어야 하며, 운영자가 가입 신청서를 검토한 결과 부적합 한 사유가 발생 하면 "회원"가입이 거부 될 수도 있습니다.</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>[제3조 회원의 구분]
		          <ul>
		          	<li>회원은 일반회원과 고객회원으로 구분되며, 이에 따른 서비스 제한 및 kt 내부관련 서비스는 이용제한이 있습니다. </li>
		          	<li>1. 일반회원 : 약관동의 및 회원가입신청서 작성 후 운영자의 이용 승낙 등록 됩니다. </li>
		          	<li>2. 고객회원 : 약관동의 및 회원가입신청서 작성 후 운영자가 고객여부를 확인한 후 개별 통지 하여 등록됩니다.</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>[제4조 가입정보 변경 및 해지]
		          <ul>
		          	<li>회원은 본인의 이용정보가 변경되었을 경우에 그 내용을 온라인에서 수정하여야 하며, 회원 가입 해지를 하고자 할 때에는 본인이 직접 온라인에서 가입해지 하셔야 합니다. <br>
		          		변경 혹은 해지하지 않은 정보로 인하여 발생된 문제에 대한 책임은 전적으로 회원에게 있으며 운영자는 책임을 지지 않습니다.
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>[제5조 서비스 이용]
		          <ul>
		          	<li>kt그룹 국가정보통신서비스는 연중무휴 1일 24시간 동안 제공되는 것을 원칙으로 합니다. 다만, 운영자의 업무상 또는 기타 부득이한 경우에는 그러하지 아니합니다. kt국가정보통신망 서비스내에서 얻은 정보를 운영자의 사전 승낙 없이 영리를 목적으로한 복제, 출판, 재배포 등을 할 수 없습니다.</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>[제6조 개인정보 보호]
		          <ul>
		          	<li>운영자는 회원의 동의 없이 본 서비스의 정한 목적 외의 용도로 또는 관계법령에 위반하여 개인정보를 제 3자에게 제공하지 않으며, 개인정보가 외부로 유출되지 않도록 항상 최선을 다하겠습니다.</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>[제7조 약관의 해석]
		          <ul>
		          	<li>운영자는 본 약관의 내용을 회원가입 전에 화면에 게시하며, 본 약관에서 정하지 않은 사항 및 해석은 관계법령에 의거합니다.</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>[제8조 손해배상]
		          <ul>
		          	<li>운영자의 고의 또는 과실 없이 회원님에게 발생한 일체의 손해에 대하여 운영자는 책임을 부담하지 않습니다</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>[제9조 정보 유효 기준일]
		          <ul>
		          	<li>1. 상품정보를 포함한 모든 자료는 2002년 7월 1일을 기준으로 한다.</li>
					<li>2. 요금표등의 변경된 기준은 홈페이지에 즉시 적용을 기본으로 한다.<br>
						단, 내부사정으로 인해 미적용된 자료에 대한 분쟁시 책임지지 않는다.</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	    </div>
	    
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</html>