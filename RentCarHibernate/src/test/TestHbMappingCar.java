package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import session.SessionManager;
import bean.Car;

public class TestHbMappingCar {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Session hbSession = SessionManager.openSession();
		List<Car> cars;
		Car newCar;
		Long newCarId;
		Car carToUpdate;
		Car loadedUpdateCar;
		Car loadedCar;
		Car carToDelete;
		List<Car> freeCars;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
			Date date = sdf.parse("13.05.1992");

			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.dd.mm");
			Date date2 = sdf2.parse("1992.13.05");

			System.out.println(date.equals(date2));

			// GET ALL CARS!!!
			System.out.println(" LIST OF ALL CARS:");

			cars = Car.getAllCars(hbSession);
			for (Car car : cars) {
				System.out.println(car);
			}

			System.out.println();

			// STORE CARS!!!!!
			newCar = new Car("Audi", "A4", date, "CA 4128 MC", "blue", 150.2);
			System.out.println("NEW CAR BEFORE THE STORE:\n" + newCar + "\n");
			newCarId = newCar.store(hbSession);
			System.out.println("STORED CAR:\n" + newCar + "\n");

			System.out.println(" LIST OF ALL CARS AFTER ADDED A NEW CAR:\n");
			cars = Car.getAllCars(hbSession);
			for (Car car : cars) {
				System.out.println(car);
			}
			System.out.println();

			// GET CAR BY PRIMERY KEY!!!!
			newCarId = newCar.getId();
			loadedCar = Car.getCarByPrimeryKey(hbSession, newCarId);
			System.out.println("LOADED CAR:\n" + loadedCar + "\n");
			System.out.println();

			// UPDATE CAR!!!
			carToUpdate = Car.getCarByPrimeryKey(hbSession, newCarId);
			carToUpdate.setColor("red");
			carToUpdate.setPriceForDay(400);
			System.out.println("UPDATE CAR BEFORE STORE:\n" + carToUpdate
					+ "\n");
			carToUpdate.update(hbSession);

			loadedUpdateCar = Car.getCarByPrimeryKey(hbSession, newCarId);
			System.out.println("LOADED UPDATE CAR:\n" + loadedUpdateCar + "\n");
			System.out.println();

			// DELETE CAR!!!
			carToDelete = Car.getCarByPrimeryKey(hbSession, newCarId);
			carToDelete.delete(hbSession);
			System.out
					.println("LIST OF ALL CAR AFTER DELETE OF THE CAR WITH ID:\n"
							+ newCarId + "\n");

			cars = Car.getAllCars(hbSession);
			for (Car car : cars) {
				System.out.println(car);
			}
			System.out.println();

			// GET FREECARS!!!!

			freeCars = Car.getFreeCars(hbSession, date);
			System.out.println("LIST OF THE FREE CARS:\n");
			for (Car car : freeCars) {
				System.out.println(car);
			}
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
