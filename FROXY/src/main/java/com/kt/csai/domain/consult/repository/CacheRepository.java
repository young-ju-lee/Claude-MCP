package com.kt.csai.domain.consult.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.kt.csai.domain.consult.payload.send.Chat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CacheRepository {

	private Map<String, String> cache;
	private Map<String, Chat> chatCache;

	@PostConstruct
	private void init() {
		this.cache = new HashMap<>();
		this.chatCache = new HashMap<>();
	}

	public void addUserKey(String serialNumber, String userKey) {
		log.info(serialNumber + " >>> " + userKey);
		this.cache.put(serialNumber, userKey);
	}

	public String getUserKey(String serialNumber) {
		return this.cache.get(serialNumber);
	}

	public String removeSerialNumber(String serialNumber) {
		return this.cache.remove(serialNumber);
	}

	public boolean isContainsSerialNumber(String serialNumber) {
		return this.cache.containsKey(serialNumber);
	}
	
	public void addChat(String key, Chat chat) {
		this.chatCache.put(key, chat);
	}
	
	public Chat removeChat(String key) {
		return this.chatCache.remove(key);
	}
}
