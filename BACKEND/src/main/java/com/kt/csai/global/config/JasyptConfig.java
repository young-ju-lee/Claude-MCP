package com.kt.csai.global.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Sample Code
 *
 * @author 82022961
 * @version 1.0.0
 * @since 2020/11/19
 */

@Slf4j
@Configuration
public class JasyptConfig{
	
	private static final String JASYPT_KEY 	= "oss";
	private static final String ALGORITHM 	= "PBEWithMD5AndDES";
	
	@Bean("jasyptStringEncrptor")
	public StandardPBEStringEncryptor StringEnc() {
		 StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		 EnvironmentPBEConfig conf = new EnvironmentPBEConfig();
		 conf.setPassword(JASYPT_KEY);
		 conf.setAlgorithm(ALGORITHM);
		 enc.setConfig(conf);
		 return enc;
	 }
	
	public static void main(String[] args) {
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setAlgorithm(JasyptConfig.ALGORITHM);
		pbeEnc.setPassword(JasyptConfig.JASYPT_KEY);
		
		String username = pbeEnc.encrypt("csai");
		String password = pbeEnc.encrypt("Omuccomucc!1234");
				
		log.debug("username:" + username);
		log.debug("password:" + password);
	
	}
}

