package com.kt.csai.domain.alarmtalk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sender implements Serializable {
	private static final long serialVersionUID = 1411207072981880346L;
	
	@Column(name = "snd_cmp_no")
	private String sndCmpNo;
	
	@Column(name = "snd_nm")
	private String sndNm;

	@Column(name = "snd_team")
	private String sndTeam;
}
