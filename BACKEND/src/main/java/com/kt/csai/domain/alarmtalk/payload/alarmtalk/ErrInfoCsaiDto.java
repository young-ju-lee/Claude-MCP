package com.kt.csai.domain.alarmtalk.payload.alarmtalk;
	
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
	
@Data
public class ErrInfoCsaiDto {
    
	@JsonProperty("tel_Num")
	private String telNum = "";
	
	@JsonProperty("step_execution_id")
	private int stexecId ;
	
	@JsonProperty("version")
	private int version ;
	      
	@JsonProperty("step_name")
	private String stpNm;	
	       
	@JsonProperty("job_execution_id")
	private int jbexecNm ;
	
	@JsonProperty("start_time")
	private String stTime;
       
	@JsonProperty("status")
	private String status;
	
    @JsonProperty("exit_code")
    private String exitCd;

    @JsonProperty("exit_message")
    private String exitMsg;

}