package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;
import exceptions.EmagExistingObjectException;
import exceptions.EmagInvalidPassException;

public class SignUp extends HttpServlet {

	private static final String INSERT_USER_QUERY = "INSERT INTO users VALUES (null, 1, ?, ?, ?, ?, 0, ?);";
	private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE username=?";
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final long serialVersionUID = 1L;
	private static PrintWriter out;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String address = request.getParameter("address");

		if (username.isEmpty() || password1.isEmpty() || password2.isEmpty() || name.isEmpty() || address.isEmpty()) {
			out.println("<script>alert(\"All fields are required!\")</script>");
		}

		if (userExists(username)) {
			try {
				throw new EmagExistingObjectException("You are already registered!");
			} catch (EmagExistingObjectException e) {
				e.printStackTrace();
			}
		}

		try {
			if (!userExists(username) && arePasswordsTheSame(password1, password2)
					&& checkPasswordStrength(password1)) {
				Connection con = DBConnection.getInstance().getCon();
				// (null, 1, ?, ?, ?, ?, 0, ?)
				PreparedStatement ps = con.prepareStatement(INSERT_USER_QUERY);
				ps.setString(1, null);
				ps.setString(2, name);
				ps.setString(3, username);
				ps.setString(4, password1);
				ps.setString(5, address);
				out.println("User is created successfully!");
			}
		} catch (EmagInvalidPassException | SQLException e) {
			e.printStackTrace();
		}

	}

	private boolean userExists(String username) {
		boolean userExists = false;
		Connection con = DBConnection.getInstance().getCon();
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_USER_QUERY);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userExists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userExists;
	}

	private boolean arePasswordsTheSame(String pass1, String pass2) {
		boolean theSame = false;
		if (pass1.equals(pass2)) {
			theSame = true;
		}
		return theSame;
	}

	private boolean checkPasswordStrength(String password) throws EmagInvalidPassException {

		boolean isStrongEnough = true;

		if (password.isEmpty()) {
			throw new EmagInvalidPassException("Password cannot be empty!");
		}

		boolean isLongEnough = password.length() < MIN_PASSWORD_LENGTH;
		boolean hasUppercase = !password.equals(password.toLowerCase());
		boolean hasLowercase = !password.equals(password.toUpperCase());
		boolean hasDigit = password.matches(".*\\d+.*");

		if (isLongEnough || !hasUppercase || !hasLowercase || !hasDigit) {
			if (isLongEnough) {
				out.println("<script>alert(\"Password must be at least " + MIN_PASSWORD_LENGTH + " characters long!\")</script>");
				isStrongEnough = false;
			}
			if (!hasUppercase || !hasLowercase || !hasDigit) {
				out.println("<script>alert(\"Password must have at least one digit, one uppercase and one lowercase character!\")</script>");
				isStrongEnough = false;
			}
			// set the boolean values again for every new attempt
			isLongEnough = password.length() < MIN_PASSWORD_LENGTH;
			hasUppercase = !password.equals(password.toLowerCase());
			hasLowercase = !password.equals(password.toUpperCase());
			hasDigit = password.matches(".*\\d+.*");
		}

		return isStrongEnough;
	}
}
