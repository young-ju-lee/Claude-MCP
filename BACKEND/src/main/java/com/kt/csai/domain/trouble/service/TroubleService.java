package com.kt.csai.domain.trouble.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.trouble.dto.HdmsDgrmDto;
import com.kt.csai.domain.trouble.dto.HdmsResultInterface;
import com.kt.csai.domain.trouble.dto.NonMoveTgtInterface;
import com.kt.csai.domain.trouble.dto.OfficeCodeInterface;
import com.kt.csai.domain.trouble.dto.TroubleAcceptDto;
import com.kt.csai.domain.trouble.dto.TroubleCompleteDto;
import com.kt.csai.domain.trouble.entity.HdmsDgrm;
import com.kt.csai.domain.trouble.entity.TroubleAccept;
import com.kt.csai.domain.trouble.entity.TroubleComplete;
import com.kt.csai.domain.trouble.payload.response.NonMoveTarget;
import com.kt.csai.domain.trouble.repository.HdmsDgrmRepository;
import com.kt.csai.domain.trouble.repository.HdmsResultRepository;
import com.kt.csai.domain.trouble.repository.TroubleAcceptRepository;
import com.kt.csai.domain.trouble.repository.TroubleCompleteRepository;
import com.kt.csai.global.common.payload.CommonResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TroubleService {

	private final TroubleAcceptRepository troubleAcceptRepository;
	private final TroubleCompleteRepository troubleCompleteRepository;
	private final HdmsDgrmRepository hdmsDgrmRepository;
	private final HdmsResultRepository hdmsResultRepository;
 
	private final ModelMapper modelMapper;

	/**
	 * 고장접수정보
	 * 
	 * @param ttId
	 * @return
	 */
	public TroubleAcceptDto getAccptInfo(String ttId) {
		Optional<TroubleAccept> optional = troubleAcceptRepository.findById(ttId);
		if (!optional.isPresent()) {
			return null;
		}
		TroubleAccept info = optional.get();
		CommonResponse<TroubleAcceptDto> response = new CommonResponse<>();
		response.setData(modelMapper.map(info, TroubleAcceptDto.class));
		return response.getData();
	}

	/**
	 * 1개월내 고장이력
	 * 
	 * @return
	 */
	public List<TroubleCompleteDto> getTroubleHistList(String ttId) {
		List<TroubleComplete> list = troubleCompleteRepository.getTroubleHistList(ttId);
		CommonResponse<List<TroubleCompleteDto>> response = new CommonResponse<>();
		response.setData(list.stream().map(entity -> modelMapper.map(entity, TroubleCompleteDto.class))
				.collect(Collectors.toList()));
		return response.getData();
	}

	/**
	 * 고장이력 상세
	 * 
	 * @param ttId
	 * @param lscn
	 * @return
	 */
	public TroubleCompleteDto getTroubleHistInfo(String ttId, String lscn) {
		TroubleComplete info = troubleCompleteRepository.findByTtIdAndLscn(ttId, lscn);
		CommonResponse<TroubleCompleteDto> response = new CommonResponse<>();
		response.setData(modelMapper.map(info, TroubleCompleteDto.class));
		return response.getData();
	}

	/**
	 * 국사정보 조회
	 * 
	 * @return
	 */
	public List<OfficeCodeInterface> getOfficeCodeList(String userId) {
		List<OfficeCodeInterface> list = troubleAcceptRepository.getOfficeCodeList(userId);
		return list;
	}

	/**
	 * AI 무출동 대상조회
	 * 
	 * @param svcMainClsCode
	 * @param custName
	 * @param svcId
	 * @param troubReceiptDt
	 * @param pageable
	 * @return
	 */
//	public List<NonMoveTgtInterface> getNonMoveTgtList(List<String> svcMainClsCodeArray, String custName, String svcId,
//			String userId, List<String> officesCodeArray, String ttId, String rsvDt) {
//		List<NonMoveTgtInterface> list = troubleAcceptRepository.getNonMoveTgtList(svcMainClsCodeArray, custName, svcId,
//				userId, officesCodeArray, ttId, rsvDt);
//		return list;
//	}	
	public List<NonMoveTarget> getNonMoveTgtList(List<String> svcMainClsCodeArray, String custName, String svcId,
			String userId, List<String> officesCodeArray, String ttId, String rsvDt, String rsvFromDt, String rsvToDt) {
		List<NonMoveTgtInterface> list = troubleAcceptRepository.getNonMoveTgtList(svcMainClsCodeArray, custName, svcId,
				userId, officesCodeArray, ttId, /*rsvDt,*/ rsvFromDt, rsvToDt);		
		
		List<NonMoveTarget> nonMoveTargets = list.stream().map(origin -> modelMapper.map(origin, NonMoveTarget.class))
				.collect(Collectors.toList());
		return nonMoveTargets;
	}

	/**
	 * 망구성도 조회
	 * 
	 * @param ttId
	 * @return
	 */
	public List<HdmsDgrmDto> getHdmsDgrmList(String ttId) {
		List<HdmsDgrm> list = hdmsDgrmRepository.findByTtidOrderBySeqAsc(ttId);
		CommonResponse<List<HdmsDgrmDto>> response = new CommonResponse<>();
		response.setData(
				list.stream().map(entity -> modelMapper.map(entity, HdmsDgrmDto.class)).collect(Collectors.toList()));
		return response.getData();
	}

	/**
	 * 망구성도 팝업 조회
	 * 
	 * @param ttId
	 * @param lscn
	 * @return
	 */
	public HdmsResultInterface getHdmsResultInfo(String ttId, String scn) {
		return hdmsResultRepository.findByTtIdAndScn(ttId, scn);
	}

	/**
	 * 망구성도 정보 저장
	 * 
	 * @return
	 */
	@Transactional
	public int saveHdmsDgrm() {

		int insertCnt = 0;
		List<HdmsResultInterface> list = hdmsResultRepository.getTtidList(); // pk 중복 데이터는 제외하고 내려줌
		for (HdmsResultInterface dto : list) {
			String ttId = dto.getTtId().toString();
			String createYn = hdmsDgrmRepository.getCreateYn(ttId); // 망구성도 생성여부 조회
			if (!"Y".equals(createYn)) { // 생성되지 않은 데이터만 인서트 처리 
				insertCnt += hdmsDgrmRepository.saveDgrm(ttId);
			}
		}
		return insertCnt;
	}
}
