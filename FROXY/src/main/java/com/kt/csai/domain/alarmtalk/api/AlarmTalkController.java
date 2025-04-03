package com.kt.csai.domain.alarmtalk.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kt.csai.domain.alarmtalk.payload.AlarmTalk;
import com.kt.csai.domain.alarmtalk.payload.ReportRequest;
import com.kt.csai.domain.alarmtalk.service.AlarmTalkService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/alarm")
public class AlarmTalkController {

	private final AlarmTalkService alarmTalkService;

	@PostMapping("/send")
	public ResponseEntity<?> sendAlarmTalk(@RequestBody AlarmTalk alarmTalk) {
		return new ResponseEntity<>(alarmTalkService.sendAlarmTalk(alarmTalk), HttpStatus.OK);
	}

	@PostMapping("/report")
	public ResponseEntity<?> receiveReport(@RequestBody ReportRequest reportRequest) {
		return new ResponseEntity<>(alarmTalkService.sendReport(reportRequest), HttpStatus.OK);
	}
}
