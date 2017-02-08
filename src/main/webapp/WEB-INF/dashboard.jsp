<html>
<head>
<!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
     
<meta name="google-signin-client_id" content="30081514308-nsdkis6qpuda4f9vr2mb2d3aeg0otqgq.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
       
     
<script>
function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId());
  // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail());
  
  
 
}
</script>
<script>
  function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
      window.location="index.jsp";
    });
  }
</script>
  
</head>
<body>
<!--Import jQuery before materialize.js-->
    
    
<nav>
    <div >
      <a href="#" class="brand-logo">Dashboard</a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
      
        <li><a href="about.jsp">${name }</a></li>
        <li><a href="contact.jsp">contact</a></li>
        <li ><img src="${avatar}" width="40" height="40"  class="circle responsive-img" valign="middle" onclick="signOut()"  ></li>
      
      <li> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</li>
        
      </ul>
    </div>
  </nav>
  </body>
  </html>