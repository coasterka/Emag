package dao;

import java.util.ArrayList;
import java.util.List;

import engine.CartItem;

public class ShoppingCartDAO {
	private List<CartItem> cartItems = new ArrayList<CartItem>();

	public void addCartItem(CartItem cartItem) {
		if (cartItem != null) {
			cartItems.add(cartItem);
		}
	}
	
	public CartItem getCartItemById(Integer id) {
		if (id > 0) {
			return cartItems.get(id.intValue());
		}
		return null;
	}
	
	public int getCartItemsCount() {
		return cartItems.size();
	}
	
	public void removeCartItemAtId(Integer id) {
		cartItems.remove(id.intValue());
	}
}
