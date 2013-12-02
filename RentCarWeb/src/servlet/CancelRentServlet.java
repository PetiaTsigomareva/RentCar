package servlet;

import java.io.IOException;
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
 * Servlet implementation class CancelRentServlet
 */
public class CancelRentServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = Logger.getLogger(CancelRentServlet.class.getName());

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CancelRentServlet() {
    super();
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Session hbSession;
    String rentIdString;
    long rentId;

    rentIdString = request.getParameter("rentId");
    rentId = Long.parseLong(rentIdString);

    hbSession = SessionManager.openSession();

    try {
      Rent rent = Rent.getRentByPrimeryKey(hbSession, rentId);
      rent.setCencelled(1);
      rent.store(hbSession);

      SessionManager.commitTransaction();
      LOGGER.log(Level.INFO, "Cancelled Rent with ID={0}", new Object[] { rent.getId() });
    } catch (HibernateException e) {
      SessionManager.rollbackTransaction();
      LOGGER.log(Level.SEVERE, "Cancellation of Rent with ID={0} failed with the following error:\n{1}", new Object[] { rentId, e });
      throw new RuntimeException(e);
    } finally {
      SessionManager.closeSession();
    }

    getServletContext().getRequestDispatcher("/HiredCarsServlet").forward(request, response);
    // response.sendRedirect("/RentCarWeb/HiredCarsServlet");
  }
}