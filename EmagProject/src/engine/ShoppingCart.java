package engine;

import java.util.ArrayList;
import products.IProduct;
import products.Product;

public class ShoppingCart {

	private double moneyInCart;
	private ArrayList<CartItem> cartItems;

	private int productsCount = 0;

	public ShoppingCart() {
		this.moneyInCart = 0;
		this.cartItems = new ArrayList<CartItem>();
	}
	
	public void addCartItem(CartItem cartItem) {
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
		this.cartItems.clear(); //should remove all products from cart
	}
	
	public ArrayList<CartItem> getCartItems() {
		return new ArrayList<CartItem>(this.cartItems);
	}
}