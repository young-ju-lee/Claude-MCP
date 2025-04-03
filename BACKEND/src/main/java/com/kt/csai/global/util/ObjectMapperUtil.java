package com.kt.csai.global.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ObjectMapperUtil {
	private final ObjectMapper objectMapper;
	
	public Map<String, Object> convertStringToMap(String val) {
		Properties props = new Properties();
		Map<String, Object> map = new HashMap<>();
		try {
			props.load(new StringReader(val.substring(1, val.length() - 1).replace(", ", "\n")));

			for (Map.Entry<Object, Object> entry : props.entrySet()) {
				map.put((String) entry.getKey(), (String) entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}
	
	public <T> T convertValue(String token, Class<T> returnType) {
		return objectMapper.convertValue(convertStringToMap(token), returnType);
	}
}
