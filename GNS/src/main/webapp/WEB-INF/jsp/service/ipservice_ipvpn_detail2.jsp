<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div class="row">
		    <div class="col s12 l6">
		        <h3>요금 상세 조회</h3>
		        <ul class="list-style1">
		          <li>회선을 설치할 지역을 선택 후 조회하시면, 설치 거리에 따른 요금을 산정합니다.
		          	<ul>
		          		<li>찾으실 지역의 동/읍/면 이름을 공백 없이 입력해주세요.</li>
			        </ul>
		          </li>
		        </ul>
		         <div class="user-info-write">
			      <dl>
			        <dt style="width: 17%;">상위주소</dt>
			        <dd><input type="text" class="kt-input" title="상위주소" name="topStationText" id="topStationText"> <a href="javascript:" class="btn" onclick="stationSearch('T')">검색</a><br>
			        	<div class="select-wrap">
				            <select name="topStationCombo" id="topStationCombo" title="검색 옵션 선택">
				              <option value="" selected>상위주소 입력 후 검색 결과에서 상세 주소를 선택하세요</option>                      
				            </select>
				          </div>
			        </dd>
			      </dl>
			      <dl>
			        <dt style="width: 17%;">하위주소</dt>
			        <dd>
			          <input type="text" class="kt-input" title="하위주소" name="subStationText" id="subStationText"> <a href="javascript:" class="btn" onclick="stationSearch('S')">검색</a><br>
			          <div class="select-wrap">
			            <select name="subStationCombo" id="subStationCombo" title="검색 옵션 선택">
			              <option value="" selected>하위주소 입력 후 검색 결과에서 상세 주소를 선택하세요</option>                         
			            </select>
			          </div>
			        </dd>
			      </dl>
			      <dl>
			        <dt style="width: 17%;">속도</dt>
			        <dd>
						<div class="select-wrap">
				            <select name="speed" id="speed_ipvpn" title="검색 옵션 선택">
								<option>속도를 선택하세요.</option>
				            </select>
				         </div>
					</dd>
			      </dl>
			    </div>
			    <div class="center-align">
			    	<a href="javascript:" class="btn kt-red" onclick="calcul('basic');">요금조회</a>
			    </div>
		      </div>
		      
		      <div class="col s12 l6">
		        <ul class="list-style1" style="margin-top:43px;">
		          <li>요금조회
		          	<ul>
		          		<li>선택한 정보로 요금을 조회 할 수 있습니다.</li>
		          	</ul>
		          </li>
		        </ul>
	        	<div class="user-info-write">
			      <dl>
			        <dt style="width: 17%;">상위주소</dt>
			        <dd><label for="r_gbn_l" class="kt-label right-m" id="topStationView"></label></dd>
			      </dl>
			      <dl>
			        <dt style="width: 17%;">하위주소</dt>
			        <dd><label for="r_gbn_l" class="kt-label right-m" id="subStationView"></label></dd>
			      </dl>
			      <dl>
			        <dt style="width: 17%;">속도</dt>
			        <dd><label for="r_gbn_l" class="kt-label right-m" id="speedView"></label></dd>
			      </dl>
			    </div>
		        <br>
		        <ul class="list-style1">
		          <li>월 이용요금 (VAT별도, 단위:원)&nbsp;&nbsp;&nbsp;<span class="kt-red-text">※ 할인 및 설치비는 별도 적용됩니다.</span></li>
		        </ul>
		        <div class="user-info-write">
			      <dl>
			        <dt style="width: 20%;">시내/외 구분</dt>
			        <dd><label for="r_gbn_l" class="kt-label right-m" id="displayDistance"></label></dd>
			      </dl>
			      <dl>
			        <dt  style="width: 20%;">이용요금</dt>
			        <dd><label for="r_gbn_l" class="kt-label right-m" id="fee_ipvpn"></label></dd>
			      </dl>
			    </div>
		 </div>
	  </div>
	  
<!-- 임시변수 -->    
<input type="hidden" id="dist_code" name="dist_code" value=""/>
<input type="hidden" name="cctvYN" id="cctvYN" value="N">
<input type="hidden" name="internetYN" id="internetYN" value="N">