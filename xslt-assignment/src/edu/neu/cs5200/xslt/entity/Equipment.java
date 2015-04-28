package edu.neu.cs5200.xslt.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.math.BigDecimal;


/**
 * The persistent class for the equipment database table.
 * 
 */
@Entity
@NamedQuery(name="Equipment.findAll", query="SELECT e FROM Equipment e")
@XmlRootElement 
@XmlAccessorType(value = XmlAccessType.FIELD) 
public class Equipment  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@XmlAttribute
	private String brand;
	@XmlAttribute
	private String description;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private BigDecimal price;
	@ManyToOne
	@JoinColumn(name="towerId")
	@XmlTransient
	private Tower tower;
	public Equipment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


}