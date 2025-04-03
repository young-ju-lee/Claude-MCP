package com.kt.csai.domain.trouble.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.trouble.dto.NonMoveTgtInterface;
import com.kt.csai.domain.trouble.dto.OfficeCodeInterface;
import com.kt.csai.domain.trouble.entity.TroubleAccept;


@Repository
public interface TroubleAcceptRepository extends JpaRepository<TroubleAccept, String> {
		
	/**
	 * AI 무출동 대상조회
	 * @param svcMainClsCode
	 * @param custName
	 * @param svcId
	 * @param troubReceiptDt
	 * @param pageable
	 * @return
	 */
	@Query(value = 
			"SELECT A.tt_id AS ttId, A.cust_name AS custName, A.svc_main_type_code_desc AS svcMainTypeCodeDesc  											" +
			", A.svc_id AS svcId, A.reporter_tel_num1 AS reporterTelNum1, A.reporter_tel_num2 AS reporterTelNum2 											"+
			", A.first_rsv_wish_date AS firstRsvWishDate, A.first_rsv_wish_time AS firstRsvWishTime, A.reporter_opinion reporterOpinion 				"+
			", A.svc_main_cls_code AS svcMainClsCode, A.offices_code_desc AS officesCodeDesc 				"+
			", A.troub_receipt_dt AS troubReceiptDt, C.cnvrst_sttus AS cnvrstSttus																																										"+																															
			", CASE " +
			"		WHEN ( RSV.status = 'R' AND C.go_yn is null) THEN '예약' 									" +
			"		WHEN ( C.cnvrst_sttus = 'A' OR C.cnvrst_sttus = 'W' ) THEN '조치중'                                                                                                             "+
			"       WHEN ( C.go_yn = 'Y' ) THEN '출동'  "  +
			"       WHEN ( C.go_yn = 'N' ) THEN '무출동'  "  +
			"		WHEN C.cnvrst_sttus = 'D' THEN '완료'                                                                                                                                           "+
			"		WHEN ( C.cnvrst_sttus = 'S' OR NULLIF(TRIM(C.cnvrst_sttus),'') IS NULL ) THEN '미완료' " +
			"		ELSE '' END AS cnvrstSttusTxt                                                           "+
			", A.rsv_dt AS rsvDt 																																"+		
			", A.rsv_time AS rsvTime " + 
			/*
			 * tb_reservation 추가에 따른 코드 start
			 */
			", RSV.rsv_time AS reservationTime " +
			/*
			 * tb_reservation 추가에 따른 코드 end
			 */
			"FROM  tb_sa_tt_rcv A																													" +
			"INNER JOIN TB_AI_RESLT B		" +
			"ON A.tt_id  = B.tt_id                                                                                                                  " +			
			/*
			 * tb_reservation 추가에 따른 코드 start
			 */
			"LEFT OUTER JOIN tb_reservation RSV                    " +
			"ON A.tt_id = RSV.tt_id AND RSV.status = 'R'       " +
			/*
			 * tb_reservation 추가에 따른 코드 end
			 */
			"LEFT OUTER JOIN tb_consult C																		"+																										
			"ON A.tt_id = C.tt_id AND C.id = (SELECT max(D.id) FROM tb_consult D WHERE D.tt_id = A.tt_id)       "+
			"WHERE 1=1                                                                                                                              " +
			"AND A.troub_receipt_dt  != '?'			/*물음표값 제외*/                                                                               " +						
			"AND B.go_yn = 'F'		/*현장출동 구분코드 : (T : 출동 / F : 무촐동) */							" +						
			"AND ( (:svcMainClsCode) IS NULL OR A.svc_main_cls_code IN ( :svcMainClsCode ) )		/*상품 (3000 : 인터넷 / IPM0:TV)*/    			  "+
			"AND ( CAST(:custName AS VARCHAR) IS NULL OR A.cust_name LIKE CAST(:custName AS VARCHAR)||'%' )			/*고객명*/                      "+
			"AND ( CAST(:svcId AS VARCHAR) IS NULL OR A.svc_id LIKE CAST(:svcId AS VARCHAR)||'%' )						/*접속번호*/                 "+								
			"AND CASE WHEN (:officesCode) IS NULL THEN A.offices_code IN (SELECT officescode FROM tb_role_fm WHERE user_id = :userId)			   "+
			"ELSE A.offices_code IN (:officesCode) END                                                                                             "+
			"AND ( CAST(:ttId AS VARCHAR) IS NULL OR A.tt_id = CAST(:ttId AS VARCHAR) )						                                       "+			
			//"AND CASE WHEN (:ttId) IS NULL THEN A.rsv_dt = :rsvDt ELSE 1=1 END																   "+			
			"AND CASE WHEN (:ttId) IS NULL THEN A.rsv_dt BETWEEN :rsvFromDt	AND :rsvToDt ELSE 1=1 END											   "+
			"AND CASE WHEN (:ttId) IS NULL THEN                                                                                                    "+
			"A.tt_id NOT IN (                                                                                                                      "+
			"	SELECT tt_id																													   "+
			"	FROM tb_sa_tt_cmplt                                                                                                                "+
			//"	WHERE rsv_dt BETWEEN to_char(to_date(:rsvDt, 'YYYYMMDD') - INTERVAL '2 days', 'YYYYMMDD')                                          "+
			//"                  AND to_char(to_date(:rsvDt, 'YYYYMMDD'), 'YYYYMMDD')	                                                           "+
			"	WHERE rsv_dt BETWEEN :rsvFromDt	AND :rsvToDt                                                                          			   "+			
			") ELSE 1=1 END                                                                                                                        "+						
			"ORDER BY A.troub_receipt_dt DESC	"
	, nativeQuery = true)		
	public List <NonMoveTgtInterface> getNonMoveTgtList(			
			@Param("svcMainClsCode") List<String> svcMainClsCodeArray,
			@Param("custName") String custName,
			@Param("svcId") String svcId,						
			@Param("userId") String userId,
			@Param("officesCode") List<String> officesCodeArray,
			@Param("ttId") String ttId,
			//@Param("rsvDt") String rsvDt,
			@Param("rsvFromDt") String rsvFromDt,
			@Param("rsvToDt") String rsvToDt
			);	
	
	/**
	 * 국사정보 조회
	 * @return
	 */
	@Query(value = 
			"SELECT																			"+	
			"	 officescode AS officesCode                                                 "+
			"	, officesname AS officesCodeDesc	                                        "+
			"FROM tb_role_fm WHERE user_id = :userId ORDER BY officesname		"			
	, nativeQuery = true)	
	public List <OfficeCodeInterface> getOfficeCodeList(@Param("userId") String userId);
	
}
