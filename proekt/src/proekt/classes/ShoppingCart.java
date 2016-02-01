package proekt.classes;

import java.util.ArrayList;
import proekt.products.IProduct;
import proekt.products.Product;

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
}