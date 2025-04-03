package com.kt.csai.domain.consult.payload.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserReference {
	@JsonProperty("extra")
	private String extra;
}
