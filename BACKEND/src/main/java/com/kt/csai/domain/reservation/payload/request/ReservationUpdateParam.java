package com.kt.csai.domain.reservation.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReservationUpdateParam extends ReservationParam {
	@JsonProperty("id")
	private Long id;
}
