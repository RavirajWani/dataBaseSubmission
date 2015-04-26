<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.vcare.bean.*,edu.neu.vcare.controller.*,edu.neu.vcare.dao.*,edu.neu.vcare.entity.*, java.util.*"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>View Initiative</title>

    <!-- Bootstrap -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->


<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body style="background-image:url('images/login.jpg'); background-size:100%;">
<div class="container" >
<div id="header">
<div id="logo" style="width:200px;margin:0;">
 <img src="http://www.logomaker.com/logo-images/050aea97fe6778cb.gif"/>
</div>
<div id="userInfo" style="float:left;width:100%;font: 2em;">
<span id="welcomeMsg" style="font-size: 1.5em;">
<%
	UserBean currentUser = (UserBean) (session
			.getAttribute("currentSessionUser"));
	int userId =currentUser.getId();
	String user= (String.valueOf(userId));
%>

Welcome, <%=currentUser.getFirstName() + " "
					+ currentUser.getLastName()%>

</span>
<span id="logoutDiv" style="float:right;font-size: 1.5em;">
<a href="home.jsp">Home</a> | <a href="logout.jsp">Logout</a>
</span>
</div>
</div>
<hr>
<div id="<%= session.getAttribute("id")%>" class="Blog" style="height: 500px">
<H1><%= session.getAttribute("title")%></H1>
<h3>
<span id = "blogContent"><%= session.getAttribute("content")%></span> 
</h3>
<h3><img alt="" src="<%= session.getAttribute("imgURL")%>"></h3>
</div>
<div id="footer" style="position: relative;bottom:0;"> <center><h4> All Rights Reserved | V Care | 2015 </h4></center></div>
</div>
</body>
</html>