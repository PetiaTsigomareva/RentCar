package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import session.SessionManager;

public class Car {
  private long id;

  private String producer;

  private String modification;

  private Date manifactureDate;

  private String registrationNumber;

  private String color;

  private double priceForDay;

  private List<Rent> rents = new ArrayList<Rent>();

  public Car() {
    super();
  }

  public Car(String producer, String modification, Date manufactureDate, String registrationNumber, String color, double priceForday) {
    super();
    this.producer = producer;
    this.modification = modification;
    this.manifactureDate = manufactureDate;
    this.registrationNumber = registrationNumber;
    this.color = color;
    this.priceForDay = priceForday;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getProducer() {
    return producer;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Date getManifactureDate() {
    return manifactureDate;
  }

  public void setManifactureDate(Date manufactureDate) {
    this.manifactureDate = manufactureDate;
  }

  public String getModification() {
    return modification;
  }

  public void setModification(String modification) {
    this.modification = modification;
  }

  public double getPriceForDay() {
    return priceForDay;
  }

  public void setPriceForDay(double priceForday) {
    this.priceForDay = priceForday;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public List<Rent> getRents() {
    return rents;
  }

  public void setRents(List<Rent> rents) {
    this.rents = rents;
  }

  @Override
  public String toString() {
    return "Car [id=" + id + ", producer=" + producer + ", modification=" + modification + ", manufactureDate=" + manifactureDate + ", registrationNumber="
        + registrationNumber + ", color=" + color + ", priceForday=" + priceForDay + ", rents=" + rents + "]";
  }

  public Long store(Session hbSession) {
    Long result;

    result = (Long) hbSession.save(this);
    SessionManager.commitTransaction();

    return result;
  }

  @SuppressWarnings("unchecked")
  public void delete(Session hbSession) {
    Query allCarRentsQuery;
    List<Rent> allCarRents;

    // find all car rents
    allCarRentsQuery = hbSession.createQuery("from Rent where car_id= :carId");
    allCarRentsQuery.setLong("carId", id);
    allCarRents = allCarRentsQuery.list();

    // delete all car rents
    for (Rent rent : allCarRents) {
      hbSession.delete(rent);
    }

    // delete the car
    hbSession.delete(this);
    SessionManager.commitTransaction();
  }

  public void update(Session hbSession) {
    hbSession.update(this);
    SessionManager.commitTransaction();
  }

  @SuppressWarnings("unchecked")
  public static List<Car> getAllCars(Session hbSession) {
    List<Car> result;
    Query query;

    query = hbSession.createQuery("from Car");

    result = query.list();

    return result;
  }

  public static Car getCarByPrimeryKey(Session hbSession, long primaryKey) {
    Car result;
    result = (Car) hbSession.load(Car.class, primaryKey);
    return result;
  }

  @SuppressWarnings("unchecked")
  public static List<Car> getFreeCars(Session hbSession, Date date) {
    List<Car> result = new ArrayList<Car>();
    Query rentsQuery;
    // String freeCarsQuery = " select car "
    // + " from Car as car "
    // + "left outer join car.rents as rents "
    // + "where rents.rentDate != :date "
    // + "   or rents is null ";

    String freeCarsQuery = "   from Car as car "
        + "where not exists ( "
        + " from Rent as rent "
        + " where rent.car = car "
        + " and rent.rentDate != :date "
        + "                  ) ";

    rentsQuery = hbSession.createQuery(freeCarsQuery);
    rentsQuery.setDate("date", date);
    result = rentsQuery.list();

    return result;
  }
}