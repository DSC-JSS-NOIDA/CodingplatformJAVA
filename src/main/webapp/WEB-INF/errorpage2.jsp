<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>


<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.1/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.1/js/materialize.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Insert title here</title>
</head>
<body>

	<nav>
	<div class="nav-wrapper blue darken-1">
		<a href="dashboard" class="brand-logo" style="margin-left: 30px;">{Code
			In Less}</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="#">Team : <span><b>${TeamName}</b></span></a></li>
			<li><a href="dashboard">Dashboard</a></li>
			<li><a href="rules">Rules</a></li>
			<li><a href="leaderboard">Leaderboard</a></li>
			<li><a href="logout">Log Out</a></li>
		</ul>
	</div>
	</nav>
	<div id="main">
		<div id="ques" class="z-depth-1">
		<h3>Go back and submit again</h3>
		</div>
	</div>
	<footer class="page-footer blue darken-1" style="padding-top: 0px;">
	<div class="footer-copyright">
		<div class="container">
			© 2017 Copyright <a target="_blank"
				class="grey-text text-lighten-4 right" href="http://gdgjss.in">GDG
				JSS Noida </a>
		</div>
	</div>
	</footer>
</body>
<style>
#ques {
	max-width: 1100px;
	margin-left: 50px;
	margin-top: 30px;
	padding: 10px 30px 30px 30px;;
	background: rgba(255, 255, 255, .4);
}

h1 {
	font-size: 30px;
	color: #0572d2;
}
</style>
</html>