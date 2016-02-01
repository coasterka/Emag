package proekt.products;

import proekt.classes.Category;
import proekt.exceptions.EmagInvalidArgumentException;

public class WashingMachine extends Product {
	
	private static final int MIN_OBOROTI_WASHING_MACHINE = 200;
	private int oboroti;
	

	public WashingMachine(String brand, String model, String color, double price, int quantityLeft,
			Category cat, int oboroti) throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, cat);
		this.setOboroti(oboroti);
	}

	public int getOboroti() {
		return this.oboroti;
	}

	public void setOboroti(int oboroti) throws EmagInvalidArgumentException {
		if (oboroti >= MIN_OBOROTI_WASHING_MACHINE) {
			this.oboroti = oboroti;
		}
		else {
			throw new EmagInvalidArgumentException("Ivalid RPM! Must be over 200");
		}
		
	}

}
