package com.kt.csai.domain.consult.payload.send;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SendFile extends SendBase{
	private String fileType;
	
	public void setSerial_number(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public void setRef_key(String refKey) {
		this.refKey = refKey;
	}
	
	public void setFile_type(String fileType) {
		this.fileType = fileType;
	}
	
	public void setUser_key(String userKey) {
		this.userKey = userKey;
	}
}
