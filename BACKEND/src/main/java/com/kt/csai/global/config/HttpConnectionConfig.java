package com.kt.csai.global.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class HttpConnectionConfig {

	@Bean
	public RestTemplate getRestTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		TrustStrategy trustStrategy = new TrustSelfSignedStrategy();
		
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build();
		
		SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(connectionSocketFactory).build();
		
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(7000);
		httpRequestFactory.setReadTimeout(7000);
		
//		HttpClient httpClient = HttpClientBuilder.create()
//				.setMaxConnTotal(200)
//				.setMaxConnPerRoute(20)
//				.build();
		
		httpRequestFactory.setHttpClient(httpClient);
		
		return new RestTemplate(httpRequestFactory);
	}
	
	@Bean
	public HttpHeaders getHttpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return httpHeaders;
	}
}
