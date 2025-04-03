package com.ktds.egov.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.egov.common.util.PropertyUtil;

public class AES extends HandlerInterceptorAdapter { 
	
 
   //<!2019.04.26 하드코딩 된 암호화키 수정-->		
	public static String key() throws Exception {
					PropertyUtil util = null;
						//util = new PropertyUtil("C:\\extsfilter.properties"); //local
						util = new PropertyUtil("/home/jboss/uploadFiles/extsfilter.properties"); //dev & prd
						String key = util.getPropertyValue("key");
						return key;
	}
	
    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }

    /**
     * byte[] to hex : unsigned byte(����Ʈ) �迭�� 16��� ���ڿ��� �ٲ۴�.
     * 
     * @param ba        byte[]
     * @return
     */
    public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    } 

    /**
     * AES ����� ��ȣȭ
     * 
     * @param message
     * @return ��ȣȭ �� ���ڿ�
     * @throws ApocalypseException
     */
    public static String encrypt(String message) throws Exception {
    	//<!2019.04.26 하드코딩 된 암호화키 수정-->	
       
    	String key = key();		
        // use key coss2
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
     
        // Instantiate the cipher
        Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (NoSuchPaddingException e) {
			throw e;
		}
        try {
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		} catch (InvalidKeyException e) {
			throw e;
		}

        byte[] encrypted;
		try {
			encrypted = cipher.doFinal(message.getBytes());
		} catch (IllegalBlockSizeException e) {
			throw e;
		} catch (BadPaddingException e) {
			throw e;
		}
        return byteArrayToHex(encrypted);
    }

    /**
     * AES ����� ��ȣȭ
     * 
     * @param message
     * @return ��ȣȭ �� ���ڿ�
     * @throws ApocalypseException
     */
    public static String decrypt(String encrypted) throws Exception {
        // use key coss2\
    	//<!2019.04.26 하드코딩 된 암호화키 수정-->	
    	String key = key();
    	
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
         
        Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (NoSuchPaddingException e) {
			throw e;
		}
        try {
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		} catch (InvalidKeyException e) {
			throw e;
		}
        byte[] original;
		try {
			original = cipher.doFinal(hexToByteArray(encrypted));
		} catch (IllegalBlockSizeException e) {
			throw e;
		} catch (BadPaddingException e) {
			throw e;
		}
        String originalString = new String(original);
        return originalString;
    }
}


