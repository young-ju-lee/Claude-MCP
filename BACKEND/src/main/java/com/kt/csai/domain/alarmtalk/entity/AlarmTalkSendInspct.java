package com.kt.csai.domain.alarmtalk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "tb_alarmtalk_Inspct")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmTalkSendInspct implements Serializable {
	private static final long serialVersionUID = -8009065842904999576L;
	
	@EmbeddedId
	private AlarmTalkSendInspctId id;

	@Column(name = "tel_nm")
	private String telNm;
	
	@Column(name = "ispt_dt")
	private String isptDt;

	public String getName() {
		return id != null ? id.getName() : null;
	}

	public String getTelNm() {
		return this.telNm;
	}

	public String getIsptDt() {
		return this.isptDt;
	}

}
