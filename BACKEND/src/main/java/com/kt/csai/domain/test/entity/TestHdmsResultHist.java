package com.kt.csai.domain.test.entity;
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
@Table(name = "tb_hdms_reslt_hist")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestHdmsResultHist {
	
	@Id
	@Column(name = "ttid")
	private String[] ttid;
	
}
