<%@page import="model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="styles/betsStyles.css">
<link href="https://fonts.googleapis.com/css?family=Asap"
	rel="stylesheet">
</head>
<body>

	<c:choose>
		<c:when test="${action == 'Create Bet'}">
			<div class="wrapper">
				<h2>Alright, let's make a bet!</h2>
				<form action="LandingPageController" method="get">
					<input type="text" value="${user.userName}" name="creator" readonly><br>
				
						
						<select name="opponent" required id="selectOpponent">
						<option selected disabled >Who is your opponent?</option>
						<c:forEach items="${friendList}" var="friendList">
							<option value="${friendList.getUserName()}"required>${friendList.getUserName()}</option>
						</c:forEach>
					</select>
						<br>
					<textarea name="desc" rows="4" cols="50" maxlength="490" required
						placeholder="What are you betting on?"></textarea>
					<br> <br> <label>Your Stance<br> <textarea
							name="cc" rows="4" cols="50" maxlength="490" required
							placeholder="Enter your stance"></textarea> <br> <br> <label>There
							Stance<br> <textarea name="oc" rows="4" cols="50"
								maxlength="490" required
								placeholder="Enter your opponents stance"></textarea> <br>
							<br> <label>Wager<br> <textarea name="wager"
									rows="4" cols="50" maxlength="195"
									placeholder="What are you willing to wager?" required></textarea>
								<br>
								<button type="submit" name="action" value="Send Bet">Send
									Bet</button>
				</form>
				<form action="home.jsp" method="get">
					<button type="submit" name="action" value="Back">Back</button>
				</form>
			</div>
		</c:when>
		<c:when test="${action == 'Confirm Bet'}">


			<div class="wrapper">
				<form action="LandingPageController" method="get">
					<h2>Does this Bet look good to you?</h2>
					<input type="hidden" value="${bet.betId}"
						name="betId"> <label>Creator<input
						type="text" value="${bet.creator.userName}" name="creator"
						readonly></label><br> <label>Opponent<br>
						
						 <input
						type="text" name="opponent" value="${bet.opponent.userName}"
						readonly></label><br> <label>Description<br> <textarea
							name="desc" rows="4" cols="50" maxlength="490">${bet.desc}</textarea></label><br>
					<br> <br> <label>Your Stance<br> <textarea
							name="cc" rows="4" cols="50" maxlength="490" required
							placeholder="Enter your stance">${bet.creatorOption}</textarea> <br>
						<br> <label>There Stance<br> <textarea name="oc"
								rows="4" cols="50" maxlength="490" required
								placeholder="Enter your opponents stance">${bet.opponentOption}</textarea>
							<br> <label>Wager<br> <textarea name="wager"
									rows="4" cols="50" maxlength="195">${bet.wager}</textarea></label><br>
							<c:if test="${user.userName==bet.opponent.userName}">
							
							<button type="submit" name="action" value="Update/Counter">Update/Counter</button>
							<br>
						
							<button type="submit" name="action" value="Accept">Accept
								Bet</button> <br>
							<button type="submit" name="action" value="Reject">Reject/Remove</button>
							</c:if>
							<c:if test="${user.userName==bet.creator.userName}">
							
							<button type="submit" name="action" value="Update/Counter">Update/Counter</button>
							<br>
						
							<button type="submit" name="action" value="Reject">Reject</button>
							</c:if>
				</form>
				<form action="home.jsp" method="get">
					<button type="submit" name="action" value="Back">Back</button>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div class="wrapper">
				<form action="LandingPageController" method="get">
					<h2>Who Won</h2>
				<input type="hidden" value="${bet.betId}"
						name="betId"> <label>Creator<input
						type="text" value="${bet.creator.userName}" name="creator"
						readonly></label><br> <label>Opponent<br> <input
						type="text" name="opponent" value="${bet.opponent.userName}"
						readonly></label><br> <label>Description<br> <textarea
							name="desc" rows="4" cols="50" maxlength="490" readonly>${bet.desc}</textarea></label><br><br>
					
					<label>Select Winner</label><br>
					<label><input class="select"  type="radio" name="player"
						value="${bet.creator.userName}" required> ${bet.creator.userName} Bet: ${bet.creatorOption}</label>
					<label><input class="select"  type="radio" name="player"
						value="${bet.opponent.userName}" required> ${bet.opponent.userName} Bet: ${bet.opponentOption}</label><br><br>
		
					<label>Wager<br> <textarea name="wager" rows="4"
							cols="50" maxlength="195" readonly>${bet.wager}</textarea></label><br>
					<button type="submit" name="action" value="winner">Submit</button>

				</form>
				<form action="home.jsp" method="get">
					<button type="submit" name="action" value="Back">Back</button>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>