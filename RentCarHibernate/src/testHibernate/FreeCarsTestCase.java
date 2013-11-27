package testHibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.junit.Test;

import session.SessionManager;
import bean.Car;
import bean.Rent;
import bean.Renter;

public class FreeCarsTestCase extends TestCase {

  private Car carReservedInTheStartOfTheTimeInterval;

  private Car carReservedInTheTimeInterval;

  private Car carReservedInTheEndOfTimeInterval;

  private Car carReservedBeforeStartAfterEndTimeInterval;

  private Car carReservedBeforeStartOfTimeInterval;

  private Car carReservedAfterTheEndOfTimeInterval;

  private Car carNotReserved;

  private final List<Rent> allRent = new ArrayList<Rent>();

  private Session hbSession;

  private Date toDate(String date) throws ParseException {
    Date result = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    result = sdf.parse(date);
    return result;
  }

  @Override
  public void setUp() throws Exception, ParseException {
    Date rentDate = new Date();

    hbSession = SessionManager.openSession();

    carReservedInTheStartOfTheTimeInterval = new Car("Lada", "S1", toDate("13.05.1992 10:00"), "CA1223DD", "Red", 120.32);
    carReservedInTheTimeInterval = new Car("Lada", "S2", toDate("13.05.1992 10:00"), "CA1223DD", "Red", 150.32);
    carReservedInTheEndOfTimeInterval = new Car("Lada", "S3", toDate("13.05.1992 10:00"), "CA1223DD", "Red", 20.32);
    carReservedBeforeStartAfterEndTimeInterval = new Car("Lada", "S4", toDate("13.05.1992 10:00"), "CA1223DD", "Red", 12.32);
    carReservedBeforeStartOfTimeInterval = new Car("Lada", "S5", toDate("13.05.1992 10:00"), "CA1223DD", "Red", 100.32);
    carReservedAfterTheEndOfTimeInterval = new Car("Lada", "S6", toDate("13.05.1992 10:00"), "CA1223DD", "Red", 130.32);
    carNotReserved = new Car("Lada", "S7", toDate("13.05.1992 10:00"), "CA1223DD", "Red", 130.32);

    carReservedInTheStartOfTheTimeInterval.store(hbSession);
    carReservedInTheTimeInterval.store(hbSession);
    carReservedInTheEndOfTimeInterval.store(hbSession);
    carReservedBeforeStartAfterEndTimeInterval.store(hbSession);
    carReservedBeforeStartOfTimeInterval.store(hbSession);
    carReservedAfterTheEndOfTimeInterval.store(hbSession);
    carNotReserved.store(hbSession);

    Renter renter1 = new Renter("PPP", "PPP", "12354", "Sofia", "12356479");
    Renter renter2 = new Renter("PPP", "PPP", "12354", "Sofia", "12356479");
    Renter renter3 = new Renter("PPP", "PPP", "12354", "Sofia", "12356479");
    Renter renter4 = new Renter("PPP", "PPP", "12354", "Sofia", "12356479");
    Renter renter5 = new Renter("PPP", "PPP", "12354", "Sofia", "12356479");
    Renter renter6 = new Renter("PPP", "PPP", "12354", "Sofia", "12356479");

    renter1.store(hbSession);
    renter2.store(hbSession);
    renter3.store(hbSession);
    renter4.store(hbSession);
    renter5.store(hbSession);
    renter6.store(hbSession);

    Rent rentEndInTimeInterval = new Rent(carReservedInTheStartOfTheTimeInterval, renter1, rentDate, toDate("01.11.2013 10:00"), toDate("06.11.2013 12:00"), 1);
    allRent.add(rentEndInTimeInterval);

    Rent rentInTimeInterval = new Rent(carReservedInTheTimeInterval, renter2, rentDate, toDate("06.11.2013 10:00"), toDate("09.11.2013 12:00"), 1);
    allRent.add(rentInTimeInterval);

    Rent rentStartInTimeInterval = new Rent(carReservedInTheEndOfTimeInterval, renter3, rentDate, toDate("09.11.2013 10:00"), toDate("13.11.2013 12:00"), 1);
    allRent.add(rentStartInTimeInterval);

    Rent rentStartBeforeEndAfterTimeInterval = new Rent(carReservedBeforeStartAfterEndTimeInterval, renter4, rentDate, toDate("01.11.2013 10:00"),
        toDate("13.11.2013 12:00"), 1);
    allRent.add(rentStartBeforeEndAfterTimeInterval);

    Rent rentBeforeTimeInterval = new Rent(carReservedBeforeStartOfTimeInterval, renter5, rentDate, toDate("01.11.2013 10:00"), toDate("04.11.2013 12:00"), 1);
    allRent.add(rentBeforeTimeInterval);

    Rent rentAfterTimeInterval = new Rent(carReservedAfterTheEndOfTimeInterval, renter6, rentDate, toDate("12.11.2013 10:00"), toDate("14.11.2013 12:00"), 1);
    allRent.add(rentAfterTimeInterval);

    for (Rent rent : allRent) {
      rent.store(hbSession);
    }
  }

