package com.kt.csai.domain.consult.payload.send;

import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public abstract class UploadBase {
	@JsonProperty("serial_number")
	protected String serialNumber;
	@JsonProperty("ref_key")
	protected String refKey;
	@JsonProperty("file_type")
	protected String fileType;
	
	abstract public MultiValueMap<String, Object> toMultiValueMap();
}
