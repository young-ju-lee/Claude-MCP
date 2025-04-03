package com.kt.csai.domain.reservation.entity;

import java.time.LocalDateTime;

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
@Table(name = "tb_reservation")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "tt_id")
	private String ttId;
	
	@Column(name = "rsv_time")
	private LocalDateTime rsvTime;
	
	@Column(name = "status")
	private String status;
	
	public void doReservation(LocalDateTime rsvTime) {
		this.status = "R";
		this.rsvTime = rsvTime;
	}
	
	public void done() {
		this.status = "D";
	}
}
