/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : Global.java
* Overview    : 전역 함수 및 상수 클래스 
* Description : 전역에 필요한 함수 및 상수값을 제공한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.framework.util;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Vector;

import java.sql.*;

import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.*;

import javax.servlet.http.HttpServletRequest;

public class Global {

	public static final String SQL_HOME = System.getProperty("vbb.sql.home");
	public static final String FILE_SEPARATOR = java.io.File.separator;
	public static final String ENC_TYPE = "utf-8";
	public static final String WebRoot = "/ktdsframework";
  /**
	* 페이징 된 페이지의 출력 리스트 수
	*/
  public static final int PRINT_LIST_CNT = 100;

  /**
  * 페이징된 페이지의 출력 페이지수
  */
  public static final int PRINT_PAGE_LIST_CNT = 10;

  /**
  * 현재 페이지의 페이지번호 글자 크기
  */
  public static final String CUR_PAGE_NUM_FONT_SIZE = "2";

  /**
  * 일반 페이지의 페이지번호 글자 크기
  */
  public static final String NORMAL_PAGE_NUM_FONT_SIZE = "2";

  /**
   * 선택된 페이지의 페이지번호 글자 색상
   */
  public static final String CUR_PAGE_NUM_FONT_COLOR = "FF3366";
  
  /**
   * 저장시 테이블 Grid 데이터를 각 row별로 구분하기 위한 구분자
   * pro 페이지에서 구분자를 넣어 setting함.
   */
  public static final char DELIM_ROW = '|';

  /**
   * 저장시 테이블 Grid 데이터를 각 cell별로 구분하기 위한 구분자
   * pro 페이지에서 구분자를 넣어 setting함.
   */
  public static final char DELIM_CELL = '$';

  /**
   * new line 문자
   */
  public static final String NEW_LINE = new String(new char[]{0x0D, 0x0A});
  
  /**
   * 현재 접속중인 사용자 정보 저장
   * */
  public static HashMap memberMap = new HashMap();
  /**
   * 8자리 날짜표현을 지정된 날짜 포맷으로 변환하여 반환한다.
   * @author 
   * @param strDate 8자리 날짜표현
   * @param strPattern 날짜 포맷
   * @throws 숫자포맷에러 java.lang.NumberFormatException 
   * @throws Null값에러 java.lang.NullPointerException
   * @return 지정된 포맷으로 변환된 날짜, 날짜표현이 null이면 빈문자열을 반환한다.
   */
  public static String dateFormat(String strDate, String strPattern)
    throws NumberFormatException, NullPointerException
  {
    // 변환할 날짜가 NULL이거나 빈문자열일 경우
    if (strDate == null || strDate.length() == 0) return "";
    if (strDate.length() < 8) return strDate;
    if (strDate.length() > 8) strDate = strDate.substring(0, 10);

    boolean   bNeedEncode = false;
    char      chDelim     = '-';

    if (strDate.indexOf('/') != -1) { chDelim = '/'; bNeedEncode = true; }
    else if (strDate.indexOf('.') != -1) { chDelim = '.'; bNeedEncode = true; }
    else if (strDate.indexOf('-') != -1) { chDelim = '-'; bNeedEncode = true; }

    // 8자리 날짜로 변환한다.
    if (bNeedEncode) {
      StringBuffer strbufDate = new StringBuffer(strDate);
      int index = 0;

      // 날짜 형식에서 구분기호를 삭제하여 8자리 날짜 문자열로 변환한다.
      do {
        index = strbufDate.toString().indexOf(chDelim);
        if (index != -1) strbufDate.deleteCharAt(index);
      } while (index != -1);
      strDate = strbufDate.toString();
    } // if (bNeedEncode)

    // SimpleDateFormat 객체를 반환받아서, 지정된 날짜 포맷으로 설정한다.
    SimpleDateFormat dateFormatter = (SimpleDateFormat)SimpleDateFormat.getInstance();
    dateFormatter.applyPattern(strPattern);

    // 지정된 날짜값을 가지는 Calendar 객체를 생성한다.
    Calendar  calendar  = Calendar.getInstance();
    int       iYear     = Integer.parseInt(strDate.substring(0, 4));
    int       iMonth    = Integer.parseInt(strDate.substring(4, 6));
    int       iDay      = Integer.parseInt(strDate.substring(6, 8));
    calendar.set(iYear, iMonth-1, iDay);

    return dateFormatter.format(calendar.getTime());
  }

  /**
   * 4자리/6자리 시간표현을 지정된 시간 포맷으로 변환하여 반환한다.
   * @author 
   * @param strTime 4자리/6자리 시간표현
   * @param strPattern 시간 포맷
   * @throws 숫자포맷에러 java.lang.NumberFormatException 
   * @throws Null값에러 java.lang.NullPointerException
   * @return 지정된 포맷으로 변환된 시간, 시간표현이 null이면 빈문자열을 반환한다.
   */
  public static String timeFormat(String strTime, String strPattern)
    throws NumberFormatException, NullPointerException
  {
    // 변환할 시간이 NULL이거나 빈문자열일 경우
    if (strTime == null || strTime.length() == 0) return "";
    if (strTime.length() < 4) return strTime;
    if (strTime.length() > 6) strTime = strTime.substring(0, 6);

    // SimpleDateFormat 객체를 반환받아서, 지정된 시간 포맷으로 설정한다.
    SimpleDateFormat timeFormatter = (SimpleDateFormat)SimpleDateFormat.getInstance();
    timeFormatter.applyPattern(strPattern);

    // 지정된 날짜값을 가지는 Calendar 객체를 생성한다.
    Calendar  calendar  = Calendar.getInstance();
    int iHour   = 0;
    int iMinute = 0;
    int iSecond = 0;

    // 4자리 시간값일 경우
    if (strTime.length() == 4) {
      iHour   = Integer.parseInt(strTime.substring(0, 2));
      iMinute = Integer.parseInt(strTime.substring(2, 4));
    }
    // 6자리 시간값일 경우
    else if (strTime.length() == 6) {
      iHour   = Integer.parseInt(strTime.substring(0, 2));
      iMinute = Integer.parseInt(strTime.substring(2, 4));
      iSecond = Integer.parseInt(strTime.substring(4, 6));
    } // if (strTime.length() == 4)
    calendar.set(Calendar.HOUR_OF_DAY, iHour);
    calendar.set(Calendar.MINUTE, iMinute);
    calendar.set(Calendar.SECOND, iSecond);

    return timeFormatter.format(calendar.getTime());
  }

  /**
   * 숫자를 지정된 포맷으로 변환하여 반환한다.<br>
   * 변환할 숫자문자열이 NULL이거나 빈문자열일 경우 빈문자열을 반환하거나 ""을 반환한다.<br>
	 * ###  : 숫자값이 0일때 "" 리턴<br>
	 * ##0	: 숫자값이 0일때 0 리턴<br>
	 * ###.n# : 숫자값이 0이면 "" 리턴<br>
	 * ###.n0	: 숫자값이 0 이면 .n0리턴<br>
	 * ##0.n#	: 숫자값이 0 이면 0 리턴<br>
	 * ##0.n0	: 숫자값이 0 이면 0.00 리턴<br>
   * @author 
   * @param strNumber 문자열 숫자
   * @param strPattern 숫자 포맷
   * @throws 숫자포맷에러 java.lang.NumberFormatException 
   * @throws Null값에러 java.lang.NullPointerException
   * @return 지정된 포맷으로 변환된 숫자
   */
  public static String numberFormat(String strNumber, String strPattern) throws NumberFormatException, NullPointerException {
		
		if("".equals(NullCheck(strNumber, "")))
			return "";

		return numberFormat(Double.parseDouble(strNumber), strPattern);
  }

