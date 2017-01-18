<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

function log(){
	 var x = document.getElementById("names").value;
	 var y = document.getElementById("passs").value;
	    if (x == "admin" & y=="gdg") {
	        
	document.getElementById("login_form").innerHTML =document.getElementById("detail_form").innerHTML;
	    }
	    else{
	    	alert("invalid user");
	    	return false;
	    }
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<div id="login_form">
<form  name="log_form" >
enter ur name<input type=text name="name" id=names>
<br>
enter your password<input type=password name="pass" id=passs>
<br>
<input type=button value=submit onclick="log()"    >
</form>
</div>
<br>
<div id="detail_form" style="display:none">
<form name=frm action="questions" method=post>
enter question title<input type=text name=title><br>
enter ques detail<textarea name=detail>
</textarea><br>
enter constraints<textarea name=const>

</textarea><br>
enter input format<textarea name=inp_format>

</textarea><br>
enter sample test case<textarea name=t4>
</textarea><br><br>
enter input test case<input type=file><br><br>
enter output test case<input type=file><br><br>
<input type=submit>
</form>
</div>
</body>
</html>