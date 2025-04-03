package com.kt.csai.domain.trouble.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kt.csai.domain.alarmtalk.payload.alarmtalk.TroubleDto;
import com.kt.csai.domain.trouble.dto.HdmsDgrmDto;
import com.kt.csai.domain.trouble.dto.HdmsResultInterface;
import com.kt.csai.domain.trouble.dto.NonMoveTgtInterface;
import com.kt.csai.domain.trouble.dto.NonMoveTgtRequest;
import com.kt.csai.domain.trouble.dto.OfficeCodeInterface;
import com.kt.csai.domain.trouble.dto.TroubleAcceptDto;
import com.kt.csai.domain.trouble.dto.TroubleCompleteDto;
import com.kt.csai.domain.trouble.payload.response.NonMoveTarget;
import com.kt.csai.domain.trouble.service.TroubleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
@RequestMapping("/trouble")
@Slf4j
public class TroubleController {
		
	private final TroubleService troubleService; 
		
	//-------------------------------------------------------------------
	// 고장접수 정보
	//-------------------------------------------------------------------	
	/**
	 * 고장접수정보 Get
	 * @return
	 */
	@GetMapping("/accptInfo")
	@ResponseBody
    public ResponseEntity<TroubleAcceptDto> getAccptInfo(@RequestParam(required=true) String ttId) {		
		log.info("========== TroubleController.getAccptInfo ==========");			
		TroubleAcceptDto accptInfo = troubleService.getAccptInfo(ttId);		
        return new ResponseEntity<>(accptInfo, HttpStatus.OK);
    }
	 
	/**
	 * 고장접수정보 Post
	 * @return
	 */
	@PostMapping("/accptInfoP")
	@ResponseBody    
	public ResponseEntity<TroubleAcceptDto> getAccptInfoP(@RequestBody TroubleDto req) {
		log.info("========== TroubleController.accptInfoP ==========");			
		String ttId = req.getTtId();		
		TroubleAcceptDto accptInfo = troubleService.getAccptInfo(ttId);			
        return new ResponseEntity<>(accptInfo, HttpStatus.OK);
    }
	
	//-------------------------------------------------------------------
	// 고장이력
	//-------------------------------------------------------------------	
	/**
	 * 1개월내 고장이력
	 * @return
	 */
	@GetMapping("/cmpltHistList")
	@ResponseBody
	public ResponseEntity<List<TroubleCompleteDto>> getTroubleHistList(@RequestParam(required=true) String ttId) {
		log.info("========== TroubleController.getTroubleHistList ==========");		       
		List<TroubleCompleteDto> troubleHistList = troubleService.getTroubleHistList(ttId);
        return new ResponseEntity<>(troubleHistList, HttpStatus.OK);
    }
	
	/**
	 * 1개월내 고장이력 Post
	 * @return
	 */
	@PostMapping("/cmpltHistListP")
	@ResponseBody
	public ResponseEntity<List<TroubleCompleteDto>> getTroubleHistListP(@RequestBody TroubleDto req) {
		log.info("========== TroubleController.getTroubleHistList ==========");
		String ttId = req.getTtId();		        
		List<TroubleCompleteDto> troubleHistList = troubleService.getTroubleHistList(ttId);
        return new ResponseEntity<>(troubleHistList, HttpStatus.OK);
    }
	
	/**
	 * 고장이력 상세 Get
	 * @return
	 */
	@GetMapping("/cmpltHistInfo")
	@ResponseBody    
	public ResponseEntity<TroubleCompleteDto> getTroubleHistInfo(@RequestParam(required=true) String ttId, @RequestParam(required=true) String lscn) {
		log.info("========== TroubleController.getTroubleHistInfo ==========");			
		TroubleCompleteDto histInfo = troubleService.getTroubleHistInfo(ttId, lscn);		
        return new ResponseEntity<>(histInfo, HttpStatus.OK);
    }
	
	/**
	 * 고장이력 상세 Post
	 * @return
	 */
	@PostMapping("/cmpltHistInfoP")
	@ResponseBody    
	public ResponseEntity<TroubleCompleteDto> getTroubleHistInfoP(@RequestBody TroubleDto req) {
		log.info("========== TroubleController.getTroubleHistInfoP ==========");			
		String ttId = req.getTtId();
		String lscn = req.getLscn();		
		TroubleCompleteDto histInfo = troubleService.getTroubleHistInfo(ttId, lscn);			
        return new ResponseEntity<>(histInfo, HttpStatus.OK);
    }
	
	
	/**
	 * 국사정보 조회 Get
	 * @return
	 */
	@GetMapping("/officeCodeList")
	@ResponseBody    
	public ResponseEntity<List<OfficeCodeInterface>> getOfficeCodeList(@RequestParam(required=true) String userId) {
		log.info("========== TroubleController.getOfficeCodeList ==========");			
		List<OfficeCodeInterface> officeCodeList = troubleService.getOfficeCodeList(userId);		
        return new ResponseEntity<>(officeCodeList, HttpStatus.OK);
    }
	
