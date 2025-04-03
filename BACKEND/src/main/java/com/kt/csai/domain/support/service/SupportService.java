package com.kt.csai.domain.support.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.support.dto.NoticeDto;
import com.kt.csai.global.common.payload.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupportService {
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	/* notice_list 조회  */
	public static List<NoticeDto> getNoticeList() {
		
		List<NoticeDto> info = new ArrayList<NoticeDto>();
		
		/* 공지사항 리스트 */
		for(int i=0; i<13; i++) {
			NoticeDto no = new NoticeDto();
			
			String tt = " [ 공지 ] 임시 타이틀_"+(i+1);
			String ct = " 임시 컨텐트 내용"+(i+1)+" 테스트중 입니다. ";
			
			no.setTitle(tt);
			no.setContent(ct);
			
			info.add(no);
		}	
		
		CommonResponse<List<NoticeDto>> response = new CommonResponse<>();
		response.setData(info.stream().map(entity -> modelMapper.map(entity, NoticeDto.class))
				.collect(Collectors.toList()));
		return response.getData();
	}
}