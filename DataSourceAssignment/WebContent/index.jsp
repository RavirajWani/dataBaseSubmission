<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.jdbc.manager.*,edu.neu.cs5200.jdbc.entity.*,java.util.Date,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> Hello World </h1>
<%
	
	UserManager userManager = new UserManager();
	List<User> users = new ArrayList<User>();
	users= userManager.readAllUsers();
	for(User u : users){
		out.println(u.getFirstName());
	}

	MovieManager movieManager = new MovieManager();
	List<Movie> movies = new ArrayList<Movie>();
	movies= movieManager.readAllMovie();
	for(Movie m : movies){
		out.println(m.getTitle());
	}
	
	ActorManager actorManager = new ActorManager();
	List<Actor> actors = new ArrayList<Actor>();
	actors= actorManager.readAllActors();
	for(Actor a : actors){
		out.println(a.getFirstName());
		out.println(a.getLastName());
	}
	
	CastManager castManager = new CastManager();
	List<Cast> cast = new ArrayList<Cast>();
	cast =  castManager.readAllCast();
	for(Cast c : cast){
		out.println(c.getCharacterName());
	}

	CommentManager commentManager = new CommentManager();
	List<Comment> comments = new ArrayList<Comment>();
	comments =  commentManager.readAllComments();
	for(Comment c : comments){
		out.println(c.getComment());
	}
%>
</body>
</html>