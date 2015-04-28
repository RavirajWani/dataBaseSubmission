package edu.neu.cs5200.xslt.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement 
@XmlAccessorType(value = XmlAccessType.FIELD) 
public class Site {
	@Id
	@XmlAttribute
	Integer id;
	@XmlAttribute
	String name;
	@XmlAttribute
	String latitude;
	@XmlAttribute
	String longitude;
	@OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="tower")
	private List<Tower> towers;
	public List<Tower> getTowers() {
		return towers;
	}
	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Site(Integer id, String name, String latitude, String longitude) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Site() {
		super();
	}
	

}
