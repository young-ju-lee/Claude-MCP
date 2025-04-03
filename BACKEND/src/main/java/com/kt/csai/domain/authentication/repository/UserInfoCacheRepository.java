package com.kt.csai.domain.authentication.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.kt.csai.domain.authentication.payload.ResponseSigninVo;

@Repository
public class UserInfoCacheRepository {
	private Map<String, ResponseSigninVo> userInfoCache;

	@PostConstruct
	private void init() {
		this.userInfoCache = new HashMap<>();
	}

	public ResponseSigninVo getUserInfo(String key) {
		return userInfoCache.get(key);
	}

	public void addUserInfo(String key, ResponseSigninVo userInfo) {
		this.userInfoCache.put(key, userInfo);
	}

	public ResponseSigninVo removeUserInfo(String key) {
		return this.userInfoCache.remove(key);
	}
}
