package edu.neu.vcare.bean;

import java.util.Date;
import java.util.List;

import edu.neu.vcare.entity.Comment;

public class BlogBean {
	
	private int id;
	private int userId;
	private String title;
	private String content;
	private Date datePosted;
	private int favourite;
	private boolean like;
	
	public int getFavourite() {
		return favourite;
	}
	public void setFavourite(int favourite) {
		this.favourite = favourite;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	private List<Comment> blogcomments;
	
	public List<Comment> getBlogcomments() {
		return blogcomments;
	}
	public void setBlogcomments(List<Comment> blogcomments) {
		this.blogcomments = blogcomments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	

}
