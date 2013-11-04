package servlet;

import static htmlEscape.Escape.htmlEscape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import session.SessionManager;
import bean.Car;
import bean.Rent;
import bean.Renter;

/**
 * Servlet implementation class StoreRentServlet
 */

// @WebServlet ("/StoreRentServlet")
public class StoreRentServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public StoreRentServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    List<String> validationErrorsList = new ArrayList<String>();
    Session hbSession;

    String carIdStr = request.getParameter("carId");
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");

    String firstName = htmlEscape(request.getParameter("firstName"));
    if (!isValidField("[A-ZÀ-ß][a-zà-ÿ]+", firstName)) {
      validationErrorsList.add("The value of the field First Name:<b>" + firstName
          + "</b> is in incorrect formmat. The first name must start with capital letter, includes only letters and be at least 2 leters long ");
    }

    // TODO: Review the usage of the htmlEscape() method
    String lastName = htmlEscape(request.getParameter("lastName"));
    // TODO: Add validation

    String egn = htmlEscape(request.getParameter("egn"));
    // TODO: Add validation

    String address = htmlEscape(request.getParameter("address"));
    // TODO: Add validation

    String cardNumber = htmlEscape(request.getParameter("cardNumber"));
    // TODO: Add validation

    if (!validationErrorsList.isEmpty()) { // validation errors occurs
      session.setAttribute("validationErrorsList", validationErrorsList);
      // response.sendRedirect("validationErrors.jsp");
      request.getRequestDispatcher("validationErrors.jsp").forward(request, response);
    } else {
      try {
        hbSession = SessionManager.openSession();

        long carId = Long.parseLong(carIdStr);
        Car car = Car.getCarByPrimeryKey(hbSession, carId);

        Renter renter = new Renter(firstName, lastName, egn, cardNumber, address);
        renter.store(hbSession);

        Rent rent = new Rent(car, renter, new Date());
        rent.store(hbSession);

        SessionManager.commitTransaction();

        response.sendRedirect("reservationConfirmation.jsp");
      } catch (HibernateException e) {
        SessionManager.rollbackTransaction();

        session.setAttribute("reservationError", e.getMessage());
        response.sendRedirect("reservationError.jsp");
      } finally {
        SessionManager.closeSession();
      }
    }
  }

  /**
   * The method return true in case the fieldName parameter matches the regex
   * provided in the regex parameter
   * 
   * @param regex
   *          non-null valid regex to match upon
   * @param fieldName
   *          non-null field to match
   * @return true in case the fieldName parameter matches the regex provided in
   *         the regex parameter
   */
  private boolean isValidField(String regex, String fieldName) {
    boolean result;

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(fieldName);

    result = matcher.matches();

    return result;
  }
}
