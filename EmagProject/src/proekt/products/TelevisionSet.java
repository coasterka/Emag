package proekt.products;

import proekt.classes.Category;
import proekt.exceptions.EmagInvalidArgumentException;

public class TelevisionSet extends Product {
	
	private TelevisionType type;

	public TelevisionSet(String brand, String model, String color, double price,
			int quantityLeft, Category cat, TelevisionType type) throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, cat);
		setType(type);
	}

	public TelevisionType getType() {
		return this.type;
	}

	public void setType(TelevisionType type) throws EmagInvalidArgumentException {
		if (type == null) {
			throw new EmagInvalidArgumentException("Television type can not be null!");
		}
		this.type = type;
	}
}