package com.kt.csai.global.util;

import java.util.UUID;

public class StringUtil {

	public static boolean isNullOrEmpty(String data) {
		return data == null || data.length() == 0;
	}

	public static String eraseDash(String before) {
		String after = "";

		for (int i = 0; i < before.length(); i++) {
			if (before.charAt(i) != '-')
				after += before.charAt(i);
		}

		return after;
	}

	public static String generateSerialNumber() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		StringBuffer sb = new StringBuffer(uuid);
		sb.insert(sb.length() / 2, '-');

		return sb.substring(0, 30);
	}
}
