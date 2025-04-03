package com.kt.csai.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import com.kt.csai.global.socket.handler.AgentWebSocketHandlerDecoratorFactory;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	private final int MESSAGE_SIZE_LIMIT = 1024 * 1024 * 3; // 3MB
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/web-socket").setAllowedOriginPatterns("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/pub");
		registry.enableSimpleBroker("/sub");
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		registry.setMessageSizeLimit(MESSAGE_SIZE_LIMIT);
		registry.setSendTimeLimit(20 * 10000);
		registry.setSendBufferSizeLimit(MESSAGE_SIZE_LIMIT);
		//registry.setDecoratorFactories(new AgentWebSocketHandlerDecoratorFactory());
	}
}
