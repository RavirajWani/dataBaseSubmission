package edu.neu.cs5200.jws.entity;

import javax.persistence.*;


/**
 * The persistent class for the tower database table.
 * 
 */
@Entity
@NamedQuery(name="Tower.findAll", query="SELECT t FROM Tower t")
public class Tower  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String height;

	private String name;

	private String side;

	private int siteId;

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

	public String getSide() {
		return this.side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public int getSiteId() {
		return this.siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

}