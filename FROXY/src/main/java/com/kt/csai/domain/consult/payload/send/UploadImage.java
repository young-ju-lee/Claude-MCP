package com.kt.csai.domain.consult.payload.send;

import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UploadImage extends UploadBase {
	@JsonProperty("image")
	private Resource image;
	@JsonProperty("image_type")
	private String imageType;

	@Override
	public MultiValueMap<String, Object> toMultiValueMap() {
		MultiValueMap<String, Object> result = new LinkedMultiValueMap<>();
		result.add("serial_number", this.serialNumber);
		result.add("ref_key", this.refKey);
		result.add("image_type", this.imageType);
		result.add("image", this.image);

		return result;
	}

	public UploadImage() {
	}

	public UploadImage(SendBase sendBase, Resource resource) {
		SendImage sendImage = (SendImage) sendBase;
		
		this.serialNumber = sendImage.getSerialNumber();
		this.refKey = sendImage.getRefKey();
		this.imageType = sendImage.getImageType();
		this.image = resource;
	}
}
