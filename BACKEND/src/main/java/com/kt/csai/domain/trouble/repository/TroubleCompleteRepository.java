package com.kt.csai.domain.trouble.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.trouble.entity.TroubleComplete;

@Repository
public interface TroubleCompleteRepository extends JpaRepository<TroubleComplete, String> {
			
	/**
	 * 1개월내 고장이력	
	 * @param ttId
	 * @return
	 */
	//TODO : 접수일자 조회조건은 추후 필요 
	@Query(value = 
			"SELECT *										" +															
			"FROM tb_sa_tt_cmplt                    " +
			"WHERE                                      " +
			"lscn IN (                                  " +
			"	SELECT                                  " +
			"		lscn                                " +
			"	FROM                                    " +
			"		tb_sa_tt_cmplt                      " +
			"	WHERE                                   " +
			"		tt_id = :ttId                       " +
			//"	AND troub_receipt_dt between TO_CHAR(current_timestamp + '-1 MONTHS', 'YYYY-MM-DD HH:MI:SS') and TO_CHAR(current_timestamp, 'YYYY-MM-DD HH:MI:SS')" +
			")                                          " +
			"ORDER BY troub_receipt_dt DESC             "	
	, nativeQuery = true)
	public List<TroubleComplete> getTroubleHistList(@Param("ttId") String ttId);
	
	
	/**
	 * 고장이력 상세
	 * @param ttId
	 * @param lscn
	 * @return
	 */
	public TroubleComplete findByTtIdAndLscn (String ttId, String lscn);	
	
}
