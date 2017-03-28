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

<title>Rules</title>
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
		<div id="ques" class="z-depth-1" align="center">
			<h2>RULES</h2>
			<div id="rules" style="">
				<p>1.It is a 2 hour event.</p>
				<p>2.In it students will be provided with 6 questions.</p>
				<p>3.Students are allowed to code in the following four
					languages.</p>
				<p>C/C++.</p>
				<p>JAVA.</p>
				<p>Python.</p>
				<p>Php.</p>
				<p>They can code in either one language or multiple languages.</p>
				<p>4.Each language has 100 points for the shortest code.</p>
				<p>5.Use of internet is strictly prohibited.</p>
				<p>6.In case of any discrepancy, the decision of the event
					coordinator will be final.</p>
			</div>
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