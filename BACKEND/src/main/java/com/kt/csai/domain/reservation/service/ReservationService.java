package com.kt.csai.domain.reservation.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.consult.service.ConsultService;
import com.kt.csai.domain.reservation.entity.Reservation;
import com.kt.csai.domain.reservation.payload.request.ReservationParam;
import com.kt.csai.domain.reservation.payload.response.ReservationResponse;
import com.kt.csai.domain.reservation.repository.ReservationRepository;
import com.kt.csai.global.common.payload.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final ModelMapper modelMapper;

	@Transactional
	public CommonResponse<ReservationResponse> doReservation(ReservationParam reservationParam) {
		CommonResponse<ReservationResponse> response = new CommonResponse<>();
		ReservationResponse data = null;

		Optional<Reservation> entity = reservationRepository.findByttIdAndStatusNot(reservationParam.getTtId(),
				"D");		
		if (entity.isPresent()) {
			Reservation updateEntity = entity.get();
			if ("done".equals(reservationParam.getStatus())) {
				updateEntity.done();
			} else if ("reservation".equals(reservationParam.getStatus())) {
				updateEntity.doReservation(reservationParam.getRsvTime());
			}

			data = modelMapper.map(updateEntity, ReservationResponse.class);
		} else if ("reservation".equals(reservationParam.getStatus())) {
			reservationParam.setStatus("R");
			Reservation insertEntity = reservationRepository.save(modelMapper.map(reservationParam, Reservation.class));

			data = modelMapper.map(insertEntity, ReservationResponse.class);
		}

		response.setData(data);

		return response;
	}

	@Transactional
	public CommonResponse<ReservationResponse> doneReservation(String ttId) {
		CommonResponse<ReservationResponse> response = new CommonResponse<>();

		Optional<Reservation> entity = reservationRepository.findByttIdAndStatusNot(ttId, "D");

		if (entity.isPresent()) {
			entity.get().done();

			ReservationResponse data = modelMapper.map(entity, ReservationResponse.class);
			response.setData(data);

		} else {
			response.setSuccess(false);
		}

		return response;
	}
}
