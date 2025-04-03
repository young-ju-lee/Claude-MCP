/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : HTMLUtil.java
* Overview    : html 에서 사용되는 util 클래스 
* Description : html 출력 시 필요한 util을 제공한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.framework.util;

import java.util.HashMap;
import java.util.List;

public class HTMLUtil {
	/**
	   * Html에서 쓰일 SelectBox의 Options를 만드는 Method
	   * @param ArrayList       SelectBox의 Option의 value와 text에 들어가 Data
	   * @param selectedValue   SelectBox에서 선택되어 보여줄 값
	   * @param defaultType     전체항목 추가여부('a'일 경우 전체항목 추가)
	   * @return Html에서 쓰일 SelectBox의 Options 문자열 값
	   * */
	public static String getOptions(
			List<HashMap<String, Object>> dataList,
		    String selectedValue,
		    String defaultType){
	    StringBuffer sb=new StringBuffer();
	        if(dataList == null){
	        	return "";
	        }
	      //전체항목추가
	      if(!"".equals(defaultType)){
	    	  sb.append("\t")
	          .append("<option value='' ");
		     if(selectedValue.compareTo("") ==0)
		    	 sb.append("selected");
			 sb.append(">")
	          .append(defaultType)
	          .append("</option>\n");
	      }
	      
	    if(dataList.size() > 0){
			 for(int i=0; i<dataList.size(); i++){
				 HashMap<String, Object> map = dataList.get(i);
				 sb.append("\t")
		          .append("<option value='").append(map.get("bscode")).append("' ");
			     if(selectedValue.compareTo((String) map.get("bscode")) ==0)
			    	 sb.append("selected");
				 sb.append(">")
		          .append(map.get("bsname"))
		          .append("</option>\n");
				  
		} 
			 } 
			  
		return sb.toString();
	} 
	
	public static String getOptionsJson(
			List<HashMap<String, Object>> dataList,
		    String selectedValue,
		    String defaultType){
	    StringBuffer sb=new StringBuffer();
	        if(dataList == null){
	        	return "";
	        }
	        
	        //var timeHour="0:0;1:1;2:2;3:3;4:4;5:5;6:6;7:7;8:8";
	      //전체항목추가
	      if(!"".equals(defaultType)){
	    	  sb.append(":").append(defaultType).append(";"); 
	      }
	      
	    if(dataList.size() > 0){
			 for(int i=0; i<dataList.size(); i++){
				 HashMap<String, Object> map = dataList.get(i);
				 
				 sb.append(map.get("bscode")+":").append((String) map.get("bsname")).append(";");  
				  
		      }
			 
	     } 
			  
	    
		return sb.toString().substring(0,sb.toString().length()-1);
	} 
	

	/**
	   * Html에서 쓰일 SelectBox를 만드는 Method
	   * @param name          SelectBox이름
	   * @param jsFunction    JavaScript를 call할 Function 이름 ex) changeAcive_yn()
	   * @param etcAttr       Setting할 기타 Select Box Attribute ex) class ='display_type_01'
	   * @param dataSet       SelectBox의 Option에 사용할 정보를 가지고 있는 DataSet
	   * @param selectedValue SelectBox에서 선택되어 보여줄 값
	   * @param startRow      DataSet의 시작 Row Index
	   * @param endRow        DataSet의 종료 Row Index
	   * @param arrValueTextIdx   JavaScript Array에 Setting할 dataSet의 컬럼 Index들의 배열 값
	   * @return Html에서 쓰일 SelectBox의 문자열 값
	   * */
	public static String getSelectBox(
			String name,          String jsFunction,
			String etcAttr,
			List<HashMap<String, Object>> dataList,
		    String selectedValue,
		    String defaultType){
	    StringBuffer sb=new StringBuffer();
	        if(dataList == null){
	        	return "";
	        }
	        
	        sb.append("\n<select ");
			sb.append(" id=\"").append(name).append("\" ");
			sb.append(" name=\"").append(name).append("\" ");
			sb.append(" onChange=").append(jsFunction).append(" ");
			sb.append( etcAttr );
			sb.append(" >\n ");
	      //전체항목추가
	      if(!"".equals(defaultType)){
	    	  sb.append("\t")
	          .append("<option value='' ");
		     if(selectedValue.compareTo("") ==0)
		    	 sb.append("selected");
			 sb.append(">")
	          .append(defaultType)
	          .append("</option>\n");
	      }
	      
	    if(dataList.size() > 0){
			 for(int i=0; i<dataList.size(); i++){
				 HashMap<String, Object> map = dataList.get(i);
				 sb.append("\t")
		          .append("<option value='").append(map.get("bscode")).append("' ");
			     if(selectedValue.compareTo((String) map.get("bscode")) ==0)
			    	 sb.append("selected");
				 sb.append(">")
		          .append(map.get("bsname"))
		          .append("</option>\n");
				  
		} 
			 }
	    sb.append("</select>\n");
			  
		return sb.toString();
	} 
	
	 
	
	
	/**
	   * Html에서 쓰일 SelectBox의 Options를 만드는 Method
	   * @param ArrayList       SelectBox의 Option의 value와 text에 들어가 Data
	   * @param selectedValue   SelectBox에서 선택되어 보여줄 값
	   * @param defaultType      
	   * @return Html에서 쓰일 SelectBox의 Options 문자열 값
	   * */
	
