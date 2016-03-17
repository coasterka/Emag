package engine;

import exceptions.EmagInvalidArgumentException;
import exceptions.EmagInvalidPassException;

public interface IUser {
	boolean validateLogin(String username, String password) throws EmagInvalidPassException;
	void changePassword(String oldPass, String passwordNew1, String passwordNew2)
			throws EmagInvalidPassException, EmagInvalidArgumentException;
	void updateProfile(String name, String username) throws EmagInvalidArgumentException;
}
