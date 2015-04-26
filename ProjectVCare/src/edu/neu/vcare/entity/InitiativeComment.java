package edu.neu.vcare.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="comment_initiative")
public class InitiativeComment {
	@Id
	private int id;
	private int initiativeId;
	private String content;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInitiativeId() {
		return initiativeId;
	}
	public void setInitiativeId(int initiativeId) {
		this.initiativeId = initiativeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
