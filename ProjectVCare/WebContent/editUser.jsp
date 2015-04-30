<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.vcare.bean.*,edu.neu.vcare.controller.*,edu.neu.vcare.dao.*,edu.neu.vcare.entity.*, java.util.*"%>
<!DOCTYPE html >
<html>
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
<title>Admin Rights</title>
<script type="text/javascript">
function gtButtonDetails(elem){
	var id= $(elem).attr("id");
	 
	$.ajax({
	    url: "sigin.do?id="+id,
	    type : "delete",
		dataType: "json",
	    cache: 'false',
	    success: function (data) {
	        $('#userRow').html('');
	        $.each(data, function (i, item) {
	        	$('#userRow').append('<tr><td align="center">'+ item.username +'</td><td align="center">'+ item.firstName +'</td><td align="center">'+ item.lastName + '</td><td align="center">'+ item.blogCount +'</td><td align="center"><input type="button" id="user'+item.id+'" value="X" onclick="gtButtonDetails(this)" class="form-control btn-login btn" style="background:#59B2E0;color:white;width:50px;display:block;"></td></tr>');
	        });
	    },
	    error: function () {
	    	$('#state').html("<div id='errMsg'><p>No Comments to Display</p></div>");
	        console.log('Cannot retrieve the comments');
	    }
	}); 
}
</script>
</head>
<body>



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
	
	UserController controller = new UserController();
	List<UserBean> users = controller.fetchAllUser();
%>

Welcome, <%=currentUser.getFirstName() + " "
					+ currentUser.getLastName()%>

</span>

<span id="logoutDiv" style="float:right;font-size: 1.5em;">
<a href="home.jsp">Home</a> | <a href="logout.jsp">Logout</a>
</span>
</div>

<div align="center">
        <table id="userTable" border="1" cellpadding="5">
            <caption><h3>List of users</h3></caption>
            <thead>
            <tr>
                <td height="50" width="100" align="center"><b>Username</b></td>
                <td height="50" width="100" align="center"><b>FirstName</b></td>
                <td height="50" width="100" align="center"><b>LastName</b></td>
                <td height="50" width="100" align="center"><b>BlogCount</b></td>
                <td height="50" width="100" align="center"><b>Delete User</b></td>
            </tr>
            </thead>
            <tbody id="userRow">
           <% 
	for (UserBean u : users){ %>
                <tr>
                    <td align="center"><%= u.getUsername() %></td>
                    <td align="center"><%= u.getFirstName() %></td>
                    <td align="center"><%= u.getLastName() %></td>
                    <td align="center"><%= u.getBlogCount() %></td>
                    <td align="center"><input type="button" id="user<%= u.getId()%>" value="X" onclick="gtButtonDetails(this)" class="form-control btn-login btn" style="background:#59B2E0;color:white;width:50px;display:block;"></td>
                </tr>
           <% } %>
           </tbody>
        </table>
    </div> 
</div>
</div>
</body>
</html>