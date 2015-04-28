<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="edu.neu.vcare.bean.*,edu.neu.vcare.controller.*,edu.neu.vcare.dao.*,edu.neu.vcare.entity.*, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Home Page</title>

    <!-- Bootstrap -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<title>My Page | Home</title>

<script>
  $(function() {
	  $( "#tabs" ).tabs({
  		heightStyle: "content"		
  });
  });
  </script>
  
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  
</head>
<body style="background-image:url('images/login.jpg'); background-size:100%;">
<div class="container">
<div id="header">
<div id="logo" style="width:200px;margin:0;">
 <img src="http://www.logomaker.com/logo-images/050aea97fe6778cb.gif"/>
</div>
<div id="userInfo" style="float:left;width:100%;font: 2em;">
<span id="welcomeMsg" style="font-size: 1.5em;">
<%
	UserBean currentUser = (UserBean) (session
			.getAttribute("currentSessionUser"));
%>

Welcome, <%=currentUser.getFirstName() + " "
					+ currentUser.getLastName()%>

</span>

<span id="editDiv" style="font-size: 1.5em;">
<a href="editProfile.jsp">  | Edit Profile</a>
</span>

<span id="logoutDiv" style="float:right;font-size: 1.5em;">
<a href="logout.jsp">Logout</a>
</span>
</div>
</div>

<%
		BlogDC bdc = new BlogDC();
		List<Blogpost> blogs = bdc.fetchAllBlog(currentUser.getId());
		int count = blogs.size();
		
		List<Blogpost> userblogs = bdc.fetchUserBlog(currentUser.getId());
		int userBlogCount = userblogs.size();
		
		Parser p = new Parser();
		WebInitiative[] initiatives = p.fetchWebInitiative(); 
	%>
	


<br>
<br>
<div id="create">
	<input type="button" value="Create Blog" id="create_blog"
		onclick="showBlogForm()">
	
	<script type="text/javascript">
		function showBlogForm() {
			if($('#blogForm').css('display') == 'block')
				{
				$("#blogForm").css("display", "none")
				$("#create_blog").attr("value", "Create Blog");
			}else if($('#blogForm').css('display') == 'none'){
				$("#blogForm").css("display", "block")
				$("#create_blog").attr("value", "Hide Blog");
			}
			//$("#blogForm").css("display", "block");
			
			}
	</script>
<br>

</div>
<div id="blogForm" style="display: none">
<br>
<fieldset>
<legend>New Blog Post:</legend>
<form action="BlogpostServlet">
	<input type="text" name="title" id="title_id" placeholder="Blog Title" maxlength="250" size="100"> <br>
		<br>
	<textarea type="text" name="content" id="content-id" placeholder="Enter Blog Content" cols="100" rows="20"></textarea><br><br>
			 <input type="submit" value="submit">
	</form>

</fieldset>
	
	<br>
</div>
	
<br>
<div id="tabs">
  <ul>
    <li><a href="#display-blog">Blogs</a></li>
    <li><a href="#myBlogs">My Blogs</a></li>
    <li><a href="#initiatives">Initiatives</a></li>
  </ul>

<div id="display-blog" style="height:550px;overflow:auto;">
 <%if (count== 0) { %>

    <p> No blogs to display </p>
<%}%>
<% if(count > 0) {
	for (Blogpost b : blogs){ %>
<div style="margin-bottom:1 em;" id="blog<%= b.getId()%>">
<a href='ViewBlogDetails.do?id=<%= b.getId()%>&title=<%= b.getTitle()%>&content=<%= b.getContent()%>&date=<%= b.getDate()%>&like=<%= b.getFavourite()%>&userId=<%=b.getUserId()%>'>
<h4>Title :  <span id="title"><%= b.getTitle()%></span> </h4></a>
<h6>Date :  <span id="date"><%= b.getDate()%></span></h6>
<h6>Like :  <span id="like"><%= b.getFavourite()%></span></h6>
<h6><span id="userId" style="display:none"><%= b.getUserId()%>  </span></h6>
 <span id="content" style="display:none;"><%= b.getContent()%></span>
</div>
<% } }  %>
</div>
<div id="myBlogs" style="height:550px;overflow:auto;">

<% if(count > 0) {
	for (Blogpost b : userblogs){ %>
	<hr>
<div style="margin-bottom:1 em" id="blog<%= b.getId()%>" >
<a href='ViewBlogDetails.do?id=<%= b.getId()%>&title=<%= b.getTitle()%>&content=<%= b.getContent()%>&date=<%= b.getDate()%>&like=<%= b.getFavourite()%>&userId=<%= b.getUserId()%>'>
<h4>Title :  <span id="title"><%= b.getTitle()%></span> </h4></a>
<h6>Date :  <span id="date"><%= b.getDate()%></span></h6>
<h6>Like :  <span id="like"><%= b.getFavourite()%></span></h6>
<h6><span id="userId" style="display:none"><%= b.getUserId()%> </span></h6>
 <span id="content" style="display:none;"><%= b.getContent()%></span>
</div>
<% } }  %>
</div>
<div id="initiatives" style="height:550px;overflow:auto;">
<p>Initiatives Around The World</p>
<% if(count > 0) {
	for (WebInitiative b : initiatives){ %>
<div style="margin-bottom:1 em;" id="blog<%= b.getId()%>">
<a href='ViewInitiative.do?id=<%= b.getId()%>&title=<%= b.getTitle()%>&content=<%= b.getSummary()%>&imgurl=<%= b.getImageLink()%>'>
<h4>Title :  <span id="title"><%= b.getTitle()%></span> </h4></a>
<h6><img alt="" src="<%= b.getImageLink()%>"></h6>
 <span id="content" style="display:none;"><%= b.getSummary()%></span>
</div>
<% } }  %>
</div>

</div>
<hr>
<div id="footer"> <center><h4> All Rights Reserved | V Care | 2015 </h4></center></div>
</div>
</body>
</html>