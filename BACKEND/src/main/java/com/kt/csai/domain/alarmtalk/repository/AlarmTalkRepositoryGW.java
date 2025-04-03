package com.kt.csai.domain.alarmtalk.repository;
	
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSendGW;
import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSendGWId;
	
@Repository
public interface AlarmTalkRepositoryGW extends JpaRepository<AlarmTalkSendGW, AlarmTalkSendGWId> {

		@Query(value =                                  
	                "SELECT DISTINCT                        	" +
	                "      A.folder_nm,           		       	" + 
	                "      A.workflow_nm,         		       	" + 	                
					"      A.err_cd  	                     	" +
	                "  FROM tb_gw_log A                     	" +             
	                " WHERE 1=1                             	" +
	                "   and work_dt= :workDt                	" +
					" ORDER BY workflow_nm                   	" +
	                " limit 50                              	"
	        , nativeQuery = true)
	        public List<AlarmTalkSendGW> getGwErrorList(
	                        @Param("workDt") String workDt ); 


	    /*@Query(value =                                        
	                "SELECT count(*) AS cnt  " +
	                "  FROM tb_gw_log 					   " +
	                " WHERE 1 = 1 						   " +
	                " limit 1                               "
	        , nativeQuery = true)
	        public String findByCnt();
	    */
    
	}