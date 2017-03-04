
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
      <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection" />
     
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
     
      <link rel="shortcut icon" type="image/png" href="assest/images/favicon.png">
      <script>
function myFunction() {
    document.getElementById("display").innerHTML =document.getElementById("login_form").innerHTML;
    }   
     
    function myFunction2() {
        document.getElementById("display").innerHTML =document.getElementById("signup_form").innerHTML;
       
}
    
    
</script>

<meta charset="ISO-8859-1">
<title>index</title>
</head>
<body background="#"><font color=white><center>
<h1>GDG JSS NOIDA</h1></center></font>


<div style="margin-left:4cm;margin-bottom:1cm;valign:middle;align:left;" id="display">

 <button class="btn waves-effect waves-light indigo accent-4 centre-align hoverable" type="button" name="action" onclick="myFunction()">CLICK HERE TO CONTINUE

</div>
<div style="display:none" id="login_form"  margin-bottom:"3cm">
<h1>LOGIN </h1>
<form action=login method=post>
Enter emailid :<input type=text  name=email required><br><br> 
Enter contact no :<input type=password name=contact required><br>
<input type=submit><a href="#" onclick="myFunction2()"><font color=red>Not Registered. Click Here</font></a>

</form></div> 
<div style="display:none" id=signup_form>

<h1>SIGN UP</h1>
<form action=signup method="post" >
Enter team name<input type=text name=team_name required><br><br> 
Enter 1 participant name<input type=text name=name_1 required><br><br> 
Enter 1 participant roll number<input type=text name=roll_1 required><br><br> 
Enter 2 participant name<input type=text name=name_2 required><br><br> 
Enter 2 participant roll number<input type=text name=roll_2 required><br><br> 
Enter Email Id:<input type=text name=emailid required><br><br>
Enter contact number:<input type=password name=contact required ><br><br> 

<input type=submit value="submit" onclick="myFunction3()" >
</div>
</body>
</html>