package com.kt.csai.domain.alarmtalk.repository;
	
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSendInspct;
import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSendInspctId;
	
@Repository
public interface AlarmTalkRepositoryInspct extends JpaRepository<AlarmTalkSendInspct, AlarmTalkSendInspctId> {

		@Query(value =                                  
					" SELECT                                 												" +     	                
					"        A.tt_id,                    													" +
					"        A.user_nm,                    													" +
					"        A.tel_nm,                    													" + 
					"        TO_CHAR(TO_DATE(A.ispt_dt, 'YYYY-MM-DD'), 'MM월DD일') || '( ' || 		 		" +
					"        CASE TO_CHAR(TO_DATE(A.ispt_dt,'YYYY-MM-DD'), 'D')								" +
					"             WHEN '2' THEN '월'														" +
					"             WHEN '3' THEN '화'														" +
					"             WHEN '4' THEN '수'														" +
					"             WHEN '5' THEN '목'														" +
					"             WHEN '6' THEN '금'														" +
					"         END || ' )' AS ispt_dt											 			" +											
					"   FROM tb_alarmtalk_Inspct A           								 				" +					
					"  WHERE 1=1                             												" +
					"    AND ispt_dt = TO_CHAR((current_timestamp + interval '1 days'), 'YYYY-MM-DD')		" +
					"  ORDER BY A.ispt_dt DESC              												" +
					"  limit 5 																			"
	        , nativeQuery = true)
	    public List <AlarmTalkSendInspct> getSndInspct();

		@Query(value =                                  
						"SELECT                                 	" +     						
						"      A.tt_id,                    			" +
						"      A.user_nm,                    		" +
						"      A.tel_nm,                    		" + 
						"      A.ispt_dt                    		" +						
						"  FROM tb_alarmtalk_Inspct A           	" +             
						" WHERE 1=1                             	" +	
						"   AND (coment IS NULL OR coment = '') 	" +
						" ORDER BY A.ispt_dt	              		" +
						" limit 50                              	"
				, nativeQuery = true)
		public List<AlarmTalkSendInspct> getMemberList();

		@Modifying
		@Transactional
		@Query(value = 
					   "INSERT INTO tb_alarmtalk_Inspct (tt_id, tel_nm, user_nm ) VALUES " +
					   " ('1', '01091745242', '손용원'), " +
					   " ('2', '01050197691', '정대철'), " +
					   " ('3', '01086289574', '유연화'), " +
					   " ('4', '01071420012', '김수호'), " +
					   " ('5', '01068941206', '류기홍'), " +
					   " ('6', '01050206904', '김창일'), " +
					   " ('7', '01064794472', '배지영'), " +
					   " ('8', '01049500881', '전대웅'), " +
					   " ('9', '01034269005', '남재현'), " +
					   " ('10', '01022783752', '이상아'), " +
					   " ('11', '01021445113', '이예령'), " +
					   " ('12', '01092919981', '김혜진'), " +
					   " ('13', '01029774029', '강민구'), " +
					   " ('14', '01035944823', '강혜연'), " +
					   " ('15', '01090478659', '고은진'), " +
					   " ('16', '01045016266', '김해구'), " +
					   " ('17', '01068021484', '장용우'), " +
					   " ('18', '01091797986', '서제호'), " +
					   " ('19', '01064964582', '양세준'), " +
					   " ('20', '01049332352', '서가연'), " +
					   " ('21', '01073361009', '이명길'), " +
					   " ('22', '01030059115', '석미화'), " +
					   " ('23', '01031562306', '염수홍'), " +
					   " ('24', '01065482932', '한누리'), " +
					   " ('25', '01075722655', '김경진'), " +
					   " ('26', '01045924534', '하승현'), " +
					   " ('27', '01026566364', '구자훈'), " +
					   " ('28', '01065809860', '권가람'), " +
					   " ('29', '01095830777', '우신우'), " +
					   " ('30', '01054287481', '이승훈')  " 
			, nativeQuery = true)
		public void InsertMemberList();

		@Modifying
		@Transactional
		@Query(value =  " UPDATE tb_alarmtalk_Inspct																	" +
						" 	 SET ispt_dt = NULL																			" +
						"  WHERE ispt_dt is NOT NULL;																	" +
						" 																								" +
						" 																								" +
						" WITH RECURSIVE data_series AS (																" +
						" 	SELECT CAST(TO_DATE(:st_dt,'YYYY-MM-DD') as DATE)  work_dt									" +
						" 	UNION ALL																					" +
						" 	SELECT CAST(work_dt + interval '1 day' as DATE)												" +
						" 	  FROM data_series																			" +
						" 	 WHERE work_dt < TO_DATE(:ed_dt,'YYYY-MM-DD')												" +
						" )																								" +
						" SELECT work_dt																				" +
						"   INTO temp tmp_work_dates																	" +
						"   FROM data_series																			" +
						"  WHERE EXTRACT(dow FROM work_dt) NOT IN (0,6);												" +
						" 																								" +
						" 																								" +
						" UPDATE tb_alarmtalk_Inspct AS t 																" +
						"    SET ispt_dt = w.work_dt																	" +
						"   FROM (																						" +
						"   		SELECT user_nm, work_dt, row_number() OVER() as rm									" +
						"  		      FROM ( SELECT user_nm, work_order 												" +						
						" 				       FROM tb_alarmtalk_Inspct													" +
						"            		  WHERE (work_order is not null OR work_order != '')						" +
						"				   ) as ordered_users															" +
						"   		  JOIN ( SELECT work_dt, row_number() over() AS rm from tmp_work_dates) AS work_dt	" +
						" 				ON cast(ordered_users.work_order as bigint) = work_dt.rm 						" +
  						"			) as w 																				" +
  						"	where t.user_nm = w.user_nm ;																" +
						"																								" +
						"																								" +
						" drop table if exists tmp_work_dates;															"
			, nativeQuery = true)
		public void UpdateWorkDate(
			@Param("st_dt") String st_dt,
			@Param("ed_dt") String ed_dt);
		

		@Query(value = 
					   "SELECT " +
					   " TO_CHAR(TO_DATE(:date, 'YYYY-MM-DD'), 'MM') to_mon, 												" +
					   " TO_CHAR(TO_DATE(:date, 'YYYY-MM-DD'), 'YYYY-MM-DD') st_Dt,											" +
					   " TO_CHAR(TO_DATE(:date,'YYYY-MM-DD') +INTERVAL '1 MONTH' + INTERVAL '2 DAY', 'YYYY-MM-DD') ed_Dt 	"
			, nativeQuery = true)
		public List<Map<String,String>> checkMonthDate(
			@Param("date") String date );

			@Query(value = 
						"SELECT " +
						"      A.tt_id,                    																 				" +
						"      A.user_nm,                    															 				" +
						"      A.tel_nm,                    															 				" + 
						"      A.ispt_dt,																				 				" +											
						"      TO_CHAR(TO_DATE(A.ispt_dt,'YYYY-MM-DD'), 'MM') to_mon									 				" +											
	                	"  FROM tb_alarmtalk_Inspct A           														 				" +					
	                	" WHERE 1=1                             														 				" +
						"   AND ispt_dt is not null																						" +
						" order by ispt_dt 																								" +
	                	" limit 30                              														 				"
 			, nativeQuery = true)
			public List<Map<String,String>> checkWorkDate();	 
			        	    
	}