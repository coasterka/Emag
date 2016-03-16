package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.DBConnection;
import engine.Category;
import exceptions.CategoryDAOException;
import exceptions.EmagExistingObjectException;
import exceptions.EmagInvalidArgumentException;

//@WebServlet("/CreateCategory")
public class CreateCategory extends HttpServlet {
	private static final String RELOAD_PAGE_SCRIPT = "<script>document.location = \"./adminPages/createCategory.html\";</script>";
	private static final String EXISTING_CATEGORY_ERROR_MSG = "<script>alert(\"Category already exists!\");</script>";
	private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM categories WHERE name=?;";
	private static final String LOAD_HOME_PAGE_SCRIPT = "<script>document.location = \"./index.html\";</script>";
	private static final String CATEGORY_CREATED_MESSAGE = "<script>alert(\"Category is created successfully!\");</script>";
	private static final long serialVersionUID = 1L;
	private static PrintWriter out;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		String categoryName = request.getParameter("categoryName");

		if (!categoryExists(categoryName)) {
			try {
				insertCategory(categoryName);
				out.println(CATEGORY_CREATED_MESSAGE);
				out.println(LOAD_HOME_PAGE_SCRIPT);
			} catch (EmagInvalidArgumentException | CategoryDAOException e) {
				e.printStackTrace();
			}
		} else {
//			throw new EmagExistingObjectException("Category already exists!");
			out.println(EXISTING_CATEGORY_ERROR_MSG);
			out.println(RELOAD_PAGE_SCRIPT);
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
			PreparedStatement ps = con.prepareStatement(GET_CATEGORY_BY_NAME);
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

	private void insertCategory(String categoryName) throws EmagInvalidArgumentException, CategoryDAOException {
		Category newCategory = new Category(categoryName);
		CategoryDAO dao = new CategoryDAO();
		dao.addCategory(newCategory);
	}

}
