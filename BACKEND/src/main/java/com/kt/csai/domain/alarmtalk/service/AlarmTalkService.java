package com.kt.csai.domain.alarmtalk.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSend;
import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSendCsai;
import com.kt.csai.domain.alarmtalk.exception.AlarmTalkServiceUnavailableException;
import com.kt.csai.domain.alarmtalk.exception.model.AlarmTalkErrorCode;
import com.kt.csai.domain.alarmtalk.payload.ReportRequest;
import com.kt.csai.domain.alarmtalk.payload.ReportResponse;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalk;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkGW;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkCsai;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkHistDto;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkInspct;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkRequest;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkResponse;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.ErrInfoCsaiDto;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.ErrInfoGWDto;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.InspctDto;
import com.kt.csai.domain.alarmtalk.repository.AlarmTalkRepository;
import com.kt.csai.domain.alarmtalk.repository.AlarmTalkRepositoryCsai;
import com.kt.csai.domain.consult.repository.ConsultCacheRepository;
import com.kt.csai.domain.consult.service.ConsultService;
import com.kt.csai.global.common.payload.CommonResponse;
import com.kt.csai.global.socket.model.SenderType;
import com.kt.csai.global.socket.payload.SocketMessageDto;
import com.kt.csai.global.socket.service.SocketService;
import com.kt.csai.global.util.RestUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmTalkService {
	private final RestUtil restUtil;
	private final ConsultCacheRepository consultCacheRepository;
	private final ConsultService consultService;
	
	private final SocketService socketService;
	
	private final AlarmTalkDataService alarmTalkDataService;
	
	private final ModelMapper modelMapper;
	private final AlarmTalkRepository alarmTalkRepository;	
	private final AlarmTalkRepositoryCsai alarmTalkRepositoryCsai;
	
	@Value("${URL.ALARM_TALK.SEND}")
	private String sendAlarmTalkUrl;
	
	public AlarmTalkResponse sendAlarmTalk(AlarmTalkRequest requestAlarmTalk) {
		
		AlarmTalk alarmTalk = new AlarmTalk(requestAlarmTalk);			
		
		AlarmTalkResponse response = restUtil.postRequest(sendAlarmTalkUrl, alarmTalk, AlarmTalkResponse.class);
		response.setConsultId(requestAlarmTalk.getConsultId());
		
		if(!response.isSuccess()) {
			throw new AlarmTalkServiceUnavailableException(AlarmTalkErrorCode.valueOfErrorCode(response.getStatus()));
		}
		
		alarmTalkDataService.closeAlarmTalk(requestAlarmTalk.getCustomer().getCustTelNo());
		alarmTalkDataService.save(requestAlarmTalk);
		consultService.createConsultInfo(requestAlarmTalk);
		caching(response);
		
		return response;
	}
	
	private void caching(AlarmTalkResponse response) {
		consultCacheRepository.addConsultId(response);
	}
	
	public ReportResponse sendReportMessage(ReportRequest reportRequest) {
		log.info(reportRequest.toString());
		String messageId = reportRequest.getMessageId();
		
		ReportResponse reportResponse = new ReportResponse();
		reportResponse.setMessageId(messageId);
				
		if(!reportRequest.isSuccess()) {
			socketService.sendMessage(
					SocketMessageDto.builder()
					.consultId(consultCacheRepository.getConsultId(messageId))
					.senderType(SenderType.NOTIFICATION.getType())
					.content(String.format("%s\n[%s]", "메세지 발송에 실패 했습니다.", AlarmTalkErrorCode.valueOfErrorCode(reportRequest.getResultCode()).getErrorMessage()))
					.contentType("TX")
					.build()
					);
		} else {
			consultCacheRepository.removeConsultId(messageId);
		}
		return reportResponse;
	}
	
	/**
	 * 알림톡 발송이력
	 * @param sndNm
	 * @param sndDt
	 * @param pageable
	 * @return
	 */	
	public List<AlarmTalkHistDto> getAlarmTalkSndHistList(String sndCmpNo, String custName, String custTelNo, String sndFromDt, String sndToDt){
		List<AlarmTalkSend> alarmTalkHistEntities = alarmTalkRepository.getAlarmTalkSndHistList(sndCmpNo, custName, custTelNo, sndFromDt, sndToDt);	
		CommonResponse<List<AlarmTalkHistDto>> response = new CommonResponse<>();		
		response.setData(alarmTalkHistEntities.stream().map(entity -> modelMapper.map(entity, AlarmTalkHistDto.class))
				.collect(Collectors.toList()));
		return response.getData();
	}
	
	
	/**
	 * 알림톡 발송 - GW에러에 의한 SMS발송 오류시 호출
	 * @param 
	 * @return
	 * @throws InterruptedException 
	 */
	@Transactional
	public AlarmTalkResponse sendAlarmTalkGW(ErrInfoGWDto errinfo) {
		
		AlarmTalkGW alarmTalk2 = new AlarmTalkGW(errinfo);

		//System.out.println("@@@@@@@@@@ alarmTalk2 :: "+alarmTalk2.toString()+" @@@@@@@@@@@@");

		AlarmTalkResponse response = restUtil.postRequest(sendAlarmTalkUrl, alarmTalk2, AlarmTalkResponse.class);
		
		if(!response.isSuccess()) {
			throw new AlarmTalkServiceUnavailableException(AlarmTalkErrorCode.valueOfErrorCode(response.getStatus()));
		}
		
		caching(response);
		
		return response;
	}
	
	/**
	 * 알림톡 발송 - CSAI에러에 의한 SMS발송 오류시 호출
	 * @param 
	 * @return
	 * @throws InterruptedException 
	 */
	@Transactional
	public AlarmTalkResponse sendAlarmTalkCsai(ErrInfoCsaiDto errinfo) {
		
		AlarmTalkCsai alarmTalk2 = new AlarmTalkCsai(errinfo);		

		AlarmTalkResponse response = restUtil.postRequest(sendAlarmTalkUrl, alarmTalk2, AlarmTalkResponse.class);
		
		if(!response.isSuccess()) {
			throw new AlarmTalkServiceUnavailableException(AlarmTalkErrorCode.valueOfErrorCode(response.getStatus()));
		}
				
		alarmTalkRepositoryCsai.deleteByexitCd(errinfo.getExitCd()); ///tb_interface_failed 전체 삭제		
		
		caching(response);
		
		return response;
	}

	/**
	 * 알림톡 발송 - 주간점검 담당자 알람톡 발송
	 * @param 
	 * @return
	 * @throws InterruptedException 
	 */
	@Transactional
	public AlarmTalkResponse sendTalkInspct(InspctDto inspct) {
				
		AlarmTalkInspct alarmTalk2 = new AlarmTalkInspct(inspct);		

		AlarmTalkResponse response = restUtil.postRequest(sendAlarmTalkUrl, alarmTalk2, AlarmTalkResponse.class);
		
		if(!response.isSuccess()) {
			throw new AlarmTalkServiceUnavailableException(AlarmTalkErrorCode.valueOfErrorCode(response.getStatus()));
		}
		
		caching(response);
		
		return response;
	}
	
	/**
	 * 알림톡 발송 - 파라미터 처리 ( 오류 코드 or 초기화) 
	 * @param 
	 * @return 
	 * @throws InterruptedException 
	 */
	@Transactional
	public Object setAlarmTalk(String type, Boolean isInit) {
		
		if(type.equals("1")) {
			return null;			
		} else if(type.equals("2")) {
			// CSAI			
			AlarmTalkSendCsai entity = new AlarmTalkSendCsai();		
			
			if(isInit.equals(false)) {
				entity.exitCd();
				entity.status();
		    } else if(isInit.equals(true)) {
				entity.init();
			}
			      
			return modelMapper.map(entity, ErrInfoCsaiDto.class);
		}		
		
		return null;
	}
	
}
