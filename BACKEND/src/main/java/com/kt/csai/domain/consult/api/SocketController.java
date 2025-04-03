package com.kt.csai.domain.consult.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.kt.csai.domain.consult.service.ConsultService;
import com.kt.csai.global.socket.payload.SocketMessageDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SocketController {
	private final ConsultService consultService;

    @MessageMapping(value = "/message")
    public void sendMessage(SocketMessageDto socketMessageDto) {
    	consultService.sendMessage(socketMessageDto);
    }
}
