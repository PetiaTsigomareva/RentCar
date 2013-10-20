<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Successful</title>
  </head>
  <body>
    <h2>The Reservation is successful!</h2>
  <a href="showFreeCars.jsp">Return into Cars.</a> 
    <%-- <a href="index.jsp">Return into Cars.</a>--%> 
  </body>
</html>