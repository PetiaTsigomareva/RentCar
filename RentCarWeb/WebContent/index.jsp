<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RentCarWeb Home</title>
</head>
<body>
	<h2>Please enter dates for viewing free cars.</h2>

	<%--
	  Object fromDateObj = session.getAttribute("fromDate");
	  Object toDateObj = session.getAttribute("toDate");
	  String fromDate = fromDateObj.toString();
	  String toDate = toDateObj.toString();
	  
	 
	--%>


	<form method="Get" action="FreeCarsServlet">

		From Date [dd.MM.YYYY HH:mm] <input type="text" name="fromDate"
			onkeyup="changeResButton()" /> To Date [dd.MM.YYYY HH:mm] <input
			type="text" name="toDate" onkeyup="changeResButton()" /> <input
			type="submit" value="Enter" />

	</form>
	<%-- jsp:forward page="/FreeCarsServlet" /--%>
</body>
</html>