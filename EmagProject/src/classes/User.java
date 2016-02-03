package classes;

import exceptions.EmagInvalidArgumentException;
import exceptions.EmagInvalidPassException;
import products.IProduct;

public class User {

	private static final int MIN_LENGTH_FOR_PASSWORDS = 6;

	private static int numberOfCustomers = 1;

	private final int customerID;
	private String name;
	private String userName;
	private String password;
	private String address;
	private ShoppingCart shoppingCart;
	private Order order;

	public User(String name, String username, String password, String address) throws EmagInvalidArgumentException {
		this.customerID = numberOfCustomers++; // or update automatically from DB?
		setName(name);
		setUserName(username);
		setAddress(address);
		setPassword(password);
		this.shoppingCart = new ShoppingCart();

	}

	public boolean validateLogin(String username, String password) throws EmagInvalidPassException {
		if (this.getUserName().equals(username) && this.password.equals(password)) {
			return true;
		}
		throw new EmagInvalidPassException("Invalid username or password!");
	}

	public void displayCart() {
		if (this.shoppingCart != null) {
			for (CartItem cartItem : shoppingCart.getCartItems()) {
				// не съм добавил гетър за бройката и парите в картАйтъм
				System.out.println("Product brand: " + cartItem.getProduct().getProductBrand() + "\n Quantity ordered: "
						+ cartItem.getQuantityOrdered() + "\n Price:" + cartItem.getMoneyAmount());
			}
		}
	}

	public void updateProfile(String name, String username, String address) throws EmagInvalidArgumentException {
		setName(name);
		setUserName(username);
		setAddress(address);
	}

	public void changePassword(String oldPass, String passwordNew1, String passwordNew2)
			throws EmagInvalidArgumentException, EmagInvalidPassException {
		if (!oldPass.equals(this.password) || !passwordNew1.equals(passwordNew2)) {
			throw new EmagInvalidPassException("Wrong password!");
		}
		setPassword(passwordNew1);
	}

	public void makeAnOrder() throws EmagInvalidArgumentException {
		if (this.order == null) {
			this.order = new Order();
			this.order.makeAnOrder(this.shoppingCart.getCartItems());// задавам
																		// поръчка
			this.shoppingCart.clearAll();
			this.order.setUser(this);
		} else {
			this.order.makeAnOrder(this.shoppingCart.getCartItems());
			this.shoppingCart.clearAll();
		}
	}

	public Order checkOut() throws EmagInvalidArgumentException {
		if (order == null) {
			throw new EmagInvalidArgumentException("User doesn't have an order!");
		}
		Order copy = new Order();
		copy = this.order;
		return copy;
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) throws EmagInvalidArgumentException {
		if (name == null || name.isEmpty()) {
			throw new EmagInvalidArgumentException("Please insert a valid name!");
		}
		this.name = name;
	}

	public String getUserName() {
		return this.userName;
	}

	private void setUserName(String userName) throws EmagInvalidArgumentException {
		if (userName == null || userName.isEmpty()) {
			throw new EmagInvalidArgumentException("Please insert valid username!");
		}
		this.userName = userName;
	}

	private void setPassword(String password) throws EmagInvalidArgumentException {
		if (password.length() < MIN_LENGTH_FOR_PASSWORDS || password == null) {

			throw new EmagInvalidArgumentException("Please choose a valid password!");
		}
		this.password = password;
	}

	public String getAddress() {
		return this.address;
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
}
