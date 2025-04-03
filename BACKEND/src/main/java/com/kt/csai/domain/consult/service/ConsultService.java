package com.kt.csai.domain.consult.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkRequest;
import com.kt.csai.domain.alarmtalk.service.AlarmTalkDataService;
import com.kt.csai.domain.consult.entity.Consult;
import com.kt.csai.domain.consult.exception.ConsultServiceUnavailableException;
import com.kt.csai.domain.consult.exception.model.ConsultErrorCode;
import com.kt.csai.domain.consult.model.chat.Chat;
import com.kt.csai.domain.consult.model.chat.ChatFactory;
import com.kt.csai.domain.consult.payload.dto.ChatDto;
import com.kt.csai.domain.consult.payload.dto.ConsultDto;
import com.kt.csai.domain.consult.payload.dto.ConsultStatus;
import com.kt.csai.domain.consult.payload.dto.ConsultingDto;
import com.kt.csai.domain.consult.payload.dto.DoneConsult;
import com.kt.csai.domain.consult.payload.receive.ReceiveMessage;
import com.kt.csai.domain.consult.payload.receive.ResultResponse;
import com.kt.csai.domain.consult.payload.receive.SendResponse;
import com.kt.csai.domain.consult.payload.receive.UserMetadataRequest;
import com.kt.csai.domain.consult.payload.send.ChatWrite;
import com.kt.csai.domain.consult.payload.send.SendFile;
import com.kt.csai.domain.consult.payload.send.SendImage;
import com.kt.csai.domain.consult.repository.ConsultCacheRepository;
import com.kt.csai.domain.consult.repository.ConsultRepository;
import com.kt.csai.domain.reservation.service.ReservationService;
import com.kt.csai.global.common.exception.InternalServerException;
import com.kt.csai.global.common.exception.model.ErrorCode;
import com.kt.csai.global.common.payload.CommonResponse;
import com.kt.csai.global.common.payload.CustomMultipartFile;
import com.kt.csai.global.security.util.SecurityUtil;
import com.kt.csai.global.socket.model.ContentType;
import com.kt.csai.global.socket.model.SenderType;
import com.kt.csai.global.socket.payload.SocketMessageDto;
import com.kt.csai.global.socket.service.SocketService;
import com.kt.csai.global.util.CsvUtil;
import com.kt.csai.global.util.ImageUtil;
import com.kt.csai.global.util.RestUtil;
import com.kt.csai.global.util.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultService {

	private final RestUtil restUtil;
	private final ConsultRepository consultRepository;
	private final SocketService socketService;
	private final AlarmTalkDataService alarmTalkDataService;
	private final ConsultCacheRepository consultCacheRepository;
	private final ReservationService reservationService;
	private final ImageUtil fileService;
	private final ModelMapper modelMapper;
	private final CsvUtil csvUtil;

	@Value("${URL.CONSULT.SEND.TEXT}")
	private String sendConsultTextUrl;

	@Value("${URL.CONSULT.SEND.MESSAGE}")
	private String sendConsultMessagetUrl;

	@Value("${URL.CONSULT.SEND.IMAGE}")
	private String sendConsultImageUrl;

	@Value("${URL.CONSULT.SEND.IMAGE-BASE64}")
	private String sendConsultImageBase64Url;

	@Value("${URL.CONSULT.SEND.FILE}")
	private String sendConsultFileUrl;

	public CommonResponse<SendResponse> enteredCustomer(UserMetadataRequest userMetadataRequest) {
		log.info(userMetadataRequest.toString());

		String consultId = userMetadataRequest.getReference().getExtra();
		CommonResponse<SendResponse> response = new CommonResponse<>();

		caching(userMetadataRequest);
		start(userMetadataRequest);
		alarmTalkDataService.receivedAlarmTalk(consultId);

		socketService.sendMessage(consultId, ContentType.TEXT, "상담이 시작되었습니다.",
				SenderType.NOTIFICATION, userMetadataRequest.getUserKey());

		SendResponse sendResponse = sendCreateConsultMessage(userMetadataRequest);
		response.setData(sendResponse);

		return response;
	}

	private void caching(UserMetadataRequest userMetadataRequest) {
		consultCacheRepository.addConsultId(userMetadataRequest);
	}

	private SendResponse sendCreateConsultMessage(UserMetadataRequest userMetadataRequest) {
		ChatWrite chatWrite = new ChatWrite(userMetadataRequest);

		return restUtil.postRequest(sendConsultTextUrl, chatWrite, SendResponse.class);
	}
	
	@Transactional
	private ConsultDto start(UserMetadataRequest metadataRequest) {
		Optional<Consult> consult = consultRepository.findByConsultId(metadataRequest.getReference().getExtra());
		consult.get().start(metadataRequest.getUserKey());
		Consult saveConsult = consultRepository.save(consult.get());
		
		reservationService.doneReservation(saveConsult.getTtId());
		
		return modelMapper.map(saveConsult, ConsultDto.class);
	}

	@Transactional
	public ConsultDto createConsultInfo(AlarmTalkRequest alarmTalkRequest) {

		String consultId = alarmTalkRequest.getConsultId();

		Consult consult = Consult.builder().consultId(consultId)
				.telNum(alarmTalkRequest.getCustomer().getCustTelNo())
				.chatFileName(consultId.concat(".csv"))
				.csttNm(alarmTalkRequest.getSender().getSndNm()).csttCmpNo(alarmTalkRequest.getSender().getSndCmpNo())
				.lscn(alarmTalkRequest.getTrouble().getLscn()).ttId(alarmTalkRequest.getTrouble().getTtId()).custNm(alarmTalkRequest.getCustomer().getCustNm()).build();

		Consult saveConsult = consultRepository.save(consult);
		return modelMapper.map(saveConsult, ConsultDto.class);
	}

	public void receiveMessage(ReceiveMessage receiveMessage) {
		ContentType contentType = ContentType.convertMessageTypeToContentType(receiveMessage.getType());

		String consultId = consultCacheRepository.getConsultId(receiveMessage.getUserKey());
		String content = receiveMessage.getContent();

		socketService.sendMessage(consultId, contentType, content, SenderType.CUSTOMER);
	}

	public CommonResponse<?> doReceiveProcess(ResultResponse resultResponse) {

		String serialNumber = resultResponse.getSerialNumber();
		String requestType = resultResponse.getRequestType();

		if ("0".equals(resultResponse.getCode())) {

			switch (requestType) {
			case "upload":
				Chat chat = ChatFactory.create(resultResponse, consultCacheRepository.removeUserKey(serialNumber));
				log.info("CHAT: " + chat.toString());
				consultCacheRepository.addChat(chat.getSerialNumber(), chat);
				return sendMessage(chat);
			case "write":
				consultCacheRepository.removeChat(serialNumber);
				break;
			default:
				break;
			}
		} else {
			switch (requestType) {
			case "write":
				return retrySendMessage(serialNumber);
			default:
				break;
			}
		}
		return null;
	}

	/*
	 * WEB TO APP
	 */
	public CommonResponse<SendResponse> sendMessage(SocketMessageDto socketMessageDto) {
		
		CommonResponse<SendResponse> response = new CommonResponse<>();
		SendResponse sendResponse = new SendResponse();

		String contentType = socketMessageDto.getContentType();

		if ("TX".equals(contentType)) {
			sendResponse = sendTextMessage(socketMessageDto);
		} else if ("IM-BASE64".equals(contentType)) {
			sendResponse = sendImageBase64Message(socketMessageDto);
		} else if ("IM".equals(contentType)) {
			sendResponse = sendImageMessage(socketMessageDto);
		} else if ("VI".equals(contentType)) {
			sendResponse = sendFileMessage(socketMessageDto);
		} else {
			sendResponse = sendTextMessage(socketMessageDto);
		}

		response.setData(sendResponse);
		
		socketService.sendMessage(socketMessageDto);		

		return response;
	}

	private CommonResponse<SendResponse> sendMessage(Chat chat) {
		CommonResponse<SendResponse> response = new CommonResponse<>();
		SendResponse sendResponse = restUtil.postRequest(sendConsultMessagetUrl, chat, SendResponse.class);
		response.setData(sendResponse);

		return response;
	}

	private CommonResponse<SendResponse> retrySendMessage(String serialNumber) {

		Chat chat = consultCacheRepository.removeChat(serialNumber);
		if (chat.isLimit()) {
			String userKey = chat.getUserKey();
			String consultId = consultCacheRepository.getConsultId(userKey);
			
			socketService.sendMessage(consultId, ContentType.TEXT, "채팅 전송이 실패 했습니다.",
					SenderType.NOTIFICATION, userKey);
			return null;
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		chat.generateSerialNumber();
		chat.increaseRetryCount();
		consultCacheRepository.addChat(chat.getSerialNumber(), chat);
		return sendMessage(chat);
	}

	public CommonResponse<List<ConsultDto>> findAllByTelNum(String telNum) {
		List<Consult> consultEntities = consultRepository.findAllByTelNum(telNum);
		CommonResponse<List<ConsultDto>> response = new CommonResponse<>();
		
		List<ConsultDto> consults = consultEntities.stream().map(consultEntity -> modelMapper.map(consultEntity, ConsultDto.class))
				.collect(Collectors.toList());
		
//		for(ConsultDto consult : consults) {
//			consult.setCustNm(StringUtil.maskingForName(consult.getCustNm()));
//			consult.setTelNum(StringUtil.maskingForTel(consult.getTelNum()));
//			consult.setCsttNm(StringUtil.maskingForName(consult.getCsttNm()));
//			consult.setCsttCmpNo(StringUtil.maskingForEmployeeNumber(consult.getCsttCmpNo()));
//		}
		
		response.setData(consults);

		return response;
	}

	private ConsultDto findById(String id) {
		Optional<Consult> consultEntity = consultRepository.findById(Long.valueOf(id));

		if (consultEntity.isPresent()) {
			return modelMapper.map(consultEntity, ConsultDto.class);
		}

		throw new InternalServerException(ErrorCode.INVALID_REQUEST);
	}

	public CommonResponse<List<ChatDto>> readChatData(String id) {
		CommonResponse<List<ChatDto>> response = new CommonResponse<>();
		ConsultDto consultDto = findById(id);
		String fileName = consultDto.getChatFileName();

		List<String[]> chatHistOrigin = csvUtil.readChat(fileName);
		List<ChatDto> chatHist = new ArrayList<>();
		for (String[] chat : chatHistOrigin) {
			if (chat.length == 4)
				chatHist.add(ChatDto.builder().senderType(chat[0]).contentType(chat[1]).content(chat[2])
						.chatTime(chat[3]).build());
			else
				chatHist.add(
						ChatDto.builder().senderType(chat[0]).contentType("-").content(chat[1]).chatTime("-").build());
		}
		response.setData(chatHist);

		return response;
	}

	public CommonResponse<ConsultDto> changeConsultStatus(ConsultStatus consultStatus) {
		String status = consultStatus.getStatus();

		CommonResponse<ConsultDto> res = new CommonResponse<>();
		Optional<Consult> entity = consultRepository.findByConsultId(consultStatus.getConsultId());

		if (!entity.isPresent()) {
			throw new ConsultServiceUnavailableException(ConsultErrorCode.NOT_FOUND);
		}

		Consult consultEntity = entity.get();

		if ("done".equals(status)) {
			consultEntity.done();
			sendTextMessage(consultStatus.getUserKey());
		} else if ("pending".equals(status)) {
			consultEntity.pending();
		} else if ("consulting".equals(status)) {
			consultEntity.consulting();
		} 
		else {
			throw new ConsultServiceUnavailableException(ConsultErrorCode.INVALID_REQUEST);
		}

		ConsultDto consult = modelMapper.map(consultRepository.save(consultEntity), ConsultDto.class);
		res.setData(consult);

		return res;
	}

	public CommonResponse<ConsultDto> doneConsult(DoneConsult doneConsult) {		
		CommonResponse<ConsultDto> res = new CommonResponse<>();
		Optional<Consult> entity = consultRepository.findByConsultId(doneConsult.getConsultId());

		if (!entity.isPresent()) {
			throw new ConsultServiceUnavailableException(ConsultErrorCode.NOT_FOUND);
		}

		if(doneConsult.getGoReason() != null) {
			doneConsult.setGoReason(SecurityUtil.cleanStringXSS(doneConsult.getGoReason()));
		}
		
		Consult consultEntity = entity.get();
		consultEntity.done(doneConsult);
		
		ConsultDto consult = modelMapper.map(consultRepository.save(consultEntity), ConsultDto.class);
		sendTextMessage(consult.getUserKey());

		res.setData(new ConsultDto());		
		return res;
	}

	private SendResponse sendImageBase64Message(SocketMessageDto socketMessageDto) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

		CustomMultipartFile image = fileService.base64ToMultipartfile(socketMessageDto.getFileName(),
				socketMessageDto.getContent());
		SendImage sendImage = new SendImage();
		sendImage.setUser_key(socketMessageDto.getUserKey());

		consultCacheRepository.addUserKey(sendImage.getSerialNumber(), sendImage.getUserKey());

		return restUtil.requestAPI(sendImage.toMultiValueMap(image.getResource()), SendResponse.class,
				sendConsultImageBase64Url, HttpMethod.POST, httpHeaders);
	}

	private SendResponse sendImageMessage(SocketMessageDto socketMessageDto) {
		SendImage sendImage = new SendImage(socketMessageDto);
		consultCacheRepository.addUserKey(sendImage.getSerialNumber(), sendImage.getUserKey());
		return restUtil.requestAPI(sendImage, SendResponse.class, sendConsultImageUrl, HttpMethod.POST);
	}

	private SendResponse sendFileMessage(SocketMessageDto socketMessageDto) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

		CustomMultipartFile media = fileService.base64ToMultipartfile(socketMessageDto.getFileName(),
				socketMessageDto.getContent());
		SendFile sendFile = new SendFile();
		sendFile.setFile_type("video");
		sendFile.setUser_key(socketMessageDto.getUserKey());

		return restUtil.requestAPI(sendFile.toMultiValueMap(media.getResource()), SendResponse.class,
				sendConsultFileUrl, HttpMethod.POST, httpHeaders);
	}

	private SendResponse sendTextMessage(SocketMessageDto socketMessageDto) {
		ChatWrite chatWrite = new ChatWrite(socketMessageDto);
		return restUtil.postRequest(sendConsultTextUrl, chatWrite, SendResponse.class);
	}

	private SendResponse sendTextMessage(String userKey) {
		ChatWrite chatWrite = new ChatWrite();
		chatWrite.closeChat(userKey);

		return restUtil.postRequest(sendConsultTextUrl, chatWrite, SendResponse.class);
	}

	public CommonResponse<List<ConsultDto>> findAllByCnvrstSttusNotAndCsttCmpno(ConsultingDto consulting) {
		log.info(consulting.toString());
		List<Consult> consultEntities = consultRepository.findAllByCnvrstSttusNotAndCsttCmpNoIs("D",
				consulting.getCsttCmpNo());
		CommonResponse<List<ConsultDto>> response = new CommonResponse<>();
		response.setData(consultEntities.stream().map(consultEntity -> modelMapper.map(consultEntity, ConsultDto.class))
				.collect(Collectors.toList()));

		return response;
	}
}
