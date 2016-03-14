package engine;

import exceptions.EmagInvalidArgumentException;
import exceptions.EmagInvalidPassException;
import products.IProduct;

public class Customer extends User implements ICustomer {

	private String address;
	private ShoppingCart shoppingCart;
	private Order order;

	public Customer(String name, String username, String password, String address) throws EmagInvalidArgumentException {
		super(name, username, password);
		setAddress(address);
		this.shoppingCart = new ShoppingCart();
		super.setIsAdmin(false);
	}
	
	@Override
	public void displayCart() {
		if (this.shoppingCart != null) {
			for (CartItem cartItem : shoppingCart.getCartItems()) {
				System.out.println("Product brand: " + cartItem.getProduct().getProductBrand() + "\n Quantity ordered: "
						+ cartItem.getQuantityOrdered() + "\n Price:" + cartItem.getMoneyAmount());
			}
		}
	}
	
	@Override
	public void makeAnOrder() throws EmagInvalidArgumentException {
		if (this.order == null) {
			this.order = new Order();
			this.order.makeAnOrder(this.shoppingCart.getCartItems());
			this.shoppingCart.clearAll();
			this.order.setUser(this);
		} else {
			this.order.makeAnOrder(this.shoppingCart.getCartItems());
			this.shoppingCart.clearAll();
		}
	}

	@Override
	public Order checkOut() throws EmagInvalidArgumentException {
		if (order == null) {
			throw new EmagInvalidArgumentException("User doesn't have an order!");
		}
		Order copy = new Order();
		copy = this.order;
		return copy;
	}
	
	public void setAddress(String address) throws EmagInvalidArgumentException {
		if (address == null || address.isEmpty()) {
			throw new EmagInvalidArgumentException("Please enter a valid address!");
		}
		this.address = address;
	}

	public ShoppingCart getCart() {
		return shoppingCart;
	}

	public String getAddress() {
		return address;
	}
	
	
}
