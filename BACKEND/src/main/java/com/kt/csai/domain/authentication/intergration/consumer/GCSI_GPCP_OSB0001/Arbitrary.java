package com.kt.csai.domain.authentication.intergration.consumer.GCSI_GPCP_OSB0001;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "searchKey" })
@XmlRootElement(name = "Arbitrary", namespace = "http://www.kt.com/bit/standard/header/v1")
public class Arbitrary {

	@XmlElement(name = "SearchKey", namespace = "http://www.kt.com/bit/standard/header/v1")
	protected List<Arbitrary.SearchKey> searchKey;

	public List<Arbitrary.SearchKey> getSearchKey() {
		if (searchKey == null) {
			searchKey = new ArrayList<Arbitrary.SearchKey>();
		}
		return this.searchKey;
	}

	public void setSearchKey(List<Arbitrary.SearchKey> searchKey) {
		this.searchKey = searchKey;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "value" })

	@Data
	public static class SearchKey {

		@XmlValue
		protected String value;
		@XmlAttribute(name = "key")
		protected String key;

	}
}