package classes;

import java.util.ArrayList;
import products.IProduct;
import products.Product;

public class ShoppingCart {

	private double moneyInCart;
	private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();

	private int productsCount = 0;

	public void addProduct(CartItem cartItem) {
		if (cartItem != null) {
			cartItems.add(cartItem);
			productsCount++;
			moneyInCart += cartItem.getMoneyAmount();
		}
	}

	public void removeProduct(CartItem cartItem) {
		if (cartItem != null) {
			moneyInCart -= cartItem.getMoneyAmount();
			cartItems.remove(cartItem);
			productsCount--;
		}
	}
	
	public void clearAll() {
		this.cartItems.clear();//трябва да премахва всички продукти 
	}
	
	public ArrayList<CartItem> getCartItems() {
		return new ArrayList<CartItem>(this.cartItems);
	}
}