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

  private Date fromDate;

  private Date toDate;

  private int cencelled;

  public Rent() {
    super();
  }

  public Rent(Car car, Renter renter, Date rentDate, Date fromDate, Date toDate, int cencelled) {
    super();

    this.car = car;
    this.renter = renter;
    this.rentDate = rentDate;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.cencelled = cencelled;
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

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public int getCencelled() {
    return cencelled;
  }

  public void setCencelled(int cencelled) {
    this.cencelled = cencelled;
  }

  @Override
  public String toString() {
    return "Rent [id=" + id + ", car=" + car + ", renter=" + renter + ", rentDate=" + rentDate + ", fromDate= " + fromDate + ",toDate" + toDate + ", cencelled= "
        + cencelled + "]";
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
    result = query.list();

    return result;
  }

  @SuppressWarnings("unchecked")
  public static List<Rent> getActiveRents(Session hbSession) {
    List<Rent> result;
    StringBuffer freeRentsQueryText = new StringBuffer();
    Query freeRentsQuery;

    freeRentsQueryText.append("  from Rent as rent ");
    freeRentsQueryText.append(" where rent.cencelled = 0 ");

    freeRentsQuery = hbSession.createQuery(freeRentsQueryText.toString());
    result = freeRentsQuery.list();

    return result;
  }

  public static Rent getRentByPrimeryKey(Session hbSession, long primaryKey) {
    Rent result;
    result = (Rent) hbSession.load(Rent.class, primaryKey);
    return result;
  }

}
