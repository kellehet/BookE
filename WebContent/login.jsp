<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book-E Login</title>
<link href="https://fonts.googleapis.com/css?family=Asap" rel="stylesheet">
<link rel="stylesheet" href="styles/login.css">
</head>
<body>

	<header><img alt="Book-E Logo2" src="images/Logo-02.png"></header>
	
	<!-- 
	***************************************************************************
	-->
	
	<div id="loginForm">
		<form action="UserController" method="post">
			<input type="text" name="username" placeholder="Username">
			<input type="password" name="pw" placeholder="Password">
			<button type="submit" name="action" value="Login">LOG IN</button>
			<% session.setAttribute("userExists", false);	%>
		</form>
		<div id="signInLink">
			<a href="signup.jsp">Need an account?</a>
		</div>
		
		<c:if test = "${userCreated}">
			<div class = "infobox green">
				<h2>Account Created</h2>
				<% session.setAttribute("userCreated", false); %>
			</div>
		</c:if>
		
		<c:if test = "${wrongCredentials}">
			<div class = "infobox">
				<h2>Incorrect Username/Password</h2>
			</div>
		</c:if>
		
		<!-- 
		***************************************************************************
		 -->
	</div>
	
	<footer>Created by Matt Ditmars, Tyler Kelleher, Luka Leko, and Jay Shah</footer>

</body>
</html>