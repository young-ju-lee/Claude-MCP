package com.kt.csai.domain.alarmtalk.payload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
	@JsonProperty("button")
	private List<Supplement> button;
}
