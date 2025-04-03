<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
	
	<jsp:include page="../common/header.jsp" />
	
	<form name="popup_form" action="" method="post" target="" >
		<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
		<input type="hidden" value="" name="request_url" />
	</form>
	
	  <!--2019.04.26 중복로그인 방지- session 폼 추가 공통함수 -->
  <form id="sessionForm2" name="sessionForm" method="post" >
	<input type="hidden" name="user_id" value="<%=request.getSession().getAttribute("user_id") %>" />
	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token"/>
	<input type="hidden" value="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getAttribute("javax.servlet.forward.request_uri")%>" name="request_url" />
  </form>
	
	<div class="page-contents agree-ps">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>개인정보처리방침</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">개인정보처리방침</span>입니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>고객지원</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/member/personal.do');">개인정보처리방침</a></li>
	      </ul>
	    </div>
	    <div class="col s12 m3 right-align">
	    	<a href="javascript:" onclick="javascript:personalPop();" class="btn show-on-small"><!--   -->이전 개인정보처리방침 보기</a>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>개인정보처리방침</h3>
	        <ul class="list-style1">
	          <li>제01장 (총칙)
		          <ul>
		          	<li>kt는 고객님의 개인정보를 소중하게 생각하고 고객님의 개인정보를 효과적으로 관리하고 안전하게 보호하기 위하여 최선의 노력을 다 하고 있습니다. kt는 『정보통신망 이용촉진 및 정보보호 등에 관한 법률』과 개인정보보호 관련 각종 법규를 준수하고 있습니다.<br><br>
		          		또한 개인정보처리방침을 제정하여 이를 준수하고 있으며, 본 처리방침을 홈페이지(http://www.kt.com) 및 본 사이트(http://gns.kt.com)내에 공개하여 고객님이 언제나 쉽게 열람할 수 있도록 하고 있습니다.
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>제02장 (개인정보의 수집 및 이용 목적)
		          <ul>
		          	<li>kt는 본 사이트에서 수집한 개인정보를 다음의 목적을 위해 이용합니다.<br>
		          		본 사이트내 회원가입/변경/해지 처리, 본인확인, 개인식별, 회원가입의사확인, 고지사항전달, 국가정보통신서비스 이용확인 및 서비스제공관련 안내
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>제03장 (수집하는 개인정보의 항목 및 수집방법)
		          <ul>
		          	<li>kt는 회원가입, 상담, 서비스 제공 등을 위하여 아래와 같이 최소한의 개인정보만을 수집합니다. </li>
		          	<li>1. 수집하는 개인정보의 항목<br>
		          		필수정보 <br>
          				사이트내 회원가입을 위한 개인 식별정보인 이름, 이메일, 전화번호, 휴대전화번호<br>
          				이용중인 서비스 정보인 회선번호
		          	</li>
		          	<li>2. kt는 다음과 같은 방법으로 개인정보를 수집합니다.<br>
		          		본 사이트 회원가입시 고객이 입력하는 개인정보를 수집
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>제04장 (개인정보 수집 및 이용 등에 관한 동의방법)
		          <ul>
		          	<li>1. 고객님의 개인정보 수집 시에는 관련 법령의 규정에 따라 개인정보의 수집·이용 목적과 제3자 제공 및 처리을 위탁하고 있는 내용을 고객님께 알리고 동의를 얻거나 개인정보처리방침을 통해 고지하고 있습니다.</li>
		          	<li>2. kt의 '개인정보처리방침'에 따른 고객님의 개인정보 수집, 이용, 제3자 제공 및 처리업무의 위탁 등에 대해 고객님께서는 아래와 같은 방법으로 동의하실 수 있습니다.<br>
		          		본 사이트에 회원가입시 동의내용 또는 회사의 패밀리 사이트(http://www.olleh.com 등)에 게시된 '개인정보처리방침'의 동의내용을 확인하신 후 '동의' 버튼 또는 '동의하지 않음' 버튼을 클릭하는 방법
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>제05장 (개인정보의 보유 및 이용기간)
		          <ul>
		          	<li>고객님의 개인정보는 회사가 신청인에게 서비스를 제공하는 기간 동안에 한하여 보유하고 이를 활용합니다. 다만 다른 법률에 특별한 규정이 있는 경우에는 관계 법령에 따라 보관합니다.<br>
		          		이용기간 : 서비스 가입일 ~ 해지일까지<br>보유기간 : 서비스 해지 즉시 파기
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>제06장 (개인정보의 파기절차 및 방법)
		          <ul>
		          	<li>kt는 원칙적으로 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 파기절차 및 방법은 다음과 같습니다.</li>
		          	<li>1. 파기절차<br>
		          		고객이 서비스 가입 등을 위해 제공하신 정보는 서비스 제공 등의 목적이 달성된 후 내부 방침 및 기타 관련 법령에 의한 보유 사유에 따라 일정기간 저장된 후 파기되어집니다. <br>
		          		파기대상 : 보유기간 및 관련법령에 따른 보존기간이 종료된 고객정보
		          	</li>
		          	<li>2. 파기방법<br>
		          		DB 등 전자적 파일 형태로 저장된 개인정보 : 기록을 재생할 수 없는 기술적 방법으로 삭제
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	        <!-- 2017.12.22  보안성검토 개인정보처리방침 수정 -->
	          <li>제07장 (개인정보 처리의 위탁)
		          <ul>
		          	<li>회사는 서비스 제공에 관한 계약을 이행하고 고객님의 편의 증진 등을 위하여 개인정보 처리업무를 외부 전문업체에 위탁하고 있으며, 
		          	수탁자에 대해서는 “위·수탁계약서” 등을 통하여 관련 법규 및 지침의 준수, 정보보호 및 비밀유지, 제3자 제공 금지, 사고 시 책임부담, 
		          	위탁기간 종료 즉시 개인정보의 반납/파기 의무 등을 규정하여 관리하고 있습니다.
		          	</li>
		          		
		          </ul>
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
	          </li>
	        </ul>
	         
	      </div>
	      
	       <div class="col s12">
	        <ul class="list-style1">
	          <li>제08장 (이용자 및 법정대리인의 권리와 그 행사 방법)
		          <ul>
		          	<li>1.고객님(만 14세 미만 아동의 경우 법정대리인 포함)께서는 언제든지 개인정보, 개인정보를 이용하거나 제3자에게 제공한 현황, 
		          	개인정보 수집·이용·제공 등의 동의를 한 현황(이하 '개인정보 등'이라 합니다)에  대한 열람이나 제공을 요구하실 수 있고, 
		          	오류가 있는 경우에는 그 정정을 요구하실 수 있으며, 개인정보의 수집·이용·제공에 대한 동의를 철회 하실 수 있습니다.
		          	</li>
		          	<li>2.고객님(법정대리인)께서는 다음과 같은 방법으로 개인정보 등에 대한 열람 및 정정과 가입해지(동의철회)를 요구할 수 있습니다.		          	
		          	</li>
		          	<li>3. 고객님께서 본인의 개인정보 등에 대한 열람이나 정정을 요구하시거나 개인정보 수집·이용·제공 등의 동의를 철회하시는 경우 
		          	고객님의 신분을 증명할 수 있는 주민등록증, 여권, 운전면허증(신형) 등의 신분증명(사본)을 제시 받아 본인 여부를 확인합니다.		          	
		          	</li>
		          	<li>4.고객님의 대리인이 고객님의 개인정보 등에 대한 열람이나 정정을 요구하거나 고객님의 개인정보의 수집·이용·제공에 대한 
		          	동의를 철회하는 경우에는 대리 관계를 나타내는 위임장, 명의고객님의 인감증명서와 대리인의 신분증명서 등의 증표를 제시 받아 적법한 대리인인지 여부를 확인합니다.	          	
		          	</li>
		          	<li>5.고객님께서는 개인정보 등의 열람이나 제공을 요청하실 수 있으며,회사는 이러한 요청에 지체 없이 필요한 조치를 취합니다.		          	
		          	</li>
		          	<li>6. 고객님께서 개인정보 등의 오류에 대한 정정을 요청하신 경우에는 지체 없이 그 오류를 정정하거나 정정하지 못하는 사유를 이용자에게 알리는 등 필요한 조치를 하고, 
		          	필요한 조치를 할 때까지는 당해 개인정보를 이용 또는 제공하지 않습니다. 또한 잘못된 개인정보를 제3자에게 이미 제공한 경우에는 정정 처리결과를
		          	 제3자에게 지체없이 통지하여 정정이 이루어지도록 하겠습니다. 다만, 다른 법률에 따라 개인정보의 제공을 요청받은 경우에는 그 개인정보를 제공하거나 이용할 수 있습니다.
		          	</li>
		          	<li>7. 회사는 고객님의 요청에 의해 해지 또는 삭제된 개인정보를 "개인정보의 보유 및 이용기간"에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 
		          	이용할 수 없도록 처리하고 있습니다.
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	       <div class="col s12">
	        <ul class="list-style1">
	          <li>제09장 (아동의 개인정보보호)
		          <ul>
		          	<li>1. 회사는 만 14세 미만 아동(이하 '아동')의 개인정보에 대하여 수집·이용·제공 등의 동의를 받는 경우 부모 등 
		          	법정대리인의 동의를 얻도록 하고 있습니다.
		          	</li>
		          	<li>
		          	2. 회사는 법정대리인의 동의를 얻기 위하여 법정대리인의 성명, 생년월일, 연락전화번호 등 필요한 최소한의 정보를 요구할 수 있습니다. 
		          	이 경우 개인정보의 수집·이용 또는 제공 목적 및 법정대리인의 동의가 필요하다는 취지를 아동이 쉽게 이해할 수 있는 평이한 표현으로 아동에게 고지합니다.
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	       <div class="col s12">
	        <ul class="list-style1">
	          <li>제10장 (개인정보 자동 수집장치의 설치 운영 및 그 거부에 관한 사항)
		          <ul>
		          	<li>회사는 홈페이지 운영에 있어 필요 시 고객님의 정보를 찾아내고 저장하는 '쿠키(Cookie)'를 운용합니다. 쿠키는 회사의 웹사이트를 운영하는데 이용되는 서버가 고객님의 브라우저에 보내는 
		          	아주 작은 텍스트 파일로서 고객님의 컴퓨터 하드디스크에 저장됩니다. 고객님께서는 웹브라우저의 보안 정책을 통해 쿠키에 의한 정보수집의 허용 거부 여부를 결정하실 수 있습니다.
		          	</li>
		          	
		          </ul>
	          </li>
	        </ul>
	      </div>
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>제11장 (개인정보의 기술적, 관리적)
		          <ul>
		          	<li>회사는 고객님의 개인정보가 분실, 도난, 유출, 위조·변조 또는 훼손되지 않도록 안전성 확보를 위하여 다음과 같은 기술적·관리적 대책을 마련하고 있습니다.
		          	</li>
		          	<li>
		          	1. 개인정보는 비밀번호에 의해 보호되며, 중요한 데이터는 파일 및 전송 데이터를 암호화하거나 파일 잠금기능(Lock)을 사용하는 등 별도 보안기능을 통해 보호되고 있습니다.
		          	</li>
		          	<li>
		          	2. 백신 소프트웨어를 이용하여 컴퓨터바이러스 등에 의한 피해를 방지하기 위한 조치를 취하고 있습니다. 백신 소프트웨어는 주기적으로 업데이트되며 갑작스런 바이러스가 출현할 경우 백신이 나오는 즉시 이를 도입, 
		          	적용함으로써 개인정보가 침해되는 것을 방지하고 있습니다.
		          	</li>
                    <li>
                    3. 네트워크 상의 개인정보 및 개인인증정보를 안전하게 전송할 수 있도록 보안장치(SSL)를 채택하고 있습니다.
                    </li>
                    <li>
                    4. 해킹 등에 의해 고객님의 개인정보가 유출되는 것을 방지하기 위해, 외부로부터 접근이 통제된 구역에 시스템을 설치하고, 침입을 차단하는 장치를 이용하고 있으며, 
                                          아울러 침입탐지시스템을 설치하여 24시간 침입을 감시하고 있습니다.
                    </li>
                    <li>
                    5. 회사는 고객님의 개인정보를 안전하게 처리하기 위한 내부관리계획을 마련하여 임직원이 이를 숙지하고 준수하도록 하고 있으며 준수 여부를 주기적으로 점검하고 있습니다.
		          	</li>
		          	<li>
		          	6. 회사는 고객님의 개인정보를 처리할 수 있는 자를 최소한으로 제한하고 접근 권한을 관리하며, 새로운 보안 기술 습득 및 개인정보보호 의무 등에 관해 정기적인 사내 교육과 
		          	외부 위탁교육을 통하여 법규 및 정책을 준수할 수 있도록 하고 있습니다.
		          	</li>
		          	<li>
		          	7. 신규직원 채용 시 그리고 연 1회 전 임직원이 정보보호서약서에 서명하게 함으로써 직원에 의한 정보(개인정보 포함) 유출을 사전에 방지하고, 
		          	수시로 개인정보보호 의무를 상기시키며 준수 여부를 감사하기 위한 내부 절차를 마련하여 시행하고 있습니다.
		          	</li>
		          	<li>
		          	8. 개인정보 취급자의 업무 인수인계는 보안이 유지된 상태에서 철저하게 이뤄지고 있으며, 입사 및 퇴사 후 개인정보 침해사고에 대한 책임을 명확하게 규정하고 있습니다.		          	
		          	</li>
		          	<li>
		          	9. 회사는 전산실 및 자료보관실 등을 통제구역으로 설정하여 출입을 통제합니다.
		          	</li>
		          		<li>
		          	10.  서비스 이용계약 체결 또는 서비스 제공을 위하여 고객님의 은행결제계좌, 신용카드번호 등 대금결제에 관한 정보를 수집하거나 
		          	고객님께 제공하는 경우 당해 고객님이 본인임을 확인하기 위하여 필요한 조치를 취하고 있습니다.
		          	</li>
		          		<li>
		          	11. 회사는 고객님 개인의 실수나 기본적인 인터넷의 위험성 때문에 일어나는 일들에 대해 책임을 지지 않습니다. 
		          	고객님의 개인정보를 보호하기 위해서 자신의 ID와 비밀번호를 철저히 관리하고 책임을 져야 합니다.
		          	</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>제12장 (수집한 개인정보의 공유 및 제공)
		          <ul>
		          	<li>kt는 고객님의 개인정보를 '개인정보처리방침'에서 명시한 범위 내에서 이용하며, 명시한 범위를 넘어서 제3자와 공유하거나 제공하지 않습니다.<br><br>
		          		다만, 고객님의 사전동의가 있거나, 관련 법령(통신비밀보호법, 전기통신사업법, 국세기본법 등)의 특별한 규정이 있는 경우, 법령에 정해진 규정과 절차에 따라 제공하는 경우는 예외로 합니다.<br>
		          		본 사이트에서 수집한 개인정보는 별도의 명시가 없는 한 제3자와 공유하거나 제공하지 않습니다.
		          	</li>
		          		
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>제13장 (개인정보 보호책임 부서 및 연락처)
		          <ul>
		          	<li>개인정보 침해신고 : 02-3674-3311</li>
		          	<li>보호책임자 : KT IP운용센터 프리미엄팀 라윤희</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	      <div class="col s12">
	        <ul class="list-style1">
	          <li>제14장 (개인정보처리방침 고지)
		          <ul>
		          	<li>16.3.22 정보통신망법 개정(시행일 16.9.23)</li>
		          </ul>
	          </li>
	        </ul>
	      </div>
	      
	    </div>
	  </div>
	  
	</div>
	
	<jsp:include page="../common/footer.jsp" />
<script>
	function personalPop() {
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		sessioncheck();
				
				
		document.popup_form.action = "<%=request.getContextPath()%>/member/personalPop.do";
		document.popup_form.target = "personalPop";
		window.open("","personalPop","width=700, height=500, scrollbars=yes");
	
		document.popup_form.submit();
	}
	
	function download(obj){
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		sessioncheck();
				
				
		window.open('<%=request.getContextPath()%>/member/personalDownload.do?requestname='+obj, obj, 'width=1, height=1');
		//$("#requestname").val(obj);
		
		//document.downloadForm.submit();
	}
</script>
</html>

