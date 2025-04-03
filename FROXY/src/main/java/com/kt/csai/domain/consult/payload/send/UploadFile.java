package com.kt.csai.domain.consult.payload.send;

import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UploadFile extends UploadBase {
	@JsonProperty("file")
	private Resource file;
	@JsonProperty("file_type")
	private String fileType;

	@Override
	public MultiValueMap<String, Object> toMultiValueMap() {
		MultiValueMap<String, Object> result = new LinkedMultiValueMap<>();
		result.add("serial_number", this.serialNumber);
		result.add("ref_key", this.refKey);
		result.add("file_type", this.fileType);
		result.add("file", this.file);

		return result;
	}
	
	public UploadFile() {
	}

	public UploadFile(SendBase sendBase, Resource resource) {
		SendFile sendFile = (SendFile) sendBase;
		
		this.serialNumber = sendFile.getSerialNumber();
		this.refKey = sendFile.getRefKey();
		this.fileType = sendFile.getFileType();
		this.file = resource;
	}
}
