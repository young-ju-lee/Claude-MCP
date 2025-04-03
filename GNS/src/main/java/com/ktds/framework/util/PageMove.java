/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : PageMove.java
* Overview    : 게시판 페이징 처리
* Description : 게시판 형태 화면에 페이징 처리 로직을 제공한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.framework.util;

import java.util.*;

import com.ktds.framework.util.Global;

/**
 * 리스트 페이징 class
 */
public class PageMove
{
  
 
  /**
   * 페이지 이동시 전달될 파라미터 리스트
   */
	protected Hashtable paramList; 

  /**
   * 전체 리스트 갯수
   */
	protected long totalCnt;

  /**
   * 현재 페이지 번호
   */
	protected int curPage;

  /**
   * 출력될 리스트 갯수
   */
	protected int listCnt;

  /**
   * 출력될 페이지 갯수
   */
	protected int pageCnt;

	/**
   * 이전 페이지 이동 버튼 이미지 경로
   */
	protected String btnPrePath;

  /**
   * 다음 페이지 버튼 이미지 경로
   */
	protected String btnNextPath;

  /**
   * 이전 페이지 이동 버튼(출력페이지 갯수 만큼 이동) 이미지 경로
   */
	protected String btnPrePagePath;

  /**
   * 다음 페이지 이동버튼(출력페이지 갯수 만큼 이동) 이미지 경로
   */
	protected String btnNextPagePath;

  /**
   * 바탕 화면 색상
   */
	protected String bgColor;

  /**
   * 일반 페이지 번호 폰트 색상
   */
	protected String normalPageColor;

  /**
   * 일반 페이지 번호 폰트 크기
   */
	protected String normalPageSize;

  /**
   * 현재 페이지의 페이지 번호 폰트 색상
   */
	protected String selectedPageColor;

  /**
   * 현재 페이지의 페이지 번호 폰트 크기
   */
	protected String selectedPageSize;

  /**
   * 번호별 구별자
   */
	protected String separator;

	/**
   * Paging class 생성자
   * @  param long  totalCnt  전체 글의 갯수
   * @  param int curPage 현재 페이지 번호
   * @  param int listCnt 출력될 리스트 갯수
   * @  param int pageCnt 출력될 페이지 갯수
   */
	public PageMove(long totalCnt, int curPage, int listCnt, int pageCnt) {
		this.totalCnt = totalCnt;
		this.curPage = curPage;
		this.listCnt = listCnt;
		this.pageCnt = pageCnt;
		this.normalPageSize = "9pt";//Global.NORMAL_PAGE_NUM_FONT_SIZE;
		this.selectedPageColor = Global.CUR_PAGE_NUM_FONT_COLOR;
		this.selectedPageSize = Global.CUR_PAGE_NUM_FONT_SIZE;
		this.separator = ".";
    this.normalPageColor= Global.CUR_PAGE_NUM_FONT_COLOR;
	}

  /**
   * Paging class 생성자
   * @  param long  totalCnt  전체 글의 갯수
   * @  param int curPage 현재 페이지 번호
   * @  param int listCnt 출력될 리스트 갯수
   * @  param int pageCnt 출력될 페이지 갯수
   * @  param String  btnPrePath  이전 페이지 이동 버튼 이미지 경로
   * @  param String  btnNextPath 다음 페이지 버튼 이미지 경로
   * @  param String  btnPrePagePath  이전 페이지 이동 버튼(출력페이지 갯수 만큼 이동) 이미지 경로
   * @  param String  btnNextPagePath  다음 페이지 이동버튼(출력페이지 갯수 만큼 이동) 이미지 경로
   * @  param String  bgColor  바탕색상
   * @  param String  normalPageColor 일반 페이지 번호 폰트 색상
   * @  param String  normalPageSize  일반 페이지 번호 폰트 크기
   * @  param String  selectedPageColor  현재 페이지 번호 폰트 색상
   * @  param String  selectedPageSize 현재 페이지 번호 폰트 크기
   * @  param String  separator  페이지 번호별 구분자
   */
	
	public PageMove(long totalCnt, int curPage, int listCnt, int pageCnt, String btnPrePath, String btnNextPath, 
					String btnPrePagePath, String btnNextPagePath, String bgColor, String normalPageColor, String normalPageSize, 
					String selectedPageColor, String selectedPageSize, String separator) {
		
		this.totalCnt = totalCnt;
		this.curPage = curPage;
		this.listCnt = listCnt;
		this.pageCnt = pageCnt;
		this.btnPrePath = Global.NullCheck(btnPrePath,"");
		this.btnNextPath = Global.NullCheck(btnNextPath,"");
		this.btnPrePagePath = Global.NullCheck(btnPrePagePath,"");
		this.btnNextPagePath = Global.NullCheck(btnNextPagePath,"");
		this.bgColor = Global.NullCheck(bgColor,"");
		this.normalPageColor= Global.NullCheck(normalPageColor,"");
		this.normalPageSize = Global.NullCheck(normalPageSize, Global.NORMAL_PAGE_NUM_FONT_SIZE);
		this.selectedPageColor = Global.NullCheck(selectedPageColor, Global.CUR_PAGE_NUM_FONT_COLOR);
		this.selectedPageSize = Global.NullCheck(selectedPageSize, Global.CUR_PAGE_NUM_FONT_SIZE);
		this.separator = Global.NullCheck(separator,".");
	}