  /**
   * 숫자를 지정된 포맷으로 변환하여 반환한다.<br>
   * 변환할 숫자문자열이 NULL이거나 빈문자열일 경우 빈문자열을 반환하거나 ""을 반환한다.<br>
	 * ###  : 숫자값이 0일때 "" 리턴<br>
	 * ##0	: 숫자값이 0일때 0 리턴<br>
	 * ###.n# : 숫자값이 0이면 "" 리턴<br>
	 * ###.n0	: 숫자값이 0 이면 .n0리턴<br>
	 * ##0.n#	: 숫자값이 0 이면 0 리턴<br>
	 * ##0.n0	: 숫자값이 0 이면 0.00 리턴<br>
   * @author 
   * @param val 문자열 숫자
   * @param strPattern 숫자 포맷
   * @throws 숫자포맷에러 java.lang.NumberFormatException 
   * @throws Null값에러 java.lang.NullPointerException
   * @return 지정된 포맷으로 변환된 숫자
   */
	public static String numberFormat(double val, String strPattern) throws NumberFormatException, NullPointerException {
		String rStr = "";
		String format = "#,###";
		DecimalFormat df = null;

    if(!"".equals(NullCheck(strPattern, "")))
			format = strPattern;
		
		int idx = -1;
		String key1 = "";	// 소수점 앞자리 값
		String key2 = "";	// 마지막 값
		if(format.lastIndexOf(".") > -1) {
			idx = format.lastIndexOf(".");
			key1 = format.substring(idx-1, idx);
			key2 = format.substring(format.length()-1, format.length());

			if("#".equals(key1) && "#".equals(key2) && 0 == val)
				return "";
			else {
				df = new DecimalFormat(format);
				rStr = df.format(val);
			}
		}
		else {
			key1 = format.substring(format.length()-1, format.length());
		
			if("#".equals(key1) && 0 == val)
				return "";
			else {
				df = new DecimalFormat(format);
				rStr = df.format(val);
			}
		}

		return rStr;
  }

  /**
   * 숫자를 지정된 포맷으로 변환하여 반환한다.<br>
   * 변환할 숫자문자열이 NULL이거나 빈문자열일 경우 빈문자열을 반환하거나 ""을 반환한다.<br>
	 * ###  : 숫자값이 0일때 "" 리턴<br>
	 * ##0	: 숫자값이 0일때 0 리턴<br>
   * @author 
   * @param strNumber 문자열 숫자
   * @throws 숫자포맷에러 java.lang.NumberFormatException 
   * @throws Null값에러 java.lang.NullPointerException
   * @return 지정된 포맷으로 변환된 숫자
   */
  public static String numberFormat(String strNumber) throws NumberFormatException, NullPointerException {
		
    if("".equals(NullCheck(strNumber, "")))
			return "";		
		
		return numberFormat(Double.parseDouble(strNumber), null);
  }

  /**
   * 숫자를 지정된 포맷으로 변환하여 반환한다.<br>
   * 변환할 숫자문자열이 NULL이거나 빈문자열일 경우 빈문자열을 반환하거나 ""을 반환한다.<br>
	 * ###  : 숫자값이 0일때 "" 리턴<br>
	 * ##0	: 숫자값이 0일때 0 리턴<br>
   * @author 
   * @param val 문자열 숫자
   * @throws 숫자포맷에러 java.lang.NumberFormatException 
   * @throws Null값에러 java.lang.NullPointerException
   * @return 지정된 포맷으로 변환된 숫자
   */
  public static String numberFormat(double val) throws NumberFormatException, NullPointerException {
		
    return numberFormat(val, null);
  }

  /**
   * 문자와 숫자로 이루어진 코드를 지정된 포맷으로 변환하여 반환한다.(오른쪽정렬)<br>
   * 변환할 문자열이 NULL이거나 빈문자열일 경우 빈문자열을 반환한다.
   * @author 
   * @param strCode 코드 문자열(문자/숫자)
   * @param strPattern 코드 포맷
   * @throws Null값에러 java.lang.NullPointerException
   * @return 지정된 포맷으로 변환된 코드를 반환한다.
   */
  public static String codeFormat(String strCode, String strPattern)
    throws NullPointerException
  {
    return codeFormat(strCode, strPattern, false, false, false);
  }

  /**
   * 문자와 숫자로 이루어진 코드를 지정된 포맷으로 변환하여 반환한다.(오른쪽정렬)<br>
   * 변환할 문자열이 NULL이거나 빈문자열일 경우 빈문자열을 반환하거나 패턴문자열을 반환한다.<br>
   * @author 
   * @param strCode 코드 문자열(문자/숫자)
   * @param strPattern 코드 포맷
   * @param bLeftAlign 왼쪽 정렬 여부
   * @param bInitPattern 패턴 문자열 초기화 여부
   * @param bOneByteChars 1바이트 문자 여부
   * @throws Null값에러 java.lang.NullPointerException
   * @return 지정된 포맷으로 변환된 코드를 반환한다.
   */
  public static String codeFormat(String strCode, String strPattern,
                                  boolean bLeftAlign, boolean bInitPattern, boolean bOneByteChars)
    throws NullPointerException
  {
    // 변환할 문자열이 NULL이거나 빈문자열일 경우
    if (strCode == null || strCode.length() == 0) {
      if (bInitPattern) strCode = "";
      else return "";
    }

    StringBuffer strbufCode = new StringBuffer();
    int   iPtnLength  = bLeftAlign ? strPattern.length() : strPattern.length() - 1;
    int   iOrgLength  = bLeftAlign ? strCode.length() : strCode.length() - 1;
    int   iPtnIndex   = 0;
    int   iOrgIndex   = 0;
    char  chLiteral;

    // 왼쪽 정렬로 문자열을 변환할 경우
    if (bLeftAlign) {
      // 패턴 형식에 맞추어서 원본 데이터를 변환한다.
      while (iPtnIndex < iPtnLength) {
        chLiteral = strPattern.charAt(iPtnIndex++);
        if (chLiteral == '#') {
          if (iOrgIndex >= iOrgLength) chLiteral = ' ';
          else chLiteral = strCode.charAt(iOrgIndex++);
          // 1바이트 캐릭터로 포맷을 변환할 경우
          if (bOneByteChars && (chLiteral < 0x00 || chLiteral > 0xFF)) iPtnIndex++;
        }
        strbufCode.append(chLiteral);
      } // while (iPtnIndex < iPtnLength)
      // 패턴 형식보다 원본 데이터가 길 경우, 남은 데이터를 그대로 추가한다.
      while (iOrgIndex < iOrgLength) {
        strbufCode.append(strCode.charAt(iOrgIndex++));
      }

      return strbufCode.toString();
    }
    // 오른쪽 정렬로 문자열을 변환할 경우
    else {
      // 패턴 형식에 맞추어서 원본 데이터를 변환한다.
      while (iPtnLength >= 0) {
        chLiteral = strPattern.charAt(iPtnLength--);
        if (chLiteral == '#') {
          if (iOrgLength < 0) chLiteral = ' ';
          else chLiteral = strCode.charAt(iOrgLength--);
          // 1바이트 캐릭터로 포맷을 변환할 경우
          if (bOneByteChars && (chLiteral < 0x00 || chLiteral > 0xFF)) iPtnLength--;
        }
        strbufCode.append(chLiteral);
      } // while (iPtnLen >= 0)
      // 패턴 형식보다 원본 데이터가 길 경우, 남은 데이터를 그대로 추가한다.
      while (iOrgLength >= 0) {
        strbufCode.append(strCode.charAt(iOrgLength--));
      }

      return strbufCode.reverse().toString();
    } // if (bLeftAlign)
  }

  /**
   * 문자열에 포함된 기호(특수 문자 ', ") 등을 스트림에 출력할 수 있도록 '\'문자를 추가한다.
   * @author 
   * @param strOrigin - 특수 문자가 포함된 문자열
   * @return 특수 문자를 출력할 수 있는 문자열('\' 포함)
   */
  public static String encodeSymbol(String strOrigin)
  {
    if (strOrigin == null) return strOrigin;

    StringBuffer strbufOrigin = new StringBuffer();
    // 기호/특수문자 목록
    char[]  chaSymbol = {'\'', '\"'};
    int     iLength   = chaSymbol.length;
    
    // 등록된 모든 기호/특수문자를 변환한다.
    for (int index=0 ; index < iLength ; index++) {
      int iStrLength = strOrigin.length();
      // 문자열에 포함된 모든 기호/특수문자를 인코딩한다.
      for (int iBegin=0, iSearch=0 ; iBegin < iStrLength ; iBegin = iSearch+1) {
        // 기호/특수문자가 있는 인덱스를 반환받는다.
        iSearch = strOrigin.indexOf(chaSymbol[index], iBegin);
        if (iSearch != -1) {
          strbufOrigin.append(strOrigin.substring(iBegin, iSearch));
          // 기호/특수문자 앞에 '\'문자를 추가한다.
          strbufOrigin.append("\\").append(chaSymbol[index]);
        }
        else {
          iSearch = iStrLength;
          strbufOrigin.append(strOrigin.substring(iBegin, iSearch));
        }
      } // for (int iBegin=0, iSearch=0 ; iBegin < iStrLength ; iBegin = iSearch+1)
      strOrigin = strbufOrigin.toString();
      strbufOrigin.setLength(0);
    } // for (int index=0 ; index < iLength ; index++)

    return strOrigin;
  }

