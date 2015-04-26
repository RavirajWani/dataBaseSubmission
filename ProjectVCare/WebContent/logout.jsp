<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="1;URL=login.jsp">
<title>Logout Page</title>

 <!-- Bootstrap -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="login.css">
<!-- Optional theme -->


<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript">
    window.onload=body_load;
 
    </script>
</head>
<body style="background-image:url('images/login.jpg'); background-size:100%;">
<div class="container" >

    	<div class="row">
<% session.removeAttribute("currentSessionUser"); %>

<h2>Logging Out.....You will be redirected to login.</h2>

<%-- <% response.sendRedirect("login.jsp");
        %> --%>
        </div>
        </div>
</body>
</html>