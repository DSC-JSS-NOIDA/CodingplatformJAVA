<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>admin pannel</title>
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
<option value="6">edit ques</option>
<option value="7">view all ques</option>
<option value="8">edit submision</option>
<option value="9">view submission</option>
</select>

<div id=task_form>
</div>
<div hidden id=adduser>
<h1>add user</h1>
<form name=userctrl action=adduser method=post>
enter new user email<input type=text name=email><br>
enter name<input type=text name=name><br>
enter admno<input type=text name=admno><br>
enter year<input type=text name=year><br>
enter branch<input type=text name=branch><br>
<input type=submit>

</form>
</div>
<div hidden id="edituser">
<h1>edit user</h1>
<form name=userctrl action=edituser method=post>

enter admno<input type=text name=admno><br>

<input type=submit>

</form>
</div>
<div hidden id="edituser">
<h1>delete user</h1>
<form name=userctrl action=deluser method=post>

enter email<input type=text name=email><br>

<input type=submit>

</form>
</div>
</body>
</html>