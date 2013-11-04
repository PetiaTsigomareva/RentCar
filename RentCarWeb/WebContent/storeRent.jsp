<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import= "htmlEscape.Escape" %>
<%@page import= "bean.Car" %>
<%@page import="org.hibernate.Session"%>
<%@page import="session.SessionManager"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Enter Rent Information.</title>
</head>
<body>
<%
  String carIdStr = request.getParameter("carId");
   if (carIdStr == null) {
   response.sendRedirect("showFreeCars.jsp");
   } else {
    Session hbSession = null;
    Long carId = Long.parseLong(carIdStr);
    try {
     hbSession = SessionManager.openSession();
     Car car= Car.getCarByPrimeryKey(hbSession, carId);
     response.setCharacterEncoding("utf-8"); 
     session.setAttribute("carId",carId);
%>
  Reservation on the <%= Escape.htmlEscape(car.getProducer() + " " + car. getModification())%>
   <form method="POST" action="StoreRentServlet">
  <table border="3" bordercolor="red">
     <tr>
        <td>Name:</td>
        <td><input type="text" name="firstName"  onkeyup="changeResButton()"></td>
     </tr>
     
     <tr> 
        <td>Last Name:  </td>
        <td><input type="text" name="lastName" onkeyup="changeResButton()"></td>
     </tr>
     
     <tr>
        <td>EGN:</td>
        <td><input type="text" name="egn" onkeyup="changeResButton()"></td>
     </tr>
     
      <tr>
        <td>Address:</td>
        <td><input type="text" name="address" onkeyup="changeResButton()"></td>
      </tr>
     
     <tr>
        <td>Number of identity card :</td>
        <td><input type="text" name="cardNumber" onkeyup="changeResButton()"></td>
     </tr>
    </table>
    <input type="submit" id="submitBtn" value="Reservation" />
    <input type="hidden" name="carId" value="<%=carId%>"/> 
    <input type="hidden" name="addRent" value="add"/>
  </form>
  <%
    } finally {
      if (hbSession != null) {
        SessionManager.closeSession();
      }
    }
  }
%>
</body>
</html>