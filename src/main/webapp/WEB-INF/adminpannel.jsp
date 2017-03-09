<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://cdn.ckeditor.com/4.6.2/standard/ckeditor.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>admin pannel</title>

</head>
<body>
welcome ${name}<br>

<select name=opt onchange="task_func()" id=frm_task value="select">
		<option selected="selected">select</option>
		<option value="0">add user</option>
		<option value="1">edit user</option>
		<option value="2">delete user</option>
		<option value="3">view all user</option>
		<option value="4">add ques</option>
		<option value="5">del ques</option>
		<option value="6">edit ques</option	>
		<option value="7">view all ques</option>
		<option value="8">edit submision</option>
		<option value="9">view submission</option>
</select>
<br>


ADD QUESTIONS
 
<div>
         <form name=frm action="addques" method="post">
         
          <h2>enter question id:<input type="text" name="quesid"></h2>
          <h2>enter title<input type="text" name=title></h2>
          <h2>enter detail</h2><textarea name="detail"></textarea><br>
                   
          <h2>enter constraints</h2><textarea name="constraints"></textarea><br>
         <h2> enter input  format</h2><textarea name="inputformat"></textarea><br> 
          <h2>enter output format  format</h2><textarea name="outputformat"></textarea><br>
          <h2>enter sampletestcase</h2><textarea name="sampletestcase"></textarea><br>
          <h2>enter out file path<input type="text" name="outputfilepath"></h2>
          <h2>enter input  file path<input type="text" name="inputfilepath"></h2>
          
         <input type="submit">
          </form>
</div>



</body>
<script>
function task_func(){
	var task=document.getElementById("frm_task").value;
console.log(task);
switch(task)
{
case "0":
	console.log("adduser");
	document.getElementById("task_form").innerHTML=document.getElementById("adduser").innerHTML;
	break;
case "1":
	console.log("edituser");
	document.getElementById("task_form").innerHTML=document.getElementById("edituser").innerHTML;
	break;
case "2":
	console.log("deluser");
	document.getElementById("task_form").innerHTML=document.getElementById("deluser").innerHTML;
	break;
case "3":
	console.log("adduser");
	document.getElementById("task_form").innerHTML=document.getElementById("user").innerHTML;
	break;
case "4":
	console.log("adduser");
	document.getElementById("task_form").innerHTML=document.getElementById("user").innerHTML;
	break;
case "5":
	console.log("adduser");
	document.getElementById("task_form").innerHTML=document.getElementById("user").innerHTML;
	break;
case "6":
	console.log("adduser");
	document.getElementById("task_form").innerHTML=document.getElementById("user").innerHTML;
	break;
case "7":
	console.log("adduser");
	document.getElementById("task_form").innerHTML=document.getElementById("user").innerHTML;
	break;
case "8":
	console.log("adduser");
	document.getElementById("task_form").innerHTML=document.getElementById("user").innerHTML;
	break;
case "9":
	console.log("adduser");
	document.getElementById("task_form").innerHTML=document.getElementById("user").innerHTML;
	break;
default:
	console.log("default");

}
}
</script>

 <script>
          CKEDITOR.replace( 'detail', {
  			width: '70%',
  			height: 50
  		} );
            
        </script>
        <script>
          CKEDITOR.replace( 'constraints', {
  			width: '70%',
  			height: 50
  		} );
          </script>
          <script>
          CKEDITOR.replace( 'inputformat', {
    			width: '70%',
    			height: 50
    		} );
            </script>
            <script>
           
          CKEDITOR.replace( 'outputformat', {
    			width: '70%',
    			height: 50
    		} );
            </script>
         <script> 
          CKEDITOR.replace( 'sampletestcase', {
    			width: '70%',
    			height: 50
    		} );
            
        </script> 
</html>