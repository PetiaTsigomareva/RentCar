<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.util.List"%>
<%@page import="bean.Car"%>
<%@page import="htmlEscape.EscapeHtml"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Rent Cars</title>
</head>
<body>
	<%
		List<Car> cars = (List<Car>) request.getAttribute("cars");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
		Date date = new Date();
	%>
	<%
		if (cars.size() == 0) {
	%>
	<h2>No Free Cars!</h2>
	<%
		} else {
	%>
	<h2>Wellcome to the Tsigomarevi Rent Cars!  </h2>
	<h3>Free Cars </h3>


	<table border="3" cellpadding="5">
		<tr>
			<td>Producer</td>
			<td>Modification</td>
			<td>Manifacture Date</td>
			<td>Color</td>
			<td>Price For Day</td>
			<td>Reservation</td>
		</tr>


		<%
			for (Car car : cars) {
		%>
		<form method="POST" action="storeRent.jsp">
			<tr>
				<td><%=EscapeHtml.htmlEscape(car.getProducer())%></td>
				<td><%=EscapeHtml.htmlEscape(car.getModification())%></td>
				<td><%=sdf.format(car.getManifactureDate())%></td>
				<td><%=EscapeHtml.htmlEscape(car.getColor())%></td>
				<td><%=car.getPriceForDay()%></td>
				<td><input type="submit" value="Reservation"></td>
			</tr>
			<input type="hidden" name="carId" value="<%=car.getId()%>">
		</form>
		<%
			}
		%>
	</table>
	<%
		}
	%>
</body>
</html>