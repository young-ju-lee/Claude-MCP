package com.kt.csai.domain.log.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogType {
	IN_REQ("IN_REQ")
	, OUT_REQ("OUT_REQ")
	, IN_RES("IN_RES")
	, OUT_RES("OUT_RES")
	, NOTIFY("NOTIFY")
	, ASYNC("ASYNC")
	, IN_MSG("IN_MSG")
	, OUT_MSG("OUT_MSG")
	;

	private final String type;
}
