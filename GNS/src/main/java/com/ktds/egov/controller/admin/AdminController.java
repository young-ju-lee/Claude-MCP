/* POR version 1.0
 * Copyright @ 2013 kt Inc. All rights reserved.
 * This is a proprietary software of kt Inc, and you may not use this file except in compliance
 * with license agreement with kt Inc. Any redistribution or use of this software, with or without
 * modification shall be strictly prohibited without prior written approval of kt Inc, and
 * the copyright notice above does not evidence any actual or intended publication of such software.
 *
 * File Name   : AdminController.java
 * Overview    : 관리자 메뉴 Controller
 * Description : 관리자 메뉴 팝업창 관리 화면으로 이동합니다.
 * *=====================================================================
 * Version    변경  일자       개발자               설명
 * ---------- ---------- --------- --------------------------------------
 * 1.0.0.0    2013/11/11       강훈병            신규개발
 *=====================================================================
 */

package com.ktds.egov.controller.admin;

import java.sql.SQLException;
import java.util.List;

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

import com.ktds.egov.service.admin.PopupService;
import com.ktds.egov.vo.admin.PopupVo;

/***
 * 관리자 화면 controller
 * 
 * @author hoonbyung
 * 
 */
@Controller
public class AdminController {

	// 로그
	protected final Logger log = LogManager.getLogger(AdminController.class);

	// 팝업관리 서비스
	@Resource(name = "popupService")
	private PopupService popupService;

	// ******************************************************************************************
	// ** 팝업창 관리 시작
	// ******************************************************************************************

