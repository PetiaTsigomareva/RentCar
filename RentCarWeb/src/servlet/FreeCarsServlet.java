package servlet;

import java.io.IOException;
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
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    Session hbSession = null;
    try {
      Date date = new Date();

      hbSession = SessionManager.openSession();
      session.setAttribute("hbSession", hbSession);

      List<Car> cars = Car.getFreeCars(hbSession, date);
      request.setAttribute("cars", cars);
      request.getRequestDispatcher("showFreeCars.jsp").forward(request, response);
    } catch (HibernateException e) {
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      // if (hbSession != null) {
      // try {
      // SessionManager.commitTransaction();
      // session.setAttribute("reservation", "OK");
      // request.getRequestDispatcher("reservation.jsp").forward(request,
      // response);
      // } catch (Exception ex) {
      // SessionManager.rollbackTransaction();
      // session.setAttribute("reservation", "failed");
      // request.getRequestDispatcher("error.jsp").forward(request, response);
      // } finally {
      // if (hbSession != null) {
      // hbSession.close();
      // }
      // }
      // }
    }
  }
}
