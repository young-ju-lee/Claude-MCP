package com.kt.csai.domain.consult.payload.send;

import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.kt.csai.global.util.StringUtil;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SendFile {
	private String serialNumber;
	private String refKey;
	private String fileType;
	private String userKey;
	
	public SendFile() {
		this.serialNumber = StringUtil.generateSerialNumber();
	}
	
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
	
	public MultiValueMap<String, Object> toMultiValueMap(Resource resource) {
		MultiValueMap<String, Object> result = new LinkedMultiValueMap<>();
		result.add("serial_number", this.serialNumber);
		result.add("ref_key", this.refKey);
		result.add("file_type", this.fileType);
		result.add("user_key", this.userKey);
		result.add("file", resource);
		
		return result;
	}
}