  /**
   * 페이징을 생성하여 문자열로 반환한다
   * 출력리스트 갯수, 출력 페이지수 는 설정된 변수 값에 따른다.
   * 이동될 페이지 경로/파일명, 현재페이지 파라미터 이름, 값을 생성한다.
   * 페이지 이동시 전달되어질 name=value 형태의 파라미터를 생성한다.
   * 파라미터 생성은 Hashtable 에 저장된 내용을 String 으로 생성한다.
   * 이동번튼및 색상, 글자 폰트 색상, 크기는 설정된 변수값에 따른다.
   * 페이지 이동 스크립트를 자동생성한다.
   * @ return String
   */
	public String getPageList() { 

		if (curPage <= 0)
			curPage = 1;

		if (listCnt == 0 )
			listCnt = 10;

		long lastPage   = (totalCnt - 1) / listCnt + 1;

		StringBuffer strPageList = new StringBuffer();

		strPageList.append("<table border=0 align=center cellspacing = 0 cellpadding = 0 border = 0 >")
			.append("<tr><td></td>");
			 
    long prevPage   = (curPage - 1 ) / pageCnt * pageCnt;
    
    if ( curPage > pageCnt ) {
        strPageList.append("<td onclick= 'goMastPageMove(")
                   .append(prevPage)
                   .append(",this")                   
                   //.append(")'style='font-size:" + normalPageSize + ";font-color:" + normalPageColor +";cursor:hand'>");
                   .append(")'style='font-size:" + normalPageSize + ";color:" + normalPageColor +";cursor:pointer;width:30px;'>");

          if("".equals(Global.NullCheck(btnPrePagePath, "")))
            strPageList.append("◀&nbsp");
          else 
            strPageList.append("<img src="+btnPrePagePath+" border=0 >");
						
          strPageList.append("</td>");
    }

		int print_page = 0;

		for ( int i = 0; i < pageCnt; i++) {
			print_page = (((curPage - 1) / pageCnt) * pageCnt) + i + 1;

			if(print_page <= lastPage) {
				if(print_page != curPage) {
					strPageList.append("<td onclick= 'goMastPageMove(")
                     .append(print_page)
                     .append(",this")                   
                     //.append(")' style='font-size:" + normalPageSize + ";font-color:" + normalPageColor +";cursor:hand'> ");
                     .append(")' style='font-size:" + normalPageSize + ";color:" + normalPageColor +";cursor:pointer;width:30px;'> ");
						
					if(print_page < 10){						
						strPageList.append("0"+print_page);
					}
					else{
						strPageList.append(print_page);
					}
          strPageList.append(separator);					
					strPageList.append("</td>");
				}else{//해당 선택된 Page
					strPageList.append("<td onclick= 'goMastPageMove(")
                     .append(print_page)
                     .append(",this")
                     //.append(")' style='font-size:" + normalPageSize + ";font-color:" + normalPageColor +";cursor:hand;font-weight:bold'> ");
                     .append(")' style='font-size:" + selectedPageSize + ";color:" + selectedPageColor +";cursor:pointer;font-weight:bold;width:30px;'> ");
					
					if(print_page<10){
						strPageList.append("0"+print_page);
					}
					else{
						strPageList.append(print_page);
					}
          strPageList.append(separator);					
				}
				strPageList.append("</td>");
			}
		}

		long nextPage   = prevPage + pageCnt + 1;
	    
		if ( nextPage <= lastPage ) {
			strPageList.append("<td onclick= 'goMastPageMove(")
                 .append(nextPage)
                 .append(",this")                 
                 //.append(")' style='font-size:" + normalPageSize + ";font-color:" + normalPageColor +";cursor:hand;'> ");
			     .append(")' style='font-size:" + normalPageSize + ";color:" + normalPageColor +";cursor:pointer;width:30px;'> ");

				if("".equals(Global.NullCheck(btnNextPagePath, "")))
					strPageList.append("&nbsp▶");
				else
					strPageList.append("<img src="+btnNextPagePath+" border=0 >");

     	strPageList.append("</td>");
   	}
    
	 strPageList.append("<td></td></tr>")
			       .append("</table>");
		return strPageList.toString();
  }

}
