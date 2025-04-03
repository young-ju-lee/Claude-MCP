<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<jsp:include page="../common/header.jsp" />

<div class="page-contents">
	<div class="container">

		<div class="page-title">
			<div>
				<h2>FAQ</h2>
				<p>kt그룹 국가정보통신서비스(4단계)를 이용하시면서 주로 질문하시는 사항입니다.</p>
			</div>
			<ul>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img
						src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif"
						alt="홈페이지 메인"></a></li>
				<li>고객지원</li>
				<li><a
					href="javascript:menuMove('<%=request.getContextPath()%>/support/faq.do');">FAQ</a></li>
			</ul>
		</div>

		<ul class="faq-list">
			<!--         unit start -->
			<li><input type="radio" id="faq_list_1" name="faq" /> <label
				for="faq_list_1">국가정보통신서비스 이용대상은 어떻게 되나요?</label>
				<div class="ac-con">
					국가기관(행정기관, 입법기관, 사법기관) 및 지방자치단체<br /> 공공기관 운영에 관한 법률 제4조에 따른 공공기관(준정부기관)<br />
					※ 단, 공공기관은 "B"그룹 서비스 이용불가, "C"그룹(인터넷전화서비스)을 사용하고자 하는 경우에만 "B"그룹 서비스 이용가능(음성 용도)
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_2" name="faq" /> <label
				for="faq_list_2">서비스 신청은 어떻게 하나요?</label>
				<div class="ac-con">
					국가정보통신서비스 관련 절차에 의거하여 "이용계약신청서"를 제출하여 신청 <br /> - 신규(신규신청/해제),
					변경(서비스변경/해지), 전환(3단계 서비스를 4단계 서비스로 전환)<br /> - 접수 · 상담 번호 <br />
					1899-3773 (ARS 1) (A,E그룹- 전용회선,CCTV서비스)<br />
					1588-0114 (ARS 2 > 2) (B,C,D그룹- 인터넷,인터넷전화,무선서비스)<br /> ※ 청약서 다운받기 : 자료실 참고
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_3" name="faq" /> <label
				for="faq_list_3">국가정보통신서비스 4단계 제공 서비스는 무엇인가요?</label>
				<div class="ac-con">
					A그룹(전용회선서비스그룹), B그룹(인터넷서비스그룹), C그룹(인터넷전화서비스그룹), D그룹(무선서비스그룹), E그룹(CCTV서비스그룹)<br /> -
					A그룹(전용회선서비스그룹) : 기본회선, 백본회선<br /> - B그룹(인터넷서비스그룹) :
					인터넷서비스<br /> - C그룹(인터넷전화서비스그룹): 인터넷전화서비스, 모바일행정전화서비스<br /> - D그룹(무선서비스그룹) : 무선데이터서비스, IoT서비스<br />
					- E그룹(CCTV서비스그룹) : CCTV서비스
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_4" name="faq" /> <label
				for="faq_list_4">국가정보통신서비스 4단계 개시일은 언제인가요?</label>
				<div class="ac-con">
					2019.10.1일입니다. (2019.10.1일 이후 3단계 신규 불가)<br /> ※ 4단계 협약기간 : 2019.10.1
					~ 2022.6.30
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_5" name="faq" /> <label
				for="faq_list_5">이용기관은 4단계 국가정보통신서비스에 대해서 어떻게 알게 되나요?</label>
				<div class="ac-con">
					사전 행정자치부가 각 국가기관 및 지방자치단체(시도) 담당자들 대상으로 4단계 국가정보통신서비스에 대해 이용기관 설명회를
					개최<br /> 2019.7.3일자로
					<제4차 국가정보통신서비스 개통식> 협약식을 가졌으며, 설명자료 배포<br />
					행정자치부에서 국가정보통신이용지침서를 2019.9.30일 배포<br />
					이용지침서는 NIA웹 사이트(www.nia.or.kr)의 공지게시판과, 국가정보통신서비스 웹
					사이트(http://gns.kt.com) 자료실에 업로드되어 있습니다. 
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_6" name="faq" /> <label
				for="faq_list_6">ATM, 이더넷 등 기존 국가정보통신서비스 회선들은 강제 해지되나요?</label>
				<div class="ac-con">계약 종료된 서비스는 서비스전환을 권유드리며, 해지시까지는 기존 계약요금으로
					지속 사용하실 수 있습니다.</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_7" name="faq" /> <label
				for="faq_list_7">기존 국가정보통신 회선들은 어떻게 되나요?</label>
				<div class="ac-con">
					계약만료된 회선들은 전환 신청을 통하여 4단계 서비스로 전환하여 이용하실 수 있습니다.<br /> 기 계약조건을
					지속적으로 유지하고자 하는 경우, 별도의 신청 절차없이 지속 유지됩니다.
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_8" name="faq" /> <label
				for="faq_list_8">전용회선서비스 중 백본회선의 이용요건은 어떻게 되나요?</label>
				<div class="ac-con">
					(지방자치단체) 광역시청↔군구간, 도청↔시·군간 연결하는 회선으로, 사업소 등 시·도 본청 직속 기관도 백본회선서비스
					대상기관에 포함<br /> (국가기관) 노드기준과 회선기준을 모두 만족하는 경우에 한하여 이용 가능<br /> -
					노드기준 : 전국망을 구성하고, 17개 광역시·도 중 8개 이상에 백본 노드를 갖추고 있는 기관으로, 노드의 기준은 본부
					기준 1차 소속기관으로 함<br /> - 회선기준 : 백본회선 대역폭의 합이 45M 이상이며, 백본회선 하위에 연결되는
					기본회선이 5회선 이상인 경우<br /> ※ 단, 회선기준은 만족하나, 노드기준이 충족 안 되는 기관은 백본회선서비스
					요금을 이용하되 기본회선 할인율 적용<br /> ※ 백본회선서비스 이용 상세조건은 이용지침
					<백본회선서비스 개요> 참고(9p) 
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_9" name="faq" /> <label
				for="faq_list_9">CCTV 서비스 이용 중 다수의 카메라를 이용하는 경우 회선료는 어떻게
					되나요?</label>
				<div class="ac-con">
					서비스 회선요금은 카메라수와 무관하게 1회선(10Mbps)당 이용요금 부과 <br /> 예시1) 통신사업자가 CCTV 카메라별로 물리적 회선/포트(10M) 단위로 제공하는 경우 
					 회선별로 요금 청구<br /> 예시2) 통신사업자는 CCTV 1회선(30M)을 제공하고 기관이 다수의 카메라를 연결하여 이용하는
					경우 통신사업자는 물리적인 1회선 구성 및 요금 청구
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_10" name="faq" /> <label
				for="faq_list_10">우리 기관이 현재 선납하여 선납할인율을 적용받고 있는데 요금이 인하되면
					선납한 요금은 어떻게 되나요?</label>
				<div class="ac-con">선납할인율은 최초 선납 계약 시 선납 개월 수에 의해 정해지므로 선납할인율은
					변화가 없으며, 계약한 선납개월수만큼 인하된 요금에 대해 선납반영이 되고, 남은 금액은 이용기관의 요청시 환급해드리며,
					이용기관이 환급을 요청하지 않을 경우 지속적으로 해당회선 또는 청구계약의 월요금으로 납부하게 됩니다.</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_11" name="faq" /> <label
				for="faq_list_11">할인 적용순서는 어떻게 되나요?</label>
				<div class="ac-con">
					1.장기계약할인<br /> 2.다량이용할인/다회선할인/예비회선/망분리할인 (장기계약과 다량이용 또는 장기계약과 다회선이용만 
					중복 할인적용, 단 다량이용과 다회선 이용 할인은 중복할인 불가) </br />
					※ 망분리회선, 예비회선, 단기이용회선은 중복 할인 대상 제외
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_12" name="faq" /> <label
				for="faq_list_12">다량이용할인/다회선할인의 기준은 무엇인가요?</label>
				<div class="ac-con">
					동일서비스, 동일청구계약단위별 적용.<br /> 다량이용할인은 청구기준(부가세 제외된 할인적용 후 금액 기준으로
					다량이용할인 적용)<br /> 다회선할인은 전월 말일자 기준 회선수로 산정
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_13" name="faq" /> <label
				for="faq_list_13">계약기간 만료 전 해지시 위약금(할인반환금)이 있는지요?</label>
				<div class="ac-con">
					 <ul class="list-style1">
	          <li>다음 각 호에 해당하지 않는 경우, 계약기간 만료 이전에 중도 해지시 </br> 이용요금 할인액을 위약금으로 사업자에게 지급</br>
	           1) 서비스 제공이 불가능한 지역으로서 이전등 불가피한 사유가 있는 경우</br> 
	           2) 사업자가 SLA 품질기준을 3회 이상 위반한 경우	          
	           </li>
	        </ul>	        
	        <table class="centered">
	           <thead>
	           	<tr>
	              <th >위약금 산정 방식</th>	           
	            </tr>
	            
	          </thead>
	          <tbody>
	            <tr>
	              <td> 계약기간내 차하위 할인율이 없는 경우 = 이용요금 * 계약할인율 * 사용일/월</br>
	              계약기간내 차하위 할인율이 있는 경우 = (이용요금 * 계약할인율 * 사용일/월) - (이용요금*차하위할인율*사용일/월)
	              </td>	             
	            </tr>
	           
	          </tbody>
	        	
	        </table>
					
				</div></li>
			<!--         unit end -->
			<!--         unit start -->
			<li><input type="radio" id="faq_list_14" name="faq" /> <label
				for="faq_list_14">단기간 이용 가능한가요?</label>
				<div class="ac-con">
					기본회선/인터넷서비스는 단기간 이용 가능합니다.<br /> 대회·행사·선거 등 한시적인 통신수요에 대해 기본회선을 사용기간 동안만 과금
		          하는 서비스로서 이용요금은<br /> 기본회선 1일 24시간, 한 달 30일 기준으로 사용한 기간을 나누어 일할
		          계산방식을 적용하며, 사용기간 등이 모호할 경우 신청시 협의·조정합니다
				</div></li>
		</ul>

	</div>
</div>
<jsp:include page="../common/footer.jsp" />
</html>