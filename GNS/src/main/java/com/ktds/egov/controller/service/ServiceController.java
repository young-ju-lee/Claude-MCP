/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : ServiceController.java
* Overview    : kt서비스 Controller
* Description : kt서비스 화면으로 이동한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.controller.service;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.egov.service.member.StatService;
import com.ktds.egov.vo.member.MenuStateVo;
import com.ktds.framework.util.Global;

@Controller
public class ServiceController {

	protected final Logger logger = LogManager.getLogger(ServiceController.class);
	
	
	//메뉴접속통계 적용
    @Resource(name = "StatService")
    private StatService statService;
    
    
    @RequestMapping(value = "/service/leased_basic", method = { RequestMethod.POST })
	public ModelAndView getLeased_basic(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /Leased_basic  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "201010";

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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/leased_basic");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/leased_backbone", method = { RequestMethod.POST })
	public ModelAndView getLeased_backbone(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /leased_backbone  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "201020";

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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/leased_backbone");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/leased_cctv", method = { RequestMethod.POST })
	public ModelAndView getLeased_cctv(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /leased_cctv  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "201030";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/leased_cctv");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/leased_lamda", method = { RequestMethod.POST })
	public ModelAndView getLeased_lamda(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /leased_lamda  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "201040";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/leased_lamda");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/leasedline_infra", method = { RequestMethod.POST })
	public ModelAndView getLeasedline_infra(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /leasedline_infra  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "201050";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/leasedline_infra");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/ipservice_internet", method = { RequestMethod.POST })
	public ModelAndView getIpservice_internet(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /ipservice_internet  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "202010";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/ipservice_internet");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/ipservice_ipvpn", method = { RequestMethod.POST })
	public ModelAndView getIpservice_ipvpn(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /ipservice_ipvpn  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "202020";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/ipservice_ipvpn");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/ipservice_infra", method = { RequestMethod.POST })
	public ModelAndView getIpservice_infra(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /ipservice_infra  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "202030";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/ipservice_infra");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/ipservice_soip", method = { RequestMethod.POST })
	public ModelAndView getIpservice_soip(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /ipservice_soip  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "203010";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/ipservice_soip");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/ipservice_soip_infra", method = { RequestMethod.POST })
	public ModelAndView getIpservice_soip_infra(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /ipservice_soip_infra  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "203020";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/ipservice_soip_infra");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/wireless_m_internetphone", method = { RequestMethod.POST })
	public ModelAndView getWireless_m_internetphone(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /wireless_m_internetphone  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "204010";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/wireless_m_internetphone");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/wireless_m_internetphone_infra", method = { RequestMethod.POST })
	public ModelAndView getWireless_m_internetphone_infra(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /wireless_m_internetphone_infra  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "204010";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/wireless_m_internetphone_infra");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/wireless_data", method = { RequestMethod.POST })
	public ModelAndView getWireless_data(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /wireless_data  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "204020";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/wireless_data");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/iot_service", method = { RequestMethod.POST })
	public ModelAndView getIot_service(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /iot_service  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "204020";
	
		MenuStateVo menuVo = new MenuStateVo();
		menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"),""));
		menuVo.setMenu_id(menuId);		
		try {
			this.statService.insertMenuState(menuVo);
		} catch (SQLException e) {			
			System.out.println("시스템 에러 발생");
		}
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/iot_service");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/iot_infra", method = { RequestMethod.POST })
	public ModelAndView getIot_infra(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /iot_infra  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "204020";
	
		MenuStateVo menuVo = new MenuStateVo();
		menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"),""));
		menuVo.setMenu_id(menuId);		
		try {
			this.statService.insertMenuState(menuVo);
		} catch (SQLException e) {			
			System.out.println("시스템 에러 발생");
		}
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/iot_infra");
		
		return mav;
	}
   
    @RequestMapping(value = "/service/wireless_cctv", method = { RequestMethod.POST })
	public ModelAndView getWireless_cctv(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /wireless_cctv  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "204030";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/wireless_cctv");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/wireless_securitywifi", method = { RequestMethod.POST })
	public ModelAndView getWireless_security(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /wireless_securitywifi  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "204040";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/wireless_securitywifi");
		
		return mav;
	}
    
