package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.hibernate.Session;

import session.SessionManager;
import bean.Car;

public class AddCars {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Session hbSession = SessionManager.openSession();

		SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
		Date date = sdf.parse("13.05.1992");

		Car car = new Car("BMW", "X5", date, "CA 5555 KL", "Black", 150);
		car.store(hbSession);
		car = new Car("Ford", "CV12", date, "CA 6666 MN", "RED", 300);
		car.store(hbSession);
		//car = new Car("Opel", "Astra", date, "CA 1256 KL", "Blue", 50.3);
		//car.store(hbSession);
		//car = new Car("Opel", "Astra", date, "CA 1256 KL", "Blue", 50.3);
		//car.store(hbSession);

		SessionManager.commitTransaction();

		System.out.println(" LIST OF ALL CARS AFTER ADDED A NEW CAR:\n");
		List<Car> cars = Car.getAllCars(hbSession);
		for (Car car2 : cars) {
			System.out.println(car2);
		}
	}

}