	// 팝업창 미리보기
	@RequestMapping(value = "/admin/popup_preview", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getPopupWrite(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			PopupVo popupVo) throws RuntimeException {

		log.info("팝업창 미리보기 호출 /admin/popup_preview");

		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		mav.addObject("popupVo", popupVo);
		mav.setViewName("/admin/popup_preview");
		return mav;
	}

	// 팝업창관리 작성
	@RequestMapping(value = "/admin/popup_write", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getPopupWrite(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws RuntimeException {

		log.info("팝업창관리 등록화면 호출 /admin/popup_write");

		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		//<!--2019.04.26 구조적보안진단 파라미터 변조 수정>
		//세션 사용자 확인
 		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 	
 			
 			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");	
			return mav;
 		}
 		
 		//세션 권한 확인
 		if(session.getAttribute("user_auth_id") == null || ! session.getAttribute("user_auth_id").equals("40")){ 			
 			mav.addObject("redirect_url", "/main.do");
			mav.setViewName("/redirectPage");
 			return mav;
 		}
		mav.addObject("flag", "W"); // "W" : 신규, "E" : 수정
		mav.setViewName("/admin/popup_write");
		return mav;
	}

	// 팝업창관리 수정
	@RequestMapping(value = "/admin/popup_edit", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getPopupEdit(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			String pop_id) throws RuntimeException {

		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		//<!--2019.04.26 구조적보안진단 파라미터 변조 수정>
		//세션 사용자 확인
 		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 	
 			
 			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");	
			return mav;
 		}
 		
 		//세션 권한 확인
 		if(session.getAttribute("user_auth_id") == null || ! session.getAttribute("user_auth_id").equals("40")){ 			
 			mav.addObject("redirect_url", "/main.do");
			mav.setViewName("/redirectPage");
 			return mav;
 		}

		try {
			PopupVo popupVo = this.popupService.select(pop_id);
			mav.addObject("popupVo", popupVo);
			mav.addObject("flag", "E");
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}

		
		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/admin/popup_write");

		return mav;
	}

	// 팝업창관리 저장시
	@RequestMapping(value = "/admin/popup_write_register", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getPopupWriteRegister(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			String flag, PopupVo popupVo) throws RuntimeException {

		log.info("팝업창관리 등록화면 저장 /admin/popup_write_register");

		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		//<!--2019.04.26 구조적보안진단 파라미터 변조 수정>
		//세션 사용자 확인
 		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 	
 			
 			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");	
			return mav;
 		}
 		
 		//세션 권한 확인
 		if(session.getAttribute("user_auth_id") == null || ! session.getAttribute("user_auth_id").equals("40")){ 			
 			mav.addObject("redirect_url", "/main.do");
			mav.setViewName("/redirectPage");
 			return mav;
 		}
		String popId = "";

		if (flag.equals("W")) {
			try {
				popId = this.popupService.selectPopupId();
				popupVo.setPop_id(popId);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
			
		}

		log.info(" ### popId : " + popId);

		int result = 0;
		if (flag.equals("W")) {
			try {
				result = this.popupService.insertPopup(popupVo);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			} // 저장
			log.info(" 저장 : " + result);
		} else if (flag.equals("E")) {
			try {
				result = this.popupService.updatePopup(popupVo);
			} catch (SQLException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			} // 수정
			log.info(" 수정 : " + result);
		}

		mav.addObject("redirect_url", "/admin/popup_list.do");
		mav.setViewName("/redirectPage");
		return mav;
	}

	// 팝업창관리 리스트 호출
	@RequestMapping(value = "/admin/popup_list", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getPopupList(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			PopupVo popupVo) throws RuntimeException {
		log.info("팝업창관리 리스트화면 호출 /admin/popup_list");


		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		//세션 권한 확인
 		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 	
 			mav.addObject("redirect_url", "/member/login.do");
 			mav.setViewName("/redirectPage");	
 			return mav;
 		}
 		//20180928 모의해킹 URL 강제접속 수정
 		if(!session.getAttribute("user_auth_id").equals("40") )
 				{ 	
 					mav.addObject("redirect_url", "/error.do");
 		 			mav.setViewName("/redirectPage");	
 		 			return mav;
 		}
		String searchTitle = ""; // 검색 조건

		// 페이징 초기화
		popupVo.setLimit(10); // 출력 행의 갯수
		popupVo.setOffSet(0); // 출력 첫행 위치

		// 검색 하는 경우
		if (popupVo.getSearch_yn().equals("Y")) {
			// 검색 버튼 클릭시

			log.info(" ### 검색 버튼 클릭시	 ");

			searchTitle = popupVo.getSearch_title();
			popupVo.setP_search_title(searchTitle); // 검색 조건을 계속 저장 관리

			// 페이징 초기화
			popupVo.setPage("1"); // 페이징 순번
		} else {
			if (popupVo.getPage().equals("1")) {
				// 최초로 목록 화면 들어가는 경우
				log.info(" ### 최초로 목록 화면 들어가는 경우 ");

				// 검색 조건 초기화, 페이징 초기화
				popupVo.setOffSet(0);
			} else {
				// 페이징으로 화면 들어가는 경우
				log.info(" ### 페이징으로 화면 들어가는 경우 ");

				int page = Integer.parseInt(popupVo.getPage());
				int offset = page * 10 - 10;
				popupVo.setOffSet(offset);
			}
		}


		
		try {
			List<PopupVo> list = this.popupService.selectList(popupVo);
			
			mav.addObject("popup_list", list);
			mav.addObject("popupVo", popupVo);
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}

		
		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/admin/popup_list");
		return mav;
	}

	// 팝업창관리 삭제
	@RequestMapping(value = "/admin/popup_delete", method = { RequestMethod.POST })
	public ModelAndView getPopupDelete(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			String pop_id) throws RuntimeException {

		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		//<!--2019.04.26 구조적보안진단 파라미터 변조 수정>
		//세션 사용자 확인
 		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 	
 			
 			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");	
			return mav;
 		}
 		
 		//세션 권한 확인
 		if(session.getAttribute("user_auth_id") == null || ! session.getAttribute("user_auth_id").equals("40")){ 			
 			mav.addObject("redirect_url", "/main.do");
			mav.setViewName("/redirectPage");
 			return mav;
 		}
		try {
			int result = this.popupService.deletePopup(pop_id);
			log.info(" 행 삭제 : " + result);
		} catch (SQLException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		// 보여질 View 페이지를 설정한다.
		mav.addObject("redirect_url", "/admin/popup_list.do");
		mav.setViewName("/redirectPage");

		return mav;
	}
}
