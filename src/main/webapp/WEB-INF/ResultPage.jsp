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

<title>quespage</title>
<style type="text/css" media="screen">
#editor {
	margin: 0;
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
}

#editor_select {
	background-color: #141414;
	color: white;
	border: 0px;
}

#editor_lang {
	background-color: #141414;
	border-radius: 4px;
}
</style>
</head>
<body>
	<div align=right>
		<nav>


			<a href=#>leader board</a> <a href=#>logout</a>
		</nav>
	</div>

	<br>
	<h1>YOUR SUBMISSION</h1>
	<input type="hidden" name="source" class="source" value="" id="source">
	<br>

	<div id="editor_lang">

		<select class="language" id="editor_select" name="editor_lang">
			<option value="">Select</option>
			<option value="c_cpp">C</option>
			<option value="c_cpp">C++</option>
			<option value="java">Java</option>
			<option value="python">Python</option>
		</select>



		<textarea id="editor2" name="code">${code}</textarea>


	</div>
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


	<input type=hidden value="${quesid}" name=qid>
	<br>
	<div>
		<h1>RESULT STATUS</h1>


		<h5>message=${message}</h5>
		<h5>status=${status}</h5>
		<h5>stdout=${stdout}</h5>
		<h5>${verify}</h5>

	</div>

	</form>
</body>
</html>
