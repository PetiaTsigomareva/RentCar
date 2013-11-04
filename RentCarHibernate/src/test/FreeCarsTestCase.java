package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import session.SessionManager;
import bean.Car;
import bean.Rent;

public class FreeCarsTestCase extends TestCase {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Test
  public void test() throws ParseException {
    Session hbSession = SessionManager.openSession();

    SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
    Date date = sdf.parse("12.01.1992");

    System.out.println("List of All Cars.");
    List<Car> allCarList = Car.getAllCars(hbSession);
    for (Car car : allCarList) {
      System.out.println(car);
      List<Rent> carRents = car.getRents();
      for (int i = 0; i < carRents.size(); i++) {
        Rent rent = carRents.get(i);
        System.out.println(rent);
      }
    }

    System.out.println("");
    assertTrue("The all cars list is empty!!!", !allCarList.isEmpty());

    System.out.println("List of the free cars is.");
    List<Car> freeCarList = Car.getFreeCars(hbSession, date);
    for (Car car1 : freeCarList) {
      System.out.println(car1);
    }

    assertTrue("The free cars list is empty!!!", !freeCarList.isEmpty());
  }
}
