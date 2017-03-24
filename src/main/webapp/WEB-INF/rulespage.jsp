<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Rules</title>
</head>
<body>
	<nav>
	<div class="nav-wrapper blue darken-1">
		<a href="dashboard" class="brand-logo" style="margin-left: 30px;">{Code
			In Less}</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="dashboard">Dashboard</a></li>
			<li><a href="rules">Rules</a></li>
			<li><a href="leaderboard">Leaderboard</a></li>
			<li><a href="logout">Log Out</a></li>
		</ul>
	</div>
	</nav>

	<div id="ques" class="z-depth-1" align="center">
		<h2>RULES</h2>
		<div id="rules" style="">
			<p>1. Establishing SSL connection without server's identity
				verification is not recommended.</p>
			<p>2. Establishing SSL connection without server's identity
				verification is not recommended.</p>
			<p>3. Establishing SSL connection without server's identity
				verification is not recommended.</p>
			<p>4. Establishing SSL connection without server's identity
				verification is not recommended.</p>
			<p>4. Establishing SSL connection without server's identity
				verification is not recommended.</p>
		</div>
	</div>
</body>
<style>
body {
	height: 100vh;
	min-height: 600px;
	min-width: 1300px;
}

#ques {
	background: rgba(255, 255, 255, .3);
	padding: 10px;
	border-radius: 3px;
	width: 820px;
	margin: 30px auto;
}

#rules {
	background: rgba(255, 255, 255, .5);
	text-align: left;
	padding-top: 8px;
	padding-bottom: 8px;
	padding-left: 20px;
	font-size: 18px;
}

p {
	margin-bottom: 0px;
}

h2 {
	font-size: 40px;
	color: #0572d2;
}
</style>
</html>