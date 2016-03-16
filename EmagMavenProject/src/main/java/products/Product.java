package products;

import java.util.HashMap;

import engine.Brand;
import engine.Category;
import engine.Color;
import exceptions.EmagInvalidArgumentException;

public class Product implements IProduct {

	private static int numberOfProducts = 1;

	private final int productID;
	private Brand brand;
	private String model;
	private Color color;
	private double price;
	private Category category;
	private int quantityLeft;
	private HashMap<String, String> attributesWithValues;
	private String characteristics;

	public Product(Brand brand, String model, Color color, double price, int quantityLeft, Category category,
			String characteristics) throws EmagInvalidArgumentException {
		this.productID = numberOfProducts++;
		setBrand(brand);
		setModel(model);
		setColor(color);
		setPrice(price);
		setQuantityLeft(quantityLeft);
		setCategory(category);
		setCharacteristics(characteristics);
		this.attributesWithValues = new HashMap<String, String>();
	}

	public String displayProduct() {
		return this.toString();
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", brand=" + brand + ", model=" + model + ", color=" + color
				+ ", price=" + price + ", category=" + category + ", quantityLeft=" + quantityLeft + "]";
	}
	
	public int getId() {
		return this.productID;
	}

	public Brand getProductBrand() {
		return this.brand;
	}

	public double getPrice() {
		return this.price;
	}

	public Color getColor() {
		return color;
	}

	public Brand getBrand() {
		return brand;
	}

	public Category getCategory() {
		return category;
	}

	public int getProductID() {
		return productID;
	}

	public String getModel() {
		return model;
	}

	public int getQuantityLeft() {
		return quantityLeft;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	private void setBrand(Brand brand) throws EmagInvalidArgumentException {
		if (brand == null) {
			throw new EmagInvalidArgumentException("Product brand cannot be null or empty!");
		}
		this.brand = brand;
	}

	private void setModel(String model) throws EmagInvalidArgumentException {
		if (model == null || model.isEmpty()) {
			throw new EmagInvalidArgumentException("Product model cannot be null or empty!");
		}
		this.model = model;

	}

	private void setColor(Color color) throws EmagInvalidArgumentException {
		if (color == null) {
			throw new EmagInvalidArgumentException("Product color cannot be null or empty!");
		}
		this.color = color;
	}

	private void setPrice(double price) throws EmagInvalidArgumentException {
		if (price <= 0) {
			throw new EmagInvalidArgumentException("Product price should be higher than 0!");
		}
		this.price = price;
	}

	private void setCategory(Category category) throws EmagInvalidArgumentException {
		if (category == null) {
			throw new EmagInvalidArgumentException("No such category!");
		}
		this.category = category;
		category.addProduct(this);
	}

	private void setQuantityLeft(int quantityLeft) throws EmagInvalidArgumentException {
		if (quantityLeft < 0) {
			throw new EmagInvalidArgumentException("Product quantity cannot be negative!");
		}
		this.quantityLeft = quantityLeft;
	}

	public void setCharacteristics(String characteristics) throws EmagInvalidArgumentException {
		if (characteristics == null || characteristics.isEmpty()) {
			throw new EmagInvalidArgumentException("Product characteristics cannot be null or empty!");
		}
		this.characteristics = characteristics;
	}
	
	public void setAttributesWithValues(String attribute, String value) throws EmagInvalidArgumentException {
		if (attribute == null || attribute.isEmpty()) {
			throw new EmagInvalidArgumentException("Attribute cannot be null or empty!");
		}
		if (value == null || value.isEmpty()) {
			throw new EmagInvalidArgumentException("Value cannot be null or empty!");
		}
		this.attributesWithValues.put(attribute, value);
	}

	@Override
	public void addAttributesCharacteristics(String attributes, String characteristics) {
		// TODO Auto-generated method stub
		
	}
}
