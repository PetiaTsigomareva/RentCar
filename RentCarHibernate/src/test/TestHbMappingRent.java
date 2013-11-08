package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import session.SessionManager;
import bean.Car;
import bean.Rent;
import bean.Renter;

public class TestHbMappingRent {

  /**
   * @param args
   */
  public static void main(String[] args) throws ParseException {
    Session hbSession = SessionManager.openSession();
    Rent rent;
    List<Rent> rents;
    Long rentId;
    Rent loadedRent;
    Rent rentToUpdate;
    Rent loadedUpdateRent;
    Rent rentToDelete;

    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
      Date date = sdf.parse("13.05.1992");

      Car car = new Car("Opel", "Astra", date, "CA 1256 KL", "Blue", 50.3);
      car.store(hbSession);
      Renter renter = new Renter("Milen ", "Georgiew", "2356987415", "Sliven ul.Bulgarca23", "125639");
      renter.store(hbSession);

      SessionManager.commitTransaction();

      List<Car> cars = Car.getAllCars(hbSession);
      car = cars.iterator().next();

      List<Renter> renters = Renter.getAllRenters(hbSession);
      renter = renters.iterator().next();

      // GET ALL RENT!!
      System.out.println("LIST OF ALL RENT:");
      rents = Rent.getAllRents(hbSession);
      for (Rent rent2 : rents) {
        System.out.println(rent2);
      }

      System.out.println();

      // STORE RENT!!!
      rent = new Rent(car, renter, date, 1);

      System.out.println("NEW RENT BEFORE THE STORE:" + rent + "\n");
      rentId = rent.store(hbSession);
      System.out.println("STORED RENT:" + rent + "\n");

      SessionManager.commitTransaction();

      System.out.println(" LIST OF ALL RENTS AFTER ADDED A NEW RENT : ");
      rents = Rent.getAllRents(hbSession);
      for (Rent rent2 : rents) {
        System.out.println(rent2);
      }

      System.out.println();
      // GET RENT BY PRIMERY KEY!!!!
      rentId = rent.getId();
      loadedRent = Rent.getRentByPrimeryKey(hbSession, rentId);
      System.out.println("LOADED RENT:" + loadedRent + "\n");
      System.out.println();

      // UPDATE CAR!!!
      rentToUpdate = Rent.getRentByPrimeryKey(hbSession, rentId);
      rentToUpdate.setRentDate(date);
      rentToUpdate.setRenter(renter);
      System.out.println("UPDATE CAR BEFORE STORE:" + rentToUpdate + "\n");
      rentToUpdate.update(hbSession);

      loadedUpdateRent = Rent.getRentByPrimeryKey(hbSession, rentId);
      System.out.println("LOADED UPDATE CAR:" + loadedUpdateRent + "\n");
      System.out.println();

      // // DELETE CAR!!!
      // rentToDelete = Rent.getRentByPrimeryKey(hbSession, rentId);
      // rentToDelete.delete(hbSession);
      // System.out
      // .println("LIST OF ALL CAR AFTER DELETE OF THE CAR WITH ID:"
      // + rentId + "\n");
      // System.out.println();
      //
      // SessionManager.commitTransaction();
      // } catch (HibernateException e) {
      // SessionManager.rollbackTransaction();
      // throw new HibernateException(e);
    } finally {
      SessionManager.closeSession();
    }
  }

}
