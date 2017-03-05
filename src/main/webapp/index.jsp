<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 	  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
      <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <meta charset="ISO-8859-1">
      <title>index</title>
</head>
<body>

<div id="login_form">
	<h1>LOGIN </h1>
	<form action="login" method="POST">
	Enter email Id :
	<br>
	<input type="text" name="email" required>
	<br> 
	Enter contact no :
	<br>
	<input type="password" name="contact" required>
	<br>
	<br>
	<input type="submit" value="submit">

	</form>
</div> 

<br>
<br>
<br>
<br>
<br>

<div>
<h1>SIGN UP</h1>
	<form action="signup" method="post" >
	Enter team name<br><input type="text" name="team_name" required><br> 
	Enter 1 participant name<br><input type="text" name="name"_1 required><br> 
	Enter 1 participant roll number<br><input type="text" name="roll_1" required><br> 
	Enter 2 participant name<br><input type="text" name="name_2" required><br> 
	Enter 2 participant roll number<br><input type="text" name="roll_2" required><br> 
	Enter Email Id:<br><input type="text" name="emailid" required><br>
	Enter contact number:<br><input type="password" name="contact" required>
	<br> 
	<br>
	<input type=submit value="submit">
	</form>
</div>
</body>
</html>