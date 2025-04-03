package com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0002;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reqCsaiOtpAthnLogInValid", propOrder = {
	"csaiOtsysOntmPwdInputVo"
})
@ToString
@Data
public class ReqCsaiOtpAthnLogInValid {

	@XmlElement(name = "CsaiOtsysOntmPwdInputVo")
	protected CsaiOtsysOntmPwdInputVo csaiOtsysOntmPwdInputVo;
	
}
