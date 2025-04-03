package com.kt.csai.global.common.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse <T> {

	@JsonIgnore
	private boolean isSuccess = true;
	
	public int code;
	private T data;
	
	public int getCode() {
		return isSuccess ? 0 : 1;
	}
}
