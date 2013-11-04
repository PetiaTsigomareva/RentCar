<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RentCarWeb Home</title>
</head>
<body>

	<jsp:forward page="/FreeCarsServlet" />
	<%-- response.sendRedirect("showFreeCars.jsp"); --%>

	<%--request.getContextPath()--%>
	<%-- jsp:forward  page="FreeCarsServlet"--%>

	<%--
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/FreeCarsServlet");
		dispatcher.include(request, response);
	--%>

	<%--
	  response.sendRedirect("showFreeCars.jsp");
	--%>
</body>
</html>