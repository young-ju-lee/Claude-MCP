package com.kt.csai.domain.consult.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.kt.csai.domain.consult.payload.receive.UploadResponse;
import com.kt.csai.domain.consult.payload.send.UploadFile;
import com.kt.csai.domain.consult.payload.send.UploadImage;
import com.kt.csai.global.util.RestUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
	private final RestUtil restUtil;
	private final HttpHeaders fileUploadHeaders;

	@Value("${URL.KAKAO.IMAGE_UPLOAD}")
	private String imageUploadUrl;
	
	@Value("${URL.KAKAO.FILE_UPLOAD}")
	private String fileUploadUrl;

	public UploadResponse imageUploadToKakao(MultipartFile multipartFile) {
		MultiValueMap<String, Object> mvMap = new LinkedMultiValueMap<>();
		mvMap.add("image", multipartFile.getResource());
		return restUtil.requestAPI(mvMap, UploadResponse.class, imageUploadUrl,
				HttpMethod.POST, fileUploadHeaders);
	}
	
	public UploadResponse uploadImageToKakao(UploadImage imageUpload) {
		return restUtil.requestAPI(imageUpload.toMultiValueMap(), UploadResponse.class, imageUploadUrl,
				HttpMethod.POST, fileUploadHeaders);
	}
	
	public UploadResponse uploadFileToKakao(UploadFile fileUpload) {
		return restUtil.requestAPI(fileUpload.toMultiValueMap(), UploadResponse.class, fileUploadUrl,
				HttpMethod.POST, fileUploadHeaders);
	}
}
