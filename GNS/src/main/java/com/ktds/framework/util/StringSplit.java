/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : StringSplit.java
* Overview    : 문자열 처리 
* Description : 문자열 처리 util 을 제공한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/


package com.ktds.framework.util;
 
/**
 * StringTokenizer 클래스와 같은 기능을 하는 클래스이다. 차이점은 구분자 사이에 문자가 없더라도 빈 문자열을 반환한다.
 */ 
public class StringSplit 
{
  private String  m_strSource   = null;     // 구분자가 포함된 원본 문자열
  private int     m_index       = 0;        // 문자열내 토큰의 시작 인덱스(구분자의 인덱스)
  private int     m_iLength     = 0;        // 문자열의 길이
  private char    m_chDelimiter = ' ';      // 구분자

 /**
  * 디폴트 구분자(' ')로 문자열을 토큰화시킨다.
  * @param strSource 구분자가 포함된 문자열
  * @return 없음
  */  
  public StringSplit(String strSource)
  {
    // 원본 문자열이 null일 경우, 빈 문자열로 대치한다.
    m_strSource = (strSource == null) ? "" : strSource;
    m_iLength = m_strSource.length();
    if (m_iLength > 0) {
      m_index = (m_strSource.charAt(0) == m_chDelimiter) ? 0 : -1;
    }
  }

  /**
   * 지정된 구분자로 문자열을 토큰화시킨다.
   * @param strSource 구분자가 포함된 문자열
   * @param chDelim 구분자
   * @return 없음
   */   
  public StringSplit(String strSource, char chDelim)
  {
    // 원본 문자열이 null일 경우, 빈 문자열로 대치한다.
    m_strSource = (strSource == null) ? "" : strSource;
    m_chDelimiter = chDelim;
    m_iLength = m_strSource.length();
    if (m_iLength > 0) {
      m_index = (m_strSource.charAt(0) == m_chDelimiter) ? 0 : -1;
    }
  }

  /**
   * 남은 토큰이 있는지 검사하여 결과를 반환한다.
   * @return 남은 토큰이 있으면 true를 반환하고, 그렇지 않으면 false를 반환한다.
   */  
  public boolean hasMoreTokens()
  {
    // 새로운 토큰의 시작 인덱스가 문자열내에 있을 경우
    if (m_index < m_iLength) return true;
    else return false;
  }

  /**
   * 구분자로 자른 토큰을 반환한다.
   * @return 구분자로 잘린 토큰
   */  
  public String nextToken()
  {
    String  strToken;
    int     iSearchIndex;   // 구분자의 인덱스

    // 현재 토큰의 시작인덱스부터 문자열의 마지막 인덱스 사이에 구분자가 있는 인덱스를 반환받는다.
    iSearchIndex = m_strSource.indexOf(m_chDelimiter, m_index + 1);
    // 구분자가 없을 경우, 문자열의 마지막 인덱스로 설정한다.
    if (iSearchIndex == -1) iSearchIndex = m_iLength;

    // 한 개의 토큰을 반환받는다.
    strToken = m_strSource.substring(m_index + 1, iSearchIndex);
    m_index = iSearchIndex;

    return strToken;
  }
}
