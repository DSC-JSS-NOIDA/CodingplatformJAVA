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


<a href=#>leader board</a>
<a href=#>logout</a>
</nav>
</div>
<br>

<p><font size="4px">TEAM NAME</font>-${Teamname}</p>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
    <b>Question</b> :${Question}  <br /><br />
    </div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	<b>Constraint </b>:${Constraint}
	</div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	<b>Input Format</b>:${InputFormat} <br>
	</div>
</div>


<div class="row">
	<div class="col-md-10 col-md-offset-1">
	<b>Sample Test Case</b>:${SampleTestCase} 
	</div>
</div> 
<br>
CHOOSE LANGUAGE
<form method="post" action="api" >
	 	 <select class="language" name="lang" >
  			<option value="Select">Select</option>
  			<option value="C">C</option>
  			<option value="CPP">C++</option>
  			<option value="JAVA">Java</option>
  			<option value="PYTHON">Python</option>
		</select>
 <br>

<input type="hidden" name="source" class="source" value="" id="source"><br>
				
				<div id="editor_lang"> 

                <select class="language" id="editor_select" name="editor_lang">
                    <option value="">Select</option>
                    <option value="c_cpp">C</option>
                    <option value="c_cpp">C++</option>
                    <option value="java">Java</option>
                    <option value="python">Python</option>
                </select>



<textarea id="editor2" name="code" >${code}</textarea> 


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

    
<input type=hidden value="${quesid}" name=qid><br>

<input type="submit"  >

  <div>

</form>
</body>
</html>
    