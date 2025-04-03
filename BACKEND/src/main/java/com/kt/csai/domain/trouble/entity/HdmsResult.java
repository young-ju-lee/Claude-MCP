package com.kt.csai.domain.trouble.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_hdms_reslt")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(HdmsResultId.class)
public class HdmsResult {

	@Id
	@Column(name = "rcvdt", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date rcvDt;
	
	@Id
	@Column(name = "scn", length = 20, nullable = false)
	private String scn;
	
	@Id
	@Column(name = "ttid", length = 16, nullable = false)
	private String ttId;
	
	@Column(name = "lscn", length = 20, nullable = false)
	private String lscn;
	
	@Column(name = "terml_type", length = 2)
	private String termlType;
	
	@Column(name = "dag_reslt", length = 10)
	private String dagReslt;
	
	@Column(name = "inet_tie", length = 50)
	private String inetTie;
	
	@Column(name = "profile", length = 30)
	private String profile;
	
	@Column(name = "mac_rstrtn_num", length = 10)
	private String macRstrtnNum;
	
	@Column(name = "port_sttus", length = 50)
	private String portSttus;
	
	@Column(name = "host_num", length = 10)
	private String hostNum;
	
	@Column(name = "updown_speed", length = 20)
	private String updownSpeed;
	
	@Column(name = "crc_dag_reslt", length = 30)
	private String crcDagReslt;
	
	@Column(name = "traffic_dag_reslt", length = 50)
	private String trafficDagReslt;
	
	@Column(name = "olt_ont_opti_dag_reslt", length = 50)
	private String oltOntOptiDagReslt;
	
	@Column(name = "equip_alarm_dag_reslt", length = 10)
	private String equipAlarmDagReslt;
	
	@Column(name = "equip_syslog_dag_reslt", length = 10)
	private String equipSyslogDagReslt;
	
	@Column(name = "svc_sttus_dag_reslt", length = 10)
	private String svcSttusDagReslt;
	
	@Column(name = "cpu_dag_reslt", length = 50)
	private String cpuDagReslt;
	
	@Column(name = "wips_dag_reslt", length = 10)
	private String wipsDagReslt;
	
	@Column(name = "wless_dag_reslt", length = 10)
	private String wlessDagReslt;
	
	@Column(name = "low_terml_cnct_sttus_dag_reslt", length = 50)
	private String lowTermlCnctSttusDagReslt;
	
	@Column(name = "tv_cnct_dag_reslt", length = 100)
	private String tvCnctDagReslt;
	
	@Column(name = "wless_qat_dag_reslt", length = 10)
	private String wlessQatDagReslt;
	
	@Column(name = "ap_updown_speed", length = 20)
	private String apUpdownSpeed;
	
	@Column(name = "ap_ping_reslt", length = 20)
	private String apPingReslt;
	
	@Column(name = "ap_homehub_link_speed", length = 20)
	private String apHomehubLinkSpeed;
	
	@Column(name = "ap_reslt_dt", length = 20)
	private String apResltDt;					// String?
	
	@Column(name = "dag_type", length = 20)
	private String dagType;
	
	@Column(name = "dag_dt", length = 20)
	private String dagDt;						// String?
	
	@Column(name = "inet_prod_nm", length = 50)
	private String inetProdNm;
	
	@Column(name = "svc_type", length = 10)
	private String svcType;
	
	@Column(name = "ip_mode", length = 20)
	private String ipMode;
	
	@Column(name = "mac_address", length = 20)
	private String macAddress;
	
	@Column(name = "model_nm", length = 50)
	private String modelNm;
	
	@Column(name = "terml_ip", length = 50)
	private String termlIp;
	
	@Column(name = "up_terml_ip", length = 50)
	private String upTermlIp;
	
	@Column(name = "up_terml_model", length = 50)
	private String upTermlModel;
	
	@Column(name = "ont_mac", length = 20)
	private String ontMac;
	
	@Column(name = "ont_model", length = 50)
	private String ontModel;
	
	@Column(name = "l2_terml_ip", length = 50)
	private String l2TermlIp;
	
	@Column(name = "modem_mac", length = 20)
	private String modemMac;
	
	@Column(name = "modem_model", length = 50)
	private String modemModel;
	
}
