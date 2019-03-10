<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.perscholas.servletjsp_sba.Poem"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Poem</title>
</head>
<body> 
	<a href="Home">Back</a>
	<h1>Edit a poem</h1>
	
	<form action="PoemByTitle" method="POST">
		Search Poem By Title:<br>
		<input type="text" name="title" value="">
		<input type="submit" value="Search">
	</form>
	<br><br>
	<form action="UpdatePoem" method="POST">
		New Content:<br>
		<input type="hidden" name="title" value="" />
		<input type="hidden" name="author" value="" />
		<textarea rows="10" cols="50" name="newContent">
			</textarea>
		<br><br>
		<input type="submit" value="Update">
	</form> 
</body>
</html>