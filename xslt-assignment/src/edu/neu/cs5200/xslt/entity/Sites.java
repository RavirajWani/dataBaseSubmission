package edu.neu.cs5200.xslt.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
@XmlAccessorType(value = XmlAccessType.FIELD) 
public class Sites {
	@XmlElement(name="site")
	private List<Site> site;

	public List<Site> getSite() {
		return site;
	}

	public void setSite(List<Site> site) {
		this.site = site;
	}

	public Sites(List<Site> site) {
		super();
		this.site = site;
	}

	public Sites() {
		super();
	}
	
}
