<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Friend</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="styles/styles.css">

<link href="https://fonts.googleapis.com/css?family=Asap"
	rel="stylesheet">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<header> <nav class="navbar navbar-expand-sm justify-content-between fixed-top">

<div class="logo">
	<img src="images/Logo-01.png" class="navbar-brand">
</div>

<ul class="navbar-nav collapse navbar-collapse">

	<li class="nav-item"><a class="nav-link" href="home.jsp">Home</a></li>

	<li class="nav-item"><a class="nav-link" href="profile.jsp">Profile</a></li>

	<li class="nav-item"><a class="nav-link" href="leaderboard.jsp">Leaderboard</a></li>
</ul>
<form action="UserController" method="post">
    <input type="submit" name="action" value="Sign-Out"/>
</form>
</nav></header>
<body>
	<h2>Find Friend</h2>
	<form action="findFriend" method="POST">
		<label>Username: </label>
		<input type="text" name="username">
		<button type="submit" value="Find Friend">Find Friend</button>
	</form>
	
	<c:if test="${pageContext.request.method == 'POST'}">
		<h3>Status: ${friendResult}</h3>
		<c:if test="${friendResult == 'confirmRequest'}">
			<form action="findFriend" method="GET">
				<h3>${friendToFind}</h3>
				<button type="submit" value="Request Friend">Request Friend</button>
			</form>
		</c:if>
	</c:if>
</body>
</html>