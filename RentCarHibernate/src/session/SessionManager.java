package session;

/**
 * Hibernate session manager class. Handles the Hibernate session factory,
 * session and transaction and stores the currently active session in the
 * context of the current thread.
 * 
 * How to use it:
 *
 * Session hbSession = SessionManager.openSession();
 * try {
 *     // Use the session here - retrieve data, modify data, etc.
 *     // ...
 *     SessionManager.commitTransaction();
 * } catch (Throwable ex) {
 *     SessionManager.rollbackTransaction();
 *     throw new RuntimeException(ex);
 * } finally {
 *     SessionManager.closeSession();
 * }
 * 
 * (c) 2007 by Svetlin Nakov - http://www.nakov.com
 * National Academy for Software Development - http://academy.devbg.org
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionManager {

	private static SessionFactory mSessionFactory = null;

	private static ThreadLocal<Session> mLocalThreadSession = new ThreadLocal<Session>();

	/**
	 * Starts a new session and stores it in the context of the current thread.
	 */
	public static Session openSession() {
		// Start new Hibernate session
		SessionFactory sessionFactory = getSessionFactory();
		Session hbSession = sessionFactory.openSession();

		// Start new transaction for the session
		hbSession.beginTransaction();

		// Save the session in the context of the current thread
		mLocalThreadSession.set(hbSession);

		return hbSession;
	}

	/**
	 * Gets the currently active Hibernate session (for the current thread).
	 * Returns null if the current thread do not have an active session.
	 */
	public static Session getCurrentSession() {
		Session hbSession = mLocalThreadSession.get();
		return hbSession;
	}

	/**
	 * Commits the active transaction and immediately starts a new one. The
	 * session remains active.
	 */
	public static void commitTransaction() {
		Session hbSession = getCurrentSession();
		Transaction tran = hbSession.getTransaction();
		if (tran.isActive()) {
			tran.commit();
			hbSession.beginTransaction();
		}
	}

	/**
	 * Rollbacks the active transaction, closes the active sesion (because it's
	 * state is invalid) and starts a new session and transaction.
	 */
	public static void rollbackTransaction() {
		Session hbSession = getCurrentSession();

		// Rollback the active transaction
		Transaction tran = hbSession.getTransaction();
		tran.rollback();

		// Close the active session. It's state is invalid
		hbSession.close();

		// Start new Hibernate session
		SessionFactory sessionFactory = getSessionFactory();
		hbSession = sessionFactory.openSession();

		// Start new transaction for the session
		hbSession.beginTransaction();
	}

	public static void closeSession() {
		Session hbSession = getCurrentSession();
		hbSession.close();
		mLocalThreadSession.set(null);
	}

	/**
	 * Create a single instance of the session factory (on demand). Implements the
	 * classical thread-safe singleton pattern.
	 */
	private static SessionFactory getSessionFactory() {
		if (mSessionFactory == null) {
			synchronized (SessionManager.class) {
				if (mSessionFactory == null) {
					// Load the Hibernate settings from hibernate.cfg.xml
					Configuration cfg = new Configuration();
					cfg.configure();

					// Create the Hibernate SessionFactory instance
					mSessionFactory = cfg.buildSessionFactory();
				}
			}
		}

		return mSessionFactory;
	}
}
