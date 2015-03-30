package edu.neu.cs5200.jdbc.entity;

import java.util.Date;

public class Comment {
	
	private String commentId;
	private String userName;
	private String movieId;
	private String Comment;
	private Date date;
	
	public Comment(){	
	}
	
	public Comment(String userId, String movieId, String comment, Date date){
		this.userName = userId;
		this.movieId = movieId;
		this.Comment = comment;
		this.date = date;
	}
	
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
