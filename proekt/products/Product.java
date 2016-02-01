package proekt.products;

import proekt.classes.Category;
import proekt.exceptions.EmagInvalidArgumentException;

public abstract class Product implements IProduct {
	
	private static int numberOfProducts = 0;
	
	private final int productID;
	private String brand;
	private String model;
	private String color;
	private double price;
	private Category cat;
	private int quantityLeft;
	
	
	public Product(String brand, String model, String color, double price
			, int quantityLeft, Category cat) throws EmagInvalidArgumentException {
		this.productID = ++numberOfProducts;
		this.setBrand(brand);
		this.setModel(model);
		this.setColor(color);
		this.setPrice(price);
		this.setCat(cat);
		this.setQuantityLeft(quantityLeft);
	}
	
	@Override
	public int getProductID() {		
		return this.productID;
	}
	
	@Override
	public String getProductBrand() {		
		return this.brand;
	}
	
	@Override
	public String getProductModel() {
		return this.model;
	}
	@Override
	public String getProductColor() {		
		return this.color;
	}
	@Override
	public double getPrice() {		
		return this.price;
	}
	@Override
	public void displayProduct() {
		System.out.println("brand=" + brand + ", model=" + model + ", color=" + color + ", price=" + price);
		
	}	
	@Override
	public String getProductCategory() {		
		return this.cat.getName();
	}
	
	@Override
	public int getQuantityLeft() {		
		return this.quantityLeft;
	}
	
	

	public void setBrand(String brand) throws EmagInvalidArgumentException {
		if (brand != null && !brand.equals("")) {
			this.brand = brand;
		}
		else {
			throw new EmagInvalidArgumentException("Invalid brand!");
		}
		
	}
	public void setModel(String model) throws EmagInvalidArgumentException {
		if (model != null && !model.equals("")) {
			this.model = model;
		}
		else {
			throw new EmagInvalidArgumentException("Invalid model!");
		}
		
	}
	public void setColor(String color) throws EmagInvalidArgumentException {
		if (color != null && !color.equals("")) {
			this.color = color;
		}
		else {
			throw new EmagInvalidArgumentException("Invalid color!");
		}
		
	}
	public void setPrice(double price) throws EmagInvalidArgumentException {
		if (price > 0) {
			this.price = price;
		}
		else {
			throw new EmagInvalidArgumentException("Price should be higher than 0!");
		}
		
	}

	public void setCat(Category cat) throws EmagInvalidArgumentException {
		if (cat != null) {
			this.cat = cat;
			cat.addProduct(this);
		}
		else {
			throw new EmagInvalidArgumentException("No such category!");
		}
		
	}

	public void setQuantityLeft(int quantityLeft) throws EmagInvalidArgumentException {
		if (quantityLeft >= 0) {
			this.quantityLeft = quantityLeft;
		}
		else {
			throw new EmagInvalidArgumentException("Invalid quantity!");
		}
		
	}
	

}
