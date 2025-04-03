package com.kt.csai.domain.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.test.entity.TestHdmsResultHist;

@Repository
public interface TestHdmsResultRepository extends JpaRepository<TestHdmsResultHist, String> {
	
	/* < Resource Data >
	 * WITH RESOURCE AS 		                                                                                                        
		(                                                                                                                                                  
			SELECT TTID, TERML_IP, SPLIT_PART(TERML_IP, '(', 1) AS IP, REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE                
				  , ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                                              
				  , (SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT
				  , (SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT
				  , NOW() AS CRET_DT, SCN				                                                                                                        
	          FROM TB_HDMS_RESLT                                                                                                                              
			 WHERE TTID = :ttId                                                                                                                              
		),
	*/
	
	
	/**
	 * 망구성도 정보 조회1	
	 * IP = GW_IP Row Data	
	 * 
	 * @param ttId, tableNm
	 * @return
	 */ 
	@Query(value = 				
			" SELECT AA.*																										 						" +
			"		, CASE WHEN ( AA.TERML_TYPE_H_CNT >= 3 OR AA.TERML_TYPE_ETC_CNT >= 3 ) THEN 'N' ELSE 'Y' END AS DGRM_YN 							" +
			"	  	, ROW_NUMBER () OVER() AS SEQ, '' AS PARENT_SEQ, '' AS PARENT_SEQ1                    												" +
			"  FROM (SELECT TTID, TERML_IP																												" +
			"              ,SPLIT_PART(TERML_IP, '(', 1) AS IP																							" +
			"              ,REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE														" +
			"			   ,ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                           												" +
			"			   ,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT					" +
			"			   ,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT	" +
			"	  		   ,NOW() AS CRET_DT, SCN																										" +
			"	  	   FROM TB_HDMS_RESLT																												" +
			"	      WHERE TTID = :ttId																												" +
			"	   	 ) AS AA																															" +
			" WHERE AA.IP = AA.GW_IP																													" +
			" LIMIT 20 																																	"

	, nativeQuery = true)                                                                                                                       
	public String chkDgrm1(@Param("ttId") String ttId); 
	
	
	
	/**
	 * 망구성도 정보 조회2	
	 * IP != GW_IP Row Data	
	 * 
	 * @param ttId, tableNm
	 * @return
	 */ 
	@Query(value = 				
			"SELECT AA.*																																				" +
			"	   ,CASE WHEN ( AA.TERML_TYPE_H_CNT >= 3 OR AA.TERML_TYPE_ETC_CNT >= 3 ) THEN 'N' ELSE 'Y' END AS DGRM_YN												" +
			"	   ,ROW_NUMBER () OVER() AS SEQ																															" +
			"	   ,(SELECT TMP.SEQ || '' 																																" +
			"		   FROM ( SELECT AA.*																																" +
			"			   			,CASE WHEN ( AA.TERML_TYPE_H_CNT >= 3 OR AA.TERML_TYPE_ETC_CNT >= 3 ) THEN 'N' ELSE 'Y' END AS DGRM_YN 							 	" +
			"	  		   			,ROW_NUMBER () OVER() AS SEQ, '' AS PARENT_SEQ, '' AS PARENT_SEQ1                    												" +
			"  					FROM (SELECT TTID, TERML_IP																												" +
			"              					,SPLIT_PART(TERML_IP, '(', 1) AS IP																							" +
			" 					            ,REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE														" +
			"			   					,ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                           												" +
			"			   					,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT					" +
			"			   					,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT	" +
			"	  		   					,NOW() AS CRET_DT, SCN																										" +
			"	  	   					FROM TB_HDMS_RESLT																												" +
			"	      				   WHERE TTID = :ttId																												" +
			"	   	 				 ) AS AA																															" +
			" 				   WHERE AA.IP = AA.GW_IP																													" +
			" 				   LIMIT 10 																																" +
			"				) AS TMP WHERE TMP.IP = AA.GW_IP) AS PARENT_SEQ														   										" +
			"	   ,(SELECT ORI_RESOURCE.SEQ1 || '' 																													" +
			"          FROM ( SELECT TTID, TERML_IP																														" +
			"              			,SPLIT_PART(TERML_IP, '(', 1) AS IP																									" +
			"              			,REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE																" +
			"			   			,ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                           														" +
			"			   			,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT							" +
			"			   			,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT			" +
			"	  		   			,NOW() AS CRET_DT, SCN																												" +
			"	  	   			FROM TB_HDMS_RESLT																														" +
			"	     		   WHERE TTID = :ttId																														" +
			"               ) AS ORI_RESOURCE WHERE ORI_RESOURCE.IP = AA.GW_IP) AS PARENT_SEQ1 	/*사설장비 부모 시퀀스*/														" +
			"  FROM (SELECT TTID, TERML_IP																																" +
			"              ,SPLIT_PART(TERML_IP, '(', 1) AS IP																											" +
			"              ,REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE																		" +
			"			   ,ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                           																" +
			"			   ,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT									" +
			"			   ,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT					" +
			"	  		   ,NOW() AS CRET_DT, SCN																														" +
			"	  	   FROM TB_HDMS_RESLT																																" +
			"	      WHERE TTID = :ttId																																" +
			"       ) AS AA 																																			" +
			" WHERE AA.IP != AA.GW_IP 																																	" +
			" LIMIT 20  																																				"
	, nativeQuery = true)                                                                                                                       
	public String chkDgrm2(@Param("ttId") String ttId);  
	
	
	
