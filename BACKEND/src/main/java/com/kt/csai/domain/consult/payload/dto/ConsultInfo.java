package com.kt.csai.domain.consult.payload.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ConsultInfo {
	private String consultantName;
	private String consultantNo;
	private String lscn;
	private String ttId;
	private String custName;
}
