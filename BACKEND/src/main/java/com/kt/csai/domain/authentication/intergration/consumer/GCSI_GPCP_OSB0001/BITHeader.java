package com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "interfaceID", "sourceSys", "targetSys", "guid", "seqNumber", "returnCode",
		"returnMSG", "arbitrary" })
@XmlRootElement(name = "BITHeader", namespace = "http://www.kt.com/bit/standard/header/v1")
@Data
@ToString
public class BITHeader {

	@XmlElement(name = "InterfaceID", namespace = "http://www.kt.com/bit/standard/header/v1", required = true)
	protected String interfaceID;
	@XmlElement(name = "SourceSys", namespace = "http://www.kt.com/bit/standard/header/v1", required = true)
	protected String sourceSys;
	@XmlElement(name = "TargetSys", namespace = "http://www.kt.com/bit/standard/header/v1", required = true)
	protected String targetSys;
	@XmlElement(name = "GUID", namespace = "http://www.kt.com/bit/standard/header/v1", required = true)
	protected String guid;
	@XmlElement(name = "SeqNumber", namespace = "http://www.kt.com/bit/standard/header/v1", required = true)
	protected int seqNumber;
	@XmlElement(name = "ReturnCode", namespace = "http://www.kt.com/bit/standard/header/v1", required = true)
	protected String returnCode;
	@XmlElement(name = "ReturnMSG", namespace = "http://www.kt.com/bit/standard/header/v1", required = true)
	protected String returnMSG;
	@XmlElement(name = "Arbitrary", namespace = "http://www.kt.com/bit/standard/header/v1", required = true)
	protected Arbitrary arbitrary;

}
