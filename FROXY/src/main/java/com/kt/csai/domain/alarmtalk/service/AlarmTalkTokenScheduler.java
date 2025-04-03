package com.kt.csai.domain.alarmtalk.service;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kt.csai.global.config.HttpHeadersConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class AlarmTalkTokenScheduler {

	private final AlarmTalkService alarmTalkService;
	
	@PostConstruct
	public void init() {
		log.info("get token..");
		setAlarmTalkToken();
	}
	
	@Scheduled(cron = " 0 0/30 * * * *")
	public void scheduledToken() {
		log.info("token renewal..");
		setAlarmTalkToken();
	}
	
	private void setAlarmTalkToken() {
		HttpHeadersConfig.ALARM_TALK_TOKEN = alarmTalkService.getToken();
	}
}
