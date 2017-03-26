<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="js/jquery.js"></script>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="css/materialize.min.css">
 
<!-- Compiled and minified JavaScript -->
<script src="js/materialize.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Result</title>

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
			<br>
			<h1>YOUR SUBMISSION</h1>
			<input type="hidden" name="source" class="source" value=""
				id="source">
				

			<div id="editor_lang">

				<textarea id="editor2" name="code">${code}</textarea>
				

			</div>
			<!-- load ace -->
			<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js"
				type="text/javascript" charset="utf-8"></script>

			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
			<script>
				// trigger extension

				

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


			<input type=hidden value="${quesid}" name=qid> <br>
			<div>
				<h1>RESULT STATUS</h1>

				<h6 style="color:${colour};">${status}</h6>
				<h6>${stdout}</h6>
				<h6>${verify}</h6>
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
<style type="text/css" media="screen">
#editor {
	
}

#editor_select {
	background-color: #141414;
	color: white; px;
	border: 0px;
}

#editor_lang {
	background-color: #141414;
	border-radius: 4px;
}

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

.language {
	background: rgba(255, 255, 255, .7);
	width: 150px;
}
</style>
</html>
