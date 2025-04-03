/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : InitServlet.java
* Overview    : WAS 필터링
* Description : WAS 필터링 실행한다.(차후에 확장 가능함)
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.common.util;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <code> InitServlet.java </code>
 * 
 * @author hskim
 * @since 
 * @version 1.0  
 */

public class InitServlet extends javax.servlet.http.HttpServlet implements
        javax.servlet.Servlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6682647505457732491L;

	/**
	 * 
	 */

	public InitServlet() {
        super();
    }

    public void init(ServletConfig arg0) throws ServletException {
        super.init(arg0);
        DefaultContext.createInstance(getServletContext().getRealPath(""));
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }
}
