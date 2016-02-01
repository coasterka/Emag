package proekt.products;

import proekt.classes.Category;
import proekt.exceptions.EmagInvalidArgumentException;

	

public class SmartPhone extends MobileDevice {
	
	private static final int MIN_DISPLAY_SIZE_SMARTPHONE = 2;

	public SmartPhone(String brand, String model, String color, double price, int quantityLeft, 
			String oS, Category cat, double displaySize) throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, oS, cat);
		this.setDisplaySize(displaySize);
	}
	
	@Override
	public void setDisplaySize(double displaySize) throws EmagInvalidArgumentException {
		if (displaySize >= MIN_DISPLAY_SIZE_SMARTPHONE) {
			super.setDisplaySize(displaySize);
		}
		else {
			throw new EmagInvalidArgumentException("Invalid display size!");
		}
		
	}

}
