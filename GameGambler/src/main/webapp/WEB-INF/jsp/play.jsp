<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game Login Form</title>
</head>
<body>
	<div>
		<h3>Welcome ${name}</h3>
	</div>
	<br />

	<form action="play" method="post">

		<table style="with: 50%">
			<tr>
				<input type="hidden" name="id" value="${id}" />
			</tr>
			<tr>
				<td>Number of coins you want to spend</td>
				<td><input type="text" name="numOfCoins" required="true"
					min="1" /></td>
			</tr>
			<!-- <tr>
				<td>Your last Score:</td>
				<td>
					<table style="with: 50%" border="1">
						<tr>
							<td>Score</td>
							<td>TimeOfPlay</td>
						</tr>
						<tr>
							<c:forEach items="${ScoreDetails}" var="item">
								<td><c:out value="${item}" /></td>
							</c:forEach>
						</tr>
					</table>
				</td>
			</tr> -->
			<tr>
				<td align="center"><input type="submit" value="Play" /></td>
			</tr>
		</table>
	</form>
</body>
</html>