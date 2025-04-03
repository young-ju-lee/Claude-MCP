/* POR version 1.0
* Copyright @ 2013 kt Inc. All rights reserved.
* This is a proprietary software of kt Inc, and you may not use this file except in compliance
* with license agreement with kt Inc. Any redistribution or use of this software, with or without
* modification shall be strictly prohibited without prior written approval of kt Inc, and
* the copyright notice above does not evidence any actual or intended publication of such software.
*
* File Name   : SFTPUtil.java
* Overview    : SFTP 
* Description : SFTP API 를 제공한다.
* *=====================================================================
* Version    변경  일자       개발자               설명
* ---------- ---------- --------- --------------------------------------
* 1.0.0.0    2013/11/11       강훈병            신규개발
*=====================================================================
*/

package com.ktds.egov.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 서버와 연결하여 파일을 업로드하고, 다운로드한다.
 * 
 * @author haneulnoon
 * @since 2009-09-10
 * 
 */
public class SFTPUtil {
	private Session session = null;

	private Channel channel = null;

	private ChannelSftp channelSftp = null;

	/**
	 * 서버와 연결에 필요한 값들을 가져와 초기화 시킴
	 * 
	 * @param host
	 *            서버 주소
	 * @param userName
	 *            접속에 사용될 아이디
	 * @param password
	 *            비밀번호
	 * @param port
	 *            포트번호
	 */
	public void init(String host, String userName, String password, int port) {
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(userName, host, port);
			session.setPassword(password);

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
		} catch (JSchException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		}

		channelSftp = (ChannelSftp) channel;

	}

	/**
	 * 하나의 파일을 업로드 한다.
	 * 
	 * @param dir
	 *            저장시킬 주소(서버)
	 * @param file
	 *            저장할 파일
	 */
	public void upload(String dir, File file) {

		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			//<!--2019.04.26 구조적보안진단 업로드 수정>
		    String fileExt = "";  //파일확장자명 
	    	String fileName = file.getName();
	    	fileExt = fileName.substring(fileName.lastIndexOf(".") + 1); 			
				System.out.println("### upload4 파일확장자 : " + fileExt);
				
			if(fileExt.equals("pdf")) {
				System.out.println("### File EXT Check Success!");
		 	} else {
		 		System.out.println("### BAD EXT!");	 		
				return;
		 	}
			channelSftp.cd(dir);
			channelSftp.put(in, file.getName());
		} catch (SftpException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		} catch (FileNotFoundException e) {
			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
			System.out.println("시스템 에러 발생");
		} finally {	
			if(in != null)
			try {					
				in.close();
			} catch (IOException e) {
				//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
				System.out.println("시스템 에러 발생");
			}
		}
	}
	
	
	

	/**
	 * 하나의 파일을 다운로드 한다.
	 * 
	 * @param dir
	 *            저장할 경로(서버)
	 * @param downloadFileName
	 *            다운로드할 파일
	 * @param path
	 *            저장될 공간
	 */
//	public void download(String dir, String downloadFileName, String path) {
//		InputStream in = null;
//		FileOutputStream out = null;
//	<!--2019.04.26 구조적보안진단 다운로드 수정>
//	if(downloadFileName.contains("..")||downloadFileName.contains("/"))		{
//           return;	
//       }
//		try {
//			channelSftp.cd(dir);
//			in = channelSftp.get(downloadFileName);
//		} catch (SftpException e) {
//			//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
//	        System.out.println("시스템 에러 발생");
//		}
//
//		try {
//			out = new FileOutputStream(new File(path));
//			int i;
//
//			while ((i = in.read()) != -1) {
//				out.write(i);
//			}
//		} catch (IOException e) {
//	//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
//    System.out.println("시스템 에러 발생");
//		} finally {			
//			try {
//				out.close();
//				in.close();
//			} catch (IOException e) {
//	//<!--2019.04.26 구조적보안진단 오류메세지를 통한 정보노출 수정 -->
//  System.out.println("시스템 에러 발생");
//			}
//
//		}
//
//	}

	/**
	 * 서버와의 연결을 끊는다.
	 */
	public void disconnection() {
		channelSftp.quit();

	}
}
