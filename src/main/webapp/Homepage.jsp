
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="google-signin-client_id" content="30081514308-nsdkis6qpuda4f9vr2mb2d3aeg0otqgq.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<script>
var tokenid;
function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId());
  // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail());
   tokenid = googleUser.getAuthResponse().id_token;
  console.log(tokenid);
  	    document.getElementById("demo").innerHTML =document.getElementById("demo1").innerHTML;
  	  var textbox3 = document.getElementById('textbox3');
  	 textbox3.value=tokenid;
  	document.getElementById("textbox3").innerHTML =document.getElementById("textbox3a").innerHTML;
}


</script>
<body>

<div id=demo  >
<div class="g-signin2" data-onsuccess="onSignIn"  ></div>
</div>


<script>
  function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
</script>
<br>
<div id=demo1 hidden>
<a href="#" onclick="signOut();">Sign out</a>
<h1>ENTER YOUR DETAILS</h1>
<form action="login" method="post" >
Enter Admission no:<input type=text name=admno  ><br><br> 

Enter YEAR:<input type=text name=year id="p1"  ><br>
<input type=text name=auth_token id="textbox3" hidden >
<br>
ENTER BRANCH:<input type=text name=branch id="p2" ><br><br><br>
<input type=submit value="submit"  ><br>

</div>
<input type=text name=auth_token id="textbox3a" hidden>
</body>



</html>