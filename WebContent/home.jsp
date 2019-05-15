<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="styles/leaderboardStyles.css">

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
	
	<div class="container">
		<h2>Welcome ${user.userName}, here are your bets</h2>
		
		<label>(${user.wins} - ${user.loses} - <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${user.wins/(user.wins + user.loses)}" />)</label>
		<form action="LandingPageController" name="action" method="get">
		<table class="table table-hover table-secondary table-striped">
			<thead>
				<tr>
					<th>Select</th>
					<th>Creator</th>
					<th>Opponent</th>
					<th>Description</th>
					<th>Creator's Bet</th>
					<th>Opponent's Bet</th>
					<th>Wager</th>
					<th>Winner</th>
					<th>Loser</th>
					<th>Accepted</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bets}" var="bets">
					<tr>
						
						<td><input type="radio" name="selected" value="${bets.betId}" required></td>
						<td><c:out  value="${bets.creator.userName}"></c:out></td>
						<td><c:out  value="${bets.opponent.userName}"></c:out></td>
						<td><c:out  value="${bets.desc}"></c:out></td>
						<td><c:out  value="${bets.creatorOption}"></c:out></td>
						<td><c:out  value="${bets.opponentOption}"></c:out></td>
						<td><c:out  value="${bets.wager}"></c:out></td>
						<td><c:out value="${bets.getWinner().getUserName()}"></c:out></td>
						<td><c:out  value="${bets.loser.userName}"></c:out></td>
						<td><c:out  value="${bets.accepted}"></c:out></td>


					</tr>
				</c:forEach>
			</tbody>
		</table>
		
			<c:if test="${isAccepted}"><label>Bet need to be accepted first</label><br> </c:if>
			<c:if test="${isConfirmed}"><label>Bet has already been accepted </label><br> </c:if>
			<c:if test="${isWinnerDecided}"><label>Winner has been decided already </label><br> </c:if>
			
			<c:if test="${not empty bets}">
			<button type="submit" name="action" value="Confirm Bet">Confirm Bet</button>
			<button type="submit" name="action" value="Who Won">Who Won</button><br><br>
			</c:if>
			
		</form>
		<form action="LandingPageController" name="action" method="get">
			<button type="submit" name="action" value="Create Bet">Create Bet</button><br><br>
		</form>
		
		<hr>
		
		<!-- Taking user to find user page -->
		<form action="findFriend.jsp" method="get">
			<h3>Find User</h3>
			<button type="submit" name="action" value="Find User">Find User</button>
		</form>
		
		<!-- Taking user to see active friend requests -->
		<form action="Friends" method="get">
			<h3>Friends Page</h3>
			<button type="submit" name="action" value="Friends">Friends</button>
		</form>
		
	</div>

</body>
</html>