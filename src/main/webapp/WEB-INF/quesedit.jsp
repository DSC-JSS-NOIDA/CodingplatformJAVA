<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edit question</title>
</head>
<body>
	<form name=qedit action=modifyque method=post
		enctype='multipart/form-data'>
		ques id <input type=text value="${qid}${norec}" name=id readonly>
		<br> title<input type=text name=title value="${title}"> <br>
		details<input type=text name=det value="${detail}"><br>
		contraints <input type=text name=cons value="${constraints}"><br>
		input case<input type=text name=inpcase value="${inputcase}"><br>
		output case<input type=text name=opcase value="${outputcase}"><br>
		the file path<input type=text name=path value="${path}" readonly><br>
		add testcase file<input type=file name=file><br> <input
			type=submit>
	</form>
	<br>

	<h1>${rec }</h1>

</body>
</html>