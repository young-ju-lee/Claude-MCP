package com.kt.csai.domain.consult.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kt.csai.domain.consult.payload.cache.UserKeyInfo;
import com.kt.csai.domain.consult.service.ConsultCacheService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consult/cache")
public class ConsultCacheController {

	private ConsultCacheService cacheService;
	
	@PostMapping("/user-key/add")
	public ResponseEntity<?> addUserKey(@RequestBody UserKeyInfo userKeyInfo) {
		return new ResponseEntity<>(cacheService.addUserKey(userKeyInfo), HttpStatus.OK);
	}
	
	@PostMapping("/user-key/remove")
	public ResponseEntity<?> removeUserKey(@RequestBody UserKeyInfo userKeyInfo) {
		return new ResponseEntity<>(cacheService.removeUserKey(userKeyInfo), HttpStatus.OK);
	}
}
