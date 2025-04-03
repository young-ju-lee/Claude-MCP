package com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CsaiOtsysOntmPwdResVo", propOrder = {
	"rsltCd",
	"rsltMsg",
	"ossAthnInntNo"
})
@ToString
@Data
public class CsaiOtsysOntmPwdResVo {

	@XmlElement(name = "rsltCd")
	protected String rsltCd;		// 응답코드
	
	@XmlElement(name = "rsltMsg")
	protected String rsltMsg;		// 응답 메시지
	
	@XmlElement(name = "ossAthnInntNo")
	protected String ossAthnInntNo;	// OSS인증고유번호
	
}
