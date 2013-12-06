package servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class HiredCarsServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = Logger.getLogger(HiredCarsServlet.class.getName());

  /**
   * @see HttpServlet#HttpServlet()
   */
  public HiredCarsServlet() {
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
      List<Rent> rents = Rent.getActiveRents(hbSession);
      LOGGER.log(Level.INFO, "The List of the hired cars is:{0}", new Object[] { Rent.getActiveRents(hbSession) });

      request.setAttribute("rents", rents);
      request.getRequestDispatcher("showHiredCars.jsp").forward(request, response);
    } catch (HibernateException e) {
      SessionManager.rollbackTransaction();
      LOGGER.log(Level.SEVERE, "Loading of the hired cars list {0}\n failed with the following error:\n{1}", new Object[] { Rent.getActiveRents(hbSession), e });
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
    doGet(request, response);
  }

}
