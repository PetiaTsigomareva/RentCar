package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import session.SessionManager;
import bean.Rent;

/**
 * Servlet implementation class HireCarsServlet
 */
public class HireCarsServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public HireCarsServlet() {
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

    hbSession = SessionManager.openSession();

    try {
      List<Rent> rents = Rent.getAllRents(hbSession);

      request.setAttribute("rents", rents);
      request.getRequestDispatcher("showHiredCars.jsp").forward(request, response);
    } catch (HibernateException e) {
      SessionManager.rollbackTransaction();
      throw new RuntimeException(e);
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
    // TODO Auto-generated method stub
  }

}
