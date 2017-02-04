<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>admin pannel</title>
</head>
<body>
welcome ${name}
<form name=func action="#">
<select name=opt onclick="task_func()">
<option value="adduser">add user</option>
<option value="edituser">edit user</option>
<option value="deluser">delete user</option>
<option value="viewuser">view all user</option>
<option value="addques">add ques</option>
<option value="delques">del ques</option>
<option value="editques">edit ques</option>
<option value="allques">view all ques</option>
<option value="editsub">edit submision</option>
<option value="viewsub">view submission</option>
</select>
</form>
<div id=task>
</div>
<div hidden id=user>
<form name=userctrl action=adduser method=post>
enter new user email<input type=text name=email><br>
enter name<input type=text name=name><br>
enter admno<input type=text name=admno><br>
enter year<input type=text name=year><br>
enter branch<input type=text name=branch><br>
<input type=submit>

</form>
</div>
</body>
</html>