	/*
<li mcode="MA01"><a href="#"><span>BIT본부</span></a>
	<ul>
		<li mcode="MI01"><span class="type1"><!-- 문서 아이콘 --></span><a href="#">BIT기획1팀</a></li>
		<li mcode="MI02"><span class="type2"><!-- 문서 아이콘 --></span><a href="#">BIT기획1팀</a></li>
		<li mcode="MI03"><span class="type3"><!-- 문서 아이콘 --></span><a href="#">BIT기획1팀</a></li>
		<li mcode="MI04"><span class="type4"><!-- 문서 아이콘 --></span><a href="#">BIT기획1팀</a></li>
	</ul>
</li>
*/
	

	public static String getOranization(
			List<HashMap<String, Object>> dataList,
		    String selectedValue,
		    String defaultType){ 
	        if(dataList == null){
	        	return "";
	        } 
 
			    int curLvl = 0; //현재 레벨
			    int preLvl = 0; //이전 레벨
			    int posLvl = 0; //이후 레벨 
		        StringBuffer sb = new StringBuffer();
			    StringBuffer subSb = new StringBuffer(); 
		        sb.append("<ul id=\"browser\" class=\"filetree treeview-famfamfam\">");
		    if(dataList.size() > 0){
				 for(int i=0; i<dataList.size(); i++){
					 HashMap<String, Object> map = dataList.get(i);
					 curLvl = Integer.parseInt((map.get("lvl")+"")); //현재레벨
					 preLvl = Integer.parseInt((map.get("pretree")+"")); //현재레벨
					 posLvl = Integer.parseInt((map.get("posttree")+"")); //현재레벨
					 
					 subSb.setLength(0);
					 for(int j = 0 ; j < curLvl -posLvl  ;j++ ){
						 subSb.append("  </ul> \n"); 
						 subSb.append("  </li> \n"); 
						 }
					  
					 
					 if(i==0){
						 sb.append("<li  mcode="+map.get("treedep1")+"><a href=\"#\"><strong>"+map.get("deptname")+"</strong></a> \n"); 
						 sb.append("  <ul> \n"); 
					 }else if(curLvl < posLvl){
						 sb.append("<li  class=\"closed\" mcode="+map.get("treedep1")+"><span class=\"folder\">");
						 sb.append(" <a href=\"#\" onClick=\"javascript:btnOrgClick('"+map.get("treedep1")+"','"+map.get("deptcd")+"','"+map.get("deptname")+"'); return false;\">");
                         sb.append(map.get("deptname")+"</a></span> \n");
						 sb.append("  <ul> \n"); 
					 }else if(curLvl > posLvl){
						 if(posLvl ==0){ //하위레벨이 업고 제일 마지막 조직인 경우
							 sb.append("<li   mcode="+map.get("treedep1")+"><span class=\"folderclose\">");
							 sb.append(" <a href=\"#\" onClick=\"javascript:btnOrgClick('"+map.get("treedep1")+"','"+map.get("deptcd")+"','"+map.get("deptname")+"'); return false;\">");
							 sb.append(map.get("deptname")+"</a></span></li> \n");
							 sb.append(subSb);  
						 }else{
							 sb.append("<li   mcode="+map.get("treedep1")+"><span class=\"file\">");
							 sb.append(" <a href=\"#\" onClick=\"javascript:btnOrgClick('"+map.get("treedep1")+"','"+map.get("deptcd")+"','"+map.get("deptname")+"'); return false;\">");
							 sb.append(map.get("deptname")+"</a></span></li> \n");
							 sb.append(subSb);
						 }
					 }else{ 
						 if(curLvl == 2){ //level2 이고 하위레벨이 없는 경우
							 sb.append("<li   mcode="+map.get("treedep1")+"><span class=\"folderclose\">");
							 sb.append(" <a href=\"#\" onClick=\"javascript:btnOrgClick('"+map.get("treedep1")+"','"+map.get("deptcd")+"','"+map.get("deptname")+"'); return false;\">");
							 sb.append(map.get("deptname")+"</a></span></li> \n");	 
						 }else{
						 sb.append("<li   mcode="+map.get("treedep1")+"><span class=\"file\">");
						 sb.append(" <a href=\"#\" onClick=\"javascript:btnOrgClick('"+map.get("treedep1")+"','"+map.get("deptcd")+"','"+map.get("deptname")+"'); return false;\">");
						 sb.append(map.get("deptname")+"</a></span></li> \n");
						 }
					 }
				 }      
			}
		    sb.append("</li></ul></li></ul>"); 
			 
		return sb.toString();
	} 
	
	
	public static String getOranization_org(
			List<HashMap<String, Object>> dataList,
		    String selectedValue,
		    String defaultType){
	    StringBuffer sb=new StringBuffer();
	        if(dataList == null){
	        	return "";
	        } 

	        int currentLvl = 0; //현재 레벨
	        int preLvl     = 10; //이전레벨
	        sb.append("<ul><li>");
	    if(dataList.size() > 0){
			 for(int i=0; i<dataList.size(); i++){
				 HashMap<String, Object> map = dataList.get(i);
				 currentLvl = Integer.parseInt((map.get("lvl")+"")); //현재레벨
				 
				 if(currentLvl == preLvl || currentLvl > preLvl){
					 sb.append("<li mcode=\""+map.get("treedep3")+"\">");
					 //sb.append("<span class=\"type1\" onClick=\"javascript:btnOrgClick('"+map.get("treedep1")+"','"+map.get("deptcd")+"','"+map.get("deptname")+"');\">");
					 sb.append("<span class=\"type1\"></span>");
					 sb.append("<a href=\"javascript:btnOrgClick('"+map.get("treedep1")+"','"+map.get("deptcd")+"','"+map.get("deptname")+"');\">"+map.get("deptname")+"</a></li>\n"); 
					 
				 }else{ 
					 sb.append("</ul>\n");
					 sb.append("</li>\n");
					  
					 sb.append("<li mcode=\""+map.get("treedep3")+"\"><a href=\"javascript:btnOrgClick('"+map.get("treedep1")+"','"+map.get("deptcd")+"','"+map.get("deptname")+"');\">");
					 //sb.append("<span onClick=\"javascript:btnOrgClick('"+map.get("treedep1")+"','"+map.get("deptcd")+"','"+map.get("deptname")+"');\">"+map.get("deptname")+"</span></a>\n");
					 sb.append("<span>"+map.get("deptname")+"</span></a>\n");
					  
					 sb.append("<ul>\n");
				 }
				 if(dataList.size()-1 == i){
					 sb.append("</ul>\n"); 
					 sb.append("</li>\n"); 
				 }
				 preLvl = currentLvl;     
				   
			 }      
		}     
		return sb.toString().replace("<ul><li></ul>\n</li>\n","");
	} 
	
