package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import engine.Admin;
import engine.Catalog;
import engine.Customer;
import engine.User;
import exceptions.EmagInvalidArgumentException;
import exceptions.UserDAOException;

public class UserDAO extends AbstractDAO implements IUserDAO {
	
	private static final String INSERT_NEW_USER_QUERY = "INSERT INTO users VALUES (null, 1, ?, ?, ?, ?, 0, ?);";
	private static final String UPDATE_USER_QUERY = "UPDATE users SET name = ?, username = ?, password = ?, address = ? WHERE id = ?;";
	private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users WHERE user_id = ?";

	
	@Override
	public int addUser(User user) throws UserDAOException {
		if (user != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(INSERT_NEW_USER_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS);
				
//				if (user.isAdmin()) {
//					ps.setInt(1, ((Admin)user).getCatalog().getId());
//				}
				ps.setString(1, null); // shopping_cart_id; newly created users don't have a shopping cart
				ps.setString(2, user.getName());
				ps.setString(3, user.getUserName());
				ps.setString(4, user.getPassword());
				ps.setString(5, user.getAddress());
//				if (!user.isAdmin()) {
//					ps.setString(6, ((Customer)user).getAddress());
//				}
				ps.executeUpdate();

				ResultSet result = ps.getGeneratedKeys();
				result.next();
				return result.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
				throw new UserDAOException("The user cannot be added right now. Thank you.", e);
			}
		} else {
			throw new UserDAOException("No such user!");
		}
	}
	
	
	@Override
	public void updateUser(User user) throws UserDAOException {
		if (user != null) {
			try {
				PreparedStatement ps = getCon().prepareStatement(UPDATE_USER_QUERY);

				ps.setString(1, user.getName());
				ps.setString(2, user.getUserName());
				ps.setString(3, user.getPassword());
				if (!user.isAdmin()) {
					ps.setString(4, ((Customer)user).getAddress());
				}
				else {
					ps.setString(4, null);
				}
				
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new UserDAOException("The driver cannot be updated right now. Thank you.", e);
			}
		}
		else {
			throw new UserDAOException("No such User");
		}
	}
	
	@Override
	public void removeUser(int userId) throws UserDAOException {
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement("DELETE FROM users WHERE id = ?");
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new UserDAOException("The driver cannot be deleted right now. Thank you.", e);
		}
	}
	
	@Override
	public User getUserById(int userId) throws UserDAOException, EmagInvalidArgumentException{
		User wantedUser = null;

		try {
			PreparedStatement ps = getCon().prepareStatement(FIND_USER_BY_ID_QUERY);
			ps.setInt(1, userId);
			ResultSet result = ps.executeQuery();
			result.next();

			String name = result.getString(3);
			String username = result.getString(4);
			String password = result.getString(5);
			if (result.getBoolean(6) == true) {				
				wantedUser = new Admin(name, username, password);
				wantedUser.setIsAdmin(true);
				((Admin)wantedUser).setCatalog(Catalog.createCatalog());
			}
			else {
//				String address = result.getString(7);
				wantedUser = new Customer(name, username, password);
				wantedUser.setIsAdmin(false);
			}
			
			return wantedUser;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserDAOException("The user with id " + userId + " cannot be found . Thank you.", e);
		}
	}

}
