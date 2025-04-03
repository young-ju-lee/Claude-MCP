package com.kt.csai.domain.alarmtalk.payload;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplement {
	@JsonProperty("quick_reply")
	private List<QuickReply> quickReplys;
	
	public Supplement(QuickReply quickReply) {
		this.quickReplys = new ArrayList<>();
		quickReplys.add(quickReply);
	}
}
