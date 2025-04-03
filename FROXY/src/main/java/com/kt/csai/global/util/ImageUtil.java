package com.kt.csai.global.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt.csai.domain.consult.payload.receive.ImageInfo;
import com.kt.csai.global.common.payload.CustomMultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageUtil {

	@Value("${IMAGE_DIR}")
	private String imageDir;

	public static String getBase64FromMediaUrl(String content) {
		log.info("### getBase64FromMediaUrl <String> ###");
		
		URL imageUrl;
		try {
			imageUrl = getUrl(content);
			
			URLConnection conn = imageUrl.openConnection();
			
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(15000);
			
			InputStream inputStream = conn.getInputStream();
			byte[] bytes = new byte[inputStream.available()];

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int cur = 0;
			while ((cur = inputStream.read(bytes, 0, bytes.length)) != -1) {
				outputStream.write(bytes, 0, cur);
			}

			outputStream.flush();
			return Base64.getEncoder().encodeToString(outputStream.toByteArray());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Retryable(
			value = {Exception.class},
			maxAttempts = 10,
			backoff = @Backoff(delay = 2000),
			recover = "exceptionProcess"
			)
	public static String getBase64FromMediaUrl(Object obj) throws Exception {
		log.info("### getBase64FromMediaUrl <Object> ###");
		
		URL imageUrl;
		imageUrl = getUrl(obj);
		
		URLConnection conn = imageUrl.openConnection();
		
		conn.setConnectTimeout(15000);
		conn.setReadTimeout(15000);
		
		InputStream inputStream = conn.getInputStream();
		byte[] bytes = new byte[inputStream.available()];

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int cur = 0;
		while ((cur = inputStream.read(bytes, 0, bytes.length)) != -1) {
			outputStream.write(bytes, 0, cur);
		}

		outputStream.flush();
		return Base64.getEncoder().encodeToString(outputStream.toByteArray());
	}
	
	@Recover
	public static String exceptionProcess(Exception e, Object obj) {
		log.info("exceptionProcess :: " + e.getMessage());
		return null;
	}

	private static URL getUrl(String content) throws MalformedURLException {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			String strUrl = objectMapper.readValue(content, Map.class).get("url").toString();
			return new URL(strUrl);

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static URL getUrl(Object object) throws MalformedURLException {
		ObjectMapper objectMapper = new ObjectMapper();
		String strUrl = objectMapper.convertValue(object, ImageInfo.class).getUrl();
		return new URL(strUrl);
	}

	public Resource loadImage(String fileName) {
		String filePath = imageDir.concat(fileName);
		File file = new File(filePath);
		try {
			byte[] bytes = new byte[(int)file.length()];
			
			try(FileInputStream fis = new FileInputStream(file)){
				fis.read(bytes);
			}
			CustomMultipartFile cmf = new CustomMultipartFile(bytes, fileName);
			return cmf.getResource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}