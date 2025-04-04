package com.ktds.egov.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 회원 개인정보 암호화 유틸리티
 * 이름, 이메일, 전화번호 등의 개인정보를 암호화/복호화 하는 기능 제공
 */
public class MemberEncrypt {
    
    /**
     * 개인정보를 AES 암호화하는 메소드
     * 
     * @param plainText 암호화할 평문 텍스트
     * @return 암호화된 문자열 (Base64 인코딩)
     * @throws Exception 암호화 오류 발생 시
     */
    public static String encryptPersonalInfo(String plainText) throws Exception {
        if (plainText == null || plainText.isEmpty()) {
            return plainText;
        }
        
        try {
            // AES.java의 key() 메소드를 활용하여 동일한 키 사용
            String key = AES.key();
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new Exception("개인정보 암호화 중 오류 발생", e);
        }
    }
    
    /**
     * AES 암호화된 개인정보를 복호화하는 메소드
     * 
     * @param encryptedText 암호화된 텍스트 (Base64 인코딩)
     * @return 복호화된 평문 텍스트
     * @throws Exception 복호화 오류 발생 시
     */
    public static String decryptPersonalInfo(String encryptedText) throws Exception {
        if (encryptedText == null || encryptedText.isEmpty()) {
            return encryptedText;
        }
        
        try {
            // AES.java의 key() 메소드를 활용하여 동일한 키 사용
            String key = AES.key();
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] original = cipher.doFinal(decodedBytes);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("개인정보 복호화 중 오류 발생", e);
        }
    }
    
    /**
     * 마스킹 처리된 개인정보를 반환하는 메소드
     * 
     * @param plainText 평문 텍스트
     * @param type 개인정보 타입 (name, email, phone)
     * @return 마스킹 처리된 텍스트
     */
    public static String maskPersonalInfo(String plainText, String type) {
        if (plainText == null || plainText.isEmpty()) {
            return plainText;
        }
        
        switch (type.toLowerCase()) {
            case "name":
                // 이름은 마지막 글자만 '*'로 마스킹
                if (plainText.length() > 1) {
                    return plainText.substring(0, plainText.length() - 1) + "*";
                }
                return "*";
                
            case "email":
                // 이메일은 @ 앞부분의 마지막 3자리를 '*'로 마스킹
                int atIndex = plainText.indexOf('@');
                if (atIndex > 3) {
                    return plainText.substring(0, atIndex - 3) + "***" + 
                           plainText.substring(atIndex);
                } else if (atIndex > 0) {
                    return "***" + plainText.substring(atIndex);
                }
                return plainText;
                
            case "phone":
                // 전화번호는 가운데 자리를 '**'로 마스킹
                if (plainText.length() >= 4) {
                    return plainText.substring(0, plainText.length() - 4) + 
                           "**" + plainText.substring(plainText.length() - 2);
                }
                return plainText;
                
            default:
                return plainText;
        }
    }
}
