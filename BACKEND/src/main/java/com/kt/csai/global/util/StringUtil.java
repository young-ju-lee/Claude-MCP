package com.kt.csai.global.util;

import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static String generateTransactionId(String hostName) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		StringBuffer sb = new StringBuffer(uuid);
		sb.insert(sb.length() / 2, '-');
		sb.insert(0, hostName + "-");
		return sb.substring(0, 50);
	}

	public static String maskingForName(String name) {
		if (name == null) {
			return "";
		}

		name = name.substring(0, name.length() - 1) + '*';
		return name;
	}
	
	public static String maskingForEmployeeNumber(String employeeNumber) {
		if (employeeNumber == null) {
			return "";
		}

		employeeNumber = employeeNumber.substring(0, employeeNumber.length() - 3) + "***";
		return employeeNumber;
	}

	public static String maskingForTel(String tel) {		
		if (tel == null) {
			return "";
		}

		if (tel.indexOf("82") == 0) {
			tel = "0" + tel.substring(2, tel.length());
		}

		if (tel.length() == 12) {
			tel = tel.substring(1, tel.length());
		}
		
		String regex = "(\\d{2,3})-?(\\d{3,4})-?(\\d{4})$";

		Matcher matcher = Pattern.compile(regex).matcher(tel);
		if (matcher.find()) {
			String maskingTarget = matcher.group(2);
			int length = maskingTarget.substring(2).length();
			char[] c = new char[length];
			Arrays.fill(c, '*');

			return tel.replace(maskingTarget, maskingTarget.substring(0, 2) + String.valueOf(c));
		}

		return "";
	}
}
