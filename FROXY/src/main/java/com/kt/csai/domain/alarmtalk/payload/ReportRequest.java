package com.kt.csai.domain.alarmtalk.payload;

import lombok.Data;

@Data
public class ReportRequest {
	private String messageId;
	private String resultCode;
	private String errorText;
	private String reportTime;
	private String brandtalkType;
	private String ref;
}
