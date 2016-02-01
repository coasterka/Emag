package proekt.classes;

import proekt.exceptions.EmagInvalidArgumentException;

public class Order {
	
	private static int numberOfOrders = 0;
	
	private int orderID;
	private User user;
	private double orderSum;
	
	
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
