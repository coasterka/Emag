package proekt.classes;

import java.util.ArrayList;

import proekt.exceptions.EmagInvalidArgumentException;

public class Order {
	
	private static int numberOfOrders = 0;
	
	private int orderID;
	private User user;
	private double orderSum;
	private ArrayList<CartItem> cartItems;
	private int broi;
	
	public Order(){
		cartItems=new ArrayList<CartItems>();
		this.numberOfOrders++;
	}
	
	public void makeAnOrder(ArrayList<CartItem> cartItems){
		if(products!=null){
			for(CartItem cartItem:cartItems){
				if(cartItem!=null){
					broi++;
					orderSum=+cartItem.getMoneyAmount;
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
		if (user != null) {
			this.user = user;
		}
		else {
			throw new EmagInvalidArgumentException("No such user!");
		}
		
	}
	

}
