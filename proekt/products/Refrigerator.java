package proekt.products;

import proekt.classes.Category;
import proekt.exceptions.EmagInvalidArgumentException;

public class Refrigerator extends Product {
	
	private static final int MIN_REFRIGERATOR_VOLUME = 20;
	private int volume;
	

	public Refrigerator(String brand, String model, String color, double price, int quantityLeft, 
			Category cat, int volume) throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, cat);
		this.setVolume(volume);
	}
	
	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) throws EmagInvalidArgumentException {
		if (volume > MIN_REFRIGERATOR_VOLUME) {
			this.volume = volume;
		}
		else {
			throw new EmagInvalidArgumentException("Invalid volume! Must be over 20 litres!");
		}
		
	}

}
