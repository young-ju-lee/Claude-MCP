package com.kt.csai.global.socket.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.kt.csai.global.security.util.SecurityUtil;
import com.kt.csai.global.socket.model.ContentType;
import com.kt.csai.global.socket.model.SenderType;
import com.kt.csai.global.socket.payload.SocketMessageDto;
import com.kt.csai.global.util.CsvUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocketService {
	private final CsvUtil csvUtil;

	private final SimpMessagingTemplate template;

	private final String BASE_SUBSCRIBE_TOPIC = "/sub/consult/";

	public void sendMessage(SocketMessageDto socketMessageDto) {
		
		if("TX".equals(socketMessageDto.getContentType().toUpperCase())) {
			socketMessageDto.setContent(SecurityUtil.cleanStringXSS(socketMessageDto.getContent()));
		}
		
		socketMessageDto.setChatTime(this.getSendTime());
		saveMessage(socketMessageDto);
		template.convertAndSend(BASE_SUBSCRIBE_TOPIC + socketMessageDto.getConsultId(), socketMessageDto);
	}

	public void sendMessage(String consultId, ContentType contentType, String content, SenderType senderType) {
		SocketMessageDto socketMessageDto = new SocketMessageDto();
		socketMessageDto.setConsultId(consultId);
		socketMessageDto.setSenderType(senderType.getType());
		socketMessageDto.setContent(content);
		socketMessageDto.setContentType(contentType.getType());

		this.sendMessage(socketMessageDto);
	}

	public void sendMessage(String consultId, ContentType contentType, String content, SenderType senderType,
			String userKey) {
		SocketMessageDto socketMessageDto = new SocketMessageDto();
		socketMessageDto.setConsultId(consultId);
		socketMessageDto.setSenderType(senderType.getType());
		socketMessageDto.setContent(content);
		socketMessageDto.setContentType(contentType.getType());
		socketMessageDto.setUserKey(userKey);

		this.sendMessage(socketMessageDto);
	}

	private void saveMessage(SocketMessageDto socketMessageDto) {
		csvUtil.writeChat(socketMessageDto);
	}

	private String getSendTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a h시 mm분").withLocale(Locale.forLanguageTag("ko"));

		return now.format(formatter);
	}
}
