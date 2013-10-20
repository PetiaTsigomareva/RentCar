package bean;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class Renter {
	private long id;
	private String firstName;
	private String lastName;
	private String egn;
	private String address;
	private String cardNumber;
	private List<Rent> rents = new ArrayList<Rent>();

	public Renter() {
		super();
	}

	public Renter(String firstName, String lastName, String egn,
			String address, String cardNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.egn = egn;
		this.address = address;
		this.cardNumber = cardNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEgn() {
		return egn;
	}

	public void setEgn(String egn) {
		this.egn = egn;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	@Override
	public String toString() {
		return "Renter [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", egn=" + egn + ", address=" + address
				+ ", cardNumber=" + cardNumber + ", rents=" + rents + "]";
	}

	public Long store(Session hbSession) {
		Long result;
		result = (Long) hbSession.save(this);
		return result;
	}

	@SuppressWarnings("unchecked")
	public void delete(Session hbSession) {
		Query allRenterRentsQuery;
		List<Rent> allCarRents;

		// find all renter rents
		allRenterRentsQuery = hbSession
				.createQuery("from Rent where car_id= :renterId");
		allRenterRentsQuery.setLong("renterId", id);
		allCarRents = (List<Rent>) allRenterRentsQuery.list();

		// delete all renter rents
		for (Rent rent : allCarRents) {
			hbSession.delete(rent);
		}

		// delete the renter
		hbSession.delete(this);

	}

	public void update(Session hbSession) {
		hbSession.update(this);
	}

	@SuppressWarnings("unchecked")
	public static List<Renter> getAllRenters(Session hbSession) {
		List<Renter> result;
		Query query;

		query = hbSession.createQuery("from Renter");
		result = (List<Renter>) query.list();

		return result;
	}

	public static Renter getRenterByPrimeryKey(Session hbSession,
			long primaryKey) {
		Renter result;
		result = (Renter) hbSession.load(Renter.class, primaryKey);
		return result;
	}
}