package com.kt.csai.domain.alarmtalk.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class AlarmTalkId implements Serializable {
	private static final long serialVersionUID = 8054215260622680699L;

	@Column(name = "tt_id")
	private String ttId;
	
	@CreationTimestamp
	@Column(name = "snd_dt")
	private LocalDateTime sndDt;
}
