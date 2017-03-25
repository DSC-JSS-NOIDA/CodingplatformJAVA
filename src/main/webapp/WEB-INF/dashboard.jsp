<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>


<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta charset="ISO-8859-1">
<title>Dashboard</title>
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
			<h2>QUESTIONS</h2>
			<%
				int i = 1;
			%>
			<table class=" bordered highlight ">

				<c:forEach var="question" items="${ques}">
					<tr>
						<td id="index"><%=i%>.</td>
						<td style="min-width: 750px;"><a
							href="ques?id=${question.quesid}"><c:out
									value="${question.title}" /></a></td>
					</tr>
					<%
						i = i + 1;
					%>
				</c:forEach>
			</table>
		</div>
	</div>
	<footer class="page-footer blue darken-1" style="padding-top: 0px;">
		<div class="footer-copyright">
			<div class="container">
				© 2017 Copyright <a target="_blank"
					class="grey-text text-lighten-4 right" href="http://gdgjss.in">GDS
					JSS Noida </a>
			</div>
		</div>
	</footer>
</body>

<style>
table {
	width: 800px;
}

#ques {
	background: rgba(255, 255, 255, .3);
	padding: 10px;
	border-radius: 3px;
	width: 820px;
	margin: 30px auto;
}

td {
	font-size: 18px;
	padding-left: 20px;
	background: rgba(255, 255, 255, .4);
}

td a {
	color: #444;
}

h2 {
	font-size: 40px;
	color: #0572d2;
}

#index {
	width: 60px;
}
</style>
</html>