	/**
	 * 망구성도 정보 조회3	
	 * Modem Row Data
	 * 
	 * @param ttId, tableNm
	 * @return
	 */ 
	@Query(value = 				
			"SELECT TMP.TTID, '0' AS IP, TMP.GW_IP,																																" +
			"		CASE WHEN  SVC_TYPE = 'FTTH-R' THEN 'ONT'																													" +
			"			 WHEN  ( SVC_TYPE = 'GES' AND NULLIF(TRIM(MODEM_MAC),'') IS NOT NULL ) THEN 'UTP'																		" +
			"			 WHEN  ( SVC_TYPE = 'GES' AND NULLIF(TRIM(MODEM_MAC),'') IS NULL ) THEN 'L2'																			" +
			" 			 ELSE 'MODEM' END AS TERML_TYPE, DGRM_YN, CASE WHEN DGRM_YN = 'N' THEN NULL ELSE '0' END AS SEQ, TMP.CRET_DT, TMP.SCN, 'Y' AS CREATE_YN					" +
			"  FROM ( SELECT AA.*																										 										" +
			"			 	, CASE WHEN ( AA.TERML_TYPE_H_CNT >= 3 OR AA.TERML_TYPE_ETC_CNT >= 3 ) THEN 'N' ELSE 'Y' END AS DGRM_YN 											" +
			"	  			, ROW_NUMBER () OVER() AS SEQ, '' AS PARENT_SEQ, '' AS PARENT_SEQ1                    																" +
			"  			FROM (SELECT TTID, TERML_IP																																" +
			"              			,SPLIT_PART(TERML_IP, '(', 1) AS IP																											" +
			"              			,REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE																		" +
			"			   			,ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                           																" +
			"			   			,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT									" +
			"			   			,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT					" +
			"	  		   			,NOW() AS CRET_DT, SCN																														" +
			"	  	   			FROM TB_HDMS_RESLT																																" +
			"	      		   WHERE TTID = :ttId																																" +
			"	   	 		 ) AS AA																																			" +
			" 		   WHERE AA.IP = AA.GW_IP																																	" +
			" 		   LIMIT 10 																																				" +
			"		) AS TMP 																																					" +
			" WHERE TMP.SEQ = 1																																					" +
			"UNION  ALL																																							" +
			"SELECT TMP2.TTID, '0' AS IP, TMP2.GW_IP,																															" +
			"		CASE WHEN  SVC_TYPE = 'FTTH-R' THEN 'ONT'																													" +
			"			 WHEN  ( SVC_TYPE = 'GES' AND NULLIF(TRIM(MODEM_MAC),'') IS NOT NULL ) THEN 'UTP'																		" +
			"			 WHEN  ( SVC_TYPE = 'GES' AND NULLIF(TRIM(MODEM_MAC),'') IS NULL ) THEN 'L2'																			" +
			"			 ELSE 'MODEM' END AS TERML_TYPE, DGRM_YN, CASE WHEN DGRM_YN = 'N' THEN NULL ELSE '0' END AS SEQ, TMP2.CRET_DT, TMP2.SCN, 'Y' AS CREATE_YN				" +
			"  FROM ( SELECT AA.*																																				" +
			"	   			,CASE WHEN ( AA.TERML_TYPE_H_CNT >= 3 OR AA.TERML_TYPE_ETC_CNT >= 3 ) THEN 'N' ELSE 'Y' END AS DGRM_YN												" +
			"	   			,ROW_NUMBER () OVER() AS SEQ																														" +
			"	   			,(SELECT TMP.SEQ || '' 																																" +
			"		   			FROM ( SELECT AA.*																																" +
			"			   					 ,CASE WHEN ( AA.TERML_TYPE_H_CNT >= 3 OR AA.TERML_TYPE_ETC_CNT >= 3 ) THEN 'N' ELSE 'Y' END AS DGRM_YN 							" +
			"	  		   					 ,ROW_NUMBER () OVER() AS SEQ, '' AS PARENT_SEQ, '' AS PARENT_SEQ1                    												" +
			"  							 FROM (SELECT TTID, TERML_IP																											" +
			"              							 ,SPLIT_PART(TERML_IP, '(', 1) AS IP																						" +
			" 					            		 ,REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE														" +
			"			   							 ,ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                           												" +
			"			   							 ,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT					" +
			"			   							 ,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT	" +
			"	  		   							 ,NOW() AS CRET_DT, SCN																										" +
			"	  	   							 FROM TB_HDMS_RESLT																												" +
			"	      				   			WHERE TTID = :ttId																												" +
			"	   	 				 		  ) AS AA																															" +
			" 				   			WHERE AA.IP = AA.GW_IP																													" +
			" 				   			LIMIT 10 																																" +
			"						 ) AS TMP WHERE TMP.IP = AA.GW_IP) AS PARENT_SEQ														   									" +
			"	   			,(SELECT ORI_RESOURCE.SEQ1 || '' 																													" +
			"          			FROM ( SELECT TTID, TERML_IP																													" +
			"              					 ,SPLIT_PART(TERML_IP, '(', 1) AS IP																								" +
			"              					 ,REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE																" +
			"			   					 ,ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                           														" +
			"			   					 ,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT							" +
			"			   					 ,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT			" +
			"	  		   					 ,NOW() AS CRET_DT, SCN																												" +
			"	  	   					 FROM TB_HDMS_RESLT																														" +
			"	     		   			WHERE TTID = :ttId																														" +
			"               		 ) AS ORI_RESOURCE WHERE ORI_RESOURCE.IP = AA.GW_IP) AS PARENT_SEQ1 	/*사설장비 부모 시퀀스*/													" +
			"  			FROM (SELECT TTID, TERML_IP																																" +
			"              			,SPLIT_PART(TERML_IP, '(', 1) AS IP																											" +
			"              			,REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE																		" +
			"			   			,ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                           																" +
			"			   			,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT									" +
			"			   			,(SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT					" +
			"	  		   			,NOW() AS CRET_DT, SCN																														" +
			"	  	   			FROM TB_HDMS_RESLT																																" +
			"	       		   WHERE TTID = :ttId																																" +
			"       		 ) AS AA 																																			" +
			" 		  WHERE AA.IP != AA.GW_IP 																																	" +
			" 		  LIMIT 20  																																				" +
			"       )AS TMP2 WHERE TMP2.SEQ = 1																																	" +
			" LIMIT 20																																							"
	, nativeQuery = true)                                                                                                                       
	public String chkDgrm3(@Param("ttId") String ttId);  

}
