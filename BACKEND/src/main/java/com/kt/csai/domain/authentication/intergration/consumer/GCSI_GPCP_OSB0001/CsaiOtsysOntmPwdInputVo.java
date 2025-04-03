package com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kt.csai.domain.authentication.payload.RequestSigninVo;

import lombok.Data;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CsaiOtsysOntmPwdInputVo", propOrder = {
	"csaiAthnInntNo",
	"userMphonNo",
	"otpDspTypeId",
	"mblSvcTypeCd",
	"handsetType",
	"handsetId"
})
@ToString
@Data
public class CsaiOtsysOntmPwdInputVo {
	
	@XmlElement(name = "csaiAthnInntNo")
	protected String csaiAthnInntNo;	// csai인증고유번호
	
	@XmlElement(name = "userMphonNo")
	protected String userMphonNo;		// 핸드폰번호
	
	@XmlElement(name = "otpDspTypeId")
	protected String otpDspTypeId;		// OTP발송유형아이디
	
	@XmlElement(name = "mblSvcTypeCd")
	protected String mblSvcTypeCd;		// 모바일서비스유형코드
	
	@XmlElement(name = "handsetType")
	protected String handsetType;		// 사용단말유형
	
	@XmlElement(name = "handsetId")
	protected String handsetId;			// 기기고유번호
}
