package com.kt.csai.domain.consult.payload.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsultDto {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("tel_num")
	private String telNum;
	
	@JsonProperty("user_key")
	private String userKey;
	
	@JsonProperty("consult_id")
	private String consultId;
	
	@JsonProperty("cnvrst_sttus")
	private String cnvrstSttus;
	
	@JsonProperty("cstt_nm")
	private String csttNm;

	@JsonProperty("cstt_cmpno")
	private String csttCmpNo;

	@JsonProperty("lscn")
	private String lscn;

	@JsonProperty("go_yn")
	private String goYn;
	
	@JsonProperty("go_reason")
	private String goReason;

	@JsonProperty("last_guide_code")
	private String lastGuideCode;
	
	@JsonProperty("consult_datetime")
	private LocalDateTime createDatetime;
	
	@JsonProperty("tt_id")
	private String ttId;
	
	@JsonProperty("cust_nm")
	private String custNm;
	
	@JsonProperty("scn")
	private String scn;
	
	@JsonIgnore
	private String chatFileName;
}