	/**
	 * AI 무출동 대상조회 Post
	 * @param req
	 * @return
	 */
	@PostMapping("/nonMoveTgtListP")
	@ResponseBody    
	public ResponseEntity<Page<NonMoveTarget>> getNonMoveTgtListP(@RequestBody NonMoveTgtRequest req) {		
		log.info("========== TroubleController.getNonMoveTgtListP ==========");		
		List<String> svcMainClsCodeArray = new ArrayList<String>();				
		String[] svcMainClsCode = req.getSvcMainClsCode();		
		String custName = req.getCustName();
		String svcId = req.getSvcId();
		//String troubReceiptDt = req.getTroubReceiptDt();
		String userId = req.getUserId();
		List<String> officesCodeArray = new ArrayList<String>();
		String[] officesCode = req.getOfficesCode();		
		if(svcMainClsCode != null) {
			for(String product : svcMainClsCode) {
				svcMainClsCodeArray.add(product);
			}
		}		
		if(officesCode != null) {
			for(String code : officesCode) {
				officesCodeArray.add(code);
			}
		}		
		String ttId = req.getTTID();
		String rsvDt = req.getRsvDt();
		String rsvFromDt = req.getRsvFromDt();
		String rsvToDt = req.getRsvToDt();
		List<NonMoveTarget> list = troubleService.getNonMoveTgtList(
				svcMainClsCodeArray, 
				custName, 
				svcId, 				
				userId, 
				officesCodeArray, 
				ttId,
				rsvDt,
				rsvFromDt,
				rsvToDt);
		
		int page = req.getPage();
		int size = req.getSize();
		
		/*
		log.info("=====size : "+size+" or page : "+page+" ======="); //size :: 10, page ::0 
		*/
		
		Pageable pageable = PageRequest.of(page, size);
		
		int start = (int)pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());		
		Page<NonMoveTarget> pageList = new PageImpl<NonMoveTarget>(list.subList(start, end), pageable, list.size());
		System.out.println("pageList ::: "+pageList);
        return new ResponseEntity<>(pageList, HttpStatus.OK);
        
//        List<NonMoveTgtInterface> list = troubleService.getNonMoveTgtList(
//				svcMainClsCodeArray, 
//				custName, 
//				svcId, 				
//				userId, 
//				officesCodeArray, 
//				ttId,
//				rsvDt);
//		
//		int page = req.getPage();
//		int size = req.getSize();		
//		Pageable pageable = PageRequest.of(page, size);
//		
//		int start = (int)pageable.getOffset();
//		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());		
//		Page<NonMoveTgtInterface> pageList = new PageImpl<NonMoveTgtInterface>(list.subList(start, end), pageable, list.size());
//        return new ResponseEntity<>(pageList, HttpStatus.OK);
    }
	
	/**
	 * 망구성도 조회
	 * @return
	 */
	@GetMapping("/hdmsDgrmList")
	@ResponseBody    
	public ResponseEntity<List<HdmsDgrmDto>> getHdmsDgrmList(@RequestParam(required=true) String ttId) {
		log.info("========== TroubleController.getHdmsDgrmList ==========");			
		List<HdmsDgrmDto> list = troubleService.getHdmsDgrmList(ttId);			
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	/**
	 * 망구성도 팝업 조회
	 * @return
	 */
	@GetMapping("/hdmsResultInfo")
	@ResponseBody    
	public ResponseEntity<HdmsResultInterface> getHdmsResultInfo(@RequestParam(required=true) String ttId, @RequestParam(required=true) String scn) {
		log.info("========== TroubleController.getHdmsResultInfo ==========");		
		HdmsResultInterface info = troubleService.getHdmsResultInfo(ttId, scn);		
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
	
	/**
	 * 망구성도 정보 저장
	 * @return
	 */
	@GetMapping("/saveHdmsDgrm")
	@ResponseBody    
	public ResponseEntity<HashMap<String, Integer>> saveHdmsDgrm() {
		log.info("========== TroubleController.saveHdmsDgrm ==========");				
		int insertCnt = troubleService.saveHdmsDgrm();					
		HashMap<String, Integer> rtrnMap = new HashMap<String, Integer>();
		rtrnMap.put("insertCnt", insertCnt);
        return new ResponseEntity<>(rtrnMap, HttpStatus.OK);
    }
	
}
