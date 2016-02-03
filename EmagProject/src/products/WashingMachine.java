package products;

import engine.Category;
import exceptions.EmagInvalidArgumentException;

public class WashingMachine extends Product {

	private static final int MIN_ROUNS_PER_MINUTE_WASHING_MACHINE = 200;
	private int roundsPerMinute;

	public WashingMachine(String brand, String model, String color, double price, int quantityLeft, Category category,
			int roundsPerMinute) throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, category);
		this.setRoundsPerMinute(roundsPerMinute);
	}

	public int getRoundsPerMinute() {
		return this.roundsPerMinute;
	}

	public void setRoundsPerMinute(int roundsPerMinute) throws EmagInvalidArgumentException {
		if (roundsPerMinute < MIN_ROUNS_PER_MINUTE_WASHING_MACHINE) {
			throw new EmagInvalidArgumentException("Ivalid RPM! Must be over " + MIN_ROUNS_PER_MINUTE_WASHING_MACHINE);
		}
		this.roundsPerMinute = roundsPerMinute;
	}
}
