<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>요금정보</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">요금정보</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>이용안내</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/guide/area.do');">권역/거리/속도체계</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">거리/속도체계</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/charges.do');">요금표</a>
	        </li> 
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/guide/area.do');">권역/거리/속도체계</a>
	        </li>   
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/charge/reckoning.do');">이용요금계산</a>
	        </li>   
	      </ul>
	    </div>
	    <div class="row">
	      <div class="col s12">
	        <h3>권역 체계</h3>
	         <ul class="list-style1">	              
		          <li> 81개 서비스 권역을 기반으로 서비스 제공</li>
		        </ul>
	        <table class="centered">
	           <thead>
	           	<tr>
	              <th>81개 권역</th>
	              <th>행정권</th>
	               <th>81개 권역</th>
	              <th>행정권</th>
	               <th>81개 권역</th>
	              <th>행정권</th>
	            </tr>
	          </thead>
	          <tbody>
	            <tr>
	              <th>가평</th>
	              <td>가평</td>
	               <th>부산</th>
	              <td>부산, 양산</td>
	               <th>익산</th>
	              <td>익산</td>	              
	            </tr>
	            <tr>
	              <th>강릉</th>
	              <td>강릉</td>
	               <th>삼척</th>
	              <td>삼척</td>
	               <th>인제</th>
	              <td>인제, 양구</td>	              
	            </tr>
	            <tr>
	              <th>거창</th>
	              <td>거창, 함양</td>
	               <th>상주</th>
	              <td>상주, 문경</td>
	               <th>인천</th>
	              <td>인천</td>	              
	            </tr>
	            <tr>
	              <th>경주</th>
	              <td>경주</td>
	               <th>서산</th>
	              <td>서산, 홍성, 예산(태안)</td>
	               <th>장성</th>
	              <td>장성, 영광</td>	              
	            </tr>
	            <tr>
	              <th>고령</th>
	              <td>고령</td>
	               <th>서울</th>
	              <td>서울, 고양, 구리, 성남,</br>의정부, 과천, 부천, 양주,</br>남양주, 동두천, 광명, 하남</td>
	               <th>전주</th>
	              <td>전주, 김제, 임실, 진안, 완주</td>	              
	            </tr>
	            <tr>
	              <th>곡성</th>
	              <td>곡성</td>
	               <th>서천</th>
	              <td>서천</td>
	               <th>정읍</th>
	              <td>정읍, 고창, 부안</td>	              
	            </tr>
	            <tr>
	              <th>광주</th>
	              <td>광주, 담양, 화순</td>
	               <th>속초</th>
	              <td>속초, 양양, 고성(강원)</td>
	               <th>제주</th>
	              <td>제주</td>	              
	            </tr>
	            <tr>
	              <th>광주경</th>
	              <td>광주(경기)</td>
	               <th>수원</th>
	              <td>수원, 화성</td>
	               <th>제천</th>
	              <td>제천, 단양</td>	              
	            </tr>
	            <tr>
	              <th>괴산</th>
	              <td>괴산, 증평</td>
	               <th>순천</th>
	              <td>순천, 광양, 구례</td>
	               <th>진도</th>
	              <td>진도</td>	              
	            </tr>
	            <tr>
	              <th>구미</th>
	              <td>구미, 김천, 군위</td>
	               <th>아산</th>
	              <td>아산</td>
	               <th>진주</th>
	              <td>진주, 산청, 의령, 사천</td>	              
	            </tr>
	            <tr>
	              <th>군산</th>
	              <td>군산</td>
	               <th>안동</th>
	              <td>안동, 영주, 예천, 의성</td>
	               <th>창녕</th>
	              <td>창녕</td>	              
	            </tr>
	            <tr>
	              <th>김포</th>
	              <td>김포</td>
	               <th>안산</th>
	              <td>안산, 시흥</td>
	               <th>창원</th>
	              <td>창원, 함안</td>	              
	            </tr>
	            <tr>
	              <th>김해</th>
	              <td>김해</td>
	               <th>안양</th>
	              <td>안양, 군포, 의왕</td>
	               <th>세종</th>
	              <td>천안, 세종</td>	              
	            </tr>
	            <tr>
	              <th>나주</th>
	              <td>나주, 영암, 함평</td>
	               <th>양평</th>
	              <td>양평</td>
	               <th>철원</th>
	              <td>철원</td>	              
	            </tr>
	            <tr>
	              <th>남원</th>
	              <td>남원 순창, 장수</td>
	               <th>여수</th>
	              <td>여수</td>
	               <th>청송</th>
	              <td>청송, 영덕, 영양</td>	              
	            </tr>
	            <tr>
	              <th>남해</th>
	              <td>남해</td>
	               <th>연천</th>
	              <td>연천</td>
	               <th>청주</th>
	              <td>청주, 보은, 진천, 음성</td>	              
	            </tr>
	            <tr>
	              <th>논산</th>
	              <td>논산, 부여</td>
	               <th>영동</th>
	              <td>영동, 옥천</td>
	               <th>춘천</th>
	              <td>춘천, 화천, 홍천</td>	              
	            </tr>
	            <tr>
	              <th>당진</th>
	              <td>당진</td>
	               <th>영월</th>
	              <td>영월, 정선, 평창</td>
	               <th>충주</th>
	              <td>충주</td>	              
	            </tr>
	            <tr>
	              <th>대구</th>
	              <td>대구, 성주, 청도, 칠곡, 경산</td>
	               <th>영천</th>
	              <td>영천</td>
	               <th>태백</th>
	              <td>태백</td>	              
	            </tr>
	            <tr>
	              <th>대전</th>
	              <td>대전, 공주, 금산, 계룡</td>
	               <th>오산</th>
	              <td>오산</td>
	               <th>통영</th>
	              <td>통영, 거제, 고성</td>	              
	            </tr>
	            <tr>
	              <th>동해</th>
	              <td>동해</td>
	               <th>완도</th>
	              <td>완도</td>
	               <th>파주</th>
	              <td>파주</td>	              
	            </tr>
	            <tr>
	              <th>목포</th>
	              <td>목포, 무안, 신안</td>
	               <th>용인</th>
	              <td>용인</td>
	               <th>평택</th>
	              <td>평택, 안성</td>	              
	            </tr>
	            <tr>
	              <th>무주</th>
	              <td>무주</td>
	               <th>울릉</th>
	              <td>울릉</td>
	               <th>포천</th>
	              <td>포천</td>	              
	            </tr>
	            <tr>
	              <th>밀양</th>
	              <td>밀양</td>
	               <th>울산</th>
	              <td>울산</td>
	               <th>포항</th>
	              <td>포항</td>	              
	            </tr>
	            <tr>
	              <th>보령</th>
	              <td>보령, 청양</td>
	               <th>울진</th>
	              <td>울진</td>
	               <th>하동</th>
	              <td>하동</td>	              
	            </tr>
	            <tr>
	              <th>보성</th>
	              <td>보성, 고흥</td>
	               <th>원주</th>
	              <td>원주, 횡성</td>
	               <th>합천</th>
	              <td>합천</td>	              
	            </tr>
	             <tr>
	              <th>봉화</th>
	              <td>봉화</td>
	               <th>이천</th>
	              <td>이천, 여주</td>
	               <th>해남</th>
	              <td>해남, 강진, 장흥</td>	              
	            </tr>
	            
	            
	            
	          </tbody>
	        	
	        </table>
	      </div>
	    </div>
	    <div class="row">
	      <div class="col s12">
	        <h3>거리 체계</h3>
	        <table class="centered">
	           <thead>
	           	<tr>
	              <th>구분</th>
	              <th>1단계</th>
	              <th>2단계</th>
	              <th>3단계</th>
	              <th>4단계</th>
	              <th>5단계</th>
	              <th>6단계</th>
	              <th>7단계</th>
	            </tr>
	          </thead>
	          <tbody>
	            <tr>
	              <td>거리</td>
	              <td>시내</td>
	              <td>30km이내</td>
	              <td>50km이내</td>
	              <td>100km이내</td>
	              <td>200km이내</td>
	              <td>300km이내</td>
	              <td>300km초과</td>
	            </tr>
	          </tbody>
	        	
	        </table>
	      </div>
	    </div>
	    
	    <div class="row">
	      <div class="col s12">
	        <h3>속도 체계</h3>
	         <ul class="list-style1">	              
		          <li>단, "제공" 외 속도는 기존 해당속도 이용기간에 한하여 제공(신규 청약 및 기존 계약변경 불가)</li>
		        </ul>
	        <table class="centered">
	           <thead>
	           	<tr>
	              <th>구분</th>
	              <th>속도</th>
	              <th>기본회선</th>
	              <th>백본회선</th>	              
	              <th>인터넷</th>	            
	            </tr>
	          </thead>
	          <tbody>
	            <tr> <td rowspan="16">저속급<br></td> <td>2.4Kbps</td> <td>-</td> <td>-</td>  <td>-</td> </tr>
	            <tr> <td>9.6Kbps</td> <td>제공</td> <td>-</td>  <td>-</td>  </tr>
	            <tr> <td>56Kbps </td> <td>-  </td> <td>-</td>  <td>-</td> </tr>
	            <tr> <td>64Kbps </td> <td>제공</td> <td>-</td> <td>제공</td> </tr>
	            <tr> <td>128Kbps</td> <td>-  </td> <td>-</td>  <td>-</td>  </tr>
	            <tr> <td>192Kbps</td> <td>-  </td> <td>-</td>  <td>-</td>  </tr>
	            <tr> <td>256Kbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>320Kbps</td> <td>-  </td> <td>-</td>  <td>-</td>  </tr>
	            <tr> <td>384Kbps</td> <td>제공</td> <td>-</td>  <td>-</td>  </tr>
	            <tr> <td>448Kbps</td> <td>-  </td> <td>-</td>  <td>-</td>  </tr>
	            <tr> <td>512Kbps</td> <td>제공</td> <td>-</td> <td>제공</td>  </tr>
	            <tr> <td>640Kbps</td> <td>-  </td> <td>-</td> <td>-</td>  </tr>
	            <tr> <td>768Kbps</td> <td>제공</td> <td>-</td>  <td>-</td> </tr>
	            <tr> <td>960Kbps</td> <td>-  </td> <td>-</td>  <td>-</td>  </tr>
	            <tr> <td>1Mbps  </td> <td>제공</td> <td>-</td> <td>제공</td>  </tr>
	            <tr> <td>1.5Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td rowspan="21">중속급<br></td> <td>2Mbps</td> <td>제공</td> <td>제공</td> <td>제공</td> </tr>
	            <tr> <td>3Mbps</td> <td>-  </td> <td>-</td> <td>제공</td>  </tr>
	            <tr> <td>4Mbps</td> <td>제공</td> <td>-</td> <td>제공</td>  </tr>
	            <tr> <td>5Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>6Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>7Mbps</td> <td>제공  </td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>8Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>9Mbps</td> <td>제공 </td> <td>-</td>  <td>제공</td> </tr>
	            <tr> <td>10Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>12Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>14Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td> </tr>
	            <tr> <td>16Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>18Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td> </tr>
	            <tr> <td>20Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>22Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td> </tr>
	            <tr> <td>24Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>26Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td> </tr>
	            <tr> <td>28Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>   </tr>
	            <tr> <td>30Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>35Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>40Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td> </tr>
	            <tr> <td rowspan="28" class="border-end-bottom">고속급<br></td> <td>45Mbps</td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	            <tr> <td>50Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td></tr>
	            <tr> <td>55Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td> </tr>
	            <tr> <td>60Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>65Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td> </tr>
	            <tr> <td>70Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td> </tr>
	            <tr> <td>75Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>80Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>85Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td>  </tr>
	            <tr> <td>90Mbps</td> <td>제공</td> <td>-</td>  <td>제공</td></tr>
	            <tr> <td>95Mbps</td> <td>-  </td> <td>-  </td>  <td>-</td>  </tr>
	            <tr> <td>100Mbps</td> <td>제공</td> <td>제공</td>  <td>제공</td>   </tr>
	            <tr> <td>155Mbps</td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	            <tr> <td>200Mbps</td> <td>제공</td> <td>제공</td>  <td>제공</td> </tr>
	            <tr> <td>300Mbps</td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	            <tr> <td>400Mbps</td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	            <tr> <td>500Mbps</td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	            <tr> <td>600Mbps(622Mbps)</td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	            <tr> <td>700Mbps</td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	            <tr> <td>800Mbps</td> <td>제공</td> <td>제공</td> <td>제공</td> </tr>
	            <tr> <td>900Mbps</td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	            <tr> <td>1Gbps  </td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	            <tr> <td>1.5Gbps</td> <td>제공</td> <td>-  </td>  <td>제공</td> </tr>
	            <tr> <td>2Gbps  </td> <td>제공</td> <td>-  </td>  <td>제공</td>  </tr>
	            <tr> <td>2.5Gbps</td> <td>제공</td> <td>제공</td> <td>제공</td>  </tr>
	            <tr> <td>5Gbps  </td> <td>제공</td> <td>-  </td>  <td>제공</td>  </tr>
	            <tr> <td>7.5Gbps</td> <td>-  </td> <td>-  </td>  <td>제공</td> </tr>
	            <tr> <td>10Gbps </td> <td>제공</td> <td>제공</td>  <td>제공</td>  </tr>
	          </tbody>
	        	
	        </table>
	      </div>
	    </div>
	    
	  </div>
	</div>

	<jsp:include page="../common/footer.jsp" />

</html>