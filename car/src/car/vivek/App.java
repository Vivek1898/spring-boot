package car.vivek;

public class App {
	public static void main(String[] args) {
		car c = new car();
		c.setEngine(new DieselEngine());
		c.drive();
		c.setEngine(new PetrolEngine());
		c.drive();
	}
}

