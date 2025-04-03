package com.kt.csai.domain.consult.payload.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserMetadataRequest {
	@JsonProperty("pf_id")
	private String pfId;
	@JsonProperty("user_key")
	private String userKey;
	@JsonProperty("reference")
	private UserReference reference;
	@JsonProperty("last_reference")
	private LastReference lastReference;
	@JsonProperty("support_button")
	private String supportButton;
}
