/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : RequestWrapper.java
* Overview    : CrossSiteScripting 방지 filter 클래스 
* Description : 보안(CrossSiteScripting) 를 방지한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.framework.frame.filter;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletRequestWrapper;  

public final class RequestWrapper extends HttpServletRequestWrapper {  
   private String reqType;  
   public RequestWrapper(HttpServletRequest servletRequest,String reqType) {  
       super(servletRequest);
       this.reqType = reqType;
	    }  
	      
	    public String[] getParameterValues(String parameter) {  
	 
	      String[] values = super.getParameterValues(parameter);  
	      if (values==null)  {  
	                  return null;  
	          }  
	      int count = values.length;  
	      String[] encodedValues = new String[count];  
	      for (int i = 0; i < count; i++) {  
	                 encodedValues[i] = cleanXSS(values[i]);  
	       }    
	      return encodedValues;   
	    }  
	      
	    public String getParameter(String parameter) {  
	          String value = super.getParameter(parameter);  
	          if (value == null) {  
	                 return null;   
	                  }  
	          return cleanXSS(value);  
	    }  
	      
	    public String getHeader(String name) {  
	        String value = super.getHeader(name);  
	        if (value == null)  
	            return null;  
	        return cleanXSS(value);  
	          
	    }  
	 
	    private String cleanXSS(String value) {    
	    		    	
	    	if (!value.equals("")) { 
	    		filstr.replaceAll(" ",""); 
	    		String [] st = filstr.split(","); 
	    		for( int x = 0; x < st.length; x++ ) { 
	    			value = value.replaceAll(st[x], "_"+st[x]+"_"); 
	    		} 
	    		}
 
	     	//2020.09.18  XSS 방어 특수문자 추가 
	    //	value = value.replaceAll("&","& #amp;");
	    //	value = value.replaceAll("\\\\","& #92;");
	    // 	value = value.replaceAll("%","& #37;");
	    	
	    	//2015.02.03 이호남 특수문자 추가 및 post방식에 대해서도 XSS검출 추가
//	    	if(reqType.equals("GET")){
	        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");  
	        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");  
	        value = value.replaceAll("'", "&#39;");            
	        value = value.replaceAll("eval\\((.*)\\)", "");  
	        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");  
	        value = value.replaceAll("script", "");
	        value = value.replaceAll("\"","&#34;");
//	    	} 
	        return value;  
	    }  
	    
	    //2014.12.18 xss 대상 필터링 문자열 추가
	    //String filstr = "javascript,vbscript,expression,applet,meta,xml,blink,link,style,script,embed,object,iframe,frame,frameset,ilayer,layer,bgsound,title,base,eval,innerHTML,charset,document,string,create,append,binding,alert,msgbox,refresh,embed,ilayer,applet,cookie,javascript,void,href,onabort,onactivae,onafterprint, onafterupdate,onbefore,onbeforeactivate,onbeforecopy,onbeforecut,onbeforedeactivate,onbeforeeditfocus,onbeforepaste,onbeforeprint,onbeforeunload,onbeforeupdate,onblur,onbounce,oncellchange,onchange,onclick,oncontextmenu,oncontrolselect,oncopy,oncut,ondataavailable,ondatasetchanged,ondatasetcomplete,ondblclick,ondeactivate,ondrag,ondragend,ondragenter,ondragleave,ondragover,ondragstart,ondrop,onerror,onerrorupdate,onfilterchange,onfinish,onfocus,onfocusin,onfocusout,onhelp,onkeydown,onkeypress,onkeyup,onlayoutcomplete,onload,onlosecapture,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,onmousewheel,onmove,onmoveend,onmovestart,onpaste,onpropertychange,onreadystatechange,onreset,onresize,onresizeend,onresizestart,onrowenter,onrowexit,onrowsdelete,onrowsinserted,onscroll,onselect,onselectionchange,onselectstart,onstart,onstop,onsubmit,onunload"; //필터링 할 문자열
	    // 2023-07-14 : 모의해킹 취약점 개선
	    //String filstr = "javascript,vbscript,expression,applet,meta,xml,blink,link,style,script,embed,object,iframe,frame,frameset,ilayer,layer,bgsound,title,base,eval,innerHTML,charset,document,string,create,append,binding,alert,msgbox,refresh,embed,ilayer,applet,cookie,javascript,void,href,onabort,onactivae,onafterprint, onafterupdate,onbefore,onbeforeactivate,onbeforecopy,onbeforecut,onbeforedeactivate,onbeforeeditfocus,onbeforepaste,onbeforeprint,onbeforeunload,onbeforeupdate,onblur,onbounce,oncellchange,onchange,onclick,oncontextmenu,oncontrolselect,oncopy,oncut,ondataavailable,ondatasetchanged,ondatasetcomplete,ondblclick,ondeactivate,ondrag,ondragend,ondragenter,ondragleave,ondragover,ondragstart,ondrop,onerror,onerrorupdate,onfilterchange,onfinish,onfocus,onfocusin,onfocusout,onhelp,onkeydown,onkeypress,onkeyup,onlayoutcomplete,onload,onlosecapture,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,onmousewheel,onmove,onmoveend,onmovestart,onpaste,onpropertychange,onreadystatechange,onreset,onresize,onresizeend,onresizestart,onrowenter,onrowexit,onrowsdelete,onrowsinserted,onscroll,onselect,onselectionchange,onselectstart,onstart,onstop,onsubmit,onunload,new,function,window";
	    String filstr = "javascript,vbscript,expression,applet,meta,xml,blink,link,style,script,embed,object,iframe,frame,frameset,ilayer,layer,bgsound,title,base,eval,innerHTML,charset,document,string,create,append,binding,alert,msgbox,refresh,cookie,void,href,onabort,onactivae,onafterprint,onafterupdate,onbefore,onbeforeactivate,onbeforecopy,onbeforecut,onbeforedeactivate,onbeforeeditfocus,onbeforepaste,onbeforeprint,onbeforeunload,onbeforeupdate,onblur,onbounce,oncellchange,onchange,onclick,oncontextmenu,oncontrolselect,oncopy,oncut,ondataavailable,ondatasetchanged,ondatasetcomplete,ondblclick,ondeactivate,ondrag,ondragend,ondragenter,ondragleave,ondragover,ondragstart,ondrop,onerror,onerrorupdate,onfilterchange,onfinish,onfocus,onfocusin,onfocusout,onhelp,onkeydown,onkeypress,onkeyup,onlayoutcomplete,onload,onlosecapture,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,onmousewheel,onmove,onmoveend,onmovestart,onpaste,onpropertychange,onreadystatechange,onreset,onresize,onresizeend,onresizestart,onrowenter,onrowexit,onrowsdelete,onrowsinserted,onscroll,onselect,onselectionchange,onselectstart,onstart,onstop,onsubmit,onunload,prompt"; 
	    
	}  
