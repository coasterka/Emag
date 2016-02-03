package products;

import classes.Category;
import exceptions.EmagInvalidArgumentException;

public class Laptop extends MobileDevice {

	private static final int MIN_DISPLAY_SIZE_LAPTOP = 10;

	public Laptop(String brand, String model, String color, double price, int quantityLeft, String oS, Category cat,
			double displaySize) throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, oS, cat);
		setDisplaySize(displaySize);
	}

	@Override
	public void setDisplaySize(double displaySize) throws EmagInvalidArgumentException {
		if (displaySize < MIN_DISPLAY_SIZE_LAPTOP) {
			throw new EmagInvalidArgumentException(
					"Invalid display size! Must be over " + MIN_DISPLAY_SIZE_LAPTOP + "!");
		}
		super.setDisplaySize(displaySize);
	}
}
