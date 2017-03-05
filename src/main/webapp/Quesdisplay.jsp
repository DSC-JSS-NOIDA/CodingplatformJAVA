<%@ page import="java.io.*,java.util.*,java.sql.*"%>  
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
  
<html>  
<head>  
<title>sql:query Tag</title>  
</head>  
<body>  
   
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/codingplatform"  
     user="root"  password="Divy1996@" />
<sql:query dataSource="${db}" var="rs">  
SELECT * from questions;  
</sql:query>  
   
<table border="2" width="100%">  
<tr>  
<th><a href="#">TITLES</a></th>
</tr>  
<c:forEach var="question" items="${rs.rows}">  
<tr>  
<td><a href="#"><c:out value="${question.title}"/></a></td>   
</tr>  
</c:forEach>  
</table>  

</body>  
</html>  

