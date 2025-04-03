package com.kt.csai.global.security.encryption.service;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.kt.csai.global.common.payload.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RsaService {
	
	// 복호화 처리. 16진수 문자열을 바이트 배열로 변환
	private byte[] hexToByteArray(String hex) {		
		if (hex == null || hex.length() % 2 != 0) {
			return new byte[] {};
		}

		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}

	public String decryption(String securedValue, HttpSession session) throws Exception {	
		PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
		
		Cipher cipher = Cipher.getInstance("RSA");
		byte[] encryptedBytes = hexToByteArray(securedValue);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩
		return decryptedValue;
	}
	
	

	public CommonResponse<com.kt.csai.global.security.encryption.payload.PublicKey> generatePublicKey(
			HttpSession session) {
		CommonResponse<com.kt.csai.global.security.encryption.payload.PublicKey> res = new CommonResponse<>();

		KeyPairGenerator generator;
		KeyPair keyPair;
		KeyFactory keyFactory;
		try {
			generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048); // 키 사이즈 - 1024, 2048

			keyPair = generator.genKeyPair();
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

			res.setSuccess(false);
			return res;
		}

		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		// 개인 키 생성 후 세션에 저장
		session.setAttribute("__rsaPrivateKey__", privateKey);

		// 공개키를 문자열로 변환
		RSAPublicKeySpec publicSpec;
		try {
			publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();

			res.setSuccess(false);
			return res;
		}

		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

		com.kt.csai.global.security.encryption.payload.PublicKey resPublicKey = new com.kt.csai.global.security.encryption.payload.PublicKey(
				publicKeyModulus, publicKeyExponent);

		res.setData(resPublicKey);

		return res;
	}
}
