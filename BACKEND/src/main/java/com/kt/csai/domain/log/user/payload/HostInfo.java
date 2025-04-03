package com.kt.csai.domain.log.user.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HostInfo {
	@JsonProperty("name")
	private String hostName;

	@JsonProperty("ip")
	private String hostIp;
}
