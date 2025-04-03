package com.kt.csai.domain.log.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Local implements Serializable{
	private static final long serialVersionUID = -5228890685924379878L;

	@Column(name = "user_ip")
	private String userIp;
	
	@Column(name = "user_id")
	private String userId;
}
