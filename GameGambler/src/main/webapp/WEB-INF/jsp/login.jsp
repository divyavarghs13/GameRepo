<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game Login Form</title>
</head>
<body>
<h1>Game Login Form</h1>
<form action="findUser" method="post">
			<table style="with: 50%">
				<tr>
					<td>UserName</td>
					<td><input type="text" name="username" required="true"  /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" required="true" autocomplete="new-password"/></td>
				</tr></table>
			<input type="submit" value="Submit" /></form>
</body>
</html>