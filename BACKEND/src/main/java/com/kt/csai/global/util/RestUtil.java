package com.kt.csai.global.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestUtil {

	private final RestTemplate restTemplate;

	public <R, T> T requestAPI(R requestBody, Class<T> responseClass, String url, HttpMethod httpMethod,
			HttpHeaders httpHeaders) {
		HttpEntity<R> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
		return restTemplate.exchange(url, httpMethod, httpEntity, responseClass).getBody();
	}

	public <R, T> T requestAPI(R requestBody, Class<T> responseClass, String url, HttpMethod httpMethod) {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<R> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
		return restTemplate.exchange(url, httpMethod, httpEntity, responseClass).getBody();
	}

	public <T> T postRequest(String url, Object requestBody, Class<T> classType) {
		ResponseEntity<T> response = restTemplate.postForEntity(url, requestBody, classType);
		return response.getBody();
	}

	public <T> T postRequest(String url, Object requestBody, HttpHeaders httpHeaders, Class<T> classType) {
		ResponseEntity<T> response = restTemplate.postForEntity(url, requestBody, classType);
		return response.getBody();
	}

	public <T> T getRequest(String uri, T obj, Class<T> c) {
		return obj;
	}
}