  /**
   * 문자열에 포함된 공백문자를 HTML 페이지에서 표현 가능한 공백문자로 변환하여 반환한다.
   * @author 
   * @param strOrigin 공백문자가 포함된 문자열
   * @return HTML 페이지에서 표현 가능한 공백문자로 변환된 문자열
   */
  public static String encodeWhiteSpace(String strOrigin)
  {
    if (strOrigin == null) return strOrigin;

    StringBuffer strbufOrigin = new StringBuffer();
    int iLength = strOrigin.length();

    // 문자열에 포함된 모든 공백문자를 변환한다.
    for (int iBegin=0, iSearch=0 ; iBegin < iLength ; iBegin = iSearch+1) {
      iSearch = strOrigin.indexOf(' ', iBegin);
      if (iSearch != -1) {
        strbufOrigin.append(strOrigin.substring(iBegin, iSearch));
        // HTML 페이지에서 표현 가능한 공백문자를 추가한다.
        strbufOrigin.append("&nbsp;");
      }
      else {
        iSearch = iLength;
        strbufOrigin.append(strOrigin.substring(iBegin, iSearch));
      }
    } // for (int iBegin=0, iSearch=0 ; iBegin < iLength ; iBegin = iSearch+1)

    return strbufOrigin.toString();
  }

  /**
   * 소수점 아래값을 절삭하여 정수부분을 반환한다.
   * @author 
   * @param dNumber 절삭할 숫자
   * @return 소수점 아래값이 절삭된 숫자
   */
  public static double trunc(double dNumber)
  {
    return trunc(dNumber, 0);
  }
  
  /**
   * 지정된 소수점 자리에서 값을 절삭하여 반환한다.
   * @author 
   * @param dNumber 절삭할 숫자
   * @param iPos 절삭할 자릿수(음수: 정수부분 절삭, 0:소수부분 절삭 , 양수: 소수점 아래부분 절삭)
   * @return 지정된 소수점 자리에서 절삭된 숫자
   */
  public static double trunc(double dNumber, int iPos)
  {
    double dDestNumber;

    // 정수 지정자릿수 아래의 값을 버린다.
    if (iPos < 0) {
      iPos *= -1 * 10;    // 양수로 변환한다.
      dDestNumber = Math.floor(dNumber / iPos) * iPos;
    }
    // 소수점 지정자릿수 아래의 값을 버린다.
    else if (iPos > 0) {
      iPos *= 10;
      dDestNumber = Math.floor(dNumber * iPos) / iPos;
    }
    // 소수점 아래의 값을 버린다.
    else dDestNumber = Math.floor(dNumber);

    return dDestNumber;
  }

  /**
   * 주어진 문자열에서 원하는 문자를 삭제한다.
   * @author 
   * @param strOrigin 처리하고자는 원래 문자열
   * @param gu 문자열에서 삭제하고자하는 문자
   * @return 처리된 문자열
   */
  public static String unSep(String strOrigin,char gu)
  {
    int iLength = strOrigin.length();
    StringBuffer strbufOrigin = new StringBuffer();
    
    for ( int i = 0 ; i < iLength ; i ++)
    {
      if (strOrigin.charAt(i) == gu ) continue;
      strbufOrigin.append(strOrigin.charAt(i));
    }

    return strbufOrigin.toString();
  }
  
  
  //==================================================================
  /**
   * 원하는 문자열을 다른 문자열로 바꾸는 함수
   * @author 
   * @param str 바꿀 문자들이 포함되어 있는 전체 문자열
   * @param changedStr 바꾸고 싶은 문자열
   * @param wantedStr 새로 넣을 문자열(대체문자열)
   * @return 바꾸어져 처리가 끝난 새 문자열
   */
  public static String replace(String str, String changedStr, String wantedStr) {
    int index = 0;
    int length = str.length();
    String tempStr = null;
    while (index < length && (index = str.indexOf(changedStr, index)) != -1) {
        // 바꾸기를 원하는 문자열을 찾았으면
        tempStr = str.substring(0, index) + wantedStr + str.substring(index+changedStr.length());
        str = replace(tempStr, changedStr, wantedStr);
        index += wantedStr.length();
    }

    return str;
  }

	/**
	* 전달된 문자열중  'From'을 'To'로 변환
	* @param String src						전체 문자열
	* @param String from					변환될 문자열배열
	* @param String to						변환시킬 문자열배열
	* @return String							변환된 전체 문자열
	*/

	static public String replace(String src, String[] from, String[] to){
		String res = "";

		if( "".equals( NullCheck(src, "") ) || from == null || to == null)
			return res;

		if(from.length > 0) {
			for(int i = 0; i < from.length && i < to.length; i++) {
				if("".equals(NullCheck(from[i], "")))
					continue;

				res += replaceString(src, from[i], to[i]);
			}
		}
				 
		return res;
	}

	/**
	* 전달된 문자열중  'From'을 'To'로 변환
	* @param String src						전체 문자열
	* @param String from					변환될 문자열
	* @param String to						변환시킬 문자열
	* @return String							변환된 전체 문자열
	*/

	static public String replaceString(String src, String from, String to){
		String res = "";
		int pos=-1;

		if ( "".equals(NullCheck(src, "")) || "".equals(NullCheck(from, "")) ) return src;
		to = NullCheck(to, "");

		while (true) {
			if ((pos = src.indexOf(from)) == -1) {
				res += src;
				break;
			}
			res += src.substring(0, pos) + to;
			src = src.substring(pos + from.length());
		}
      
		return res;
	}

	/**
   * 전체 문자열에서 원하는 위치에 원하는 문자로 바꾸는 함수
   * @author 
   * @param str 바꿀 문자가 포함되어 있는 전체 문자열
   * @param index 문자열에서 바꾸고자 하는 문자의 위치
   * @param ch 새로 넣을 문자(대체문자)
   * @return 바꾸어져 처리가 끝난 새 문자열
   */
  public static String replace(String str, int index, char ch) {
    if (index < 0 || index >= str.length())
        return null;

    return str.substring(0, index++) + ch + str.substring(index); 
  }

  /**
   * 전체 문자열에서 바꾸길 원하는 문자를 새 문자로 바꾸는 함수
   * @author 
   * @param str 바꿀 문자가 포함되어 있는 전체 문자열
   * @param pCh 문자열에서 바꾸고자 하는 문자
   * @param ch 새로 넣을 문자(대체문자)
   * @return 바꾸어져 처리가 끝난 새 문자열
   */  
  public static String replace(String str, char pCh, char ch) {
    //int length = str.length();

    int index = 0;

    while ((index = str.indexOf(pCh)) != -1) {
        str = replace(str, index, ch);
    }

    return str;
  }
  /**
   * 전체 문자열에서 삭제하길 원하는 문자를 없애는 함수
   * @author 
   * @param str 전체 문자열
   * @param removeStr 문자열에서 없애고자 하는 문자
   * @return 삭제 처리가 끝난 새 문자열
   */  
  public static String remove(String str, String removeStr) {
    if(str==null) return null;
    return str.replaceAll(removeStr,"");
  }
  //==================================================================

  /**
   * 전체 문자열에서 바꾸고자하는 문자열을 찾아 다른 문자열들로 변환한다
   * @author 
   * @param str 바꿀 문자가 포함되어 있는 전체 문자열
   * @param parms 새로 넣을 문자열 배열
   * @return 변환된 문자열
   */  
  public static String FormatString(String Str,String[] parms)
  {
    int index = 0;
//    String tempStr = null;
    String changedStr = null;
    int length = 0;
    for(int i=0;i<parms.length;i++)
    {
        changedStr = "%s"+i;
        length = Str.length();
        index = 0;
        boolean isHappened = false;
        while (index < length && (index = Str.indexOf("%s"+i, index)) != -1) {
            if (!isHappened) {
                // 바꾸기를 원하는 문자열을 찾았으면
                Str = Str.substring(0, index) + parms[i] + Str.substring(index+changedStr.length());                
                isHappened = true;
            } else
                break;
        }
    }

    return Str;    
  }

  /**
   * 숫자를 받아 문자 format을 넘겨주는 함수 
   * @author 
   * @param numStr 숫자를 나타내는 문자열
   * @param length format되어지는 문자열의 길이
   * @return 숫자를 나타내는 Format되어진 문자열 - 예) 00012
   */
  public static String getNumberFormat(String numStr, int length) {
      int count = length - numStr.length();
      StringBuffer buffer = new StringBuffer(length);
      for (int i = 0; i < count; i++) {
          buffer.append("0");
      }

      buffer.append(numStr);
      return buffer.toString();
  }

