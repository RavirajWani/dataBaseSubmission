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
    <title>View Blog</title>

    <!-- Bootstrap -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->


<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript">


$(document).ready(function(){
    $("#submitComment").click(function(){
    	/* alert("in submit button of add comment"); */
    	if($('#commentBoxText').val()==null || $('#commentBoxText').val()==""){
    		alert("Enter a comment");
    		$('#commentBoxText').focus();
    		$('#commentBoxText').style("background-color","pink");
    	}
    	else{
    		$.ajax({
    		    url: "ViewBlogDetails.do",
    		    type: 'POST',
    		    data: {id : $('.Blog').attr("id"), content: $("#commentBoxText").val()},
    		    success: function (data) {
    		    	$('#comments').html('');
    		    	$('#commentBoxText').val('');
    		        $.each(data, function (i, item) {
    		        	$('#comments').append('	<hr><div id="newComment'+item.id+'" style="opacity: 0.8;background:white;font-weight: bold"><p>'+item.content+'</p><p>'+item.date+'</p><p>'+item.userName+'</p><input type="button" id="comment'+item.id+'" value="X" onclick="gtButtonDetails(this)" class="form-control btn-login btn" style="background:#59B2E0;color:white;width:50px;display:block;"></div>');
    		        });
    		    },
    		    error: function () {
    		    	$('#state').html("<div id='errMsg'><p>No Comments to Display</p></div>");
    		        console.log('Cannot retrieve the comments');
    		    }
    		});
    	}
    });
});


function gtButtonDetails(elem){
	var id= $(elem).attr("id");
	/* alert("Dails:::::"+ id+"vxcvxcv " +$('.Blog').attr("id")) */
	$.ajax({
	    url: "ViewBlogDetails.do?id="+id+"&bid="+$('.Blog').attr("id"),
	    type : "delete",
		//contentType: "application/json; charset=utf-8",
		dataType: "json",
	    cache: 'false',
	   // data: {cid : id, bid: $('.Blog').attr("id"), content: $("#commentBoxText").val()},
	    success: function (data) {
	    	$('#comments').html('');
	    	$('#commentBoxText').val('');
	        $.each(data, function (i, item) {
	        	$('#comments').append('	<hr><div id="newComment'+item.id+'" style="opacity: 0.8;background:white;font-weight: bold"><p>'+item.content+'</p><p>'+item.date+'</p><p>'+item.userName+'</p><input type="button" id="comment'+item.id+'" value="X" onclick="gtButtonDetails(this)"class="form-control btn-login btn" style="background:#59B2E0;color:white;width:50px;display:block;"> </div>');
	        });
	    },
	    error: function () {
	    	$('#state').html("<div id='errMsg'><p>No Comments to Display</p></div>");
	        console.log('Cannot retrieve the comments');
	    }
	}); 
}
   
   
function editBlogContent(){
	if($('#editBlogContent').css('display') == 'block'){
		$("#commentBoxContent").css("display", "block")
		$("#submitBlogContent").css("display", "block")
		$('#editBlogContent').css("display",'none')
		$('#blogContent').css("display",'none')
	}
}

function submitBlog(){
	$('#operation').val('submit');
	$('#like').val('edit');
	$('#blogUpdateForm').submit();
}

function likeBlog(){
	var likeCount=$("#favouriteVal").html();
	$('#operation').val('submit');
	$('#like').val('like');
	$("#favourite").val(likeCount);
	$('#blogUpdateForm').submit();
}

function deleteBlogContent(){
	$('#operation').val('delete');
	$('#blogUpdateForm').submit();
}


</script>
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
<div id="<%= session.getAttribute("id")%>" class="Blog">
<H1><%= session.getAttribute("title")%></H1>
<h6>Posted on: <%= session.getAttribute("date")%></h6>
<%if (user.equals(session.getAttribute("userId").toString())) {%>
<h3>
<fieldset>
<legend>Content<span id="deleteSpan" style="float:right"><input type="button" id="deleteBlog" value="Delete Blog" onclick="deleteBlogContent()" class="form-control btn-login btn" style="background:#59B2E0;color:white;width:200px;display:block;float:right;"></span></legend>
<span id = "blogContent"><%= session.getAttribute("content")%></span> 
<%} %>
</fieldset>
</h3>
<a id="favouriteLink" value="LIKE" href="#" onclick="likeBlog()">Likes : </a>
<span id="favouriteVal" ><%= session.getAttribute("like")%></span>
<br>
<%if (user.equals(session.getAttribute("userId").toString())) {%>
<input type="button" id="editBlogContent" value="Edit" onclick="editBlogContent()" class="form-control btn-login btn" style="background:#59B2E0;color:white;width:200px;display:block;">
<%} %>
<form action="BlogpostServlet" id="blogUpdateForm" method="post">
<input type="hidden" name="favourite" id="favourite" value="<%= session.getAttribute("like")%>"/>
<input type="hidden" name="bid" value="<%= session.getAttribute("id")%>" />
<input type= "hidden" name="title" value = "<%= session.getAttribute("title")%>" />

<textarea rows="10" cols="50" name="updatedContent" id="commentBoxContent" style="display:none">
 <%= session.getAttribute("content")%>
</textarea>
<input type="hidden" id="userId" name= "userId" value="<%= session.getAttribute("userId")%>"/>
<input type="hidden" id="operation" name= "operation" value="" />
<input type="hidden" id="like" name= "like" value="" />


<input type="button" id="submitBlogContent" value="Submit" onclick="submitBlog()" class="form-control btn-login btn" style="background:#59B2E0;color:white;width:200px;display:none;">
<!-- <input type="button" id="favourite" value="LIKE" onclick="likeBlog()"> -->

</form>
<%-- <p><span id="blaaag"><%= session.getAttribute("id")%></span></p> --%>
</div>
<hr>
<div id="commentBox">
<form>
<textarea rows="10" cols="50" id="commentBoxText" onchange="style.background.color='white'" placeholder="Like the blog? Post your comments here"></textarea>
<br>
<br>
 <div class="form-group">
									
<input type="button" id="submitComment" value="Submit" class="form-control btn-login btn" style="background:#59B2E0;color:white;width:200px;"></div>
</form>
</div>

<div id="comments">
<% List<CommentBean> comments = (List<CommentBean>)session.getAttribute("comments");
	for (CommentBean c : comments){ %>
	<hr>
<div id="newComment'+item.id+'" style="opacity: 0.8;background:white;font-weight: bold">
<p><%= c.getContent()%></p>
<%--  <img src="images/Cancel.png" id="comment<%=c.getId()%>"  height="20" width="20" style="float: right;"/> --%>

<p>Commented on : <%= c.getDate() %></p>
<p>Commented by : <%= c.getUserName()%></p>
  <input type="button" id="comment<%=c.getId()%>" value="X" onclick="gtButtonDetails(this)" class="form-control btn-login btn" style="background:#59B2E0;color:white;width:50px;display:block;"> 
</div>

<% } %>
</div>
<hr>
<div id="footer" style="opacity: 1.0;font-weight: bold"> <center><h4> All Rights Reserved | V Care | 2015 </h4></center></div>
</div>
</body>
</html>