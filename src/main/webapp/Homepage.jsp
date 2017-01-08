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
var profile="";
function onSignIn(googleUser) {
  profile = googleUser.getBasicProfile();
  console.log(profile);
  console.log('ID: ' + profile.getId());
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail());
  $.ajax({
		type: 'POST',
		headers: {
 			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
		url: 'login',
		data: {
			'auth_token':googleUser.getAuthResponse().id_token
		} /* ,
		success: function(){
			console.log("yes");
			alert("done");
			redirect: true,
			  redirectURL = "/hello" 
				
		}  */
	});
 
}
</script>
<body>
<div class="g-signin2" data-onsuccess="onSignIn"></div>

<a href="#" onclick="signOut();">Sign out</a>
<script>
  function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
    	alert('signed out success');
      console.log('User signed out.');
    });
  }
</script>
</body>
</html>