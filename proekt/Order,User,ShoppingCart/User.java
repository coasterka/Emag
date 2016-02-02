package proekt.classes;

import proekt.exceptions.EmagInvalidArgumentException;
import proekt.exceptions.EmagInvalidPassException;

public class User {

	private static final int MIN_LENGTH_FOR_PASSWORDS = 6;

	private static int numberOfCutomers = 1;

	private final int customerID;
	private String firstName;
	private String userName;
	private String password;
	private String address;
	private ShoppingCart cart;
	private Order order;

	public User(String name, String username, String password, String address) throws EmagInvalidArgumentException {
		this.customerID = numberOfCutomers++;
		this.setName(name);
		this.setUserName(username);
		this.setAddress(address);
		this.setPassword(password);
		this.cart = new ShoppingCart();

	}

	public boolean validateLogin(String username, String password) throws EmagInvalidPassException {
		if (this.getUserName().equals(username) && this.password.equals(password)) {
			return true;
		} else {
			throw new EmagInvalidPassException("Invalid username or password!");
		}
	}

	public void displayCart() {
		if (this.cart != null) {
			for (CartItem cart : this.cart.getCart()) {// добавил съм гетър в
														// катайтъмс
				for (IProduct product : cart.getProduct) {
					//не съм добавил гетър за бройката и парите в картАйтъм
					System.out.println("Product name " + product.getName + " quantityOrdered" + cart.getQuantityOrdered
							+ " Cost" + cart.getMoneyAmount);
				}
			}
		}

	}

	public void updateProfile(String name, String username, String address) throws EmagInvalidArgumentException {
		this.setName(name);
		this.setUserName(username);
		this.setAddress(address);
	}

	public void changePassword(String oldPass, String passwordNew1, String passwordNew2)
			throws EmagInvalidArgumentException, EmagInvalidPassException {
		if (oldPass.equals(this.password) && passwordNew1.equals(passwordNew2)) {
			this.setPassword(passwordNew1);
		} else {
			throw new EmagInvalidPassException("Wrong password!");
		}
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public String getName() {
		return this.firstName;
	}

	public void setName(String name) throws EmagInvalidArgumentException {
		if (name != null && !name.equals("")) {
			this.firstName = name;
		} else {
			throw new EmagInvalidArgumentException("Please insert valid name!");
		}

	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) throws EmagInvalidArgumentException {
		if (userName != null && !userName.equals("")) {
			this.userName = userName;
		} else {
			throw new EmagInvalidArgumentException("Please insert valid username!");
		}
	}

	public void setPassword(String password) throws EmagInvalidArgumentException {
		if (password.length() >= MIN_LENGTH_FOR_PASSWORDS && password != null) {
			this.password = password;
		} else {
			throw new EmagInvalidArgumentException("Please choose valid password!");
		}

	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) throws EmagInvalidArgumentException {
		if (address != null && !address.equals("")) {
			this.address = address;
		} else {
			throw new EmagInvalidArgumentException("Please add valid address!");
		}
	}

	public ShoppingCart getCart() {
		return cart;
	}

	// Задавам порачка като при задаването на нова порачка изпразвам количката
	public void makeAnOrder(){
		if(this.order==null){
			this.order=new Order;
			this.order.makeAnOrder(this.cart.getCartItems());//задавам поръчка
			this.cart.clearAll();
			this.order.setUser(this);
		}
		else{
			this.order.makeAnOrder(this.cart.getCartItems());
			this.cart.clearAll();
		}
		
	}

	public Order checkOut(){
		if(order!=null){
			Order copy=new Order;
			copy=this.order;
			return copy;
		}
	}

}

