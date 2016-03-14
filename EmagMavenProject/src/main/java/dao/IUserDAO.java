package dao;

import engine.User;
import exceptions.EmagInvalidArgumentException;
import exceptions.UserDAOException;

public interface IUserDAO {

	int addUser(User user) throws UserDAOException;

	void updateUser(User user) throws UserDAOException;

	void removeUser(int userId) throws UserDAOException;

	User getUserById(int userId) throws UserDAOException, EmagInvalidArgumentException;

}