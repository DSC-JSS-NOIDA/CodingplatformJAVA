<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta charset="ISO-8859-1">
<title>index</title>
</head>
<body>
	<!-- <nav style="baclground-color:rgba(0,0,0,0);">
		<div class="nav-wrapper transparent">
			<a href="index" class="brand-logo center" style="margin-left: 30px;color:">{Code
				In Less}</a>
		</div>
	</nav> -->
	<!-- <h5 style="color: red;" class="left-align"></h5> -->
	<div id="snackbar">${invalid}${norecord}</div>
	<div id="main">
		<div id="ques" style="height: 100%; width: 700px; margin: 0 auto;"
			align="center">
			<h1>{CODE IN LESS}</h1>
			<!-- Modal Structure -->
			<div id="modal1" class="modal">
				<div class="modal-content">
					<div class="card col s6 offset-s3"
						style="margin-top: 20px; padding: 20px;">
						<div class="card-content" align="center">
							<p style="font-size: 30px; color: #0572d2;">GET STARTED</p>
						</div>
						<div class="card-tabs">
							<ul class="tabs tabs-fixed-width"
								style="background: rgba(255, 255, 255, .7);">
								<li class="tab" onclick="indicator();"><a class="active"
									href="#login_form" style="color: #1E88E5;">Login</a></li>
								<li class="tab" onclick="indicator();"><a
									href="#signup_form" style="color: #1E88E5;">Sign Up</a></li>
							</ul>
						</div>
						<div class="card-content " id="cards" style="margin-top: 0;">
							<div id="login_form">
								<form action="login" method="POST">
									<div class="input-field col s10 offset-s1">
										<input type="text" id="username" name="id" required> <label
											for="email">Email</label>
									</div>

									<div class="input-field col s10 offset-s1">
										<input type="password" id="pass" name="pass" required>
										<label for="pass">Password</label>
									</div>
									<div align="center">
										<button type="submit"
											class="waves-effect waves-light btn blue"
											style="margin-bottom: 0;">Submit</button>
									</div>
								</form>
							</div>
							<div id="signup_form">
								<form action="signup" method="post">
									<div class="input-field col s10 offset-s1">
										<input type="text" name="team_name" id="team" required>
										<label for="team">Team name</label>
									</div>
									<div class="input-field col s10 offset-s1">
										<input type="text" name="participant1_name"
											id="participant1_name" required> <label
											for="participant1_name">Participant 1 name</label>
									</div>
									<div class="input-field col s10 offset-s1">
										<input type="text" name="participant1_roll"
											id="participant1_roll" required> <label
											for="participant1_roll">Participant 1 roll number/
											Zeal Id</label>
									</div>
									<div class="input-field col s10 offset-s1">
										<input type="text" name="participant2_name"
											id="participant2_name" required> <label
											for="participant2_name">Participant 2 name</label>
									</div>
									<div class="input-field col s10 offset-s1">
										<input type="text" name="participant2_roll"
											id="participant2_roll" required> <label
											for="participant2_roll">Participant 2 roll number /
											Zeal Id</label>
									</div>
									<div class="input-field col s10 offset-s1">
										<input type="text" name="emailid" id="email" required>
										<label for="email">Email</label>
									</div>
									<div class="input-field col s10 offset-s1">
										<input type="text" name="contactno" id="contact" required>
										<label for="contact">Contact</label>
									</div>
									<div class="input-field col s10 offset-s1">
										<input type="password" name="password" id="password" required>
										<label for="password">Password</label>
									</div>
									<div align="center">
										<button type="submit"
											class="waves-effect waves-light btn blue"
											style="margin-bottom: 0;">Submit</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal Trigger -->
			<button id="modlbtn" data-target="modal1" class="btn blue darken-1">Let's
				Code</button>
		</div>
	</div>
	<!-- <footer class="page-footer blue darken-1" style="padding-top: 0px;">
		<div class="footer-copyright">
			<div class="container">
				© 2017 Copyright <a target="_blank"
					class="grey-text text-lighten-4 right" href="http://gdgjss.in">GDG
					JSS Noida </a>
			</div>
		</div>
	</footer> -->
</body>

<style>

body {
	background: url('css/back.jpg');
	background-size: cover;
	background-position: center center;
	background-attachment: fixed;
}

h1 {
	margin-top: 180px;
	margin-bottom: 50px;
	font-size: 70px;
	color: #fff;
}

#login_form, #signup_form {
	padding: 20px 45px;
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
	margin-top: 50px;
}

.indicator {
	background: #1E88E5;
}

#mdlbtn {
	/* 	margin-top:300px; */
	
}
/* snackbar */
/* The snackbar - position it at the bottom and in the middle of the screen */
#snackbar {
	visibility: hidden; /* Hidden by default. Visible on click */
	min-width: 250px; /* Set a default minimum width */
	margin-left: -125px; /* Divide value of min-width by 2 */
	background-color: #333; /* Black background color */
	color: #fff; /* White text color */
	text-align: center; /* Centered text */
	border-radius: 2px; /* Rounded borders */
	padding: 16px; /* Padding */
	position: fixed; /* Sit on top of the screen */
	z-index: 1; /* Add a z-index if needed */
	left: 50%; /* Center the snackbar */
	bottom: 60px; /* 30px from the bottom */
}

/* Show the snackbar when clicking on a button (class added with JavaScript) */
#snackbar.show {
	visibility: visible; /* Show the snackbar */
	/* Add animation: Take 0.5 seconds to fade in and out the snackbar. 
However, delay the fade out process for 2.5 seconds */
	-webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
	animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

/* Animations to fade the snackbar in and out */
@
-webkit-keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
keyframes fadein {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
-webkit-keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}

}
@
keyframes fadeout {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}
}
</style>
<script>
	function indicator() {
		$(".indicator").css("background", "#1E88E5");
	}
	function snackBar() {
		var x = document.getElementById("snackbar")
		if ($('#snackbar').is(':empty')) {
			return;
		} else {
			x.className = "show";
			setTimeout(function() {
				x.className = x.className.replace("show", "");
			}, 3000);
		}

	}
	$(function() {
		indicator();
		snackBar();
	});

	$(document).ready(function() {
		$(".indicator").css("background", "#1E88E5");
		// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
		$('.modal').modal();
	});

	$('.modal').modal({
		dismissible : true, // Modal can be dismissed by clicking outside of the modal
		opacity : .5, // Opacity of modal background
		inDuration : 300, // Transition in duration
		outDuration : 200, // Transition out duration
		startingTop : '4%', // Starting top style attribute
		endingTop : '10%', // Ending top style attribute
		ready : function(modal, trigger) { // Callback for Modal open. Modal and trigger parameters available.
			alert("Ready");
			console.log(modal, trigger);
		},
		complete : function() {
			alert('Closed');
		} // Callback for Modal close
	});
	$('#modlbtn').on('click', function() {
		indicator();
	});
</script>
</html>