<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
  String state = (String) session.getAttribute("reservation");
  if (state == null) {
    response.sendRedirect("showFreeCars.jsp");
  } else if (!state.equals("failed")){
    response.sendRedirect("showFreeCars.jsp");
  } else {
    session.removeAttribute("reservation");
  }
%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>
<h2>The Reservation is failed!</h2>
    <h3>Please Try again to enter your data!</h3>
    <a href="showFreeCars.jsp">Return into Cars.</a> 
   <%-- <a href="index.jsp">Return into Cars.</a>--%> 
</body>
</html>