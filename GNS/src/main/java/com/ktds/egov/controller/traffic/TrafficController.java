/* POR version 1.0
 * Copyright @ 2013 kt Inc. All rights reserved.
 * This is a proprietary software of kt Inc, and you may not use this file except in compliance
 * with license agreement with kt Inc. Any redistribution or use of this software, with or without
 * modification shall be strictly prohibited without prior written approval of kt Inc, and
 * the copyright notice above does not evidence any actual or intended publication of such software.
 *
 * File Name   : TrafficController.java
 * Overview    : 추가서비스 메뉴 Controller
 * Description : 추가서비스 화면 으로 이동한다.
 * *=====================================================================
 * Version    변경  일자       개발자               설명
 * ---------- ---------- --------- --------------------------------------
 * 1.0.0.0    2013/11/11       강훈병            신규개발
 *=====================================================================
 */

package com.ktds.egov.controller.traffic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import com.ktds.egov.service.traffic.TrafficService;
import com.ktds.egov.vo.member.MenuStateVo;
import com.ktds.egov.vo.traffic.TrafficVo;
import com.ktds.framework.util.Global;

@Controller
public class TrafficController {

	protected final Logger logger = LogManager.getLogger(MainController.class);

	// 메뉴접속통계 적용
	@Resource(name = "StatService")
	private StatService statService;

	// 트래픽관리 서비스
	@Resource(name = "trafficService")
	private TrafficService trafficService;

