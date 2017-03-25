<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Error</title>
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
			<h5>INTERNET PROBLEM TRY REFRESHING AGAIN</h5>
		
			
			<div id="editor_lang">
<div style="color:red">YOUR SUBMISSION</div>
				<textarea id="editor2" name="code">${code}</textarea>


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


			
			<!-- load ace -->
			<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js"
				type="text/javascript" charset="utf-8"></script>

			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
			<script>
				// trigger extension

				var lang = "c_cpp";
				$(document).ready(function() {
					$("#editor_select").change(function() {
						$("#editor_select option:selected").each(function() {
							lang = $(this).attr('value');
							console.log(lang);
							editor2.session.setMode("ace/mode/" + lang);
							// v: Date.now();  
						});
					});
				});

				var editor2 = ace.edit("editor2");
				editor2.setTheme("ace/theme/twilight");
				editor2.session.setMode("ace/mode/html");
				editor2.setAutoScrollEditorIntoView(true);
				editor2.setOption("maxLines", 16);
				editor2.setOption("minLines", 8);
				var source = $("#editor2").val();
				$("#source").val('source');
				editor2.getSession().on("change", function() {
					$("#source").val(editor2.getSession().getValue());
				});
			</script>


<style>
#ques {
	background: rgba(255, 255, 255, .3);
	padding: 10px;
	border-radius: 3px;
	width: 820px;
	margin: 30px auto;
}
</style>
</html>