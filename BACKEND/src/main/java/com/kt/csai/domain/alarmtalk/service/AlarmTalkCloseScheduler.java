package com.kt.csai.domain.alarmtalk.service;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AlarmTalkCloseScheduler {
	
	private final AlarmTalkDataService alarmTalkDataService;
	
	@PostConstruct
	public void init() {
		closeAlarmTalk();
	}
	
	@Scheduled(cron = "0/30 * * * * ?")
	public void scheduledCloseAlarmTalk() {
		closeAlarmTalk();
	}
	
	private void closeAlarmTalk() {
		alarmTalkDataService.closeAlarmTalk();
	}
}