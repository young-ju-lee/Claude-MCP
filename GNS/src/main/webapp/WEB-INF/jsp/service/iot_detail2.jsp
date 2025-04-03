<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
	    <div class="col s12 l6">
	        <h3>IoT 서비스 요금 상세 조회</h3>
	        <ul class="list-style1">
	          <li>IoT요금제를 선택 후 조회하시면, 요금제에 따른 요금을 산정합니다 </li>
	        </ul>
	         <div class="user-info-write">
		      <dl>
		        <dt>요금제</dt>
		        <dd>
			        <div class="select-wrap">
		              <select name="speed2"  id="speed2" title="검색 옵션 선택">
						<option>선택하세요.</option>
		              </select>
		            </div>
		        </dd>
	      </dl>
	      
	    </div>
	    <div class="center-align">
			<a href="javascript:" class="btn kt-red" onclick="calcul('IoT');">요금조회</a>
	    </div>
      </div>
      
      <div class="col s12 l6">
        <br>
        <ul class="list-style1">
          <li>요금조회
          	<ul>
          		<li>선택한 정보로 요금을 조회 할 수 있습니다.</li>
          	</ul>
          </li>
        </ul>
        	<div class="user-info-write">
		      <dl>
		        <dt>요금제</dt>
		        <dd><label for="r_gbn_l" class="kt-label right-m" id="speedView2"></label></dd>
		      </dl>
		    </div>
        <ul class="list-style1">
          <li>월 이용요금 (VAT별도, 단위:원)&nbsp;&nbsp;&nbsp;<span class="kt-red-text">※ 할인 및 설치비는 별도 적용됩니다.</span></li>
        </ul>
        <div class="user-info-write">
	      <dl>
	        <dt style="width: 17%;">이용요금</dt>
	        <dd><label for="r_gbn_l" class="kt-label right-m" id="fee_IoT"></label></dd>
	        
	      </dl>
	    </div>
	 </div>
  </div>
  
