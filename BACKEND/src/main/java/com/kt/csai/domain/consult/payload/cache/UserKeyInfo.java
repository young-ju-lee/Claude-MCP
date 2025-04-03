package com.kt.csai.domain.consult.payload.cache;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserKeyInfo {
	private String serialNumber;
	private String userKey;
}
