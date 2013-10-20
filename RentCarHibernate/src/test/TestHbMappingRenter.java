package test;

import java.text.ParseException;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;

import session.SessionManager;
import bean.Renter;

public class TestHbMappingRenter {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ParseException {
		Session hbSession = SessionManager.openSession();
		Renter renter;
		List<Renter> renters;
		Long renterId;
		Renter loadedRenter;
		Renter renterToUpdate;
		Renter loadedUpdateRenter;
		Renter renterToDelete;
		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
			// Date date = sdf.parse("13.05.1992");
			// GET ALL RENTERS!!!
			System.out.println("LIST OF ALL RENTER!");
			renters = Renter.getAllRenters(hbSession);

			for (Renter renter2 : renters) {
				System.out.println(renter2);
			}

			System.out.println();
			// STORE NEW RENTER!!!

			renter = new Renter("Iven", "Petrov", "8564921476",
					"Sofia ul.Stolichna 29", "1234567");
			System.out.println("NEW RENTER BEFORE THE STORE:" + renter + "\n");
			renterId = renter.store(hbSession);
			System.out.println("Stored RENTER: " + renter + "\n");
			renters = Renter.getAllRenters(hbSession);
			for (Renter renter2 : renters) {
				System.out.println(renter2);
			}
			System.out.println();
			// GET RENTER BY PRIMERY KEY!!
			renterId = renter.getId();
			loadedRenter = Renter.getRenterByPrimeryKey(hbSession, renterId);
			System.out.println("THE RENTER IS:" + loadedRenter + "\n");
			System.out.println();
			// UPDATE RENTER!!!
			renterToUpdate = Renter.getRenterByPrimeryKey(hbSession, renterId);
			renterToUpdate.setAddress("Plovdiv ul.Slynce 23");
			renterToUpdate.update(hbSession);
			System.out.println("UPDATE RENTER BEFORE STORE:" + renterToUpdate
					+ "\n");
			loadedUpdateRenter = Renter.getRenterByPrimeryKey(hbSession,
					renterId);
			System.out.println("LOADED UPDATE RENTER:" + loadedUpdateRenter
					+ "\n");
			System.out.println();
			// DELETE RENTER!!!
			renterToDelete = Renter.getRenterByPrimeryKey(hbSession, renterId);
			renterToDelete.delete(hbSession);
			System.out
					.println("LIST OF ALL RENTER AFTER DELETE OF THE RENTER WITH ID:"
							+ renterId + "\n");
			System.out.println();

			SessionManager.commitTransaction();
		} catch (HibernateException e) {
			SessionManager.rollbackTransaction();
			throw new HibernateException(e);
		} finally {
			SessionManager.closeSession();
		}
	}

}
