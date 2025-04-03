package com.kt.csai.domain.guide.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Guide {
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("description_detail")
	private String descriptionDetail;
	@JsonProperty("file")
	private String file;
	
	@JsonProperty("scn")
	private String scn;

	public Guide(EazyGuideLineQueryResultInterface eazyGuideLine) {
		this.title = eazyGuideLine.getGuideTitle();
		this.description = eazyGuideLine.getGuideDesc();
		this.descriptionDetail = eazyGuideLine.getGuideDesc2();
		this.file = eazyGuideLine.getGuideCode();
		this.scn = eazyGuideLine.getScn();
	}
}
