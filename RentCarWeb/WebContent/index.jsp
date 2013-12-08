<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RentCarWeb Home</title>
</head>
<body>
	<header>
	<h1>THIS IS A HEADER</h1>
	</header>
	<%
	  String fromDate = (String) session.getAttribute("fromDate");
	  String toDate = (String) session.getAttribute("toDate");

	  if (fromDate == null) {
	    fromDate = "";
	  }

	  if (toDate == null) {
	    toDate = "";
	  }
	%>
	<h2>Please enter dates for viewing free cars.</h2>
	<form method="Get" action="FreeCarsServlet">
		<table>
			<tr>
				<td>From Date [dd.MM.YYYY HH:mm]</td>
				<td><input type="text" name="fromDate" value="<%=fromDate%>" /></td>
			</tr>
			<tr>
				<td>To Date [dd.MM.YYYY HH:mm]</td>
				<td><input type="text" name="toDate" value="<%=fromDate%>" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Enter" /></td>
			</tr>
		</table>
	</form>
</body>
</html>