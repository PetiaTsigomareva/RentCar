                                                                               
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="bean.Car"%>
<%@page import="tools.Escape"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Free Cars</title>
</head>
<body>
	<h1>Welcome to Fast&Furious Rent a Car</h1>
	<a href="/RentCarWeb/HiredCarsServlet"> Show Hired Cars </a>
	<%
	  List<Car> cars = (List<Car>) request.getAttribute("cars");
	  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	%>

	<%
	  if (cars.size() == 0) {
	%>
	<h2>No Free Cars!</h2>
	<%
	  } else {
	%>
	<h2>Free Cars</h2>


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
		<tr>
			<form method="POST" action="storeRent.jsp">
				<td><%=Escape.htmlEscape(car.getProducer())%></td>
				<td><%=Escape.htmlEscape(car.getModification())%></td>
				<td><%=sdf.format(car.getManifactureDate())%></td>
				<td><%=Escape.htmlEscape(car.getColor())%></td>
				<td><%=car.getPriceForDay()%></td>
		    <input type="hidden" name="carId" value="<%=car.getId()%>">
				<td><input type="submit" value="Reservation"></td>
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