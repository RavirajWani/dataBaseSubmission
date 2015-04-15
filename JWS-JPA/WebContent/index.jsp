<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

</head>
<body>
<f:view>
<h1> Hello JSP</h1>
<script>
	$(function()
			{
		alert("Hello from JQuery");
		var site = {
				"id":3,
				"name":"CooperMine",
				"latitude":"100NE",
				"longitude":"200SW"
		}
		$.ajax({
			url:"/jws-jpa/api/site/" ,
			type : "post",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data : JSON.stringify(site),
			success : function(response){
				console.log("yahoo")
			},
			error : function(response){
				console.log(response);
			}
			
		})	
	})
	
	function updateSite(id, site)
	{
		$.ajax({
			url:"/jws-jpa/api/site/"+id ,
			type : "put",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data : JSON.stringify(site),
			success : function(response){
				console.log("yahoo")
			},
			error : function(response){
				console.log(response);
			}
		});
	}
	
	function removeSite(id)
	{
		$.ajax({
			url:"/jws-jpa/api/site/"+id ,
			type : "delete",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data : JSON.stringify(site),
			success : function(response){
				console.log("yahoo")
			},
			error : function(response){
				console.log(response);
			}
		});
	}
	
</script>
</f:view>
</body>
</html>