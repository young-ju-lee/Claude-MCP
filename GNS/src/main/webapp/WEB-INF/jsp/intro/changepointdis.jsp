<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>제도변경사항</h2>
	        <p>kt그룹 4단계 국가정보통신서비스의 1,2,3단계 대비 달라진 <span class="kt-red-text">제도변경사항</span>을 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>제도소개</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepoint.do');">제도변경사항</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">제도변경사항</a>
	      </div>
	      <ul>
	        <li >
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepoint.do');">서비스</a>
	        </li> 
	        <li >
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepointspeed.do');">속도체계</a>
	        </li> 
	       
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepointsla.do');">SLA</a>
	        </li>   
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/intro/changepointdis.do');">할인율</a>
	        </li>   
	      </ul>
	    </div>
	   
	   
	    <div class="row">
	      <div class="col s12">
	        <h3>할인율 수준</h3>
	        <ul class="list-style1">
	          <li>장기계약 할인(CCTV 제외)</li>
	        </ul>
	        
	        <table class="centered">
	           <thead>
	           	<tr>
	              <th colspan="3">1단계</th>
	              <th colspan="2">NIS</th>
	              <th colspan="3">2단계</th>
	              <th colspan="4">3단계</th>
	            </tr>
	            <tr>
	              <th>계약기간</th>
	              <th>할인율<br/>(A/B그룹)</th>
	              <th>할인율<br/>(C그룹)</th>
	              <th>계약기간</th>
	              <th>할인율</th>
	              <th>계약기간</th>
	              <th>할인율<br/>(A/B그룹)</th>
	              <th>할인율<br/>(C그룹)</th>
	              <th>계약기간</th>
	              <th>할인율<br/>(A/B그룹)</th>
	              <th>할인율<br/>(C그룹)</th>
	              <th>할인율<br/>(D그룹)</th>
	            </tr>
	          </thead>
	          <tbody>
	            <tr>
	              <td>2년</td>
	              <td>3%</td>
	              <td>1%</td>
	              <td>2년</td>
	              <td>5%</td>
	              <td>2년 이상<br>3년 미만</td>
	              <td>5%</td>
	              <td>2%</td>
	              <td>2년 이상<br>3년 미만</td>
	              <td>7%</td>
	              <td>2%</td>
	              <td>2%</td>
	            </tr>
	            <tr>
	              <td>3년</td>
	              <td>5%</td>
	              <td>2%</td>
	              <td rowspan="2" class="border-end-bottom">3년</td>
	              <td rowspan="2" class="border-end-bottom">10%</td>
	              <td>3년 이상<br>5년 미만</td>
	              <td>10%</td>
	              <td>3%</td>
	              <td>3년 이상<br>5년 미만</td>
	              <td>12%</td>
	              <td>3%</td>
	              <td>3%</td>
	            </tr>
	            <tr>
	              <td>5년</td>
	              <td>10%</td>
	              <td>-</td>
	              <td>5년 이상</td>
	              <td>12%</td>
	              <td>5%</td>
	              <td>5년 이상</td>
	              <td>14%</td>
	              <td>5%</td>
	              <td>5%</td>
	            </tr>
	          </tbody>
	        	
	        </table>
	        
	        <ul class="list-style1">
	          <li>다회선 할인
	          	<ul>
	          		<li>시내.시외 구간 분리 적용된 다회선이용 할인율은 시내 ∙시외 구분 없이 회선수 중심으로 단순화하여 할인제도 개선</li>
	          	</ul>
	          </li>
	        </ul>
	        
	        
	        <table class="centered">
	           <thead>
	           	<tr>
	              <th colspan="2">1단계</th>
	              <th colspan="2">2단계</th>
	              <th colspan="2">3단계</th>
	            </tr>
	          </thead>
	          <tbody>
	            <tr>
	              <td>3회선</td>
	              <td>2%</td>
	              <td rowspan="2">3회선 이상<br>10회선 미만</td>
	              <td rowspan="2">3%</td>
	              <td rowspan="2">3~9회선</td>
	              <td rowspan="2">4%</td>
	            </tr>
	            <tr>
	              <td>4~5회선</td>
	              <td class="border-end-right">3%</td>
	            </tr>
	            <tr>
	              <td>6회선 이상</td>
	              <td>4%</td>
	              <td rowspan="2">10회선 이상<br>50회선 미만</td>
	              <td rowspan="2">5%</td>
	              <td rowspan="2">10~49회선</td>
	              <td rowspan="2">6%</td>
	            </tr>
	            <tr>
	              <td>5~9회선</td>
	              <td class="border-end-right">3%</td>
	            </tr>
	            <tr>
	              <td rowspan="5" class="border-end-bottom">10회선 이상</td>
	              <td rowspan="5" class="border-end-bottom">5%</td>
	              <td rowspan="5" class="border-end-bottom">50회선 이상</td>
	              <td rowspan="5" class="border-end-bottom">6%</td>
	              <td>50~99회선</td>
	              <td>7%</td>
	            </tr>
	            <tr>
	              <td>100~399회선</td>
	              <td>8%(10%)</td>
	            </tr>
	            <tr>
	              <td>400~699회선</td>
	              <td>8%(12%)</td>
	            </tr>
	            <tr>
	              <td>700~999회선</td>
	              <td>8%(13%)</td>
	            </tr>
	            <tr>
	              <td>1,000회선 이상</td>
	              <td>8%(14%)</td>
	            </tr>
	          </tbody>
	        	
	        </table>
	        
	        <ul class="list-style1">
	          <li>요금납입책임자 기준(청구 분리시 청구 계약 단위), 월 납부금액에 따른 이용요금 할인</li>
	          <li>다량이용 할인과 다회선 이용 할인은 중복적용 없이 선택 적용</li>
	          <li>100회선 이상 중 ( ) 내 할인율은 CCTV 전송 서비스에 한함</li>
	          <li>※ 상세내역은 지침참고</li>
	        </ul>
	      </div>
	    </div>
	      <div class="row">
	      <div class="col s12">
	        <h3>4단계 기준[상한] 할인율</h3>
	     <h3>기본회선·인터넷서비스·CCTV전송회선서비스 </h3>
	     
	      <h3>장기계약</h3>
	      <ul class="list-style1">
		          <li>요금납입책임자 기준</li>
		          <li>※ CCTV전송회선 서비스는 장기계약 할인 제외</li>
		        </ul>
		        <table class="centered">
		           <thead>
		            <tr>
		              <th>구분</th>
		              <th>2년이상 3년미만</th>
		              <th>3년이상 5년미만</th>
		              <th>5년이상</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td class="border-end-bottom">할인율</td>
		              <td class="border-end-bottom">7%</td>
		              <td class="border-end-bottom">12%</td>
		              <td class="border-end-bottom">14%</td>
		            </tr>
		          </tbody>
		        	
		        </table>
		      </div>
		    </div>
		    
		    <div class="row">
		      <div class="col s12">
		        <h3>다량이용금액 </h3>
		        <ul class="list-style1">
		          <li>요금납입책임자 기준, 월 납부금액에 따른 이용요금 할인</li>
		        </ul>
		        <table class="centered">
		           <thead>
		           	<tr>
		              <th>구분</th>
		              <th>5백~1천만</th>
		              <th>~3천만</th>
		              <th>~5천만</th>
		              <th>~1억</th>
		              <th>~3억</th>
		              <th>~5억</th>
		              <th>~10억</th>
		              <th>~15억</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td class="border-end-bottom">할인율</td>
		              <td class="border-end-bottom">2%</td>
		              <td class="border-end-bottom">3%</td>
		              <td class="border-end-bottom">4%</td>
		              <td class="border-end-bottom">6%</td>
		              <td class="border-end-bottom">8%</td>
		              <td class="border-end-bottom">10%</td>
		              <td class="border-end-bottom">12%</td>
		              <td class="border-end-bottom">15%</td>
		            </tr>
		          </tbody>
		        	
		        </table>
		      </div>
		    </div>
		    
		    <div class="row">
		      <div class="col s12">
		        <h3>다회선이용 </h3>
		        <ul class="list-style1">
		          <li>요금납입책임자 기준, 이용회선수에 따른 이용요금 할인</li>		          
		        </ul>
		        <table class="centered">
		           <thead>
		           	<tr>
		              <th>구분</th>
		              <th>3~9회선</th>
		              <th>10~49회선</th>
		              <th>50~99회선</th>
		              <th>100~399회선</th>
		              <th>400~699회선</th>
		              <th>700~999회선</th>
		              <th>1000회선 이상</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td class="border-end-bottom">할인율</td>
		              <td class="border-end-bottom">4%</td>
		              <td class="border-end-bottom">6%</td>
		              <td class="border-end-bottom">7%</td>
		              <td class="border-end-bottom">8%</td>
		              <td class="border-end-bottom">8%</td>
		              <td class="border-end-bottom">8%</td>
		              <td class="border-end-bottom">8%</td>
		            </tr>
		          </tbody>
		        	
		        </table>
		        </div>
		        </div>
	        <div class="row">
		      <div class="col s12 ">
		        <h3>예비회선(재해복구 · 이중화) :  기본회선 · 인터넷서비스에 한정</h3>
		        <table class="centered">
		           <thead>
		            <tr>
		              <th colspan="2">구분</th>
		              <th>용도설명</th>
		              <th>할인율(%)</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td colspan="2">재해복구</td>
		              <td>기관의 재해대비 복구회선  재해가 발생하지 않은 평시에도 재해복구 훈련 등 주기적으로 활용할 수 있음</td>
		              <td>50%</td>
		            </tr>
		            <tr>
		              <td rowspan="2" class="border-end-bottom">이중화</td>
		              <td>예비</br>백업</td>
		              <td>주/예비 회선으로 운영하고 평상시 예비회선에는 트래픽이 전송되지 않고 장애 시에만 트래픽이 전송됨</td>
		              <td>추가회선에 대해 50%</td>
		            </tr>
		            <tr>
		              <td>부하</br>분산</td>
		              <td>주/부 회선의 각 2개 회선을 운영하며, 평상시 양쪽회선에 트래픽이 분산 전송되다가 장애시에는 정상회선으로 트래픽 전부 전송</br> * 부회선은 주회선 대역폭의 70% 이상인 경우 해당</td>
		              <td>추가회선에 대해 40%</td>
		            </tr>
		          </tbody>
		        	
		        </table>
	       
	      </div>
	    </div>
	    <div class="row">
		      <div class="col s12">
		        <h3>망분리회선</h3>
		        <ul class="list-style1">
		          <li>망분리회선은 기본회선만 해당하며 구간별 합산요금에 대하여 해당 인하율만큼 추가 인하</li>
		          <li>동일루트, 동일장비를 통해 사용하는 추가회선에 적용</li>
		         
		        </ul>
		        <table class="centered">
		           <thead>
		           	<tr>
		              <th>구분</th>
		              <th>2회선</th>
		              <th>3회선</th>
		              <th>4회선 이상</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td class="border-end-bottom">할인율</td>
		              <td class="border-end-bottom">15%</td>
		              <td class="border-end-bottom">17%</td>
		              <td class="border-end-bottom">20%</td>
		            </tr>
		          </tbody>
		        	
		        </table>
		      </div>
		    </div>
		    <h3>할인율 적용방식</h3>
		        <ul class="list-style1">
		          <li> 요금납입책임자 기준으로 장기계약과 다량이용 또는 장기계약과 </br> 다회선이용만 중복 할인적용</li>
		          <li>※ 망분리회선, 예비회선, 단기이용회선은 중복 할인 대상 제외</li>
		         
		        </ul>
	   <div class="row">
	      <div class="col s12">
	        <h3>백본회선 서비스 할인</h3>
	        <ul class="list-style1">
	          <li>요금납입책임자 기준, 이용 회선 수에 따른 이용요금 할인</li>
	          <li>장기계약과 다량이용/장기계약과 다회선이용은 중복할인 적용되나 둘 중 하나만 선택가능</li>
	          <li>다량이용금액 할인과 다회선이용 할인은 중복적용 불가</li>
	        </ul>
	        <table class="centered">
	           <thead>
	           	<tr>
	              <th>구분</th>
	              <th colspan="8">할인율</th>
	            </tr>
	          </thead>
	          <tbody>
	            <tr>
	              <td rowspan="2">장기계약</td>
	              <td>계약기간</td>
	              <td colspan="3">2년 이상 3년 미만</td>
	              <td colspan="4">3년 이상</td>
	            </tr>
	            <tr>
	              <td>할인율</td>
	              <td colspan="3">20%</td>
	              <td colspan="4">30%</td>
	            </tr>
	            <tr>
	              <td rowspan="2">다량이용금액</td>
	              <td>이용료</td>
	              <td>1억/월 이상</td>
	              <td>2억/월 이상</td>
	              <td>3억/월 이상</td>
	              <td>4억/월 이상</td>
	              <td>5억/월 이상</td>
	              <td>6억/월 이상</td>
	              <td>7억/월 이상</td>
	            </tr>
	            <tr>
	              <td>할인율</td>
	              <td>10%</td>
	              <td>20%</td>
	              <td>30%</td>
	              <td>40%</td>
	              <td>50%</td>
	              <td>60%</td>
	              <td>70%</td>
	            </tr>
	            
	            <tr>
	              <td rowspan="2" class="border-end-bottom">다회선 이용</td>
	              <td>백본회선수</td>
	              <td>10회선 이상</td>
	              <td>20회선 이상</td>
	              <td>30회선 이상</td>
	              <td>40회선 이상</td>
	              <td>50회선 이상</td>
	              <td>60회선 이상</td>
	              <td>70회선 이상</td>
	            </tr>
	            <tr>
	              <td>할인율</td>
	              <td>10%</td>
	              <td>20%</td>
	              <td>30%</td>
	              <td>40%</td>
	              <td>50%</td>
	              <td>60%</td>
	              <td>70%</td>
	            </tr>
	          </tbody>
	        	
	        </table>
	      </div>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>인터넷전화/모바일행정전화서비스</h3>
	        <ul class="list-style1">
	          <li>장기계약과 다량가입 중복할인 적용
	          <ul>
		          	<li> 장기계약 할인 : 계약기간에 대하여 할인</li>
		          </ul>
		         
		          <table class="centered">
		           <thead>
		           	<tr>
		              <th>구분</th>
		              <th>2년</th>
		              <th>3년</th>
		              <th>5년</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td>할인율</td>
		              <td>2%</td>
		              <td>3%</td>
		              <td>5%</td>
		            </tr>
		          </tbody>
		        </table>
		        <ul>
		          	<li>다량가입 할인 : 요금납입책임자 기준, 이용포트(DOD) 수에 따른 기본료 할인</li>
		          </ul>
		          <table class="centered">
		           <thead>
		           	<tr>
		              <th>구분</th>
		              <th>5포트~9포트</th>
		              <th>10포트~49포트</th>
		              <th>50포트 이상</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td>할인율</td>
		              <td>-</td>
		              <td>3%</td>
		              <td>5%</td>
		            </tr>
		          </tbody>
		        </table>
	          </li>
	          
	        </ul>
	      </div>
	    </div>
	     <div class="row">
	      <div class="col s12">
	        <h3>IoT서비스</h3>
	        <ul class="list-style1">
	          <li>IoT서비스는 장기계약과 다량이용 또는 장기계약과 다회선이용 택일하여 중복할인 적용
		          <ul>
		          	<li>장기계약</li>
		          </ul>
		          <table class="centered">
		           <thead>
		           	<tr>
		              <th>구분</th>
		              <th>2년 이상 ~ 3년 미만</th>
		              <th>2년 이상 ~ 3년 미만</th>
		              <th>5년 이상 ~</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td>할인율</td>
		              <td>15%</td>
		              <td>25%</td>
		              <td>30%</td>
		            </tr>
		          </tbody>
		        </table>
		         <ul>
		          	<li>다량이용(이용요금 납부기관 월 이용요금 총액 기준)</li>
		          </ul>
		          <table class="centered">
		           <thead>
		           	<tr>
		              <th>구분</th>
		              <th>5백 ~ 1천만</th>
		              <th> ~ 2천만</th>
		              <th> ~ 3천만</th>
		              <th> ~ 4천만</th>
		              <th> ~ 5천만</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td>할인율</td>
		              <td>40%</td>
		              <td>41%</td>
		              <td>42%</td>
		              <td>43%</td>
		              <td>44%</td>
		            </tr>
		          </tbody>
		          <thead>
		           	<tr>
		              <th>구분</th>
		              <th> ~ 6천만</th>
		              <th> ~ 7천만</th>
		              <th> ~ 8천만</th>
		              <th> ~ 9천만</th>
		              <th> 9천만 ~</th>
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td>할인율</td>
		              <td>45%</td>
		              <td>46%</td>
		              <td>47%</td>
		              <td>48%</td>
		              <td>50%</td>
		            </tr>
		          </tbody>
		        </table>
		        <ul>
		          	<li>다회선</li>
		          </ul>
		          <table class="centered">
		           <thead>
		           	<tr>
		              <th>구분</th>
		              <th>100 ~ 500회선</th>
		              <th>501 ~ 1,000회선</th>
		              <th>1,001 ~ 3,000회선</th>
		              <th>3,001 ~ 5,000회선</th>		             
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td>할인율</td>
		              <td>5%</td>
		              <td>10%</td>
		              <td>15%</td>
		              <td>20%</td>
		            </tr>
		          </tbody>
		           <thead>
		           	<tr>
		              <th>구분</th>
		              <th>5,001 ~ 7,000회선</th>
		              <th>7,001 ~ 10,000회선</th>
		              <th>10,001 ~ 20,000회선</th>	
		              <th>20,001 ~</th>	             
		            </tr>
		          </thead>
		          <tbody>
		            <tr>
		              <td>할인율</td>
		              <td>25%</td>
		              <td>30%</td>
		              <td>35%</td>
		              <td>40%</td>
		            </tr>
		          </tbody>
		        </table>
	          </li>
	          
	        </ul>
	      </div>
	    </div>
       </div>
	    </div>
	<jsp:include page="../common/footer.jsp" />
</html>