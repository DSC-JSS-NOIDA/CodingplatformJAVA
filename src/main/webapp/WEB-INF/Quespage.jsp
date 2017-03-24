<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta charset="ISO-8859-1">
<title>Question</title>

</head>
<body>
	<nav>
		<div class="nav-wrapper blue darken-1">
			<a href="dashboard" class="brand-logo" style="margin-left: 30px;">{Code
				In Less}</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="#">Team : <span>${TeamName}</span></a></li>
				<li><a href="dashboard">Dashboard</a></li>
				<li><a href="rules">Rules</a></li>
				<li><a href="leaderboard">Leaderboard</a></li>
				<li><a href="logout">Log Out</a></li>
			</ul>
		</div>
	</nav>
	<div style="padding-left: 70px;" id="main">
		<p style="color: #0572d2;">
			<span style="font-size: 16px; font-weight: 500;">TEAM NAME : </span><span
				style="font-size: 14px;">${Teamname}</span>
		</p>
		<div id="ques" class="z-depth-1">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<b>Title</b> : ${Title} <br />
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<b>Question</b> : ${Question}
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<b>Input Format</b>:${InputFormat}
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<b>Output Format</b>:${OutputFormat}
				</div>
			</div>

			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<b>Constraint </b>:${Constraint}
				</div>
			</div>

			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<b>Sample Test Case</b>:${SampleTestCase}
				</div>
			</div>
			CHOOSE LANGUAGE
			<form method="post" action="api">
				<select class="browser-default language" name="lang">
					<option value="C">C</option>
					<option value="CPP">C++</option>
					<option value="JAVA">Java</option>
					<option value="PYTHON">Python</option>
				</select> <br> <input type="hidden" name="source" class="source"
					value="" id="source">

				<div id="editor_lang">
					<select class="language browser-default " id="editor_select"
						name="editor_lang">
						<option value="c_cpp">C</option>
						<option value="c_cpp">C++</option>
						<option value="java">Java</option>
						<option value="python">Python</option>
					</select>
					<textarea id="editor2" name="code">${code}</textarea>



				</div>
				<!-- load ace -->
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js"
					type="text/javascript" charset="utf-8"></script>

				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
				<script>
					// trigger extension

					var lang = "c_cpp";
					$(document)
							.ready(
									function() {
										$("#editor_select")
												.change(
														function() {
															$(
																	"#editor_select option:selected")
																	.each(
																			function() {
																				lang = $(
																						this)
																						.attr(
																								'value');
																				console
																						.log(lang);
																				editor2.session
																						.setMode("ace/mode/"
																								+ lang);
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


				<input type=hidden value="${quesid}" name=qid>
				<button type="submit" class="waves-effect waves-light btn blue">Submit</button>


			</form>
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
<style type="text/css" media="screen">
#editor {
	
}

#editor_select {
	background-color: #242424;
	color: white;
	border: 0px;
}

#editor_lang {
	background-color: #141414;
	border-radius: 4px;
}

#ques {
	background: rgba(255, 255, 255, .4);
	padding: 15px 25px 10px 25px;
	border-radius: 3px;
	width: 1000px;
}

.language {
	background: rgba(255, 255, 255, .7);
	width: 150px;
}

button {
	margin: 10px 0px 20px 0px;
}
</style>
</html>
