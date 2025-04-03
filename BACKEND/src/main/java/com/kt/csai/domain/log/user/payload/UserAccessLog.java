package com.kt.csai.domain.log.user.payload;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserAccessLog {
	@JsonProperty("timestamp")
	private LocalDateTime timestamp;

	@JsonProperty("host")
	private HostInfo host;

	@JsonProperty("user")
	private LocalInfo local;

	@JsonProperty("service")
	private String service;

	@JsonProperty("operation")
	private String operation;

	@JsonProperty("transaction_id")
	private String transactionId;

	@JsonProperty("log_type")
	private String logType;

	@JsonProperty("security_type")
	private String securityType;

}
