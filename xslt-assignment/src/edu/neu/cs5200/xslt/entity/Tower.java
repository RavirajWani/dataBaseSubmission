package edu.neu.cs5200.xslt.entity;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the tower database table.
 * 
 */
@Entity
@NamedQuery(name="Tower.findAll", query="SELECT t FROM Tower t")
@XmlRootElement 
@XmlAccessorType(value = XmlAccessType.FIELD) 
public class Tower  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@XmlAttribute
	private String height;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String sides;
	@OneToMany(mappedBy="tower", cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="equipment")
	private List<Equipment> equipments;
	@ManyToOne
	@JoinColumn(name="siteId")
	@XmlTransient
	private Site site;
	public Tower() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSides() {
		return this.sides;
	}

	public void setSides(String side) {
		this.sides = side;
	}


	public Tower(String height, String name, String sides,
			List<Equipment> equipments, Site site) {
		super();
		this.height = height;
		this.name = name;
		this.sides = sides;
		this.equipments = equipments;
		this.site = site;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}