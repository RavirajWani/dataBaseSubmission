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

import edu.neu.cs5200.jdbc.entity.Movie;

public class MovieManager {
	
	DataSource ds;
	
	public MovieManager(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/datasourceAssignment");
			System.out.println(ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private void closeConnection (Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private java.sql.Date movieDate(java.util.Date datOfBirth) {
		return (new java.sql.Date(datOfBirth.getTime()));
	}
	
	public void createMovie(Movie newMovie) throws SQLException{
		Connection connection = null;
		String query = "Insert into movie values (null, ? ,?, ?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newMovie.getTitle());
			statement.setString(2, newMovie.getPosterImage());
			statement.setDate(3, movieDate(newMovie.getReleaseDate()));
			statement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
	public List<Movie> readAllMovie(){
		List<Movie> movies = new ArrayList<Movie>();
		Connection connection = null;
		String query = "Select * from movie";
		try {
			    connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet results = statement.executeQuery();
				while(results.next())
				{
					Movie movie = new Movie();
					movie.setId(results.getString("id"));
					movie.setTitle(results.getString("title"));
					movie.setPosterImage(results.getString("posterImage"));
					movie.setReleaseDate(results.getDate("releaseDate"));
					movies.add(movie);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return movies;
	}
	
	public Movie readMovie(String movieId){
		Connection connection = null;
		Movie movie = new Movie();
		String query = "Select * from movie where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				movie.setId(results.getString("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				movie.setReleaseDate(results.getDate("releaseDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return movie;
	}
	
	public void updateMovie(String movieId, Movie movie){
		Connection connection = null;
		String query = "Update movie set title = ? , posterImage = ?  , releaseDate = ? where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setDate(3, movieDate(movie.getReleaseDate()));
			statement.setString(4, movieId);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
	public void deleteMovie(String movieId){
		Connection connection = null;
		String query = "DELETE from movie where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, movieId);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
	
}
