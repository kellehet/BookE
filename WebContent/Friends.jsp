<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="styles/styles.css">

<link href="https://fonts.googleapis.com/css?family=Asap"
	rel="stylesheet">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<header> <nav
	class="navbar navbar-expand-sm justify-content-between fixed-top">

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
</nav> </header>
<body>
	<form action="Friends">
		<h1>${user.userName}'s Friends List</h1>
		<table class="table table-hover table-secondary table-striped">
			<thead>
				<tr>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Wins</th>
					<th>Losses</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${friendList}" var="friendList">
					<tr>
						<td><input type="radio" name="selected" value="${friendList.getUserName()}">    ${friendList.getUserName()}</td>
						<td><c:out value="${friendList.getFirstName()}"></c:out></td>
						<td><c:out value="${friendList.getLastName()}"></c:out></td>
						<td><c:out value="${friendList.getWins()}"></c:out></td>
						<td><c:out value="${friendList.getLoses()}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button type="submit" name="option" value="Remove Friend"> Remove Friend</button>
		<br><br>
	</form>
	
	<form action="Friends">
		<h1>${user.userName}'s Friend Requests</h1>
		<table class="table table-hover table-secondary table-striped">
			<thead>
				<tr>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Wins</th>
					<th>Losses</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${friendRequests}" var="friendRequests">
					<tr>
						<td><input type="radio" name="selected" value="${friendRequests.getUserName()}">    ${friendRequests.getUserName()}</td>
						<td><c:out value="${friendRequests.getFirstName()}"></c:out></td>
						<td><c:out value="${friendRequests.getLastName()}"></c:out></td>
						<td><c:out value="${friendRequests.getWins()}"></c:out></td>
						<td><c:out value="${friendRequests.getLoses()}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button type="submit" name="option" value="Accept">Accept</button>
		<button type="submit" name="option" value="Decline">Decline</button>
	</form>
	
	<form action="Friends">
		<h1>${user.userName}'s Pending Requests</h1>
		<table class="table table-hover table-secondary table-striped">
			<thead>
				<tr>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Wins</th>
					<th>Losses</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pendingRequests}" var="pendingRequests">
					<tr>
						<td><input type="radio" name="selected" value="${pendingRequests.getUserName()}">    ${pendingRequests.getUserName()}</td>
						<td><c:out value="${pendingRequests.getFirstName()}"></c:out></td>
						<td><c:out value="${pendingRequests.getLastName()}"></c:out></td>
						<td><c:out value="${pendingRequests.getWins()}"></c:out></td>
						<td><c:out value="${pendingRequests.getLoses()}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button type="submit" name="option" value="Cancel">Cancel</button>
	</form>
</body>
</html>