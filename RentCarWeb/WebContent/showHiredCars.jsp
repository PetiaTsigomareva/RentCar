<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="bean.Rent"%>
<%@page import="tools.Escape"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hired Cars</title>
</head>
<body>
  <a href="index.jsp"> Show Free Cars </a>
	<%
	  List<Rent> rents = (List<Rent>) request.getAttribute("rents");
	  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy ");
	%>

	<%
	  if (rents.size() == 0) {
	%>
	<h2>No Hire Cars!</h2>
	<%
	  } else {
	%>

	<h2>Hired Cars</h2>

	<table border="3" cellpadding="5">
		<tr>
			<td>Producer</td>
			<td>Modification</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>From Date</td>
			<td>To Date</td>
			<td>Cencel Reservation</td>
		</tr>
		<%
		  for (Rent rent : rents) {
		%>
		<tr>
			<form method="POST" action="CancelRentServlet">
				<td><%=Escape.htmlEscape(rent.getCar().getProducer())%></td>
				<td><%=Escape.htmlEscape(rent.getCar().getModification())%></td>
				<td><%=Escape.htmlEscape(rent.getRenter().getFirstName())%></td>
				<td><%=Escape.htmlEscape(rent.getRenter().getLastName())%></td>
				<td><%=sdf.format(rent.getFromDate())%></td>
				<td><%=sdf.format(rent.getToDate())%></td>
				<td><input type="submit" value="Cencel Reservation"></td> <input
					type="hidden" name="rentId" value="<%=rent.getId()%>">
			</form>
		</tr>
		<%
		  }
		%>
	</table>
	<%
	  }
	%>

</body>
</html>