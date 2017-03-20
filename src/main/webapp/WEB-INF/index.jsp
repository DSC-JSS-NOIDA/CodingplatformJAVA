<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title>index</title>
</head>
<body>
	<nav>
		<div class="nav-wrapper blue darken-1">
			<a href="#" class="brand-logo" style="margin-left: 30px;">Crack In the Shell</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="#">Home</a></li>
				<li><a href="#">Dashboard</a></li>
				<li><a href="#">Leaderboard</a></li>
			</ul>
		</div>
	</nav>
	<h5 style="color: red;" class="left-align">${invalid}${norecord}</h5>
	<div class="row ">
		<div class="card col s6 offset-s3" style="background: rgba(255, 255, 255, .5);">
			<div class="card-content" align="center">
				<p style="font-size:30px;color:#0572d2;">GET STARTED</p>
			</div>
			<div class="card-tabs" >
				<ul class="tabs tabs-fixed-width" style="background: rgba(255, 255, 255, .7);">
					<li class="tab" onclick="indicator();"><a class="active" href="#login_form" style="color:#1E88E5;">Login</a></li>
					<li class="tab" onclick="indicator();"><a href="#signup_form"  style="color:#1E88E5;">Sign Up</a></li>
				</ul>
			</div>
			<div class="card-content " id="cards" style="background: rgba(255, 255, 255, .5);">
				<div id="login_form">
					<form action="login" method="POST" >
						<div class="input-field col s10 offset-s1">
							<input type="text" id="username" name="id" required> <label
								for="username">Username</label>
						</div>

						<div class="input-field col s10 offset-s1">
							<input type="password" id="pass" name="pass" required> <label
								for="pass">Password</label>
						</div>
						<div align="center">
							<button type="submit" class="waves-effect waves-light btn blue">Submit</button>
						</div>
					</form>
				</div>
				<div id="signup_form">
					<form action="signup" method="post">
						<div class="input-field col s10 offset-s1">
							<input type="text" name="team_name" id="team" required> <label
								for="team">Team name</label>
						</div>
						<div class="input-field col s10 offset-s1">
							<input type="text" name="participant1_name"
								id="participant1_name" required> <label
								for="participant1_name">Participant 1 name</label>
						</div>
						<div class="input-field col s10 offset-s1">
							<input type="text" name="participant1_roll"
								id="participant1_roll" required> <label
								for="participant1_roll">Participant 1 roll number</label>
						</div>
						<div class="input-field col s10 offset-s1">
							<input type="text" name="participant2_name"
								id="participant2_name" required> <label
								for="participant2_name">Participant 2 name</label>
						</div>
						<div class="input-field col s10 offset-s1">
							<input type="text" name="participant2_roll"
								id="participant2_roll" required> <label
								for="participant2_roll">Participant 2 roll number</label>
						</div>
						<div class="input-field col s10 offset-s1">
							<input type="text" name="email" id="email" required> <label
								for="email">Email</label>
						</div>
						<div class="input-field col s10 offset-s1">
							<input type="text" name="contact" id="contact" required>
							<label for="contact">Contact</label>
						</div>
						<div class="input-field col s10 offset-s1">
							<input type="text" name="password" id="password" required>
							<label for="password">Password</label>
						</div>
						<div align="center">
							<button type="submit" class="waves-effect waves-light btn blue">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>

<style>
h1 {
	padding-top: 25px;
	font-size: 30px;
}

#login_form h3 {
	font-size: 30px;
	margin: 0;
}

#cards button {
	margin-top: 10px;
	margin-bottom: 15px;
}

#cards {
	padding: 0;
}
.indicator{
	background: #1E88E5;
}
</style>
<script>
	function indicator(){
		$(".indicator").css("background","#1E88E5");
	}
	$(function(){
		indicator();
	});
</script>
</html>