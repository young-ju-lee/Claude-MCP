package com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0002;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

	private final static QName _ReqCsaiOtpAthnLogInValid_QNAME = new QName("http://www.kt.com/bit/oss/GCSI_GPCP_OSB0002", "reqCsaiOtpAthnLogInValid");
	private final static QName _ReqCsaiOtpAthnLogInValidResponse_QNAME = new QName("http://www.kt.com/bit/oss/GCSI_GPCP_OSB0002", "reqCsaiOtpAthnLogInValidResponse");
	
	public ReqCsaiOtpAthnLogInValid createReqCsaiOtpAthnLogInValid() {
		return new ReqCsaiOtpAthnLogInValid();
	}
	
	public Arbitrary createArbitrary() {
		return new Arbitrary();
	}
	
	public BITHeader createBITHeader() {
		return new BITHeader();
	}
	
	@XmlElementDecl(namespace = "http://www.kt.com/bit/oss/GCSI_GPCP_OSB0002", name = "reqCsaiOtpAthnLogInValid")
	public JAXBElement<ReqCsaiOtpAthnLogInValid> createReqCsaiOtpAthnLogInValid(ReqCsaiOtpAthnLogInValid value) {
		return new JAXBElement<ReqCsaiOtpAthnLogInValid>(_ReqCsaiOtpAthnLogInValid_QNAME, ReqCsaiOtpAthnLogInValid.class, null, value);
	}
	
	@XmlElementDecl(namespace = "http://www.kt.com/bit/oss/GCSI_GPCP_OSB0002", name = "reqCsaiOtpAthnLogInValidResponse")
	public JAXBElement<ReqCsaiOtpAthnLogInValidResponse> createReqCsaiOtpAthnLogInValidResponse(ReqCsaiOtpAthnLogInValidResponse value) {
		return new JAXBElement<ReqCsaiOtpAthnLogInValidResponse>(_ReqCsaiOtpAthnLogInValidResponse_QNAME, ReqCsaiOtpAthnLogInValidResponse.class, null, value);
	}
	
}