package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AlarmTalkRequest {
	
	@JsonProperty("trouble")
	private TroubleDto trouble;
	
	@JsonProperty("sender")
	private SenderDto sender;
	
	@JsonProperty("customer")
	private CustomerDto customer;

	@JsonIgnore
	private String consultId;

	public AlarmTalkRequest() {
		this.consultId = generateConsultId();
	}

	@JsonIgnore
	private String generateConsultId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
