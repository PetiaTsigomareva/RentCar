<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
  List<String> validationErrorsList = (List<String>) session.getAttribute("validationErrorsList");
%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Validation Error Page</title>
</head>
<body>
	<h2>The Reservation is failed because following fields are
		recognized as invalid:</h2>
	<table>
		<%
		  for (String error : validationErrorsList) {
		%>
		<tr>
			<td><%=error%></td>
		</tr>
		<%
		  }
		%>
	</table>

	<br />
	<a href="index.jsp">Return to Free Cars</a>
</body>
</html>