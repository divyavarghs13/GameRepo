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
				<td>You can pay</td>
				<td><input type="text" name="numOfCoins" min="10" required="true" value="10" /></td>
			</tr>
			<tr>
				<td>Your Score:</td>
				<td>
					<table style="with: 50%" border="1">
						<tr>
							<td>Score</td>
						<c:forEach items="${scorelist}" var="item">		
								<td><c:out value="${item}" /></td>
						</c:forEach>
						</tr>
						<tr>
							<td>TimeOfPlay</td>
						<c:forEach items="${playlist}" var="time">								
								<td><c:out value="${time}" /></td>
						</c:forEach> 
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center"><input type="submit" value="Play" /></td>
			</tr>
		</table>
	</form>
</body>
</html>