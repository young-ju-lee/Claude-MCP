package com.kt.csai.global.security.encryption.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicKey {
	@JsonProperty("public_key_modulus")
	private String publicKeyModulus;
	
	@JsonProperty("public_key_exponent")
	private String publicKeyExponent;
}
