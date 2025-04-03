package com.kt.csai.domain.guide.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kt.csai.domain.guide.service.EazyGuideLineService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/guide")
public class GuideController {

	private final EazyGuideLineService eazyGuideLineService;
	
	@GetMapping("/egl")
	public ResponseEntity<?> getEglData(@RequestParam(name = "tt_id") String ttId) {
		return new ResponseEntity<>(eazyGuideLineService.findEasyGuideLineByTtId(ttId), HttpStatus.OK);
	}
}
