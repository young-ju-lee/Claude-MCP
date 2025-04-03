package com.kt.csai.domain.alarmtalk.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.alarmtalk.entity.AlarmTalkId;
import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSend;

@Repository
public interface AlarmTalkRepository extends JpaRepository<AlarmTalkSend, AlarmTalkId> {
	
	@Query(value = 					
		"SELECT																	"+	
		"	  A.tt_id                                                            "+
		"	, A.snd_dt                                                            "+
		"	, A.snd_team                                                        "+
		"	, A.snd_nm                                                          "+
		"	, A.snd_cmp_no                                                          "+		
		"	, A.cust_nm					/*고객명*/                          "+
		"	, A.cust_tel_no		                          "+			
		"	, A.svc_main_cls_code		                          "+		
		"	, A.consult_id												"+
		"	, A.receive_yn                                             "+		
		"	, B.svc_main_cls_code_desc	/*상품명*/                              "+
		"	, B.troub_receipt_dt					/*접수일자*/                "+
		"	, B.reporter_opinion					/*접수의견*/                "+		
		"FROM tb_alarmtalk_snd A                                                "+
		"INNER JOIN tb_sa_tt_rcv B ON A.tt_id = B.tt_id "+				
		"WHERE 1=1                                                              " +		
		"AND A.snd_cmp_no = CAST(:sndCmpNo AS VARCHAR)  " +
		"AND ( CAST(:sndFromDt AS VARCHAR) IS NULL OR to_char(A.snd_dt, 'YYYY-MM-DD') between CAST(:sndFromDt AS VARCHAR) and CAST(:sndToDt AS VARCHAR) )"+			
		"AND ( CAST(:custName AS VARCHAR) IS NULL OR A.cust_nm LIKE CAST(:custName AS VARCHAR)||'%' )			/*고객명*/                                                     " +
		"AND ( CAST(:custTelNo AS VARCHAR) IS NULL OR A.cust_tel_no LIKE CAST(:custTelNo AS VARCHAR)||'%' )			/*고객 연락처*/                                                     " +		
		"ORDER BY A.snd_dt DESC				"
	, nativeQuery = true)
	public List <AlarmTalkSend> getAlarmTalkSndHistList(									
			@Param("sndCmpNo") String sndCmpNo,
			@Param("custName") String custName,
			@Param("custTelNo") String custTelNo,
			@Param("sndFromDt") String sndFromDt,
			@Param("sndToDt") String sndToDt); 
	
	public AlarmTalkSend findByConsultId(String consultId);
	
	public List<AlarmTalkSend> findAllByCustomerCustTelNoAndReceiveYNIsNull(String telNum);
	
	public List<AlarmTalkSend> findAllByReceiveYNIsNullAndAlarmTalkIdSndDtBefore(LocalDateTime time);
}
