package bean;

import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;

public class Rent {
  private long id;
  private Car car;
  private Renter renter;
  private Date rentDate;
  

  public Rent() {
	super();
}

public Rent(Car car, Renter render, Date rentDate) {
    super();
    this.car = car;
    this.renter = render;
    this.rentDate = rentDate;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Renter getRenter() {
    return renter;
  }

  public void setRenter(Renter renter) {
    this.renter = renter;
  }

  public Date getRentDate() {
    return rentDate;
  }

  public void setRentDate(Date rentDate) {
    this.rentDate = rentDate;
  }

  @Override
  public String toString() {
    return "Rent [id=" + id + ", car=" + car + ", renter=" + renter
        + ", rentDate=" + rentDate + "]";
  }

  public Long store(Session hbSession) {
    Long result;
    result = (Long) hbSession.save(this);
    return result;
  }

  public void delete(Session hbSession) {
    hbSession.delete(this);

  }

  public void update(Session hbSession) {
    hbSession.update(this);
  }

  @SuppressWarnings("unchecked")
  public static List<Rent> getAllRents(Session hbSession) {
	  List<Rent> result;
    Query query;

    query = hbSession.createQuery("from Rent");
    result = (List<Rent>) query.list();

    return result;
  }

  public static Rent getRentByPrimeryKey(Session hbSession, long primaryKey) {
    Rent result;
    result = (Rent) hbSession.load(Rent.class, primaryKey);
    return result;
  }

}
