package com.kt.csai.domain.alarmtalk.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSendGW;
import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSendInspct;
import com.kt.csai.domain.alarmtalk.payload.ReportRequest;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkHistDto;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkHistRequest;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkRequest;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.ErrInfoCsaiDto;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.ErrInfoGWDto;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.InspctDto;
import com.kt.csai.domain.alarmtalk.repository.AlarmTalkRepositoryCsai;
import com.kt.csai.domain.alarmtalk.repository.AlarmTalkRepositoryGW;
import com.kt.csai.domain.alarmtalk.repository.AlarmTalkRepositoryInspct;
import com.kt.csai.domain.alarmtalk.service.AlarmTalkService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/alarm")
public class AlarmTalkController {
	
	private final AlarmTalkService alarmTalkService;
	private final AlarmTalkRepositoryGW alarmTalkRepositoryGW;
	private final AlarmTalkRepositoryCsai alarmTalkRepositoryCsai;
	private final AlarmTalkRepositoryInspct alarmTalkRepositoryInspct;
	
	@PostMapping("/send")
	public ResponseEntity<?> sendAlarmTalk(@RequestBody AlarmTalkRequest requestAlarmTalk) {
		return new ResponseEntity<>(alarmTalkService.sendAlarmTalk(requestAlarmTalk), HttpStatus.OK);
	}
	
	@PostMapping("/report")
	public ResponseEntity<?> receiveReport(@RequestBody ReportRequest reportRequest) {
		return new ResponseEntity<>(alarmTalkService.sendReportMessage(reportRequest), HttpStatus.OK);
	}
	
	/**
	 * 알림톡 발송이력 Get
	 * @param sndNm
	 * @param sndDt
	 * @param pageable
	 * @return
	 */
	@GetMapping("/alarmTalkSndHistList")
	@ResponseBody
    public ResponseEntity<Page<AlarmTalkHistDto>> getAlarmTalkSndHistList(    		
    		@RequestParam(required=false) String sndCmpNo,
    		@RequestParam(required=false) String custName,
    		@RequestParam(required=false) String custTelNo,
    		@RequestParam(required=false) String sndFromDt,
    		@RequestParam(required=false) String sndToDt,
    		Pageable pageable) {		
		List<AlarmTalkHistDto> list = alarmTalkService.getAlarmTalkSndHistList(sndCmpNo, custName, custTelNo, sndFromDt, sndToDt);	
		int start = (int)pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
		Page<AlarmTalkHistDto> pageList = new PageImpl<AlarmTalkHistDto>(list.subList(start, end), pageable, list.size());						
        return new ResponseEntity<>(pageList, HttpStatus.OK);		
    }
	
	/**
	 * 알림톡 발송이력 Post
	 * @param req
	 * @return
	 */
	@PostMapping("/alarmTalkSndHistListP")
	@ResponseBody
    public ResponseEntity<Page<AlarmTalkHistDto>> getAlarmTalkSndHistListP(@RequestBody AlarmTalkHistRequest req) {	
		String sndCmpNo = req.getSndCmpNo(); 
		String custName = req.getCustName();
		String custTelNo = req.getCustTelNo();
		String sndFromDt = req.getSndFromDt();
		String sndToDt = req.getSndToDt();				
		int page = req.getPage();
		int size = req.getSize();		
		Pageable pageable = PageRequest.of(page, size);
		
		List<AlarmTalkHistDto> list = alarmTalkService.getAlarmTalkSndHistList(sndCmpNo, custName, custTelNo, sndFromDt, sndToDt);	
		
//		for(AlarmTalkHistDto alarmTalkHist : list) {
//			alarmTalkHist.getSender().setSndCmpNo(StringUtil.maskingForEmployeeNumber(alarmTalkHist.getSender().getSndCmpNo()));
//			alarmTalkHist.getSender().setSndNm(StringUtil.maskingForName(alarmTalkHist.getSender().getSndNm()));
//			
//			alarmTalkHist.getCustomer().setCustNm(StringUtil.maskingForName(alarmTalkHist.getCustomer().getCustNm()));
//			alarmTalkHist.getCustomer().setCustTelNo(StringUtil.maskingForTel(alarmTalkHist.getCustomer().getCustTelNo()));
//		}
		
		int start = (int)pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
		Page<AlarmTalkHistDto> pageList = new PageImpl<AlarmTalkHistDto>(list.subList(start, end), pageable, list.size());						
        return new ResponseEntity<>(pageList, HttpStatus.OK);		
    }
	
