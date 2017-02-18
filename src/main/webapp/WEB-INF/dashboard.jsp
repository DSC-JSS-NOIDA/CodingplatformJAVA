<html>
<head>
<!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
     
<meta name="google-signin-client_id" content="30081514308-nsdkis6qpuda4f9vr2mb2d3aeg0otqgq.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
       <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
       <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
     
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
      window.location="Homepage.jsp";
    });
  }
</script>
  
</head>
<body>
<!--Import jQuery before materialize.js-->
      <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>
    
<nav>
    <div class="nav-wrapper">
      <a href="#" class="brand-logo">Dashboard</a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
      
        <li><a href="about.jsp">${name }</a></li>
        <li><a href="contact.jsp">contact</a></li>
        <li ><img src="${avatar}" width="40" height="40"  class="circle responsive-img" valign="middle" onclick="signOut()"  ></li>
      
      <li> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</li>
        
      </ul>
    </div>
  </nav>
  <br>
  <h1>questions</h1>
  <br>
  <table>
  <tr>
  <th>id</th>
  <th>title</th>
</tr>
<tr>
<td>${id}</td>
<td><a href="/ques?=${id}">${title}</a>
</td>
</tr>
  </table>
  </body>
  </html>