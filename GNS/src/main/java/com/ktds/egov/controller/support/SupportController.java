/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : SupportController.java
* Overview    : 고객지원 메뉴 Controller
* Description : 고객지원 화면으로 이동한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.controller.support;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.egov.common.util.DefaultContext;
import com.ktds.egov.common.util.FileManager;
import com.ktds.egov.common.util.SFTPUtil;
import com.ktds.egov.common.util.PropertyUtil;
import com.ktds.egov.service.member.StatService;
import com.ktds.egov.vo.member.MenuStateVo;
import com.ktds.egov.vo.support.NoticeVo;
import com.ktds.egov.vo.support.DataRoomVo;
import com.ktds.framework.core.mvc.BaseService;
import com.ktds.framework.util.Global;

@Controller
public class SupportController{
	protected final Logger logger = LogManager.getLogger(SupportController.class);

	@Resource(name = "supportService")
    private BaseService<NoticeVo> supportService;
	 
	@Resource(name = "dataRoomService")
    private BaseService<DataRoomVo> dataRoomService;
	
	//메뉴접속통계 적용
    @Resource(name = "StatService")
    private StatService statService;
	    
	private static final String filePath = "/home/jboss/uploadFiles/"; //업로드 파일경로 			
		
	// 공지사항 LIST
		@RequestMapping(value = "/support/notice_list", method = { RequestMethod.POST })
		public ModelAndView getNoticeList(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, NoticeVo noticeVo) throws RuntimeException {
			
			logger.info("공지사항 조회/support/notice_list");
			
//			logger.info(" searchTitle : " + noticeVo.getSearch_title());
//			
//			logger.info(" ########## 방식 여부 " + request.getMethod().toString());
			
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			ModelAndView mav = new ModelAndView();
			
			if(request.getMethod() == "GET" && noticeVo.getP_search_title() != null && !noticeVo.getP_search_title().equals("")){
				mav.addObject("redirect_url", "/error.do");
				mav.setViewName("/redirectPage");	
				return mav;			  
			}
			
			//메뉴통계
			String menuId = ""; 
			menuId = "501010";
	 		
	 		MenuStateVo menuVo = new MenuStateVo();
	 		menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"),""));
	 		menuVo.setMenu_id(menuId);		
	 		try {
				this.statService.insertMenuState(menuVo);
			} catch (SQLException e1) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
			
			
			 String searchTitle = ""; //검색 조건
			
			 noticeVo.setLimit(10);
			 noticeVo.setOffSet(0);
			
			//검색 하는 경우
			if(noticeVo.getSearch_yn().equals("Y")){ 
				//검색 버튼 클릭시			
				
				logger.info(" ### 검색 버튼 클릭시	 ");
				
				searchTitle = noticeVo.getSearch_title();
				noticeVo.setP_search_title(searchTitle); //검색 조건을 계속 저장 관리
				 			
				//페이징 초기화
				noticeVo.setPage("1");
				//noticeVo.setPage("1"); //페이징 순번
			}else{ 			
				int page = Integer.parseInt(noticeVo.getPage());
				
				int offset = page * 10 - 10; 										
				noticeVo.setOffSet(offset); 													
			}			
			
			try{
				mav.addObject("notice_list", supportService.select(noticeVo));
			}catch(Exception e){
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
			
			mav.addObject("noticeVo", noticeVo);
			// 보여질 View 페이지를 설정한다.
			
			logger.info( " ### page : " + noticeVo.getPage());
			mav.setViewName("/support/notice_list");
			
			return mav;
		}
		
		// 공지사항 VIEW
		@RequestMapping(value = "/support/notice_view", method = { RequestMethod.POST,
				/*RequestMethod.GET*/ }) //2015-01-30 GET 방식만 받도록 수정(장혁준)
		public ModelAndView getNoticeView(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, NoticeVo noticeVo) throws RuntimeException {
						
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
						ModelAndView mav = new ModelAndView();
			if(request.getMethod() == "GET" && noticeVo.getNotice_id() != null && !noticeVo.getNotice_id().equals("")){
				mav.addObject("redirect_url", "/error.do");
				mav.setViewName("/redirectPage");	
				return mav;
			}
			
			//<!--2019.04.26 구조적보안진단 파라미터 변조 수정>
			//세션 사용자 확인
	 		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){ 	
	 			
	 			mav.addObject("redirect_url", "/member/login.do");
				mav.setViewName("/redirectPage");	
				return mav;
	 		}
			
			String curPage = noticeVo.getPage();
						
			try{

				mav.addObject("notice_list", supportService.select(noticeVo));
				mav.addObject("noticeVo", noticeVo);
			
				//view 건주증가
				HashMap<String,String> map1 = new HashMap<String,String>();
				map1.put("mapperInfo", "updateNoticeCnt");

				supportService.update(noticeVo, map1);		
				
				//view 데이타 조회
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("mapperInfo", "selectNoticeView");
				List<NoticeVo> list = supportService.select(noticeVo, map);
				if(list.size() > 0){
					list.get(0).setPage(curPage);
					
					String cont = list.get(0).getNotice_cont().replace("\r\n", "<br/>").replace(" ", "&nbsp;");
					list.get(0).setNotice_cont(cont);						
					
					mav.addObject("noticeVo", list.get(0));
				}
				
			}catch(Exception e){
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
			
			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/support/notice_view");
			
			return mav;
		}
		
		@RequestMapping(value = "/support/notice_write", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView getNoticeWrite(HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
			
			ModelAndView mav = new ModelAndView();
			
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
	 		
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			

			// 보여질 View 페이지를 설정한다.
			mav.addObject("flag", "W");
			mav.setViewName("/support/notice_write");
			
			return mav;
		}
		
		@RequestMapping(value = "/support/notice_edit", method = { RequestMethod.POST,
				/*RequestMethod.GET*/ }) //2015-01-30 GET 방식만 받도록 수정(장혁준)
		public ModelAndView getNoticeEdit(HttpSession session,
				HttpServletRequest request, HttpServletResponse response, NoticeVo noticeVo) throws RuntimeException {
			
			ModelAndView mav = new ModelAndView();
			
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
	 		
			// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
			
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("mapperInfo", "selectNoticeView");
			
			try{

				List<NoticeVo> list = supportService.select(noticeVo, map);
				if(list.size() > 0){
					mav.addObject("noticeVo", list.get(0));
				}
			}catch(Exception e){
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
			
			mav.addObject("flag", "E");
			// 보여질 View 페이지를 설정한다.
			mav.setViewName("/support/notice_write");
			
			return mav;
		}
		
		 // 자료실 삭제
	  	@RequestMapping(value = "/support/notice_delete", method = { RequestMethod.POST })
	  	public ModelAndView getDeleteNotice(HttpSession session,
	  			HttpServletRequest request, HttpServletResponse response, NoticeVo noticeVo) throws RuntimeException {  		
	  		ModelAndView mav = new ModelAndView();
					
	  		logger.info(" ### notice_id " +noticeVo.getNotice_id() );
	  		
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
	 		 		
	 		HashMap<String,String> map = new HashMap<String,String>();
			map.put("mapperInfo", "selectNoticeView");
			
			try{

				int rt = supportService.delete(noticeVo);
						
				logger.info(" 삭제 : " + rt);
			}catch(Exception e){
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
			
			// 보여질 View 페이지를 설정한다.		
			mav.addObject("redirect_url", "/support/notice_list.do");
			mav.setViewName("/redirectPage");
 			return mav;
	  	}
	  	
		// insert / update
		@RequestMapping(value = "/support/notice_write_register", method = { RequestMethod.POST,
				RequestMethod.GET })
		public ModelAndView setNoticeWrite(HttpSession session,
				MultipartHttpServletRequest request, HttpServletResponse response, NoticeVo noticeVo, String flag) throws RuntimeException {

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
			Calendar cal = Calendar.getInstance();
			String noticeId= "";
			int result = 0;
			
/*			if(!request.getFile("upload_file").getOriginalFilename().equals("")){
				fileName = request.getFile("upload_file").getOriginalFilename();
				filePath = DefaultContext.getInstance().getPath().replaceAll("\\\\", "/")+"/";
				boardVo.setBoard_file_name(fileName);
				boardVo.setBoard_file_path(filePath);
				UploadFile("upload_file",filePath,"",request);
			}*/

			try{
				StringBuilder sb = new StringBuilder("");
				if(flag.equals("W")){
					sb.append(cal.get(Calendar.YEAR));
					sb.append(cal.get(Calendar.MONTH)+1 >= 10 ? cal.get(Calendar.MONTH)+1 : "0"+(cal.get(Calendar.MONTH)+1));
					sb.append(noticeId += cal.get(Calendar.DATE) >= 10 ? cal.get(Calendar.DATE) : "0"+cal.get(Calendar.DATE));
					
					logger.info( "#### notice_id : " + sb.toString());
					
					noticeVo.setNotice_id(sb.toString());
					HashMap<String,String> map = new HashMap<String,String>();
					map.put("mapperInfo", "selectNoticeId");
					
					List<NoticeVo> list = supportService.select(noticeVo, map);
					if(list.size() > 0){
						if(list.get(0) != null){
							noticeVo.setNotice_id(list.get(0).getNotice_id());
						}else{
							sb.append("001");
							noticeVo.setNotice_id(sb.toString());
						}
					}else{
						sb.append("001");
						noticeVo.setNotice_id(sb.toString());
					}
					result = supportService.insert(noticeVo);
					logger.info(" 저장 행 : " + result);
				}else if(flag.equals("E")){
					result = supportService.update(noticeVo);
					logger.info(" 수정 행 : " + result);
				}

				mav.addObject("notice_list", supportService.select(noticeVo));
				mav.addObject("noticeVo", noticeVo);
				
			}catch(Exception e){
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
			
			mav.setViewName("/support/notice_list");
			
			return mav;
		}

		
    public void UploadFile(String componentName,String dirName,String fileName,MultipartHttpServletRequest multipartRequest) {  
    	  
    	File f = new File(dirName);    	
    	
    	logger.info(" ################## " + dirName);
    	//<!--2019.04.26 구조적보안진단 업로드 수정>
    	String fileExt = "";  //파일확장자명 
    	
    	fileExt = fileName.substring(fileName.lastIndexOf(".") + 1); 			
			logger.info("### upload2 파일확장자 : " + fileExt);
			
		if(fileExt.equals("pdf")) {
			logger.info("### File EXT Check Success!");
	 	} else {
	 		logger.info("### BAD EXT!");	 		
			return;
	 	}
    	if(!f.exists())
    	{    		
    		 f.mkdirs();
    	}
    	else {
			try {
				if (Global.fileDelete(f)) {
					logger.info("### 디렉토리 삭제 디렉토리 삭제 " + dirName + "/ 디렉토리 삭제");
					logger.info("### 디렉토리 삭제 디렉토리 삭제 " + dirName + "/ 디렉토리 삭제");
					logger.info("### 디렉토리 삭제 디렉토리 삭제 " + dirName + "/ 디렉토리 삭제");
					f.mkdirs(); // 재생성
				} else {
					logger.info("### 삭제 실패 " + dirName + "/ 디렉토리 삭제");
				}
			} catch (Exception e) {
				// 2023-01-30 TOCTOU_RACE_CONDITION 처리 추가
			}

		}
    	
    	
    	if(multipartRequest.getFile(componentName) != null)
    	{
    		FileManager manager = new FileManager(multipartRequest);
	    	try {
				manager.UploadFile(componentName,dirName,fileName);
			} catch (Exception e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
    	}
    }
    
    public ModelAndView DownloadFile(String fileName,String downloadName) {
    	//<!--2019.04.26 구조적보안진단 다운로드 수정 -->
  		if(downloadName.contains("..")||downloadName.contains("/"))		{
  			ModelAndView mav = new ModelAndView();
  			mav.addObject("redirect_url", "/error.do");
 			mav.setViewName("/redirectPage");
 	
  			return mav;
  		}
  		// 2023-01-30 PATH_TRAVERSAL 처리 추가
		if (fileName != null) {
			fileName = fileName.replaceAll("\\", "");
			fileName = fileName.replaceAll("&", "");
		}
    	File downloadFile = new File(fileName);
    	ModelAndView mvc = new ModelAndView("fileDownloadView","downloadFile",downloadFile);		
    	mvc.addObject("downloadFile",downloadFile);
    	mvc.addObject("egov_download_name",downloadName);
    	
    	return mvc;
    }
    
    @RequestMapping(value = "/support/traffic", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getTrafficMain(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();

		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/support/traffic");
		
		return mav;
	}
    
    @RequestMapping(value = "/support/faq", method = { RequestMethod.POST })
	public ModelAndView getFaq(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		
		logger.info("faq/support/faq");
		
		//메뉴통계
		String menuId = ""; 
		menuId = "503010";
 		
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
		mav.setViewName("/support/faq");
		
		return mav;
	}
   
    
    ///////////////////////////// 자료실 Controller 시작 //////////////////////////////
    // 자료실 쓰기
 	@RequestMapping(value = "/support/dataroom_write", method = { RequestMethod.POST })
 	public ModelAndView getDataRoomWrite(HttpSession session,
 			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
 		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
 		ModelAndView mav = new ModelAndView();
 	 		
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
 		
 		logger.info("자료실 호출 /support/dataroom_write");

 		// 보여질 View 페이지를 설정한다.
 		mav.addObject("flag", "W");
 		mav.setViewName("/support/dataroom_write");
 		
 		return mav;
 	}
 	
    // 자료실 저장(INSERT, UPDATE)
 	@RequestMapping(value = "/support/dataroom_write_register", method = { RequestMethod.POST,
 			RequestMethod.GET })
 	public ModelAndView setDataRoomWrite(HttpSession session,
 			MultipartHttpServletRequest request, HttpServletResponse response, DataRoomVo dataRoomVo, String flag) throws RuntimeException {
 		
 		logger.info(" ### file_id :" + dataRoomVo.getFile_id());
 		logger.info("자료실 저장 /support/dataroom_write_register");
 		logger.info(" dataRoomVo conf value :" + dataRoomVo.getFile_cont() );
 		logger.info(" ### flag :" + flag );
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
 		int result = 0;
 		 		
 		String fileId= "";  // 자료실 ID
 		//신규일때 ID 할당 
 		
 		String fileName = ""; //업로드 파일명 
 		String fileExt = "";  //파일확장자명 
 		
		try{
			if(flag.equals("W")){ 				
//	 			Calendar cal = Calendar.getInstance();
//	 			String tempFileId = cal.get(Calendar.YEAR)+"";
//	 			tempFileId += cal.get(Calendar.MONTH)+1 > 10 ? cal.get(Calendar.MONTH)+1 : "0"+(cal.get(Calendar.MONTH)+1);
//	 			tempFileId += cal.get(Calendar.DATE) > 10 ? cal.get(Calendar.DATE) : "0"+cal.get(Calendar.DATE);
	 			 			 			
	 			HashMap<String,String> map = new HashMap<String,String>();
	 			map.put("mapperInfo", "selectFileId"); //mapper 명
//	 			dataRoomVo.setFile_id(tempFileId); //파라미터 MAX ID 를 갖고 오기 위한 파라미터
	 			
	 			List<DataRoomVo> list = dataRoomService.select(dataRoomVo, map); //자료실ID 할당( yyyMMdd + 001 (3자리 순번) ) 			
	 			map.clear();
	 			
	 			fileId = list.get(0).getFile_id(); //ID 쿼리조회 결과값(MAX + 1)
	 			logger.info(" ### list.get(0).getFile_id() :" + fileId );
	 			dataRoomVo.setFile_id(fileId); //자료실id
	 		} 		
	 		else if(flag.equals("E")){
	 			//수정인 경우
	 			fileId = dataRoomVo.getFile_id();
	 		}
	 			
	 		//파일업로드 경우 파일처리 
	 		if(!request.getFile("upload_file").getOriginalFilename().equals("")){
	 			logger.info(" ### 파일업로드 경우 파일처리 ");
	 			// 2023-07-14 : 모의해킹 취약점 개선
	 			fileName = Global.cleanStringXSS(request.getFile("upload_file").getOriginalFilename());
	 			//String tempFilePath = DefaultContext.getInstance().getPath().replaceAll("\\\\", "/")+"/";
	 			
	 			//실제 파일 저장 경로( WAS ROOT + upload 폴더 + fileId )
	 			//filePath = tempFilePath + "upload/" + fileId + "/"; 	  			
	 			
	 			fileExt = fileName.substring(fileName.lastIndexOf(".") + 1); 			
	 			logger.info("### upload 파일확장자 : " + fileExt);
	 			/*
	 			PropertyUtil util = null;
	 			try {
	 				logger.info("### properties path : " + this.getClass().getResource("/").getPath()+"./conf/extsfilter.properties");
	 				util = new PropertyUtil(this.getClass().getResource("/").getPath()+"./conf/extsfilter.properties");
	 			} catch (IOException e) {
	 				//	//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
                   //    System.out.println("시스템 에러 발생");
	 			}
	 			
	 			String extsfilter = util.getPropertyValue("white_exts");
	 			*/
				if(fileExt.equals("pdf")) {
					logger.info("### File EXT Check Success!");
			 	} else {
			 		logger.info("### BAD EXT!");
			 		
					mav.addObject("redirect_url", "/error.do");
					mav.setViewName("/redirectPage");
					return mav;
			 	}
	 			
	 			/*
	 			Properties prop = new Properties();
	 			
	 			try {
	 				logger.info(" ### File EXT Check ");
	 				prop.load(new FileInputStream(DefaultContext.getInstance().getPath()+"/WEB-INF/conf/extsfilter.properties"));
	 				
	 				String extsfilter = prop.getProperty("white_exts");
	 				if(extsfilter.indexOf(fileExt.toLowerCase()) != -1) {
	 					logger.info("### File EXT Check Success!");
	 		 		} else {
	 		 			logger.info("### BAD EXT!");
	 		 			mav.setViewName("/error");
	 					return mav;
	 		 		}
	 			} catch (NullPointerException e) {
	 				//	//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
                     //    System.out.println("시스템 에러 발생");
	 			} catch (IOException e) {
	 				//	//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
                    //    System.out.println("시스템 에러 발생");
	 			}
	 			*/
	 			dataRoomVo.setFile_name(fileName);
	 			dataRoomVo.setFile_path(filePath);
	 			dataRoomVo.setFile_ext(fileExt);
	 			logger.info("### upload 디렉토리 : " + filePath + " 파일이름 : " + fileName);	
	 	 	}
	 		
	 		//DB 저장
	 		if(flag.equals("W")){ 				 			 			            						 	
	 			result = dataRoomService.insert(dataRoomVo);
	 			logger.info(" 저장 : " + result);
	 		}else if(flag.equals("E")){
	 			HashMap<String, String> map = new HashMap<String, String>();
	 			map.put("mapperInfo", "updateDataRoom");
	 			result = dataRoomService.update(dataRoomVo, map);
	 			logger.info(" 수정 : " + result);
	 		}
			
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
 		//파일 업로드 된 경우 파일업로드 처리
 		if(!fileName.equals("")){
 			logger.info("### 파일 업로드 된 경우 파일업로드 처리");
 			//실제 서버에 저장되는 파일명은 file id 임
 			// /upload/{file_id}/{file_id}+"."+{file_ext}
 			  			 			 	
 			UploadFile("upload_file",filePath, fileId + "." + fileExt,request); //파일업로드 처리	
 		
 		}
 		mav.addObject("redirect_url", "/support/dataroom_list.do");
		mav.setViewName("/redirectPage");
		return mav;
 	}
 	
 	
 	/* 필요유무 확인
 	private void UploadSFTP(File file){
 		
 		String ipAddr = "";
 		try
		{
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();)
			{
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();)
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress())
					{
						ipAddr = inetAddress.getHostAddress().toString();
						
						logger.info("#### HostAddress : " + inetAddress.getHostAddress()
								.toString());		
						logger.info("#### HostName : " + inetAddress.getHostName().toString());
					}
				}
			}
		}
		catch (SocketException e)
		{
			logger.info(e.toString());
		}
 		
 		String destIP = "10.220.177.66";
 		if(ipAddr.equals("10.220.177.66")){
 			destIP = "10.220.177.67";
 		}
 		SFTPUtil sftp = new SFTPUtil(); 		
 		sftp.init(destIP, "jboss", "new1234!", 22);
 		sftp.upload(filePath, file);	
 	}
 	*/
  	 	
 	// 자료실 리스트 호출시 
 	@RequestMapping(value = "/support/dataroom_list", method = { RequestMethod.POST })
 	public ModelAndView getDataRoomList(HttpSession session,
 			HttpServletRequest request, HttpServletResponse response, DataRoomVo dataRoomVo ) throws RuntimeException {
 		logger.info("자료실 호출 /support/dataroom_list");
 		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
 		//<!--2019.04.26 구조적보안진단 파라미터 변조 수정>
 		//세션 사용자 확인
 		if(session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")){
 			mav.addObject("redirect_url", "/member/login.do");
			mav.setViewName("/redirectPage");
 			return mav;
 		}
 		
 		//메뉴통계
		String menuId = ""; 
		menuId = "502010";
 		
 		MenuStateVo menuVo = new MenuStateVo();
 		menuVo.setUser_id(Global.nvl(session.getAttribute("user_id"),""));
 		menuVo.setMenu_id(menuId);		
 		try {
			this.statService.insertMenuState(menuVo);
		} catch (SQLException e1) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
 		
 		String searchTitle = ""; //검색 조건
 		String searchGbn = ""; //검색 조건
	 		
 		//페이징 초기화
 		dataRoomVo.setLimit(10); //출력 행의 갯수		
 		dataRoomVo.setOffSet(0); //출력 첫행 위치
		
 		//검색 하는 경우
 		if(dataRoomVo.getSearch_yn().equals("Y")){ 
 			//검색 버튼 클릭시		 			
 			logger.info(" ### 검색 버튼 클릭시	 ");
 			
 			//검색조건 저장
 			searchTitle = dataRoomVo.getSearch_title(); //검색명
 			searchGbn = dataRoomVo.getSearch_gbn(); //검새구분
 			dataRoomVo.setP_search_title(searchTitle);			 			
 			dataRoomVo.setP_search_gbn(searchGbn);
 			
 			//페이징 초기화
 			dataRoomVo.setPage("1"); //페이징 순번
 		}else{ 			
 			if(dataRoomVo.getPage().equals("1")){
 				//최초로 목록 화면 들어가는 경우
 				logger.info(" ### 최초로 목록 화면 들어가는 경우 ");
 				
 				//검색 조건 초기화, 페이징 초기화 								 	 			
 				dataRoomVo.setOffSet(0);
 			}
 			else{
 				//페이징으로 화면 들어가는 경우
 				logger.info(" ### 페이징으로 화면 들어가는 경우 ");
 				
 				int page = Integer.parseInt(dataRoomVo.getPage());
 				int offset = page * 10 - 10; 				
 				
 				dataRoomVo.setOffSet(offset); 				
 			} 			
 		}
 		
	
		
		try{
			mav.addObject("dataroom_list", this.dataRoomService.select(dataRoomVo));
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		//mav.addObject("dataroom_list", list);
		mav.addObject("dataroomVo", dataRoomVo);
		// 보여질 View 페이지를 설정한다.
		mav.setViewName("/support/dataroom_list");
		
		return mav;		 	
 	}
 	
 // 자료실 리스트 호출시 
  	@RequestMapping(value = "/support/dataroom_view", method = { RequestMethod.POST, 
  			/*RequestMethod.GET*/ }) //2015-01-30 GET 방식만 받도록 수정(장혁준)
  	public ModelAndView getDataRoomView(HttpSession session,
  			HttpServletRequest request, HttpServletResponse response, DataRoomVo dataRoomVo ) throws RuntimeException {
  		logger.info("자료실 호출 /support/dataroom_view");
 		 		 		 		 
 		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
 		ModelAndView mav = new ModelAndView();
 		
		//view 건주증가
		HashMap<String,String> map1 = new HashMap<String,String>();

		map1.put("mapperInfo", "updateDataRoomCnt");
		
		try{

			dataRoomService.update(dataRoomVo, map1);
			map1.clear();
	 		
	 		HashMap<String,String> map = new HashMap<String,String>();
	 		map.put("mapperInfo", "selectDataRoomView");

	 		List<DataRoomVo> list = this.dataRoomService.select(dataRoomVo, map);
	 		map.clear();
	 		
	 		logger.info("### Next_File_ID : " + list.get(0).getNext_file_id() ); 
	 		logger.info("### Prev_File_ID : " + list.get(0).getPrev_file_id());
	 		 		 		
	 		if(list.size() > 0){
	 			
	 			String cont = list.get(0).getFile_cont().replace("\r\n", "<br/>").replace(" ", "&nbsp;");
				list.get(0).setFile_cont(cont);		
				
				mav.addObject("dataRoomVo", list.get(0));
			} 				
	 		
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
 		// 보여질 View 페이지를 설정한다.
 		mav.setViewName("/support/dataroom_view");		

 		return mav;		 	
  	}
  	
  	@RequestMapping(value = "/support/dataroom_edit", method = { RequestMethod.POST,
			/*RequestMethod.GET*/ }) //2015-01-30 GET 방식만 받도록 수정(장혁준)
	public ModelAndView getDataRoomEdit(HttpSession session,
			HttpServletRequest request, HttpServletResponse response, DataRoomVo dataRoomVo) throws RuntimeException {		
		// 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
  		ModelAndView mav = new ModelAndView();
  		
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
 		
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mapperInfo", "selectDataRoomView");
		
		try{

			List<DataRoomVo> list = this.dataRoomService.select(dataRoomVo, map);
			if(list.size() > 0){
				mav.addObject("dataRoomVo", list.get(0));
			}
		}catch(Exception e){
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		
		mav.addObject("flag", "E");
		// 보여질 View 페이지를 설정한다.		
		mav.setViewName("/support/dataroom_write");
		
		return mav;
	}
  	
  	 // 자료실 삭제
  	@RequestMapping(value = "/support/dataroom_delete", method = { RequestMethod.POST })
  	public ModelAndView getPopupDelete(HttpSession session,
  			HttpServletRequest request, HttpServletResponse response, DataRoomVo dataRoomVo) throws RuntimeException {  		
  		ModelAndView mav = new ModelAndView();
				
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
 		 		
 		try{
 			int result = this.dataRoomService.delete(dataRoomVo);
 			logger.info(" 삭제 : " + result);
 			
 		}catch(Exception e){
 			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
 		}
		
		// 보여질 View 페이지를 설정한다.		
		mav.addObject("redirect_url", "/support/dataroom_list.do");
		mav.setViewName("/redirectPage");
		return mav;
  	}
  	
    // select
 	@RequestMapping(value = "/support/file_download", method = { RequestMethod.POST,
 			/*RequestMethod.GET*/ })   //20161202 모의해킹 취약점 - 장혁준
 	public ModelAndView getFileDownload(HttpSession session,
 			HttpServletRequest request, HttpServletResponse response, String file_id, String file_name) throws RuntimeException {
 		//String fileName = "";
 		//String fileNm = URLDecoder.decode(file_name, "utf-8");
 		
 		logger.info( " ### file_id : " + file_id );
 		logger.info( " ### file_name : " + file_name );
 		
 		// 모의 해킹 진단 조치사항 - 다운로드 파일의 위치를 변경하여 주요정보 파일을 받을 수 있는 부분 수정
 		if(file_id.contains("..")||file_id.contains("/"))		{
 			
 			ModelAndView mav = new ModelAndView();
 			mav.addObject("redirect_url", "/support/dataroom_list.do");
			mav.setViewName("/redirectPage");
	
 			return mav;
 		}
 		//return DownloadFile( file_path +file_name ,file_name);
 		return DownloadFile( filePath + file_id ,file_name);
 	}
    /////////////////////////// 자료실 Controller 시작   끝 . //////////////////////////////
 	
 	// select
 	@RequestMapping(value = "/support/board_file_download", method = { RequestMethod.POST,
 			RequestMethod.GET })
 	public ModelAndView getBoardFileDownload(HttpSession session,
 			HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
 		String fileName = "";
 		return DownloadFile(DefaultContext.getInstance().getPath().replaceAll("\\\\","/")+"/upload/"+fileName,fileName);
 	}
 	
 	
 	
}
