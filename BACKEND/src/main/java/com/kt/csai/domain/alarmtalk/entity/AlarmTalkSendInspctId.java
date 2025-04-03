package com.kt.csai.domain.alarmtalk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlarmTalkSendInspctId implements Serializable {
	private static final long serialVersionUID = 8054215260622680699L;
	
	@Column(name = "tt_id")
	private String ttId;

	@Column(name = "user_nm")
	private String name;
}
