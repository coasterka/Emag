package products;

import engine.Category;
import exceptions.EmagInvalidArgumentException;

public abstract class Product implements IProduct {

	private static int numberOfProducts = 1;

	private final int productID;
	private String type;
	private String brand;
	private String model;
	private String color;
	private double price;
	private Category category;
	private int quantityLeft;

	public Product(String type, String brand, String model, String color, double price, int quantityLeft, Category category)
			throws EmagInvalidArgumentException {
		this.productID = numberOfProducts++;
		setType(type);
		setBrand(brand);
		setModel(model);
		setColor(color);
		setPrice(price);
		setCategory(category);
		setQuantityLeft(quantityLeft);
	}
	
	public String displayProduct() {
		return this.toString();
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", brand=" + brand + ", model=" + model + ", color=" + color
				+ ", price=" + price + ", category=" + category + ", quantityLeft=" + quantityLeft + "]";
	}

	public String getProductBrand() {
		return this.brand;
	}

	public String getProductModel() {
		return this.model;
	}

	public String getProductColor() {
		return this.color;
	}

	public double getPrice() {
		return this.price;
	}

	public String getProductCategory() {
		return this.category.getName();
	}

	public int getQuantityLeft() {
		return this.quantityLeft;
	}
	
	private void setType(String type) throws EmagInvalidArgumentException {
		if (type == null || type.isEmpty()) {
			throw new EmagInvalidArgumentException("Product type can not be null or empty!");
		}
		this.type = type;
	}

	public void setBrand(String brand) throws EmagInvalidArgumentException {
		if (brand == null || brand.isEmpty()) {
			throw new EmagInvalidArgumentException("Brand can not be null or empty!");
		}
		this.brand = brand;
	}

	public void setModel(String model) throws EmagInvalidArgumentException {
		if (model == null || model.isEmpty()) {
			throw new EmagInvalidArgumentException("Model can not be null or empty!");
		}
		this.model = model;

	}

	public void setColor(String color) throws EmagInvalidArgumentException {
		if (color == null || color.isEmpty()) {
			throw new EmagInvalidArgumentException("Color can not be null or empty!");
		}
		this.color = color;
	}

	public void setPrice(double price) throws EmagInvalidArgumentException {
		if (price <= 0) {
			throw new EmagInvalidArgumentException("Price should be higher than 0!");
		}
		this.price = price;
	}

	public void setCategory(Category category) throws EmagInvalidArgumentException {
		if (category == null) {
			throw new EmagInvalidArgumentException("No such category!");
		}
		this.category = category;
		category.addProduct(this);
	}

	public void setQuantityLeft(int quantityLeft) throws EmagInvalidArgumentException {
		if (quantityLeft < 0) {
			throw new EmagInvalidArgumentException("Quantity can not be negative!");
		}
		this.quantityLeft = quantityLeft;
	}

}