	/**
	 * 알림톡 발송 - GW에러에 의한 SMS발송 오류시 호출
	 * @param 
	 * @return
	 */
	@PostMapping("/sendGW")	
	@Transactional	
	public ResponseEntity<?> sendAlarmTalkGW() {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter  fm = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String workDt = now.format(fm);
					
		/* tb_gw_log 테이블 건수 조회 */
		//String cnt = alarmTalkRepositoryGW.findByCnt();		
		
		List<AlarmTalkSendGW> errorlist = alarmTalkRepositoryGW.getGwErrorList(workDt);
	
		System.out.println("@@@@ GW start & SIZE :: "+errorlist.size()+" @@@@");		

		if( errorlist.size() != 0 ) {			

			ErrInfoGWDto errinfo = new ErrInfoGWDto();
						
			for(int j=0; j<errorlist.size(); j++){

				System.out.println("@@@@ errorlist.getWfwNm :: "+errorlist.get(j).getWfwNm()+" @@@@");
				System.out.println("@@@@ errorlist.getErrCd :: "+errorlist.get(j).getErrCd()+" @@@@");

				errinfo.setWfwNm((errorlist.get(j).getWfwNm()).substring(7));
				errinfo.setErrCd(errorlist.get(j).getErrCd());

				String[] TelNo = {"821088718198","821064289816","821095830777"};
				for(int i=0; i<TelNo.length; i++) {						
					errinfo.setTelNum(TelNo[i]);                          // 전화번호 하드코딩
					new ResponseEntity<>(alarmTalkService.sendAlarmTalkGW(errinfo), HttpStatus.OK);				
				}							
			}			
		} 
		
		return null;  
	}
	
	/**
	 * 알림톡 발송 - CSAI에러에 의한 SMS발송 오류시 호출
	 * @param 
	 * @return
	 */
	@PostMapping("/sendCSAI")	
	public ResponseEntity<?> sendAlarmTalkCsai() {
					
		/* 테이블 건수 조회 */
		String cnt = alarmTalkRepositoryCsai.findByCnt();		
		
		System.out.println("@@@@ CSAI start & cnt :: "+cnt+" @@@@"); 
		
		if( Integer.parseInt(cnt) > 0 ) {
			
			ErrInfoCsaiDto errinfo = new ErrInfoCsaiDto();			
			errinfo = (ErrInfoCsaiDto) alarmTalkService.setAlarmTalk("2",false);			
			
			String[] TelNo = {"821088718198","821064289816","821025467536","821095830777"};
			for(int i=0; i<TelNo.length; i++) {						
				errinfo.setTelNum(TelNo[i]);                          // 전화번호 하드코딩
				new ResponseEntity<>(alarmTalkService.sendAlarmTalkCsai(errinfo), HttpStatus.OK);				
			}
			
			alarmTalkService.setAlarmTalk("2",true); //초기화
		}		
			
		return null;  
	}

	/**
	 * 알림톡 발송 - 주간점검 담당자 알람톡 발송
	 * @param 
	 * @return
	 */
	@PostMapping("/sendInspct")
	public ResponseEntity<?> sendAlarmTalkInspct() {

		//현재 month 체크
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("YYYY-MM-");
		String date = now.format(fm) + "01";

		InspctDto inspct = new InspctDto();		

		/* 멤버 리스트 조회 */
		List<AlarmTalkSendInspct> member = alarmTalkRepositoryInspct.getMemberList();	
		//System.out.println("@@@@ member ::  "+member.toString()+" @@@");	

		//월별 시작일자 종료일자 구하기			
		List<Map<String,String>> mDt = alarmTalkRepositoryInspct.checkMonthDate(date);
		String st_dt = mDt.get(0).get("st_Dt");
		String ed_dt = mDt.get(0).get("ed_Dt");					
		String to_mon = mDt.get(0).get("to_mon");
		
		if(member.size() == 0){
			System.out.println("@@@@ 멤버리스트 Insert @@@");

			//table이 비어있는경우 실행 ( 최초 1번만 실행되고 차후 ispt_dt 일자만 업데이트 예정 )
			alarmTalkRepositoryInspct.InsertMemberList();
			
			//멤버 테이블 재 조회
			member = alarmTalkRepositoryInspct.getMemberList();		
		}

		List<Map<String,String>> workDate = alarmTalkRepositoryInspct.checkWorkDate();
		if(workDate.size() == 0){	
			System.out.println("@@@@ no date & Insert work_Dt @@@");

			//전체 데이터 근무일자 미셋팅 :: 근무일자 업데이트 
			alarmTalkRepositoryInspct.UpdateWorkDate(st_dt, ed_dt);
		} else if(!to_mon.equals(workDate.get(0).get("to_mon"))) {
			System.out.println("@@@@ Change Month to update work_dt  @@@");

			//근무일자는 존재 하지만 월이 다른경우 :: 근무일자 업데이트
			alarmTalkRepositoryInspct.UpdateWorkDate(st_dt, ed_dt);
		}

		/* 점검 담당자 조회 */
		List<AlarmTalkSendInspct> Inspct = alarmTalkRepositoryInspct.getSndInspct();
				
		System.out.println("@@@@ Inspct start & SIZE :: "+Inspct.size()+" @@@@");		
		if(Inspct.size() != 0){
			inspct.setIsptDt(Inspct.get(0).getIsptDt());
			inspct.setName(Inspct.get(0).getName());
			 
			//알림톡 발송
			for(int i=0; i<member.size();i++){							
				String chngTel = member.get(i).getTelNm();
				chngTel = "82"+ chngTel.substring(1);				
				inspct.setTelNm(chngTel);
				new ResponseEntity<>(alarmTalkService.sendTalkInspct(inspct), HttpStatus.OK);
			}
		}
				
		return null;  
	}
	
}
