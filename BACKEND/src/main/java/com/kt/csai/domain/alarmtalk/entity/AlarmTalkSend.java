package com.kt.csai.domain.alarmtalk.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kt.csai.domain.alarmtalk.payload.alarmtalk.TroubleDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "tb_alarmtalk_snd")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmTalkSend implements Serializable {
	private static final long serialVersionUID = -8009065842904999576L;

	@EmbeddedId
	private AlarmTalkId alarmTalkId;

	@Embedded
	private Sender sender;

	@Embedded
	private Customer customer;

	@Column(name = "svc_main_cls_code")
	private String svcMainClsCode;

	@Column(name = "svc_main_cls_code_desc")
	private String svcMainClsCodeDesc;

	@Column(name = "troub_receipt_dt")
	private String troubReceiptDt;

	@Column(name = "reporter_opinion")
	private String reporterOpinion;

	@Column(name = "consult_id")
	private String consultId;

	@Column(name = "receive_yn")
	private String receiveYN;

	public void setTroubleInfo(TroubleDto trouble) {
		this.alarmTalkId = AlarmTalkId.builder().ttId(trouble.getTtId()).sndDt(LocalDateTime.now()).build();
		this.svcMainClsCode = trouble.getSvcMainClsCode();
	}

	public void received() {
		this.receiveYN = "Y";
	}

	public void close() {
		this.receiveYN = "N";
	}
}
