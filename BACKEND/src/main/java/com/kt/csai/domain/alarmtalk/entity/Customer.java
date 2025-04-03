package com.kt.csai.domain.alarmtalk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer implements Serializable {
	private static final long serialVersionUID = 7461459960909435534L;

	@Column(name = "custNm")
	private String custNm;

	@Column(name = "custTelNo")
	private String custTelNo;
}
