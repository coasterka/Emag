package proekt.classes;

import proekt.exceptions.EmagInvalidArgumentException;
import proekt.products.Product;

public class CartItem {
	private int cartItemID;
	private Product product;
	private int quantityOrdered;
	private double moneyAmount;
	
	public CartItem(Product product, int quantityOrdered) throws EmagInvalidArgumentException {
		setProduct(product);
		setQuantityOrdered(quantityOrdered);
		setMoneyAmount();
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	private void setProduct(Product product) throws EmagInvalidArgumentException {
		if (product == null) {
			throw new EmagInvalidArgumentException("Product can not be null!");
		}
		this.product = product;
	}
	
	public double getQuantityOrdered() {
		return this.quantityOrdered;
	}
	
	private void setQuantityOrdered(int quantityOrdered) throws EmagInvalidArgumentException {
		if (quantityOrdered <= 0) {
			throw new EmagInvalidArgumentException("Product quantity can not be negative or equal to 0!");
		}
		this.quantityOrdered = quantityOrdered;
	}
	
	public double getMoneyAmount() {
		return this.moneyAmount;
	}
	
	private void setMoneyAmount() throws EmagInvalidArgumentException {
		if (this.moneyAmount <= 0) {
			throw new EmagInvalidArgumentException("Money amount can not be negative or equal to 0!");
		}
		if (this.product.getPrice() <= 0) {
			throw new EmagInvalidArgumentException("Product price can not be negative or equal to 0!");
		}
		this.moneyAmount = quantityOrdered * this.product.getPrice();
	}
}
