package com.kt.csai.global.socket.handler;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.web.socket.WebSocketSession;

public class SubProtocolWebSocketHandlerInterceptor extends DelegatingIntroductionInterceptor {
	private static final long serialVersionUID = -5427102042804577445L;

	private final int MESSAGE_SIZE_LIMIT = 1024 * 1024 * 3; // 3MB

	@Override
	protected Object doProceed(MethodInvocation mi) throws Throwable {
		if ("afterConnectionEstablished".equals(mi.getMethod().getName())) {
			WebSocketSession session = (WebSocketSession) mi.getArguments()[0];
			session.setTextMessageSizeLimit(MESSAGE_SIZE_LIMIT);
			session.setBinaryMessageSizeLimit(MESSAGE_SIZE_LIMIT);
		}

		return super.doProceed(mi);
	}
}
