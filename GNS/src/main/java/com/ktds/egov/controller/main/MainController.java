/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : MainController.java
* Overview    : 메인화면 Controller
* Description : 메인화면 으로 이동한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.controller.main;

import java.sql.SQLException;
import java.util.HashMap;
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
import com.ktds.egov.vo.support.DataRoomVo;
import com.ktds.egov.vo.support.NoticeVo;
import com.ktds.framework.core.mvc.BaseService;

@Controller
public class MainController {

	protected final Logger logger = LogManager.getLogger(MainController.class);
	
	@Resource(name = "supportService")
    private BaseService<NoticeVo> supportService; //공지사항 service
	
	@Resource(name = "dataRoomService")
    private BaseService<DataRoomVo> dataRoomService; //자료실 service
	
	//팝업관리 서비스
	@Resource(name="popupService")
	private PopupService popupService;
		
	// select
	@RequestMapping(value = "/intro/sample", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getTop(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/intro/sample");
		
		return mav;
	}
	
	// select
	@RequestMapping(value = "/main", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getMain(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		

		logger.info("[INFO]  /main  Call");
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> map = new HashMap<String, String>();
		

		PopupVo popupVo = new PopupVo();
		
		map.put("mapperInfo", "selectMainPopup");
		
		try {
			List<PopupVo> popup_list = popupService.selectMainPopupList(popupVo, map);
			
			if(popup_list.size() > 0) {
				for(int i=0; i<popup_list.size(); i++) {
					String cont = popup_list.get(i).getPop_cont( ).replace("\r\n" , "<br/>");
					cont = cont.replace(" ","&nbsp;");
					popupVo.setPop_cont(cont);
					/*
					popupVo.setPop_title(popup_list.get(i).getPop_title());
					popupVo.setPop_cont(cont);
					*/
					popupVo.setPop_yn(popup_list.get(i).getPop_yn());
					
				}
				
			} else {
				popupVo.setPop_yn("N");
			}
			mav.addObject("pop_list", popup_list);
			mav.addObject("popupVo", popupVo);
			
		} catch (SQLException e1) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}

		map.clear();

		/*
		PopupVo popupVo = popupService.selectMainPopup();

		if(popupVo == null){
			
			popupVo.setPop_yn("N");
		}else{
			String cont = popupVo.getPop_cont( ).replace("\r\n" , "<br/>");
			logger.info( " ### " + cont );
			cont = cont.replace(" ","&nbsp;");
			popupVo.setPop_cont( cont);
		}
		
		mav.addObject("popupVo", popupVo);
		*/

		try{
			map.put("mapperInfo", "selectNoticeList");
			mav.addObject("notice_list", supportService.select(null, map));
			map.put("mapperInfo", "selectDataroomList");
			mav.addObject("dataroom_list", dataRoomService.select(null, map));
			
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/main");
		return mav;
	}
		
	 @RequestMapping(value="/error", method = { RequestMethod.POST, 
			 RequestMethod.GET})
	public ModelAndView getError(HttpServletRequest request, HttpServletResponse response ) throws RuntimeException {
		ModelAndView mav = new ModelAndView();
		logger.info("Error Referer : " + request.getHeader("Referer"));
		mav.setViewName("/errorPages/error");  
		return mav; 
	}
	 
}
