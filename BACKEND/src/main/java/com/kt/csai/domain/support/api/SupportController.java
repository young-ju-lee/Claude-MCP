package com.kt.csai.domain.support.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kt.csai.domain.alarmtalk.payload.alarmtalk.TroubleDto;
import com.kt.csai.domain.support.dto.NoticeDto;
import com.kt.csai.domain.support.service.SupportService;
import com.kt.csai.domain.trouble.api.TroubleController;
import com.kt.csai.domain.trouble.dto.TroubleAcceptDto;
import com.kt.csai.domain.trouble.service.TroubleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support")
@Slf4j
public class SupportController {
    
	/* 공지사항 리스트 조회 */	
	@PostMapping("/notice_list")
	@ResponseBody    
    public ResponseEntity<List<NoticeDto>> NoticeList(@RequestBody NoticeDto notice) {
        
        /* notice_list 하드코딩 */		       
		log.info("========== Support notice_list Port :: 8080==========");
		List<NoticeDto> nto = SupportService.getNoticeList();
        /* notice_list 하드코딩 */

        return new ResponseEntity<>(nto, HttpStatus.OK);
    }
}