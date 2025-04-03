package com.kt.csai.domain.guide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_ai_reslt_guide")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiResultGuide {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "scn")
	private String scn;

	@Column(name = "tt_id")
	private String ttId;

	@Column(name = "seq_no")
	private String seqNo;

	@Column(name = "terml_type")
	private String termlType;

	@Column(name = "guide1")
	private String guide1;

	@Column(name = "guide2")
	private String guide2;

	@Column(name = "guide3")
	private String guide3;

	@Column(name = "guide4")
	private String guide4;

	@Column(name = "guide5")
	private String guide5;
	
	@Column(name = "guide6")
	private String guide6;
	
	@Column(name = "guide7")
	private String guide7;
	
	@Column(name = "guide8")
	private String guide8;
	
	@Column(name = "guide9")
	private String guide9;
	
	@Column(name = "guide10")
	private String guide10;
}