    @RequestMapping(value = "/service/wireless_infra", method = { RequestMethod.POST })
	public ModelAndView getWireless_infra(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /wireless_infra  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "204050";
	
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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/wireless_infra");
		
		return mav;
	}
    
    
    
	// select ip응용서비스
	@RequestMapping(value = "/service/ip", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getService_System(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("[INFO]  /ip  Call"); // print
		
		//메뉴접속통계 적용
		String menuId = "202010";

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

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/service/ip");
		
		return mav;
	}
	
	// select
		@RequestMapping(value = "/service/group_a", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getService_GroupA(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /Group_A  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/group_a");
			
			return mav;
		}
		
		// select
		@RequestMapping(value = "/service/group_b", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getService_GroupB(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /Group_B  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/group_b");
			
			return mav;
		}
		
		// select
		@RequestMapping(value = "/service/group_c", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getService_GroupC(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /group_c  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/group_c");
			
			return mav;
		}
		
		// select
		@RequestMapping(value = "/service/integrated_vpn", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getIntegrated_Vpn(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /Integrated_VPN  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/integrated_vpn");
			
			return mav;
		}
		
		// select
		@RequestMapping(value = "/service/security", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getSecurity(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /Security  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/security");
			
			return mav;
		}
		
		// select
		@RequestMapping(value = "/service/rule", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getRule(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /Rule  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/rule");
			
			return mav;
		}
		
		// select
		@RequestMapping(value = "/service/composition", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getComposition(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /Composition  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/composition");
			
			return mav;
		}
		
		// select
		@RequestMapping(value = "/service/preservation", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getPreservation(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /Preservation  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/preservation");
			
			return mav;
		}
		
		//인프라소개 페이지 호출
		@RequestMapping(value = "/service/infra", method = { RequestMethod.POST,RequestMethod.GET })
		public ModelAndView getInfra(HttpSession session) throws RuntimeException {
			
			logger.info("인프라소개 /service/infra");
			
			//메뉴접속통계 적용
			String menuId = "203010";
	 		
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

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/infra");				
			return mav;
		}
		
		//기본회선 상세요금조회
	    @RequestMapping(value = "/service/leased_basic_detail", method = { RequestMethod.POST})
		public ModelAndView getLeased_basic_detail(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /leased_basic_detail  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/leased_basic_detail");
			
			return mav;
		}
	    
	    //백본회선 상세요금조회
	    @RequestMapping(value = "/service/leased_backbone_detail", method = { RequestMethod.POST})
		public ModelAndView getLeased_backbone_detail(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /leased_backbone_detail  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/leased_backbone_detail");
			
			return mav;
		}
	    
	    //CCTV 전송회선 상세요금조회
	    @RequestMapping(value = "/service/leased_cctv_detail", method = { RequestMethod.POST})
		public ModelAndView getLeased_cctv_detail(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /leased_cctv_detail  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/leased_cctv_detail");
			
			return mav;
		}

	    //인터넷회선 상세요금조회
	    @RequestMapping(value = "/service/ipservice_internet_detail", method = { RequestMethod.POST })
		public ModelAndView getIpservice_internet_detail(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /ipservice_internet_detail  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/ipservice_internet_detail");
			
			return mav;
		}
	    
	    //IP-VPN 상세요금조회
	    @RequestMapping(value = "/service/ipservice_ipvpn_detail", method = { RequestMethod.POST })
		public ModelAndView getIpservice_ipvpn_detail(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("[INFO]  /ipservice_ipvpn_detail  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/service/ipservice_ipvpn_detail");
			
			return mav;
		}
	    
	    @RequestMapping(value = "/service/ktservice", method = { RequestMethod.POST })
		public ModelAndView getLeasedline(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, @Param("pageType") String pageType) throws RuntimeException {
			
			logger.info("[INFO]  /ktservice  Call"); // print
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();
			// 2023-07-14 : 모의해킹 취약점 개선 
			// pageType = Global.cleanStringXSS(pageType); : 필터링 통해서 처리하던 방식(아래 if로 더 확실하게 보안)
			if(1 < pageType.length()){
				pageType = "1";
			}
			logger.info("pageType : " +pageType);
			// 보여질 View 페이지를 설정한다.
			mav.addObject("pageType", pageType);
			mav.setViewName("/service/ktservice");
			
			return mav;
		}
}
