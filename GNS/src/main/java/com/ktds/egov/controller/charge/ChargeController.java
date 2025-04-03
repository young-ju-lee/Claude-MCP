/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : ChargeController.java
* Overview    : 이용요금계산 Controller
* Description : 이용요금계산 및 권역거리 화면으로 이동합니다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.controller.charge;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.egov.controller.main.MainController;
import com.ktds.egov.service.member.StatService;
import com.ktds.egov.vo.charge.ChargeVo;
import com.ktds.egov.vo.member.MenuStateVo;
import com.ktds.framework.core.mvc.BaseService;
import com.ktds.framework.util.Global;

@Controller
public class ChargeController {
	protected final Logger logger = LogManager.getLogger(MainController.class);

	//메뉴접속통계 적용
    @Resource(name = "StatService")
    private StatService statService;
	
	@Resource(name="chargeService")
	private BaseService<ChargeVo> chargeService;
	
	@RequestMapping(value = "/charge/reckoning", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getreckoning(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {
		
		logger.info("이용요금계산 /charge/reckoning");
		String menuId = ""; 
		menuId = "302030";  //이용요금계산
		
		MenuStateVo menuVo = new MenuStateVo();
		menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"),""));
		menuVo.setMenu_id(menuId);		
		try {
			this.statService.insertMenuState(menuVo);
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mapper", "goodsList");

		// 데이터 셋팅
		try {
			mav.addObject("goodsList", chargeService.select(model, map));
		} catch (Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/charge/reckoning");
		
		return mav;
	}
	
	@RequestMapping(value = "/charge/reckoning1", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getreckoning1(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// mybatis select ID값 셋팅
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mapper", "goodsList");

		// 데이터 셋팅
		try {
			mav.addObject("goodsList", chargeService.select(model, map));
		} catch (Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/charge/reckoning1");
		
		return mav;
	}
	//
	@RequestMapping(value = "/charge/main", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getGoods(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// mybatis select ID값 셋팅
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mapper", "goodsList");

		// 데이터 셋팅
		try {
			mav.addObject("goodsList", chargeService.select(model, map));
		} catch (Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/charge/main");
		
		return mav;
	}
	
	@RequestMapping(value = "/charge/speed", method = { RequestMethod.POST,	RequestMethod.GET })
	@ResponseBody
	public String getSpeed(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {
		
		JSONObject result = new JSONObject();
		
		try {
			// 한글 파라미터 변환
			String svc_type = URLDecoder.decode(model.getSvc_type(), "utf-8");
			model.setSvc_type(svc_type);

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("mapper", "speedList");
			// 데이터 조회
			List<ChargeVo> speedList = chargeService.select(model, map);
			
			JSONArray jarray = new JSONArray();
			// 조회된 데이터를 JSONARRAY에 넣음.

			for(int i=0; i<speedList.size();i++){
				JSONObject jobj = new JSONObject();
				jobj.put("speed", speedList.get(i).getSpeed());
				jarray.add(jobj);
			}
			// 화면으로 전송을 위해 JSONARRAY 를 JSON OBJECT 에 담음 
			result.put("speed", jarray);
		} catch(Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		return result.toString();
	}
	
	@RequestMapping(value = "/charge/stationSearch", method = { RequestMethod.POST,	RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> getStation(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {
		
		JSONObject result = new JSONObject();
		
		try {
			// 한글 파라미터 변환
			String addr = URLDecoder.decode(model.getAddr(), "utf-8");
			model.setAddr(addr);
			//  mybatis select ID값 셋팅
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("mapper", "stationList");
			// 데이터 조회
			List<ChargeVo> stationList = chargeService.select(model, map);
			
			JSONArray jarray = new JSONArray();
			//2019.12.06 주소 버그 긴급수정 500건 이상이면 실패 0건인 경우는 주소 없음 alert창
			if(stationList.size() == 0 ){
				result.put("result", "addr_zero");
			}else if(stationList.size() > 499 ){
				result.put("result", "addr_fail");
			}else{
				// 조회된 데이터를 JSONARRAY에 넣음.
				for(int i=0; i<stationList.size();i++){
					logger.info(stationList.get(i).getAddr());
					JSONObject jobj = new JSONObject();
					jobj.put("addr", stationList.get(i).getAddr());
					jobj.put("icis_2", stationList.get(i).getIcis_2());
					jarray.add(jobj);
					
					result.put("stationList", jarray);
			}
			
			}
		} catch(Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		// 한글 전송을 위한 셋팅 
		 HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	        return new ResponseEntity<String>(result.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/charge/distanceSearch", method = { RequestMethod.POST,	RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> getDistance(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {	
		JSONObject jobj = new JSONObject();
		// 데이터 조회
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("mapper", "distanceSearch");
			List<ChargeVo> distanceList = chargeService.select(model, map);
			
			for(int i=0; i<1;i++){		//중복데이터 없다는 조건(List형식으로 리턴받는 BASESERVICE때문,,
				jobj.put("dist_value", distanceList.get(i).getDist_value());
				jobj.put("dist_code", distanceList.get(i).getDist_code());
				
				if(model.getSvc_type().equals("IP-VPN")) {
					if(distanceList.get(i).getDist_code().equals("SN00")) {
						jobj.put("distance", "시내");
					} else {
						jobj.put("distance", "시외");
					}
					
				}
			}
		} catch(Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		// 단건 데이터기 때문에 ARRAY에 담지않고 OBJECT를 리턴함.
		// 한글 전송을 위한 셋팅 
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
        return new ResponseEntity<String>(jobj.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/charge/calculation", method = { RequestMethod.POST,	RequestMethod.GET })
	@ResponseBody
	public String getCalculation(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {
		
		JSONObject jobj = new JSONObject();
		try {
			// 한글 파라미터 변환
			String svc_type = URLDecoder.decode(model.getSvc_type(), "utf-8");

			if(svc_type.equals("인터넷")){
				model.setSvc_type_ename("internet");	//DB 동적쿼리를 위한 파라미터설정
			}else if(svc_type.equals("CCTV")){
				
				model.setSvc_type_ename("CCTV");	//DB 동적쿼리를 위한 파라미터설정
				model.setSpeed(URLDecoder.decode(model.getCctv_speed(), "utf-8"));	// speed 셋팅 cctv일시 cctv_speed 나머지 speed
				model.setDistance(URLDecoder.decode(model.getDistance(), "utf-8"));	// distince 셋팅 한글변환
				model.setDist_code(URLDecoder.decode(model.getDist_code(), "utf-8"));
				
			}else if(svc_type.equals("IoT")){
				model.setDist_code("");				
			}
			model.setSvc_type(svc_type);
			//  mybatis select ID값 셋팅
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("mapper", "calculation");
			// 데이터 조회
			List<ChargeVo> calculation = chargeService.select(model, map);

			for(int i=0; i<1;i++){		//중복데이터 없다는 조건(List형식으로 리턴받는 BASESERVICE때문,,
				jobj.put("fee", calculation.get(i).getFee());
				//jobj.put("distance", calculation.get(i).getDistance());
			}
		} catch(Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		// 단건 데이터기 때문에 ARRAY에 담지않고 OBJECT를 리턴함.
		return jobj.toString();
	}
	
	@RequestMapping(value = "/charge/cctvsetting", method = { RequestMethod.POST,	RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> getCctvMenuList(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {
		
		
		
		JSONObject result = new JSONObject();
		
		try {
			// 한글 파라미터 변환
			String svc_type = URLDecoder.decode(model.getSvc_type(), "utf-8");
			model.setSvc_type(svc_type);
			
			//  mybatis select ID값 셋팅
			HashMap<String, String> map = new HashMap<String, String>();		
			map.put("mapper", "distanceMenu");
			List<ChargeVo> distanceList = chargeService.select(model, map);
			map.put("mapper", "contractPeriodMenu");
			List<ChargeVo> contractList = chargeService.select(model, map);
			map.put("mapper", "speedMenu");
			List<ChargeVo> speedList = chargeService.select(model, map);			
			
			JSONArray distanceArray = new JSONArray();
			JSONArray contractArray = new JSONArray();
			JSONArray speedArray = new JSONArray();
			
			// 조회된 데이터를 JSONARRAY에 넣음.
			/*for(int i=0; i<feeList.size();i++){
				JSONObject jobj = new JSONObject();
				jobj.put("fee_basis", feeList.get(i).getFee_basis());
				jobj.put("distance_cd", feeList.get(i).getDistance_cd());
				feeArray.add(jobj);
			}*/
			for(int i=0; i<distanceList.size();i++){
				JSONObject jobj = new JSONObject();
				jobj.put("distance", distanceList.get(i).getDistance());
				distanceArray.add(jobj);
			}
			for(int i=0; i<contractList.size();i++){
				JSONObject jobj = new JSONObject();				
				jobj.put("dist_code", contractList.get(i).getDist_code());
				contractArray.add(jobj);
			}
			for(int i=0; i<speedList.size();i++){
				JSONObject jobj = new JSONObject();
				jobj.put("speed", speedList.get(i).getSpeed());
				speedArray.add(jobj);
			}
			
			// 화면으로 전송을 위해 JSONARRAY 를 JSON OBJECT 에 담음 
			
			//result.put("feeArray", feeArray);
			result.put("distanceArray", distanceArray);
			result.put("contractArray", contractArray);
			result.put("speedArray", speedArray);
		}catch(Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		// 한글 전송을 위한 셋팅 
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
        return new ResponseEntity<String>(result.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/charge/area", method = { RequestMethod.POST,	RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> getArea(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {
		
		JSONObject result = new JSONObject();
		
		try {
		//  mybatis select ID값 셋팅
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("mapper", "areaList");
			// 데이터 조회
			List<ChargeVo> areaList = chargeService.select(model, map);
			
			JSONArray jarray = new JSONArray();
			// 조회된 데이터를 JSONARRAY에 넣음.
			for(int i=0; i<areaList.size();i++){
				JSONObject jobj = new JSONObject();
				jobj.put("area", areaList.get(i).getTop_nat());
				jarray.add(jobj);
				// 화면으로 전송을 위해 JSONARRAY 를 JSON OBJECT 에 담음 
				result.put("area", jarray);
			}
		} catch(Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
        return new ResponseEntity<String>(result.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/charge/dist", method = { RequestMethod.POST,	RequestMethod.GET })
	@ResponseBody
	public String getDist(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, ChargeVo model) throws RuntimeException {
		
		JSONObject jobj = new JSONObject();
		JSONObject result = new JSONObject();
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("mapper", "areaDist");
			
			String top_nat = URLDecoder.decode(model.getTop_nat(), "utf-8").replace("& #40;", "(").replace( "& #41;",")");
			String botton_nat = URLDecoder.decode(model.getBotton_nat(), "utf-8").replace("& #40;", "(").replace( "& #41;",")");

			
			model.setTop_nat(top_nat);
			model.setBotton_nat(botton_nat);
			
			// 데이터 조회
			List<ChargeVo> areaList = chargeService.select(model, map);
			
			// 조회된 데이터를 JSONARRAY에 넣음.
			
			jobj.put("dist", areaList.get(0).getDist_val());
			
			result.put("dist", jobj);
		} catch(Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
/*		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
        return new ResponseEntity<String>(result.toString(), responseHeaders, HttpStatus.CREATED);*/
		return jobj.toString();
	}
}
