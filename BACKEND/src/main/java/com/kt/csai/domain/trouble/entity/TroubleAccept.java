package com.kt.csai.domain.trouble.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_sa_tt_rcv")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TroubleAccept {
	
	@Id	
	@Column(name = "tt_id")
	private String ttId;	
	
	@Column(name = "action_type")
	private String actionType;			
	
	@Column(name = "as_cause_main_cls_code")
	private String asCauseMainClsCode;
	
	@Column(name = "as_cause_main_cls_code_desc")
	private String asCauseMainClsCodeDesc;
	
	@Column(name = "as_cause_sub_cls_code")
	private String asCauseSubClsCode;
	
	@Column(name = "as_cause_sub_cls_code_desc")
	private String asCauseSubClsCodeDesc;
	
	@Column(name = "as_equip_main_cls_code")
	private String asEquipMainClsCode;
	
	@Column(name = "as_equip_main_cls_code_desc")
	private String asEquipMainClsCodeDesc;
	
	@Column(name = "as_equip_sub_cls_code")
	private String asEquipSubClsCode;
	
	@Column(name = "as_equip_sub_cls_code_desc")
	private String asEquipSubClsCodeDesc;
	
	@Column(name = "as_fix_main_cls_code")
	private String asFixMainClsCode;
	
	@Column(name = "as_fix_main_cls_code_desc")
	private String asFixMainClsCodeDesc;
	
	@Column(name = "as_fix_sub_cls_code")
	private String asFixSubClsCode;
	
	@Column(name = "as_fix_sub_cls_code_desc")
	private String asFixSubClsCodeDesc;
	
	@Column(name = "cancel_yn")
	private String cancelYn;
	
	@Column(name = "cur_work_step")
	private String curWorkStep;
	
	@Column(name = "cur_work_step_desc")
	private String curWorkStepDesc;
	
	@Column(name = "cur_work_step_datetime")
	private String curWorkStepDatetime;
	
	@Column(name = "cust_name")
	private String custName;
	
	@Column(name = "first_rsv_wish_date")
	private String firstRsvWishDate;
	
	@Column(name = "first_rsv_wish_time")
	private String firstRsvWishTime;
	
	@Column(name = "lscn")
	private String lscn;
	
	@Column(name = "offices_code")
	private String officesCode;
	
	@Column(name = "offices_code_desc")
	private String officesCodeDesc;
	
	@Column(name = "outdoor_type_code")
	private String outdoorTypeCode;
	
	@Column(name = "outdoor_type_code_desc")
	private String outdoorTypeCodeDesc;
	
	@CreationTimestamp
	@Column(name = "rcv_date")
	private LocalDateTime rcvDate;
	
	@Column(name = "repair_code")
	private String repairCode;
	
	@Column(name = "repair_code_desc")
	private String repairCodeDesc;
	
	@Column(name = "report_type_code")
	private String reportTypeCode;
	
	@Column(name = "report_type_code_desc")
	private String reportTypeCodeDesc;
	
	@Column(name = "reporter_opinion")
	private String reporterOpinion;
	
	@Column(name = "reporter_tel_num1")
	private String reporterTelNum1;
	
	@Column(name = "reporter_tel_num2")
	private String reporterTelNum2;
	
	@Column(name = "rsv_dt")
	private String rsvDt;
	
	@Column(name = "rsv_time")
	private String rsvTime;
	
	@Column(name = "scn")
	private String scn;
	
	@Column(name = "svc_id")
	private String svcId;
	
	@Column(name = "svc_main_cls_code")
	private String svcMainClsCode;
	
	@Column(name = "svc_main_cls_code_desc")
	private String svcMainClsCodeDesc;
	
	@Column(name = "svc_main_type_code")
	private String svcMainTypeCode;
	
	@Column(name = "svc_main_type_code_desc")
	private String svcMainTypeCodeDesc;
	
	@Column(name = "troub_cause")
	private String troubCause;
	
	@Column(name = "troub_cause_desc")
	private String troubCauseDesc;
	
	@Column(name = "troub_facility")
	private String troubFacility;
	
	@Column(name = "troub_facility_desc")
	private String troubFacilityDesc;
	
	@Column(name = "troub_receipt_dt")
	private String troubReceiptDt;
	
	@Column(name = "troub_receipt_type_code")
	private String troubReceiptTypeCode;
	
	@Column(name = "troub_receipt_type_code_desc")
	private String troubReceiptTypecodeDesc;
	
	@Column(name = "work_content")
	private String workContent;
		
	@Column(name = "work_finish_datetime")
	private String workFinishDatetime;
	
}
