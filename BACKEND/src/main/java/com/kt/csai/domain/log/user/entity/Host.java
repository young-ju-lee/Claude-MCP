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
public class Host implements Serializable {
	private static final long serialVersionUID = -617201870096401239L;

	@Column(name = "host_name")
	private String hostName;

	@Column(name = "host_ip")
	private String hostIp;
}
