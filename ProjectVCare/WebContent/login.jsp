<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Login</title>

    <!-- Bootstrap -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="login.css">
<!-- Optional theme -->


<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body style="background-image:url('images/login.jpg'); background-size:100%;">
<!-- <div class="container">
		<form action="LoginServlet">
			Please enter your username 		
			<input type="text" name="un"/><br>		
			Please enter your password
			<input type="text" name="pw"/>
			<input type="submit" value="submit">			
		</form>
		<a href="signin.jsp">SignUp</a>
		</div> -->
<div class="container" >

    	<div class="row">
			<div class="col-md-6 col-md-offset-3"> 
			
				<div class="panel panel-login">
					<div class="panel-body">
						<div class="row">
						<center> <img src="http://www.logomaker.com/logo-images/050aea97fe6778cb.gif"/></center>
							<div class="col-lg-12">
							
								<form id="login-form" action="LoginServlet" method="get" role="form" style="display: block;">
									<div class="form-group">
										<input type="text" name="un" id="username" tabindex="1" class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="password" name="pw" id="password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="signin.jsp" tabindex="5" class="forgot-password">Sign up</a>
												</div>
											</div>
										</div>
									</div>
								</form>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	</body>
	
	
	
</html>