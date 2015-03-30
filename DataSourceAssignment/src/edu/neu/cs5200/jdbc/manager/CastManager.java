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

import edu.neu.cs5200.jdbc.entity.Cast;

public class CastManager {

	DataSource ds;
	
	public CastManager(){
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
	
	public void createCast(Cast newCast){
		Connection connection = null;
		String query = "Insert into cast values (null , ?, ? ,?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newCast.getMovieId());
			statement.setString(2, newCast.getActorId());
			statement.setString(3, newCast.getCharacterName());
			statement.execute();	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
	public List<Cast> readAllCast(){
		List<Cast> casts = new ArrayList<Cast>();
		Cast cast = null;
		Connection connection = null;
		String query = "Select * from cast";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				cast = new Cast();
				cast.setCastId(results.getString("id"));
				cast.setMovieId(results.getString("movieId"));
				cast.setActorId(results.getString("actorId"));
				cast.setCharacterName(results.getString("characterName"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return casts;
	}
	
	public List<Cast> readAllCastForActor(String actorId){
		List<Cast> casts = new ArrayList<Cast>();
		Cast cast = null;
		Connection connection = null;
		String query = "Select * from cast where actorid = ";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, actorId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				cast = new Cast();
				cast.setCastId(results.getString("id"));
				cast.setMovieId(results.getString("movieId"));
				cast.setActorId(results.getString("actorId"));
				cast.setCharacterName(results.getString("characterName"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return casts;
	}
	
	public List<Cast> readAllCastForMovie(String movieId){
		List<Cast> casts = new ArrayList<Cast>();
		Cast cast = null;
		Connection connection = null;
		String query = "Select * from cast where movieId = ";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				cast = new Cast();
				cast.setCastId(results.getString("id"));
				cast.setMovieId(results.getString("movieId"));
				cast.setActorId(results.getString("actorId"));
				cast.setCharacterName(results.getString("characterName"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return casts;
	}
	
	public Cast readCast(String castId){
		Cast cast = null;
		Connection connection = null;
		String query = "Select * from cast where castId = ";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, castId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				cast = new Cast();
				cast.setCastId(results.getString("id"));
				cast.setMovieId(results.getString("movieId"));
				cast.setActorId(results.getString("actorId"));
				cast.setCharacterName(results.getString("characterName"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return cast;
	}
	
	public void updateCast(String castId, Cast cast){
		Connection connection = null;
		String query = "Update cast set actorId = ? , movieId = ?  , characterName = ? where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cast.getActorId());
			statement.setString(2, cast.getMovieId());
			statement.setString(3, cast.getCharacterName());
			statement.setString(4, castId);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
	public void deleteCast(String castId){
		Connection connection = null;
		String query = "DELETE from cast where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, castId);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
}
