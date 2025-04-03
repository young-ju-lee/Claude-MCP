package com.kt.csai.domain.consult.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kt.csai.domain.consult.payload.receive.ReceiveMessage;
import com.kt.csai.domain.consult.payload.receive.ResultResponse;
import com.kt.csai.domain.consult.payload.receive.UserMetadataRequest;
import com.kt.csai.domain.consult.payload.send.Chat;
import com.kt.csai.domain.consult.payload.send.ChatWrite;
import com.kt.csai.domain.consult.payload.send.SendFile;
import com.kt.csai.domain.consult.payload.send.SendImage;
import com.kt.csai.domain.consult.service.ConsultService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class ConsultController {

	private final ConsultService consultService;

	@PostMapping("/reference")
	public ResponseEntity<?> receiveUserMetaData(@RequestBody UserMetadataRequest userMetadataRequest) {
		return new ResponseEntity<>(consultService.sendUserMetadataToApi(userMetadataRequest), HttpStatus.OK);
	}

	@PostMapping("/message")
	public ResponseEntity<?> receiveMessage(@RequestBody ReceiveMessage receiveMessage) {
		consultService.sendMessageToApi(receiveMessage);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@PostMapping("/result")
	public ResponseEntity<?> receiveResult(@RequestBody ResultResponse resultResponse) {
		log.info("*******" + resultResponse.toString());
		return new ResponseEntity<>(consultService.sendResult(resultResponse), HttpStatus.OK);
	}
	
	@PostMapping("/consult/send/text")
	public ResponseEntity<?> sendMessage(@RequestBody ChatWrite chatWrite) {
		return new ResponseEntity<>(consultService.sendMesageToKakao(chatWrite), HttpStatus.OK);
	}
	
	@PostMapping("/consult/send/image/base64")
	public ResponseEntity<?> sendImage(SendImage sendImage, @RequestParam(name = "image") MultipartFile multipartFile) {
		return new ResponseEntity<>(consultService.sendImage(sendImage, multipartFile), HttpStatus.OK);
	}
	
	@PostMapping("/consult/send/image")
	public ResponseEntity<?> sendImage(@RequestBody SendImage sendImage) {
		return new ResponseEntity<>(consultService.sendImage(sendImage), HttpStatus.OK);
	}
	
	@PostMapping("/consult/send/file")
	public ResponseEntity<?> sendFile(SendFile sendFile, @RequestParam(name = "file") MultipartFile multipartFile) {
		return new ResponseEntity<>(consultService.sendFile(sendFile, multipartFile), HttpStatus.OK);
	}
	
	@PostMapping("/consult/send/message") 
	public ResponseEntity<?> sendMessage(@RequestBody Chat chat) {
		log.debug(chat.toString());
		return new ResponseEntity<>(consultService.sendMesageToKakao(chat), HttpStatus.OK);
	}
}