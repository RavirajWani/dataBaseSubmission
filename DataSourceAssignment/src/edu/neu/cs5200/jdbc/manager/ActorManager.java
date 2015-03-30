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

import edu.neu.cs5200.jdbc.entity.Actor;

public class ActorManager {

	DataSource ds;
	
	public ActorManager(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/datasourceAssignment");
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
	
	private java.sql.Date actorDate(java.util.Date datOfBirth) {
		return (new java.sql.Date(datOfBirth.getTime()));
	}

	
	public void createActor(Actor newActor){
		Connection connection = null;
		String query = "Insert into actor values (null , ? ,?, ?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newActor.getFirstName());
			statement.setString(2, newActor.getLastName());
			statement.setDate(3, actorDate(newActor.getDateOfBirth()));
			statement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
	public List<Actor> readAllActors(){
		List<Actor> actors = new ArrayList<Actor>();
		Connection connection = null;
		String query = "Select * from actor";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Actor actor = new Actor();
				actor.setId(results.getString("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("lastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
				actors.add(actor);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return actors;
	}
	
	public Actor readActor(String actorId){
		Actor actor = null;
		Connection connection = null;
		String query = "Select * from actor where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, actorId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				actor = new Actor();
				actor.setId(results.getString("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("lastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return actor;
	}
	
	public void updateActor(String actorId, Actor actor){
		Connection connection = null;
		String query = "Update actor set firstName = ? , lastName = ?  , dateOfBirth = ? where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actorDate (actor.getDateOfBirth()));
			statement.setString(4, actorId);
			statement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
	public void deleteActor(String actorId){
		Connection connection = null;
		String query = "Delete from actor where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, actorId);
			statement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
}
