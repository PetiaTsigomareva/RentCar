<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="tools.Escape"%>
<%@page import="bean.Car"%>
<%@page import="org.hibernate.Session"%>
<%@page import="session.SessionManager"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter Rent Information.</title>
</head>
<body>
	<form method="POST" action="StoreRentServlet">
		<table border="3" bordercolor="red">
			<tr>
				<td>Name</td>
				<td><input type="text" name="firstName"
					onkeyup="changeResButton()"></td>
			</tr>

			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName"
					onkeyup="changeResButton()"></td>
			</tr>

			<tr>
				<td>EGN</td>
				<td><input type="text" name="egn" onkeyup="changeResButton()"></td>
			</tr>

			<tr>
				<td>Address</td>
				<td><input type="text" name="address"
					onkeyup="changeResButton()"></td>
			</tr>

			<tr>
				<td>Number of identity card</td>
				<td><input type="text" name="cardNumber"
					onkeyup="changeResButton()"></td>
			</tr>

			<tr>
				<td>From Date, [dd.MM.YYYY HH:mm]</td>
				<td><input type="text" name="fromDate"
					onkeyup="changeResButton()"></td>
			</tr>
			<tr>
				<td>To Date, [dd.MM.YYYY HH:mm]</td>
				<td><input type="text" name="toDate"
					onkeyup="changeResButton()"></td>
			</tr>
		</table>
		<input type="submit" id="submitBtn" value="Reservation" /> <input
			type="hidden" name="carId" value="<%=request.getParameter("carId")%>" />
	</form>
</body>
</html>