package proekt.products;

import proekt.classes.Category;
import proekt.exceptions.EmagInvalidArgumentException;

public class Cooker extends Product {
	
	private int width;	
	

	public Cooker(String brand, String model, String color, double price, Category cat, 
			int width, int quantityLeft)	throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, cat);
		this.setWidth(width);
	}


	public int getWidth() {
		return this.width;
	}


	public void setWidth(int width) throws EmagInvalidArgumentException {
		if (width >= 50) {
			this.width = width;
		}
		else {
			throw new EmagInvalidArgumentException("Invalid cooker width! Must be over 50!");
		}
		
	}

}
