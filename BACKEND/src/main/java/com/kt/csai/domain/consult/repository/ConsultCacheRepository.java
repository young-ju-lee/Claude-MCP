package com.kt.csai.domain.consult.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkRequest;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkResponse;
import com.kt.csai.domain.consult.model.chat.Chat;
import com.kt.csai.domain.consult.payload.cache.ChatInfo;
import com.kt.csai.domain.consult.payload.cache.UserKeyInfo;
import com.kt.csai.domain.consult.payload.dto.ConsultInfo;
import com.kt.csai.domain.consult.payload.receive.UserMetadataRequest;

@Repository
public class ConsultCacheRepository {
	private Map<String, String> consultIdCache;

	private Map<String, ConsultInfo> consultInfoCache;
	
	private Map<String, String> userKeyCache;
	
	private Map<String, Chat> chatCache;

	@PostConstruct
	private void init() {
		this.consultIdCache = new HashMap<>();
		this.consultInfoCache = new HashMap<>();
		this.userKeyCache = new HashMap<>();
		this.chatCache = new HashMap<>();
	}

	/*
	 * consult id
	 */
	public String getConsultId(String key) {
		return consultIdCache.get(key);
	}

	public void addConsultId(String key, String consultId) {
		this.consultIdCache.put(key, consultId);
	}

	public void addConsultId(UserMetadataRequest userMetadataRequest) {
		this.consultIdCache.put(userMetadataRequest.getUserKey(), userMetadataRequest.getReference().getExtra());
	}

	public void addConsultId(AlarmTalkResponse alarmTalkResponse) {
		this.consultIdCache.put(alarmTalkResponse.getMessageId(), alarmTalkResponse.getConsultId());
	}

	public String removeConsultId(String key) {
		return this.consultIdCache.remove(key);
	}

	/*
	 * lscn
	 */
	public ConsultInfo getConsultInfo(String key) {
		return this.consultInfoCache.get(key);
	}

	public void addConsultInfo(String key, ConsultInfo consultInfo) {
		this.consultInfoCache.put(key, consultInfo);
	}

	public void addConsultInfo(AlarmTalkRequest alarmTalkRequest) {
		this.consultInfoCache.put(alarmTalkRequest.getConsultId()
				, ConsultInfo.builder()
					.consultantName(alarmTalkRequest.getSender().getSndNm())
					.consultantNo(alarmTalkRequest.getSender().getSndCmpNo())
					.lscn(alarmTalkRequest.getTrouble().getLscn())
					.ttId(alarmTalkRequest.getTrouble().getTtId())
					.custName(alarmTalkRequest.getCustomer().getCustNm())
					.build()
					);
	}

	public ConsultInfo removeConsultInfo(String key) {
		return this.consultInfoCache.remove(key);
	}
	
	/*
	 * user key
	 */
	public void addUserKey(UserKeyInfo userKeyInfo) {
		this.userKeyCache.put(userKeyInfo.getSerialNumber(), userKeyInfo.getUserKey());
	}
	
	public void addUserKey(String serialNumber, String userKey) {
		this.userKeyCache.put(serialNumber, userKey);
	}

	public String getUserKey(UserKeyInfo userKeyInfo) {
		return this.userKeyCache.get(userKeyInfo.getSerialNumber());
	}

	public String removeUserKey(UserKeyInfo userKeyInfo) {
		return this.userKeyCache.remove(userKeyInfo.getSerialNumber());
	}
	
	public String removeUserKey(String serialNumber) {
		return this.userKeyCache.remove(serialNumber);
	}

	public boolean isContainsUserKey(UserKeyInfo userKeyInfo) {
		return this.userKeyCache.containsKey(userKeyInfo.getSerialNumber());
	}
	
	/*
	 * chat
	 */
	public void addChat(ChatInfo chatInfo) {
		this.chatCache.put(chatInfo.getSerialNumber(), chatInfo.getChat());
	}
	
	public void addChat(String serialNumber, Chat chat) {
		this.chatCache.put(serialNumber, chat);
	}
	
	public Chat removeChat(ChatInfo chatInfo) {
		return this.chatCache.remove(chatInfo.getSerialNumber());
	}
	
	public Chat removeChat(String serialNumber) {
		return this.chatCache.remove(serialNumber);
	}
	
	public boolean isContainsChat(ChatInfo chatInfo) {
		return this.chatCache.containsKey(chatInfo.getSerialNumber());
	}
}
