package com.kt.csai.domain.log.user.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LocalInfo {
	@JsonProperty("ip")
	private String userIp;

	@JsonProperty("id")
	private String userId;
}
