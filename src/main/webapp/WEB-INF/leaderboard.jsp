<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
  
<html>  
<head>  
<title></title>  
</head>  
<body>  
   

<h1>LEADERBOARD</h1>   
<%
		int i = 1;
	%>
<table border="2" width="100%">  
<tr>  
<th><span>RANK</span></th>
<th><span>TEAM</span></th>

<th><span>Ques 1 C/C++</span></th>
<th><span>Ques 1 JAVA</span></th>
<th><span>Ques 1 PYTHON</span></th>

<th><span>Ques 2 C/C++</span></th>
<th><span>Ques 2 JAVA</span></th>
<th><span>Ques 2 PYTHON</span></th>

<th><span>Ques 3 C/C++</span></th>
<th><span>Ques 3 JAVA</span></th>
<th><span>Ques 3 PYTHON</span></th>

<th><span>Ques 4 C/C++</span></th>
<th><span>Ques 4 JAVA</span></th>
<th><span>Ques 4 PYTHON</span></th>

<th><span>Ques 5 C/C++</span></th>
<th><span>Ques 5 JAVA</span></th>
<th><span>Ques 5 PYTHON</span></th>

<th><span>TOTAL SCORE</span></th>

</tr>  

<c:forEach var="result_row" items="${resultRows}">  
<tr>  
<td> <%=i%> </td>
<td><c:out value="${result_row.team}"/></td>

<td><c:out value="${result_row.ques1_C}"/></td>
<td><c:out value="${result_row.ques1_JAVA}"/></td>
<td><c:out value="${result_row.ques1_PYTHON}"/></td>

<td><c:out value="${result_row.ques2_C}"/></td>
<td><c:out value="${result_row.ques2_JAVA}"/></td>
<td><c:out value="${result_row.ques2_PYTHON}"/></td>

<td><c:out value="${result_row.ques3_C}"/></td>
<td><c:out value="${result_row.ques3_JAVA}"/></td>
<td><c:out value="${result_row.ques3_PYTHON}"/></td>

<td><c:out value="${result_row.ques4_C}"/></td>
<td><c:out value="${result_row.ques4_JAVA}"/></td>
<td><c:out value="${result_row.ques4_PYTHON}"/></td>


<td><c:out value="${result_row.ques5_C}"/></td>
<td><c:out value="${result_row.ques5_JAVA}"/></td>
<td><c:out value="${result_row.ques5_PYTHON}"/></td>

<td><c:out value="${result_row.total}"/></td>   
</tr>  
<%
		 i = i+1;
	%>

</c:forEach>  
</table>  
  
</body>  
</html>  