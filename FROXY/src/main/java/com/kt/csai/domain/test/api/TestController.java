package com.kt.csai.domain.test.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kt.csai.domain.alarmtalk.service.AlarmTalkService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class TestController {
	
	private final AlarmTalkService alarmTalkService;
	
	@GetMapping()
	public ResponseEntity<?> test() {
		alarmTalkService.getToken();
		return new ResponseEntity<>("T", HttpStatus.OK);
	}
}
