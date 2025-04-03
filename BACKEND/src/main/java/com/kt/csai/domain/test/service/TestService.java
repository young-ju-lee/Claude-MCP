package com.kt.csai.domain.test.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.test.repository.TestHdmsResultHistRepository;
import com.kt.csai.domain.test.repository.TestHdmsResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

	private final TestHdmsResultRepository hdmsRsltRepository;
	private final TestHdmsResultHistRepository hdmsRsltHistRepository;
 
	private final ModelMapper modelMapper;

		
	/**
	 * 망구성도 정보 조회
	 * 
	 * @return
	 */
	@Transactional
	public void chkHdmsDgrm(String[] ttid, String type, String tableNm) {
 			
		for (int i=0; i<ttid.length; i++) {
			if("1".equals(type)) {
				if(tableNm.equals("reslt")) {
					hdmsRsltRepository.chkDgrm1(ttid[i]);
				} else {
					hdmsRsltHistRepository.chkDgrm1(ttid[i]);
				}
			} else if("2".equals(type)) {
				if(tableNm.equals("reslt")) {
					hdmsRsltRepository.chkDgrm2(ttid[i]);
				} else {
					hdmsRsltHistRepository.chkDgrm2(ttid[i]);
				}  
			} else {
				if(tableNm.equals("reslt")) {
					hdmsRsltRepository.chkDgrm3(ttid[i]);
				} else {
					hdmsRsltHistRepository.chkDgrm3(ttid[i]);
				}  
			}
			System.out.println("@@@@ cnt :: "+i+" , ttid1 :: "+ttid[i]+" @@@@");
		}
	}

}
