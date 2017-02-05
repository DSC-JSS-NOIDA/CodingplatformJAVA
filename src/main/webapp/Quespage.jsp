<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>quespage</title>
  <style type="text/css" media="screen">
    body {
        overflow: hidden;
    }
    
    #editor { 
        margin: 0;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }
  </style>
</head>
<body>
<div align=right>
<nav>
<a href="#">ques page</a>

<a href=#>successful submission</a>
<a href=#>logout</a>
</nav>
</div>
<br>
select ques no
<form name=frm action="#" >
<input type="submit" value="1" style="height:0.8cm;width:0.8cm" name=b1>
<input type="button" value="2" style="height:0.8cm;width:0.8cm" name=b2>
<input type="button" value="3" style="height:0.8cm;width:0.8cm" name=b3>
<input type="button" value="4" style="height:0.8cm;width:0.8cm" name=b4><br>
<input type="button" value="5" style="height:0.8cm;width:0.8cm" name=b5>
<input type="button" value="6" style="height:0.8cm;width:0.8cm" name=b6>
<input type="button" value="7" style="height:0.8cm;width:0.8cm" name=b7>
<input type="button" value="8" style="height:0.8cm;width:0.8cm" name=b8><br>
</form>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
    Question :  <br /><br />
    </div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	Constraint 
	</div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	Input Format <br /><br />
	</div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	Output Format<br /><br />
	</div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	Sample TestCase <br /><br />
	</div>
</div>
<br>
<form method="post" action="submission" >


 <select class="language" name=lang>
  <option value="Select">Select</option>
  <option value="C">C</option>
  <option value="C++">C++</option>
  <option value="Java">Java</option>
  <option value="Python">Python</option>
</select> <br>
<br>

<br>
<br>


<textarea id="editor2" name="code"></textarea>


<!-- load ace -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js" type="text/javascript" charset="utf-8"></script>
<script>
    // trigger extension
    

 

    var editor2 = ace.edit("editor2");
    editor2.setTheme("ace/theme/tomorrow_night_blue"); 
    editor2.session.setMode("ace/mode/html");
    editor2.setAutoScrollEditorIntoView(true);
    editor2.setOption("maxLines", 16);
    editor2.setOption("minLines", 8);

    
    
</script>

    



<input type="submit"  >
<script src="resources/src/show_own_source.js "></script>

</form>
</body>
</html>
    