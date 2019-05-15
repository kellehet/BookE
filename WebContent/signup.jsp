<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book-E Sign Up</title>
<link href="https://fonts.googleapis.com/css?family=Asap" rel="stylesheet">
<link rel="stylesheet" href="styles/signUp.css">
</head>
<body>

	<header><img alt="Book-E Logo2" src="images/Logo-02.png"></header>
	
	<div id="loginForm">
	<!-- 
	***************************************************************************
	 -->
	<c:if test = "${!userExists}">
		<form action="UserController" method="post">
			<input type="text" name="firstname" placeholder="First Name" required>
			<input type="text" name="lastname" placeholder="Last Name" required>
			<input type="email" name="email" placeholder="Email" required>
			<input type="text" name="username" placeholder="Username" required>
			<input type="password" name="pw" placeholder="Password" required>
			<button type="submit" name="action" value="Sign Up">CREATE ACCOUNT</button>
		</form>
	</c:if>
	
	<c:if test = "${userExists}">
		<form action="UserController" method="post">
			<input type="text" name="firstname" placeholder="First Name" value="${userCreate.firstName}" required>
			<input type="text" name="lastname" placeholder="Last Name" value="${userCreate.lastName}" required>
			<input type="email" name="email" placeholder="Email" value="${userCreate.email}" required>
			<input type="text" name="username" placeholder="Username" required>
			<input type="password" name="pw" placeholder="Password" required>
			<button type="submit" name="action" value="Sign Up">CREATE ACCOUNT</button>
		</form>
		<div class = "signupbox">
			<h2>User Already Exists</h2>
		</div>
		
		<!-- 
		***************************************************************************
		 -->
	</c:if>
	
	<!-- 
	***************************************************************************
	 -->
	</div>
	
	<footer>Created by Matt Ditmars, Tyler Kelleher, Luka Leko, and Jay Shah</footer>

</body>
</html>