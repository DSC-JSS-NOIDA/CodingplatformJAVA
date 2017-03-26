<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Leaderboard</title>
<script src="js/jquery.js"></script>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script src="js/materialize.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

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
	<%
		int i = 1;
	%>
	<div id="main" align="center" style="">
		<div id="lead" class="z-depth-1">
			<h1>LEADERBOARD</h1>

			<div id="table">
				<table class=" bordered highlight ">
					<tr>
						<th><span>RANK</span></th>
						<th><span>TEAM</span></th>

						<th><span>TOTAL SCORE</span></th>

						<th><span>Ques 1 C/C++</span></th>
						<th><span>Ques 1 JAVA</span></th>
						<th><span>Ques 1 PYTHON</span></th>

						<th><span>Ques 2 C/C++</span></th>
						<th><span>Ques 2 JAVA</span></th>
						<th><span>Ques 2 PYTHON</span></th>

						<th><span>Ques 3 C/C++</span></th>
						<th><span>Ques 3 JAVA</span></th>
						<th><span>Ques 3 PYTHON</span></th>

						<th><span>Ques 4 C/C++</span></th>
						<th><span>Ques 4 JAVA</span></th>
						<th><span>Ques 4 PYTHON</span></th>

						<th><span>Ques 5 C/C++</span></th>
						<th><span>Ques 5 JAVA</span></th>
						<th><span>Ques 5 PYTHON</span></th>

					</tr>

					<c:forEach var="result_row" items="${resultRows}">
						<tr>
							<td><%=i%></td>
							<td><c:out value="${result_row.team}" /></td>

							<td><c:out value="${result_row.total}" /></td>

							<td><c:out value="${result_row.ques1_C}" /></td>
							<td><c:out value="${result_row.ques1_JAVA}" /></td>
							<td><c:out value="${result_row.ques1_PYTHON}" /></td>

							<td><c:out value="${result_row.ques2_C}" /></td>
							<td><c:out value="${result_row.ques2_JAVA}" /></td>
							<td><c:out value="${result_row.ques2_PYTHON}" /></td>

							<td><c:out value="${result_row.ques3_C}" /></td>
							<td><c:out value="${result_row.ques3_JAVA}" /></td>
							<td><c:out value="${result_row.ques3_PYTHON}" /></td>

							<td><c:out value="${result_row.ques4_C}" /></td>
							<td><c:out value="${result_row.ques4_JAVA}" /></td>
							<td><c:out value="${result_row.ques4_PYTHON}" /></td>


							<td><c:out value="${result_row.ques5_C}" /></td>
							<td><c:out value="${result_row.ques5_JAVA}" /></td>
							<td><c:out value="${result_row.ques5_PYTHON}" /></td>

						</tr>
						<%
							i = i + 1;
						%>

					</c:forEach>

				</table>
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
	min-width: 1300px;
}

table {
	width: 1200px;
}

#table {
	width: 1200px;
	overflow: auto;
	overflow-x: scroll;
	border-radius: 5px;
	margin-top: 20px;
}

td, th {
	width: 100%;
	min-width: 80px;
	background: rgba(255, 255, 255, .5);
}

td, th {
	border-right: solid 1px #ddd;
}

#table::-webkit-scrollbar-track {
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
	border-radius: 20px;
	background-color: #F5F5F5;
}

#table::-webkit-scrollbar {
	width: 7px;
	height: 8px;
	background-color: #F5F5F5;
}

#table::-webkit-scrollbar-thumb {
	border-radius: 20px;
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
	background-color: rgba(30, 136, 229, .59);
}

#lead {
	background: rgba(255, 255, 255, .3);
	padding: 10px 10px 20px 10px;
	border-radius: 3px;
	width: 1220px;
	margin: 30px auto;
}

h1 {
	font-size: 40px;
	color: #0572d2;
	margin-top: 20px;
}
</style>

</html>
