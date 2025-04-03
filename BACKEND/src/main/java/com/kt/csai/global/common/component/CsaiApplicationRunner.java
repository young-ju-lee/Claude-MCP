package com.kt.csai.global.common.component;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.kt.csai.global.util.DirectoryUtil;

import lombok.RequiredArgsConstructor;

@Profile("prd")
@Component
@RequiredArgsConstructor
public class CsaiApplicationRunner implements ApplicationRunner {

	private final DirectoryUtil directoryUtil;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (!directoryUtil.exists()) {
			directoryUtil.initDirectory();
		}
	}
}
