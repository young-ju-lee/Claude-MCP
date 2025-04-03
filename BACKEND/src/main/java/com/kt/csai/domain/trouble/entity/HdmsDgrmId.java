package com.kt.csai.domain.trouble.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class HdmsDgrmId implements Serializable {
	
	private static final long serialVersionUID = 2788274530502369743L;

	private String ttid;
		
	private String ip;
	
	private String scn;
		
}
