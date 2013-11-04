package servlet;

import static htmlEscape.Escape.htmlEscape;

import java.io.IOException;
import java.util.Date;

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

    String carIdStr = request.getParameter("carId");
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    String firstName = htmlEscape(request.getParameter("firstName"));
    String lastName = htmlEscape(request.getParameter("lastName"));
    String egn = htmlEscape(request.getParameter("egn"));
    String address = htmlEscape(request.getParameter("address"));
    String cardNumber = htmlEscape(request.getParameter("cardNumber"));

    Session hbSession = null;
    try {
      hbSession = SessionManager.openSession();
      long carId = Long.parseLong(carIdStr);
      Renter renter = new Renter(firstName, lastName, egn, cardNumber, address);
      renter.store(hbSession);
      Car car = Car.getCarByPrimeryKey(hbSession, carId);
      Rent rent = new Rent(car, renter, new Date());
      rent.store(hbSession);
      session.setAttribute("reservation", "successful");
      response.sendRedirect("reservation.jsp");
    } catch (HibernateException e) {
      e.printStackTrace();
    } finally {
      if (hbSession != null) {
        try {
          SessionManager.commitTransaction();
        } catch (Exception ex) {
          SessionManager.rollbackTransaction();
          session.setAttribute("reservation", "failed");
          request.getRequestDispatcher("error.jsp").forward(request, response);
        }
      }
    }
  }
}