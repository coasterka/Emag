package classes;

import java.util.ArrayList;

import exceptions.EmagInvalidArgumentException;

public class Order {
	
	private static int numberOfOrders = 0;
	
	private int orderID;
	private User user;
	private double orderSum;
	private ArrayList<CartItem> cartItems;
	private int quantity;
	
	public Order(){
		cartItems = new ArrayList<CartItem>();
		Order.numberOfOrders++;
	}
	
	public void makeAnOrder(ArrayList<CartItem> cartItems) {
		if(cartItems != null){
			for(CartItem cartItem : cartItems) {
				if (cartItem != null){
					quantity++;
					orderSum += cartItem.getMoneyAmount();
					this.cartItems.add(cartItem);
				}
			}
		}
	}
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public double getOrderSum() {
		return this.orderSum;
	}
	
	public void setUser(User user) throws EmagInvalidArgumentException {
		if (user == null) {
			throw new EmagInvalidArgumentException("No such user!");
		}
		this.user = user;
	}
}