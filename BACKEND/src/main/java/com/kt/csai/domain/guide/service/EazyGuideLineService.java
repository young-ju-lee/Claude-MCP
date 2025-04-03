package com.kt.csai.domain.guide.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kt.csai.domain.guide.payload.response.EazyGuideLine;
import com.kt.csai.domain.guide.payload.response.EazyGuideLineQueryResultInterface;
import com.kt.csai.domain.guide.repository.AiResultGuideRepository;
import com.kt.csai.global.common.payload.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EazyGuideLineService {
	private final AiResultGuideRepository aiResultGuideRepository;

	public CommonResponse<EazyGuideLine> findEasyGuideLineByTtId(String ttId) {
		List<EazyGuideLineQueryResultInterface> result = aiResultGuideRepository.findEasyGuideLineByTtId(ttId);

		CommonResponse<EazyGuideLine> response = new CommonResponse<>();
		response.setData(new EazyGuideLine(result));
		return response;
	}
}
