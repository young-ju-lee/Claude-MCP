package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
	
@Data
public class ErrInfoGWDto {
    
	@JsonProperty("tel_Num")
	private String telNum = "";
	
	@JsonProperty("folder_nm")
	private String fldNm;
	      
	@JsonProperty("workflow_nm")
	private String wfwNm;

    @JsonProperty("err_cd")
    private String errCd;

}