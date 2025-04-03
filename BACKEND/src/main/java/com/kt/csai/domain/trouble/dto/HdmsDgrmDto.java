package com.kt.csai.domain.trouble.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HdmsDgrmDto {
		
	@JsonProperty("ttId")
	private String ttid;
	
	@JsonProperty("ip")
	private String ip;
	
	@JsonProperty("gwIp")
	private String gwIp;
	
	@JsonProperty("termlType")
	private String termlType;
	
	@JsonProperty("dgrmYn")
	private String dgrmYn;
	
	@JsonProperty("seq")
	private String seq;
	
	@JsonProperty("cretDt")
	private LocalDateTime cretDt;
	
	@JsonProperty("scn")
	private String scn;
		
	@JsonProperty("createYn")
	private String createYn;
	
	
}
