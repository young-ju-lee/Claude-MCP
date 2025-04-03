package com.kt.csai.domain.consult.model.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public abstract class Chat {	
	@JsonIgnore
	protected int retryCount = 0;
	
	@JsonIgnore
	private final int LIMIT = 5;
	
	public void increaseRetryCount() {
		++retryCount;
	}
	
	public boolean isLimit() {
		return retryCount >= LIMIT;
	}
	
	public abstract void generateSerialNumber(); 
	public abstract String getSerialNumber();
	public abstract String getUserKey();
}
