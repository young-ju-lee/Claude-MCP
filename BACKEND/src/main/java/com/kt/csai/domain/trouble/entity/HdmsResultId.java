package com.kt.csai.domain.trouble.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

public class HdmsResultId implements Serializable {
		
	private static final long serialVersionUID = 5106176015507913570L;

	@Column(name = "ttid", length = 16, nullable = false)
	private String ttId;
	
	@Column(name = "rcvdt", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date rcvDt;
	
	@Column(name = "scn", length = 20, nullable = false)
	private String scn;

}
