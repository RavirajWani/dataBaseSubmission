<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="edu.neu.vcare.bean.*,edu.neu.vcare.controller.*,edu.neu.vcare.dao.*,edu.neu.vcare.entity.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
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
<title>Sign up</title>

<script type="text/javascript">

$(document).ready(function(){
	$("#country").change(function(){
		//alert("In change function of state. Country selected :" + $('#country').val())
		$.ajax({
            url: "sigin.do",
            type: 'POST',
            data: {id : $('#country').val(), operation : "countryChange"},
            success: function (data) {
            	var states='<option=" ">-- select state --</option>';           
            	
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

<script>
function checkEmail(){
	//alert("In check Email");
    var email = $("#email").val();
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

    if (!filter.test(email)) {
    alert('Please provide a valid email address');
    $("#email").val('');
    $("#email").focus();
    //return false;
    }
}


function checkPassword()
{
	$("#unameMsg").css('display','none');
	var pass = $("#password").val();
  // at least one number, one lowercase and one uppercase letter
  // at least six characters that are letters, numbers or the underscore
  var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
  if (!re.test(pass)) {
	    alert('Password must have at least one number, one lowercase and one uppercase letter and at least six charaters');
	    $("#password").val('');
	    $("#password").focus();
	    //return false;
	    }
  //return re.test(pass);
}

function checkUname(){
	//alert("In check uname");
    var uname = $("#uname").val();

    $.ajax({
        url: "sigin.do",
        type: 'POST',
        data: {operation : "checkUserName", uname : uname},
        success: function (data) {
        	$("#unameMsg").html(data);
        	$("#unameMsg").css('display','block');
        	 $("#uname").focus();
        },
        error: function () {
        	$("#unameMsg").val('Username cannot be checked');
            alert('Cannot retrieve the data');  
        }
    });   
    

    //return false;
    }


 
</script>

</head>
<body>
<div class="container" >

    	<div class="row">
			<div class="col-md-6 col-md-offset-3"> 
			
				<div class="panel panel-login">
					<div class="panel-body">
						<div class="row">
						<center> <img src="http://www.logomaker.com/logo-images/050aea97fe6778cb.gif"/></center>
							<div class="col-lg-12">
	<%
		CountryDao cDao = new CountryDao();
		List<Country> countries = cDao.fetchCountryList();
	%>
	<div id="sign-div" style="align:center"> 
	
	<form action="SigninServlet">
		<div class="form-group">
		<input type="text" name="un" placeholder="Enter Username" class="form-control" id="uname" onchange="checkUname()"/>
		</div>
		 <div id="unameMsg" style="display:none;">
		</div>
		<div class="form-group"> <input type="password" name="pw" placeholder="Enter Password" class="form-control" id="password" onblur="checkPassword()"/> </div>
		<div class="form-group"> <input type="text" name="fn" placeholder="Enter First Name" class="form-control"/></div>
		<div class="form-group"> <input type="text" name="ln" placeholder="Enter Last Name" class="form-control"/> </div>
		<div class="form-group"> <input type="text" name="em" placeholder="Enter Email" class="form-control" id="email" onblur="checkEmail()"/></div>
		<div class="form-group"><select name="country" id="country" class="form-control">
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
           <div class="form-group"> <select id="state" name="state" class="form-control">
                   <option>-- select state--</option>
                 </select></div>
                <div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
		 <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn-login btn" value="Sign up" ></div></div></div>
		
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