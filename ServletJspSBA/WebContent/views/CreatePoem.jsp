<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.perscholas.servletjsp_sba.Poem"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Poem</title>
</head>
<body>
	<a href="Home">Back</a>
	<h1>Create a new poem</h1>
	
	<form action="CreatePoem" method="POST">
		Title:<br>
		<input type="text" name="title" value="">
		<br>
		Author:<br>
		<input type="text" name="author" value="">
		<br>
		Content:<br>
		<textarea rows="10" cols="50" name="content">
		</textarea>
		<br><br>
		<input type="submit" value="Submit">
	</form> 
</body>
</html>