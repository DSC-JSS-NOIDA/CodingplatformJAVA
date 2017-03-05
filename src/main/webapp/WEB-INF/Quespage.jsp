<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
<a href="#">ques page</a>

<a href=#>successful submission</a>
<a href=#>logout</a>
</nav>
</div>
<br>

<p>${TeamName}
<div class="row">
	<div class="col-md-10 col-md-offset-1">
    Question :<input type=text values=${Question}/>  <br /><br />
    </div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	Constraint :<input type=text values=${Constraint}/> 
	</div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	Input Format:<input type=text values=${InputFormat}/> <br /><br />
	</div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	Output Format<br /><br />
	</div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	Sample Test Case:<input type=text values=${SampleTestCase}/> <br /><br />
	</div>
</div>
<br>
CHOOSE LANGUAGE
<form method="post" action="api" >
	 	<select class="language" name=lang >
  			<option value="Select">Select</option>
  			<option value="1">C</option>
  			<option value="2">C++</option>
  			<option value="3">Java</option>
  			<option value="5">Python</option>
		</select>
 <br>
<textarea name="source" class="source" value="" id="source" hidden/></textarea><br>
				
				<div id="editor_lang"> 

                <select class="language" id="editor_select" name="editor_lang">
                    <option value="">Select</option>
                    <option value="c_cpp">C</option>
                    <option value="c_cpp">C++</option>
                    <option value="java">Java</option>
                    <option value="python">Python</option>
                </select>



<textarea id="editor2" name="code"></textarea> 

 </div>
<!-- load ace -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js" type="text/javascript" charset="utf-8"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
<script>
    // trigger extension
     

 var lang = "c_cpp";
        $(document).ready(function(){
            $("#editor_select").change(function(){
              $("#editor_select option:selected").each(function(){
                lang=$(this).attr('value');
                console.log(lang);
                editor2.session.setMode("ace/mode/"+lang);
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
    editor2.getSession().on("change", function () {
        $("#source").val(editor2.getSession().getValue());
      });
     

    
    
</script>

    
<input type=hidden value="${qid}" name=qid><br>


<input type="submit"  >


</form>
</body>
</html>
    