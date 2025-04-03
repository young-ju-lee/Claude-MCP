package com.kt.csai.domain.alarmtalk.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "tb_interface_failed")
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmTalkSendCsai implements Serializable {
	private static final long serialVersionUID = -8009065842904999576L;
	
	@Id
	@Column(name = "step_execution_id")
	private int stexecId;
	
	@Column(name = "version")
	private int version;
	
	@Column(name = "step_name")
	private String stpNm = "";
	
	@Column(name = "job_execution_id")
	private int jbexecId;
	
	@Column(name = "start_time") //현재시간 
	Timestamp stTime = new Timestamp(System.currentTimeMillis());
		
	@Column(name = "status")
	private String status = "";

	@Column(name = "exit_code")
	private String exitCd = "";
	
	@Column(name = "exit_message")
	private String exitMsg = "";	
	
	public void exitCd() {
		this.exitCd = "FAILED";
	}
	
	public void status() {
		this.status = "FAILED";
	}
	
	public void init() {
		this.exitCd = "";
		this.status = "";
	}
}
