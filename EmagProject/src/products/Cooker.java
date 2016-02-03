package products;

import engine.Category;
import exceptions.EmagInvalidArgumentException;

public class Cooker extends Product {

	private static final int MIN_COOKER_WIDTH = 50;
	private int width;

	public Cooker(String brand, String model, String color, double price, Category cat, int width, int quantityLeft)
			throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, cat);
		setWidth(width);
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) throws EmagInvalidArgumentException {
		if (width < MIN_COOKER_WIDTH) {
			throw new EmagInvalidArgumentException("Invalid cooker width! Must be over " + MIN_COOKER_WIDTH + "!");
		}
		this.width = width;
	}
}
