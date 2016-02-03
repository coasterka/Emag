package products;

import engine.Category;
import exceptions.EmagInvalidArgumentException;

public class SmartPhone extends MobileDevice {

	private static final int MIN_DISPLAY_SIZE_SMARTPHONE = 2;

	public SmartPhone(String brand, String model, String color, double price, int quantityLeft, String OS, Category category,
			double displaySize) throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, OS, category);
		setDisplaySize(displaySize);
	}

	@Override
	public void setDisplaySize(double displaySize) throws EmagInvalidArgumentException {
		if (displaySize < MIN_DISPLAY_SIZE_SMARTPHONE) {
			throw new EmagInvalidArgumentException(
					"Invalid display size! Must be over " + MIN_DISPLAY_SIZE_SMARTPHONE + " inches!");
		}
		super.setDisplaySize(displaySize);
	}
}