/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : PageTag2.java
* Overview    : 게시판 페이징
* Description : 게시판 페이징 서비스를 제공한다. 
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.common.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PageTag2 extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String listCount;
	private String pageCount;
	private String funcName;
	private String currPage;
	private String totCount;

	private static final String LIST_COUNT = "10";
	private static final String PAGE_COUNT = "10";
	private static final String FUNC_NAME = "jsGoPage";
	//private final String TOT_COUNT = "totCount";
	//private final String CURR_PAGE = "currPage";

	private int nTotCount;
	private int nLists;
	private int nPages;
	private int nCurrPage;
	private int nCurrZone;

	private static Logger m_logger = LogManager.getLogger(PageTag2.class);

	public int doStartTag() {
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		//HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		JspWriter out = pageContext.getOut();
		try {
			int nLCount = 0;
			if (getListCount() == null)
				nLCount = Integer.parseInt(LIST_COUNT);
			else
				nLCount = Integer.parseInt(getListCount());

			int nPCount = 1;
			if (getPageCount() == null)
				nPCount = Integer.parseInt(PAGE_COUNT);
			else
				nPCount = Integer.parseInt(getPageCount());
			
			if(nPCount == 0) nPCount = 1;

			if (getFuncName() == null)
				setFuncName(FUNC_NAME);
			else
				setFuncName(getFuncName());
			
			if (getTotCount() == null) {
				nTotCount = 0;
				return SKIP_BODY;
			} else {
				nTotCount = Integer.parseInt(getTotCount());
			}
			
			if (getCurrPage() == null || getCurrPage().trim().length() == 0) {
				nCurrPage = 1;
			} else {
				nCurrPage = Integer.parseInt(getCurrPage());
			}			
			

			if (m_logger.isDebugEnabled()) {
				m_logger.debug("nLCount : " + nLCount);
				m_logger.debug("nPCount : " + nPCount);
				m_logger.debug("funcName : " + funcName);
				m_logger.debug("totCount : " + nTotCount);
				m_logger.debug("currPage : " + nCurrPage);
			}
			
			nCurrZone = (int) Math.ceil((double) nCurrPage / (double) nPCount);
			nLists = (int) Math.ceil((double) nTotCount / (double) nLCount);
			nPages = (int) Math.ceil((double) nLists / (double) nPCount);

			StringBuffer sb = new StringBuffer();
			
			sb.append("<table cellspacing=\"0\" cellpadding=\"0\">");
			sb.append("<tr>");
			sb.append("<Td class=\"page\">");
			if (nCurrPage != 1) {
				sb.append("<a href=\"javascript: ");
				sb.append(getFuncName());
				sb.append("('");
				sb.append(1);
				sb.append(
					"')\"><img src=\"/common/img/page_first.gif\" border=\"0\" alt=\"Go to the first page\"></a>&nbsp;&nbsp;");
			} else {
				sb.append("<img src=\"/common/img/page_first_dis.gif\" alt=\"Go to the first page\">&nbsp;&nbsp;");
			}
			
			sb.append("</td>");
			sb.append("\n");
			sb.append("<Td class=\"page\" valign=\"middle\">");
			if (hasPrevZone()) {
				sb.append("<a href=\"javascript: ");
				sb.append(getFuncName());
				sb.append("('");
				sb.append((nCurrPage - nPCount - 1) / nPCount * nPCount + 1);
				sb.append(
					"')\"><img src=\"/common/img/page_prev.gif\" border=\"0\" alt=\"Go to the previous "
						+ nPCount
						+ " pages\"></a>&nbsp;");
			} else {
				sb.append(
					"<img src=\"/common/img/page_prev_dis.gif\" alt=\"Go to the previous "
						+ nPCount
						+ " pages\">&nbsp;");
			}
			
			sb.append("</td>");
			sb.append("\n");
			sb.append("<Td class=\"page\">");

			for (int i = (nCurrZone - 1) * nPCount + 1; i <= (nCurrZone - 1) * nPCount + nPCount; i++) {
				if (i <= nLists) {
					
					if (i > ((nCurrZone - 1) * nPCount + 1)) {
						sb.append("<img src=\"/common/img/page_li.gif\" width=\"3\" height=\"10\" align=\"absmiddle\">");
					}
					
					if (i != nCurrPage) {
						sb.append("&nbsp;<a href=\"javascript: ");
						sb.append(getFuncName());
						sb.append("('");
						sb.append(i);
						sb.append("')\">");
						sb.append(i);
						sb.append("</a>&nbsp;");
					} else {
						sb.append("&nbsp;<font color=\"darkred\">");
						sb.append(i);
						sb.append("</font>&nbsp;");
					}
					
					sb.append("\n");
				}
				
			}
			sb.append("</td>");
			sb.append("\n");
			sb.append("<Td class=\"page\">&nbsp;");
			
			if (hasNextZone()) {
				sb.append("<a href=\"javascript: ");
				sb.append(getFuncName());
				sb.append("('");

				if (nLists <= (nCurrPage + nPCount - 1) / nPCount * nPCount + 1) {
					sb.append(nLists);
				} else {
					sb.append((nCurrPage + nPCount - 1) / nPCount * nPCount + 1);
				}

				sb.append(
					"')\"><img src=\"/common/img/page_next.gif\" border=\"0\" alt=\"Go to the next "
						+ nPCount
						+ " pages\"></a>&nbsp;");
			} else {
				sb.append(
					"<img src=\"/common/img/page_next_dis.gif\" alt=\"Go to the next "
						+ nPCount
						+ " pages\">&nbsp;");
			}
			
			sb.append("</td>");
			sb.append("\n");
			sb.append("<Td class=\"page\">&nbsp;");

			if (nCurrPage != nLists) {
				sb.append("<a href=\"javascript: ");
				sb.append(getFuncName());
				sb.append("('");
				sb.append(nLists);
				sb.append(
					"')\"><img src=\"/common/img/page_last.gif\" border=\"0\" alt=\"Go to the last page\"></a>\n");
			} else {
				sb.append("<img src=\"/common/img/page_last_dis.gif\" border=\"0\"  alt=\"Go to the last page\"></a>\n");
			}
			
			sb.append("</td></tr></table>");
			sb.append("\n");
			

			if (nTotCount > 0)
				out.println(sb.toString());
		} catch (Exception e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}
		return SKIP_BODY;
	}

	public boolean hasPrevPage() {
		if (nCurrPage - 1 >= 1) {
			return (true);
		} else {
			return (false);
		}
	}

	public boolean hasNextPage() {
		if (nCurrPage + 1 <= nLists) {
			return (true);
		} else {
			return (false);
		}
	}

	public boolean hasPrevZone() {
		if ((nCurrZone - 1) >= 1) {
			return (true);
		} else {
			return (false);
		}
	}

	public boolean hasNextZone() {
		if (nCurrZone + 1 <= nPages) {
			return (true);
		} else {
			return (false);
		}
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getListCount() {
		return listCount;
	}
	
	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}
	
	public String getCurrPage() {
		return currPage;
	}
	
	public void setTotCount(String totCount) {
		this.totCount = totCount;
	}
	
	public String getTotCount() {
		return totCount;
	}
	
	public void setListCount(String listCount) {
		this.listCount = listCount;
	}

	public void release() {
		super.release();
		listCount = "10";
		pageCount = "10";
		funcName = "jsGoPage";
	}
}
