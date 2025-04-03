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
@Table(name = "tb_gw_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmTalkSendGW implements Serializable {
	private static final long serialVersionUID = -8009065842904999576L;

	@EmbeddedId
	private AlarmTalkSendGWId id;
	
	@Column(name = "err_cd")
	private String errCd;
	
	public String getFldNm(){
		return id != null ? id.getFldNm() : null;
	}

	public String getWfwNm(){
		return id != null ? id.getWfwNm() : null;
	}

	public String getErrCd(){
		return this.errCd;
	}

}
