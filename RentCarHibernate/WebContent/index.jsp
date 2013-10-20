<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="javax.servlet.RequestDispatcher"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>RentCarHibernateHome</title>
</head>
<body>
  <%-- --%>jsp:forward page="/FreeCarsServlet" /-->
	<%-- response.sendRedirect("/FreeCarsServlet"); --%>
	<%-- request.getContextPath()--%>
  <%-- jsp:forward  page="FreeCarsServlet"/--%> 

	<%--
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/FreeCarsServlet");
		dispatcher.include(request, response);
	--%>
</body>
</html>