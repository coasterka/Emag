package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;
import exceptions.EmagInvalidArgumentException;

public class CreateCategory extends HttpServlet {
	private static final String INSERT_CATEGORY_QUERY = "INSERT INTO categories VALUES (null, 1, ?);";
	private static final long serialVersionUID = 1L;
	private static PrintWriter out;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String categoryName = request.getParameter("categoryName");

		if (!categoryExists(categoryName)) {
			insertCategory(categoryName);
		} else {
			out.println("<h1 style=\"color: #FFF\" align=\"center\">Category " + categoryName
					+ " is created successfully!</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
	}

	private boolean categoryExists(String categoryName) {
		boolean categoryExists = false;
		if (categoryName == null || categoryName.isEmpty()) {
			try {
				throw new EmagInvalidArgumentException("Invalid category name!");
			} catch (EmagInvalidArgumentException e) {
				e.printStackTrace();
			}
		}
		Connection con = DBConnection.getInstance().getCon();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM categories WHERE name=?;");
			ps.setString(1, categoryName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				categoryExists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryExists;
	}

	private void insertCategory(String categoryName) {
		Connection con = DBConnection.getInstance().getCon();
		try {
			PreparedStatement ps = con.prepareStatement(INSERT_CATEGORY_QUERY);
			ps.setString(1, categoryName);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
