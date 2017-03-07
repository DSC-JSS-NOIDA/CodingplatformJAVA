<%@ page import="java.io.*,java.util.*,java.sql.*"%>  
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
  
<html>  
<head>  
<title>sql:query Tag</title>  
</head>  
<body>  
   

   
<table border="2" width="100%">  
<tr>  
<th><a href="#">LEADERBOARD</a></th>
</tr>  
<c:forEach var="User" items="${user}">  
<tr>  
<td><c:out value="${User.team_name}"/></td>
<td><c:out value="${User.score}"/></td>   
</tr>  
</c:forEach>  
</table>  
  
</body>  
</html>  