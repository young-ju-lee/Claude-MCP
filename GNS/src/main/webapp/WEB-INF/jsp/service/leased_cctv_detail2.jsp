<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="row">
		    <div class="col s12 l6">
		        <h3>cctv 요금 상세 조회</h3>
		        <ul class="list-style1">
		          <li>CCTV 계약,약정,속도 구분을 선택 후 조회하시면 요금을 산정합니다.</li>
		        </ul>
		         <div class="user-info-write">
			      <dl>
			        <dt style="width: 17%;">계약구분</dt>
			        <dd>
			        	<fieldset class="search-box">
					    	<div class="select-wrap">
					            <select id="distance" title="검색 옵션 선택">
					              <option value="" selected>선택하세요</option>                      
					            </select>
					        </div>
					    </fieldset>
			        </dd>
			      </dl>
			       <dl>
			        <dt style="width: 17%;">약정구분</dt>
			        <dd><fieldset class="search-box">
					    	<div class="select-wrap">
					            <select id="dist_code2" title="검색 옵션 선택">
					              <option value="" selected>선택하세요</option>                                
					            </select>
					          </div>
					       </fieldset>
			        </dd>
			      </dl>
			      <dl>
			        <dt style="width: 17%;">속도구분</dt>
			        <dd>
						<fieldset class="search-box">
					    	<div class="select-wrap">
					            <select id="cctv_speed" title="검색 옵션 선택">
					              <option value="" selected>선택하세요</option>                       
					            </select>
					         </div>
					     </fieldset>
					</dd>
			      </dl> 
			    
			    </div>
			    <div class="center-align">
			    	<a href="javascript:" class="btn kt-red" onclick="calcul('cctv');">요금조회</a>
			    </div>
		      </div>
		      
		      <div class="col s12 l6">
		        <ul class="list-style1" style="margin-top:22px;">
		          <li>요금조회
		          	<ul>
		          		<li>선택한 정보로 요금을 조회 할 수 있습니다.</li>
		          	</ul>
		          </li>
		        </ul>
		        	<div class="user-info-write">
				      <dl>
				        <dt style="width: 17%;">계약구분</dt>
				        <dd><label for="r_gbn_l" class="kt-label right-m" id="distance_View"></label></dd>
				      </dl>
				     <dl>
				        <dt style="width: 17%;">약정구분</dt>
				        <dd><label for="r_gbn_l" class="kt-label right-m" id="dist_code2_view"></label></dd>
				      </dl>
				      <dl>
				        <dt style="width: 17%;">속도구분</dt>
				        <dd><label for="r_gbn_l" class="kt-label right-m" id="cctv_speed_view"></label></dd>
				      </dl>
				      
				    </div>
		        <ul class="list-style1">
		          <li>이용요금 (VAT별도, 단위:원)&nbsp;&nbsp;&nbsp;<span class="kt-red-text">※ 할인 및 설치비는 별도 적용됩니다.</span></li>
		        </ul>
		        <div class="user-info-write">
			      <dl>
			        <dt style="width: 17%;">이용요금</dt>
			        <dd><label for="r_gbn_l" class="kt-label right-m" id="fee_cctv"></label></dd>
			      </dl>
			    </div>
		 </div>
	  </div>
	  
