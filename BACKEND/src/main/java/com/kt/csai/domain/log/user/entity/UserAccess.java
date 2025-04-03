package com.kt.csai.domain.log.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tb_user_access")
@Getter
@ToString
@Builder
public class UserAccess {
	@Id
	@GeneratedValue
	private Long id;
	
	@CreationTimestamp
	@Column(name = "timestamp")
	private LocalDateTime timestamp;
	
	@Embedded
	private Host host;
	
	@Embedded
	private Local local;
	
	@Column(name = "service")
	private String service;
	
	@Column(name = "operation")
	private String operation;
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "log_type")
	private String logType;
	
	@Column(name = "security_type")
	private String securityType;
	
}
