<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin login page</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="back" align="center">
		<h1>ADMIN LOGIN</h1>
		<form name=logondet method=post action=adminverify>
			<div>
				<label for="username">Enter your user id : </label> <input
					type="text" id="username" required>
			</div>
			<div>
				<label for="pass">Enter password : </label> <input type="password"
					id="pass" required>
			</div>

			<button type="submit">Submit</button>

		</form>
	</div>

</body>
<style>
h1 {
	padding-top: 25px;
}

input {
	width: 250px;
	margin-right: 140px;
}

label {
}

#back {
	width: 600px;
	margin: 180px auto;
	background: rgba(255, 255, 255, .3);
	padding: 0 50px;
}

form {
	
}

button {
	margin-top: 10px;
}
</style>
</html>