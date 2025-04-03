package com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;

public class PcpCustOtpAthnLoginProviderImplServiceClient extends WebServiceGatewaySupport {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static final String END_POINT = "http://%s/GPCP/PcpCsaiOtpAthnLogInProviderImplService"; 

	@SuppressWarnings("unchecked")
	public ReqCsaiOtpAthnLogInResponse requestOTPNumber(String osbUrl, ReqCsaiOtpAthnLogIn req) throws JAXBException {
		log.info("========== start PcpCustOtpAthnLoginProviderImplServiceClient.requestOTPNumber ==========");
		log.info("## ReqCsaiOtpAthnLogIn : {}", req.toString());
		
		String url = String.format(END_POINT, osbUrl);
		
		JAXBElement<ReqCsaiOtpAthnLogIn> je = new ObjectFactory().createReqCsaiOtpAthnLogIn(req);
		JAXBElement<ReqCsaiOtpAthnLogInResponse> element = (JAXBElement<ReqCsaiOtpAthnLogInResponse>) getWebServiceTemplate()
				.marshalSendAndReceive(url, je, new WebServiceMessageCallback() {
					
					@Override
					public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
						log.info("========== start WebServiceMessageCallback.doWithMessage ==========");
						
						SoapHeader soapHeader = ((SoapMessage) message).getSoapHeader();
						
						// header 생성
						ObjectFactory factory = new ObjectFactory();
						Arbitrary arbitrary = factory.createArbitrary();
						BITHeader bitHeader = factory.createBITHeader();
						
						// arbitrary
						Arbitrary.SearchKey searchKey = new Arbitrary.SearchKey();
						searchKey.setKey("iuserid");
						searchKey.setValue(req.getCsaiOtsysOntmPwdInputVo().getCsaiAthnInntNo());
						arbitrary.setSearchKey(new ArrayList<>());
						arbitrary.getSearchKey().add(searchKey);
						
						// BIT header
						String now = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
						bitHeader.setArbitrary(arbitrary);
						bitHeader.setGuid("GCSI_GPCP_OSB0001_" + now);
						bitHeader.setInterfaceID("GCSI_GPCP_OSB0001");
						bitHeader.setSourceSys("GCSI");
						bitHeader.setTargetSys("GPCP");
						bitHeader.setSeqNumber(0);
						
						try {
							
							JAXBContext context = JAXBContext.newInstance(BITHeader.class);
							context.createMarshaller().marshal(bitHeader, soapHeader.getResult());
							
							log.info("========== end WebServiceMessageCallback.doWithMessage ==========");
						}
						catch (Exception e) {
							log.info("========== error WebServiceMessageCallback.doWithMessage ==========");
							log.info("## Exception : {}", e);
						}
						
					}
					
				});
		
		log.info("========== end PcpCustOtpAthnLoginProviderImplServiceClient.requestOTPNumber ==========");
		return element.getValue();
	}
	
}

