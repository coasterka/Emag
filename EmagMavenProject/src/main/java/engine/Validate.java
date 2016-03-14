package engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.DBConnection;

public class Validate {

	private static final String SELECT_USER_QUERY = "select * from users where username=? and password=?;";

	public static boolean checkUser(String username, String password) {
		boolean exists = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DBConnection.getInstance().getCon();
			PreparedStatement ps =con.prepareStatement(SELECT_USER_QUERY);
			ps.setString(1, username);
	        ps.setString(2, password);
	        ResultSet rs = ps.executeQuery();
	        exists = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}
}