  /**
   * 숫자를 받아 문자 format을 넘겨주는 함수 
   * @author 
   * @param num 문자열 format으로 나타낼 숫자
   * @param length format되어지는 문자열의 길이
   * @return 숫자를 나타내는 Format되어진 문자열 - 예) 00012
   */
  public static String getNumberFormat(int num, int length) {
    return getNumberFormat(String.valueOf(num), length);
  }

  /**
   * 한개의 구분자를 가진 전체 String과 Data의 갯수를 넣으면 각 Data를 원소로 가지고 있는 String[]를 return한다.
   * @author 
   * @param data 한개의 구분자로 Format되어 있는 전체 문자열
   * @param del1 구분자 String
   * @return 각 Data를 포함하고 있는 String을 원소로 하는 전체 String[]를 리턴함
   */
//  public static String[] parseData(String data, char del1) {
//    StringSplit split = new StringSplit(data, del1);
//    String[] resultArray = null;
//    String temp = null;
//    int count = 0;
//    
//    Vector totalRep = new Vector(10);
//    while (split.hasMoreTokens()) {
//        temp = (String)split.nextToken();
//              
//        totalRep.addElement(temp);
//        count++;
//    }
//
//    int size = totalRep.size();
//    for (int i = 0; i < size; i++) {
//      if (i == 0)
//        resultArray = new String[size];
//
//      resultArray[i] = (String)totalRep.get(i);
//    }
//    
//    return resultArray;
//  }

  /**
   * 두개의 Default 구분자를 적용하여 각 Data를 원소로 가지는 String[][]를 return한다.
   * @author 
   * @param data 두개의 구분자로 Format되어 있는 전체 문자열
   * @param 없음
   * @return 각 Data를 포함하고 있는 String[]를 원소로 하는 전체 String[][]를 리턴함
   */
//  public static String[][] parseData(String data) {
//    return parseData(data, DELIM_ROW, DELIM_CELL);
//  }
  
  /**
   * 한개의 구분자를 가진 전체 String과 Data의 갯수를 넣으면 각 Data를 원소로 가지고 있는 1차원 String배열을 return한다.
   * @author 
   * @param data 한개의 구분자로 Format되어 있는 전체 문자열
   * @param del1 구분자 String
   * @param length 하위 구분자에 의해 구분되어진 Data의 갯수
   * @return 각 Data를 포함하고 있는 String을 원소로 하는 전체 String[]를 리턴함
   */
//  public static String[] parseData(String data, char del1, int length) {
//    StringSplit split = new StringSplit(data, del1);
//    String[] resultArray = null;
//    int count = 0;
//    
//    while (split.hasMoreTokens() && count < length) {
//      if (count == 0) {
//        resultArray = new String[length];
//      }
//        
//      resultArray[count] = (String)split.nextToken();
//      count++;
//    }
//
//    for (int i = count; count != 0 && i < length; i++) {
//        resultArray[i] = "";
//    }
//            
//    return resultArray;
//  }

  /**
   * 두개의 구분자를 가진 전체 String을 구분자별로 분리하여 각 Data를 원소로 가지는 2차원 String배열을 return한다.
   * @author 
   * @param data 두개의 구분자로 Format되어 있는 전체 문자열
   * @param del1 상위 구분자 String
   * @param del2 하위 구분자 String
   * @return 각 Data를 포함하고 있는 String[]를 원소로 하는 전체 String[][]를 리턴함
   */
//  public static String[][] parseData(String data, char del1, char del2) {
//    StringSplit split = new StringSplit(data, del1);
//    
//    Vector totalRep = new Vector(20);
//    String[][] resultArray = null;
//    String[] subArray = null;
//    String temp = null;
//    int count = 0, length = 0;
//    
//    while (split.hasMoreTokens()) {
//      temp = (String)split.nextToken();
//
//      if (temp.equals(""))
//        break;
//        
//      subArray = parseData(temp, del2);
//
//      if (count == 0)
//        length = subArray.length;
//      
//      totalRep.addElement(subArray);
//      count++;
//    }
//
//    int size = totalRep.size();
//    for (int i = 0; i < size; i++) {
//      if (i == 0)
//        resultArray = new String[size][length];
//
//      subArray = (String[])totalRep.get(i);
//      for(int j = 0; j < length; j++) {
//        resultArray[i][j] = subArray[j];
//      }
//    }
//    
//    return resultArray;
//  }

  /**
   * 두개의 구분자를 가진 전체 String과 두개의 구분자, 그리고 Data의 갯수를 넣으면 구분자별로 분리하여 
   * 각 Data를 원소로 가지는 2차원 String배열을 return한다.
   * @author 
   * @param data 두개의 구분자로 Format되어 있는 전체 문자열
   * @param del1 상위 구분자 String
   * @param del2 하위 구분자 String
   * @param length 하위 구분자에 의해 구분되어진 Data의 갯수
   * @return 각 Data를 포함하고 있는 String[]를 원소로 하는 전체 String[]를 리턴함
   */
//  public static String[][] parseData(String data, char del1, char del2, int length) {
//    StringSplit split = new StringSplit(data, del1);
//    
//    Vector totalRep = new Vector(20);
//    String[][] resultArray = null;
//    String[] subArray = null;
//    String temp = null;
//    int count = 0;
//    while (split.hasMoreTokens()) {
//      temp = (String)split.nextToken();
//      if (temp.equals(""))
//        break;
//        
//      subArray = parseData(temp, del2, length);
//
//      totalRep.addElement(subArray);
//      count++;
//    }
//
//    int size = totalRep.size();
//    for (int i = 0; i < size; i++) {
//      if (i == 0)
//        resultArray = new String[size][length];
//
//      subArray = (String[])totalRep.get(i);
//      for(int j = 0; j < length; j++) {
//        resultArray[i][j] = subArray[j];
//      }
//    }
//    
//    return resultArray;
//  }
	/**
   * 한개의 구분자를 가진 전체 String과 Data의 갯수를 넣으면 각 Data를 원소로 가지고 있는 int[]를 return한다.
   * @author 
   * @param data 한개의 구분자로 Format되어 있는 전체 문자열
   * @param del1 구분자 String
   * @return 각 Data를 포함하고 있는 String을 원소로 하는 전체 int[]를 리턴함
   */
//  public static int[] parseIntData(String data, char del1) {
//    StringSplit split = new StringSplit(data, del1);
//    int[] resultArray = null;
//    String temp = null;
//    int count = 0;
//    
//    Vector totalRep = new Vector(10);
//    while (split.hasMoreTokens()) {
//        temp = (String)split.nextToken();
//              
//        totalRep.addElement(temp);
//        count++;
//    }
//
//    int size = totalRep.size();
//    for (int i = 0; i < size; i++) {
//      if (i == 0)
//        resultArray = new int[size];
//
//      resultArray[i] = Integer.parseInt(NullCheck((String)totalRep.get(i), "0"));
//    }
//    
//    return resultArray;
//  }

    
  /**
   * 조회한 결과가 1개 밖에 없을 경우에 그 ResultSet의 값을 String배열로 변환하는 함수
   * @author 
   * @param rs 조회한 ResultSet
   * @param length resultSet에 있는 data의 갯수
   * @return 조회한 결과를 담고있는 String 배열(ResultSet이 1 row일 경우만 사용가능함)
   */ 
  public static String[] getArray2ResultSet(ResultSet rs, int length) throws SQLException {
    if (!rs.next())
      return null;
    	
    String[] strArray = new String[length];
    for (int i = 0; i < length; i++) {
          if (rs.getString(i+1) != null)
          strArray[i] = rs.getString(i+1);
        else
          strArray[i] = "";
    }
    	
    return strArray;
  }
    
  /**
   * 조회한 결과의 열을 알고 있을 경우그 ResultSet의 값을 2차원 String배열로 변환하는 함수
   * @author 
   * @param rs 조회한 ResultSet
   * @param rowLen resultSet에 있는 data의 행수
   * @param cellLen resultSet에 있는 1행의 data의 열수
   * @return 조회한 결과를 담고있는 2차원 String 배열(ResultSet에 있는 줄의 수를 알경우만 사용가능함)
   */
  public static String[][] getArray2ResultSet(ResultSet rs, int rowLen, int cellLen) throws SQLException {
    String[][] strArray = null;
    int i = 0;
    while (rs.next()) {
      if (i == 0)
        strArray = new String[rowLen][cellLen];
    		
      for (int j = 0; j < cellLen; j++) {
        if (rs.getString(j+1) != null)
          strArray[i][j] = rs.getString(j+1);
        else
          strArray[i][j] = "";
      }
      i++;
    }
    	
    return strArray;
  }

