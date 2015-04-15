package edu.neu.cs5200.jws.entity;


import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the equipment database table.
 * 
 */
@Entity
@NamedQuery(name="Equipment.findAll", query="SELECT e FROM Equipment e")
public class Equipment  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String brand;

	private String description;

	private String name;

	private BigDecimal price;

	private int towerId;

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

	public int getTowerId() {
		return this.towerId;
	}

	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}

}