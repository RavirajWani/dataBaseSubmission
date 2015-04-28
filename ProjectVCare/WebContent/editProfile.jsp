<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="edu.neu.vcare.bean.*,edu.neu.vcare.controller.*,edu.neu.vcare.dao.*,edu.neu.vcare.entity.*, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="login.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Edit User Profile</title>

<script>
  $(function() {
    $( "#tabs" ).tabs({
    		heightStyle: "content"		
    });
  });
  
  
  $(document).ready(function(){
  $('#country').val($("#userCountry").val());
  $('#state').val($("#userState").val());
  });
  
  
  
  $(document).ready(function(){
		$("#country").change(function(){
			//alert("In change function of state. Country selected :" + $('#country').val())
			$.ajax({
	            url: "sigin.do",
	            type: 'POST',
	            data: {id : $('#country').val(), operation : "countryChange"},
	            success: function (data) {
	            	var states='<option=" ">--select state--</option>';                
	                $.each(data, function (i, item) {
	                	 states+='<option value="'+item.id+'">'+item.name+'</option>';
	                });
	                $('select#state').html(states);
	            },
	            error: function () {
	                alert('Cannot retrieve the data');  
	                console.log('Cannot retrieve the data');
	            }
	        });   
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
<body>

<div class="container">


<div class="row">
			<div class="col-md-6 col-md-offset-3"> 
				<div class="panel panel-login">
					<div class="panel-body">
					<span id="logoutDiv" style="font-size: 1.5em;">
<a href="home.jsp">Home</a> <a href="logout.jsp" style="float:right;">Logout</a>
</span>
						<div class="row">
						<center> <img src="http://www.logomaker.com/logo-images/050aea97fe6778cb.gif"/></center>
							<div class="col-lg-12">
<%
	UserBean currentUser = (UserBean) (session
			.getAttribute("currentSessionUser"));
	CountryDao cDao = new CountryDao();
	List<Country> countries = cDao.fetchCountryList();
	
	UserController controller = new UserController();
	UserBean userInfo = new UserBean();
	userInfo = controller.fetchUserDetails(currentUser.getId());
	
	StateDao sDao = new StateDao();
	List<State> states = sDao.fetchStateList((Integer)userInfo.getCountry());
	
%>

					<div id="sign-div" style="align:center"> 
	<form action="SigninServlet" method= "post" >
	<div class="form-group">
		Username<input type="text" name="un"  value =<%=userInfo.getUsername()%> placeholder="Enter Username" readonly="readonly" class="form-control"><br>
		</div>
		<div class="form-group"> Password <input type="password" name="pw" value =<%=userInfo.getPassword()%> placeholder="Enter Password" class="form-control"> </div>
		<div class="form-group"> First Name <input type="text" name="fn" value =<%=userInfo.getFirstName()%> placeholder="Enter First Name" class="form-control"/></div>
		<div class="form-group"> Last Name <input type="text" name="ln" value =<%=userInfo.getLastName()%> placeholder="Enter Last Name" class="form-control"/> </div>
		<div class="form-group"> Email Id<input type="text" name="em" value =<%=userInfo.getEmailId()%> placeholder="Enter Email" class="form-control"/></div>
		<input type="text" id="userState" value =<%=userInfo.getState()%> style="display:none">
		<input type="text" id="userCountry" value =<%=userInfo.getCountry()%> style="display:none">
		<input type="text" id="operation" value="editUserProfile" name="operation" style="display:none">
		<div class="form-group"> Country<select name="country" id="country" class="form-control">
                <option value="dropdown">-- select country--</option>
                
	<%
		for(Country c : countries)
		{
	%>
				<option value="<%= c.getId() %>"><%= c.getName()  %></option>
	<%			
		}
	%>
         	</select></div>
       <div class="form-group"> State <select id="state" name="state" class="form-control">            
   <%
		for(State s : states)
		{
	%>
				<option value="<%= s.getId() %>"><%= s.getName() %></option>
	<%			
		}
	%>                
            </select></div>
            <div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
	<input type="submit" value="submit" tabindex="4" class="form-control btn-login btn"></div></div></div>		 
	</form>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</div>
</body>
</html>