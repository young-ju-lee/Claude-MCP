package com.kt.csai.domain.consult.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kt.csai.domain.consult.service.FileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
	private final FileService fileService;

	@PostMapping(value = "/upload")
	public ResponseEntity<?> imageUpload(@RequestParam(name = "image") MultipartFile multipartFile) {
		//log.info(imageUpload.toString());
		return new ResponseEntity<>(fileService.imageUploadToKakao(multipartFile), HttpStatus.OK);
	}
}
