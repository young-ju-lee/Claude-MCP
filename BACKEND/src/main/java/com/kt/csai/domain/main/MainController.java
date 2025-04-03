package com.kt.csai.domain.main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
	@GetMapping()
	public ResponseEntity<?> loadMain() {
		return new ResponseEntity<>("C S A I - A P I", HttpStatus.OK);
	}
}
