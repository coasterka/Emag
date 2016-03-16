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
import dao.UserDAO;
import engine.Customer;
import engine.User;
import exceptions.EmagInvalidArgumentException;
import exceptions.EmagInvalidPassException;
import exceptions.UserDAOException;

public class SignUp extends HttpServlet {

	private static final String EXISTING_USER_MESSAGE = "<script>alert(\"This username is already taken! Please choose another one!\");</script>";
	private static final String SUCCESS_MESSAGE = "<script>alert(\"Registration is successful!\");</script></h1>";
	private static final String PASSWORD_DONT_MATCH_MESSAGE = "<script>alert(\"Passwords didn\'t match!\");</script>";
	private static final String NOT_ALL_FIELDS_ARE_FILLED_SCRIPT = "<script>alert(\"All fields are required!\");</script>";
	private static final String BACK_TO_INDEX_SCRIPT = "<script>document.location = \"./index.html\";</script>";
	private static final String INVALID_PASSWORD_MESSAGE_SCRIPT = "<script>alert(\"Password must have at least one digit, one uppercase and one lowercase character!\")</script>";
	private static final String RELOAD_PAGE_SCRIPT = "<script>document.location = \"./signUp.html\";</script>";
	private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE username=?";
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final String INVALID_PASSWORD_LENGTH_MESSAGE = "<script>alert(\"Password must be at least "
			+ MIN_PASSWORD_LENGTH + " characters long!\")</script>";
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
			out.println(NOT_ALL_FIELDS_ARE_FILLED_SCRIPT);
			out.println(RELOAD_PAGE_SCRIPT);
		}

		if (!arePasswordsTheSame(password1, password2)) {
			out.println(PASSWORD_DONT_MATCH_MESSAGE);
			out.println(RELOAD_PAGE_SCRIPT);
		}

		if (userExists(username)) {
			out.println(EXISTING_USER_MESSAGE);
			out.println(RELOAD_PAGE_SCRIPT);
		}

		try {
			if (!userExists(username) && arePasswordsTheSame(password1, password2)
					&& checkPasswordStrength(password1)) {
				Connection con = DBConnection.getInstance().getCon();
				// (null, 1, ?, ?, ?, ?, 0, ?)
				User newUser = new Customer(null, name, username, password1, address); // register
																						// a
																						// regular
																						// customer
																						// -
																						// not
																						// an
																						// admin
				UserDAO dao = new UserDAO();
				dao.addUser(newUser);
				out.println(SUCCESS_MESSAGE);
				out.println(BACK_TO_INDEX_SCRIPT);
				// RequestDispatcher rd =
				// request.getRequestDispatcher("index.html");
				// rd.include(request, response);
			}
		} catch (EmagInvalidPassException | EmagInvalidArgumentException | UserDAOException e) {
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

		// if (password.isEmpty()) {
		// throw new EmagInvalidPassException("Password cannot be empty!");
		// }

		boolean isLongEnough = password.length() < MIN_PASSWORD_LENGTH;
		boolean hasUppercase = !password.equals(password.toLowerCase());
		boolean hasLowercase = !password.equals(password.toUpperCase());
		boolean hasDigit = password.matches(".*\\d+.*");

		if (isLongEnough || !hasUppercase || !hasLowercase || !hasDigit) {
			if (isLongEnough) {
				out.println(INVALID_PASSWORD_LENGTH_MESSAGE);
				out.println(RELOAD_PAGE_SCRIPT);
				isStrongEnough = false;
			}
			if (!hasUppercase || !hasLowercase || !hasDigit) {
				out.println(INVALID_PASSWORD_MESSAGE_SCRIPT);
				out.println(RELOAD_PAGE_SCRIPT);
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
