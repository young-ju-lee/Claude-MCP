/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : FileManager.java
* Overview    : 파일업로드
* Description : 파일업로드
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <code> FileManager.java </code>
 * 
 * @author hskim
 * @since 
 * @version 1.0  
 */

public class FileManager extends AbstractView {
	private MultipartHttpServletRequest request = null;
	private File uploadDir;
	private int limitSize;
	private String uploadDirName = "";
	
	public FileManager(){}
	
	public FileManager(MultipartHttpServletRequest request)
	{
		this.request = request;
	}
	
	public void SetUploadDir() {	
		uploadDir = new File(uploadDirName);
	}
    
    public void UploadFile(String componentName,String dirName,String fileName) throws Exception {
    	MultipartFile file = request.getFile(componentName);
    	if(fileName.equals("")){
    		fileName = file.getOriginalFilename().trim();
    	}
    	//<!--2019.04.26 구조적보안진단 업로드 수정>
	    String fileExt = "";  //파일확장자명 
    	
    	fileExt = fileName.substring(fileName.lastIndexOf(".") + 1); 			
			logger.info("### upload3 파일확장자 : " + fileExt);
			
		if(fileExt.equals("pdf")) {
			logger.info("### File EXT Check Success!");
	 	} else {
	 		logger.info("### BAD EXT!");	 		
			return;
	 	}
    	if(uploadDir == null) SetUploadDir();
    	
    	if(file.getSize() > limitSize && limitSize != 0){
    		throw new Exception("the request was rejected because its size ("+file.getSize()+") exceeds the configured maximum ("+limitSize+")");
    	}
    	else
    	{  	
    		// 2023-01-30 PATH_TRAVERSAL 처리 추가
    		if (fileName != null) {
    			fileName = fileName.replaceAll("\\", "");
    			fileName = fileName.replaceAll("&", "");
    		}
	    	String uploadFileName = uploadDir+"/"+dirName+"/"+fileName;	
	    	file.transferTo(new File(dirName+"/",fileName));
    	}
    }
    
	@Override 
	protected void renderMergedOutputModel(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		File file = (File)model.get("downloadFile");
		//<!--2019.04.26 구조적보안진단 다운로드 수정 >
		if(file.getName().contains("..")||file.getName().contains("/")){
			System.out.println("파일 변조 발생============");
  			return;
  		}
		response.setContentType("application/octet-stream");
		response.setContentLength((int)file.length());
		response.setHeader("Content-Transfer-Encoding", "binary");
		String downloadFileName = model.get("egov_download_name") == null ? file.getName() : model.get("egov_download_name") .toString();
		response.setHeader("Content-Disposition", "attachment;fileName=\""+java.net.URLEncoder.encode(downloadFileName,"UTF-8")+"\";");
		OutputStream out = null;
		FileInputStream fis = null;
		
		try
		{
		   out = response.getOutputStream();
		  		  
		   fis = new FileInputStream(file);
		   FileCopyUtils.copy(fis,out);
		}catch(java.io.IOException e){	
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}finally{
		   if(fis != null)
		   {
			   fis.close();			 
		   }		
		   
		   if(out != null){
			   out.flush();	
			   out.close();
		   }
		  
		}		
	}
}
