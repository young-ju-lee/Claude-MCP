package com.kt.csai.domain.consult.service;

import org.springframework.stereotype.Service;

import com.kt.csai.domain.consult.model.chat.Chat;
import com.kt.csai.domain.consult.payload.cache.ChatInfo;
import com.kt.csai.domain.consult.payload.cache.UserKeyInfo;
import com.kt.csai.domain.consult.repository.ConsultCacheRepository;
import com.kt.csai.global.common.payload.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultCacheService {
	private ConsultCacheRepository cacheRepository;

	public CommonResponse<?> addUserKey(UserKeyInfo userKeyInfo) {
		CommonResponse<Void> response = new CommonResponse<>();

		cacheRepository.addUserKey(userKeyInfo);
		response.setSuccess(cacheRepository.isContainsUserKey(userKeyInfo));

		return response;
	}

	public CommonResponse<?> removeUserKey(UserKeyInfo userKeyInfo) {
		CommonResponse<String> response = new CommonResponse<>();

		boolean isContains = cacheRepository.isContainsUserKey(userKeyInfo);
		if (isContains) {
			response.setData(cacheRepository.removeUserKey(userKeyInfo));
		} else {
			response.setSuccess(false);
		}

		return response;
	}

	public CommonResponse<?> getUserKey(UserKeyInfo userKeyInfo) {
		CommonResponse<String> response = new CommonResponse<>();

		boolean isContains = cacheRepository.isContainsUserKey(userKeyInfo);
		if (isContains) {
			response.setData(cacheRepository.getUserKey(userKeyInfo));
		} else {
			response.setSuccess(false);
		}

		return response;
	}

	public CommonResponse<?> addChat(ChatInfo chatInfo) {
		CommonResponse<Void> response = new CommonResponse<>();

		cacheRepository.addChat(chatInfo);
		response.setSuccess(cacheRepository.isContainsChat(chatInfo));

		return response;
	}

	public CommonResponse<?> removeChat(ChatInfo chatInfo) {
		CommonResponse<Chat> response = new CommonResponse<>();

		boolean isContains = cacheRepository.isContainsChat(chatInfo);
		if (isContains) {
			response.setData(cacheRepository.removeChat(chatInfo));
		} else {
			response.setSuccess(false);
		}

		return response;
	}
}
