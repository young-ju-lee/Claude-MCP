package com.kt.csai.domain.trouble.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_hdms_dgrm")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(HdmsDgrmId.class)
public class HdmsDgrm {
	
	@Id
	@Column(name = "ttid")
	private String ttid;
	
	@Id
	@Column(name = "ip")
	private String ip;
	
	@Column(name = "gw_ip")
	private String gwIp;
	
	@Column(name = "terml_type")
	private String termlType;
	
	@Column(name = "dgrm_yn")
	private String dgrmYn;
	
	@Column(name = "seq")
	private String seq;
	
	@CreationTimestamp
	@Column(name = "cret_dt")
	private LocalDateTime cretDt;
		
	@Id
	@Column(name = "scn")
	private String scn;
	
	@Column(name = "create_yn")
	private String createYn;
	
}
