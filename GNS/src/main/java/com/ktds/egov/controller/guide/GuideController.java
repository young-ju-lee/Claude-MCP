/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : GuideController.java
* Overview    : 이용안내 Controller
* Description : 이용안내 화면으로 이동한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/


package com.ktds.egov.controller.guide;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class GuideController {

	protected final Logger logger = LogManager.getLogger(GuideController.class);
	
	//메뉴접속통계 적용
    @Resource(name = "StatService")
    private StatService statService;
	
  //이용절차 페이지 호출
  	@RequestMapping(value = "/guide/procedure", method = { RequestMethod.POST})
  	public ModelAndView getProcedure(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
  		
  		logger.info("이용절차 /guide/procedure");
  		
  		//메뉴접속통계 적용
  		String menuId = "301010";
   		
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
  		mav.setViewName("/guide/procedure");				
  		return mav;
  	}
  	
  	//kt청약절차 페이지 호출
  	@RequestMapping(value = "/guide/procedure1", method = { RequestMethod.POST})
  	public ModelAndView getProcedure1(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
  		
  		logger.info("kt청약절차 /guide/procedure1");
  		
  		//메뉴접속통계 적용
  		String menuId = "301020";  // KT청약절차
   		
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
  		mav.setViewName("/guide/procedure1");				
  		return mav;
  	}
	
	//이용안내 >> 요금표 화면 호출시
	@RequestMapping(value = "/guide/charges", method = { RequestMethod.POST })
	public ModelAndView getCharges(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		logger.info("Charges");
		
		//메뉴접속통계 적용
		String menuId = ""; 
    	 menuId = "302010";
 		
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
		mav.setViewName("/guide/charges");
		return mav;
	}
	
	//요금표(1단계) 페이지 호출
	@RequestMapping(value = "/guide/charges_1", method = { RequestMethod.POST})
	public ModelAndView getOldCharge1(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("요금표(1단계) /guide/charges_1");
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/guide/charges_1");				
		return mav;
	}
	
	//요금표(2단계) 페이지 호출
	@RequestMapping(value = "/guide/charges_2", method = { RequestMethod.POST})
	public ModelAndView getOldCharge2(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("요금표(2단계) /guide/charges_2");
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/guide/charges_2");				
		return mav;
	}
	
	//요금표(3단계) 페이지 호출
		@RequestMapping(value = "/guide/charges_3", method = { RequestMethod.POST})
		public ModelAndView getOldCharge3(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info("요금표(3단계) /guide/charges_3");
			
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();

			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/guide/charges_3");				
			return mav;
		}
	
	//거리/속도체계 화면 호출시
	@RequestMapping(value = "/guide/area", method = { RequestMethod.POST})
	public ModelAndView getArea(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("권역/속도/거리체계 /guide/area");
		
		//메뉴접속통계 적용
		String menuId = ""; 
		menuId = "302020";
		
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
		mav.setViewName("/guide/area");				
		return mav;
	}
		
	//상담안내 페이지 호출
	@RequestMapping(value = "/guide/counsel", method = { RequestMethod.POST})
	public ModelAndView getCounsel(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("상담안내 /guide/counsel");
		
		//메뉴접속통계 적용
		String menuId = "303010";
 		
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
		mav.setViewName("/guide/counsel");				
		return mav;
	}
		
	//kt특장점 페이지 호출
	@RequestMapping(value = "/guide/kt_merit", method = { RequestMethod.POST})
	public ModelAndView getktMerit(HttpSession session) throws RuntimeException {
		
		logger.info("kt특장점 /guide/kt_merit");
		
		//메뉴접속통계 적용
		String menuId = "304010";
 		
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
		mav.setViewName("/guide/kt_merit");				
		return mav;
	}
	
	//kt특장점 페이지 호출
		@RequestMapping(value = "/guide/ktsat_merit", method = { RequestMethod.POST})
		public ModelAndView getktsatMerit(HttpSession session) throws RuntimeException {
			
			logger.info("ktsat특장점 /guide/ktsat_merit");
			
			//메뉴접속통계 적용
			String menuId = "304010";
	 		
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
			mav.setViewName("/guide/ktsat_merit");				
			return mav;
		}
		
		//kt특장점 위성 페이지 호출
				@RequestMapping(value = "/guide/ktsat_wi", method = { RequestMethod.POST})
				public ModelAndView getktsatWi(HttpSession session) throws RuntimeException {
					
					logger.info("ktsat위성 /guide/ktsat_wi");
					
					//메뉴접속통계 적용
					String menuId = "304010";
			 		
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
					mav.setViewName("/guide/ktsat_wi");				
					return mav;
				}
	
	//조직/체계 페이지 호출
	@RequestMapping(value = "/guide/formation", method = { RequestMethod.POST})
	public ModelAndView getFormation(HttpSession session) throws RuntimeException {
		
		logger.info("조직/체계 /guide/formation");
		
		//메뉴접속통계 적용
		String menuId = "304020";
 		
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
		mav.setViewName("/guide/formation");				
		return mav;
	}
	
	//장애대응 방안 페이지 호출
	@RequestMapping(value = "/guide/hindrance", method = { RequestMethod.POST})
	public ModelAndView getHindrance(HttpSession session) throws RuntimeException {
		
		logger.info("장애대응 방안 /guide/formation");
		
		//메뉴접속통계 적용
		String menuId = "304030";
 		
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
		mav.setViewName("/guide/hindrance");				
		return mav;
	}
	
	//망관리/감시 페이지 호출
	@RequestMapping(value = "/guide/control", method = { RequestMethod.POST})
	public ModelAndView getControl(HttpSession session) throws RuntimeException {
		
		logger.info("망관리/감시 /guide/control");
		
		//메뉴접속통계 적용
		String menuId = "304040";
 		
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
		mav.setViewName("/guide/control");				
		return mav;
	}
	
}
