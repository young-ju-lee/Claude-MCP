package com.kt.csai.domain.consult.payload.send;

import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.kt.csai.global.socket.payload.SocketMessageDto;
import com.kt.csai.global.util.StringUtil;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SendImage {
	private String serialNumber;
	private String refKey;
	private String imageType;
	private String userKey;
	private String imageName;
	
	public SendImage() {
		this.serialNumber = StringUtil.generateSerialNumber();
	}
	
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
	
	public MultiValueMap<String, Object> toMultiValueMap(Resource resource) {
		MultiValueMap<String, Object> result = new LinkedMultiValueMap<>();
		result.add("serial_number", this.serialNumber);
		result.add("ref_key", this.refKey);
		result.add("image_type", this.imageType);
		result.add("user_key", this.userKey);
		result.add("image", resource);
		
		return result;
	}
	
	public SendImage(SocketMessageDto socketMessage) {
		this();
		this.userKey = socketMessage.getUserKey();
		this.imageName = socketMessage.getFileName();
	}
}