  @Override
  public void tearDown() throws Exception, ParseException {
    for (Rent rent : allRent) {
      rent.getCar().delete(hbSession);
      rent.getRenter().delete(hbSession);
      rent.delete(hbSession);
    }

    carNotReserved.delete(hbSession);

    SessionManager.closeSession();
  }

  @Test
  public void testGetFreeCarsMethodForCarReservedInTheStartOfTheTimeInterval() throws ParseException {
    Date fromDate = toDate("05.11.2013 10:00");
    Date toDate = toDate("10.11.2013 12:00");

    List<Car> freeCarList = Car.getFreeCars(hbSession, fromDate, toDate);

    // Check that the reserved car is not included in the result of the method
    // getFreeCars()
    assertFalse("The car is not include in the result of the method.This car is not free in this time interval.",
        freeCarList.contains(carReservedInTheStartOfTheTimeInterval));

  }

  public void testGetFreeCarsMethodForCarReservedInTheTimeInterval() throws ParseException {
    Date fromDate = toDate("05.11.2013 10:00");
    Date toDate = toDate("10.11.2013 12:00");

    List<Car> freeCarList = Car.getFreeCars(hbSession, fromDate, toDate);

    // Check that the reserved car is not included in the result of the method
    // getFreeCars()

    assertFalse("The car is not include in the result of the method.This car is not free in this time interval.", freeCarList.contains(carReservedInTheTimeInterval));

  }

  public void testGetFreeCarsMethodForCarReservedInTheEndOfTimeInterval() throws ParseException {
    Date fromDate = toDate("05.11.2013 10:00");
    Date toDate = toDate("10.11.2013 12:00");

    List<Car> freeCarList = Car.getFreeCars(hbSession, fromDate, toDate);

    // Check that the reserved car is not included in the result of the method
    // getFreeCars()

    assertFalse("The car is not include in the result of the method.This car is not free in this time interval.",
        freeCarList.contains(carReservedInTheEndOfTimeInterval));

  }

  public void testGetFreeCarsMethodForCarReservedBeforeStartAfterEndTimeInterval() throws ParseException {
    Date fromDate = toDate("05.11.2013 10:00");
    Date toDate = toDate("10.11.2013 12:00");

    List<Car> freeCarList = Car.getFreeCars(hbSession, fromDate, toDate);

    // Check that the reserved car is not included in the result of the method
    // getFreeCars()

    assertFalse("The car is not include in the result of the method.This car is not free in this time interval.",
        freeCarList.contains(carReservedBeforeStartAfterEndTimeInterval));

  }

  public void testGetFreeCarsMethodForCarReservedBeforeStartOfTimeInterval() throws ParseException {
    Date fromDate = toDate("05.11.2013 10:00");
    Date toDate = toDate("10.11.2013 12:00");

    List<Car> freeCarList = Car.getFreeCars(hbSession, fromDate, toDate);

    // Check that the reserved car outside the requested time interval is
    // included in the result of the method getFreeCars()
    assertTrue("The car is include in the result of the method.This car is free in this time interval.", freeCarList.contains(carReservedBeforeStartOfTimeInterval));
  }

  public void testGetFreeCarsMethodForCarReservedAfterTheEndOfTimeInterval() throws ParseException {
    Date fromDate = toDate("05.11.2013 10:00");
    Date toDate = toDate("10.11.2013 12:00");

    List<Car> freeCarList = Car.getFreeCars(hbSession, fromDate, toDate);

    // Check that the reserved car outside the requested time interval are
    // included in the result of the method getFreeCars()

    assertTrue("The car is include in the result of the method.This car is free in this time interval.", freeCarList.contains(carReservedAfterTheEndOfTimeInterval));

  }

  public void testGetFreeCarsMethodForCarNotReserved() throws ParseException {
    Date fromDate = toDate("05.11.2013 10:00");
    Date toDate = toDate("10.11.2013 12:00");

    List<Car> freeCarList = Car.getFreeCars(hbSession, fromDate, toDate);

    // Check that the free(not reserved) car is included in the result of the
    // method getFreeCars()
    assertTrue(freeCarList.contains(carNotReserved));
  }

}