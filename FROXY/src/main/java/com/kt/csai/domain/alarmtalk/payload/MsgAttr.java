package com.kt.csai.domain.alarmtalk.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MsgAttr {
	@JsonProperty("sender_key")
	private String senderKey;
	@JsonProperty("response_method")
	private String responseMethod;
	@JsonProperty("template_code")
	private String templateCode;
	@JsonProperty("supplement")
	private Supplement supplement;
}
