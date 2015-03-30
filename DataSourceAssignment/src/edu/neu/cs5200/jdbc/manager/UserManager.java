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

import edu.neu.cs5200.jdbc.entity.User;

public class UserManager {
	
	DataSource ds;
	
	public UserManager(){
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
	
	private java.sql.Date userdate(java.util.Date datOfBirth) {
		return (new java.sql.Date(datOfBirth.getTime()));
	}
	
	public void createUser(User newUser){
		Connection connection = null;
		String query = "Insert into user values (?, ? ,?, ?,?,?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newUser.getUsername());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getFirstName());
			statement.setString(4, newUser.getLastName());
			statement.setString(5, newUser.getEmail());
			statement.setDate(6, (userdate(newUser.getDatOfBirth())));
			statement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}
	
	public List<User> readAllUsers(){
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		String query = "Select * from user";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				User user = new User();
				user.setUsername(results.getString("userName"));
				user.setPassword(results.getString("password"));
				user.setFirstName(results.getString("firstName"));
				user.setLastName(results.getString("lastName"));
				user.setEmail(results.getString("email"));
				user.setDatOfBirth(results.getDate("dateOfBirth"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return users;
		
	}
	
	public User readUser(String userName){
		User user = new User();
		Connection connection = null;
		String query = "Select * from user where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				user.setUsername(results.getString("userName"));
				user.setPassword(results.getString("password"));
				user.setFirstName(results.getString("firstName"));
				user.setLastName(results.getString("lastName"));
				user.setEmail(results.getString("email"));
				user.setDatOfBirth(results.getDate("dateOfBirth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		return user;
	}
	
	public void updateUser(String userName, User user){
		Connection connection = null;
		String query = "Update user set password = ?  , firstName = ? , lastName = ? , email = ? , dateOfBirth = ?  where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setDate(4, userdate(user.getDatOfBirth()));
			statement.setString(4, user.getEmail());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String userName){
		Connection connection = null;
		String query = "DELETE from user where id = ?";
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
	}

}
