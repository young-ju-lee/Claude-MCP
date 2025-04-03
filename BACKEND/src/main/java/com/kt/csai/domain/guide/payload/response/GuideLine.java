package com.kt.csai.domain.guide.payload.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GuideLine {
	@JsonProperty("terml_type")
	private String termlType;
	@JsonProperty("guides")
	private List<Guide> guides;

	public GuideLine() {
		this.guides = new ArrayList<>();
	}

	public GuideLine(String termlType) {
		this();
		this.termlType = termlType;
	}

	public void addGuide(EazyGuideLineQueryResultInterface eazyGuideLine) {
		guides.add(new Guide(eazyGuideLine));
	}
}
