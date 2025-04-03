package com.kt.csai.global.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.kt.csai.domain.consult.exception.ConsultServiceUnavailableException;
import com.kt.csai.domain.consult.exception.model.ConsultErrorCode;
import com.kt.csai.global.common.payload.CustomMultipartFile;

@Component
public class ImageUtil {
	
	private final String localPath = "C:\\Users\\KTDS\\Desktop\\tmp\\";
	
	public boolean validationImage(MultipartFile multipartFile) {
		return isSmallerSize(multipartFile);
	}
	
	private boolean isSmallerSize(MultipartFile multipartFile) {
		long limitSize = 1024 * 1024 * 3; 
		boolean isSmaller = multipartFile.getSize() <= limitSize;
		
		if(!isSmaller) {
			throw new ConsultServiceUnavailableException(ConsultErrorCode.INVALID_IMAGE_EXCEPTION);
		}
		return isSmaller;
	}
	
	public CustomMultipartFile base64ToMultipartfile(String fileName, String base64) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(base64.getBytes());
		
		CustomMultipartFile customMultipartFile = new CustomMultipartFile(decodedBytes, fileName);
		return customMultipartFile;
	}
	
	
	public CustomMultipartFile saveFile(String fileName, String base64) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(base64.getBytes());
		
		CustomMultipartFile customMultipartFile = new CustomMultipartFile(decodedBytes, fileName);
		customMultipartFile.transferTo(localPath.concat(fileName));
		return customMultipartFile;
	}
	
	public String getBase64FromMediaUrl() {
		try {
			File file = new File("C:\\Users\\KTDS\\Desktop\\tmp\\test1.mp4");
			InputStream inputStream = new FileInputStream(file);
			byte[] bytes = new byte[inputStream.available()];

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int cur = 0;
			while ((cur = inputStream.read(bytes, 0, bytes.length)) != -1) {
				outputStream.write(bytes, 0, cur);
			}
			inputStream.close();
			outputStream.flush();
			return Base64.getEncoder().encodeToString(outputStream.toByteArray());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}