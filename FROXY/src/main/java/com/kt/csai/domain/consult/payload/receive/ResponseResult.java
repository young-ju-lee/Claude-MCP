package com.kt.csai.domain.consult.payload.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseResult {
	@JsonProperty("pf_id")
	private String pfId;
	@JsonProperty("serial_number")
	private String serialNumber;
	@JsonProperty("request_type")
	private String requestType;
	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("image")
	private String image;
	@JsonProperty("file")
	private String file;
	@JsonProperty("file_name")
	private String fileName;
	@JsonProperty("file_size")
	private String fileSize;
}
