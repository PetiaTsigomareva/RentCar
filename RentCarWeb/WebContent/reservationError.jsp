<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
  String errorMessage = (String) session.getAttribute("reservationError");
%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Error Page</title>
</head>
<body>
	<h2>The Reservation is failed because of the following error:</h2>
	<%=errorMessage%>

	<br />
	<a href="index.jsp">Return into Cars</a>
</body>
</html>