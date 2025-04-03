package com.kt.csai.domain.trouble.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.trouble.entity.HdmsDgrm;

@Repository
public interface HdmsDgrmRepository extends JpaRepository<HdmsDgrm, String> {
			
	/**
	 * 망구성도 조회	
	 * @param ttId
	 * @return
	 */ 
	public List<HdmsDgrm> findByTtidOrderBySeqAsc(String ttId);
	
	/**
	 * 망구성도 생성여부 조회
	 * @return
	 */
	@Query(value = 			
			"SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END AS create_yn FROM tb_hdms_dgrm WHERE ttid = :ttId" 			
	, nativeQuery = true)	                                                                                                                                        
	public String getCreateYn(@Param("ttId") String ttId);
	
	/**
	 * 망구성도 정보 저장	
	 * @param ttId
	 * @return
	 */ 
	@Modifying
	@Query(value = 				
			"INSERT INTO tb_hdms_dgrm																																	  "+
			"WITH RESOURCE AS 	/*Resource Data*/		                                                                                                                  "+
			"(                                                                                                                                                            "+
			"	SELECT TTID, TERML_IP, SPLIT_PART(TERML_IP, '(', 1) AS IP, REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP, TERML_TYPE	                          "+
			"		, ROW_NUMBER () OVER() AS SEQ1, SVC_TYPE, MODEM_MAC                                                                                                   "+                            
			"		, (SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE = 'H') AS TERML_TYPE_H_CNT			                                  "+                                                                                  
			"		, (SELECT COUNT(TERML_TYPE) FROM TB_HDMS_RESLT WHERE TTID = :ttId AND TERML_TYPE IN('S', 'G', 'GA')) AS TERML_TYPE_ETC_CNT                            "+                                                                                                 
			"		, NOW() AS CRET_DT, SCN				                                                                                                                  "+
			"	FROM TB_HDMS_RESLT                                                                                                                                        "+
			"	WHERE TTID = :ttId                                                                                                                                        "+
			"     AND SCN != ( select SCN																																  "+
			"                    from (select TTID, SCN, terml_type 																									  "+
	     	"		    	            	 ,SPLIT_PART(TERML_IP, '(', 1) AS IP																						  "+	
	     	"		          	   	    	 ,REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '') AS GW_IP																	  "+
			"                	    	 from TB_HDMS_RESLT																												  "+
	        "		         	   		where TTID = :ttId																												  "+
	        "		           	 	 	  and SPLIT_PART(TERML_IP, '(', 1) = REPLACE(SPLIT_PART(TERML_IP, '(', 2), ')' , '')											  "+
	        "       	   		 	  ) A																																  "+
	        "         	   		where 1 = 1																																  "+
	  		"           	 	  and terml_type = 'H'																												  	  "+  
	        "		   	   		fetch first 1 row only																													  "+
	        "           	 )																																			  "+
			"),                                                                                                                                                           "+
			"TMP AS		/* IP = GW_IP Row Data */																														  "+					
			"(                                                                                                                                                            "+
			"	SELECT AA.*                                                                                                                                               "+
			"		, CASE WHEN ( AA.TERML_TYPE_H_CNT >= 3 OR AA.TERML_TYPE_ETC_CNT >= 3 ) THEN 'N' ELSE 'Y' END AS DGRM_YN	                                              "+
			"		, ROW_NUMBER () OVER() AS SEQ, '' AS PARENT_SEQ, '' AS PARENT_SEQ1                                                                                    "+              
			"	FROM RESOURCE AS AA                                                                                                                                       "+             
			"	WHERE AA.IP = AA.GW_IP      	                                                                                                                          "+			
			"),                                                                                                                                                           "+
			"TMP2 AS 	/* IP != GW_IP Row Data */                                                                                                                        "+                           
			"(                                                                                                                                                            "+
			"	SELECT AA.*                                                                                                                                               "+
			"		, CASE WHEN ( AA.TERML_TYPE_H_CNT >= 3 OR AA.TERML_TYPE_ETC_CNT >= 3 ) THEN 'N' ELSE 'Y' END AS DGRM_YN		                                          "+
			"		, ROW_NUMBER () OVER() AS SEQ                                                                                                                         "+
			"		, (SELECT TMP.SEQ || '' FROM TMP WHERE TMP.IP = AA.GW_IP) AS PARENT_SEQ                                                                               "+
			"		, (SELECT ORI_RESOURCE.SEQ1 || '' FROM RESOURCE AS ORI_RESOURCE WHERE ORI_RESOURCE.IP = AA.GW_IP) AS PARENT_SEQ1 	/*사설장비 부모 시퀀스*/          	  "+
			"	FROM RESOURCE AS AA                                                                                                                                       "+             
			"	WHERE AA.IP != AA.GW_IP                                                                                                                                   "+
			"),                                                                                                                                                           "+
			"TMP3 AS	/*Modem Row Data */                                                                                                                               "+
			"(                                                                                                                                                            "+
			"	SELECT  TMP.TTID, '0' AS IP, TMP.GW_IP,                                                                                                                   "+
			"		CASE                                                                                                                                                  "+
			"			WHEN  SVC_TYPE = 'FTTH-R' THEN 'ONT'                                                                                                              "+ 
			"			WHEN  ( SVC_TYPE = 'GES' AND NULLIF(TRIM(MODEM_MAC),'') IS NOT NULL ) THEN 'UTP'                                                                  "+                
			"			WHEN  ( SVC_TYPE = 'GES' AND NULLIF(TRIM(MODEM_MAC),'') IS NULL ) THEN 'L2'                                                                       "+                
			"		ELSE 'MODEM' END AS TERML_TYPE, DGRM_YN, CASE WHEN DGRM_YN = 'N' THEN NULL ELSE '0' END AS SEQ, TMP.CRET_DT, TMP.SCN, 'Y' AS CREATE_YN                "+                                              
			"	FROM TMP WHERE TMP.SEQ = 1                                                                                                                                "+
			"	UNION ALL                                                                                                                                                 "+
			"	SELECT  TMP2.TTID, '0' AS IP, TMP2.GW_IP,                                                                                                                 "+
			"		CASE                                                                                                                                                  "+
			"			WHEN  SVC_TYPE = 'FTTH-R' THEN 'ONT'                                                                                                              "+ 
			"			WHEN  ( SVC_TYPE = 'GES' AND NULLIF(TRIM(MODEM_MAC),'') IS NOT NULL ) THEN 'UTP'                                                                  "+                
			"			WHEN  ( SVC_TYPE = 'GES' AND NULLIF(TRIM(MODEM_MAC),'') IS NULL ) THEN 'L2'                                                                       "+                
			"		ELSE 'MODEM' END AS TERML_TYPE, DGRM_YN, CASE WHEN DGRM_YN = 'N' THEN NULL ELSE '0' END AS SEQ, TMP2.CRET_DT, TMP2.SCN, 'Y' AS CREATE_YN              "+                                                
			"	FROM TMP2 WHERE TMP2.SEQ = 1                                                                                                                              "+
			"	LIMIT 1                                                                                                                                                   "+
			")                                                                                                                                                            "+
			"/*Total Row Data*/                                                                                                                                           "+
			"SELECT TMP3.TTID, TMP3.IP, TMP3.GW_IP, TMP3.TERML_TYPE, TMP3.DGRM_YN, TMP3.SEQ, TMP3.CRET_DT, TMP3.SCN, TMP3.CREATE_YN FROM TMP3                             "+
			"UNION ALL                                                                                                                                                    "+
			"SELECT T.TTID, T.IP, T.GW_IP, T.TERML_TYPE, T.DGRM_YN,                                                                                                       "+
			"	CASE WHEN T.DGRM_YN = 'N' THEN NULL                                                                                                                       "+
			"	ELSE                                                                                                                                                      "+		
			"		CASE                                                                                                                                                  "+
			"			/*1. 단독 사설장비*/                                                                                                                              	  "+
			"			WHEN ( T.IP != T.GW_IP AND NULLIF(TRIM(T.PARENT_SEQ),'') IS NULL AND NULLIF(TRIM(T.PARENT_SEQ1),'') IS NULL ) THEN 'E' || T.SEQ                   "+
			"			/*2. 엮인 사설장비*/                                                                                                                                  "+
			"			WHEN ( T.IP != T.GW_IP AND NULLIF(TRIM(T.PARENT_SEQ),'') IS NULL AND NULLIF(TRIM(T.PARENT_SEQ1),'') IS NOT NULL )                                 "+
			"				THEN 'E' || COALESCE(NULLIF(TRIM(T.PARENT_SEQ),''), T.PARENT_SEQ, T.PARENT_SEQ1) || T.SEQ-1                                                   "+
			"		ELSE T.PARENT_SEQ || T.SEQ END                                                                                                                        "+
			"	END AS SEQ, T.CRET_DT, T.SCN, 'Y' AS CREATE_YN                                                                                                            "+               
			"FROM                                                                                                                                                         "+
			"(                                                                                                                                                            "+
			"	SELECT TMP.* FROM TMP                                                                                                                                     "+
			"	UNION ALL                                                                                        		                                                  "+
			"	SELECT TMP2.* FROM TMP2     		                                                                                                                      "+
			") T                                                                                                                                                          "+
			"ORDER BY SEQ                                                                                                                                                 "						
	, nativeQuery = true)                                                                                                                       
	public int saveDgrm(@Param("ttId") String ttId);

}
