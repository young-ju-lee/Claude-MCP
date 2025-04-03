package com.kt.csai.domain.authentication.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_auth")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
	
	@Id
	@Column(name = "emp_no")
	private String empNo;
	
	@Column(name = "refresh_token")
	private String refreshToken;
	
	@Column(name = "create_time", updatable = false)
	@CreationTimestamp
	private LocalDateTime createTime;
	
	public void clearRefreshToken() {
		this.refreshToken = null;
	}
}
