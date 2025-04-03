package com.kt.csai.domain.alarmtalk.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.alarmtalk.payload.AlarmTalk;
import com.kt.csai.domain.alarmtalk.payload.AlarmTalkResponse;
import com.kt.csai.domain.alarmtalk.payload.AuthTokenResponse;
import com.kt.csai.domain.alarmtalk.payload.ReportRequest;
import com.kt.csai.domain.alarmtalk.payload.ReportResponse;
import com.kt.csai.global.util.RestUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmTalkService {
	private final RestUtil restUtil;

	private final HttpHeaders alarmTalkHeaders;

	@Value("${URL.KAKAO.REQUEST_TOKEN}")
	private String requestTokenUrl;

	@Value("${URL.KAKAO.SEND_ALARMTALK}")
	private String sendAlarmTalkUrl;

	@Value("${URL.KT_API.REPORT}")
	private String sendReportUrl;

	public String getToken() {
		AuthTokenResponse authTokenResponse = restUtil.requestAPI(null, AuthTokenResponse.class, requestTokenUrl,
				HttpMethod.POST, alarmTalkHeaders);

		String token = authTokenResponse.getSchema() + " " + authTokenResponse.getAccessToken();
		log.info(token);
		return token;
	}

	public AlarmTalkResponse sendAlarmTalk(AlarmTalk alarmTalk) {
		log.info(alarmTalk.toString());

		AlarmTalkResponse alarmTalkResponse = restUtil.requestAPI(alarmTalk, AlarmTalkResponse.class, sendAlarmTalkUrl,
				HttpMethod.POST, alarmTalkHeaders);
		
		return alarmTalkResponse;
	}

	public ReportResponse sendReport(ReportRequest reportRequest) {
		log.info("###### " + reportRequest.toString());
		ReportResponse reportResponse = restUtil.requestAPI(reportRequest, ReportResponse.class, sendReportUrl,
				HttpMethod.POST);
		
		return reportResponse;
	}
}
