/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : DefaultContext.java
* Overview    : WAS 필터링
* Description : WAS 필터링 실행한다.(차후에 확장 가능함)
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.common.util;

import java.io.File;

/**
 * <code> InitServlet.java </code>
 * 
 * @author hskim
 * @since 
 * @version 1.0  
 */

public class DefaultContext {
	final File baseDir;
	
    private static DefaultContext instance;

    static void createInstance(String path) {
        instance = new DefaultContext(path);
    }

    public static DefaultContext getInstance() throws NullPointerException{
        if (instance == null) {
            throw new NullPointerException("not yet");
        } else
            return instance;
    }

    

    private DefaultContext(String path) {
        baseDir = new File(path);
    }

    /**
     * ContextPath�� ��ȯ�Ѵ�.
     */

    public String getPath() {
        return baseDir.getPath();
    }
}