	/**
	   * Html에서 쓰일 SelectBox의 Options를 만드는 Method
	   * @param ArrayList       SelectBox의 Option의 value와 text에 들어가 Data
	   * @param selectedValue   SelectBox에서 선택되어 보여줄 값
	   * @param defaultType     전체항목 추가여부('a'일 경우 전체항목 추가)
	   * @return Html에서 쓰일 SelectBox의 Options 문자열 값
	   * */
	public static String getOranizationSelect(
			List<HashMap<String, Object>> dataList,
		    String selectedValue,
		    String defaultType){
	    StringBuffer sb=new StringBuffer();
	        if(dataList == null){
	        	return "";
	        }
	      //전체항목추가
	      if("a".equals(defaultType)){
	    	  sb.append("\t")
	          .append("<option value='' ");
		     if(selectedValue.compareTo("") ==0)
		    	 sb.append("selected");
			 sb.append(">")
	          .append("전체")
	          .append("</option>\n");
	      }
	      
	    if(dataList.size() > 0){
			 for(int i=0; i<dataList.size(); i++){
				 HashMap<String, Object> map = dataList.get(i);
				 sb.append("\t")
		          .append("<option value='").append(map.get("treedep2")).append("' ");
			     if(selectedValue.compareTo((String) map.get("treedep2")) ==0)
			    	 sb.append("selected");
				 sb.append(">")
		          .append(map.get("deptname"))
		          .append("</option>\n");
				  
		} 
			 } 
			  
		return sb.toString();
	} 
	

	/**
	   * Html에서 쓰일 SelectBox의 Options를 만드는 Method
	   * @param ArrayList       SelectBox의 Option의 value와 text에 들어가 Data
	   * @param selectedValue   SelectBox에서 선택되어 보여줄 값
	   * @param defaultType     전체항목 추가여부('a'일 경우 전체항목 추가)
	   * @return Html에서 쓰일 SelectBox의 Options 문자열 값
	   * */
	public static String getDomainOptions(List<HashMap<String, Object>> dataList, String selectedValue, String defaultType){
	    StringBuffer sb=new StringBuffer();
	        if(dataList == null){
	        	return "";
	        }
	      //전체항목추가
	      if(!"".equals(defaultType)){
	    	  sb.append("\t")
	          .append("<option value='' ");
		     if(selectedValue.compareTo("") ==0)
		    	 sb.append("selected");
			 sb.append(">")
	          .append(defaultType)
	          .append("</option>\n");
	      }
	      
	    if(dataList.size() > 0){
			 for(int i=0; i<dataList.size(); i++){
				 HashMap<String, Object> map = dataList.get(i);
				 sb.append("\t")
		          .append("<option value='").append(map.get("cd_domn_id")).append("' ");
			     if(selectedValue.compareTo((String) map.get("cd_domn_id")) ==0)
			    	 sb.append("selected");
				 sb.append(">")
		          .append(map.get("cd_domn_name"))
		          .append("</option>\n");
				  
		} 
			 } 
			  
		return sb.toString();
	} 
}
