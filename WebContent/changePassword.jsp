<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Asap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="styles/styles.css">
<link rel="stylesheet" type="text/css" href="styles/Profile.css">


</head>

<%
	if (request.getSession().getAttribute("user") != null && request.getSession() != null) {
%>
<header> <nav
	class="navbar navbar-expand-sm justify-content-between fixed-top">

<div class="logo">
	<img src="Logo-01.png" class="navbar-brand">
</div>

<ul class="navbar-nav collapse navbar-collapse">

	<li class="nav-item"><a class="nav-link" href="home.jsp">Home</a></li>
	<li class="nav-item"><a class="nav-link" href="profile.jsp">Profile</a></li>
	<li class="nav-item"><a class="nav-link" href="leaderboard.jsp">Leaderboard</a></li>
</ul>


<form action="UserController" method="post">
	<input type="submit" name="action" value="Sign-Out" />
</form>

</nav> </header>
<body>

<form  action="UserController" method="post">
			<input type="password" name="oldPassword"
				placeholder="Current Password" /><br> <input type="password"
				name="newPassword" placeholder="New Password" /><br> <input
				type="password" name="confirmPassword"
				placeholder="Re enter New Password"><br>
				<input
				type="submit" name="action" value="Confirm New Password" />
		</form>
	<%
		} else {
	%>
	Please click
	<a href="login.jsp">here</a> to Login!
	<%
		}
	%>
</body>
</html>