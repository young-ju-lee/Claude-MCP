package com.kt.csai.domain.trouble.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TroubleCompleteDto {
	
	@JsonProperty("ttId")
	private String ttId;
	
	@JsonProperty("lscn")
	private String lscn;
	
	@JsonProperty("svcMainTypeCodeDesc")
	private String svcMainTypeCodeDesc;
	
	@JsonProperty("workFinishDatetime")
	private String workFinishDatetime;
	
	@JsonProperty("outdoorTypeCodeDesc")
	private String outdoorTypeCodeDesc;
	
	@JsonProperty("workContent")
	private String workContent;
	
	@JsonProperty("asFixMainClsCodeDesc")
	private String asFixMainClsCodeDesc;
	
	@JsonProperty("asFixSubClsCodeDesc")
	private String asFixSubClsCodeDesc;
	
	@JsonProperty("repairCodeDesc")
	private String repairCodeDesc;
	
	@JsonProperty("asCauseMainClsCodeDesc")
	private String asCauseMainClsCodeDesc;
	
	@JsonProperty("asCauseSubClsCodeDesc")
	private String asCauseSubClsCodeDesc;
	
	@JsonProperty("troubCauseDesc")
	private String troubCauseDesc;
	
	@JsonProperty("asEquipMainClsCodeDesc")
	private String asEquipMainClsCodeDesc;
	
	@JsonProperty("asEquipSubClsCodeDesc")
	private String asEquipSubClsCodeDesc;
	
	@JsonProperty("troubFacilityDesc")
	private String troubFacilityDesc;
	
	
	
	
	
}
