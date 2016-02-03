package classes;

import exceptions.EmagInvalidPassException;

public interface  IUser {
	void displayCart();
	boolean validateLogin(String username, String password) throws EmagInvalidPassException;
	public void updateProfile(String name, String username, String address);
	public void changePassword(String oldPass, String passwordNew1, String passwordNew2);
}
