package com.kt.csai.domain.consult.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.kt.csai.domain.consult.payload.dto.DoneConsult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_consult")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consult {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "consult_id")
	private String consultId;

	@Column(name = "tel_num")
	private String telNum;

	@Column(name = "user_key")
	private String userKey;

	@Column(name = "file_name")
	private String chatFileName;

	@Column(name = "cstt_nm")
	private String csttNm;

	@Column(name = "cstt_cmpno")
	private String csttCmpNo;

	@Column(name = "lscn")
	private String lscn;

	@Column(name = "cnvrst_sttus")
	private String cnvrstSttus;

	@CreationTimestamp
	@Column(name = "create_datetime")
	private LocalDateTime createDatetime;

	@Column(name = "go_yn")
	private String goYn;
	
	@Column(name = "go_reason")
	private String goReason;

	@Column(name = "last_guide_code")
	private String lastGuideCode;
	
	@Column(name = "tt_id")
	private String ttId;
	
	@Column(name = "cust_nm")
	private String custNm;
	
	@Column(name = "scn")
	private String scn;

	@PrePersist
	public void prePersist() {
		this.waiting();
	}

	public void done() {
		this.cnvrstSttus = "D";
	}

	public void done(DoneConsult doneConsult) {
		done();
		this.lastGuideCode = doneConsult.getLastGuideCode();
		this.goYn = doneConsult.getGoYn();
		this.goReason = doneConsult.getGoReason();
		this.scn = doneConsult.getScn();
	}

	public void consulting() {
		this.cnvrstSttus = "A";
	}

	public void pending() {
		this.cnvrstSttus = "S";
	}
	
	public void waiting() {
		this.cnvrstSttus = "W";
	}
	
	public void start(String userKey) {
		this.userKey = userKey;
		this.consulting();
	}
}
