package com.kt.csai.domain.alarmtalk.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSend;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkClose;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkRequest;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkResponse;
import com.kt.csai.domain.alarmtalk.repository.AlarmTalkRepository;
import com.kt.csai.domain.consult.repository.ConsultRepository;
import com.kt.csai.global.util.RestUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmTalkDataService {
	private final AlarmTalkRepository alarmTalkRepository;
	private final ConsultRepository consultRepository;
	private final ModelMapper modelMapper;

	private final RestUtil restUtil;

	@Value("${URL.ALARM_TALK.SEND}")
	private String sendAlarmTalkUrl;

	@Transactional
	public AlarmTalkRequest save(AlarmTalkRequest alarmTalkRequest) {
		AlarmTalkSend entity = modelMapper.map(alarmTalkRequest, AlarmTalkSend.class);
		AlarmTalkSend resultEntity = alarmTalkRepository.save(entity);

		return modelMapper.map(resultEntity, AlarmTalkRequest.class);
	}
		
	@Transactional
	public AlarmTalkRequest receivedAlarmTalk(String consultId) {
		AlarmTalkSend entity = alarmTalkRepository.findByConsultId(consultId);
		entity.received();
		AlarmTalkSend resultEntity = alarmTalkRepository.save(entity);

		return modelMapper.map(resultEntity, AlarmTalkRequest.class);
	}

	@Transactional
	public void closeAlarmTalk() {
		LocalDateTime time = LocalDateTime.now().minusMinutes(10);
		
		List<AlarmTalkSend> alarmTalkSends = alarmTalkRepository.findAllByReceiveYNIsNullAndAlarmTalkIdSndDtBefore(time);
		Set<String> sTelNum = new HashSet<>();		
		for (AlarmTalkSend alarmTalkSend : alarmTalkSends) {
			alarmTalkSend.close();
			consultRepository.deleteByConsultId(alarmTalkSend.getConsultId());
			
			String telNum = alarmTalkSend.getCustomer().getCustTelNo();
			if (!sTelNum.contains(telNum)) {
				restUtil.postRequest(sendAlarmTalkUrl, new AlarmTalkClose(alarmTalkSend.getCustomer().getCustTelNo()),
						AlarmTalkResponse.class);
				sTelNum.add(telNum);
			}
			alarmTalkRepository.save(alarmTalkSend);
		}
	}
	
	@Transactional
	public void closeAlarmTalk(String telNum) {
		List<AlarmTalkSend> alarmTalkSends = alarmTalkRepository.findAllByCustomerCustTelNoAndReceiveYNIsNull(telNum);
		for (AlarmTalkSend alarmTalkSend : alarmTalkSends) {
			alarmTalkSend.close();
			alarmTalkRepository.save(alarmTalkSend);
		}
	}
}
