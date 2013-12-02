package servlet;

import static tools.Escape.htmlEscape;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import session.SessionManager;
import tools.Validation;
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
    // Logger logger;
    Date rent_Date = new Date();
    Date from_Date;
    Date to_Date;

    String carIdStr = request.getParameter("carId");
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    // TODO: Review the usage of the htmlEscape() method
    String firstName = htmlEscape(request.getParameter("firstName"));

    if (!Validation.isValidField("[A-ZÀ-ß][a-zà-ÿ]+", firstName)) {
      validationErrorsList.add("The value of the First Name field :<b>" + firstName
          + "</b> is in incorrect formmat. The first name must start with Capital letter, includes only letters and be at least 2 leters long. ");
    }

    String lastName = htmlEscape(request.getParameter("lastName"));
    if (!Validation.isValidField("[A-ZÀ-ß][a-zà-ÿ]+", lastName)) {
      validationErrorsList.add("The value of the Last Name field :<b>" + lastName
          + "</b> is in incorrect formmat. The last name must start with Capital letter, includes only letters and be at least 2 leters long. ");
    }
    // TODO:Validation on the EGN!
    String egn = htmlEscape(request.getParameter("egn"));
    // if (!Validation.isValidField(Validation.getEGNRegex(), egn)) {
    if (!Validation.isValidField("\\d{10}", egn)) {
      validationErrorsList.add("The value of the EGN field : <b>" + egn + "</b> is in incorrect formmat.The EGN must start whit digit,include only 10 digit.");
    }

    String address = htmlEscape(request.getParameter("address"));
    // if (!isValidField("[A-ZÀ-ß]\\,+[a-zà-ÿ]+\\.*\\,+[0-9]+", address))
    // if (address.length() >= 50 || address.length() == 0) {
    // validationErrorsList.add("The value of the Address field  :<b>" + address
    // +
    // "</b> is in incorrect formmat. The address must start with capital letter, includes letters and digit .");
    // }

    String cardNumber = htmlEscape(request.getParameter("cardNumber"));
    if (!Validation.isValidField("\\d{16}", cardNumber)) {
      validationErrorsList.add("The value of the CardNumber field  :<b>" + cardNumber
          + "</b> is in incorrect formmat. The card number must start with digit, includes only 16 digit .");
    }

    // validation errors occurs
    if (!validationErrorsList.isEmpty()) {
      session.setAttribute("validationErrorsList", validationErrorsList);
      // response.sendRedirect("validationErrors.jsp");
      request.getRequestDispatcher("validationErrors.jsp").forward(request, response);

    } else {
      hbSession = SessionManager.openSession();

      String fromDate = request.getParameter("fromDate");
      String toDate = request.getParameter("toDate");

      try {

        from_Date = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(fromDate);
        to_Date = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(toDate);

        long carId = Long.parseLong(carIdStr);
        Car car = Car.getCarByPrimeryKey(hbSession, carId);

        Renter renter = new Renter(firstName, lastName, egn, cardNumber, address);
        renter.store(hbSession);

        Rent rent = new Rent(car, renter, rent_Date, from_Date, to_Date, 0);
        rent.store(hbSession);

        SessionManager.commitTransaction();

        response.sendRedirect("reservationConfirmation.jsp");
      } catch (HibernateException e) {
        SessionManager.rollbackTransaction();
        throw new RuntimeException(e);
      } catch (ParseException parseEx) {
        parseEx.printStackTrace();
      } finally {
        SessionManager.closeSession();
      }
    }
  }
}
