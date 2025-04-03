/* GNS-GW version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : PropertyUtil.java
* Overview    : PropertyUtil
* Description : Property 파일에 대한 정의
* *=====================================================================
* Version      변경  일자             개발자             설명
* ----------------------------------------------------------------------
* 1.0.0.0    2013/11/11       김현수            신규개발
*=======================================================================
*/
package com.ktds.egov.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * <code> PropertyUtil.java </code>
 * 
 * @author hskim
 * @since 
 * @version 1.0  
 */

public class PropertyUtil {
	private Properties m_properties;
	
	public PropertyUtil(String sFileName) throws IOException {

		InputStream inputStream;
		try {
			inputStream = new FileInputStream(sFileName);
		} catch (FileNotFoundException e) {
			throw e;
		}
		m_properties = new Properties();
		try {
			m_properties.load(inputStream);
		} catch (IOException e) {
			throw e;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
	}
	
	public String getPropertyValue(String sKey) {
		return m_properties.getProperty(sKey);
	}

	public String getPropertyValue(String sKey, String sDefaultValue) {
		return m_properties.getProperty(sKey, sDefaultValue);
	}

	@SuppressWarnings("unchecked")
	public Enumeration<String> getPropertyNames() {
		return (Enumeration<String>)m_properties.propertyNames();
	}
}
