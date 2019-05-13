<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game Form</title>
</head>
<body>
<form action="/bet/checkStatus" method="get">

	
			<table style="with: 50%">
			<tr><td><input type="hidden" name="id" value="${id}" /></td></tr>			
			<tr><td><input type="hidden" name="numOfCoins" value="${numOfCoins}" /></td></tr>
				<tr><td align="center"><input type="submit" value="CheckForStatus" /></td>
				</tr>
				
			</table>
</form>	
</body>
</html>