  /**
   * 조회한 결과의 열을 알고 있을 경우그 ResultSet의 값을 2차원 String배열로 변환하는 함수
   * @author 
   * @param rs 조회한 ResultSet
   * @return 조회한 결과를 담고있는 2차원 String 배열
   */
//  public static String[][] getArray2ResultSet(ResultSet rs) throws SQLException {
//    String[][] resultArray = null;
//    String[] subArray = null;
//    Vector resultVec = getVector2ResultSet(rs);
//
//    int size = (resultVec != null ? resultVec.size() : 0);
//    int length = 0;
//    for (int i = 0; i < size; i++) {
//      subArray = (String[])resultVec.get(i);
//      length = subArray.length;
//      
//      if (i == 0)
//        resultArray = new String[size][length];
//
//      for (int j = 0; j < length; j++) {
//        resultArray[i][j] = subArray[j];
//      }
//    }
//    	
//    return resultArray;
//  }
    
  /**
   * 조회한 ResultSet의 값을 Vector로 변환하는 함수
   * @author 
   * @param rs 조회한 ResultSet
   * @param length resultSet에 있는 1행의 data의 열수
   * @return 조회한 결과를 담고있는 Vector (ResultSet에 있는 줄의 수를 알경우만 사용가능함)
   */
//  public static Vector getVector2ResultSet(ResultSet rs, int length) throws SQLException {
//    Vector strVec = null;
//    String[] strArray = null;
//    int i = 0;
//    while (rs.next()) {
//      if (i == 0)
//        strVec = new Vector(10);
//    		
//      strArray = new String[length];
//      for (int j = 0; j < length; j++) {
//        if (rs.getString(j+1) != null)
//          strArray[j] = rs.getString(j+1);
//        else
//          strArray[j] = "";
//      }
//      strVec.addElement(strArray);
//      i++;
//    }
//    	
//    return strVec;
//  }

  /**
   * 조회한 ResultSet의 값을 Vector로 변환하는 함수
   * @author 
   * @param rs 조회한 ResultSet
   * @return 조회한 결과를 담고있는 Vector
   */
//  public static Vector getVector2ResultSet(ResultSet rs) throws SQLException {
//    Vector strVec = null;
//    ResultSetMetaData rsmd = rs.getMetaData();
//    int length = rsmd.getColumnCount();
//    	
//    return getVector2ResultSet(rs, length);
//  }
  
  /**
   * 입력한 String배열(strArray)을 입력한 초기값(initValue)로 setting하는 함수
   * @author 
   * @param strArray 초기값을 setting할 String 배열
   * @param initValue setting할 초기값
   * @return 정상적으로 처리되었으면 true, 그렇치 않으면 false
   */
  public static boolean initStringArray(String[] strArray, String initValue) {
    if (strArray == null)
      return false;

    int length = strArray.length;
    for (int i = 0; i < length; i++) {
      strArray[i] = initValue;
    }

    return true;
  }

