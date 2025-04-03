package com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

	private final static QName _ReqCsaiOtpAthnLogIn_QNAME = new QName("http://www.kt.com/bit/oss/GCSI_GPCP_OSB0001", "reqCsaiOtpAthnLogIn");
	private final static QName _ReqCsaiOtpAthnLogInResponse_QNAME = new QName("http://www.kt.com/bit/oss/GCSI_GPCP_OSB0001", "reqCsaiOtpAthnLogInResponse");
	
	public ReqCsaiOtpAthnLogIn createReqCsaiOtpAthnLogIn() {
		return new ReqCsaiOtpAthnLogIn();
	}
	
	public Arbitrary createArbitrary() {
		return new Arbitrary();
	}
	
	public BITHeader createBITHeader() {
		return new BITHeader();
	}
	
	@XmlElementDecl(namespace = "http://www.kt.com/bit/oss/GCSI_GPCP_OSB0001", name = "reqCsaiOtpAthnLogIn")
	public JAXBElement<ReqCsaiOtpAthnLogIn> createReqCsaiOtpAthnLogIn(ReqCsaiOtpAthnLogIn value) {
		return new JAXBElement<ReqCsaiOtpAthnLogIn>(_ReqCsaiOtpAthnLogIn_QNAME, ReqCsaiOtpAthnLogIn.class, null, value);
	}
	
	@XmlElementDecl(namespace = "http://www.kt.com/bit/oss/GCSI_GPCP_OSB0001", name = "reqCsaiOtpAthnLogInResponse")
	public JAXBElement<ReqCsaiOtpAthnLogInResponse> createReqCsaiOtpAthnLogInResponse(ReqCsaiOtpAthnLogInResponse value) {
		return new JAXBElement<ReqCsaiOtpAthnLogInResponse>(_ReqCsaiOtpAthnLogInResponse_QNAME, ReqCsaiOtpAthnLogInResponse.class, null, value);
	}
	
}
