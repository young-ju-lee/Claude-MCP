package com.kt.csai.domain.trouble.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kt.csai.domain.trouble.dto.HdmsResultInterface;
import com.kt.csai.domain.trouble.entity.HdmsResult;

public interface HdmsResultRepository extends JpaRepository<HdmsResult, Long> {
			
	@Query(value = 

//			
//			"SELECT DISTINCT TTID																								"+
//			"FROM TB_HDMS_RESLT                                                                                                 "+
//			"WHERE ttid NOT IN                                                                                                  "+
//			"(                                                                                                                  "+
//			"	SELECT DISTINCT T.TTID                                                                                          "+
//			"	FROM                                                                                                            "+
//			"	(                                                                                                               "+
//			"		SELECT TTID, COUNT(*) OVER(PARTITION BY TTID, SPLIT_PART(TERML_IP, '(', 1)) AS CNT                          "+
//			"		FROM TB_HDMS_RESLT	                                                                                        "+
//			"	) T	                                                                                                            "+
//			"	WHERE T.CNT > 1                                                                                                 "+
//			")                                                                                                                  "+
//			"AND ttid NOT IN (SELECT DISTINCT TTID FROM TB_HDMS_DGRM WHERE CREATE_YN = 'Y')                                     "+
//			"ORDER BY TTID                                                                                                      "	
			"SELECT DISTINCT TTID " +
			"FROM TB_HDMS_RESLT " +
			"WHERE ttid NOT IN (SELECT DISTINCT TTID FROM TB_HDMS_DGRM WHERE CREATE_YN = 'Y') " +
			"ORDER BY TTID  "
	, nativeQuery = true)	                                                                                                                                        
	public List <HdmsResultInterface> getTtidList();
	
	/**
	 * 망구성도 팝업 조회
	 * @param ttId
	 * @param scn
	 * @return
	 */
	public HdmsResultInterface findByTtIdAndScn(String ttId, String scn);	
	
}
