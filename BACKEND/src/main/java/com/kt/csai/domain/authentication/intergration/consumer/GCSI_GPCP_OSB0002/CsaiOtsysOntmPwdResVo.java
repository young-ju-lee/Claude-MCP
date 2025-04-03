package com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0002;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CsaiOtsysOntmPwdResVo", propOrder = {
	"rsltCd",
	"rsltMsg"
})
@ToString
@Data
public class CsaiOtsysOntmPwdResVo {

	@XmlElement(name = "rsltCd")
	protected String rsltCd;		// 응답코드
	
	@XmlElement(name = "rsltMsg")
	protected String rsltMsg;		// 응답 메시지
	
}