package proekt.products;

import proekt.classes.Category;
import proekt.exceptions.EmagInvalidArgumentException;

public class Tablet extends MobileDevice {

	private static final int MIN_DISPLAY_SIZE_TABLET = 6;

	public Tablet(String brand, String model, String color, double price, int quantityLeft, String OS,
			Category category, double displaySize) throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, OS, category);
		setDisplaySize(displaySize);
	}

	@Override
	public void setDisplaySize(double displaySize) throws EmagInvalidArgumentException {
		if (displaySize < MIN_DISPLAY_SIZE_TABLET) {
			throw new EmagInvalidArgumentException("Invalid display size! Must be over " + MIN_DISPLAY_SIZE_TABLET);
		}
		super.setDisplaySize(displaySize);
	}

}
