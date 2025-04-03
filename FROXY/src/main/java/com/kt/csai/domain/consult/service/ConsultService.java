package com.kt.csai.domain.consult.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt.csai.domain.consult.payload.receive.ImageInfo;
import com.kt.csai.domain.consult.payload.receive.ReceiveMessage;
import com.kt.csai.domain.consult.payload.receive.ResultResponse;
import com.kt.csai.domain.consult.payload.receive.UploadResponse;
import com.kt.csai.domain.consult.payload.receive.UserMetadataRequest;
import com.kt.csai.domain.consult.payload.send.Chat;
import com.kt.csai.domain.consult.payload.send.ChatWrite;
import com.kt.csai.domain.consult.payload.send.SendBase;
import com.kt.csai.domain.consult.payload.send.SendFile;
import com.kt.csai.domain.consult.payload.send.SendImage;
import com.kt.csai.domain.consult.payload.send.UploadFile;
import com.kt.csai.domain.consult.payload.send.UploadImage;
import com.kt.csai.global.util.ImageUtil;
import com.kt.csai.global.util.RestUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsultService {

	private final RestUtil restUtil;
	private final HttpHeaders consultHeaders;
	private final FileService fileService;
	
	private final ImageUtil imageUtil;

	@Value("${URL.KT_API.REFERENCE}")
	private String referenceUrl;

	@Value("${URL.KT_API.MESSAGE}")
	private String sendMessageToApiUrl;

	@Value("${URL.KAKAO.SEND_MESSAGE}")
	private String sendMessageToKakaoUrl;
	
	@Value("${URL.KT_API.RESULT}")
	private String sendResultToApiUrl;
	

	public String sendUserMetadataToApi(UserMetadataRequest userMetadataRequest) {
		log.info(userMetadataRequest.toString());
		return restUtil.requestAPI(userMetadataRequest, String.class, referenceUrl, HttpMethod.POST);
	}

	@Retryable(
			value = {Exception.class},
			maxAttempts = 10,
			backoff = @Backoff(delay = 2000),
			recover = "exceptionProcess"
			)
	public void sendMessageToApi(ReceiveMessage receiveMessage) {
		log.info(receiveMessage.toString());
		String receiveType = receiveMessage.getType();
		
		log.info("############## #################");
		
		if ("photo".equals(receiveType) /* || "audio".equals(receiveType) */ || "video".equals(receiveType)) {
			try {
				receiveMessage.setContent(ImageUtil.getBase64FromMediaUrl(receiveMessage.getContents().get(0)));
			} catch (Exception e) {
				log.info("######### EXCEPTION sendMessageToApi ############");
				e.printStackTrace();
			}
			if(receiveMessage.getContent() == null) {
				receiveMessage.setType("text");
				receiveMessage.setContent("이미지 및 동영상 수신이 실패 되었습니다.");
			}
		} else {
			receiveMessage.setContent(receiveMessage.getContents().get(0).toString());
		}
		
		restUtil.requestAPI(receiveMessage, String.class, sendMessageToApiUrl, HttpMethod.POST); 
	}
	
	@Recover
	public static String exceptionProcess(Exception e, ReceiveMessage receiveMessage) {
		log.info("exceptionProcess :: " + e.getMessage());
		return null;
	}

	public String sendMesageToKakao(ChatWrite chatWrite) {
		log.info(chatWrite.toString());
		return restUtil.requestAPI(chatWrite, String.class, sendMessageToKakaoUrl, HttpMethod.POST, consultHeaders);
	}

	public String sendMesageToKakao(Chat chat) {
		log.info(chat.toString());
		return restUtil.requestAPI(chat, String.class, sendMessageToKakaoUrl, HttpMethod.POST, consultHeaders);
	}

	public UploadResponse sendImage(SendImage sendImage, MultipartFile multipartFile) {
		UploadResponse imageUploadResponse = upload(sendImage, multipartFile.getResource());
		return imageUploadResponse;
	}
	
	public UploadResponse sendImage(SendImage sendImage) {
		Resource resource = imageUtil.loadImage(sendImage.getImageName());
		
		UploadResponse imageUploadResponse = upload(sendImage, resource);
		return imageUploadResponse;
	}

	public UploadResponse sendFile(SendBase sendBase, MultipartFile multipartFile) {
		UploadResponse imageUploadResponse = upload(sendBase, multipartFile.getResource());
		return imageUploadResponse;
	}
	
	public String sendResult(ResultResponse resultResponse) {
		return restUtil.requestAPI(resultResponse, String.class, sendResultToApiUrl, HttpMethod.POST);
	}

	private UploadResponse upload(SendBase sendBase, Resource resource) {
		if (sendBase instanceof SendImage) {
			return fileService.uploadImageToKakao(new UploadImage(sendBase, resource));
		} else if (sendBase instanceof SendFile) {
			return fileService.uploadFileToKakao(new UploadFile(sendBase, resource));
		}

		/**
		 * throws
		 */
		return fileService.uploadImageToKakao(new UploadImage(sendBase, resource));
	}
}
