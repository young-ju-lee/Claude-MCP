package com.kt.csai.global.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
public class HttpHeadersConfig {

	@Value("${CLIENT.ID}")
	private String clientId;

	@Value("${CLIENT.PASSWORD}")
	private String clientPwd;

	@Value("${CLIENT.API_KEY}")
	private String apiKey;

	public static String ALARM_TALK_TOKEN;

	@Bean(name = "alarmTalkHeaders")
	public HttpHeaders getAlarmTalkHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("X-IB-Client-Id", clientId);
		httpHeaders.set("X-IB-Client-Passwd", clientPwd);

		return httpHeaders;
	}

	@Bean(name = "consultHeaders")
	public HttpHeaders getConsultHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("API-KEY", apiKey);

		return httpHeaders;
	}

	@Bean(name = "fileUploadHeaders")
	public HttpHeaders getFileUploadHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
		httpHeaders.set("API-KEY", apiKey);

		return httpHeaders;
	}
}
