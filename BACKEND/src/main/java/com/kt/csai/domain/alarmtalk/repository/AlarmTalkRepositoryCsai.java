package com.kt.csai.domain.alarmtalk.repository;
	
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
	
import com.kt.csai.domain.alarmtalk.entity.AlarmTalkId;
import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSendCsai;
	
@Repository
public interface AlarmTalkRepositoryCsai extends JpaRepository<AlarmTalkSendCsai, AlarmTalkId> {

		@Query(value =                                  
	                "SELECT                                 				" +     
	                "      A.step_execution_id,             				" + 
	                "      A.version,                       				" + 
	                "      A.setp_name,                     				" + 
	                "      A.job_execution_id,              				" +
	                "      A.status,                        				" + 
	                "      A.exit_code,                     				" + 
	                "      A.exit_message,                 					" + 
	                "  FROM tb_interface_failed A           				" +             
	                " WHERE 1=1                             				" +
	                "   and TO_CHAR(start_time, 'YYYY-MM-DD') = :startDt 	" +
	                " ORDER BY A.work_dt DESC               				" +
	                " limit 50                             					 "
	        , nativeQuery = true)
	        public List <AlarmTalkSendCsai> getCsaiErrorList(                                                                  
	                        @Param("startDt") String startDt ); 
	        
	     @Query(value =                                        
	                "SELECT count(*) AS cnt  " +
	                "  FROM tb_interface_failed			   " +
	                " WHERE 1 = 1 						   " +
	                " limit 1                              "
	        , nativeQuery = true)
	        public String findByCnt();
	     
	    /* tb_link_failed_hist 테이블 삭제  */
	    void deleteByexitCd(String exitCd);
	     
	}