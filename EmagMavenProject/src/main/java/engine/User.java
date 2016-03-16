package engine;

import exceptions.EmagInvalidArgumentException;
import exceptions.EmagInvalidPassException;

public abstract class User implements IUser {
	
	private static int numberOfCustomers = 1;
	private static final int MIN_LENGTH_FOR_PASSWORDS = 6;

	private int customerID;
	private Catalog catalog;
	private ShoppingCart shoppingCart;
	private String name;
	private String userName;
	private String password;
	private String address;
	private boolean isAdmin;
	
	public User(String name, String username, String password) throws EmagInvalidArgumentException {
		this.customerID = numberOfCustomers++;
		setName(name);
		setUserName(username);
		setPassword(password);
	}
	
	public User(ShoppingCart shoppingCart, String name, String username, String password, String address) throws EmagInvalidArgumentException {
		this.customerID = numberOfCustomers++;
		this.shoppingCart = shoppingCart;
		setName(name);
		setUserName(username);
		setPassword(password);
		setAddress(address);
	}
	
	@Override
	public boolean validateLogin(String username, String password) throws EmagInvalidPassException {
		if (this.userName.equals(username) && this.password.equals(password)) {
			return true;
		}
		throw new EmagInvalidPassException("Invalid username or password!");
	}
	
	@Override
	public void updateProfile(String name, String username) throws EmagInvalidArgumentException {
		setName(name);
		setUserName(username);
	}
	
	@Override
	public void changePassword(String oldPass, String passwordNew1, String passwordNew2)
			throws EmagInvalidArgumentException, EmagInvalidPassException {
		if (!oldPass.equals(this.password) || !passwordNew1.equals(passwordNew2)) {
			throw new EmagInvalidPassException("Wrong password!");
		}
		setPassword(passwordNew1);
	}
	
	protected void setCurrentId(int currentId) {
		this.customerID = numberOfCustomers++;
	}
	
	public void setName(String name) throws EmagInvalidArgumentException {
		if (name == null || name.isEmpty()) {
			throw new EmagInvalidArgumentException("Please insert a valid name!");
		}
		this.name = name;
	}
	
	private void setUserName(String userName) throws EmagInvalidArgumentException {
		if (userName == null || userName.isEmpty()) {
			throw new EmagInvalidArgumentException("Please insert valid username!");
		}
		this.userName = userName;
	}
	
	private void setPassword(String password) throws EmagInvalidArgumentException {
		if (password == null || password.length() < MIN_LENGTH_FOR_PASSWORDS) {

			throw new EmagInvalidArgumentException("Please choose a valid password!");
		}
		this.password = password;
	}
	
	private void setAddress(String address) throws EmagInvalidArgumentException {
		if (address == null || address.isEmpty()) {
			throw new EmagInvalidArgumentException("Invalid address!");
		}
		this.address = address;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getCustomerID() {
		return customerID;
	}

	public String getName() {
		return name;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public String getAddress() {
		return address;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
}
