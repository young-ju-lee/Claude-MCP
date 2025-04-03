package com.kt.csai.domain.consult.payload.send;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SendImage extends SendBase {
	private String imageType;
	private String imageName;
	
	public void setSerial_number(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public void setRef_key(String refKey) {
		this.refKey = refKey;
	}
	
	public void setImage_type(String imageType) {
		this.imageType = imageType;
	}
	
	public void setUser_key(String userKey) {
		this.userKey = userKey;
	}
	
	public void setImage_name(String imageName) {
		this.imageName = imageName;
	}
}