  /**
   * 입력한 String배열(strArray)을 입력한 초기값(initValue)으로 setting하는 함수
   * @author 
   * @param strArray 초기값을 setting할 String 배열
   * @param initValue setting할 초기값
   * @return 정상적으로 처리되었으면 true, 그렇치 않으면 false
   */
  public static boolean initStringArray(String[][] strArray, String initValue) {
    if (strArray == null)
      return false;

    int length = strArray.length;
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < strArray[i].length; j++)
        strArray[i][j] = initValue;
    }

    return true;
  }

  /**
   * 해당 년도 해당 월의 마지막 날짜를 반환한다. 
   * @author 
   * @param year 해당 년도
   * @param month 해당 월
   * @return 해당 년도 해당 월의 마지막 날짜
   */ 
  public static String getLastDay(String year, String month) {
    if (month.equals("01") || month.equals("03") || month.equals("05") || month.equals("07") || month.equals("08") || month.equals("10") || month.equals("12"))
      return "31";
    else if (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11"))
      return "30";
    else {
      int yyyy = 0;
      try {
        yyyy = Integer.parseInt(year);
      } catch (NumberFormatException nfEx) {}
            
      if ((yyyy % 400 == 0) || ((yyyy % 4 == 0) && (yyyy % 100 != 0))) 
        return "29";
      else
        return "28";
    }
  }

  /**
   * 입력한 String배열(dataArray)을 입력한 초기값(initValue)으로 setting하는 함수
   * @author 
   * @param dataArray 초기값을 setting할 2차원 String 배열, 만약 null이면 row X column의 2차원배열을 생성함.
   * @param initValue setting할 초기값
   * @param row 배열의 행수
   * @param column 배열의 열수
   * @return 초기값 initValue로 setting된 2차원 String배열
   */
  public static String[][] initDataArray(String[][] dataArray, String initValue, int row, int column) {
    if (dataArray == null)
      dataArray = new String[row][column];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        if (dataArray[i][j] == null || dataArray[i][j].equals(""))
          dataArray[i][j] = initValue;
      }
    }

    return dataArray;
  }

  /**
   * 입력한 int 배열(numberArray)을 입력한 초기값(initValue)으로 setting하는 함수
   * @author 
   * @param numberArray 초기값을 setting할 2차원 int 배열, 만약 null이면 row X column의 2차원배열을 생성함.
   * @param initValue setting할 초기값
   * @param row 배열의 행수
   * @param column 배열의 열수
   * @return 초기값 initValue로 setting된 2차원 int 배열
   */
  public static int[][] initNumberArray(int[][] numberArray, int initValue, int row, int column) {
    if (numberArray == null)
      numberArray = new int[row][column];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        numberArray[i][j] = initValue;
      }
    }

    return numberArray;
  }

  /**
   * 입력한 double 배열(numberArray)을 입력한 초기값(initValue)으로 setting하는 함수
   * @author 
   * @param numberArray 초기값을 setting할 2차원 int 배열, 만약 null이면 row X column의 2차원배열을 생성함.
   * @param initValue setting할 초기값
   * @param row 배열의 행수
   * @param column 배열의 열수
   * @return 초기값 initValue로 setting된 2차원 double 배열
   */
  public static double[][] initDoubleArray(double[][] numberArray, double initValue, int row, int column) {
    if (numberArray == null)
      numberArray = new double[row][column];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        numberArray[i][j] = initValue;
      }
    }

    return numberArray;
  }

  /**
   * 주어진 문자열을 URLEncoder를 사용하여 인코딩시킨다.
   * @author 
   * @param str 인코딩시킬 문자열
   * @return URLEncoder로 encoding된 문자열
   */  
  public static String encode(String str) {
    if(str != "")
      str = java.net.URLEncoder.encode(str);
          
    return str;
  }

  /**
   * 주어진 문자열을 한글로 인코딩시킨다.
   * @author 
   * @param str 한글로 인코딩시킬 문자열
   * @return 한글로 인코딩된 문자열
   */  
  public static String toHangul(String str) {
    String result = "";

    try {
        result = new String(str.getBytes("8859_1"), "euc-kr");
    } catch (UnsupportedEncodingException ue) {
    	//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
		System.out.println("시스템 에러 발생");
    }

    return result;
  }

  /**
   * 주어진 문자열을 HTML코드로 인코딩시킨다.
   * @author 
   * @param aStr HTML코드로 인코딩시킬 문자열
   * @return HTML코드로 인코딩된 문자열
   */  
  public static String encodeHTML(String aStr) {
    String src = aStr;
    int len = src.length();
    StringBuffer dst = new StringBuffer(len);
    for (int pos=0; pos < len; ++pos) {
      char ch = src.charAt(pos);
      if (ch == '&') {
          dst.append("&amp;");
      }
      else if (ch == '<') {
          dst.append("&lt;");
      }
      else if (ch == '>') {
          dst.append("&gt;");
      }
      else if(ch == '\n')
      {
         dst.append("<br>");
      }
      else {
          dst.append(ch);
      }
    }
    return dst.toString();
  }

  /**
   * HTML인코딩된 문자열을 화면에 보여주기 위해 변환
   * @author :
   * @param str HTML Decode시킬 문자
   * @return HTML로 Decode 된 문자열
   * */
  public static String decodeHTML(String str) {
      str = replaceString(str, "<BR>", "\n");
      str = replaceString(str,"<br>","\n");

      str = replaceString(str, "&amp;", "&");
      str = replaceString(str, "&lt;", "<");
      str = replaceString(str, "&gt;", ">");
      str = replaceString(str, "&acute;", "'");
      str = replaceString(str, "&quot;", "\"");
      str = replaceString(str, "&brvbar;", "|");

      str = replaceString(str, "&nbsp;", " ");

      return str;
  }
  
  /**
   * 전화번호 문자열을 전화번호형식으로 바꿔준다.
   * @author 
   * @param str 전화번호 문자열
   * @return 전화번호형식의 문자열 
   */  
  public static String check_telnum(String str) {
    String loc = "";
    String num1 = "";
    String num2 = "";
		   
    if(str != ""){   
      for (int i=0 ; i < str.length() ; i++) {
        if (str.charAt(i) == ' '){
         return str;
        }
      }
      if(str.length() < 2) {
        return str;
      }
      if(str.substring(0,2).equals("02")){
        if(str.length() == 9){
          loc = str.substring(0,2);
          num1 = str.substring(2,5);
          num2 = str.substring(5,9);
        }else if(str.length() == 10){
          loc = str.substring(0,2);     
          num1 = str.substring(2,6);
          num2 = str.substring(6,10);
        }else {
          return str;
        }
      }
      else {
        if(str.length() == 10){
          loc = str.substring(0,3);     
          num1 = str.substring(3,6);
          num2 = str.substring(6,10);
        }
        else if(str.length() == 11){
          loc = str.substring(0,3);     
          num1 = str.substring(3,7);
          num2 = str.substring(7,11);
        }else {
          return str;
        }
      }
      str = loc + "-" + num1 + "-" + num2;
      return str;
    }
    else {
      str = "";
      return str;
    }
  }

  /**
   * 해당형식의 Date를 반환
   * @author 
   * @param aStr HTML코드로 인코딩시킬 문자열
   * @return HTML코드로 인코딩된 문자열
   */  
  public static  String getTime(String format){
		if ( format == null || format.equals("") == true )
			format = "yyyyMMddHHmmss";

		TimeZone tz = TimeZone.getDefault();
		tz.setRawOffset((60*60*1000) * 9);
		TimeZone.setDefault(tz);
    Calendar cal = Calendar.getInstance(tz);
		java.util.Date date = cal.getTime();
		SimpleDateFormat formater = new SimpleDateFormat(format);
		String timestamp = formater.format(date);
 
		return timestamp; 
	}  

  public static  String[] getStyleInfo(String styleno){

    String[] rvalue = new String[9];
    rvalue[0] = "false";
    
    
		if ( styleno == null || styleno.equals("") == true )
    {
       return rvalue; 
    }
		rvalue[1] = styleno.substring(0,1);//브랜드존
    rvalue[2] = styleno.substring(1,2);//브랜드
    rvalue[3] = styleno.substring(2,3);//라인
    rvalue[4] = styleno.substring(3,5);//아이템
    rvalue[5] = styleno.substring(5,6);//년도
    rvalue[6] = styleno.substring(6,7);//시즌
    rvalue[7] = styleno.substring(7,10);//일련번호
    rvalue[8] = styleno.substring(10,11);//차수구분
  
    rvalue[0] = "true";
 
		return rvalue; 
	}   
  
 /**Null, "" String 값일경우 대치문자열로 return 해준다.
	* @param String paramstr	check할 문자열 
	* @param String repstr		대치할 문자열
	* @return String							 
	*/

  public	static String NullCheck(String paramstr, String repstr){
		if (paramstr == null || paramstr.trim().equals("") || "NULL".equals(paramstr.toUpperCase()))
			return repstr;
		else
			return paramstr.trim();
	}

  /**
   * 오늘 날짜에 특정 일을 더하거나 뺀 결과를 지정한 형식으로 반환한다.
   * @param days 더하거나 뺄 일 수. 오늘보다 과거로 가려면 음수 값을 넣는다.
   * @param format 날짜 문자열 형식 
   * @return String
   */
  public static String getDate(int days, String format) {
    GregorianCalendar gc = new GregorianCalendar();
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    gc.add(Calendar.DATE, days);
    	
    return sdf.format(gc.getTime());
  }
	/**
   * 오늘 날짜에 특정 달을 더하거나 뺀 결과를 지정한 형식으로 반환한다.
   * @param days 더하거나 뺄 일 수. 오늘보다 과거로 가려면 음수 값을 넣는다.
   * @param format 날짜 문자열 형식 
   * @return String
   */
  public static String getAddMonthDate(int val, String format) {
    GregorianCalendar gc = new GregorianCalendar();
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    gc.add(Calendar.MONTH, val);
    	
    return sdf.format(gc.getTime());
  }

	

	/**
    * 특정 날짜에 연도를 더하거나 뺀 결과를 반환한다.
    * @param startDate - 기준 날짜
    * @param years - 더하거나 뺄 연도. 기준 날짜보다 과거로 가고자 한다면 음수 값을 넣는다.
    * @return String
    */
    public static String rollYears( String startDate, String format, int years)
    {
				GregorianCalendar gc = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat(format);
        gc.setTime(stringToDate(startDate, format));
        gc.add(Calendar.YEAR, years);

				return sdf.format(gc.getTime());
    }
 
   /**
    * 특정 날짜에 달을 더하거나 뺀 결과를 반환한다.
    * @param startDate - 기준 날짜
    * @param months - 더하거나 뺄 개월수. 기준 날짜보다 과거로 가고자 한다면 음수 값을 넣는다.
    * @retrn String
    */
    public static String rollMonths( String startDate, String format, int months )
    {
        GregorianCalendar gc = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat(format);
        gc.setTime(stringToDate(startDate, format));
        gc.add(Calendar.MONTH, months);
        return sdf.format(gc.getTime());
    }
 
   /**
    * 특정 날짜에 일을 더하거나 뺀 결과를 반환한다.
    * @param startDate - 기준 날짜
    * @param days - 더하거나 뺄 일수. 기준 날짜보다 과거로 가고자 한다면 음수 값을 넣는다.
    * @return String
    */
    public static String rollDays( String startDate, String format, int days )
    {
        GregorianCalendar gc = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat(format);
        gc.setTime(stringToDate(startDate, format));
        gc.add(Calendar.DATE, days);
        return sdf.format(gc.getTime());
    }


	/**
  * 두 날짜의 차이일수 계산
  * @param earlierDate - 비교날짜1, laterDate - 비교날짜2
  * @return int 날짜 차이값
  */
  public static int daysDiff( String earlierDate, String laterDate, String format )
  {
    if( earlierDate == null || laterDate == null ) return 0;
		Date d1 = stringToDate(earlierDate, format);
		Date d2 = stringToDate(laterDate, format);

    return (int)((d2.getTime() - d1.getTime())/(24*60*60*1000));
  }

	/**
  * 2개의 날짜를 비교할 수 있다. 마찬가지로 시간도 비교할 수 있다.
  * @return -1 : date1 < date2<br>
  *          0 : date1 = date2<br>
  *          1 : date1 > date2
  */
  public static int compareDate( String date1, String date2, String format )
  {
		Calendar c1 = stringToCalendar(date1, format);
		Calendar c2 = stringToCalendar(date2, format);

		return compareDate(c1, c2);
	}

  /**
  * 2개의 날짜를 비교할 수 있다.
  * @return -1 : date1 < date2<br>
  *          0 : date1 = date2<br>
  *          1 : date1 > date2
  */
  public static int compareDate( Date date1, Date date2 )
  {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		return compareDate(c1, c2);
	}
  
	/**
  * 2개의 날짜를 비교할 수 있다.
  * @return -1 : cal1 < cal2<br>
  *          0 : cal1 = cal2<br>
  *          1 : cal1 > cal2
  */
  public static int compareDate( Calendar cal1, Calendar cal2 )
  {
		int value = 9;

		if (cal1.before(cal2)) {
			value = -1;
    }
    if (cal1.after(cal2)) {
      value = 1;     
    }  
    if (cal1.equals(cal2)) {
      value = 0;     
    }
		return value;
	}

  /**
  * 입력한 double 배열(numberArray)을 입력한 초기값(initValue)으로 setting하는 함수
  * @author 
  * @param originStr 원 문자열
  * @param matchStr 비교할 문자열
  * @return 해당 originStr 문자열에서 원하는 matchStr 문자열이 나타나는 갯수
  */
  public static int getMatchCharCount(String originStr, char ch) {
    int index = 0, count = 0;
    int length = originStr.length();
    for ( int i = 0 ; i < length ; i ++) {
      if (originStr.charAt(i) == ch ) count++;
    }
  
    return count;
  }

  /**
   * 입력한 double 배열(numberArray)을 입력한 초기값(initValue)으로 setting하는 함수
   * @author 
   * @param originStr 원 문자열
   * @param matchStr 비교할 문자열
   * @return 해당 originStr 문자열에서 원하는 matchStr 문자열이 나타나는 갯수
   */
  public static int getMatchStrCount(String originStr, String matchStr) {
    int index = 0, count = 0;
    int length = originStr.length();
    while (index < length && (index = originStr.indexOf(matchStr, index)) != -1) {
        // 일치하는 문자열이 존재하므로 count를 1증가시킨다.
      count++;
      index += matchStr.length();
    }
  
    return count;
  }

  /**
   * 입력한 2차원 String배열을 string type으로 오름차순에 따라 sorting하는 메소드
   * @author 
   * @param array sorting할 2차원 문자열 배열
   * @param orderIndex 해당 배열에서 sorting할 문자열 index(0부터 시작)
   * @return 없음
   */
  public static void sortString(String[][] array, int orderIndex) {
    sortString(array, orderIndex, 1);
  }
  
  /**
   * 입력한 2차원 String배열을 string type으로 sorting하는 메소드
   * @author 
   * @param array sorting할 2차원 문자열 배열
   * @param orderIndex 해당 배열에서 sorting할 문자열 index(0부터 시작)
   * @param orderType 해당 문자열 배열을 sorting할 type(1 - 오름차순(ASC), -1 - 내림차순(DESC))
   * @return 없음
   */
  public static void sortString(String[][] array, int orderIndex, int orderType) {
    int compared, minIndex;
    String minValue;
    for(int i = 0; i < array.length; i++) {
      minValue = array[i][orderIndex];
      minIndex = i;
      for (int j = i+1; j < array.length; j++) {
        compared = compareString(array[i][orderIndex], array[j][orderIndex]);
        if (orderType == 1) {
          // 오름차순
          if (compared == 1 && compareString(minValue, array[j][orderIndex]) == 1) {
            minIndex = j;
            minValue = array[j][orderIndex];
          }
        } else {
          // 내림차순
          if (compared == -1 && compareString(minValue, array[j][orderIndex]) == -1) {
            minIndex = j;
            minValue = array[j][orderIndex];
          }
        }
      }
		
      if (i != minIndex) {
        swap(array[i], array[minIndex]);
      }
    }
  }

  /**
   * 입력한 2차원 String배열을 number type으로 오름차순에 따라 sorting하는 메소드
   * @author 
   * @param array sorting할 2차원 문자열 배열
   * @param orderIndex 해당 배열에서 sorting할 문자열 index(0부터 시작)
   * @return 없음
   */
  public static void sortNumber(String[][] array, int orderIndex) {
    sortNumber(array, orderIndex, 1);
  }

  /**
   * 입력한 2차원 String배열을 number type으로 sorting하는 메소드
   * @author 
   * @param array sorting할 2차원 문자열 배열
   * @param orderIndex 해당 배열에서 sorting할 문자열 index(0부터 시작)
   * @param orderType 해당 문자열 배열을 sorting할 type(1 - 오름차순(ASC), -1 - 내림차순(DESC))
   * @return 없음
   */
  public static void sortNumber(String[][] array, int orderIndex, int orderType) {
    int compared, minIndex;
    String minValue;
    for(int i = 0; i < array.length; i++) {
      minValue = array[i][orderIndex];
      minIndex = i;
      for (int j = i+1; j < array.length; j++) {
        compared = compareNumber(array[i][orderIndex], array[j][orderIndex]);
        if (orderType == 1) {
          // 오름차순
          if (compared == 1 && compareNumber(minValue, array[j][orderIndex]) == 1) {
            minIndex = j;
            minValue = array[j][orderIndex];
          }
        } else {
          // 내림차순
          if (compared == -1 && compareNumber(minValue, array[j][orderIndex]) == -1) {
            minIndex = j;
            minValue = array[j][orderIndex];
          }
        }
      }
		
      if (i != minIndex) {
        swap(array[i], array[minIndex]);
      }
    }
  }

  /**
   * 두개의 1차원 String배열의 각 element를 바꾸는 함수
   * @author 
   * @param array1 바꿀 1차원 문자열 배열1
   * @param array2 바꿀 1차원 문자열 배열2
   * @return 없음
   */
  public static void swap(String[] value1, String[] value2) {
    if (value1.length != value2.length) {
      System.out.println("바꿀 두개의 String배열의 길이가 같지 않습니다.");
      return;
    }

    int length = value1.length;
    String tmpValue = null;
    for (int i = 0; i < length; i++) {
      tmpValue = value1[i];
      value1[i] = value2[i];
      value2[i] = tmpValue;
    }
  }

  /**
   * 두개의 String의 크기를 해당 문자의 ascii code값과 길이로 비교하는 메소드
   * @author 
   * @param str1 비교할 문자열1
   * @param str2 비교할 문자열2
   * @return (str1 > str2) - 1, (str1 == str2) - 0, (str1 < str2) - -1 을 return함
   */
  public static int compareString(String str1, String str2) {
    int minLength, maxLength;
    if (str1.length() < str2.length()) {
      minLength = str1.length();
      maxLength = str2.length();
    } else {
      minLength = str2.length();
      maxLength = str1.length();
    }
	
    char ch1, ch2;
    for(int i = 0; i < minLength; i++) {
      ch1 = str1.charAt(i);
      ch2 = str2.charAt(i);
      if (ch1 == ch2)
        continue;
      else if (ch1 < ch2) {
        return -1;
      } else {
        return 1;
      }
    }
	
    // 같은 범위의 String까진 둘다 똑같았기 때문에
    // 각 String의 길이로 비교를 해야한다.
    if (str1.length() > str2.length())
      return 1;
    else if (str1.length() < str2.length())
      return -1;
    else
      return 0;
  }

  /**
   * 두개의 String의 크기를 숫자값으로 비교하는 메소드
   * @author 
   * @param numStr1 비교할 문자열1
   * @param numStr2 비교할 문자열2
   * @return (str1 > str2) - 1, (str1 == str2) - 0, (str1 < str2) - -1 을 return함
   */
  public static int compareNumber(String numStr1, String numStr2) {
    double num1 = Double.parseDouble(numStr1);
    double num2 = Double.parseDouble(numStr2);
    if (num1 > num2)
      return 1;
    else if (num1 > num2)
      return -1;
    else
      return 0;
  }
   /**
     * Date Object를 구하기 위해 String을 그 String의 time format을 이용하여 parse
     * @param dateString 특정 time format으로된 String
     * @param format time format
     * @return 주어진 String으로 된 날짜의 Date Object 
 	*/
	public static Date stringToDate(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
		return sdf.parse(dateString, pos);
	}

  /**
     * Calendar Object를 구하기 위해 String을 그 String의 time format을 이용하여 parse
     * @param dateString 특정 time format으로된 String
     * @param format time format
     * @return 주어진 String으로 된 날짜의 Calendar Object 
 	*/
	public static Calendar stringToCalendar(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
    ParsePosition pos = new ParsePosition(0);
		Date date = sdf.parse(dateString, pos);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

    /**
     * 특정 Date Object를 time format을 이용하여 String 변환
     * @param date 특정 일자의 Date Object
     * @param format time format
     * @return 특정일의 String
 	*/
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return sdf.format(cal.getTime());
	}

    /**
     * 특정 Calendar Object를 time format을 이용하여 String 변환
     * @param cal 특정 일자의 Calendar Object
     * @param format time format
     * @return 특정일의 String
 	*/
	public static String formatCalendar(Calendar cal, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	/**
	* %비율을 사용자가 지정한 형식으로 리턴
	* @param totalCnt	전체 갯수
	* @param cnt		갯수
	* @param format		리턴할 테이타 형식
	* @return String
	*/

	static public String getPercentFormat(double totalCnt, double cnt , String format) throws Exception {
		try {
			
			double d_answerPercent = 0;
      
			if(cnt != 0 )
				d_answerPercent = cnt * 100 / totalCnt;
			else
				d_answerPercent = 0;

			DecimalFormat df = null;

			if (format == null || "".equals(format))
				df = new DecimalFormat(",###.##");
			else
				df = new DecimalFormat(format);

			return df.format(d_answerPercent);
			
		}
		catch (Exception e) {
			throw new Exception();
			
		}
		
	}

	/**
	* %비율을 사용자가 지정한 형식으로 리턴
	* @param totalCnt	전체 갯수
	* @param cnt		갯수
	* @param format		리턴할 테이타 형식
	* @return String
	*/

	static public String getPercentFormat(String totalCnt, String cnt , String format) throws Exception {
		try {
			
			if("".equals(NullCheck(totalCnt, "")) || "".equals(NullCheck(cnt, "")) || "0".equals(NullCheck(cnt, "")) )
				return "0";

			return getPercentFormat(Double.parseDouble(totalCnt), Double.parseDouble(cnt), format);
			
		}
		catch (Exception e) {
			throw new Exception();
		}
		
	}

  
 /**
  * 내용줄임
	* str을 사용자가 지정한만큼 자르고 뒤에 ...을 붙여서 리턴
	* @param str 자를 대상 문자열
	* @param size		자를 크기
	* @return String
	*/
  static public String makeStringShort(String str, int size)
  {
    int len = 0;
    for(int i=0;i<str.length();i++) { 
      if(len >= size) 
        return str.substring(0,i) + "..."; 
      else
        len += str.substring(i,i+1).getBytes().length;
    } 
    return str;
  }


	public static String getResultString(String[][] resultArray) {
    if (resultArray == null)
      return "";

    int rowCount = (resultArray != null) ? resultArray.length : 0;
    int cellCount = 0;
    StringBuffer buffer = new StringBuffer(100*(rowCount+1));
    for (int i = 0; i < rowCount; i++) {
      cellCount = resultArray[i].length;
      for (int j = 0; j < cellCount; j++) {
        buffer.append(resultArray[i][j]);

        //if (j != cellCount-1)
          buffer.append(DELIM_CELL);
      }

      if (i != rowCount-1)
        buffer.append(DELIM_ROW);
    }

    return buffer.toString();
  }

	public static String getResultString(String[] resultArray) {
    if (resultArray == null)
      return "";

    int count = (resultArray != null) ? resultArray.length : 0;
    StringBuffer buffer = new StringBuffer(100*(count+1));
    for (int i = 0; i < count; i++) {
      buffer.append(resultArray[i]);

     // if (i != count-1)
        buffer.append(DELIM_CELL);
    }

    return buffer.toString();
  }
	
	/**
	 * 구분자로 기준으로 스트링을 자름.
	 * */
	public static String SubString(String msg,String stVal,String endVal){
		if(msg == null || "".equals(msg)){
			return "";
		}

		 int st = 0;
		 int ed = 0;
		
		 if(stVal == null || "".equals(stVal)){
			 st = 0;
		 }else{
			 st = msg.indexOf(stVal);
		 }
		 if(endVal == null || "".equals(endVal)){
			 ed = msg.length();
		 }else{
			 ed = msg.indexOf(endVal);
		 } 
		 
		 return msg.substring(st+1,ed);
	}
	/**
	    * 문자열을 받아들여 문자열 각각의 첫줄에 : 를 붙인다.
	    * 게시판에서 reply에 대한 글을 처리할 경우 사용된다.
	    * @param msg 변경할 문자열
	    * @return 변경된 문자열
	    */
	   public static String reContent(String msg){
	      msg = specialChar(msg);
	      StringBuffer sb = new StringBuffer();
	      sb.append(":");
	      for(int i = 0; i < msg.length(); i++)
	      {
	         if(msg.charAt(i) == '\n')
	         {
	            sb.append(msg.charAt(i));
	            sb.append(":");
	         }else
	         {
	            sb.append(msg.charAt(i));
	         }

	      }
	      return sb.toString();
	   }

	   /**
	    * 공백에 대한 처리를 한다.
	    * \n 는 <br>, \t 는 &nbsp;&nbsp;&nbsp; 로 ' ' 는 &nbsp;로 각각 변경한다.
	    * @param msg 변경할 문자열
	    * @return 변경된 문자열
	    */
	   public static String whiteSpace(String msg){
	      StringBuffer sb = new StringBuffer();
	      for(int i = 0; i < msg.length(); i++)
	      {
	         if(msg.charAt(i) == '\n')
	         {
	            sb.append("<br>");
	         }else if(msg.charAt(i) == '\t'){
	            sb.append("&nbsp;&nbsp;&nbsp;");
	         }else if(msg.charAt(i) == ' '){
	            sb.append("&nbsp;");
	         }else
	            sb.append(msg.charAt(i));
	      }
	      return sb.toString();
	   }

	   /**
	    * 문자열을 받아들여 &, ", \, <, > 등의 문자를 &amp;, &quot;, &#039, &lt; , &gt;로 변경한다.
	    * @param msg 변경할 문자열
	    * @return 변경된 문자열
	    */
	   public static String specialChar(String msg){
	      StringBuffer sb = new StringBuffer();
	      for(int i = 0; i < msg.length(); i++)
	      {
	         if(msg.charAt(i) == '&')
	         {
	            sb.append("&amp;");
	         }else if(msg.charAt(i) == '"'){
	            sb.append("&quot;");
	         }else if(msg.charAt(i) == '\''){
	            sb.append("&#039;");
	         }else if(msg.charAt(i) == '<'){
	            sb.append("&lt;");
	         }else if(msg.charAt(i) == '>'){
	            sb.append("&gt;");
	         }
	         else
	            sb.append(msg.charAt(i));
	      }
	      return sb.toString();
	   }

	   public static String nvl(Object obj,String ret){
		   if(obj == null){
			   return ret;
		   }
		   
		   return obj.toString();
	   }
	   
	   public static String getClientIp(HttpServletRequest request){      
		   String clientIp =  request.getHeader("NS-CLIENT-IP");
		   
		   if(clientIp == null || "".equals(clientIp)) { 
			   clientIp =  request.getHeader("HTTP_X_FORWARDED_FOR");
		   }
		   if(clientIp == null ||  clientIp.length() == 0 || clientIp.toLowerCase().equals("unknown")){
			   clientIp =  request.getHeader("REMOTE_ADDR");
		   }
		   if(clientIp == null || clientIp.length() == 0 || clientIp.toLowerCase().equals("unknown")){
			   clientIp =  request.getRemoteAddr();
		   }
		   if(clientIp == null || clientIp.length() == 0 || clientIp.toLowerCase().equals("unknown")){
			   clientIp =  request.getHeader("x-forwarded-ip");        
		   }
		   return clientIp;
	   } 
	   
	   // delete 동기화 함수
	   public synchronized static boolean fileDelete(File file){
		  return file.delete();
	   }
	   // 2023-07-14 : 모의해킹 취약점 개선
	   public static String cleanStringXSS(String value) {    
		    String filstr = "javascript,vbscript,expression,applet,meta,xml,blink,link,style,script,embed,object,iframe,frame,frameset,ilayer,layer,bgsound,title,base,eval,innerHTML,charset,document,string,create,append,binding,alert,msgbox,refresh,cookie,void,href,onabort,onactivae,onafterprint,onafterupdate,onbefore,onbeforeactivate,onbeforecopy,onbeforecut,onbeforedeactivate,onbeforeeditfocus,onbeforepaste,onbeforeprint,onbeforeunload,onbeforeupdate,onblur,onbounce,oncellchange,onchange,onclick,oncontextmenu,oncontrolselect,oncopy,oncut,ondataavailable,ondatasetchanged,ondatasetcomplete,ondblclick,ondeactivate,ondrag,ondragend,ondragenter,ondragleave,ondragover,ondragstart,ondrop,onerror,onerrorupdate,onfilterchange,onfinish,onfocus,onfocusin,onfocusout,onhelp,onkeydown,onkeypress,onkeyup,onlayoutcomplete,onload,onlosecapture,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,onmousewheel,onmove,onmoveend,onmovestart,onpaste,onpropertychange,onreadystatechange,onreset,onresize,onresizeend,onresizestart,onrowenter,onrowexit,onrowsdelete,onrowsinserted,onscroll,onselect,onselectionchange,onselectstart,onstart,onstop,onsubmit,onunload,prompt";
	    	
		    if (!value.equals("")) { 
	    		filstr.replaceAll(" ",""); 
	    		String [] st = filstr.split(","); 
	    		for( int x = 0; x < st.length; x++ ) { 
	    			value = value.replaceAll(st[x], "_"+st[x]+"_"); 
	    		} 
	    		}

	        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");  
	        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");  
	        value = value.replaceAll("'", "&#39;");            
	        value = value.replaceAll("eval\\((.*)\\)", "");  
	        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");  
	        value = value.replaceAll("script", "");
	        value = value.replaceAll("\"","&#34;");
	        
	        return value;  
	    } 
	    
}
