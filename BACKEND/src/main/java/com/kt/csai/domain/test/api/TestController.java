package com.kt.csai.domain.test.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kt.csai.domain.test.dto.TestHdmsResltDto;
import com.kt.csai.domain.test.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/test")
@Slf4j
public class TestController {
		
	private final TestService troubleService; 
		
	
	/**
	 * 망구성도 정보 조회
	 * @return
	 */
	@GetMapping("/chkHdmsDgrm")
	@ResponseBody    
	public ResponseEntity<?> chkHdmsDgrm(@RequestBody TestHdmsResltDto req) {
		log.info("========== TroubleController.chkHdmsDgrm ==========");
						
		String[] ttid = req.getTtid();		
		String type = req.getType();
		String tableNm = req.getTableNm();		
		
		troubleService.chkHdmsDgrm(ttid, type, tableNm);
		
		return null;
    }
	
	
}