	@RequestMapping(value = "/traffic/select_llnum", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> select_llnum(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws RuntimeException {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

		String ll_num = (String) session.getAttribute("ll_id");
		// if(ll_num == null || ll_num.equals("")){
		// return new ResponseEntity<String>("", responseHeaders,
		// HttpStatus.CREATED);
		// }

		JSONObject result = new JSONObject();

		try {
			// 해당 사용자의 전체 전용회선번호 를 가지고 옴
			List<TrafficVo> llnumList = trafficService.selectAllLLnum(ll_num);
			JSONArray jarray = new JSONArray();
			// 조회된 데이터를 JSONARRAY에 넣음.
			for (int i = 0; i < llnumList.size(); i++) {
				JSONObject jobj = new JSONObject();
				jobj.put("llnum", llnumList.get(i).getLlnum());
				jarray.add(jobj);
			}
			// 화면으로 전송을 위해 JSONARRAY 를 JSON OBJECT 에 담음
			result.put("llnum", jarray);
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}

		logger.info(result.toString());
		return new ResponseEntity<String>(result.toString(), responseHeaders,
				HttpStatus.CREATED);
		// return result.toString();
	}

	@RequestMapping(value = "/traffic/select_node", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> select_node(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws RuntimeException {

		JSONObject result = new JSONObject();

		// 해당 사용자의 전체 전용회선번호 를 가지고 옴
		try {
			List<TrafficVo> nodeList = trafficService.selectAllNode();

			JSONArray jarray = new JSONArray();
			// 조회된 데이터를 JSONARRAY에 넣음.
			for (int i = 0; i < nodeList.size(); i++) {
				JSONObject jobj = new JSONObject();
				jobj.put("node", nodeList.get(i).getNescode());
				jobj.put("alias", nodeList.get(i).getNealias());
				jarray.add(jobj);
			}
			// 화면으로 전송을 위해 JSONARRAY 를 JSON OBJECT 에 담음

			result.put("nodes", jarray);
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}

		logger.info(result.toString());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(result.toString(), responseHeaders,
				HttpStatus.CREATED);
	}
	
		
	
	@RequestMapping(value = "/traffic/check_llnum", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> check_llnum(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, TrafficVo traffic)
			throws RuntimeException {
		
		JSONObject result = new JSONObject();
		
		try {
			List<TrafficVo> list = trafficService.checkllnum(traffic);
			int flag = 0;
			if(list.size() == 0) {
			
				flag = 1;
			}

			result.put("result", flag);
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(result.toString(), responseHeaders,
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/traffic/traffic1", method = { RequestMethod.POST })
	public ModelAndView getTraffic1(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			TrafficVo traffic) throws RuntimeException {
	
						
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		if (session.getAttribute("user_id") == null
				|| session.getAttribute("user_id").equals("")) {
			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");
			return mav;
		}
		
		//20171124 모의해킹 - 클라이언트 기반 인증처리
				if(session.getAttribute("user_id") != null && session.getAttribute("user_auth_id").equals("10"))
				{
					
					mav.addObject("redirect_url", "/error.do");	
					mav.setViewName("/redirectPage");
					return mav;
				}

		logger.info(" ### 조회조건 : " + traffic.getSearch_llnum());
		logger.info(" ### 노드 : " + traffic.getSearch_nat());

		logger.info("가입자선택/traffic/traffic1");
		logger.info("getReSearchFlag :" + traffic.getResearchflag());

		if (traffic.getResearchflag() == null) {
			// 메뉴통계
			String menuId = "";
			menuId = "401010";

			MenuStateVo menuVo = new MenuStateVo();
			menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"), ""));
			menuVo.setMenu_id(menuId);
			try {
				this.statService.insertMenuState(menuVo);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		}

		String ll_num = "";
		if (session.getAttribute("user_auth_id").equals("20")) {
			logger.info("ll_num :" + ll_num);
			// 일반가입자 경우 세션 전용회선번호가 전용회선번호로 셋팅
			if(traffic.getSearch_llnum() == null) {
				ll_num = (String) session.getAttribute("ll_id");
				// 데이터 셋팅
				traffic.setSearch_llnum(ll_num);
			}
			
		}

		if ((traffic.getSearch_nat() == null)
				&& (traffic.getSearch_llnum() == null)) {
			// 조건을 입력하세요
			mav.addObject("researchflag", "E");
		} else {
			mav.addObject("search_llnum", traffic.getSearch_llnum());
			mav.addObject("search_nat", traffic.getSearch_nat());
			// mav.addObject("search_llnum_list",trafficService.selectAllLLnum(traffic));

			try {
				if (request.getSession().getAttribute("user_auth_id")
						.equals("20")) {
					// 전용회선 가입자인 경우 (해당 전용회선의 모든 내역 조회)

					mav.addObject("traffic_list",
							trafficService.selectllnumList2(traffic));
				} else {
					mav.addObject("traffic_list",
							trafficService.selectllnumList3(traffic));
					mav.addObject("search_nat", traffic.getSearch_nat());
				}
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		}

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/traffic/traffic1");
		return mav;
	}
	
	@RequestMapping(value = "/traffic/traffic4", method = { RequestMethod.POST })
	public ModelAndView getTraffic4(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			TrafficVo traffic) throws RuntimeException {
	
						
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		if (!session.getAttribute("user_auth_id").equals("40")) {
			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");
			return mav;
		}
			

		logger.info(" ### 조회조건4 : " + traffic.getSearch_llnum_admin());
		logger.info(" ### 노드4 : " + traffic.getSearch_nat());

		if (traffic.getResearchflag() == null) {
			// 메뉴통계
			String menuId = "";
			menuId = "401010";

			MenuStateVo menuVo = new MenuStateVo();
			menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"), ""));
			menuVo.setMenu_id(menuId);
			try {
				this.statService.insertMenuState(menuVo);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		}

		
		logger.info("가입자선택/traffic/traffic4");
		logger.info("getReSearchFlag :" + traffic.getResearchflag());


		
			mav.addObject("search_llnum", traffic.getSearch_llnum_admin());
			mav.addObject("search_nat", traffic.getSearch_nat());
			

			try {
			
					
						mav.addObject("traffic_list",
								trafficService.selectllnumList_admin(traffic));
						mav.addObject("search_nat", traffic.getSearch_nat());
				
				
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		

	    	// 보여질 View 페이지를 설정한다.
		mav.setViewName("/traffic/traffic1");
		return mav;
	}

	@RequestMapping(value = "/traffic/traffic2", method = { RequestMethod.POST })
	public ModelAndView getTraffic2(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			TrafficVo traffic) throws RuntimeException {

		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 세션 권한 확인
		if (session.getAttribute("user_id") == null
				|| session.getAttribute("user_id").equals("")) {
			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");
			return mav;
		}
		
		//20171124 모의해킹 - 클라이언트 기반 인증처리
				if(session.getAttribute("user_id") != null && session.getAttribute("user_auth_id").equals("10"))
				{					
					mav.addObject("redirect_url", "/error.do");	
					mav.setViewName("/redirectPage");
					return mav;
				}

		logger.info("/traffic/traffic2 getSearch_llnum : "
				+ traffic.getSearch_llnum());
		logger.info("/traffic/traffic2 getResearchflag : "
				+ traffic.getResearchflag());

		if (traffic.getResearchflag() == null
				|| traffic.getResearchflag().equals("")) {
			// 메뉴통계
			String menuId = "";

			menuId = "401020";

			MenuStateVo menuVo = new MenuStateVo();
			menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"), ""));
			menuVo.setMenu_id(menuId);
			try {
				this.statService.insertMenuState(menuVo);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		}

		String ll_num = "";

		logger.info("getSearch_llnum :" + traffic.getSearch_llnum());
		if (traffic.getSearch_llnum() == null) {
			ll_num = (String) session.getAttribute("ll_id");
			// 데이터 셋팅

			logger.info("ll_num :" + ll_num);
			traffic.setSearch_llnum(ll_num);
			traffic.setTraffic_type("R");
		}

		if (traffic.getTraffic_type() == null) {
			traffic.setTraffic_type("R");
		}
		logger.info("getTraffic_type :" + traffic.getTraffic_type());

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String today = sd.format(new Date());
		String before_date_from = "";
		String before_date_to = "";
		if (traffic.getSearch_from() == null
				|| traffic.getSearch_from().equals("")) {
			traffic.setSearch_from(today.replaceAll("-", ""));
			mav.addObject("search_from", today);
		} else {
			before_date_from = traffic.getSearch_from().toString();
			traffic.setSearch_from(before_date_from.replaceAll("-", ""));
			mav.addObject("search_from", before_date_from);
		}

		if (traffic.getSearch_to() == null || traffic.getSearch_to().equals("")) {
			traffic.setSearch_to(today.replaceAll("-", ""));
			mav.addObject("search_to", today);
		} else {
			before_date_to = traffic.getSearch_to().toString();
			traffic.setSearch_to(before_date_to.replaceAll("-", ""));
			mav.addObject("search_to", before_date_to);
		}

		mav.addObject("search_llnum", traffic.getSearch_llnum());
		mav.addObject("search_nat", traffic.getSearch_nat());
		mav.addObject("traffic_type", traffic.getTraffic_type());
		mav.addObject("page", traffic.getPage());

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/traffic/traffic2");

		logger.info("############ OK");
		return mav;
	}

	//<!-- 2018.12.21 admin 전체회선 조회 쿼리 추가 트래픽조회 -->  
	@RequestMapping(value = "/traffic/traffic5", method = { RequestMethod.POST })
	public ModelAndView getTraffic5(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			TrafficVo traffic) throws RuntimeException {

		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 세션 권한 확인
		if (!session.getAttribute("user_auth_id").equals("40")) {
			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");
			return mav;
		}
		

		logger.info("/traffic/traffic5 getSearch_llnum : "
				+ traffic.getSearch_llnum_admin());
		

		if (traffic.getResearchflag() == null
				|| traffic.getResearchflag().equals("")) {
			// 메뉴통계
			String menuId = "";

			menuId = "401020";

			MenuStateVo menuVo = new MenuStateVo();
			menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"), ""));
			menuVo.setMenu_id(menuId);
			try {
				this.statService.insertMenuState(menuVo);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		}

		String ll_num = "";

		logger.info("getSearch_llnum :" + traffic.getSearch_llnum());
		logger.info("getSearch_llnum_admin :" + traffic.getSearch_llnum_admin());
	
		
		if (traffic.getSearch_llnum_admin() == null) {
			ll_num = (String) session.getAttribute("ll_id");
			// 데이터 셋팅

			logger.info("ll_num :" + ll_num);
			traffic.setSearch_llnum(ll_num);
			traffic.setTraffic_type("R");
		}

		if (traffic.getTraffic_type() == null) {
			traffic.setTraffic_type("R");
		}
		logger.info("getTraffic_type :" + traffic.getTraffic_type());

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String today = sd.format(new Date());
		String before_date_from = "";
		String before_date_to = "";
		if (traffic.getSearch_from() == null
				|| traffic.getSearch_from().equals("")) {
			traffic.setSearch_from(today.replaceAll("-", ""));
			mav.addObject("search_from", today);
		} else {
			before_date_from = traffic.getSearch_from().toString();
			traffic.setSearch_from(before_date_from.replaceAll("-", ""));
			mav.addObject("search_from", before_date_from);
		}

		if (traffic.getSearch_to() == null || traffic.getSearch_to().equals("")) {
			traffic.setSearch_to(today.replaceAll("-", ""));
			mav.addObject("search_to", today);
		} else {
			before_date_to = traffic.getSearch_to().toString();
			traffic.setSearch_to(before_date_to.replaceAll("-", ""));
			mav.addObject("search_to", before_date_to);
		}

		mav.addObject("search_llnum", traffic.getSearch_llnum_admin());
		mav.addObject("search_nat", traffic.getSearch_nat());
		mav.addObject("traffic_type", traffic.getTraffic_type());
		mav.addObject("page", traffic.getPage());

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/traffic/traffic2");

		logger.info("############ OK");
		return mav;
	}
	
	@RequestMapping(value = "/traffic/searchtraffic", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> getSearchTraffic(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			TrafficVo traffic) throws RuntimeException {
		// 2023-07-14 : 모의해킹 취약점 개선
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		//세션 사용자 확인
		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){
			return new ResponseEntity<String>("", responseHeaders, HttpStatus.CREATED);
		}
		//세션 권한 확인 
		if (!session.getAttribute("user_auth_id").equals("40") && !session.getAttribute("user_auth_id").equals("30") && !session.getAttribute("user_auth_id").equals("20") ) {
			return new ResponseEntity<String>("", responseHeaders, HttpStatus.CREATED);
		}
		logger.info("############################");
		logger.info(traffic.getSearch_from());
		logger.info(traffic.getSearch_to());
		logger.info(traffic.getSearch_llnum());
		logger.info("############################");

		logger.info("트래픽조회/traffic/searchtraffic");
		String ll_num = "";

		JSONObject obj = new JSONObject();

		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		logger.info("getSearch_llnum :" + traffic.getSearch_llnum());
		if (traffic.getSearch_llnum() == null) {
			ll_num = (String) session.getAttribute("ll_id");
			// 데이터 셋팅

			logger.info("ll_num :" + ll_num);
			traffic.setSearch_llnum(ll_num);
			traffic.setTraffic_type("R");
		}

		if (traffic.getTraffic_type() == null) {
			traffic.setTraffic_type("R");
		}
		logger.info("getTraffic_type :" + traffic.getTraffic_type());

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String today = sd.format(new Date());
		String before_date_from = "";
		String before_date_to = "";
		if (traffic.getSearch_from() == null
				|| traffic.getSearch_from().equals("")) {
			traffic.setSearch_from(today.replaceAll("-", ""));
		} else {
			before_date_from = traffic.getSearch_from().toString();
			traffic.setSearch_from(before_date_from.replaceAll("-", ""));
		}

		if (traffic.getSearch_to() == null || traffic.getSearch_to().equals("")) {
			traffic.setSearch_to(today.replaceAll("-", ""));
		} else {
			before_date_to = traffic.getSearch_to().toString();
			traffic.setSearch_to(before_date_to.replaceAll("-", ""));
		}

		logger.info("call service :" + traffic.getTraffic_type());

		List<TrafficVo> list = null;
		/* 
		traffic.setLimit(30);
		if (traffic.getPage().equals("1")) {
			traffic.setOffSet(0);
		} else {
			int page = Integer.parseInt(traffic.getPage());
			int offset = page * 30 - 30;
			traffic.setOffSet(offset);
		}
		*/
		try {
			if (traffic.getTraffic_type().equals("R")) {
				logger.info("selectTrafficRealList call :"
						+ traffic.getTraffic_type());
				list = trafficService.selectTrafficRealList(traffic);
			} else if (traffic.getTraffic_type().equals("H")) {
				logger.info("selectTrafficHourList call :"
						+ traffic.getTraffic_type());
				list = trafficService.selectTrafficHourList(traffic);
			} else if (traffic.getTraffic_type().equals("D")) {

				List<TrafficVo> list_hour = trafficService
						.selectTrafficHourList(traffic);
				list = trafficService.selectTrafficDayList(traffic);

				for (int i = 0; i < list.size(); i++) {

					String time = list.get(i).getCollect_time()
							.substring(0, 10);

					long max_inbps = 0;
					String max_in_hour = "";

					long max_outbps = 0;
					String max_out_hour = "";
					for (int j = 0; j < list_hour.size(); j++) {
						String _time = list_hour.get(j).getCollect_time()
								.substring(0, 10);
						if (time.equals(_time)) {
							if (max_inbps < list_hour.get(j).getInbps()) {
								max_inbps = list_hour.get(j).getInbps();
								max_in_hour = list_hour.get(j)
										.getCollect_time().substring(11);
							}

							if (max_outbps < list_hour.get(j).getOutbps()) {
								max_outbps = list_hour.get(j).getOutbps();
								max_out_hour = list_hour.get(j)
										.getCollect_time().substring(11);
							}
						}
					}

					list.get(i).setMax_in_hour(max_in_hour);
					list.get(i).setMax_out_hour(max_out_hour);
				}

				logger.info("selectTrafficDayList call :"
						+ traffic.getTraffic_type());

			} else if (traffic.getTraffic_type().equals("M")) {

				List<TrafficVo> list_day = trafficService
						.selectTrafficDayList(traffic);
				list = trafficService.selectTrafficMonthList(traffic);

				// 최번일 구함 ///////////////////
				for (int i = 0; i < list.size(); i++) {

					String time = list.get(i).getCollect_time().substring(0, 7);

					long max_inbps = 0;
					String max_in_day = "";

					long max_outbps = 0;
					String max_out_day = "";
					for (int j = 0; j < list_day.size(); j++) {
						String _time = list_day.get(j).getCollect_time()
								.substring(0, 7);
						if (time.equals(_time)) {
							if (max_inbps < list_day.get(j).getInbps()) {
								max_inbps = list_day.get(j).getInbps();
								max_in_day = list_day.get(j).getCollect_time()
										.substring(8);
							}

							if (max_outbps < list_day.get(j).getOutbps()) {
								max_outbps = list_day.get(j).getOutbps();
								max_out_day = list_day.get(j).getCollect_time()
										.substring(8);
							}
						}
					}

					list.get(i).setMax_in_day(max_in_day);
					list.get(i).setMax_out_day(max_out_day);
				}
				// ////////////////////////////////
				logger.info("selectTrafficMonthList call :"
						+ traffic.getTraffic_type());

			} else {
				logger.info("No service :" + traffic.getTraffic_type());
			}

			obj.put("traffic_list", list);
			int result = 0;
			if (list.size() == 0) {
				result = 1; // 조회결과값이 없을때
			} else {
			}
			obj.put("result", result);
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		logger.info(obj.toString());

		return new ResponseEntity<String>(obj.toString(), responseHeaders,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/traffic/traffic3", method = { RequestMethod.POST })
	public ModelAndView getTraffic3(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			TrafficVo traffic) throws RuntimeException {

		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 세션 권한 확인
		if (session.getAttribute("user_id") == null
				|| session.getAttribute("user_id").equals("")) {
			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");
			return mav;
		}
		
		//20171124 모의해킹 - 클라이언트 기반 인증처리
		if(session.getAttribute("user_id") != null && session.getAttribute("user_auth_id").equals("10"))
		{					
			mav.addObject("redirect_url", "/error.do");	
			mav.setViewName("/redirectPage");
			return mav;
		}

		logger.info("트래팩조회/traffic/traffic3");

		// 메뉴통계
		String menuId = "";
		String ll_num = "";
		if (traffic.getResearchflag() == null) {
			menuId = "401030";

			MenuStateVo menuVo = new MenuStateVo();
			menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"), ""));
			menuVo.setMenu_id(menuId);
			try {
				this.statService.insertMenuState(menuVo);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		}

		if (traffic.getSearch_llnum() == null) {
			ll_num = (String) session.getAttribute("ll_id");
			// 데이터 셋팅

			logger.info("ll_num :" + ll_num);
			traffic.setSearch_llnum(ll_num);
			traffic.setTraffic_type("R");
		}

		logger.info("getTraffic_type :" + traffic.getTraffic_type());

		if (traffic.getTraffic_type() == null
				|| traffic.getTraffic_type().equals("")) {
			traffic.setTraffic_type("R");
		}

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String today = sd.format(new Date());
		String before_date_from = "";
		String before_date_to = "";
		if (traffic.getSearch_from() == null
				|| traffic.getSearch_from().equals("")) {
			traffic.setSearch_from(today.replaceAll("-", ""));
			mav.addObject("search_from", today);
		} else {
			before_date_from = traffic.getSearch_from().toString();
			traffic.setSearch_from(before_date_from.replaceAll("-", ""));
			mav.addObject("search_from", before_date_from);
		}

		if (traffic.getSearch_to() == null || traffic.getSearch_to().equals("")) {
			traffic.setSearch_to(today.replaceAll("-", ""));
			mav.addObject("search_to", today);
		} else {
			before_date_to = traffic.getSearch_to().toString();
			traffic.setSearch_to(before_date_to.replaceAll("-", ""));
			mav.addObject("search_to", before_date_to);
		}

		mav.addObject("search_llnum", traffic.getSearch_llnum());
		mav.addObject("search_nat", traffic.getSearch_nat());
		mav.addObject("traffic_type", traffic.getTraffic_type());

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/traffic/traffic3");
		logger.info("############ OK");
		return mav;
	}
	//<!-- 2018.12.21 admin 전체회선 조회 쿼리 추가 그래픽조회 -->  
	@RequestMapping(value = "/traffic/traffic6", method = { RequestMethod.POST })
	public ModelAndView getTraffic6(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			TrafficVo traffic) throws RuntimeException {

		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 세션 권한 확인
		if (!session.getAttribute("user_auth_id").equals("40")) {
			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");
			return mav;
		}
		
		logger.info("트래팩조회/traffic/traffic6");

		// 메뉴통계
		String menuId = "";
		String ll_num = "";
		if (traffic.getResearchflag() == null) {
			menuId = "401030";

			MenuStateVo menuVo = new MenuStateVo();
			menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"), ""));
			menuVo.setMenu_id(menuId);
			try {
				this.statService.insertMenuState(menuVo);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		}

		if (traffic.getSearch_llnum_admin() == null) {
			ll_num = (String) session.getAttribute("ll_id");
			// 데이터 셋팅

			logger.info("ll_num :" + ll_num);
			traffic.setSearch_llnum(ll_num);
			traffic.setTraffic_type("R");
		}

		logger.info("getTraffic_type :" + traffic.getTraffic_type());

		if (traffic.getTraffic_type() == null
				|| traffic.getTraffic_type().equals("")) {
			traffic.setTraffic_type("R");
		}

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String today = sd.format(new Date());
		String before_date_from = "";
		String before_date_to = "";
		if (traffic.getSearch_from() == null
				|| traffic.getSearch_from().equals("")) {
			traffic.setSearch_from(today.replaceAll("-", ""));
			mav.addObject("search_from", today);
		} else {
			before_date_from = traffic.getSearch_from().toString();
			traffic.setSearch_from(before_date_from.replaceAll("-", ""));
			mav.addObject("search_from", before_date_from);
		}

		if (traffic.getSearch_to() == null || traffic.getSearch_to().equals("")) {
			traffic.setSearch_to(today.replaceAll("-", ""));
			mav.addObject("search_to", today);
		} else {
			before_date_to = traffic.getSearch_to().toString();
			traffic.setSearch_to(before_date_to.replaceAll("-", ""));
			mav.addObject("search_to", before_date_to);
		}

		mav.addObject("search_llnum", traffic.getSearch_llnum_admin());
		mav.addObject("search_nat", traffic.getSearch_nat());
		mav.addObject("traffic_type", traffic.getTraffic_type());

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/traffic/traffic3");
		logger.info("############ OK");
		return mav;
	}

	@RequestMapping(value = "/traffic/trafficChart3", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> getTrafficChart3(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			TrafficVo traffic) throws RuntimeException {
		// 2023-07-14 : 모의해킹 취약점 개선
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		//세션 사용자 확인
		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){
			return new ResponseEntity<String>("", responseHeaders, HttpStatus.CREATED);
		}
		//세션 권한 확인 
		if (!session.getAttribute("user_auth_id").equals("40") && !session.getAttribute("user_auth_id").equals("30") && !session.getAttribute("user_auth_id").equals("20") ) {
			return new ResponseEntity<String>("", responseHeaders,HttpStatus.CREATED);
		}
		
		logger.info("############################");
		logger.info(traffic.getSearch_from());
		logger.info(traffic.getSearch_to());
		logger.info(traffic.getSearch_llnum());
		logger.info("############################");

		logger.info("그래프조회/traffic/trafficChart3");
		String ll_num = "";

		JSONObject obj = new JSONObject();

		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		List<TrafficVo> trafficList = null;
		logger.info("getSearch_llnum :" + traffic.getSearch_llnum());
		if (traffic.getSearch_llnum() == null) {
			ll_num = (String) session.getAttribute("ll_id");
			// 데이터 셋팅

			logger.info("ll_num :" + ll_num);
			traffic.setSearch_llnum(ll_num);
			traffic.setTraffic_type("R");
		}

		if ((traffic.getSearch_nat() == null)
				&& (traffic.getSearch_llnum() == null)) {
			// 조건을 입력하세요
			mav.addObject("researchflag", "E");
		} else {

			logger.info("getTraffic_type :" + traffic.getTraffic_type());
			// 강제셋팅
			// traffic.setSearch_llnum("029970042888");
			// traffic.setTraffic_type("R");
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd",
					Locale.KOREA);
			String today = sd.format(new Date());
			String before_date_from = "";
			String before_date_to = "";
			if (traffic.getSearch_from() == null
					|| traffic.getSearch_from().equals("")) {
				traffic.setSearch_from(today.replaceAll("-", ""));
				mav.addObject("search_from", today);
			} else {
				before_date_from = traffic.getSearch_from().toString();
				traffic.setSearch_from(before_date_from.replaceAll("-", ""));
				mav.addObject("search_from", before_date_from);
			}

			if (traffic.getSearch_to() == null
					|| traffic.getSearch_to().equals("")) {
				traffic.setSearch_to(today.replaceAll("-", ""));
				mav.addObject("search_to", today);
			} else {
				before_date_to = traffic.getSearch_to().toString();
				traffic.setSearch_to(before_date_to.replaceAll("-", ""));
				mav.addObject("search_to", before_date_to);
			}

			mav.addObject("search_llnum", traffic.getSearch_llnum());
			mav.addObject("traffic_type", traffic.getTraffic_type());

			logger.info("call service :" + traffic.getTraffic_type());

			try {
				if (traffic.getTraffic_type() != null) {
					if (traffic.getTraffic_type().equals("R")) {
						logger.info("selectTrafficRealList call :"
								+ traffic.getTraffic_type());
						trafficList = trafficService
								.selectTrafficGraphRealList(traffic);
					} else if (traffic.getTraffic_type().equals("H")) {
						logger.info("selectTrafficHourList call :"
								+ traffic.getTraffic_type());
						trafficList = trafficService
								.selectTrafficGraphHourList(traffic);
					} else if (traffic.getTraffic_type().equals("D")) {
						logger.info("selectTrafficDayList call :"
								+ traffic.getTraffic_type());
						trafficList = trafficService
								.selectTrafficGraphDayList(traffic);
					} else if (traffic.getTraffic_type().equals("M")) {
						logger.info("selectTrafficMonthList call :"
								+ traffic.getTraffic_type());
						trafficList = trafficService
								.selectTrafficGraphMonthList(traffic);
					} else {
						logger.info("No service :" + traffic.getTraffic_type());
					}

					JSONArray in_list = new JSONArray();
					JSONArray out_list = new JSONArray();
					logger.info("trafficList.size() : " + trafficList.size());

					String temp_time = "";
					for (int i = 0; i < trafficList.size(); i++) {
						JSONObject intemp = new JSONObject();
						JSONObject outtemp = new JSONObject();

						String time = "";

						if (trafficList.get(i).getCollect_time() == null
								|| trafficList.get(i).getCollect_time()
										.equals("")) {
							time = "null";
						} else {
							time = trafficList.get(i).getCollect_time();
						}

						String r_time = "";

						if (traffic.getTraffic_type().equals("R")
								&& trafficList.size() > 24) {

							logger.info(time + " - " + temp_time);

							if (temp_time.equals("")) {
								r_time = time;
								temp_time = time;
							} else {
								if (time.substring(0, 2).equals(
										temp_time.substring(0, 2))) {
									r_time = "";
								} else {
									r_time = time;
									temp_time = time;
								}
							}
						} else {
							r_time = time;
						}

						intemp.put("year", r_time);
						intemp.put("num", trafficList.get(i).getInbps());
						outtemp.put("year", r_time);
						outtemp.put("num", trafficList.get(i).getOutbps());

						in_list.add(intemp);
						out_list.add(outtemp);

					}

					obj.put("in_list", in_list);
					obj.put("out_list", out_list);

				}
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		}
		logger.info(obj.toString());

		return new ResponseEntity<String>(obj.toString(), responseHeaders,
				HttpStatus.CREATED);
	}

	// select
	@RequestMapping(value = "/traffic/traffic_download", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getBoardFileDownload(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			TrafficVo traffic) throws IOException {

		// 세션 권한 확인
		if (session.getAttribute("user_id") == null
				|| session.getAttribute("user_id").equals("")) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");
			return mav;
		}

		if (traffic.getSearch_llnum() == null
				|| traffic.getSearch_llnum().equals("")) {
			return new ModelAndView("fileDownloadView", "downloadFile", "");
		}

		if (traffic.getTraffic_type() == null) {
			traffic.setTraffic_type("R");
		}

		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		String today = sd.format(new Date());

		String before_date_from = "";
		String before_date_to = "";
		if (traffic.getSearch_from() == null
				|| traffic.getSearch_from().equals("")) {
			traffic.setSearch_from(today.replaceAll("-", ""));
		} else {
			before_date_from = traffic.getSearch_from().toString();
			traffic.setSearch_from(before_date_from.replaceAll("-", ""));
		}

		if (traffic.getSearch_to() == null || traffic.getSearch_to().equals("")) {
			traffic.setSearch_to(today.replaceAll("-", ""));
		} else {
			before_date_to = traffic.getSearch_to().toString();
			traffic.setSearch_to(before_date_to.replaceAll("-", ""));
		}

		List<TrafficVo> list = null;

		try {
			if (traffic.getTraffic_type().equals("R")) {
				logger.info("selectTrafficRealList call :"
						+ traffic.getTraffic_type());
				list = trafficService.selectTrafficExcelRealList(traffic);
			} else if (traffic.getTraffic_type().equals("H")) {
				logger.info("selectTrafficHourList call :"
						+ traffic.getTraffic_type());
				list = trafficService.selectTrafficExcelHourList(traffic);
			} else if (traffic.getTraffic_type().equals("D")) {

				List<TrafficVo> list_hour = trafficService
						.selectTrafficHourList(traffic);
				list = trafficService.selectTrafficExcelDayList(traffic);

				for (int i = 0; i < list.size(); i++) {

					String time = list.get(i).getCollect_time()
							.substring(0, 10);

					long max_inbps = 0;
					String max_in_hour = "";

					long max_outbps = 0;
					String max_out_hour = "";
					for (int j = 0; j < list_hour.size(); j++) {
						String _time = list_hour.get(j).getCollect_time()
								.substring(0, 10);

						logger.info("###" + time + "-" + _time);

						if (time.equals(_time)) {
							if (max_inbps < list_hour.get(j).getInbps()) {
								max_inbps = list_hour.get(j).getInbps();
								max_in_hour = list_hour.get(j)
										.getCollect_time().substring(11);
							}

							if (max_outbps < list_hour.get(j).getOutbps()) {
								max_outbps = list_hour.get(j).getOutbps();
								max_out_hour = list_hour.get(j)
										.getCollect_time().substring(11);
							}
						}
					}

					list.get(i).setMax_in_hour(max_in_hour);
					list.get(i).setMax_out_hour(max_out_hour);
				}

				logger.info("selectTrafficDayList call :"
						+ traffic.getTraffic_type());

			} else if (traffic.getTraffic_type().equals("M")) {

				List<TrafficVo> list_day = trafficService
						.selectTrafficDayList(traffic);
				list = trafficService.selectTrafficExcelMonthList(traffic);

				// 최번일 구함 ///////////////////
				for (int i = 0; i < list.size(); i++) {

					String time = list.get(i).getCollect_time().substring(0, 7);

					long max_inbps = 0;
					String max_in_day = "";

					long max_outbps = 0;
					String max_out_day = "";
					for (int j = 0; j < list_day.size(); j++) {
						String _time = list_day.get(j).getCollect_time()
								.substring(0, 7);

						logger.info("###" + time + "-" + _time);

						if (time.equals(_time)) {
							if (max_inbps < list_day.get(j).getInbps()) {
								max_inbps = list_day.get(j).getInbps();
								max_in_day = list_day.get(j).getCollect_time()
										.substring(8);
							}

							if (max_outbps < list_day.get(j).getOutbps()) {
								max_outbps = list_day.get(j).getOutbps();
								max_out_day = list_day.get(j).getCollect_time()
										.substring(8);
							}
						}
					}

					list.get(i).setMax_in_day(max_in_day);
					list.get(i).setMax_out_day(max_out_day);
				}
				// ////////////////////////////////
				logger.info("selectTrafficMonthList call :"
						+ traffic.getTraffic_type());

			} else {
				logger.info("No service :" + traffic.getTraffic_type());
			}
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}

		Random random = new Random();
		int f = random.nextInt(10000);

		String fileName = "/home/jboss/traffic" + today + f + ".xls";

		if (list.size() > 0) {
			createTrafficExcel(fileName, list, traffic.getTraffic_type()); // 엑셀파일
																			// 생성
		}

		File downloadFile = new File(fileName);
		ModelAndView mav2 = new ModelAndView("fileDownloadView",
				"downloadFile", downloadFile);
		mav2.addObject("downloadFile", downloadFile);

		return mav2;
	}

	/***
	 * 트래픽정보 엑셀파일 생성
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public void createTrafficExcel(String fileName, List<TrafficVo> list,
			String trafficType) throws IOException {
		logger.info("[INFO]  /loadtest/select getSelectList Call"); // print

		long start = System.currentTimeMillis();

		int div_size = list.size();

		File file = new File(fileName);
		if (file.exists()) {
			try {
				Global.fileDelete(file);
			} catch (Exception e) {
				// 2023-01-30 TOCTOU_RACE_CONDITION 처리 추가
            }
		}
		
		// 파일로 작성한다.
		FileOutputStream fileOut = new FileOutputStream(fileName);

		HSSFWorkbook workbook = new HSSFWorkbook();

		Sheet sheet = workbook.createSheet("new sheet");

		int widthCol = 0;
		sheet.setColumnWidth(widthCol++, 256 * 15);
		sheet.setColumnWidth(widthCol++, 256 * 15);
		sheet.setColumnWidth(widthCol++, 256 * 20);
		sheet.setColumnWidth(widthCol++, 256 * 15);
		sheet.setColumnWidth(widthCol++, 256 * 15);
		sheet.setColumnWidth(widthCol++, 256 * 15);

		if (trafficType.equals("D") || trafficType.equals("M")) {
			sheet.setColumnWidth(widthCol++, 256 * 15);
		}

		sheet.setColumnWidth(widthCol++, 256 * 15);
		sheet.setColumnWidth(widthCol++, 256 * 15);

		if (trafficType.equals("D") || trafficType.equals("M")) {
			sheet.setColumnWidth(widthCol++, 256 * 15);
		}

		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)11);
		font.setFontName("맑은 고딕");
		
		CellStyle style = workbook.createCellStyle();
		DataFormat format = workbook.createDataFormat();
		style.setFont(font);
		style.setDataFormat(format.getFormat("#,##0"));
		
		Row row = null;
		row = sheet.createRow(0);
		
		Cell cell = null;
		
		ArrayList<Object> rowList = new ArrayList<Object>();
		rowList.add("시간");
		rowList.add("고객명");
		rowList.add("기관명");
		rowList.add("대역폭");
		rowList.add("입력(Kbps)");
		rowList.add("사용률(입력)");
		
		if (trafficType.equals("D")) {
			rowList.add("최번시(입력)");
		} else if (trafficType.equals("M")) {
			rowList.add("최번일(입력)");
		}
		rowList.add("출력(Kbps)");
		rowList.add("사용률(출력)");
		if (trafficType.equals("D")) {
			rowList.add("최번시(출력)");
		} else if (trafficType.equals("M")) {
			rowList.add("최번일(출력)");
		}
		
		for(int i=0; i<rowList.size(); i++){
			cell = row.createCell(i);
			if(rowList.get(i) instanceof String){
				cell.setCellValue(String.valueOf(rowList.get(i)));
			}else{
				cell.setCellValue(Integer.parseInt(String.valueOf(rowList.get(i))));
			}
			cell.setCellStyle(style);
		}

		try {
			for (int j = 0; j < div_size; j++) {
				row = sheet.createRow(j + 1);
				
				rowList = new ArrayList<Object>();
				rowList.add(list.get(j).getCollect_time());
				rowList.add(list.get(j).getCust_name());
				rowList.add(list.get(j).getNat_name());
				
				rowList.add(list.get(j).getBand());
				rowList.add(list.get(j).getInbps());
				rowList.add(list.get(j).getPer_inbps() + "%");

				//int cellCol = 0;
				/*
				 * row.createCell(cellCol++).setCellValue(list.get(j).getBand()
				 * + "/" + list.get(j).getBand());
				 */
				
				if (trafficType.equals("D")) {
					rowList.add(list.get(j).getMax_in_hour());
				} else if (trafficType.equals("M")) {
					rowList.add(list.get(j).getMax_in_day());
				}
				rowList.add(list.get(j).getOutbps());
				rowList.add(list.get(j).getPer_outbps() + "%");
				if (trafficType.equals("D")) {
					rowList.add(list.get(j).getMax_out_hour());
				} else if (trafficType.equals("M")) {
					rowList.add(list.get(j).getMax_out_day());
				}
				
				
				for(int i=0; i<rowList.size(); i++){
					cell = row.createCell(i);
					if(rowList.get(i) instanceof String){
						cell.setCellValue(String.valueOf(rowList.get(i)));
					}else{
						cell.setCellValue(Integer.parseInt(String.valueOf(rowList.get(i))));
					}
					cell.setCellStyle(style);
				}
				
			}
			// }
			logger.info("Excel 문서 작성중");
			workbook.write(fileOut);
		} catch (Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		} finally {
			fileOut.close();
			long end = System.currentTimeMillis();

			logger.info("1 million records excel writing time : "
					+ (end - start) / 1000 + " s");
		}
	}

}
