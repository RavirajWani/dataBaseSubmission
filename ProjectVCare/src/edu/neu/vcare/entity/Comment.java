package edu.neu.vcare.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="comment_blog")
public class Comment {
@Id
private int id;
private int blogId;
private int userId;
private String content;
@Temporal(TemporalType.DATE)
private Date date;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getBlogId() {
	return blogId;
}
public void setBlogId(int blogId) {
	this.blogId = blogId;
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
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
}
