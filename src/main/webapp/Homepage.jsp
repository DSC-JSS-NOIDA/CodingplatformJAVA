
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="google-signin-client_id" content="30081514308-nsdkis6qpuda4f9vr2mb2d3aeg0otqgq.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>

<div id=loginform >
	<div class="g-signin2" data-onsuccess="onSignIn"></div>
</div>
<a href="#" onclick="signOut();">Sign out</a>

<br>
<div id=detailform hidden>
<h1>ENTER YOUR DETAILS</h1>
<form action="login" method="post" >
Enter Admission no:<input type=text name=admno><br><br> 
Enter YEAR:<input type=text name=year id="p1"  ><br>
<br>
ENTER BRANCH:<input type=text name=branch id="p2" ><br>
<input type=text name=auth_token id="tokenbox" hidden ><br>
<input type=submit value="submit"><br>
</div>

<div >
<form id="submitform" action="login" method="post">
<input type=text name=email  id="email">
<input type="submit" value="Submit" name="Submit" />
</form>
</div>




<script>
var tokenid,profile;
function onSignIn(googleUser) {
   tokenid = googleUser.getAuthResponse().id_token;
   profile = googleUser.getBasicProfile();
  $.ajax({
	   		type: 'POST',
	    	url: 'loginverifier',
	    	dataType: "text",
	    	async: false, /*so that request is not send before google provides email of user  */
	    	data: { 'user_email':profile.getEmail()},
	   		success: function(data){
	   					console.log(data);
	   					if(data=="new_user")
	   						{
	   			       		document.getElementById("loginform").innerHTML =document.getElementById("detailform").innerHTML;
	   	  	            	var tokenbox = document.getElementById('tokenbox');
	   	  	            	tokenbox.value=tokenid;
	   						}
	   					else
	   						{
	   						var emailid=document.getElementById("email");
	   						emailid.value=profile.getEmail();
	   						console.log(emailid.value);
	   						document.getElementById("submitform").submit();
	   						}
	   						
                        },
	    	error: function(){ 
	    	} 	
	   		}); 
}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
</script>
</body>



</html>