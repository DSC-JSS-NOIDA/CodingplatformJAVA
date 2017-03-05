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
<th><a href="#">TITLES</a></th>
</tr>  
<c:forEach var="question" items="${ques}">  
<tr>  
<td><a href="ques?=${question.quesid}"><c:out value="${question.title}"/></a></td>   
</tr>  
</c:forEach>  
</table>  
  
</body>  
</html>  