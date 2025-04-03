package com.kt.csai.domain.alarmtalk.payload.alarmtalk;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SenderDto {
	@JsonProperty("snd_cmp_no")
	private String sndCmpNo;
	
	@JsonProperty("snd_nm")
	private String sndNm;

	@JsonProperty("snd_team")
	private String sndTeam;
}
