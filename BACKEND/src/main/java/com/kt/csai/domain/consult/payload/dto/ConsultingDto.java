package com.kt.csai.domain.consult.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsultingDto {
	@JsonProperty("cstt_cmp_no")
	private String csttCmpNo;
}
