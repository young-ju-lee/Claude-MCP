/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : IntroController.java
* Overview    : 제도소개 메뉴 Controller
* Description : 제도소개 화면으로 이동한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/


package com.ktds.egov.controller.intro;

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
public class IntroController {

	//로그
	protected final Logger logger = LogManager.getLogger(IntroController.class);
	
	//메뉴접속통계 적용
    @Resource(name = "StatService")
    private StatService statService;
    
    
	// 제도소개 >> 정의
	@RequestMapping(value = "/intro/definition", method = { RequestMethod.POST })
	public ModelAndView getdefinition(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		
		logger.info( " Call /intro/definition.do ");

		//메뉴접속통계 적용
		String menuId = "101010";
 		
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
		mav.setViewName("/intro/definition");		
		return mav;
	}

	// 제도소개 >> 특징
	@RequestMapping(value = "/intro/feature", method = { RequestMethod.POST })
	public ModelAndView getfeature(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info( " Call /intro/feature.do ");
		
		//메뉴접속통계 적용
		String menuId = "102010";
 		
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
		mav.setViewName("/intro/feature");		
		return mav;
	}
	
	// 제도소개 >> 서비스체계
	@RequestMapping(value = "/intro/service", method = { RequestMethod.POST})
	public ModelAndView getService(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info( " Call /intro/service.do ");
		
		//메뉴접속통계 적용
		String menuId = "103010";
 		
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
		mav.setViewName("/intro/service");		
		return mav;
	}
	
	
	// 제도소개 >> 추진체계 및 상담/문의안내
	@RequestMapping(value = "/intro/counsel", method = { RequestMethod.POST })
	public ModelAndView getcounsel(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info( " Call /intro/counsel.do ");
		
		//메뉴접속통계 적용
		String menuId = "105010";
 		
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
		mav.setViewName("/intro/counsel");		
		return mav;
	}
	

	
	// 제도소개 >> 제도변경사항
	@RequestMapping(value = "/intro/changepoint", method = { RequestMethod.POST })
	public ModelAndView getchangepoint(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info( " Call /intro/changepoint.do ");
		
		//메뉴접속통계 적용
		String menuId = "104010";
 		
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
		mav.setViewName("/intro/changepoint");		
		return mav;
	}
	// 제도소개 >> 제도변경사항
		@RequestMapping(value = "/intro/changepointsla", method = { RequestMethod.POST })
		public ModelAndView getchangepointsla(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info( " Call /intro/changepointsla.do ");
			
			//메뉴접속통계 적용
			String menuId = "104010";
	 		
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
			mav.setViewName("/intro/changepointsla");		
			return mav;
		}
		// 제도소개 >> 제도변경사항
				@RequestMapping(value = "/intro/changepointspeed", method = { RequestMethod.POST })
				public ModelAndView getchangepointspeed(HttpSession session,
						HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
					
					logger.info( " Call /intro/changepointspeed.do ");
					
					//메뉴접속통계 적용
					String menuId = "104010";
			 		
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
					mav.setViewName("/intro/changepointspeed");		
					return mav;
				}			
		// 제도소개 >> 제도변경사항
		@RequestMapping(value = "/intro/changepointdis", method = { RequestMethod.POST })
		public ModelAndView getchangepointdis(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			logger.info( " Call /intro/changepointdis.do ");
			
			//메뉴접속통계 적용
			String menuId = "104010";
	 		
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
			mav.setViewName("/intro/changepointdis");		
			return mav;
		}
	
	
	// 제도소개 >> 추진경과
	@RequestMapping(value = "/intro/propulsion", method = { RequestMethod.POST })
	public ModelAndView getpropulsion(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info( " Call /intro/propulsion.do ");
		
		//메뉴접속통계 적용
		String menuId = "106010";
 		
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
		mav.setViewName("/intro/propulsion");		
		return mav;
	}
}
