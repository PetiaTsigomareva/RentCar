<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@page import="org.hibernate.Session"%>
<%@page import="session.SessionManager"%>
<%@page import="bean.Car"%>
<%@ page import="htmlEscape.EscapeHtml" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Reservation</title>
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
  Reservation on the <%= EscapeHtml.htmlEscape(car.getProducer() + " " + car. getModification()) %>
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
</body>
      


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