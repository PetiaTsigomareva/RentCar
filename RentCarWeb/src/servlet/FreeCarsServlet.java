package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import session.SessionManager;
import bean.Car;

/**
 * Servlet implementation class FreeCarsServlet
 */
// @WebServlet("/FreeCarsServlet")
public class FreeCarsServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public FreeCarsServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Session hbSession;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    hbSession = SessionManager.openSession();

    try {

      Date fromDate = sdf.parse("01.03.2013 10:00");
      Date toDate = sdf.parse("25.12.2013 12:00");
      List<Car> cars = Car.getFreeCars(hbSession, fromDate, toDate);

      request.setAttribute("cars", cars);
      request.getRequestDispatcher("showFreeCars.jsp").forward(request, response);
    } catch (HibernateException e) {
      SessionManager.rollbackTransaction();
      throw new RuntimeException(e);
    } catch (ParseException parseEx) {
      parseEx.printStackTrace();
    } finally {
      SessionManager.closeSession();
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }
}