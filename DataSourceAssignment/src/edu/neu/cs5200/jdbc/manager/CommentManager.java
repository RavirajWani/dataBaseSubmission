package edu.neu.cs5200.jdbc.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.jdbc.entity.Comment;

public class CommentManager {

	DataSource ds;
	
	public CommentManager(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/datasourceAssignment");
			System.out.println(ds);
		} catch (NamingException e){
			e.printStackTrace();
		}
	}
	
	private java.sql.Date commentDate(java.util.Date datOfBirth) {
		return (new java.sql.Date(datOfBirth.getTime()));
	}
	
	private void closeConnection (Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createComment(Comment newComment){
		Connection connection = null;
		String query = "Insert into comment values (null, ?, ? ,?, ?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newComment.getUserName());
			statement.setString(2, newComment.getMovieId());
			statement.setString(3, newComment.getComment());
			statement.setDate(4, commentDate(newComment.getDate()));
			statement.execute();	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
	public List<Comment> readAllComments(){
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = null;
		Connection connection = null;
		String query = "Select * from comment";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				comment = new Comment();
				comment.setCommentId(results.getString("id"));
				comment.setUserName(results.getString("userName"));
				comment.setMovieId(results.getString("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return comments;
	}
	
	public List<Comment> readAllCommentsForUsername(String username){
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = null;
		Connection connection = null;
		String query = "Select * from comment where userName = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				comment = new Comment();
				comment.setCommentId(results.getString("id"));
				comment.setUserName(results.getString("userName"));
				comment.setMovieId(results.getString("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		
		return comments;
	}
	
	public List<Comment> readAllCommentsForMovie(String movieId){
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = null;
		Connection connection = null;
		String query = "Select * from comment where userid = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				comment = new Comment();
				comment.setCommentId(results.getString("id"));
				comment.setUserName(results.getString("userName"));
				comment.setMovieId(results.getString("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return comments;
	}
	
	public Comment readCommentForId(String commentId){
		Comment comment = null;
		Connection connection = null;
		String query = "Select * from comment where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, commentId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				comment = new Comment();
				comment.setCommentId(results.getString("id"));
				comment.setUserName(results.getString("userName"));
				comment.setMovieId(results.getString("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return comment;
	}
	
	public void updateComment(String commentId, Comment newComment){
		Connection connection = null;
		String query = "Update comment set userName = ? , movieId = ?  , comment = ? , date = ? where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newComment.getUserName());
			statement.setString(2, newComment.getMovieId());
			statement.setString(3, newComment.getComment());
			statement.setDate(4, (commentDate(newComment.getDate())));
			statement.setString(5, commentId);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteComment(String commentId){
		Connection connection = null;
		String query = "DELETE from actor where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, commentId);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}	
	}
}
