package engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.apache.bcel.internal.generic.ISUB;

import dao.DBConnection;
import exceptions.EmagInvalidArgumentException;
import exceptions.ValidationException;

public class Validate {

	private static final String SELECT_ADMIN_USER = "SELECT * FROM users WHERE user_id=? AND is_admin=1;";
	private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE username=? AND password=?;";
	private static int currentUserId;

	public static boolean checkUserOnSignIn(String username, String password) {
		boolean exists = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DBConnection.getInstance().getCon();
			PreparedStatement ps = con.prepareStatement(SELECT_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			exists = rs.next();
			if (exists) {
				int userId = rs.getInt(1);
				currentUserId = userId;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	public static boolean isUserAdminById() throws ValidationException, EmagInvalidArgumentException {
		if (currentUserId == 0) {
			throw new EmagInvalidArgumentException("No such user!");
		}
		boolean isAdmin = false;
		Connection con = DBConnection.getInstance().getCon();
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_ADMIN_USER);
			ps.setInt(1, currentUserId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				isAdmin = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAdmin;
	}

}
